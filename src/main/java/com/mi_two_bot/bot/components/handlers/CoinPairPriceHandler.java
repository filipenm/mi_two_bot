package com.mi_two_bot.bot.components.handlers;

import com.mi_two_bot.bot.BotState;
import com.mi_two_bot.bot.components.InputMessageHandler;
import com.mi_two_bot.bot.functionality.BinanceFunc;
import com.mi_two_bot.bot.services.CoinPriceMenuService;
import com.mi_two_bot.bot.services.ReplyMessagesService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class CoinPairPriceHandler implements InputMessageHandler {
    private final ReplyMessagesService messagesService;
    private final CoinPriceMenuService coinPriceMenuService;
    private final BinanceFunc binanceFunc;


    public CoinPairPriceHandler(ReplyMessagesService messagesService, CoinPriceMenuService coinPriceMenuService, BinanceFunc binanceFunc) {
        this.messagesService = messagesService;
        this.coinPriceMenuService = coinPriceMenuService;
        this.binanceFunc = binanceFunc;
    }

    @Override
    public SendMessage handle(Message message) {
        return binanceFunc.getPrice(message);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.PAIR_PRICE_CALC;
    }
}
