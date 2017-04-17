package br.com.bcunha.heavygear.model.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by BRUNO on 09/10/2016.
 */
public class ApiClient {
    public static final String BASE_URL = "http://query.yahooapis.com/v1/public/";
    public static final String QUERY_QUOTE = "select * from yahoo.finance.quote where symbol in (?codigo?)";
    public static final String QUERY_QUOTES = "select * from yahoo.finance.quotes where symbol in (\"?codigo?\")";
    public static final String ENV = "store://datatables.org/alltableswithkeys";
    public static final String FORMAT = "json";

    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
            .build();
        }
        return retrofit;
    }
}
