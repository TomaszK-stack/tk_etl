package com.example.tk_etlproc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class TkEtlprocApplication {

    public static void main(String[] args) {
        SpringApplication.run(TkEtlprocApplication.class, args);
    }

}
