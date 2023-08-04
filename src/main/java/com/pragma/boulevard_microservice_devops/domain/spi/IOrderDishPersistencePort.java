package com.pragma.boulevard_microservice_devops.domain.spi;


import com.pragma.boulevard_microservice_devops.domain.model.OrderDishModel;

import java.util.List;

public interface IOrderDishPersistencePort {

    void saveOrderDish(OrderDishModel orderDishModel, Long idRestaurant);

    List<OrderDishModel> getAllOrderDish();

    OrderDishModel getOrderDish(Long orderDishId);

    void updateOrderDish(OrderDishModel orderDishModel);

    void deleteOrderDish(Long orderDishId);

}