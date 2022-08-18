package com.ne_tviy_bot.bot.components.handlers.menu;

import com.ne_tviy_bot.bot.BotState;
import com.ne_tviy_bot.bot.components.handlers.InputMessageHandler;
import com.ne_tviy_bot.core.service.MainMenuService;
import com.ne_tviy_bot.core.service.ReplyMessagesService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;


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
        return mainMenuService.getMainMenuMessage(message.getChatId(), messagesService.getReplyText("reply.mainMenu.welcomeMessage"));
    }

    @Override
    public BotState getHandlerName() {
        return BotState.MAIN_MENU;
    }

}
