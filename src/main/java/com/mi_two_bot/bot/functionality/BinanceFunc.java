package com.mi_two_bot.bot.functionality;

import com.mi_two_bot.core.ApplicationManager;
import com.mi_two_bot.core.utils.JsonUtils;
import com.mi_two_bot.core.utils.RequestUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

import static com.mi_two_bot.core.constants.UrlConstants.BINANCE_API_URI;

@Component
public class BinanceFunc {

    private static final ApplicationManager app = ApplicationManager.get();

    public SendMessage getPrice(Message message) {
        List<String> coins = List.of(message.getText().toUpperCase().split(" "));
        String price = null;
        String response;
        String url = BINANCE_API_URI + "/ticker/price?symbol=" + coins.get(0) + coins.get(1);
        try {
            response = RequestUtils.get(url).asString();
            price = JsonUtils.getElementByJsonpath(response, "price");
            app.log().debug(price);
        } catch (Exception ex) {
            app.log().error("Unable to find price for the coin pair");
        } finally {
            assert price != null;
            return new SendMessage(message.getChatId(), price);
        }
    }
}
