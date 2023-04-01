package com.example.controlWorkMarch.utils;


import com.example.controlWorkMarch.dao.ClientDao;
import com.example.controlWorkMarch.dao.FoodDao;
import com.example.controlWorkMarch.dao.OrderDao;
import com.example.controlWorkMarch.dao.PlaceDao;
import com.example.controlWorkMarch.entity.Client;
import com.example.controlWorkMarch.entity.Food;
import com.example.controlWorkMarch.entity.Places;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@AllArgsConstructor
@Configuration
public class InitDatabase {
        @Bean
    CommandLineRunner init(ClientDao clientDao, FoodDao foodDao, OrderDao orderDao, PlaceDao placeDao){
            return args -> {
                clientDao.createTable();
                placeDao.createTable();
                foodDao.createTable();
                orderDao.createTable();

                orderDao.deleteAll();
                foodDao.deleteAll();
                placeDao.deleteAll();
                clientDao.deleteAll();

                Client user1 = new Client();
                user1.setName("SuperSaiyan");
                user1.setEmail("ssj@gmail.com");
                user1.setPassword("kamehameha213");
                clientDao.save(user1);

                Client user2 = new Client();
                user2.setName("Guts");
                user2.setEmail("berserk@gmail.com");
                user2.setPassword("griffitmustdie");
                clientDao.save(user2);

                for (int i = 0; i < 10; i++) {
                    Client client = new Client();
                    client.setName(GenerateData.randomPersonName());
                    client.setEmail(GenerateData.randomEmail());
                    client.setPassword("password");
                    clientDao.save(client);
                }

                for (int i = 0; i < 5; i++) {
                    GenerateData.PlaceName placeName = GenerateData.randomPlace();
                    Places place = new Places();
                    place.setName(placeName.getName());
                    place.setDescription(placeName.getDescription());
                    placeDao.save(place);
                }

                for (int i = 0; i < 20; i++) {
                    GenerateData.DishName dishName = GenerateData.randomDish();
                    List<Places> places = placeDao.getPlaces();
                    Long size = (long) places.size();
                    Long randomId = places.get(GenerateData.r.nextInt(size.intValue())).getId();

                    Food food = new Food();
                    food.setNameOfFood(dishName.getName());
                    food.setTypeOfFood(dishName.getType());
                    food.setPrice(GenerateData.r.nextInt(1000));
                    food.setPlace(randomId);
                    foodDao.save(food);
                }

            };
        }
}
