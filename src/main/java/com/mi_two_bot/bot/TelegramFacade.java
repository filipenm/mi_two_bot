package com.mi_two_bot.bot;

import com.mi_two_bot.bot.components.BotStateContext;
import com.mi_two_bot.cache.StateCache;
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

    public TelegramFacade(UserDataCache userDataCache,
                          BotStateContext botStateContext) {
        this.userDataCache = userDataCache;
        this.botStateContext = botStateContext;
    }

    private boolean needCustomProcess(BotState botState) {
        return botState == BotState.PAIR_PRICE;
    }

    private boolean isBackMessage(Message message) {
        return message.getText().equalsIgnoreCase("назад");
    }

    private SendMessage handleNonDefaultMessages(SendMessage replyMessage, Message message, BotState botState) {
        int userId = message.getFrom().getId();
        if (botState == BotState.PAIR_PRICE) {
            botState = BotState.PAIR_PRICE_CALC;
            userDataCache.setUsersCurrentBotState(userId, botState);
            replyMessage = botStateContext.processInputMessage(botState, message);
        }
        return replyMessage;
    }

    public SendMessage handleUpdate(Update update) {
        SendMessage replyMessage = null;
        Message message = update.getMessage();
        int userId = message.getFrom().getId();
        BotState botState = userDataCache.getUsersCurrentBotState(userId);
        message = update.getMessage();

        if (needCustomProcess(botState) && !isBackMessage(message)) {
            replyMessage = handleNonDefaultMessages(null, message, botState);
        } else if (message.hasText()) {
            app.log().info(String.format("New message from User: %s, chatId: %s,  with text: %s",
                    message.getFrom().getUserName(), message.getChatId(), message.getText()));
            replyMessage = handleInputMessage(message);
        }

        return replyMessage;
    }

    private String[] getPossibleStates() {
        return new String[]{"назад", "музика", "binance", "змінити лінк", "help", "ціна пари", "spotify", "apple music", "youtube music"};
    }

    private SendMessage handleInputMessage(Message message) {
        String inputMsg = message.getText().toLowerCase();
        int userId = message.getFrom().getId();
        BotState botState = userDataCache.getUsersCurrentBotState(userId);
        SendMessage replyMessage;
        String[] states = getPossibleStates();

        int i;
        for (i = 0; i < states.length; ++i) {
            if (inputMsg.contains(states[i])) break;
        }

        switch (i) {
            case 0 -> botState = new StateCache().getPreviousBotState(userDataCache.getUsersCurrentBotState(userId));
            case 1 -> botState = BotState.MUSIC;
            case 2 -> botState = BotState.BINANCE;
            case 3 -> botState = BotState.CHANGE_LINK;
            case 4 -> botState = BotState.HELP;
            case 5 -> botState = BotState.PAIR_PRICE;
            case 6 -> botState = BotState.SPOTIFY;
            case 7 -> botState = BotState.APPLE_MUSIC;
            case 8 -> botState = BotState.YOUTUBE_MUSIC;
        }
        userDataCache.setUsersCurrentBotState(userId, botState);
        replyMessage = botStateContext.processInputMessage(botState, message);

        return replyMessage;
    }

}
