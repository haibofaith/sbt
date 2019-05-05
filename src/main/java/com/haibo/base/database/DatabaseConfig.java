package com.haibo.base.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author:haibo.xiong
 * @date:2019/1/2
 * @description:
 */
@Configuration
public class DatabaseConfig {
    @Bean(name = "dbProductService")
    @ConfigurationProperties(prefix = "spring.dbproductservice")
    @Primary
    public DataSource createProductServiceDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dbUserService")
    @ConfigurationProperties(prefix = "spring.dbsource2")
    public DataSource createUserServiceDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "jdbcProductService")
    @Autowired
    public JdbcTemplate createJdbcTemplate_ProductService(@Qualifier("dbProductService") DataSource productServiceDS) {
        return new JdbcTemplate(productServiceDS);
    }

    @Bean(name = "jdbcUserService")
    @Autowired
    public JdbcTemplate createJdbcTemplate_UserService(@Qualifier("dbUserService") DataSource userServiceDS) {
        return new JdbcTemplate(userServiceDS);
    }
}