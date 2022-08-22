package com.mi_two_bot.bot.components.input_messages;

import com.mi_two_bot.bot.BotState;
import com.mi_two_bot.bot.components.InputMessageHandler;
import com.mi_two_bot.bot.services.ReplyMessagesService;
import com.mi_two_bot.bot.services.SpotifyMenuService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import static com.mi_two_bot.core.constants.MessageConstants.SEND_LINK;

@Component
public class SpotifyMenuInputMessage implements InputMessageHandler {

    private final ReplyMessagesService messagesService;
    private final SpotifyMenuService spotifyMenuService;

    public SpotifyMenuInputMessage(ReplyMessagesService messagesService, SpotifyMenuService spotifyMenuService) {
        this.messagesService = messagesService;
        this.spotifyMenuService = spotifyMenuService;
    }

    @Override
    public SendMessage handle(Message message) {
        return spotifyMenuService.getSpotifyMenuMessage(message.getChatId(), SEND_LINK);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.SPOTIFY;
    }
}
