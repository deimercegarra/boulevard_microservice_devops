package com.pragma.boulevard_microservice_devops.application.dto.request;

import com.pragma.boulevard_microservice_devops.infrastructure.configuration.Constants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderRequestDto {

    @NotNull(message = Constants.FIELD_NOT_NULL)
    private Long idRestaurant;

    @NotNull(message = Constants.FIELD_NOT_NULL)
    private Long idCustomer;

    @NotNull(message = Constants.FIELD_NOT_NULL)
    private List<OrderDishRequestDto> orderDishRequestDtoList;

}
