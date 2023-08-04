package com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.adapter;

import com.pragma.boulevard_microservice_devops.domain.model.OrderModel;
import com.pragma.boulevard_microservice_devops.domain.spi.IOrderPersistencePort;
import com.pragma.boulevard_microservice_devops.infrastructure.configuration.Constants;
import com.pragma.boulevard_microservice_devops.infrastructure.exception.NoDataFoundException;
import com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.entity.OrderEntity;
import com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.mapper.IOrderEntityMapper;
import com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class OrderJpaAdapter implements IOrderPersistencePort {

    private final IOrderRepository iOrderRepository;
    private final IOrderEntityMapper iOrderEntityMapper;

    @Override
    public OrderModel saveOrder(OrderModel orderModel) {
        return iOrderEntityMapper.toModel(
                iOrderRepository.save(iOrderEntityMapper.toEntity(orderModel))
        );
    }

    @Override
    public List<OrderModel> getAllOrders() {
        List<OrderEntity> entityList = iOrderRepository.findAll();
        if (entityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return iOrderEntityMapper.toModelList(entityList);
    }

    @Override
    public OrderModel getOrder(Long userId) {
        return iOrderEntityMapper.toModel(iOrderRepository.findById(userId)
                .orElseThrow(NoDataFoundException::new));
    }

    @Override
    public void updateOrder(OrderModel orderModel) {
        iOrderRepository.save(iOrderEntityMapper.toEntity(orderModel));
    }

    @Override
    public void deleteOrder(Long userId) {
        iOrderRepository.deleteById(userId);
    }

    @Override
    public List<OrderModel> findOrderInProcessByClient(Long idClient) {
        return iOrderEntityMapper.toModelList(
                iOrderRepository.findByIdClientAndStatus(
                        idClient,
                        Constants.ORDER_STATUS_PENDING,
                        Constants.ORDER_STATUS_PREPARATION,
                        Constants.ORDER_STATUS_READY
                )
        );
    }

    @Override
    public List<OrderModel> getOrderByStatus(Long idRestaurant, String status, Pageable pageable) {

        List<OrderEntity> orderEntityList = iOrderRepository.getOrderByStatus(idRestaurant, status, pageable);

        if (orderEntityList.isEmpty()){
            throw new NoDataFoundException();
        }

        return iOrderEntityMapper.toModelList( orderEntityList );
    }

}