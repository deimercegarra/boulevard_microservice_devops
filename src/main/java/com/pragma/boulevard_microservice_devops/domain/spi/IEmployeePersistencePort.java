package com.pragma.boulevard_microservice_devops.domain.spi;


import com.pragma.boulevard_microservice_devops.domain.model.EmployeeModel;

import java.util.List;

public interface IEmployeePersistencePort {
    EmployeeModel findByIdEmployee(Long employeeId);

}