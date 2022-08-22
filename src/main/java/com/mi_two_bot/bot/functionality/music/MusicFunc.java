package com.mi_two_bot.bot.functionality.music;

import com.mi_two_bot.core.ApplicationManager;
import org.springframework.stereotype.Component;


@Component
public interface MusicFunc {
    ApplicationManager app = ApplicationManager.get();

    String handle(String url);
}
