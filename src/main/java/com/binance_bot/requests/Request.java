package com.binance_bot.requests;

import com.binance_bot.core.utils.JsonUtils;

public class Request extends RequestBase {
    public static String  getPrice(String url) {
        String price = null;
        String response;
        try {
            response = RequestBase.get(url).asString();
            price = JsonUtils.getElementByJsonpath(response, "price");
            app.log().debug(price);
        }
        catch (Exception ex) {
            app.log().error("Unable to find price for the coin pair");
        }
        finally {
            return price;
        }
    }
}
