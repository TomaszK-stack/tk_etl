package com.example.tk_etlproc.processing.steps.config;

import com.example.tk_etlproc.processing.steps.nullif.NullifStep;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class StepsConfiguration {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public NullifStep getNullifStep(){
        return new NullifStep();
    }

}
