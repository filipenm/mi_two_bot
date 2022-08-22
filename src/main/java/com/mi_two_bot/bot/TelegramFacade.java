package com.mi_two_bot.bot;

import com.mi_two_bot.bot.components.BotStateContext;
import com.mi_two_bot.bot.responses.UserResponse;
import com.mi_two_bot.bot.state_handlers.*;
import com.mi_two_bot.cache.UserDataCache;
import com.mi_two_bot.core.ApplicationManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;


@Slf4j
@Service
public class TelegramFacade {

    private static final ApplicationManager app = ApplicationManager.get();

    private final UserDataCache userDataCache;
    private final BotStateContext botStateContext;
    private final UserResponse userResponse;

    public TelegramFacade(UserDataCache userDataCache,
                          BotStateContext botStateContext,
                          UserResponse userResponse) {
        this.userDataCache = userDataCache;
        this.botStateContext = botStateContext;
        this.userResponse = userResponse;
    }

    public SendMessage handleUpdate(Update update) {
        SendMessage replyMessage = null;
        Message message = update.getMessage();
        int userId = message.getFrom().getId();

        if (userDataCache.getUsersCurrentBotState(userId) == BotState.MAIN_MENU) {
            message = update.getMessage();
            if (message != null && message.hasText()) {
                app.log().info(String.format("New message from User: %s, chatId: %s,  with text: %s",
                        message.getFrom().getUserName(), message.getChatId(), message.getText()));
                replyMessage = handleInputMessage(message);
            }
        }
        else {
            BotState botState = userDataCache.getUsersCurrentBotState(userId);
            StateHandler stateHandler;
            switch (botState) {
                case BINANCE -> {
                    stateHandler = new BinanceStateHandler();
                    botState = stateHandler.handle(message);
                }
                case MUSIC -> {
                    stateHandler = new MusicStateHandler();
                    botState = stateHandler.handle(message);
                }
                case COIN_PAIR -> {
                    stateHandler = new CoinPriceHandler();
                    botState = stateHandler.handle(message);
                    if (botState == BotState.COIN_PAIR) {
                        return userResponse.sendResponse(botState, message);
                    }
                }
                case CHANGE_LINK -> {
                    stateHandler = new ChangeLinkHandler();
                    botState = stateHandler.handle(message);
                    if (botState == BotState.CHANGE_LINK) {
                        return userResponse.sendResponse(botState, message);
                    }
                }
                case SPOTIFY -> {
                    stateHandler = new SpotifyStateHandler();
                    botState = stateHandler.handle(message);
                    if(botState == BotState.SPOTIFY) {
                        return userResponse.sendResponse(botState, message);
                    }
                }
                default -> botState = BotState.MAIN_MENU;

            }
            userDataCache.setUsersCurrentBotState(userId, botState);
            replyMessage = botStateContext.processInputMessage(botState, message);
        }
        return replyMessage;
    }

    private SendMessage handleInputMessage(Message message) {
        String inputMsg = message.getText();
        int userId = message.getFrom().getId();
        BotState botState;
        SendMessage replyMessage;

        switch (inputMsg.toLowerCase()) {
            case "музика" -> botState = BotState.MUSIC;
            case "binance" -> botState = BotState.BINANCE;
            case "нагадування" -> botState = BotState.NOTIFICATION;
            case "погода" -> botState = BotState.WEATHER;
            case "help" -> botState = BotState.HELP;
            default -> botState = BotState.MAIN_MENU;
        }
        userDataCache.setUsersCurrentBotState(userId, botState);
        replyMessage = botStateContext.processInputMessage(botState, message);

        return replyMessage;
    }

}
