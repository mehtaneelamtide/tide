package com.tide.test.configuration;

import com.tide.test.core.usercase.DoIntraAccountTransfer;
import com.tide.test.core.usercase.store.AccountStore;
import com.tide.test.core.usercase.store.TransactionStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public DoIntraAccountTransfer doIntraAccountTransfer( TransactionStore transactionStore, AccountStore accountStore){
        return new DoIntraAccountTransfer(accountStore,transactionStore);
    }


}
