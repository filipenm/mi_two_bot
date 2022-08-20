package com.ne_tviy_bot.bot.state_handlers;

import com.ne_tviy_bot.bot.BotState;
import com.ne_tviy_bot.cache.UserDataCache;
import org.telegram.telegrambots.meta.api.objects.Message;


public class MusicStateHandler {
    public BotState handle(Message message) {
        String inputMsg = message.getText();
        BotState botState;

        switch (inputMsg) {
            case "Змінити лінк" -> botState = BotState.CHANGE_LINK;
            case "Назад" -> botState = BotState.MAIN_MENU;
            default -> botState = BotState.MUSIC;
        }

        return botState;
    }
}
