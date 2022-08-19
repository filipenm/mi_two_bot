package com.ne_tviy_bot.bot.components.handlers.menu;

import com.ne_tviy_bot.bot.BotState;
import com.ne_tviy_bot.bot.components.handlers.InputMessageHandler;
import com.ne_tviy_bot.core.service.BinanceMenuService;
import com.ne_tviy_bot.core.service.CoinPairMenuService;
import com.ne_tviy_bot.core.service.ReplyMessagesService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import static com.ne_tviy_bot.core.constants.MessageConstants.CHOOSE_COMMAND_BINANCE_MENU;
import static com.ne_tviy_bot.core.constants.MessageConstants.COIN_PAIR_MENU;

@Component
public class CoinPairMenuHandler implements InputMessageHandler {
    private final ReplyMessagesService messagesService;
    private final CoinPairMenuService coinPairMenuService;

    public CoinPairMenuHandler(ReplyMessagesService messagesService, CoinPairMenuService coinPairMenuService) {
        this.messagesService = messagesService;
        this.coinPairMenuService = coinPairMenuService;
    }

    @Override
    public SendMessage handle(Message message) {
        return coinPairMenuService.getCoinPairMenuMessage(message.getChatId(), COIN_PAIR_MENU);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.BINANCE;
    }
}
