package com.mi_two_bot.cache;

import com.mi_two_bot.bot.BotState;

public class StateCache {
    public BotState getPreviousBotState(BotState current) {
        switch (current) {
            case BINANCE, MUSIC -> current = BotState.MAIN_MENU;
            case CHANGE_LINK -> current = BotState.MUSIC;
            case SPOTIFY, APPLE_MUSIC, YOUTUBE_MUSIC -> current = BotState.CHANGE_LINK;
            case PAIR_PRICE -> current = BotState.BINANCE;
            case PAIR_PRICE_CALC -> current = BotState.PAIR_PRICE;
        }
        return current;
    }
}
