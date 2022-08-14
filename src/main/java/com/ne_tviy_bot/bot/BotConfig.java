package com.ne_tviy_bot.bot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class BotConfig{

    @Bean
    public ClientBean clientBean() {
        return new ClientBean();
    }
}