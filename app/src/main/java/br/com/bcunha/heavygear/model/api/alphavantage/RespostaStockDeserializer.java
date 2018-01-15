package br.com.bcunha.heavygear.model.api.alphavantage;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import br.com.bcunha.heavygear.model.pojo.alphavantage.LastRefreshed;
import br.com.bcunha.heavygear.model.pojo.alphavantage.MetaData;
import br.com.bcunha.heavygear.model.pojo.alphavantage.RespostaStock;
import br.com.bcunha.heavygear.model.pojo.alphavantage.TimeSeries1min;

/**
 * Created by bruno on 11/01/18.
 */

public class RespostaStockDeserializer implements JsonDeserializer<RespostaStock> {
    @Override
    public RespostaStock deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        RespostaStock respostaStock = new RespostaStock();

        final JsonObject jsonObject = json.getAsJsonObject();

        MetaData metaData = gson.fromJson(jsonObject.get("Meta Data"), MetaData.class);
        respostaStock.setMetaData(metaData);

        LastRefreshed lastRefreshed = new LastRefreshed();
        lastRefreshed.set1Open(jsonObject.getAsJsonObject("Time Series (1min)").getAsJsonObject(metaData.get3LastRefreshed()).get("1. open").getAsString());
        lastRefreshed.set2High(jsonObject.getAsJsonObject("Time Series (1min)").getAsJsonObject(metaData.get3LastRefreshed()).get("2. high").getAsString());
        lastRefreshed.set3Low(jsonObject.getAsJsonObject("Time Series (1min)").getAsJsonObject(metaData.get3LastRefreshed()).get("3. low").getAsString());
        lastRefreshed.set4Close(jsonObject.getAsJsonObject("Time Series (1min)").getAsJsonObject(metaData.get3LastRefreshed()).get("4. close").getAsString());
        lastRefreshed.set5Volume(jsonObject.getAsJsonObject("Time Series (1min)").getAsJsonObject(metaData.get3LastRefreshed()).get("5. volume").getAsString());

        TimeSeries1min timeSeries1min = new TimeSeries1min();
        timeSeries1min.setLastRefreshed(lastRefreshed);

        respostaStock.setTimeSeries1min(timeSeries1min);

        return respostaStock;
    }
}
