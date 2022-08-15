package com.ne_tviy_bot.cache;

import com.ne_tviy_bot.bot.BotState;

public interface DataCache {
    void setUsersCurrentBotState(int userId, BotState botState);

    BotState getUsersCurrentBotState(int userId);
}
