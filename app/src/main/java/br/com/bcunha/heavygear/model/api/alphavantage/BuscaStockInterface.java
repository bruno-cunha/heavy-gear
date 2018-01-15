package br.com.bcunha.heavygear.model.api.alphavantage;

import com.google.gson.JsonElement;

import org.json.JSONObject;

import br.com.bcunha.heavygear.model.pojo.alphavantage.RespostaStock;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by bruno on 09/01/18.
 */

public interface BuscaStockInterface {
    @GET("query")
    Call<RespostaStock> getStock(@retrofit2.http.Query("function") String function,
                                 @retrofit2.http.Query("symbol") String symbol,
                                 @retrofit2.http.Query("interval") String interval,
                                 @retrofit2.http.Query("apikey") String apiKey);
}
