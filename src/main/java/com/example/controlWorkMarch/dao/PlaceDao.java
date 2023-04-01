package com.example.controlWorkMarch.dao;

import com.example.controlWorkMarch.entity.Places;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

public class PlaceDao extends BaseDao{
    protected PlaceDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("create table if not exists places\n" +
                "(\n" +
                "    id bigserial primary key,\n" +
                "    name  varchar not null,\n" +
                "    description  varchar not null\n" +
                ");");
    }

    public List<Places> getPlaces(){
        String sql = "select * from places";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Places.class));
    }
}
