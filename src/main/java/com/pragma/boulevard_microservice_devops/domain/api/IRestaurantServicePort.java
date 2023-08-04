package com.pragma.boulevard_microservice_devops.domain.api;

import com.pragma.boulevard_microservice_devops.domain.model.CommonResponseModel;
import com.pragma.boulevard_microservice_devops.domain.model.RestaurantModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRestaurantServicePort {

    CommonResponseModel saveRestaurant(RestaurantModel restaurantModel);

    List<RestaurantModel> getAllRestaurants(Pageable pageable);

}