package com.mi_two_bot.bot.responses;

import com.mi_two_bot.bot.BotState;
import com.mi_two_bot.bot.functionality.BinanceFunc;
import com.mi_two_bot.bot.functionality.MusicFunc;
import com.mi_two_bot.bot.functionality.SpotifyFunc;
import com.mi_two_bot.cache.UserDataCache;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
public class UserResponse {

    private final UserDataCache userDataCache;
    private final BinanceFunc binanceFunc;
    private final SpotifyFunc spotifyFunc;
    private final MusicFunc musicFunc;

    public UserResponse(UserDataCache userDataCache,
                          BinanceFunc binanceFunc,
                          MusicFunc musicFunc,
                          SpotifyFunc spotifyFunc) {
        this.userDataCache = userDataCache;
        this.binanceFunc = binanceFunc;
        this.musicFunc = musicFunc;
        this.spotifyFunc = spotifyFunc;
    }

    public SendMessage sendResponse(BotState botState, Message message) {
        userDataCache.setUsersCurrentBotState(message.getFrom().getId(), botState);
        switch (botState) {
            case COIN_PAIR -> {
                return binanceFunc.getPrice(message);
            }
            case SPOTIFY -> {
                return spotifyFunc.getLink(message);
            }
            default -> {
                return musicFunc.getLink(message);
            }
        }
    }
}
