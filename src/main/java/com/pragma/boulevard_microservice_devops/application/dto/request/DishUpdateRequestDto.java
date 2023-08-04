package com.pragma.boulevard_microservice_devops.application.dto.request;

import com.pragma.boulevard_microservice_devops.infrastructure.configuration.Constants;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishUpdateRequestDto {

    @NotNull(message = Constants.FIELD_NOT_NULL)
    private Long id;

    @NotNull(message = Constants.FIELD_NOT_NULL)
    @NotBlank(message = Constants.FIELD_NOT_BLANK)
    private String description;

    @NotNull(message = Constants.FIELD_NOT_NULL)
    @Positive(message = "The price must be a positive number.")
    @Digits(integer = Integer.MAX_VALUE, fraction = 0, message = "The price must be an integer.")
    private int price;

    @NotNull(message = Constants.FIELD_NOT_NULL)
    private Long userId;

}
