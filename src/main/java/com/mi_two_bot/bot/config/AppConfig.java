package com.mi_two_bot.bot.config;

import com.mi_two_bot.bot.MiTwoBot;
import com.mi_two_bot.bot.TelegramFacade;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;

@Configuration
public class AppConfig {

    private final MiTwoBotConfig botConfig;

    public AppConfig(MiTwoBotConfig miTwoBotConfig) {
        this.botConfig = miTwoBotConfig;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }


    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public MiTwoBot MiTwoBot(TelegramFacade telegramFacade) {
        DefaultBotOptions options = ApiContext
                .getInstance(DefaultBotOptions.class);


        MiTwoBot miTwoBot = new MiTwoBot(options, telegramFacade);
        miTwoBot.setBotUsername(botConfig.getName());
        miTwoBot.setBotToken(botConfig.getToken());

        return miTwoBot;
    }
}