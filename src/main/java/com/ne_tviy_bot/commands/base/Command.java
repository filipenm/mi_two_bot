package com.ne_tviy_bot.commands.base;

import org.telegram.telegrambots.api.methods.send.SendMessage;

public interface Command {
    SendMessage execute();
}
