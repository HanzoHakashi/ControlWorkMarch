package com.example.controlWorkMarch.service;

import com.example.controlWorkMarch.dao.FoodDao;
import com.example.controlWorkMarch.dao.OrderDao;
import com.example.controlWorkMarch.dao.PlaceDao;
import com.example.controlWorkMarch.dto.FoodDto;
import com.example.controlWorkMarch.dto.OrderDto;
import com.example.controlWorkMarch.dto.PlaceDto;
import com.example.controlWorkMarch.entity.Order;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainService {
    private final PlaceDao placeDao;
    private final OrderDao orderDao;
    private final FoodDao foodDao;

    public MainService(PlaceDao placeDao, OrderDao orderDao, FoodDao foodDao) {
        this.placeDao = placeDao;
        this.orderDao = orderDao;
        this.foodDao = foodDao;
    }

    public List<PlaceDto> getPlaces(){
        var placeList = placeDao.getPlaces();
        return placeList.stream().map(PlaceDto::from).collect(Collectors.toList());
    }

    public List<FoodDto> getMenu(Long place_id){
        var foodList = foodDao.getFoods(place_id);
        return foodList.stream().map(FoodDto::from).collect(Collectors.toList());
    }

    public void addOrder(Long client_id, Long food){
        Order order = new Order();
        order.setClient(client_id);
        order.setOrderedFood(food);
        order.setDateOfOrder(LocalDate.now());
        orderDao.addOrder(order);
    }

    public List<OrderDto> getOrders(Long client_id){
        var orderList = orderDao.getOrders(client_id);
        return orderList.stream().map(OrderDto::from).collect(Collectors.toList());
    }
}
