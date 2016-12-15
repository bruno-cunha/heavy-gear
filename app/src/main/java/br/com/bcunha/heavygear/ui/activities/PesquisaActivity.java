package br.com.bcunha.heavygear.ui.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import br.com.bcunha.heavygear.R;
import br.com.bcunha.heavygear.model.db.HeavyGearAssetsHelper;
import br.com.bcunha.heavygear.model.pojo.Acao;
import br.com.bcunha.heavygear.ui.adapters.RvPesquisaAdapter;

public class PesquisaActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RvPesquisaAdapter rvPesquisaAdapter;
    private String  query;
    private HeavyGearAssetsHelper heavyGearAssetsHelper;
    private List<Acao> resultados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa);

        heavyGearAssetsHelper = new HeavyGearAssetsHelper(this);
        heavyGearAssetsHelper.openDB();
        Intent intent = getIntent();
        resultados = new ArrayList<Acao>();

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            query = intent.getStringExtra(SearchManager.QUERY);
            resultados = heavyGearAssetsHelper.pesquisaAcao(query);
        }

        toolbar = (Toolbar) findViewById(R.id.inc_toolbar);
        toolbar.setTitle(query);
        toolbar.inflateMenu(R.menu.menu_searchview);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_search:
                        break;
                }
                return true;
            }
        });
        setSupportActionBar(toolbar);

        recyclerView      = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager     = new LinearLayoutManager(this);
        rvPesquisaAdapter = new RvPesquisaAdapter(resultados);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(rvPesquisaAdapter);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            query      = intent.getStringExtra(SearchManager.QUERY);
            resultados = heavyGearAssetsHelper.pesquisaAcao(query);
            toolbar.setTitle(query);
            rvPesquisaAdapter.update(resultados);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_searchview, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        MenuItem menuItem     = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    protected void onDestroy() {
        heavyGearAssetsHelper.closeDB();
        super.onDestroy();
    }
}
