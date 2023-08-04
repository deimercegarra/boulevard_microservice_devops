package com.pragma.boulevard_microservice_devops.application.dto.request;

import com.pragma.boulevard_microservice_devops.infrastructure.configuration.Constants;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDishRequestDto {

    @NotNull(message = Constants.FIELD_NOT_NULL)
    private Long idDish;

    @NotNull(message = Constants.FIELD_NOT_NULL)
    @Positive(message = "The price must be a positive number.")
    @Digits(integer = Integer.MAX_VALUE, fraction = 0, message = "The price must be an integer.")
    private int quantity;

}
