package br.com.bcunha.heavygear.model.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import br.com.bcunha.heavygear.model.pojo.Acao;
import br.com.bcunha.heavygear.model.pojo.RespostaQuote;
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
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(RespostaQuote.class, new RespostaQuoteDeserializer());
            Gson gson = gsonBuilder.create();

            retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
        }
        return retrofit;
    }

    public static String formatCodigo(List<Acao> acoes) {
        if (acoes.size() == 0) {
            return "\"\"";
        }

        StringBuffer codigos = new StringBuffer();
        boolean primeiro = true;
        for (Acao acao : acoes) {
            if (primeiro) {
                codigos.append("\"").append(acao.getCodigo().toString()).append(".SA").append("\"");
                primeiro = false;
            } else {
                codigos.append(",").append("\"").append(acao.getCodigo().toString()).append(".SA").append("\"");
            }
        }
        return codigos.toString();
    }
}
