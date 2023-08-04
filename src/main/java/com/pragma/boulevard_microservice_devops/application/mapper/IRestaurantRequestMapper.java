package com.pragma.boulevard_microservice_devops.application.mapper;

import com.pragma.boulevard_microservice_devops.application.dto.request.RestaurantRequestDto;
import com.pragma.boulevard_microservice_devops.domain.model.RestaurantModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantRequestMapper {
    RestaurantModel toRestaurantModel(RestaurantRequestDto restaurantRequestDto);

}
