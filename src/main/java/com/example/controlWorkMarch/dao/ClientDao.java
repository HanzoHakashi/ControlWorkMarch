package com.example.controlWorkMarch.dao;

import com.example.controlWorkMarch.entity.Client;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;

@Component
public class ClientDao extends BaseDao {
    protected ClientDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("create table if not exists clients\n" +
                "(\n" +
                "    id bigserial primary key,\n" +
                "    name  varchar not null,\n" +
                "    email  varchar not null,\n" +
                "    password  varchar not null,\n" +
                "    role  varchar not null default 'quest',\n" +
                "    enabled BOOLEAN\n" +
                ");");
    }

    public void save(Client client) {
        String sql = "insert into clients(name,email,password,role,enabled) " +
                "values(?,?,?,?,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, client.getName());
            ps.setString(2, client.getEmail());
            ps.setString(3,client.getPassword());
            ps.setString(4,client.getRole());
            ps.setBoolean(5,client.getEnabled() );
            return ps;
        });
    }
}
