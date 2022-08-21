package com.mi_two_bot.bot.components.input_messages;

import com.mi_two_bot.bot.BotState;
import com.mi_two_bot.bot.services.HelpMenuService;
import com.mi_two_bot.bot.services.ReplyMessagesService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import static com.mi_two_bot.core.constants.MessageConstants.HELP;

@Component
public class HelpMenuInputMessage implements InputMessageHandler {
    private final ReplyMessagesService messagesService;
    private final HelpMenuService helpMenuService;

    public HelpMenuInputMessage(ReplyMessagesService messagesService, HelpMenuService helpMenuService) {
        this.messagesService = messagesService;
        this.helpMenuService = helpMenuService;
    }

    @Override
    public SendMessage handle(Message message) {
        return helpMenuService.getHelpMenuMessage(message.getChatId(), HELP);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.HELP;
    }
}
