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
    private final Map<BotState, InputMessageHandler> messageHandlers = new HashMap<>();

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
        if (isMainMenuState(currentState)) {
            return messageHandlers.get(BotState.MAIN_MENU);
        }
        if (isBinanceState(currentState)) {
            return messageHandlers.get(BotState.BINANCE);
        }
        if (isCoinPairState(currentState)) {
            return messageHandlers.get(BotState.COIN_PAIR);
        }
        return messageHandlers.get(currentState);
    }


    private boolean isMusicState(BotState currentState) {
        return currentState == BotState.MUSIC;
    }

    private boolean isMainMenuState(BotState currentState) {
        return currentState == BotState.MAIN_MENU;
    }

    private boolean isBinanceState(BotState currentState) {
        return currentState == BotState.BINANCE;
    }

    private boolean isCoinPairState(BotState currentState) {
        return currentState == BotState.BINANCE;
    }
}
