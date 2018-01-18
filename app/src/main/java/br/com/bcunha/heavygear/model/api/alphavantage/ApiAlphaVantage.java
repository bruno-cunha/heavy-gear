package br.com.bcunha.heavygear.model.api.alphavantage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.bcunha.heavygear.model.pojo.alphavantage.RespostaAcao;
import br.com.bcunha.heavygear.model.pojo.alphavantage.RespostaMoeda;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bruno on 09/01/18.
 */

public class ApiAlphaVantage {
    public static final String BASE_URL_AV = "http://www.alphavantage.co/";
    public static final String TIME_SERIES_INTRADAY = "TIME_SERIES_INTRADAY";
    public static final String TIME_SERIES_DAILY = "TIME_SERIES_DAILY";
    public static final String TIME_SERIES_MONTHLY ="TIME_SERIES_MONTHLY";
    public static final String DIGITAL_CURRENCY_INTRADAY = "DIGITAL_CURRENCY_INTRADAY";
    public static final String INTERVAL = "1min";
    public static final String MARKET = "BRL";


    public static Retrofit retrofit = null;

    public static Retrofit getRetrofit(){
        if(retrofit == null){
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(RespostaAcao.class, new RespostaAcaoDeserializer());
            gsonBuilder.registerTypeAdapter(RespostaMoeda.class, new RespostaMoedaDeserializer());
            Gson gson = gsonBuilder.create();

            retrofit = new Retrofit.Builder()
              .baseUrl(BASE_URL_AV)
              .addConverterFactory(GsonConverterFactory.create(gson))
              .build();
        }
        return retrofit;
    }

}
