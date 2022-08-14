package com.ne_tviy_bot.commands.binance;

import com.ne_tviy_bot.commands.base.Command;
import com.ne_tviy_bot.commands.base.CommandFactory;
import org.telegram.telegrambots.api.methods.send.SendMessage;

import static com.ne_tviy_bot.core.constants.MessageConstants.INVALID_COIN_PAIR;

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
