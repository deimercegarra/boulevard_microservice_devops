package com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.mapper;


import com.pragma.boulevard_microservice_devops.domain.model.OrderModel;
import com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IOrderEntityMapper {
    @Mapping(target = "restaurantEntity.id", source = "restaurantModel.id")
    OrderEntity toEntity(OrderModel orderModel);
    @Mapping(target = "restaurantModel.id", source = "restaurantEntity.id")
    OrderModel toModel(OrderEntity orderEntity);
    List<OrderModel> toModelList(List<OrderEntity> orderEntityList);

}