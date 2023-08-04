package com.pragma.boulevard_microservice_devops.domain.spi;

import com.pragma.boulevard_microservice_devops.domain.model.RestaurantModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRestaurantPersistencePort {

    RestaurantModel saveRestaurant(RestaurantModel restaurantModel);

    List<RestaurantModel> getAllRestaurants(Pageable pageable);

    RestaurantModel getRestaurant(Long restaurantId);

    RestaurantModel updateRestaurant(RestaurantModel restaurantModel);

    void deleteRestaurant(Long restaurantId);

}