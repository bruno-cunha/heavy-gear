package br.com.bcunha.heavygear.ui.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
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

import static android.R.attr.button;
import static br.com.bcunha.heavygear.R.menu.menu_searchview;

public class HeavyGearActivity extends AppCompatActivity {

    public static final String PREF = "Preferences";
    private ApiInterface apiClient;
    private HeavyGearAssetsHelper heavyGearAssetsHelper;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RvAdapter rvAdapter;
    private List<Acao> watchList;
    private List<Acao> acaoArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heavy_gear);

        watchList = new ArrayList<Acao>();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        Gson gson = new Gson();
        String json = sharedPreferences.getString("watchList", "");

        if (!json.isEmpty()) {
            Type type = new TypeToken<List<Acao>>(){}.getType();
            watchList = gson.fromJson(json, type);
        } else if (watchList.size() == 0) {
            Acao acaoBRFS3 = new Acao("BRFS3.SA", "Brasil Foodas S.A", "", 51.11, true);
            Acao acaoITSA4 = new Acao("ITSA4.SA", "Itau SA", "", 13.1, true);
            Acao acaoGGBR3 = new Acao("GGBR4.SA", "Gerdau", "", 13.1, true);
            Acao acaoGOAU4 = new Acao("GOAU4.SA", "Metalurgica Gerdau", "", 13.1, true);
            Acao acaoJBSS3 = new Acao("JBSS3.SA", "JBS", "", 13.1, true);

            watchList.add(acaoBRFS3);
            watchList.add(acaoITSA4);
            watchList.add(acaoGGBR3);
            watchList.add(acaoGOAU4);
            watchList.add(acaoJBSS3);
        }

        heavyGearAssetsHelper = new HeavyGearAssetsHelper(this);
        heavyGearAssetsHelper.openDB();
        toolbar = (Toolbar) findViewById(R.id.inc_toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        apiClient = ApiClient.getRetrofit().create(ApiInterface.class);

        Intent intent = getIntent();
        if (intent.hasExtra("activity") && intent.getStringExtra("activity").equals("PesquisaActivity")) {
            adicionaNoWatchLista(intent.<Acao>getParcelableArrayListExtra("result"));
            atualizar(findViewById(R.id.atualizar));
        }
    }

    @Override
    public void startActivity(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
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

    public void adicionaNoWatchLista(List<Acao> selecionados) {
        for (Acao acao : selecionados) {
            if (acao.isInWatch() && !watchList.contains(acao)) {
                watchList.add(acao);
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        //savedInstanceState.putParcelableArrayList("watchList", (ArrayList) watchList);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());

        Gson gson   = new Gson();
        String json = gson.toJson(watchList);

        SharedPreferences.Editor preferencesEditor = sharedPreferences.edit();
        preferencesEditor.putString("watchList", json);
        preferencesEditor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        heavyGearAssetsHelper.closeDB();
        super.onDestroy();
    }
}
