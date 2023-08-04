package com.pragma.boulevard_microservice_devops.domain.model;

public class EmployeeModel {

    private Long id;

    private Long idEmployee;

    private RestaurantModel restaurantModel;

    public EmployeeModel(Long id, Long idEmployee, RestaurantModel restaurantModel) {
        this.id = id;
        this.idEmployee = idEmployee;
        this.restaurantModel = restaurantModel;
    }

    public EmployeeModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public RestaurantModel getRestaurantModel() {
        return restaurantModel;
    }

    public void setRestaurantModel(RestaurantModel restaurantModel) {
        this.restaurantModel = restaurantModel;
    }
}
