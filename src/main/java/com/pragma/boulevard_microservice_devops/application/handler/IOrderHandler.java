package com.pragma.boulevard_microservice_devops.application.handler;

import com.pragma.boulevard_microservice_devops.application.dto.request.OrderAssignRequestDto;
import com.pragma.boulevard_microservice_devops.application.dto.request.OrderRequestDto;
import com.pragma.boulevard_microservice_devops.application.dto.response.CommonResponseDto;
import com.pragma.boulevard_microservice_devops.application.dto.response.OrderResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderHandler {

    public CommonResponseDto saveOrder(OrderRequestDto orderRequestDto);

    public void updateOrder(OrderRequestDto orderRequestDto);

    public void deleteOrder(Long orderId);

    List<OrderResponseDto> getOrderByStatus(String status, Long employeeId, Pageable pageable);

    List<OrderResponseDto> assignToOrder(List<OrderAssignRequestDto> orderAssignRequestDtoList, Long employeeId);

    CommonResponseDto orderReady(Long orderId);

    CommonResponseDto orderDelivered(Long orderId, String code);
}