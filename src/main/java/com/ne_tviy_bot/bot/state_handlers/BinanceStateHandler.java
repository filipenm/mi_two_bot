package com.ne_tviy_bot.bot.state_handlers;

import com.ne_tviy_bot.bot.BotState;
import com.ne_tviy_bot.cache.UserDataCache;
import org.telegram.telegrambots.meta.api.objects.Message;

public class BinanceStateHandler {

    public BotState handle(Message message) {
        String inputMsg = message.getText();
        BotState botState;

        switch (inputMsg) {
            case "Ціна пари" -> botState = BotState.COIN_PAIR;
            case "Назад" -> botState = BotState.MAIN_MENU;
            default -> botState = BotState.BINANCE;
        }

        return botState;
    }
}
