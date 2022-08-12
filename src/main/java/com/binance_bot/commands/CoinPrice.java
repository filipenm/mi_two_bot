package com.binance_bot.commands;

import com.binance_bot.binance.Binance;

import static com.binance_bot.core.constants.IMessageConstants.INVALID_COIN_PAIR;

public class CoinPrice extends CommandBase {
    Binance binance = new Binance();

    public String ofPair(String firstCoin, String secondCoin) {
        String price = binance.getPrice(firstCoin, secondCoin);

        return (price == null) ? INVALID_COIN_PAIR : firstCoin + " costs " + price + " of " + secondCoin;
    }
}
