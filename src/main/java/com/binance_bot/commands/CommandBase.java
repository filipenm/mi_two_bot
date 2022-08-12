package com.binance_bot.commands;

import com.binance_bot.core.ApplicationManager;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

import java.util.Arrays;
import java.util.List;

import static com.binance_bot.core.constants.ITickerConstants.USDT;


public class CommandBase {
    protected static ApplicationManager app = ApplicationManager.get();

    public SendMessage commandHandler(Update update) {
        SendMessage message = new SendMessage();

        Long chatId = update.getMessage().getChatId();
        message.setChatId(chatId);

        String inputText = update.getMessage().getText();
        List<String> words = Arrays.asList(inputText.split(" "));
        app.log().debug(words);
        switch(words.get(0)) {
            case "/start":
                StartCommand startCommand = new StartCommand();
                message = startCommand.execute(message);
                break;
            case "/price":
                CoinPrice coinPrice = new CoinPrice();
                if (words.size() > 2) {
                    coinPrice.execute(words.get(1),words.get(2), message);
                }
                else  {
                    coinPrice.execute(words.get(1), USDT, message);
                }
                break;
            default:
                UndefinedCommand undefined = new UndefinedCommand();
                undefined.execute(message);
        }
        app.log().debug(message);
        return message;
    }
}
