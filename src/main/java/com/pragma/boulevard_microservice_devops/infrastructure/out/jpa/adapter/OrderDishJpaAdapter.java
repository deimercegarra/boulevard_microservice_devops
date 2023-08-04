package com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.adapter;

import com.pragma.boulevard_microservice_devops.domain.model.OrderDishModel;
import com.pragma.boulevard_microservice_devops.domain.spi.IOrderDishPersistencePort;
import com.pragma.boulevard_microservice_devops.infrastructure.exception.DishNotBelongRestaurantException;
import com.pragma.boulevard_microservice_devops.infrastructure.exception.DishNotFoundException;
import com.pragma.boulevard_microservice_devops.infrastructure.exception.NoDataFoundException;
import com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.entity.DishEntity;
import com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.entity.OrderDishEntity;
import com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.mapper.IOrderDishEntityMapper;
import com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.repository.IDishRepository;
import com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.repository.IOrderDishRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class OrderDishJpaAdapter implements IOrderDishPersistencePort {

    private final IOrderDishRepository iOrderDishRepository;
    private final IDishRepository iDishRepository;
    private final IOrderDishEntityMapper iOrderDishEntityMapper;

    @Override
    public void saveOrderDish(OrderDishModel orderDishModel, Long idRestaurant) {

        DishEntity dishEntity = iDishRepository.findById( orderDishModel.getDishModel().getId() ).orElseThrow(DishNotFoundException::new);

        if (!dishEntity.getRestaurantEntity().getId().equals(idRestaurant)){
            throw new DishNotBelongRestaurantException();
        }

        iOrderDishRepository.save(iOrderDishEntityMapper.toEntity(orderDishModel));
    }

    @Override
    public List<OrderDishModel> getAllOrderDish() {
        List<OrderDishEntity> entityList = iOrderDishRepository.findAll();
        if (entityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return iOrderDishEntityMapper.toModelList(entityList);
    }

    @Override
    public OrderDishModel getOrderDish(Long orderDishId) {
        return iOrderDishEntityMapper.toModel(iOrderDishRepository.findById(orderDishId)
                .orElseThrow(NoDataFoundException::new));
    }

    @Override
    public void updateOrderDish(OrderDishModel orderDishModel) {
        iOrderDishRepository.save(iOrderDishEntityMapper.toEntity(orderDishModel));
    }

    @Override
    public void deleteOrderDish(Long userId) {
        iOrderDishRepository.deleteById(userId);
    }

}