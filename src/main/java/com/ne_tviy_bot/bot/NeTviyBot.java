package com.ne_tviy_bot.bot;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;


@Slf4j
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NeTviyBot extends TelegramLongPollingBot {
    String botUsername;
    String botToken;

    private TelegramFacade telegramFacade;

    public NeTviyBot(DefaultBotOptions options, TelegramFacade telegramFacade) {
        super(options);
        this.telegramFacade = telegramFacade;
    }


    public void onUpdateReceived(Update update) {
        telegramFacade.handleUpdate(update);
    }

}
