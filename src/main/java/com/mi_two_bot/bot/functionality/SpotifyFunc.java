package com.mi_two_bot.bot.functionality;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class SpotifyFunc {
    public SendMessage getLink(Message message) {
        return new SendMessage(message.getChatId(), "https://open.spotify.com/track/5l3BaJRvmztTvqW02qDb0c?si=6652f526bc4a4b69");
    }
}
