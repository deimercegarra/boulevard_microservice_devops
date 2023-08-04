package com.pragma.boulevard_microservice_devops.domain.api;

import com.pragma.boulevard_microservice_devops.domain.model.CommonResponseModel;
import com.pragma.boulevard_microservice_devops.domain.model.OrderDishModel;
import com.pragma.boulevard_microservice_devops.domain.model.OrderModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderServicePort {

    CommonResponseModel saveOrder(OrderModel orderModel, List<OrderDishModel> orderDishModelList);

    List<OrderModel> getOrderByStatus(String status, Long employeeId, Pageable pageable);

    List<OrderModel> assignToOrder(List<OrderModel> modelList, Long employeeId);

    CommonResponseModel orderReady(Long orderId);

    CommonResponseModel orderDelivered(Long orderId, String code);
}