package com.mi_two_bot.bot.components.input_messages;

import com.mi_two_bot.bot.BotState;
import com.mi_two_bot.bot.services.ChangeLinkService;
import com.mi_two_bot.bot.services.ReplyMessagesService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import static com.mi_two_bot.core.constants.MessageConstants.CHANGE_LINK_MENU;

@Component
public class ChangeLinkMenuInputMessage implements InputMessageHandler {
    private final ReplyMessagesService messagesService;
    private final ChangeLinkService changeLinkService;


    public ChangeLinkMenuInputMessage(ReplyMessagesService messagesService, ChangeLinkService changeLinkService) {
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
