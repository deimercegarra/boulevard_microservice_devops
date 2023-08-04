package com.pragma.boulevard_microservice_devops.application.handler.impl;

import com.pragma.boulevard_microservice_devops.application.dto.request.OrderAssignRequestDto;
import com.pragma.boulevard_microservice_devops.application.dto.request.OrderRequestDto;
import com.pragma.boulevard_microservice_devops.application.dto.response.CommonResponseDto;
import com.pragma.boulevard_microservice_devops.application.dto.response.OrderResponseDto;
import com.pragma.boulevard_microservice_devops.application.handler.IOrderHandler;
import com.pragma.boulevard_microservice_devops.application.mapper.*;
import com.pragma.boulevard_microservice_devops.domain.api.IOrderServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderHandler implements IOrderHandler {

    private final IOrderServicePort iOrderServicePort;
    private final IOrderRequestMapper iOrderRequestMapper;
    private final IOrderResponseMapper iOrderResponseMapper;
    private final IOrderDishRequestMapper iOrderDishRequestMapper;
    private final ICommonResponseMapper iCommonResponseMapper;
    private final IOrderUpdateRequestMapper iOrderUpdateRequestMapper;

    @Transactional
    @Override
    public CommonResponseDto saveOrder(OrderRequestDto OrderRequestDto) {
        return iCommonResponseMapper.toResponse(
                iOrderServicePort.saveOrder(
                    iOrderRequestMapper.toOrderModel(OrderRequestDto),
                    iOrderDishRequestMapper.toOrderDishModelList(OrderRequestDto.getOrderDishRequestDtoList())
                )
        );
    }

    @Transactional
    @Override
    public void updateOrder(OrderRequestDto OrderRequestDto) {
        iOrderServicePort.saveOrder(iOrderRequestMapper.toOrderModel(OrderRequestDto), iOrderDishRequestMapper.toOrderDishModelList(OrderRequestDto.getOrderDishRequestDtoList()));
    }

    @Transactional
    @Override
    public void deleteOrder(Long orderId) {
        //iOrderServicePort.deleteOrder(orderId);
    }

    @Override
    public List<OrderResponseDto> getOrderByStatus(String status, Long employeeId, Pageable pageable) {
        return iOrderResponseMapper.toResponseList(
                iOrderServicePort.getOrderByStatus(status, employeeId, pageable)
        );
    }

    @Transactional
    @Override
    public List<OrderResponseDto> assignToOrder(List<OrderAssignRequestDto> orderAssignRequestDtoList, Long employeeId) {
        return iOrderResponseMapper.toResponseList(
                iOrderServicePort.assignToOrder(
                        iOrderUpdateRequestMapper.toModelList(orderAssignRequestDtoList),
                        employeeId)
        );
    }

    @Transactional
    @Override
    public CommonResponseDto orderReady(Long orderId) {
        return iCommonResponseMapper.toResponse(iOrderServicePort.orderReady(orderId));
    }

    @Transactional
    @Override
    public CommonResponseDto orderDelivered(Long orderId, String code) {
        return iCommonResponseMapper.toResponse(iOrderServicePort.orderDelivered(orderId, code));
    }

}