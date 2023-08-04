package com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.mapper;


import com.pragma.boulevard_microservice_devops.domain.model.CategoryModel;
import com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface ICategoryEntityMapper {

    CategoryEntity toEntity(CategoryModel categoryModel);
    CategoryModel toModel(CategoryEntity categoryEntity);
    List<CategoryModel> toModelList(List<CategoryEntity> categoryEntityList);

}