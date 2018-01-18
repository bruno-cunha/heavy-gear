package br.com.bcunha.heavygear.model.api.alphavantage;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import br.com.bcunha.heavygear.model.pojo.alphavantage.MetaDataAcao;
import br.com.bcunha.heavygear.model.pojo.alphavantage.MetaDataMoeda;
import br.com.bcunha.heavygear.model.pojo.alphavantage.RespostaAcao;
import br.com.bcunha.heavygear.model.pojo.alphavantage.RespostaMoeda;
import br.com.bcunha.heavygear.model.pojo.alphavantage.TimeMomentAcao;
import br.com.bcunha.heavygear.model.pojo.alphavantage.TimeMomentMoeda;
import br.com.bcunha.heavygear.model.pojo.alphavantage.TimeSeries;

/**
 * Created by bruno on 11/01/18.
 */

public class RespostaMoedaDeserializer implements JsonDeserializer<RespostaMoeda> {
    @Override
    public RespostaMoeda deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        RespostaMoeda respostaMoeda = new RespostaMoeda();

        final JsonObject jsonObject = json.getAsJsonObject();

        MetaDataMoeda metaDataMoeda = gson.fromJson(jsonObject.get("Meta Data"), MetaDataMoeda.class);
        respostaMoeda.setMetaDataAcao(metaDataMoeda);
        TimeMomentMoeda timeMomentMoeda = new TimeMomentMoeda();

        timeMomentMoeda.set1aPriceBRL(jsonObject.getAsJsonObject("Time Series (Digital Currency Intraday)").getAsJsonObject(metaDataMoeda.get7LastRefreshed()).get("1a. price (BRL)").getAsString());
        timeMomentMoeda.set1bPriceUSD(jsonObject.getAsJsonObject("Time Series (Digital Currency Intraday)").getAsJsonObject(metaDataMoeda.get7LastRefreshed()).get("1b. price (USD)").getAsString());
        timeMomentMoeda.set2Volume(jsonObject.getAsJsonObject("Time Series (Digital Currency Intraday)").getAsJsonObject(metaDataMoeda.get7LastRefreshed()).get("2. volume").getAsString());
        timeMomentMoeda.set3MarketCapUSD(jsonObject.getAsJsonObject("Time Series (Digital Currency Intraday)").getAsJsonObject(metaDataMoeda.get7LastRefreshed()).get("3. market cap (USD)").getAsString());

        TimeSeries timeSeries = new TimeSeries();
        timeSeries.setTimeMomentMoeda(timeMomentMoeda);

        respostaMoeda.setTimeSeries(timeSeries);

        return respostaMoeda;
    }
}
