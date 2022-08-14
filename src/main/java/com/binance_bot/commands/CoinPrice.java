package com.binance_bot.commands;

import com.binance_bot.commands.base.Command;
import com.binance_bot.commands.base.CommandFactory;
import org.telegram.telegrambots.api.methods.send.SendMessage;

import static com.binance_bot.core.constants.IMessageConstants.INVALID_COIN_PAIR;

public class CoinPrice extends CommandFactory implements Command {
    public SendMessage execute(String firstCoin, String secondCoin) {
        String price = app.binance().getPrice(firstCoin, secondCoin);
        if (price == null) message.setText(INVALID_COIN_PAIR);
        else message.setText(firstCoin + " costs " + price + " of " + secondCoin);
        app.log().debug(message);
        return message;
    }

    @Override
    public SendMessage execute() {
        app.log().info("Invalid coin pair entered");
        return message.setText("Not a valid coin pair");
    }
}
