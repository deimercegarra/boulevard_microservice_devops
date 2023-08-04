package com.pragma.boulevard_microservice_devops.domain.api;


import com.pragma.boulevard_microservice_devops.domain.model.OrderDishModel;

import java.util.List;

public interface IOrderDishServicePort {

    void saveOrderDish(OrderDishModel orderDishModel);

    List<OrderDishModel> getAllOrderDish();

    OrderDishModel getOrderDish(Long orderDishId);

    void updateOrderDish(OrderDishModel orderDishModel);

    void deleteOrderDish(Long orderDishId);

}