package com.pragma.boulevard_microservice_devops.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantResponseDto {

    private Long id;

    private String name;

    private String direction;

    private Long idOwner;

    private String phone;

    private String urlLogo;

    private String nit;

}
