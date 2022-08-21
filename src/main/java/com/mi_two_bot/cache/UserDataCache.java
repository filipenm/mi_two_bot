package com.mi_two_bot.cache;

import com.mi_two_bot.bot.BotState;
import com.mi_two_bot.core.ApplicationManager;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserDataCache implements DataCache {
    private static final ApplicationManager app = ApplicationManager.get();
    private final Map<Integer, BotState> usersBotStates = new HashMap<>();

    @Override
    public void setUsersCurrentBotState(int userId, BotState botState) {
        usersBotStates.put(userId, botState);
    }

    @Override
    public BotState getUsersCurrentBotState(int userId) {
        BotState botState;
        if (usersBotStates.get(userId) == null) botState = BotState.MAIN_MENU;
        else botState = usersBotStates.get(userId);
        app.log().info(botState);
        return botState;
    }
}
