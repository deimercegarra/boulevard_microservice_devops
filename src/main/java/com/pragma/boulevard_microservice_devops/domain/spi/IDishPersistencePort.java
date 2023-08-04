package com.pragma.boulevard_microservice_devops.domain.spi;

import com.pragma.boulevard_microservice_devops.domain.model.DishModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDishPersistencePort {

    DishModel saveDish(DishModel dishModel);

    List<DishModel> getAllDishes();

    DishModel getDish(Long dishId);

    DishModel updateDish(DishModel dishModel);

    DishModel activeDish(DishModel dishModel);

    void deleteDish(Long dishId);

    List<DishModel> findDishesByRestaurantAndCategory(Long idRestaurant, Long idCategory, Pageable pageable);
}