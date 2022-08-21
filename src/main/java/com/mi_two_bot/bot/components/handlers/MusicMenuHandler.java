package com.mi_two_bot.bot.components.handlers;

import com.mi_two_bot.bot.BotState;
import com.mi_two_bot.bot.service.MusicMenuService;
import com.mi_two_bot.bot.service.ReplyMessagesService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import static com.mi_two_bot.core.constants.MessageConstants.SEND_LINK;

@Component
public class MusicMenuHandler implements InputMessageHandler {
    private final ReplyMessagesService messagesService;
    private final MusicMenuService musicMenuService;

    public MusicMenuHandler(ReplyMessagesService messagesService, MusicMenuService musicMenuService) {
        this.messagesService = messagesService;
        this.musicMenuService = musicMenuService;
    }

    @Override
    public SendMessage handle(Message message) {
        return musicMenuService.getMusicMenuMessage(message.getChatId(), SEND_LINK);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.MUSIC;
    }
}
