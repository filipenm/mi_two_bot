package com.ne_tviy_bot.bot;

import org.springframework.beans.factory.annotation.Value;

public class ClientBean {
    @Value("${bot.token}")
    private String token;

    @Value("${bot.chatId}")
    private String chatId;

    @Value("${bot.name}")
    private String botName;

    public String getToken() {
        return token;
    }

    public String getChatId() {
        return chatId;
    }

    public String getBotName() {
        return botName;
    }
}
