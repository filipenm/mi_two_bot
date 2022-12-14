package com.mi_two_bot.bot.components.input_messages;

import com.mi_two_bot.bot.BotState;
import com.mi_two_bot.bot.components.InputMessageHandler;
import com.mi_two_bot.bot.services.BinanceMenuService;
import com.mi_two_bot.bot.services.ReplyMessagesService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import static com.mi_two_bot.core.constants.MessageConstants.CHOOSE_COMMAND_BINANCE_MENU;

@Component
public class BinanceMenuInputMessage implements InputMessageHandler {
    private final ReplyMessagesService messagesService;
    private final BinanceMenuService binanceMenuService;


    public BinanceMenuInputMessage(ReplyMessagesService messagesService, BinanceMenuService binanceMenuService) {
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
