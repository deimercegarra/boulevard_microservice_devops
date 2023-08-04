package com.pragma.boulevard_microservice_devops.domain.model;


import java.util.Date;


public class OrderModel {

    private Long id;

    private Long idClient;

    private Date dateOrder;

    private String statusOrder;

    private Long idChef;

    private RestaurantModel restaurantModel;

    public OrderModel(Long id, Long idClient, Date dateOrder, String statusOrder, Long idChef, RestaurantModel restaurantModel) {
        this.id = id;
        this.idClient = idClient;
        this.dateOrder = dateOrder;
        this.statusOrder = statusOrder;
        this.idChef = idChef;
        this.restaurantModel = restaurantModel;
    }

    public OrderModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }

    public Long getIdChef() {
        return idChef;
    }

    public void setIdChef(Long idChef) {
        this.idChef = idChef;
    }

    public RestaurantModel getRestaurantModel() {
        return restaurantModel;
    }

    public void setRestaurantModel(RestaurantModel restaurantModel) {
        this.restaurantModel = restaurantModel;
    }
}
