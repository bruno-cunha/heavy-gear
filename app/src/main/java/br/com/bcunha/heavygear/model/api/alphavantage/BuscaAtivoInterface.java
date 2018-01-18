package br.com.bcunha.heavygear.model.api.alphavantage;

import br.com.bcunha.heavygear.model.pojo.alphavantage.RespostaAcao;
import br.com.bcunha.heavygear.model.pojo.alphavantage.RespostaMoeda;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by bruno on 09/01/18.
 */

public interface BuscaAtivoInterface {
    @GET("query")
    Call<RespostaAcao> getStock(@retrofit2.http.Query("function") String function,
                                @retrofit2.http.Query("symbol") String symbol,
                                @retrofit2.http.Query("interval") String interval,
                                @retrofit2.http.Query("apikey") String apiKey);

    @GET("query")
    Call<RespostaMoeda> getCurrency(@retrofit2.http.Query("function") String function,
                                    @retrofit2.http.Query("symbol") String symbol,
                                    @retrofit2.http.Query("market") String market,
                                    @retrofit2.http.Query("apikey") String apiKey);

}
