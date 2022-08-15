package com.ne_tviy_bot.bot.components;

import com.ne_tviy_bot.bot.BotState;
import com.ne_tviy_bot.bot.components.handlers.InputMessageHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BotStateContext {
    private Map<BotState, InputMessageHandler> messageHandlers = new HashMap<>();

    public BotStateContext(List<InputMessageHandler> messageHandlers) {
        messageHandlers.forEach(handler -> this.messageHandlers.put(handler.getHandlerName(), handler));
    }

    public SendMessage processInputMessage(BotState currentState, Message message) {
        InputMessageHandler currentMessageHandler = findMessageHandler(currentState);
        return currentMessageHandler.handle(message);
    }

    private InputMessageHandler findMessageHandler(BotState currentState) {
        if (isMusicState(currentState)) {
            return messageHandlers.get(BotState.MUSIC);
        }
        return messageHandlers.get(currentState);
    }

    private boolean isMusicState(BotState currentState) {
        switch (currentState) {
            case MUSIC:
                return true;
            default:
                return false;
        }
    }

}
