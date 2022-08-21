package com.mi_two_bot.bot;

import com.mi_two_bot.bot.components.BotStateContext;
import com.mi_two_bot.bot.functionality.BinanceFunc;
import com.mi_two_bot.bot.functionality.MusicFunc;
import com.mi_two_bot.bot.state_handlers.BinanceStateHandler;
import com.mi_two_bot.bot.state_handlers.ChangeLinkHandler;
import com.mi_two_bot.bot.state_handlers.CoinPriceHandler;
import com.mi_two_bot.bot.state_handlers.MusicStateHandler;
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
    private final BinanceFunc binanceFunc;
    private final MusicFunc musicFunc;

    public TelegramFacade(UserDataCache userDataCache,
                          BotStateContext botStateContext,
                          BinanceFunc binanceFunc,
                          MusicFunc musicFunc) {
        this.userDataCache = userDataCache;
        this.botStateContext = botStateContext;
        this.binanceFunc = binanceFunc;
        this.musicFunc = musicFunc;
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

    private SendMessage sendResponse(BotState botState, Message message) {
        userDataCache.setUsersCurrentBotState(message.getFrom().getId(), botState);
        switch (botState) {
            case COIN_PAIR -> {
                return binanceFunc.getPrice(message);
            }
            case CHANGE_LINK -> {
                return musicFunc.getLink(message);
            }
            default -> {
                return musicFunc.getLink(message);
            }
        }
    }

    private SendMessage handleInputMessage(Message message) {
        String inputMsg = message.getText();
        int userId = message.getFrom().getId();
        BotState botState = userDataCache.getUsersCurrentBotState(userId);
        SendMessage replyMessage;

        switch (botState) {
            case BINANCE -> {
                BinanceStateHandler binanceStateHandler = new BinanceStateHandler();
                botState = binanceStateHandler.handle(message);
            }
            case MUSIC -> {
                MusicStateHandler musicStateHandler = new MusicStateHandler();
                botState = musicStateHandler.handle(message);
            }
            case COIN_PAIR -> {
                CoinPriceHandler coinPriceHandler = new CoinPriceHandler();
                botState = coinPriceHandler.handle(message);
                if (botState == BotState.COIN_PAIR) {
                    return sendResponse(botState, message);
                }
            }
            case CHANGE_LINK -> {
                ChangeLinkHandler changeLinkHandler = new ChangeLinkHandler();
                botState = changeLinkHandler.handle(message);
                if (botState == BotState.CHANGE_LINK) {
                    return sendResponse(botState, message);
                }
            }
        }

        switch (inputMsg) {
            case "Музика" -> botState = BotState.MUSIC;
            case "Binance" -> botState = BotState.BINANCE;
            case "Нагадування" -> botState = BotState.NOTIFICATION;
            case "Погода" -> botState = BotState.WEATHER;
        }
        userDataCache.setUsersCurrentBotState(userId, botState);
        replyMessage = botStateContext.processInputMessage(botState, message);

        return replyMessage;
    }

}
