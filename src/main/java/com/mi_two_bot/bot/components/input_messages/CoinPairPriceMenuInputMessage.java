package com.mi_two_bot.bot.components.input_messages;

import com.mi_two_bot.bot.BotState;
import com.mi_two_bot.bot.components.InputMessageHandler;
import com.mi_two_bot.bot.services.CoinPriceMenuService;
import com.mi_two_bot.bot.services.ReplyMessagesService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import static com.mi_two_bot.core.constants.MessageConstants.COIN_PAIR_MENU;

@Component
public class CoinPairPriceMenuInputMessage implements InputMessageHandler {
    private final ReplyMessagesService messagesService;
    private final CoinPriceMenuService coinPriceMenuService;


    public CoinPairPriceMenuInputMessage(ReplyMessagesService messagesService, CoinPriceMenuService coinPriceMenuService) {
        this.messagesService = messagesService;
        this.coinPriceMenuService = coinPriceMenuService;
    }

    @Override
    public SendMessage handle(Message message) {
        return coinPriceMenuService.getCoinPriceMenuMessage(message.getChatId(), COIN_PAIR_MENU);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.PAIR_PRICE;
    }
}
