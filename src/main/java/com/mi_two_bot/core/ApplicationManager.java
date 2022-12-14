package com.mi_two_bot.core;

import com.mi_two_bot.core.helpers.LogHelper;

public class ApplicationManager {
    private final static ApplicationManager INSTANCE = new ApplicationManager();
    private LogHelper logHelper;

    public static ApplicationManager get() {
        return INSTANCE;
    }


    public LogHelper log() {
        if (logHelper == null) {
            logHelper = new LogHelper();
        }
        return logHelper;
    }
}
