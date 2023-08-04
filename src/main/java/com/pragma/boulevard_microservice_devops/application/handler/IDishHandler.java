package com.pragma.boulevard_microservice_devops.application.handler;

import com.pragma.boulevard_microservice_devops.application.dto.request.ActiveDishRequestDto;
import com.pragma.boulevard_microservice_devops.application.dto.request.DishRequestDto;
import com.pragma.boulevard_microservice_devops.application.dto.request.DishUpdateRequestDto;
import com.pragma.boulevard_microservice_devops.application.dto.response.CommonResponseDto;
import com.pragma.boulevard_microservice_devops.application.dto.response.DishResponseDto;
import org.springframework.data.domain.Pageable;

import javax.swing.*;
import java.util.List;

public interface IDishHandler {

    public CommonResponseDto saveDish(DishRequestDto dishRequestDto);

    public CommonResponseDto updateDish(DishUpdateRequestDto dishUpdateRequestDto);

    public CommonResponseDto activeDish(ActiveDishRequestDto activeDishRequestDto);

    public void deleteDish(Long dishId);

    List<DishResponseDto> getDishesByRestaurantAndCategory(Long idRestaurant, Long idCategory, Pageable pageable);
}