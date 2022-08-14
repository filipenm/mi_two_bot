package com.ne_tviy_bot.core.helpers;

import com.ne_tviy_bot.core.utils.JsonUtils;
import com.ne_tviy_bot.core.utils.RequestUtils;

import static com.ne_tviy_bot.core.constants.UrlConstants.BINANCE_API_URI;

public class BinanceHelper extends HelperBase {
    public String getPrice(String firstCoin, String secondCoin) {
        String price = null;
        String response;
        String url = BINANCE_API_URI + "/ticker/price?symbol=" + firstCoin.toUpperCase() + secondCoin.toUpperCase();
        try {
            response = RequestUtils.get(url).asString();
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
