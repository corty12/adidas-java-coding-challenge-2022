package com.adidas.backend.prioritysaleservice.mysql;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


@Configuration
@ComponentScan("com.adidas.backend")
public class SubscriptionsDataBaseConfig {


    @Value(value = "${mysql.driver}")
    private String mysqlDriver;

    @Value(value = "${mysql.database.host}")
    private String mysqlDatabaseHost;

    @Value(value = "${mysql.database.user}")
    private String mysqlDatabaseUser;

    @Value(value = "${mysql.database.password}")
    private String mysqlDatabasePassword;


    @Bean
    public DataSource subscriptionsDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(mysqlDriver);
        dataSource.setUrl(mysqlDatabaseHost);
        dataSource.setUsername(mysqlDatabaseUser);
        dataSource.setPassword(mysqlDatabasePassword);

        return dataSource;
    }
}