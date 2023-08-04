package com.pragma.boulevard_microservice_devops.application.dto.response;

import com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.entity.CategoryEntity;
import com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.entity.RestaurantEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishResponseDto {

    private Long id;

    private String name;

    private CategoryEntity categoryEntity;

    private String description;

    private String price;

    private RestaurantEntity restaurantEntity;

    private String urlImage;

    private boolean active;

}
