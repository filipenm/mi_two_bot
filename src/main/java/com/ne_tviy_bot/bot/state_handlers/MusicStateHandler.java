package com.ne_tviy_bot.bot.state_handlers;

import com.ne_tviy_bot.bot.BotState;
import com.ne_tviy_bot.cache.UserDataCache;
import org.telegram.telegrambots.meta.api.objects.Message;


public class MusicStateHandler {
    public BotState handle(Message message, UserDataCache cache) {
        String inputMsg = message.getText();
        int userId = message.getFrom().getId();
        BotState botState;

        switch (inputMsg) {
            case "Змінити лінк" -> botState = BotState.CHANGE_LINK;
            case "Назад" -> botState = BotState.MAIN_MENU;
            default -> botState = cache.getUsersCurrentBotState(userId);
        }

        cache.setUsersCurrentBotState(userId, botState);

        return botState;
    }
}
