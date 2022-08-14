package com.ne_tviy_bot.bot;

import com.ne_tviy_bot.commands.base.CommandFactory;
import com.ne_tviy_bot.core.ApplicationManager;
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
public class NeTviyBot extends TelegramLongPollingBot {
    protected static ApplicationManager app = ApplicationManager.get();
    protected static CommandFactory command = new CommandFactory();
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
        SendMessage message = command.commandHandler(update);
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
