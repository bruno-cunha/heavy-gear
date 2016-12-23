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
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import br.com.bcunha.heavygear.R;
import br.com.bcunha.heavygear.model.api.ApiClient;
import br.com.bcunha.heavygear.model.api.ApiInterface;
import br.com.bcunha.heavygear.model.db.HeavyGearAssetsHelper;
import br.com.bcunha.heavygear.model.pojo.Acao;
import br.com.bcunha.heavygear.model.pojo.RespostaSimplesMultipla;
import br.com.bcunha.heavygear.ui.adapters.RvAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.com.bcunha.heavygear.R.menu.menu_searchview;

public class HeavyGearActivity extends AppCompatActivity {

    private ApiInterface apiClient;
    private HeavyGearAssetsHelper heavyGearAssetsHelper;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RvAdapter rvAdapter;
    private List<Acao> watchList;
    private AutoCompleteTextView autoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heavy_gear);

        Acao acaoBRFS3 = new Acao("BRFS3.SA", "Brasil Foodas S.A", "", 51.11);
        Acao acaoITSA4 = new Acao("ITSA4.SA", "Itau SA", "", 13.1);
        Acao acaoGGBR3 = new Acao("GGBR4.SA", "Gerdau", "", 13.1);
        Acao acaoGOAU4 = new Acao("GOAU4.SA", "Metalurgica Gerdau", "", 13.1);
        Acao acaoJBSS3 = new Acao("JBSS3.SA", "JBS", "", 13.1);

        watchList = new ArrayList<Acao>();
        watchList.add(acaoBRFS3);
        watchList.add(acaoITSA4);
        watchList.add(acaoGGBR3);
        watchList.add(acaoGOAU4);
        watchList.add(acaoJBSS3);

        heavyGearAssetsHelper = new HeavyGearAssetsHelper(this);
        heavyGearAssetsHelper.openDB();
        toolbar = (Toolbar) findViewById(R.id.inc_toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        apiClient = ApiClient.getRetrofit().create(ApiInterface.class);
    }

    @Override
    public void startActivity(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            //intent.putExtra("watchList", watchList);
            intent.putParcelableArrayListExtra("watchList", (ArrayList) watchList);
        }

        super.startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(menu_searchview, menu);

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

            watchList.add(heavyGearAssetsHelper.getAcao(codigo));
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
        ApiClient.QUERY_QUOTE_LISTA.replace("?codigo?", formatCodigo(watchList)),
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
