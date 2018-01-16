package br.com.bcunha.heavygear.model.api.alphavantage;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import br.com.bcunha.heavygear.model.pojo.alphavantage.TimeMoment;
import br.com.bcunha.heavygear.model.pojo.alphavantage.MetaData;
import br.com.bcunha.heavygear.model.pojo.alphavantage.RespostaAcao;
import br.com.bcunha.heavygear.model.pojo.alphavantage.TimeSeries;

/**
 * Created by bruno on 11/01/18.
 */

public class RespostaAcaoDeserializer implements JsonDeserializer<RespostaAcao> {
    @Override
    public RespostaAcao deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        RespostaAcao respostaAcao = new RespostaAcao();

        final JsonObject jsonObject = json.getAsJsonObject();

        MetaData metaData = gson.fromJson(jsonObject.get("Meta Data"), MetaData.class);
        respostaAcao.setMetaData(metaData);
        TimeMoment timeMoment = new TimeMoment();

        timeMoment.set1Open(jsonObject.getAsJsonObject("Time Series (Daily)").getAsJsonObject(metaData.get3LastRefreshed()).get("1. open").getAsString());
        timeMoment.set2High(jsonObject.getAsJsonObject("Time Series (Daily)").getAsJsonObject(metaData.get3LastRefreshed()).get("2. high").getAsString());
        timeMoment.set3Low(jsonObject.getAsJsonObject("Time Series (Daily)").getAsJsonObject(metaData.get3LastRefreshed()).get("3. low").getAsString());
        timeMoment.set4Close(jsonObject.getAsJsonObject("Time Series (Daily)").getAsJsonObject(metaData.get3LastRefreshed()).get("4. close").getAsString());
        timeMoment.set5Volume(jsonObject.getAsJsonObject("Time Series (Daily)").getAsJsonObject(metaData.get3LastRefreshed()).get("5. volume").getAsString());

        TimeSeries timeSeries = new TimeSeries();
        timeSeries.setTimeMoment(timeMoment);

        respostaAcao.setTimeSeries(timeSeries);

        return respostaAcao;
    }
}
