package com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.repository;

import com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, Long> {

}