package com.binance_bot.binance;

import com.binance_bot.requests.Request;

import static com.binance_bot.core.constants.IUrlConstants.BINANCE_API_URI;

public class Binance extends BinanceBase {
    public String getPrice(String coin) {
        return Request.getPrice(BINANCE_API_URI + "/ticker/price?symbol=" + coin.toUpperCase() + "USDT");
    }

    public String getPrice(String firstCoin, String secondCoin) {
        return Request.getPrice(BINANCE_API_URI + "/ticker/price?symbol=" + firstCoin.toUpperCase() + secondCoin.toUpperCase());
    }
}
