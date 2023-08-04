package com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.repository;

import com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.entity.OrderEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {
    @Query("SELECT o FROM  OrderEntity o WHERE o.idClient = :idClient AND o.statusOrder in ( :pending, :preparation, :ready )")
    List<OrderEntity> findByIdClientAndStatus(
            @Param("idClient") Long idClient,
            @Param("pending") String statusPending,
            @Param("preparation") String statusPreparation,
            @Param("ready") String statusReady
    );

    @Query("SELECT o FROM  OrderEntity o WHERE o.restaurantEntity.id = :idRestaurant AND o.statusOrder = :status")
    List<OrderEntity> getOrderByStatus(
            @Param("idRestaurant") Long idRestaurant,
            @Param("status") String status,
            Pageable pageable
    );

}