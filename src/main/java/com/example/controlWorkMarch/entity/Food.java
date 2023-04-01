package com.example.controlWorkMarch.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Food {
    private Long id;
    private String nameOfFood;
    private String typeOfFood;
    private int price;
}
