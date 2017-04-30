package br.com.bcunha.heavygear.ui.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.bcunha.heavygear.R;
import br.com.bcunha.heavygear.model.db.HeavyGearAssetsHelper;
import br.com.bcunha.heavygear.model.pojo.Acao;
import br.com.bcunha.heavygear.ui.adapters.PesquisaRecycleViewAdapter;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;

public class PesquisaActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private PesquisaRecycleViewAdapter pesquisaRecycleViewAdapter;
    private String query;
    private HeavyGearAssetsHelper heavyGearAssetsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa);

        heavyGearAssetsHelper = new HeavyGearAssetsHelper(this);
        heavyGearAssetsHelper.openDB();

        List<Acao> resultados = new ArrayList<Acao>();
        List<Acao> watchList = new ArrayList<Acao>();
        Intent intent = getIntent();
        if (intent.hasExtra("watchList")) {
            watchList = intent.getParcelableArrayListExtra("watchList");
        }

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            query = intent.getStringExtra(SearchManager.QUERY);
            resultados = heavyGearAssetsHelper.pesquisaAcao(query);
            watchList = intent.getParcelableArrayListExtra("watchList");
        }

        toolbar = (Toolbar) findViewById(R.id.inc_toolbar);
        toolbar.setTitle(query);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), VERTICAL);
        pesquisaRecycleViewAdapter = new PesquisaRecycleViewAdapter(resultados, watchList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(pesquisaRecycleViewAdapter);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            query = intent.getStringExtra(SearchManager.QUERY);
            toolbar.setTitle(query);
            pesquisaRecycleViewAdapter.update(heavyGearAssetsHelper.pesquisaAcao(query));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pesquisa, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        MenuItem menuItem = menu.findItem(R.id.action_search);

        MenuItemCompat.setOnActionExpandListener(menuItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Intent intent = new Intent();
                intent.putParcelableArrayListExtra("watchList", (ArrayList) pesquisaRecycleViewAdapter.watchList);
                setResult(RESULT_OK, intent);
                finish();
                return false;
            }
        });

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint(getResources().getString(R.string.pesquisa_hint));
        MenuItemCompat.expandActionView(menuItem);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

//        if(id == android.R.id.home){
//            Intent intent = new Intent();
//            intent.putParcelableArrayListExtra("resultados", (ArrayList) pesquisaRecycleViewAdapter.resultados);
//            setResult(RESULT_OK, intent);
//            finish();
//        }
        return true;
        //return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        heavyGearAssetsHelper.closeDB();
        super.onDestroy();
    }
}
