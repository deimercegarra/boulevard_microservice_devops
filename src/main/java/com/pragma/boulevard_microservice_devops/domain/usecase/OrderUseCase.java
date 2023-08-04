package com.pragma.boulevard_microservice_devops.domain.usecase;

import com.pragma.boulevard_microservice_devops.domain.api.IOrderServicePort;
import com.pragma.boulevard_microservice_devops.domain.exception.BadRequestException;
import com.pragma.boulevard_microservice_devops.domain.exception.DomainException;
import com.pragma.boulevard_microservice_devops.domain.model.*;
import com.pragma.boulevard_microservice_devops.domain.spi.*;
import com.pragma.boulevard_microservice_devops.infrastructure.configuration.Constants;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderUseCase implements IOrderServicePort {

    private final IOrderPersistencePort iOrderPersistencePort;
    private final IOrderDishPersistencePort iOrderDishPersistencePort;

    private final IEmployeePersistencePort iEmployeePersistencePort;
    private final IUserPersistencePort iUserPersistencePort;

    private final IMessagingPersistencePort iMessagingPersistencePort;

    public OrderUseCase(IOrderPersistencePort iOrderPersistencePort,
                        IOrderDishPersistencePort iOrderDishPersistencePort,
                        IEmployeePersistencePort iEmployeePersistencePort,
                        IUserPersistencePort iUserPersistencePort,
                        IMessagingPersistencePort iMessagingPersistencePort) {
        this.iOrderPersistencePort = iOrderPersistencePort;
        this.iOrderDishPersistencePort = iOrderDishPersistencePort;
        this.iEmployeePersistencePort = iEmployeePersistencePort;
        this.iUserPersistencePort = iUserPersistencePort;
        this.iMessagingPersistencePort = iMessagingPersistencePort;
    }

    @Override
    public CommonResponseModel saveOrder(OrderModel orderModel, List<OrderDishModel> orderDishModelList) {

        List<OrderModel> orderList = iOrderPersistencePort.findOrderInProcessByClient(  orderModel.getIdClient() ) ;

        if (!orderList.isEmpty()) {
            throw new DomainException("Order in process.");
        }

        orderModel.setDateOrder(new Date());
        orderModel.setStatusOrder(Constants.ORDER_STATUS_PENDING);

        orderModel = iOrderPersistencePort.saveOrder(orderModel);

        for ( OrderDishModel orderDishModel: orderDishModelList ) {
            orderDishModel.setOrderModel(orderModel);
            iOrderDishPersistencePort.saveOrderDish(orderDishModel, orderModel.getRestaurantModel().getId());
        }

        return new CommonResponseModel("201","CREATED.", true);
    }

    @Override
    public List<OrderModel> getOrderByStatus(String status, Long employeeId, Pageable pageable) {

        EmployeeModel employeeModel = iEmployeePersistencePort.findByIdEmployee( employeeId );
        Long idRestaurant = employeeModel.getRestaurantModel().getId();

        return iOrderPersistencePort.getOrderByStatus(idRestaurant, status, pageable);
    }

    @Override
    public List<OrderModel> assignToOrder(List<OrderModel> modelList, Long employeeId) {

        if (modelList.isEmpty()) {
            throw new DomainException("The sent list is empty.");
        }

        EmployeeModel employeeModel = iEmployeePersistencePort.findByIdEmployee(employeeId);

        if (employeeModel == null){
            throw new DomainException("An employee with the submitted id was not found.");
        }

        List<OrderModel> orderListUpdated = new ArrayList<>();

        for ( OrderModel order: modelList ) {

            order = iOrderPersistencePort.getOrder(order.getId());

            if (! (order.getRestaurantModel().getId().equals(employeeModel.getRestaurantModel().getId()))){
                throw new DomainException("One of the orders does not belong to the employee's restaurant.");
            }

            order.setIdChef(employeeModel.getId());
            order.setStatusOrder(Constants.ORDER_STATUS_PREPARATION);
            order = iOrderPersistencePort.saveOrder(order);

            orderListUpdated.add(order);
        }

        return orderListUpdated;
    }

    @Override
    public CommonResponseModel orderReady(Long orderId) {

        OrderModel orderModel = iOrderPersistencePort.getOrder(orderId);

        if (!orderModel.getStatusOrder().equals(Constants.ORDER_STATUS_PREPARATION)) {
            throw new BadRequestException("The order cannot be ready, it is not being prepared.");
        }

        UserModel clientUserModel = iUserPersistencePort.findUserById(orderModel.getIdClient()).getDto();

        orderModel.setStatusOrder(Constants.ORDER_STATUS_READY);
        iOrderPersistencePort.saveOrder(orderModel);

        iMessagingPersistencePort.sendCodeVerification(
                "Your order is ready.",
                clientUserModel.getPhone()
        );

        return new CommonResponseModel("200","OK.", true);
    }

    @Override
    public CommonResponseModel orderDelivered(Long orderId, String code) {

        OrderModel orderModel = iOrderPersistencePort.getOrder(orderId);

        if (!orderModel.getStatusOrder().equals(Constants.ORDER_STATUS_READY)) {
            throw new BadRequestException("The order cannot go to delivered, it is not in a ready state.");
        }

        UserModel clientUserModel = iUserPersistencePort.findUserById(orderModel.getIdClient()).getDto();

        String status = iMessagingPersistencePort.validateCodeVerification(code, clientUserModel.getPhone())
                .get(Constants.VERIFICATION_STATUS_KEY);

        if (!status.equals(Constants.APPROVED_STATUS)) {
            throw new BadRequestException("The code sent is invalid.");
        }

        orderModel.setStatusOrder(Constants.ORDER_STATUS_DELIVERED);
        iOrderPersistencePort.saveOrder(orderModel);

        return new CommonResponseModel("200","OK.", true);
    }

}