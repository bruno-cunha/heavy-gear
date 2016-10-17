package br.com.bcunha.heavygear.model.api;

import br.com.bcunha.heavygear.model.pojo.Resposta;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by BRUNO on 09/10/2016.
 */

public interface IApi {

    //GET("yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22{codigo}.SA%22)&env=store://datatables.org/alltableswithkeys&format=json")
    @GET("yql")
    Call<Resposta> getQuery (@retrofit2.http.Query("q") String query, @retrofit2.http.Query("env") String env, @retrofit2.http.Query("format") String format);
}
