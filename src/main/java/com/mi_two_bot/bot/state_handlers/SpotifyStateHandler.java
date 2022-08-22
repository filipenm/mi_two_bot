package com.mi_two_bot.bot.state_handlers;

import com.mi_two_bot.bot.BotState;
import org.telegram.telegrambots.meta.api.objects.Message;

public class SpotifyStateHandler implements StateHandler{
    @Override
    public BotState handle(Message message) {
        String inputMsg = message.getText();
        BotState botState;

        if ("Назад".equals(inputMsg)) botState = BotState.CHANGE_LINK;
        else {
            botState = BotState.SPOTIFY;
        }

        return botState;
    }
}
