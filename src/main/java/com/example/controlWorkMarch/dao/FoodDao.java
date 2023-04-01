package com.example.controlWorkMarch.dao;

import com.example.controlWorkMarch.entity.Food;
import com.example.controlWorkMarch.entity.Places;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FoodDao extends BaseDao{
    protected FoodDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("create table if not exists foods\n" +
                "(\n" +
                "    id bigserial primary key,\n" +
                "    nameOfFood  varchar not null,\n" +
                "    typeOfFood  varchar not null,\n" +
                "    place  bigserial not null,\n" +
                "    foreign key (place) references places (id),\n" +
                "    price INTEGER not null default 0\n" +
                ");");
    }

    public List<Food> getFoods(Long place_id){
        String sql = "select * from food" +
                "where place = ?";
       return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Food.class),place_id);
    }
}
