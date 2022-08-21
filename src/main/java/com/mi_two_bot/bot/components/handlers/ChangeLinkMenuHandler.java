package com.mi_two_bot.bot.components.handlers;

import com.mi_two_bot.bot.BotState;
import com.mi_two_bot.bot.service.ChangeLinkService;
import com.mi_two_bot.bot.service.ReplyMessagesService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import static com.mi_two_bot.core.constants.MessageConstants.CHANGE_LINK_MENU;

@Component
public class ChangeLinkMenuHandler implements InputMessageHandler{
    private final ReplyMessagesService messagesService;
    private final ChangeLinkService changeLinkService;


    public ChangeLinkMenuHandler(ReplyMessagesService messagesService, ChangeLinkService changeLinkService) {
        this.messagesService = messagesService;
        this.changeLinkService = changeLinkService;
    }

    @Override
    public SendMessage handle(Message message) {
        return changeLinkService.getChangeLinkMenuMessage(message.getChatId(), CHANGE_LINK_MENU);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.CHANGE_LINK;
    }
}
