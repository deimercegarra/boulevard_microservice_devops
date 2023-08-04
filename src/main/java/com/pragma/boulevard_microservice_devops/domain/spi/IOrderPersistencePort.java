package com.pragma.boulevard_microservice_devops.domain.spi;

import com.pragma.boulevard_microservice_devops.domain.model.OrderModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderPersistencePort {

    OrderModel saveOrder(OrderModel orderModel);

    List<OrderModel> getAllOrders();

    OrderModel getOrder(Long orderId);

    void updateOrder(OrderModel orderModel);

    void deleteOrder(Long orderId);

    List<OrderModel> findOrderInProcessByClient(Long idClient);

    List<OrderModel> getOrderByStatus(Long idRestaurant, String status, Pageable pageable);
}