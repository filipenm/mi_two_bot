package com.mi_two_bot.bot.functionality.music;

import com.google.api.services.youtube.YouTube;
import org.springframework.stereotype.Component;

@Component
public class YoutubeMusicFunc implements MusicFunc {

    private static YouTube youTube;

    @Override
    public String handle(String url) {
        //YouTubeRequest<SearchListResponse> list =  YouTube.Search.List();
        String link = null;
        return link;
    }
}