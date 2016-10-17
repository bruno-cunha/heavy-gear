package br.com.bcunha.heavygear.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.bcunha.heavygear.R;
import br.com.bcunha.heavygear.model.api.ApiClient;
import br.com.bcunha.heavygear.model.api.ApiInterface;
import br.com.bcunha.heavygear.model.pojo.RespostaInfo;
import br.com.bcunha.heavygear.model.pojo.RespostaValor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.com.bcunha.heavygear.model.api.ApiClient.getRetrofit;

public class HeavyGearActivity extends Activity {

    private ApiInterface apiClient;

    @BindView(R.id.edtCodigo)
    EditText edtCotacao;

    @BindView(R.id.txtCotacao)
    TextView txtCotacao;

    @BindView(R.id.btnCotacao)
    Button   btnCotacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heavy_gear);

        apiClient = ApiClient.getRetrofit().create(ApiInterface.class);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnCotacao)
    public void buscar(View view) {
        apiClient.getQueryValor(
            ApiClient.QUERY_QUOTE.replace("?codigo?", edtCotacao.getText().toString()),
            ApiClient.ENV,
            ApiClient.FORMAT)
        .enqueue(new Callback<RespostaValor>(){
            @Override
            public void onResponse(Call<RespostaValor> call, Response<RespostaValor> response) {
                RespostaValor.Quote  respostaValor =  response.body().getQuery().getResults().getQuote();
                txtCotacao.setText(respostaValor.getLastTradePriceOnly().toString());
            }
            @Override
            public void onFailure(Call<RespostaValor> call, Throwable t) {
                // Log error here since request failed
            }
        });
        /*apiClient.getQueryInfo(
            ApiClient.QUERY_QUOTES.replace("?codigo?", edtCotacao.getText().toString()),
            ApiClient.ENV,
            ApiClient.FORMAT)
        .enqueue(new Callback<RespostaInfo>(){
            @Override
            public void onResponse(Call<RespostaInfo> call, Response<RespostaInfo> response) {
                response.body();
                RespostaInfo.Quote respostaInfo = response.body().getQuery().getResults().getQuote();
                //txtCotacao.setText(Double.toString(respostaInfo.getLastTradePriceOnly()));
            }
            @Override
            public void onFailure(Call<RespostaInfo> call, Throwable t) {
                // Log error here since request failed
            }
        });*/
    }
}
