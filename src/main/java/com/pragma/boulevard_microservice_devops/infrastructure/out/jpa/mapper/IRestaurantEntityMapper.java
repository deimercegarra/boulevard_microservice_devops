package com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.mapper;


import com.pragma.boulevard_microservice_devops.domain.model.RestaurantModel;
import com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.entity.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IRestaurantEntityMapper {

    RestaurantEntity toEntity(RestaurantModel restaurantModel);
    RestaurantModel toModel(RestaurantEntity restaurantEntity);
    List<RestaurantModel> toModelList(List<RestaurantEntity> restaurantEntityList);

}