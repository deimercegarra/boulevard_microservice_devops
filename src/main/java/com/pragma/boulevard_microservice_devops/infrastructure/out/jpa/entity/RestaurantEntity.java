package com.pragma.boulevard_microservice_devops.infrastructure.out.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "restaurant")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String direction;

    @Column(name = "id_owner")
    private Long idOwner;

    private String phone;

    @Column(name = "url_logo")
    private String urlLogo;

    private String nit;

}
