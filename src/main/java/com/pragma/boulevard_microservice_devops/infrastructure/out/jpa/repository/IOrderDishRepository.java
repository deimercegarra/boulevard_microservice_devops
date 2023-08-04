package com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.repository;


import com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.entity.OrderDishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderDishRepository extends JpaRepository<OrderDishEntity, Long> {

}