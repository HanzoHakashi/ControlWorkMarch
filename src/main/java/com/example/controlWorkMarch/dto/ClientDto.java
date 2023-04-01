package com.example.controlWorkMarch.dto;

import com.example.controlWorkMarch.entity.Client;
import lombok.*;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class ClientDto {

    public static ClientDto from(Client client){
        return builder()
                .id(client.getId())
                .name(client.getName())
                .email(client.getEmail())
                .password(client.getPassword())
                .role(client.getRole())
                .enabled(client.getEnabled())
                .build();
    }

    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;
    private Boolean enabled = Boolean.TRUE;
}
