package com.binance_bot.commands.base;

import com.binance_bot.commands.CoinPrice;
import com.binance_bot.commands.StartCommand;
import com.binance_bot.commands.UndefinedCommand;
import com.binance_bot.core.ApplicationManager;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

import java.util.Arrays;
import java.util.List;

import static com.binance_bot.core.constants.ITickerConstants.USDT;


public class CommandFactory {
    protected static ApplicationManager app = ApplicationManager.get();
    protected static SendMessage message = new SendMessage();

    public SendMessage commandHandler(Update update) {
        Long chatId = update.getMessage().getChatId();
        message.setChatId(chatId);

        String inputText = update.getMessage().getText();
        List<String> words = Arrays.asList(inputText.split(" "));
        app.log().debug(words);
        switch(words.get(0)) {
            case "/start" -> {
                StartCommand startCommand = new StartCommand();
                message = startCommand.execute();
            }
            case "/price" -> {
                CoinPrice coinPrice = new CoinPrice();
                if (words.size() == 3) {
                    message = coinPrice.execute(words.get(1),words.get(2));
                }
                else if (words.size() == 2){
                    message = coinPrice.execute(words.get(1), USDT);
                }
                else message = coinPrice.execute();
            }
            default -> {
                UndefinedCommand undefined = new UndefinedCommand();
                message = undefined.execute();
            }
        }
        app.log().debug(message);
        return message;
    }
}
