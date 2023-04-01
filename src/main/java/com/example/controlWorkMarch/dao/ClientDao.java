package com.example.controlWorkMarch.dao;

import com.example.controlWorkMarch.entity.Client;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.Optional;

@Component
public class ClientDao extends BaseDao {
    private final PasswordEncoder passwordEncoder;
    protected ClientDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate, PasswordEncoder passwordEncoder) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("create table if not exists clients\n" +
                "(\n" +
                "    id bigserial primary key,\n" +
                "    name  varchar not null,\n" +
                "    email  varchar not null,\n" +
                "    password  varchar not null,\n" +
                "    role  varchar  default 'quest',\n" +
                "    enabled BOOLEAN\n" +
                ");");
    }
    public Optional<Client> findClientByEmail(String email){
        String sql = "select id " +
                "from clients " +
                "where email = ?";
        return Optional.ofNullable(DataAccessUtils.singleResult(
                jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Client.class), email)
        ));
    }
    public void save(Client client) {
        String sql = "insert into clients(name,email,password,role,enabled) " +
                "values(?,?,?,?,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, client.getName());
            ps.setString(2, client.getEmail());
            ps.setString(3,passwordEncoder.encode(client.getPassword()) );
            ps.setString(4,client.getRole());
            ps.setBoolean(5,client.getEnabled() );
            return ps;
        });
    }
    public void deleteAll() {
        String sql = "delete from clients";
        jdbcTemplate.update(sql);
    }
}
