package br.com.bcunha.heavygear.model.api;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import br.com.bcunha.heavygear.model.pojo.Query;
import br.com.bcunha.heavygear.model.pojo.Quote;
import br.com.bcunha.heavygear.model.pojo.RespostaQuote;
import br.com.bcunha.heavygear.model.pojo.Results;

/**
 * Created by bruno on 17/04/17.
 */

public class RespostaQuoteDeserializer implements JsonDeserializer<RespostaQuote> {

    @Override
    public RespostaQuote deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        RespostaQuote respostaQuote;

        final JsonObject jsonObject = json.getAsJsonObject();
        if (jsonObject.getAsJsonObject("query").get("count").getAsInt() <= 1) {
            final JsonElement jsonQuoteElement = jsonObject.getAsJsonObject("query").getAsJsonObject("results").get("quote");
            Quote quote = gson.fromJson(jsonQuoteElement, Quote.class);

            List<Quote> listaQuotes = new ArrayList<Quote>();
            listaQuotes.add(quote);

            respostaQuote = new RespostaQuote(new Query(jsonObject.getAsJsonObject("query").get("count").getAsInt(), new Results(listaQuotes)));
        } else {
            respostaQuote = gson.fromJson(json, RespostaQuote.class);
        }

        return respostaQuote;
    }
}
