package com.mi_two_bot.bot.components.handlers;

import com.mi_two_bot.bot.BotState;
import com.mi_two_bot.bot.components.InputMessageHandler;
import com.mi_two_bot.bot.functionality.music.YoutubeMusicFunc;
import com.mi_two_bot.bot.services.ReplyMessagesService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class YoutubeMusicLinkHandler implements InputMessageHandler {
    private final ReplyMessagesService messagesService;
    private final YoutubeMusicFunc youtubeMusicFunc;


    public YoutubeMusicLinkHandler(ReplyMessagesService messagesService, YoutubeMusicFunc youtubeMusicFunc) {
        this.messagesService = messagesService;
        this.youtubeMusicFunc = youtubeMusicFunc;
    }

    @Override
    public SendMessage handle(Message message) {
        String url = message.toString();
        return new SendMessage(message.getChatId(), youtubeMusicFunc.handle(url));
    }

    @Override
    public BotState getHandlerName() {
        return BotState.YOUTUBE_MUSIC;
    }
}
