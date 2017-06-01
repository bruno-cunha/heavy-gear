package br.com.bcunha.heavygear.ui.activities;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.bcunha.heavygear.R;
import br.com.bcunha.heavygear.model.db.HeavyGearAssetsHelper;
import br.com.bcunha.heavygear.model.pojo.Acao;
import br.com.bcunha.heavygear.model.service.HeavyGearService;
import br.com.bcunha.heavygear.model.service.HeavyGearService.HeavyBinder;
import br.com.bcunha.heavygear.ui.adapters.HeavyGearRecycleViewAdapter;

import static br.com.bcunha.heavygear.R.menu.menu_heavy_gear;

public class HeavyGearActivity extends AppCompatActivity {

    public static final int REQUEST_PESQUISA = 1;
    public static final int REQUEST_CONFIGURACAO = 2;

    private static final String ACTION_HEAVYSERVICE = "ACTION_HEAVYSERVICE";

    private boolean todasAcoesInicio;

    private final SimpleDateFormat formatDate = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
    private HeavyGearAssetsHelper heavyGearAssetsHelper;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private TextView ultimaSincronizacao;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private HeavyGearRecycleViewAdapter heavyGearRecycleViewAdapter;
    private SharedPreferences sharedPreferences;
    private HeavyGearService heavyGearServiceBound;
    private Boolean isBound = false;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            HeavyBinder binder = (HeavyBinder) service;
            heavyGearServiceBound = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            heavyGearServiceBound = null;
            isBound = false;
        }
    };

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (ACTION_HEAVYSERVICE.equals(intent.getAction())) {
                heavyGearRecycleViewAdapter.update((ArrayList) intent.getParcelableArrayListExtra("watchList"));
                atualizaUltimaSincronizacao();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heavy_gear);

        // SQLite
        heavyGearAssetsHelper = new HeavyGearAssetsHelper(this);
        heavyGearAssetsHelper.openDB();

        // ToolBar
        toolbar = (Toolbar) findViewById(R.id.inc_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_black_36dp);

        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(1);
            }
        });

        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        iniciaRecycleView();
        iniciatNavigationDrawer();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(menu_heavy_gear, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.search){
            Intent intent = new Intent(this, PesquisaActivity.class);
            intent.putParcelableArrayListExtra("watchList", (ArrayList) heavyGearRecycleViewAdapter.watchList);
            startActivityForResult(intent, REQUEST_PESQUISA);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Bind Serviço
        if(!isBound) {
            Intent intent = new Intent(this, HeavyGearService.class);
            intent.putParcelableArrayListExtra("watchList", (ArrayList) heavyGearRecycleViewAdapter.watchList);
            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Registra o recevier do serviço
        LocalBroadcastManager.getInstance(this).registerReceiver(this.receiver, new IntentFilter(ACTION_HEAVYSERVICE));
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Desregistra o receier do serviço
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.receiver);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Salva watchList
        salvaWatchList();

        // UnBind Serviço
        if (isBound) {
            unbindService(serviceConnection);
            isBound = false;
        }
    }

    @Override
    protected void onDestroy() {
        heavyGearAssetsHelper.closeDB();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_PESQUISA && resultCode == RESULT_OK){
            heavyGearRecycleViewAdapter.update((ArrayList) data.getParcelableArrayListExtra("watchList"));
        } else if (requestCode == REQUEST_CONFIGURACAO && resultCode == RESULT_OK) {
            atualizaConfiguracoes();
        }
    }

    private void iniciaRecycleView() {
        // Carrega watchList
        List<Acao> watchList = new ArrayList<Acao>();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        String json = sharedPreferences.getString("watchList", "");

        if (!json.isEmpty()) {
            Type type = new TypeToken<List<Acao>>(){}.getType();
            watchList = new Gson().fromJson(json, type);
        } else if (watchList.size() == 0) {
            watchList.add(new Acao("PETR3", "Petrobras", "", 00.00, true));
        }

        // RecyclerView
        layoutManager = new LinearLayoutManager(this);
        heavyGearRecycleViewAdapter = new HeavyGearRecycleViewAdapter(watchList);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(heavyGearRecycleViewAdapter);

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                heavyGearRecycleViewAdapter.remove(viewHolder.getAdapterPosition());
            }

            @Override
            public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int fromPos, RecyclerView.ViewHolder target, int toPos, int x, int y) {
                super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void iniciatNavigationDrawer() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();

                switch (id){
                    case R.id.configuracoes:
                        startActivityForResult(new Intent(HeavyGearActivity.this, ConfiguracaoActivity.class), REQUEST_CONFIGURACAO);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.sobre:
                        startActivity(new Intent(HeavyGearActivity.this, SobreActivity.class));
                        drawerLayout.closeDrawers();
                        break;
                }
                return true;
            }
        });
        View header = navigationView.getHeaderView(0);
        ultimaSincronizacao = (TextView) header.findViewById(R.id.ultima_sincronizacao_datahora);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close){

            @Override
            public void onDrawerClosed(View v){
                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void atualizaConfiguracoes() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        Boolean oldTodasAcoesInicio = todasAcoesInicio;
        todasAcoesInicio = sharedPreferences.getBoolean(ConfiguracaoActivity.PREF_TODAS_ACOES_INICIO, false);
        if (todasAcoesInicio){
            heavyGearRecycleViewAdapter.update(heavyGearAssetsHelper.getAcoes());
        } else if (todasAcoesInicio != oldTodasAcoesInicio) {
            heavyGearRecycleViewAdapter.update(heavyGearAssetsHelper.pesquisaAcao("PETR3"));
        }
        if (isBound) {
            heavyGearServiceBound.atualizaTimer();
        }
        salvaWatchList();
    }

    private void salvaWatchList() {
        SharedPreferences.Editor preferencesEditor = sharedPreferences.edit();
        if (heavyGearRecycleViewAdapter.watchList.size() == 0){
            preferencesEditor.putString("watchList", "");
        } else {
            String json = new Gson().toJson(heavyGearRecycleViewAdapter.watchList);
            preferencesEditor.putString("watchList", json);
        }
        preferencesEditor.commit();
    }

    private Bitmap carregaBitmapAsset(String strName)
    {
        AssetManager assetManager = getAssets();
        InputStream istr = null;
        try {
            istr = assetManager.open(strName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(istr);
        return bitmap;
    }

    private void atualizaUltimaSincronizacao() {
        String ultimaSincronizacao = formatDate.format(Calendar.getInstance().getTime());
        this.ultimaSincronizacao.setText(ultimaSincronizacao.toString());
        SharedPreferences.Editor preferencesEditor = sharedPreferences.edit();
        preferencesEditor.putString("ultimaSincronizacao", ultimaSincronizacao);
        preferencesEditor.commit();
    }
}
