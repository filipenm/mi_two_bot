package com.ne_tviy_bot.cache;

import com.ne_tviy_bot.bot.BotState;
import com.ne_tviy_bot.core.ApplicationManager;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
        BotState botState = usersBotStates.get(userId);
        if (botState == null) {
            botState = BotState.MAIN_MENU;
        }
        app.log().info(botState);
        return botState;
    }
}
