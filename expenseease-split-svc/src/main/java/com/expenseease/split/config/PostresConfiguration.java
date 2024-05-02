package com.expenseease.split.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class PostresConfiguration {

    private final String jdbcUrl;
    private final String userName;
    private final String password;

    public PostresConfiguration(@Value("${split.datasource.url}") String jdbcUrl,
                                @Value("${split.datasource.username}") String userName,
                                @Value("${split.datasource.password}") String password) {
        this.jdbcUrl = jdbcUrl;
        this.userName = userName;
        this.password = password;
    }

    @Bean
    public DataSource createSplitDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(userName);
        config.setPassword(password);
//        config.setSchema(schema);
        return new HikariDataSource(config);
    }
}
