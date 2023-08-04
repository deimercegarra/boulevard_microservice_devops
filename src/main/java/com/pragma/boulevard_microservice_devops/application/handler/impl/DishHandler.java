package com.pragma.boulevard_microservice_devops.application.handler.impl;

import com.pragma.boulevard_microservice_devops.application.dto.request.ActiveDishRequestDto;
import com.pragma.boulevard_microservice_devops.application.dto.request.DishRequestDto;
import com.pragma.boulevard_microservice_devops.application.dto.request.DishUpdateRequestDto;
import com.pragma.boulevard_microservice_devops.application.dto.response.CommonResponseDto;
import com.pragma.boulevard_microservice_devops.application.dto.response.DishResponseDto;
import com.pragma.boulevard_microservice_devops.application.handler.IDishHandler;
import com.pragma.boulevard_microservice_devops.application.mapper.ICommonResponseMapper;
import com.pragma.boulevard_microservice_devops.application.mapper.IDishRequestMapper;
import com.pragma.boulevard_microservice_devops.application.mapper.IDishResponseMapper;
import com.pragma.boulevard_microservice_devops.domain.api.IDishServicePort;
import com.pragma.boulevard_microservice_devops.domain.model.DishModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DishHandler implements IDishHandler {

    private final IDishServicePort iDishServicePort;
    private final IDishRequestMapper iDishRequestMapper;
    private final IDishResponseMapper iDishResponseMapper;
    private final ICommonResponseMapper iCommonResponseMapper;

    @Transactional
    @Override
    public CommonResponseDto saveDish(DishRequestDto dishRequestDto) {
        return iCommonResponseMapper.toResponse(iDishServicePort.saveDish(iDishRequestMapper.toDishModel(dishRequestDto)));
    }

    @Transactional
    @Override
    public CommonResponseDto updateDish(DishUpdateRequestDto dishUpdateRequestDto) {
        return iCommonResponseMapper.toResponse(iDishServicePort.updateDish(iDishRequestMapper.toDishModel(dishUpdateRequestDto)));
    }

    @Transactional
    @Override
    public CommonResponseDto activeDish(ActiveDishRequestDto activeDishRequestDto) {
        return iCommonResponseMapper.toResponse(iDishServicePort.activeDish(iDishRequestMapper.toDishModel(activeDishRequestDto)));
    }

    @Transactional
    @Override
    public void deleteDish(Long dishId) {
        //iDishServicePort.deleteDish(dishId);
    }

    @Override
    public List<DishResponseDto> getDishesByRestaurantAndCategory(Long idRestaurant, Long idCategory, Pageable pageable) {

        List<DishModel> dishModelList = iDishServicePort.getDishesByRestaurantAndCategory( idRestaurant, idCategory, pageable );

        return iDishResponseMapper.toResponseList(dishModelList);
    }
}