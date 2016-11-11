package br.com.bcunha.heavygear.ui.activities;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.bcunha.heavygear.R;
import br.com.bcunha.heavygear.model.api.ApiClient;
import br.com.bcunha.heavygear.model.api.ApiInterface;
import br.com.bcunha.heavygear.model.pojo.Acao;
import br.com.bcunha.heavygear.model.pojo.RespostaInfo;
import br.com.bcunha.heavygear.model.pojo.RespostaValor;
import br.com.bcunha.heavygear.model.pojo.RespostaValorLista;
import br.com.bcunha.heavygear.ui.adapters.RvAdapter;
import br.com.bcunha.heavygear.ui.adapters.RvAdapterApiOffLine;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeavyGearActivity extends AppCompatActivity {

    private ApiInterface apiClient;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heavy_gear);

        apiClient = ApiClient.getRetrofit().create(ApiInterface.class);
        toolbar   = (Toolbar) findViewById(R.id.inc_toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.inflateMenu(R.menu.menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.action_search:
                        break;
                }
                return true;
            }
        });
        setSupportActionBar(toolbar);

        recyclerView  = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        apiClient.getQueryValorLista(
            ApiClient.QUERY_QUOTE_LISTA.replace("?codigo?", "\"BRFS3.SA\",\"GGBR3.SA\""),
            ApiClient.ENV,
            ApiClient.FORMAT)
            .enqueue(new Callback<RespostaValorLista>(){
                @Override
                public void onResponse(Call<RespostaValorLista> call,
                                       Response<RespostaValorLista> response) {
                    rvAdapter = new RvAdapter(response.body().getQuery().getResults().getQuote());
                    recyclerView.setAdapter(rvAdapter);
                }
                @Override
                public void onFailure(Call<RespostaValorLista> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
                }
            });

        //rvAdapter = new RvAdapterApiOffLine();
        //recyclerView.setAdapter(rvAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        MenuItem menuItem     = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)    MenuItemCompat.getActionView(menuItem);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    //@OnClick(R.id.btnCotacao)
    //public void buscar(View view) {
        /*apiClient.getQueryValor(
        ApiClient.QUERY_QUOTE_LISTA.replace("?codigo?", "\"BRFS3.SA\",\"GGBR3.SA\""),
        ApiClient.ENV,
        ApiClient.FORMAT)
        .enqueue(new Callback<RespostaValorLista>(){
            @Override
            public void onResponse(Call<RespostaValorLista> call, Response<RespostaValorLista> response) {
                txtCotacao.setText(response.body().toString());
            }
            @Override
            public void onFailure(Call<RespostaValorLista> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });*/
        /*apiClient.getQueryValor(
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
                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });*/
        /*Call<RespostaInfo> call =  apiClient.getQueryInfo(
            ApiClient.QUERY_QUOTES.replace("?codigo?", edtCotacao.getText().toString()),
            ApiClient.ENV,
            ApiClient.FORMAT);

        call.enqueue(new Callback<RespostaInfo>(){
            @Override
            public void onResponse(Call<RespostaInfo> call, Response<RespostaInfo> response) {
                response.body();
                RespostaInfo.Quote respostaInfo = response.body().getQuery().getResults().getQuote();
                txtCotacao.setText(Double.toString(respostaInfo.getLastTradePriceOnly()));
            }
            @Override
            public void onFailure(Call<RespostaInfo> call, Throwable t) {
                Toast.makeText(getContext(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }*/
}
