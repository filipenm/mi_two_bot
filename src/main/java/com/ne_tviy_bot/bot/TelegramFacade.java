package com.ne_tviy_bot.bot;

import com.ne_tviy_bot.bot.components.BotStateContext;
import com.ne_tviy_bot.bot.state_handlers.BinanceStateHandler;
import com.ne_tviy_bot.bot.state_handlers.MusicStateHandler;
import com.ne_tviy_bot.cache.UserDataCache;
import com.ne_tviy_bot.core.ApplicationManager;
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

    public TelegramFacade(UserDataCache userDataCache, BotStateContext botStateContext) {
        this.userDataCache = userDataCache;
        this.botStateContext = botStateContext;
    }

    public SendMessage handleUpdate(Update update) {
        SendMessage replyMessage = null;

        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            app.log().info(String.format("New message from User: %s, chatId: %s,  with text: %s",
                    message.getFrom().getUserName(), message.getChatId(), message.getText()));
            replyMessage = handleInputMessage(message);
        }
        return replyMessage;
    }

    private SendMessage handleInputMessage(Message message) {
        String inputMsg = message.getText();
        int userId = message.getFrom().getId();
        BotState botState;
        SendMessage replyMessage;

        switch (userDataCache.getUsersCurrentBotState(userId)) {
            case BINANCE -> {
                BinanceStateHandler binanceStateHandler = new BinanceStateHandler();
                binanceStateHandler.handle(message, userDataCache);
            }
            case MUSIC -> {
                MusicStateHandler musicStateHandler = new MusicStateHandler();
                musicStateHandler.handle(message, userDataCache);
            }
            default -> {

            }
        }

        switch (inputMsg) {
            case "Музика" -> botState = BotState.MUSIC;
            case "Binance" -> botState = BotState.BINANCE;
            case "Нагадування" -> botState = BotState.NOTIFICATION;
            case "Погода" -> botState = BotState.WEATHER;
            default -> botState = userDataCache.getUsersCurrentBotState(userId);
        }

        userDataCache.setUsersCurrentBotState(userId, botState);

        replyMessage = botStateContext.processInputMessage(botState, message);

        return replyMessage;
    }

}
