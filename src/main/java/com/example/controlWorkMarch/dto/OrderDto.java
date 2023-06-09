package com.example.controlWorkMarch.dto;

import com.example.controlWorkMarch.entity.Order;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class OrderDto {

    public static OrderDto from (Order order){
        return builder()
                .id(order.getId())
                .client(order.getClient())
                .orderedFood(order.getOrderedFood())
                .dateOfOrder(order.getDateOfOrder())
                .timeOfOrder(order.getTimeOfOrder())
                .build();
    }

    private Long id;
    private Long client; // FK
    private Long orderedFood; //FK
    private LocalDate dateOfOrder;
    private LocalTime timeOfOrder;
}
