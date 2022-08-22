package com.mi_two_bot.bot.components.input_messages;

import com.mi_two_bot.bot.BotState;
import com.mi_two_bot.bot.services.MainMenuService;
import com.mi_two_bot.bot.services.ReplyMessagesService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import static com.mi_two_bot.core.constants.MessageConstants.LIST_OF_COMMANDS;


@Component
public class MainMenuInputMessage implements InputMessageHandler {
    private final ReplyMessagesService messagesService;
    private final MainMenuService mainMenuService;

    public MainMenuInputMessage(ReplyMessagesService messagesService, MainMenuService mainMenuService) {
        this.messagesService = messagesService;
        this.mainMenuService = mainMenuService;
    }

    @Override
    public SendMessage handle(Message message) {
        return mainMenuService.getMainMenuMessage(message.getChatId(), LIST_OF_COMMANDS);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.MAIN_MENU;
    }

}
