package com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.repository;


import com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {

}