package com.mi_two_bot.bot.components.handlers;

import com.mi_two_bot.bot.BotState;
import com.mi_two_bot.bot.service.BinanceMenuService;
import com.mi_two_bot.bot.service.ReplyMessagesService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import static com.mi_two_bot.core.constants.MessageConstants.CHOOSE_COMMAND_BINANCE_MENU;

@Component
public class BinanceMenuHandler implements InputMessageHandler {
    private final ReplyMessagesService messagesService;
    private final BinanceMenuService binanceMenuService;


    public BinanceMenuHandler(ReplyMessagesService messagesService, BinanceMenuService binanceMenuService) {
        this.messagesService = messagesService;
        this.binanceMenuService = binanceMenuService;
    }

    @Override
    public SendMessage handle(Message message) {
        return binanceMenuService.getBinanceMenuMessage(message.getChatId(), CHOOSE_COMMAND_BINANCE_MENU);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.BINANCE;
    }


}
