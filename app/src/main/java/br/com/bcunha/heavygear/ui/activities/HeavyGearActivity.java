package br.com.bcunha.heavygear.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Filter;
import android.widget.TextView;
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

public class HeavyGearActivity extends AppCompatActivity {

    private ApiInterface apiClient;
    private HeavyGearAssetsHelper heavyGearAssetsHelper;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RvAdapter rvAdapter;
    private List<Acao> acoes;
    private AutoCompleteAdapter autoCompleteAdapter;

    public class AcoesFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Acao> acoes = new ArrayList<Acao>();
            FilterResults filterResults = new FilterResults();
            String substr = charSequence.toString().toLowerCase();

            if(substr == null || substr.length() == 0) {
                filterResults.values = acoes;
                filterResults.count = acoes.size();
            } else {
                final ArrayList<Acao> retAcoes = new ArrayList<Acao>();
                for (Acao acao : acoes) {
                    try {
                        if(acao.getCodigo().toLowerCase().contains(charSequence))  {
                            retAcoes.add(acao);
                        }
                    } catch (Exception e) {
                        Log.i(Acao.class.toString(), e.getMessage());
                    }
                }
                filterResults.values = retAcoes;
                filterResults.count = retAcoes.size();
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            autoCompleteAdapter.clear();
            if (filterResults.count > 0) {
                for (Acao acao : (ArrayList<Acao>) filterResults.values) {
                    autoCompleteAdapter.add(acao);
                }
            }
        }
    }

    public class AutoCompleteAdapter extends ArrayAdapter<Acao> {

        private Filter filter;

        public AutoCompleteAdapter(Context context, int resource, List<Acao> objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.autocomplete_item,parent, false);
            };

            TextView txtCodigo = (TextView) convertView.findViewById(R.id.codigo);

            final Acao acao = getItem(position);

            txtCodigo.setText(acao.getCodigo());

            return convertView;
        }

        @Override
        public Filter getFilter() {
            if (filter == null){
                filter = new AcoesFilter();
            }
            return super.getFilter();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heavy_gear);

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

        ActionBar actionBar = getSupportActionBar(); // you can use ABS or the non-bc ActionBar
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_USE_LOGO | ActionBar.DISPLAY_SHOW_HOME
        | ActionBar.DISPLAY_HOME_AS_UP); // what's mainly important here is DISPLAY_SHOW_CUSTOM. the rest is optional

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // inflate the view that we created before
        View v = inflater.inflate(R.layout.toolbar_autocomplete, null);
        AutoCompleteAdapter autoCompleteAdapter = new AutoCompleteAdapter(this, R.layout.autocomplete_item, acoes);
        AutoCompleteTextView autoCompleteTextView =  (AutoCompleteTextView) v.findViewById(R.id.autoComplete);

        autoCompleteTextView.setAdapter(autoCompleteAdapter);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // do something when the user clicks
            }
        });
        actionBar.setCustomView(v);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
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

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }*/

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
