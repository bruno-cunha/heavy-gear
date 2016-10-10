package br.com.bcunha.heavygear.model.api;

import br.com.bcunha.heavygear.model.pojo.Query;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by BRUNO on 09/10/2016.
 */

public interface IApi {

    @GET("http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22{codigo}.SA%22)&env=store://datatables.org/alltableswithkeys&format=json")
    Call<Query> getQuery (@Path("codigo") String codigo);
}
