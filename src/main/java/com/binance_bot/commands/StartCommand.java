package com.binance_bot.commands;

import org.telegram.telegrambots.api.methods.send.SendMessage;

public class StartCommand extends CommandBase {
    public SendMessage execute(SendMessage message) {
        return message.setText("Крипта - хуєта");
    }

}
