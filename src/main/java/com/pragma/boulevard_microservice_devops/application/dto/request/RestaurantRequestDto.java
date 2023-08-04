package com.pragma.boulevard_microservice_devops.application.dto.request;

import com.pragma.boulevard_microservice_devops.infrastructure.configuration.Constants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantRequestDto {

    @NotNull(message = Constants.FIELD_NOT_NULL)
    @NotBlank(message = Constants.FIELD_NOT_BLANK)
    @Pattern(regexp = "^(?=.*[a-zA-Z])[a-zA-Z0-9]+$", message = "The field must contain letters and can include numbers.")
    private String name;

    @NotNull(message = Constants.FIELD_NOT_NULL)
    @NotBlank(message = Constants.FIELD_NOT_BLANK)
    private String direction;

    @NotNull(message = Constants.FIELD_NOT_NULL)
    private Long idOwner;

    @NotNull(message = Constants.FIELD_NOT_NULL)
    @NotBlank(message = Constants.FIELD_NOT_BLANK)
    @Pattern(regexp = "\\+?\\d{1,13}", message = "The phone number is invalid.")
    @Size(max = 13, message = "The phone number must have a maximum of 13 characters.")
    private String phone;

    @NotNull(message = Constants.FIELD_NOT_NULL)
    @NotBlank(message = Constants.FIELD_NOT_BLANK)
    private String urlLogo;

    @NotNull(message = Constants.FIELD_NOT_NULL)
    @NotBlank(message = Constants.FIELD_NOT_BLANK)
    @Pattern(regexp = "^[0-9]+$", message = "The field must contain only numbers.")
    private String nit;

}
