package com.mi_two_bot.bot.components.handlers;

import com.mi_two_bot.bot.BotState;
import com.mi_two_bot.bot.components.InputMessageHandler;
import com.mi_two_bot.bot.functionality.music.SpotifyFunc;
import com.mi_two_bot.bot.services.ReplyMessagesService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class SpotifyLinkHandler implements InputMessageHandler {
    private final ReplyMessagesService messagesService;
    private final SpotifyFunc spotifyFunc;


    public SpotifyLinkHandler(ReplyMessagesService messagesService, SpotifyFunc spotifyFunc) {
        this.messagesService = messagesService;
        this.spotifyFunc = spotifyFunc;
    }

    @Override
    public SendMessage handle(Message message) {
        String url = message.toString();
        return new SendMessage(message.getChatId(), spotifyFunc.handle(url));
    }

    @Override
    public BotState getHandlerName() {
        return BotState.SPOTIFY;
    }
}
