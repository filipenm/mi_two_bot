package com.mi_two_bot.bot.state_handlers;

import com.mi_two_bot.bot.BotState;
import org.telegram.telegrambots.meta.api.objects.Message;

public class HelpStateHandler implements StateHandler {

    @Override
    public BotState handle(Message message) {
        String inputMsg = message.getText();
        BotState botState;

        if ("Зрозуміло".equals(inputMsg)) botState = BotState.MAIN_MENU;
        else {
            botState = BotState.HELP;
        }
        return botState;
    }
}
