package com.projetjee.projetjee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class jdbcConfig {

    @Bean
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://vps817240.ovh.net:3306/IAI2_team06_schema");
        dataSource.setUsername("IAI2_team06");
        dataSource.setPassword("U2brZ2&i2z6");

        return dataSource;
    }
}