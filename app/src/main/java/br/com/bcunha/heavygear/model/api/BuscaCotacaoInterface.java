package br.com.bcunha.heavygear.model.api;

import br.com.bcunha.heavygear.model.pojo.RespostaQuote;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by BRUNO on 09/10/2016.
 */

public interface BuscaCotacaoInterface {

    @GET("yql")
    Call<RespostaQuote> getQuotes(@retrofit2.http.Query("q") String query, @retrofit2.http.Query("env") String env, @retrofit2.http.Query("format") String format);
}
