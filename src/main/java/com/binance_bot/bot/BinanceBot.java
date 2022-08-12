package com.binance_bot.bot;

import com.binance_bot.commands.CommandBase;
import com.binance_bot.core.ApplicationManager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

@AllArgsConstructor
@NoArgsConstructor
public class BinanceBot extends TelegramLongPollingBot {
    protected static ApplicationManager app = ApplicationManager.get();
    final int RECONNECT_PAUSE = 10000;

    @Setter
    @Getter
    String userName;

    @Setter
    @Getter
    String token;


    @Override
    public void onUpdateReceived(Update update) {
        app.log().debug(update);
        CommandBase commandBase = new CommandBase();
        SendMessage message = commandBase.commandHandler(update);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        app.log().debug(userName);
        return userName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    public void botConnect() {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiRequestException e) {
            try {
                Thread.sleep(RECONNECT_PAUSE);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
                return;
            }
            botConnect();
        }
    }
}
