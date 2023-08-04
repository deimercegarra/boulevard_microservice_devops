package com.pragma.boulevard_microservice_devops.application.mapper;

import com.pragma.boulevard_microservice_devops.application.dto.request.OrderAssignRequestDto;
import com.pragma.boulevard_microservice_devops.domain.model.OrderModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderUpdateRequestMapper {

    OrderModel toModel(OrderAssignRequestDto orderAssignRequestDto);

    List<OrderModel> toModelList(List<OrderAssignRequestDto> orderAssignRequestDtoList);

}