package com.binance_bot.commands;

import com.binance_bot.commands.base.Command;
import com.binance_bot.commands.base.CommandFactory;
import org.telegram.telegrambots.api.methods.send.SendMessage;

public class UndefinedCommand extends CommandFactory implements Command {
    @Override
    public SendMessage execute() {
        return message.setText("Try another command");
    }
}
