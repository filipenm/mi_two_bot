package com.binance_bot.binance;

import com.binance_bot.requests.Request;

public class Binance extends BinanceBase {
    public String getPrice(String coin) {
        return Request.getPrice("https://api.binance.com/api/v3/ticker/price?symbol=" + coin.toUpperCase() + "USDT");
    }

    public String getPrice(String firstCoin, String secondCoin) {
        return Request.getPrice("https://api.binance.com/api/v3/ticker/price?symbol=" + firstCoin.toUpperCase() + secondCoin.toUpperCase());
    }
}
