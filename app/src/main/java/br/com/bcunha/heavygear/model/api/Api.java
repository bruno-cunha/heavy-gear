package br.com.bcunha.heavygear.model.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.IOException;

import br.com.bcunha.heavygear.model.pojo.Query;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by BRUNO on 09/10/2016.
 */
public class Api {
    private static Api ourInstance = new Api();
    public static final String BASE_URL = "http://query.yahooapis.com/v1/public/";

    public static Api getInstance() {
        return ourInstance;
    }

    private Api() {
    }

    public Call getCotacao(String codigo) {

        Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build();

        IApi apiService = retrofit.create(IApi.class);
        String query  = "select * from yahoo.finance.quotes where symbol in (\"" + codigo + "\")";
        String env    = "store://datatables.org/alltableswithkeys";
        String format = "json";

        return apiService.getQuery(query, env, format);
    }
}
