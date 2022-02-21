package com.exercise.config;

/**
 * @author Abu Bizibu
 * @created
 * @project
 */
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DBConfig {

    @Bean
    public DataSource getDataSource() {

        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUrl("jdbc:sqlite:sample.db");
        //dataSource.setUsername("");
        //dataSource.setPassword("");
        return dataSource;

    }
}