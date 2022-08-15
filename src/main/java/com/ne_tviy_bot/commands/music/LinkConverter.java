package com.ne_tviy_bot.commands.music;

import com.ne_tviy_bot.commands.base.Command;
import com.ne_tviy_bot.commands.base.CommandFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class LinkConverter extends CommandFactory implements Command {
    @Override
    public SendMessage execute() {
        return message.setText("Send the link to convert");
    }
}
