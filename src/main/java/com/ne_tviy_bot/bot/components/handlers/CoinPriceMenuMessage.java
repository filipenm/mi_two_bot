package com.ne_tviy_bot.bot.components.handlers;

import com.ne_tviy_bot.bot.BotState;
import com.ne_tviy_bot.bot.service.CoinPriceService;
import com.ne_tviy_bot.bot.service.ReplyMessagesService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import static com.ne_tviy_bot.core.constants.MessageConstants.COIN_PAIR_MENU;

@Component
public class CoinPriceMenuMessage implements InputMessageHandler {
    private final ReplyMessagesService messagesService;
    private final CoinPriceService coinPriceService;


    public CoinPriceMenuMessage(ReplyMessagesService messagesService, CoinPriceService coinPriceService) {
        this.messagesService = messagesService;
        this.coinPriceService = coinPriceService;
    }

    @Override
    public SendMessage handle(Message message) {
        return coinPriceService.getCoinPriceMenuMessage(message.getChatId(), COIN_PAIR_MENU);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.COIN_PAIR;
    }
}
