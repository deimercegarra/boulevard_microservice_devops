package com.pragma.boulevard_microservice_devops.application.mapper;

import com.pragma.boulevard_microservice_devops.application.dto.request.ActiveDishRequestDto;
import com.pragma.boulevard_microservice_devops.application.dto.request.DishRequestDto;
import com.pragma.boulevard_microservice_devops.application.dto.request.DishUpdateRequestDto;
import com.pragma.boulevard_microservice_devops.domain.model.DishModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishRequestMapper {
    DishModel toDishModel(DishRequestDto dishRequestDto);

    DishModel toDishModel(DishUpdateRequestDto dishUpdateRequestDto);

    DishModel toDishModel(ActiveDishRequestDto activeDishRequestDto);

}
