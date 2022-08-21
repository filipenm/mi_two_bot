package com.mi_two_bot.bot.components.input_messages;

import com.mi_two_bot.bot.BotState;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface InputMessageHandler {
    SendMessage handle(Message message);

    BotState getHandlerName();
}
