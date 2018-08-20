package com.tide.test.configuration;

import com.tide.test.core.usercase.DoIntraAccountTransfer;
import com.tide.test.entrypoinyts.rest.endpoints.TransactionEndpoint;
import com.tide.test.entrypoinyts.rest.exception.DefaultExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EndpointConfiguration {

    @Bean
    public TransactionEndpoint bidEndpoint(DoIntraAccountTransfer doIntraAccountTransfer){
        return new TransactionEndpoint(doIntraAccountTransfer);
    }

    @Bean
    public DefaultExceptionHandler defaultExceptionHandler(){
        return new DefaultExceptionHandler();
    }

}
