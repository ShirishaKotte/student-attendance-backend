package com.attendance.system.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    private static final Logger log = LoggerFactory.getLogger(DataSourceConfig.class);

    @Bean
    @Primary
    public DataSource dataSource(DataSourceProperties properties) {
        String url = properties.getUrl();
        // If URL is missing, invalid, or left as a placeholder (like "Your cloud MySQL JDBC URL"),
        // safely fallback to the embedded in-memory database so the app starts cleanly.
        if (url == null || !url.startsWith("jdbc:") || url.toLowerCase().contains("your cloud mysql")) {
            log.warn("Provided JDBC URL is invalid or placeholder ('{}'). Falling back to embedded H2 database.", url);
            return DataSourceBuilder.create()
                    .driverClassName("org.h2.Driver")
                    .url("jdbc:h2:mem:attendance_db;DB_CLOSE_DELAY=-1;MODE=MySQL;NON_KEYWORDS=USER")
                    .username("sa")
                    .password("")
                    .build();
        }

        log.info("Connecting with configured DataSource URL: {}", url);
        return properties.initializeDataSourceBuilder().build();
    }
}
