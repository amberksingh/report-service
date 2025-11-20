package com.example.report_service.utility;

import com.example.report_service.entity.Order;
import com.example.report_service.entity.OrderDTO;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    public static OrderDTO toDTO(Order order) {
        if (order == null) return null;

        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setOrderNumber(order.getOrderNumber());
        dto.setProductName(order.getProductName());
        dto.setUserId(order.getUserId());
        dto.setAmount(order.getAmount());
        dto.setStatus(order.getStatus());
        dto.setCreatedAt(order.getCreatedAt());

        return dto;
    }

    public static List<OrderDTO> toDTO(List<Order> orders) {
        if (orders == null) return null;

        return orders.stream().map(OrderMapper::toDTO).toList();

//        List<OrderDTO> orderDTOList = new ArrayList<>();
//        for (Order order : orders) {
//            OrderDTO dto = new OrderDTO();
//            dto.setId(order.getId());
//            dto.setOrderNumber(order.getOrderNumber());
//            dto.setProductName(order.getProductName());
//            dto.setUserId(order.getUserId());
//            dto.setAmount(order.getAmount());
//            dto.setStatus(order.getStatus());
//            dto.setCreatedAt(order.getCreatedAt());
//            orderDTOList.add(dto);
//        }
//
//        return orderDTOList;
    }

    public static Order toEntity(OrderDTO dto) {
        if (dto == null) return null;

        Order order = new Order();
        order.setId(dto.getId());
        order.setOrderNumber(dto.getOrderNumber());
        order.setProductName(dto.getProductName());
        order.setUserId(dto.getUserId());
        order.setAmount(dto.getAmount());
        order.setStatus(dto.getStatus());
        order.setCreatedAt(dto.getCreatedAt());

        return order;
    }
}
