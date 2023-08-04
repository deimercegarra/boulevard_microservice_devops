package com.pragma.boulevard_microservice_devops.application.dto.request;

import com.pragma.boulevard_microservice_devops.infrastructure.configuration.Constants;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActiveDishRequestDto {

    @NotNull(message = Constants.FIELD_NOT_NULL)
    private Long Id;

    @NotNull(message = Constants.FIELD_NOT_NULL)
    private Boolean active;

    @NotNull(message = Constants.FIELD_NOT_NULL)
    private Long userId;

}
