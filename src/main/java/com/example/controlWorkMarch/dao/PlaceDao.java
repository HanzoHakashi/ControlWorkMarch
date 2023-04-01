package com.example.controlWorkMarch.dao;


import com.example.controlWorkMarch.entity.Places;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;


import java.sql.PreparedStatement;
import java.util.List;
@Component
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

    public Long findPlaceByID(Long id){
        String sql = "select id from places" +
                "where id = ?";
        return jdbcTemplate.queryForObject(sql, Long.class,id);
    }

    public void deleteAll() {
        String sql = "delete from places";
        jdbcTemplate.update(sql);
    }

    public void save(Places places){
        String sql = "insert into places(name,description)"+
                "values(?,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,places.getName());
            ps.setString(2,places.getDescription());
            return ps;
        });
    }
}
