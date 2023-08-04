package com.pragma.boulevard_microservice_devops.application.mapper;

import com.pragma.boulevard_microservice_devops.application.dto.request.OrderRequestDto;
import com.pragma.boulevard_microservice_devops.domain.model.OrderModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderRequestMapper {
    @Mapping(target = "restaurantModel.id", source = "idRestaurant")
    @Mapping(target = "idClient", source = "idCustomer")
    OrderModel toOrderModel(OrderRequestDto orderRequestDto);

}
