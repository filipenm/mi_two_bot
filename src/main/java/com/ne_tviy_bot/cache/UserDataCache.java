package com.ne_tviy_bot.cache;

import com.ne_tviy_bot.bot.BotState;
import com.ne_tviy_bot.core.ApplicationManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserDataCache implements DataCache {
    private static final ApplicationManager app = ApplicationManager.get();
    private final Map<Integer, List<BotState>> usersBotStates = new HashMap<>();

    @Override
    public void setUsersCurrentBotState(int userId, BotState botState) {
        List<BotState> list;
        if (usersBotStates.get(userId) == null) list = new ArrayList<>();
        else list = usersBotStates.get(userId);
        list.add(botState);
        usersBotStates.put(userId, list);
    }

    @Override
    public BotState getUsersCurrentBotState(int userId) {
        BotState botState;
        List<BotState> list = usersBotStates.get(userId);
        if (list == null) botState = BotState.MAIN_MENU;
        else botState = usersBotStates.get(userId).get(usersBotStates.size() - 1);

        app.log().info(botState);
        return botState;
    }

    public BotState getUsersPreviousBotState(int userId) {
        BotState botState;
        int size = usersBotStates.get(userId).size();
        if (size == 0 || size == 1) botState = BotState.MAIN_MENU;
        else botState = usersBotStates.get(userId).get(usersBotStates.size() - 2);

        app.log().info(botState);
        return botState;
    }
}
