package com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.mapper;


import com.pragma.boulevard_microservice_devops.domain.model.OrderDishModel;
import com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.entity.OrderDishEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IOrderDishEntityMapper {
    @Mapping(target = "orderEntity.id", source = "orderModel.id")
    @Mapping(target = "dishEntity.id", source = "dishModel.id")
    OrderDishEntity toEntity(OrderDishModel orderDishModel);
    @Mapping(target = "orderModel.id", source = "orderEntity.id")
    @Mapping(target = "dishModel.id", source = "dishEntity.id")
    OrderDishModel toModel(OrderDishEntity orderDishEntity);
    List<OrderDishModel> toModelList(List<OrderDishEntity> orderDishEntities);

}