package com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.repository;


import com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IEmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    EmployeeEntity findByIdEmployee(Long idEmployee);


}