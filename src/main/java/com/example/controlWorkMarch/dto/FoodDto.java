package com.example.controlWorkMarch.dto;

import com.example.controlWorkMarch.entity.Food;
import lombok.*;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class FoodDto {

    public static FoodDto from(Food food){
        return builder()
                .id(food.getId())
                .nameOfFood(food.getNameOfFood())
                .typeOfFood(food.getTypeOfFood())
                .place(food.getPlace())
                .price(food.getPrice())
                .build();
    }

    private Long id;
    private String nameOfFood;
    private String typeOfFood;
    private Long place;//FK
    private int price;
}
