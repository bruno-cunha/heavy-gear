package br.com.bcunha.heavygear.ui.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.bcunha.heavygear.R;
import br.com.bcunha.heavygear.model.api.ApiClient;
import br.com.bcunha.heavygear.model.api.ApiInterface;
import br.com.bcunha.heavygear.model.db.HeavyGearAssetsHelper;
import br.com.bcunha.heavygear.model.pojo.Acao;
import br.com.bcunha.heavygear.model.pojo.RespostaSimplesMultipla;
import br.com.bcunha.heavygear.ui.adapters.RvAdapter;
import br.com.bcunha.heavygear.ui.adapters.RvPesquisaAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeavyGearActivity extends AppCompatActivity {

    private ApiInterface apiClient;
    private HeavyGearAssetsHelper heavyGearAssetsHelper;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RvAdapter rvAdapter;
    private List<Acao> acoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.testecoordinator);
        setContentView(R.layout.activity_heavy_gear);

        heavyGearAssetsHelper = new HeavyGearAssetsHelper(this);
        heavyGearAssetsHelper.openDB();
        toolbar = (Toolbar) findViewById(R.id.inc_toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.inflateMenu(R.menu.menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_search:
                        //botao menu
                        break;
                }
                return true;
            }
        });
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Acao acaoBRFS3 = new Acao("BRFS3.SA", "Brasil Foodas S.A", "", 51.11);
        Acao acaoITSA4 = new Acao("ITSA4.SA", "Itau SA", "", 13.1);
        Acao acaoGGBR3 = new Acao("GGBR4.SA", "Gerdau", "", 13.1);
        Acao acaoGOAU4 = new Acao("GOAU4.SA", "Metalurgica Gerdau", "", 13.1);
        Acao acaoJBSS3 = new Acao("JBSS3.SA", "JBS", "", 13.1);

        acoes = new ArrayList<Acao>();
        acoes.add(acaoBRFS3);
        acoes.add(acaoITSA4);
        acoes.add(acaoGGBR3);
        acoes.add(acaoGOAU4);
        acoes.add(acaoJBSS3);

        apiClient = ApiClient.getRetrofit().create(ApiInterface.class);
        /*apiClient.getQueryValorLista(
        ApiClient.QUERY_QUOTE_LISTA.replace("?codigo?", formatCodigo(acoes)),
        ApiClient.ENV,
        ApiClient.FORMAT)
        .enqueue(new Callback<RespostaSimplesMultipla>() {
            @Override
            public void onResponse(Call<RespostaSimplesMultipla> call,
                                   Response<RespostaSimplesMultipla> response) {
                rvAdapter = new RvAdapter().createFromQuote(response.body().getQuery().getResults().getQuote());
                recyclerView.setAdapter(rvAdapter);
            }

            @Override
            public void onFailure(Call<RespostaSimplesMultipla> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String sender;
        String codigo;

        if (intent.getStringExtra("sender").equals("PesquisaActivity")) {
            sender = intent.getStringExtra("sender");
            codigo = intent.getStringExtra("codigo");

            acoes.add(heavyGearAssetsHelper.getAcao(codigo));
            atualizar((Button) findViewById(R.id.atualizar));
        }
    }

    public String formatCodigo(List<Acao> acoes) {
        StringBuffer codigos = new StringBuffer();
        boolean primeiro = true;

        for (Acao acao : acoes) {
            if (primeiro) {
                codigos.append("\"").append(acao.getCodigo().toString()).append("\"");
                primeiro = false;
            } else {
                codigos.append(",").append("\"").append(acao.getCodigo().toString()).append("\"");
            }
        }
        return codigos.toString();
    }

    public void atualizar(View view) {
        apiClient.getQueryValorLista(
        ApiClient.QUERY_QUOTE_LISTA.replace("?codigo?", formatCodigo(acoes)),
        ApiClient.ENV,
        ApiClient.FORMAT)
        .enqueue(new Callback<RespostaSimplesMultipla>() {
            @Override
            public void onResponse(Call<RespostaSimplesMultipla> call,
                                   Response<RespostaSimplesMultipla> response) {
                rvAdapter = new RvAdapter().createFromQuote(response.body().getQuery().getResults().getQuote());
                recyclerView.setAdapter(rvAdapter);
            }

            @Override
            public void onFailure(Call<RespostaSimplesMultipla> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        heavyGearAssetsHelper.closeDB();
        super.onDestroy();
    }
}
