package com.pragma.boulevard_microservice_devops.domain.model;



public class OrderDishModel {

    private OrderModel orderModel;

    private DishModel dishModel;

    private int quantity;

    public OrderDishModel(OrderModel orderModel, DishModel dishModel, int quantity) {
        this.orderModel = orderModel;
        this.dishModel = dishModel;
        this.quantity = quantity;
    }

    public OrderDishModel() {
    }

    public OrderModel getOrderModel() {
        return orderModel;
    }

    public void setOrderModel(OrderModel orderModel) {
        this.orderModel = orderModel;
    }

    public DishModel getDishModel() {
        return dishModel;
    }

    public void setDishModel(DishModel dishModel) {
        this.dishModel = dishModel;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
