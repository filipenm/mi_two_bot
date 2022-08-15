package com.ne_tviy_bot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.ApiContextInitializer;


@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableScheduling
public class Main {

    public static void main(String[] args) {
        ApiContextInitializer.init();

        SpringApplication.run(Main.class, args);
    }
}