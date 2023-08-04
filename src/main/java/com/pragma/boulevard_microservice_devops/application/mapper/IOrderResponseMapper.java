package com.pragma.boulevard_microservice_devops.application.mapper;

import com.pragma.boulevard_microservice_devops.application.dto.response.OrderResponseDto;
import com.pragma.boulevard_microservice_devops.domain.model.OrderModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderResponseMapper {
    @Mapping(target = "idRestaurant", source = "restaurantModel.id")
    OrderResponseDto toResponse(OrderModel orderModel);

    List<OrderResponseDto> toResponseList(List<OrderModel> orderModelList);

}
