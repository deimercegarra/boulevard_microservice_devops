package com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.mapper;

import com.pragma.boulevard_microservice_devops.domain.model.EmployeeModel;
import com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.entity.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IEmployeeEntityMapper {

    @Mapping(target = "restaurantEntity.id", source = "restaurantModel.id")
    EmployeeEntity toEntity(EmployeeModel employeeModel);

    @Mapping(target = "restaurantModel.id", source = "restaurantEntity.id")
    EmployeeModel toModel(EmployeeEntity employeeEntity);

    List<EmployeeModel> toEmployeeList(List<EmployeeEntity> employeeEntityList);
}
