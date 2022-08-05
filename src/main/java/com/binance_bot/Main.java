package com.binance_bot;

import com.binance_bot.binance.Binance;
import com.binance_bot.binance.BinanceBase;
import com.github.kshashov.telegram.api.TelegramMvcController;
import com.github.kshashov.telegram.api.bind.annotation.BotController;
import com.github.kshashov.telegram.api.bind.annotation.BotPathVariable;
import com.github.kshashov.telegram.api.bind.annotation.BotRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.binance_bot.core.constants.MessageConstants.INVALID_COIN_PAIR;

@BotController
@SpringBootApplication
public class Main implements TelegramMvcController {

    Binance binance = new Binance();

    @Value("${bot.token}")
    private String token;

    @Value("${bot.chatId}")
    private String chatId;

    @Override
    public String getToken() {
        return token;
    }

    @BotRequest(value = "/price {name:[\\S]+}")
    public String price(@BotPathVariable("name") String coin) {
        if (coin.contains("/")) {
            String[] coins = coin.split("/");
            String price = binance.getPrice(coins[0], coins[1]);
            return (price == null) ? INVALID_COIN_PAIR : coins[0] + " costs " + price + " of " + coins[1];
        }
        else {
            String price = binance.getPrice(coin);
            return (price == null) ? INVALID_COIN_PAIR :"Price of " + coin + " is " + price + " USDT";
        }
    }

    @BotRequest(value = "/start")
    public String start() {
        return "Hi, available commands:";
    }

    @BotRequest(value = "/price_of_pair {first:[\\S]+} {second:[\\S]+}")
    public String priceOfPair(@BotPathVariable("first") String firstCoin, @BotPathVariable("second") String secondCoin) {
        String price = binance.getPrice(firstCoin, secondCoin);
        return (price == null) ? INVALID_COIN_PAIR : firstCoin + " costs " + price + " of " + secondCoin;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }
}