package br.com.bcunha.heavygear.model.api;

import br.com.bcunha.heavygear.model.pojo.RespostaQuote;
import br.com.bcunha.heavygear.model.pojo.RespostaQuotes;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by BRUNO on 09/10/2016.
 */

public interface BuscaCotacaoInterface {

    @GET("yql")
    Call<RespostaQuote> getQuote(@retrofit2.http.Query("q") String query, @retrofit2.http.Query("env") String env, @retrofit2.http.Query("format") String format);

    @GET("yql")
    Call<RespostaQuotes> getQuotes(@retrofit2.http.Query("q") String query, @retrofit2.http.Query("env") String env, @retrofit2.http.Query("format") String format);
}
