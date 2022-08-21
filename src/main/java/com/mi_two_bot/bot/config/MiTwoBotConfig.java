package com.mi_two_bot.bot.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;

@Component
@ConfigurationProperties(prefix = "bot")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MiTwoBotConfig {
    String name;
    String token;
    DefaultBotOptions.ProxyType proxyType;
}