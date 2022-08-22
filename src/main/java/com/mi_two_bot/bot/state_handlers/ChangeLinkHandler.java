package com.mi_two_bot.bot.state_handlers;

import com.mi_two_bot.bot.BotState;
import org.telegram.telegrambots.meta.api.objects.Message;

public class ChangeLinkHandler implements StateHandler {

    @Override
    public BotState handle(Message message) {
        String inputMsg = message.getText();
        BotState botState = BotState.CHANGE_LINK;

        switch (inputMsg.toLowerCase()) {
            case "spotify" -> botState = BotState.SPOTIFY;
            case "youtube music" -> botState = BotState.YOUTUBE_MUSIC;
            case "apple music" -> botState = BotState.APPLE_MUSIC;
            case "Назад" -> botState = BotState.MUSIC;
        }

        return botState;
    }
}
