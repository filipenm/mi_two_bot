package com.binance_bot.commands;

import com.binance_bot.binance.Binance;
import org.telegram.telegrambots.api.methods.send.SendMessage;

import static com.binance_bot.core.constants.IMessageConstants.INVALID_COIN_PAIR;

public class CoinPrice extends CommandBase {
    Binance binance = new Binance();

    public SendMessage execute(String firstCoin, String secondCoin, SendMessage message) {
        String price = binance.getPrice(firstCoin, secondCoin);
        if (price == null) message.setText(INVALID_COIN_PAIR);
        else message.setText(firstCoin + " costs " + price + " of " + secondCoin);
        app.log().debug(message);
        return message;
    }
}
