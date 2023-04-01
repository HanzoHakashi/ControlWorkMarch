package com.example.controlWorkMarch.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;
    private Boolean enabled = Boolean.TRUE;
}
