package com.ne_tviy_bot.bot.state_handlers;

import com.ne_tviy_bot.bot.BotState;
import org.telegram.telegrambots.meta.api.objects.Message;


public class CoinPriceHandler {
    public BotState handle(Message message) {
        String inputMsg = message.getText();
        BotState botState;

        if ("Назад".equals(inputMsg)) botState = BotState.BINANCE;
        else {
            botState = BotState.COIN_PAIR;
        }
        return botState;
    }
}
