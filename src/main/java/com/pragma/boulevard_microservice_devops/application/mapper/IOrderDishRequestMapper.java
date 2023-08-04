package com.pragma.boulevard_microservice_devops.application.mapper;

import com.pragma.boulevard_microservice_devops.application.dto.request.OrderDishRequestDto;
import com.pragma.boulevard_microservice_devops.domain.model.OrderDishModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderDishRequestMapper {
    @Mapping(target = "dishModel.id", source = "idDish")
    OrderDishModel toOrderDishModel(OrderDishRequestDto orderDishRequestDto);

    List<OrderDishModel> toOrderDishModelList(List<OrderDishRequestDto> orderDishRequestDtoList);
}
