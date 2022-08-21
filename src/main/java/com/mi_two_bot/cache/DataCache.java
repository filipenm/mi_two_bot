package com.mi_two_bot.cache;

import com.mi_two_bot.bot.BotState;

public interface DataCache {
    void setUsersCurrentBotState(int userId, BotState botState);

    BotState getUsersCurrentBotState(int userId);
}
