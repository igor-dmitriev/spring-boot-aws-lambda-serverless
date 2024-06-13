package com.amazonaws.serverless.sample.springboot3.config;

import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
  @Bean
  public Flyway flyway(DataSource dataSource) {
    return Flyway.configure()
        .dataSource(dataSource)
        .outOfOrder(true)
        .validateOnMigrate(false)
        .locations("classpath:db/migration/common")
        .load();
  }
}