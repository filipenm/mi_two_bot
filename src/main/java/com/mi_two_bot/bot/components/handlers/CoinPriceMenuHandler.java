package com.mi_two_bot.bot.components.handlers;

import com.mi_two_bot.bot.BotState;
import com.mi_two_bot.bot.service.CoinPriceService;
import com.mi_two_bot.bot.service.ReplyMessagesService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import static com.mi_two_bot.core.constants.MessageConstants.COIN_PAIR_MENU;

@Component
public class CoinPriceMenuHandler implements InputMessageHandler {
    private final ReplyMessagesService messagesService;
    private final CoinPriceService coinPriceService;


    public CoinPriceMenuHandler(ReplyMessagesService messagesService, CoinPriceService coinPriceService) {
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
