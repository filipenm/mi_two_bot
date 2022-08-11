package com.binance_bot;

import com.binance_bot.bot.BinanceBot;
import com.binance_bot.bot.BotConfig;
import com.binance_bot.bot.ClientBean;
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