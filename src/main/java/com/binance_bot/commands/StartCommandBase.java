package com.binance_bot.commands;

import com.github.kshashov.telegram.api.bind.annotation.BotRequest;

public class StartCommandBase extends CommandBase {
    @BotRequest(value = "/start")
    public String start() {
        return "Hi, available commands listed below.";
    }

}
