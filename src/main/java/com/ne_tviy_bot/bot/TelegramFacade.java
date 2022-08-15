package com.ne_tviy_bot.bot;

import com.ne_tviy_bot.bot.components.BotStateContext;
import com.ne_tviy_bot.cache.UserDataCache;
import com.ne_tviy_bot.core.ApplicationManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;


@Service
@Slf4j
public class TelegramFacade {
    private static ApplicationManager app = ApplicationManager.get();
    private UserDataCache userDataCache;
    private BotStateContext botStateContext;

    public TelegramFacade(UserDataCache userDataCache, BotStateContext botStateContext) {
        this.userDataCache = userDataCache;
        this.botStateContext = botStateContext;
    }

    public SendMessage handleUpdate(Update update) {
        SendMessage replyMessage = null;


        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            app.log().info("New message from User:{}, chatId: {},  with text: {}",
                    message.getFrom().getUserName(), message.getChatId(), message.getText());
            replyMessage = handleInputMessage(message);
        }

        return replyMessage;
    }

    private SendMessage handleInputMessage(Message message) {
        String inputMsg = message.getText();
        int userId = message.getFrom().getId();
        BotState botState;
        SendMessage replyMessage;

        switch (inputMsg) {
            case "music" -> {
                botState = BotState.MUSIC;
            }
            case "binance" -> {
                botState = BotState.BINANCE;
            }
            case "notification" -> {
                botState = BotState.NOTIFICATION;
            }
            case "weather" -> {
                botState = BotState.WEATHER;
            }
            default -> {
                botState = userDataCache.getUsersCurrentBotState(userId);
            }
        }

        userDataCache.setUsersCurrentBotState(userId, botState);

        replyMessage = botStateContext.processInputMessage(botState, message);

        return replyMessage;
    }

}
