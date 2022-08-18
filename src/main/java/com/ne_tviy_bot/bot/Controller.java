package com.ne_tviy_bot.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.objects.Update;


@Slf4j
@RestController
public class Controller {
    private final NeTviyBot telegramBot;

    public Controller(NeTviyBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void onUpdateReceived(@RequestBody Update update) {
        telegramBot.onUpdateReceived(update);
    }

}

