package com.example.controlWorkMarch.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;
    private Long client; // FK
    private Long orderedFood; //FK
    private LocalDate dateOfOrder;
}
