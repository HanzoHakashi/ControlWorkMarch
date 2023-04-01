package com.example.controlWorkMarch.controller;

import com.example.controlWorkMarch.dto.FoodDto;
import com.example.controlWorkMarch.dto.OrderDto;
import com.example.controlWorkMarch.dto.PlaceDto;
import com.example.controlWorkMarch.service.ClientService;
import com.example.controlWorkMarch.service.MainService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service")
public class OrderController {
    private final MainService mainService;
    private final ClientService clientService;

    public OrderController(MainService mainService, ClientService clientService) {
        this.mainService = mainService;
        this.clientService = clientService;
    }

    @GetMapping("/places")
    public List<PlaceDto> getPlaces(){
        return mainService.getPlaces();
    }

    @GetMapping("/{place_id}/menu")
    public List<FoodDto> getMenu(@PathVariable Long place_id){
        return mainService.getMenu(place_id);
    }

    @PostMapping("/makeOrder")
    public void addOrder(@RequestParam Long food_id, Authentication authentication){
        Long client_id = clientService.findClientByEmail(authentication.getName());
        mainService.addOrder(client_id,food_id);
    }

    @GetMapping("/orders")
    public List<OrderDto> getOrders(Authentication authentication){
        Long client_id = clientService.findClientByEmail(authentication.getName());
        return mainService.getOrders(client_id);
    }

}
