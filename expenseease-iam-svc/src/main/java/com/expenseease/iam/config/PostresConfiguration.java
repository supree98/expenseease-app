package com.expenseease.iam.config;

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
    private final String schema;

    public PostresConfiguration(@Value("${iam.datasource.url}") String jdbcUrl,
                                @Value("${iam.datasource.username}") String userName,
                                @Value("${iam.datasource.password}") String password,
                                @Value("${iam.datasource.schema}") String schema) {
        this.jdbcUrl = jdbcUrl;
        this.userName = userName;
        this.password = password;
        this.schema = schema;
    }

    @Bean
    public DataSource createIAMDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(userName);
        config.setPassword(password);
        config.setSchema(schema);
        return new HikariDataSource(config);
    }
}
