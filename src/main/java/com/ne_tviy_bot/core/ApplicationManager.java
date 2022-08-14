package com.ne_tviy_bot.core;

import com.ne_tviy_bot.core.helpers.BinanceHelper;
import com.ne_tviy_bot.core.helpers.LogHelper;
import com.ne_tviy_bot.core.helpers.MusicHelper;

public class ApplicationManager {
    private final static ApplicationManager INSTANCE = new ApplicationManager();
    private BinanceHelper binanceHelper;
    private LogHelper logHelper;
    private MusicHelper musicHelper;

    public static ApplicationManager get() {
        return INSTANCE;
    }


    public LogHelper log() {
        if (logHelper == null) {
            logHelper = new LogHelper();
        }
        return logHelper;
    }

    public BinanceHelper binance() {
        if (binanceHelper == null) {
            binanceHelper = new BinanceHelper();
        }
        return binanceHelper;
    }

    public MusicHelper music() {
        if (musicHelper == null) {
            musicHelper = new MusicHelper();
        }
        return musicHelper;
    }
}
