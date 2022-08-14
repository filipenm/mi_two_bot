package com.ne_tviy_bot;

import com.ne_tviy_bot.bot.BinanceBot;
import com.ne_tviy_bot.bot.BotConfig;
import com.ne_tviy_bot.bot.ClientBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.telegram.telegrambots.ApiContextInitializer;


public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(BotConfig.class);
        ClientBean bean = context.getBean(ClientBean.class);
        ApiContextInitializer.init();

        BinanceBot bot = new BinanceBot(bean.getBotName(), bean.getToken());
        bot.botConnect();
    }
}