package com.ne_tviy_bot.commands;

import com.ne_tviy_bot.commands.base.Command;
import com.ne_tviy_bot.commands.base.CommandFactory;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;


import java.util.ArrayList;
import java.util.List;

public class StartCommand extends CommandFactory implements Command {
    @Override
    public SendMessage execute() {
        message.setText("List of available commands:");
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("music");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("binance");
        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);
        return message.setReplyMarkup(keyboardMarkup);
    }

}
