package com.mi_two_bot.bot.components.handlers;

import com.mi_two_bot.bot.BotState;
import com.mi_two_bot.bot.components.InputMessageHandler;
import com.mi_two_bot.bot.functionality.music.AppleMusicFunc;
import com.mi_two_bot.bot.services.ReplyMessagesService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class AppleMusicLinkHandler implements InputMessageHandler {
    private final ReplyMessagesService messagesService;
    private final AppleMusicFunc appleMusicFunc;


    public AppleMusicLinkHandler(ReplyMessagesService messagesService, AppleMusicFunc appleMusicFunc) {
        this.messagesService = messagesService;
        this.appleMusicFunc = appleMusicFunc;
    }

    @Override
    public SendMessage handle(Message message) {
        String url = message.toString();
        return new SendMessage(message.getChatId(), appleMusicFunc.handle(url));
    }

    @Override
    public BotState getHandlerName() {
        return BotState.PAIR_PRICE_CALC;
    }
}
