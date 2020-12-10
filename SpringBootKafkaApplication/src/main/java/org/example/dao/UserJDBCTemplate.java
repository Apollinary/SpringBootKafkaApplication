package org.example.dao;

import org.example.User;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class UserJDBCTemplate implements UserDAO {

    private DataSource dataSource;

    private JdbcTemplate jdbcTemplateObject;

    public UserJDBCTemplate() {
        this.setDataSource(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/test");
        driverManagerDataSource.setUsername("postgres");
        driverManagerDataSource.setPassword("12345");
        return driverManagerDataSource;
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public void addUser(String firstName, String lastName) {
        String sqlQuery = "insert into users (firstname, lastname) values (?, ?)";
        jdbcTemplateObject.update( sqlQuery, firstName, lastName);
        System.out.println("Created Record firstName = " + firstName + " lastName = " + lastName);
    }

    @Override
    public User getUserByName(String firstName, String lastName) {
        String sqlQuery = "SELECT * FROM users where firstname = ? and lastname = ?";
        return jdbcTemplateObject.queryForObject(sqlQuery, new UserMapper(), firstName, lastName);
    }
}