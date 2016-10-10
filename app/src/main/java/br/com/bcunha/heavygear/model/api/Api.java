package br.com.bcunha.heavygear.model.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
    public static final String BASE_URL = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22GGBR3.SA%22)&env=store://datatables.org/alltableswithkeys&format=json"

    public static Api getInstance() {
        return ourInstance;
    }

    private Api() {
    }

    public void getCotacao() {

        Gson gson = new GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        .create();


        Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build();

        IApi apiService = retrofit.create(IApi.class);
        Call<Query> call = apiService.getQuery();
        call.enqueue(new Callback<Query> (){
            @Override
            public void onResponse(Call<Query> call, Response<Query> response) {
                int statusCode = response.code();
                Query user = response.body();
            }

            @Override
            public void onFailure(Call<Query> call, Throwable t) {
                // Log error here since request failed
            }
        });
    }
}
