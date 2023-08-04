package com.pragma.boulevard_microservice_devops.application.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SmsRequestDto {

    @NotBlank
    private String message;

    @NotBlank
    @Pattern(regexp = "\\+[0-9]{12}", message = "invalid phone number")
    private String phone;

}
