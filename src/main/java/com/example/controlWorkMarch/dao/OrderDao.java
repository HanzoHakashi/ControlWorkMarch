package com.example.controlWorkMarch.dao;

import com.example.controlWorkMarch.entity.Food;
import com.example.controlWorkMarch.entity.Order;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Component
public class OrderDao extends BaseDao {
    protected OrderDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("create table if not exists orders\n" +
                "(\n" +
                "    id bigserial primary key,\n" +
                "    client  bigserial not null,\n" +
                "    foreign key (client) references clients (id),\n" +
                "    orderedFood  bigserial not null,\n" +
                "    foreign key (orderedFood) references foods (id),\n" +
                "    dateOfOrder  Date not null\n" +
                ");");
    }

    public List<Order> getOrders(Long client_id){
        String sql = "select * from orders" +
                "where client = ?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Order.class),client_id);
    }

    public void addOrder(Order order){
        String sql = "insert into orders(client,orderedFood,dateOfOrder)"+
                "values(?,?,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1,order.getClient());
            ps.setLong(2,order.getOrderedFood());
            ps.setDate(3, Date.valueOf(order.getDateOfOrder()));
            return ps;
        });
    }
}
