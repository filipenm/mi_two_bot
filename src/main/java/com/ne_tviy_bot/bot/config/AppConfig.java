package com.ne_tviy_bot.bot.config;

import com.ne_tviy_bot.bot.NeTviyBot;
import com.ne_tviy_bot.bot.TelegramFacade;
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

    private final NeTviyBotConfig botConfig;

    public AppConfig(NeTviyBotConfig neTviyBotConfig) {
        this.botConfig = neTviyBotConfig;
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
    public NeTviyBot NeTviyBot(TelegramFacade telegramFacade) {
        DefaultBotOptions options = ApiContext
                .getInstance(DefaultBotOptions.class);


        NeTviyBot neTviyBot = new NeTviyBot(options, telegramFacade);
        neTviyBot.setBotUsername(botConfig.getName());
        neTviyBot.setBotToken(botConfig.getToken());
        System.out.println(botConfig.getName());

        return neTviyBot;
    }
}