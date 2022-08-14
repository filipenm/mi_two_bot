package com.ne_tviy_bot.commands.base;

import com.ne_tviy_bot.commands.binance.CoinPrice;
import com.ne_tviy_bot.commands.StartCommand;
import com.ne_tviy_bot.commands.UndefinedCommand;
import com.ne_tviy_bot.commands.music.LinkConverter;
import com.ne_tviy_bot.core.ApplicationManager;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

import java.util.Arrays;
import java.util.List;

import static com.ne_tviy_bot.core.constants.TickerConstants.USDT;


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
            case "music" -> {
                LinkConverter linkConverter = new LinkConverter();
                message = linkConverter.execute();
            }
            case "binance" -> {
                message = message.setText("Not in development");
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
