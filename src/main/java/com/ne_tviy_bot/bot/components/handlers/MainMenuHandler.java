package com.ne_tviy_bot.bot.components.handlers;

import com.ne_tviy_bot.bot.BotState;
import com.ne_tviy_bot.bot.service.MainMenuService;
import com.ne_tviy_bot.bot.service.ReplyMessagesService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import static com.ne_tviy_bot.core.constants.MessageConstants.LIST_OF_COMMANDS;


@Component
public class MainMenuHandler implements InputMessageHandler {
    private final ReplyMessagesService messagesService;
    private final MainMenuService mainMenuService;

    public MainMenuHandler(ReplyMessagesService messagesService, MainMenuService mainMenuService) {
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