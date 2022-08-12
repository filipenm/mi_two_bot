package com.binance_bot.commands;

import org.telegram.telegrambots.api.methods.send.SendMessage;

public class UndefinedCommand {
    public SendMessage execute(SendMessage message) {
        return message.setText("Try another command");
    }
}
