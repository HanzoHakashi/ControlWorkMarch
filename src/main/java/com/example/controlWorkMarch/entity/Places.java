package com.example.controlWorkMarch.entity;

import com.example.controlWorkMarch.dto.PlaceDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Places {
    private Long id;
    private String name;
    private String description;
}
