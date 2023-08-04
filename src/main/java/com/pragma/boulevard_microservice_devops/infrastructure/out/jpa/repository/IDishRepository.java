package com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.repository;

import com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.entity.DishEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IDishRepository extends JpaRepository<DishEntity, Long> {

    @Query("SELECT d FROM  DishEntity d WHERE d.restaurantEntity.id = :idRestaurant AND d.categoryEntity.id = :idCategory")
    List<DishEntity> findByRestaurantEntityAndCategoryEntity(
            @Param("idRestaurant") Long restaurant,
            @Param("idCategory") Long category,
            Pageable pageable
    );

}