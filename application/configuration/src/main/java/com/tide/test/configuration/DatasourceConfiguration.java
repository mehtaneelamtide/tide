package com.tide.test.configuration;

import org.h2.jdbcx.JdbcConnectionPool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class DatasourceConfiguration {

    private static final String SCHEMA_INITIALISATION_SCRIPT = "h2-schema.sql";

    @Bean
    public DataSource dataSource() throws IOException {

        String jdbcUrl = "jdbc:h2:mem:example;MODE=Oracle;INIT=runscript from 'classpath:/" + SCHEMA_INITIALISATION_SCRIPT + "'";
        String username = "PAYMENT_SYSTEM";
        String password = "";
        return JdbcConnectionPool.create(jdbcUrl, username, password);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource datasource) {
        return new JdbcTemplate(datasource);
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return  new DataSourceTransactionManager(dataSource);
    }

}
