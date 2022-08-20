package com.ne_tviy_bot.bot.state_handlers;

import com.ne_tviy_bot.bot.BotState;
import org.telegram.telegrambots.meta.api.objects.Message;

public class ChangeLinkHandler {
    public BotState handle(Message message) {
        String inputMsg = message.getText();
        BotState botState;

        if ("Назад".equals(inputMsg)) botState = BotState.MUSIC;
        else {
            botState = BotState.CHANGE_LINK;
        }

        return botState;
    }
}
