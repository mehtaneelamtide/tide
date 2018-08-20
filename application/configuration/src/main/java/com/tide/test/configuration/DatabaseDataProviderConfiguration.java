package com.tide.test.configuration;

import com.tide.test.core.usercase.store.AccountStore;
import com.tide.test.dataproviders.database.AccountStoreImpl;
import com.tide.test.dataproviders.database.TransactionStoreImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DatabaseDataProviderConfiguration {

    @Bean
    public AccountStore accountStore(JdbcTemplate jdbcTemplate)
    {
        return new AccountStoreImpl(jdbcTemplate);
    }

    @Bean
    public TransactionStoreImpl transactionStore(JdbcTemplate jdbcTemplate, AccountStore accountStore){
        return new TransactionStoreImpl(jdbcTemplate,  accountStore);
    }

}
