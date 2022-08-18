package com.ne_tviy_bot.commands;

import com.ne_tviy_bot.commands.base.Command;
import com.ne_tviy_bot.commands.base.CommandFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class UndefinedCommand extends CommandFactory implements Command {
    @Override
    public SendMessage execute() {
        return message.setText("Try another command");
    }
}
