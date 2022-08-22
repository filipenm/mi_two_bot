package com.mi_two_bot.bot.components;

import com.mi_two_bot.bot.BotState;
import com.mi_two_bot.bot.components.input_messages.InputMessageHandler;
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
        if (isChangeLinkState(currentState)) {
            return messageHandlers.get(BotState.CHANGE_LINK);
        }
        if (isHelpState(currentState)) {
            return messageHandlers.get(BotState.HELP);
        }
        if (isSpotifyState(currentState)) {
            return messageHandlers.get(BotState.SPOTIFY);
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
        return currentState == BotState.COIN_PAIR;
    }

    private boolean isChangeLinkState(BotState currentState) {
        return currentState == BotState.CHANGE_LINK;
    }

    private boolean isHelpState(BotState currentState) {
        return currentState == BotState.HELP;
    }

    private boolean isSpotifyState(BotState currentState) {
        return currentState == BotState.SPOTIFY;
    }
}
