package br.com.bcunha.heavygear.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import br.com.bcunha.heavygear.R;
import br.com.bcunha.heavygear.ui.fragment.ConfiguracaoFragment;

public class ConfiguracaoActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static final String PREF_TODAS_ACOES_INICIO = "pref_todas_acoes_inicio";
    public static final String PREF_TODAS_ACOES_PESQUISA = "pref_todas_acoes_pesquisa";
    public static final String PREF_FREQUENCIA_ATUALIZACAO = "pref_frequencia_atualizacao";

    private Toolbar toolbar;
    private SharedPreferences sharedPreferences;
    private boolean prefAtualizar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao);

        toolbar = (Toolbar) findViewById(R.id.inc_toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new ConfiguracaoFragment()).commit();

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            Intent intent = new Intent();
            intent.putExtra("pref_atualizar", prefAtualizar);
            setResult(RESULT_OK, intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (!prefAtualizar) {
            prefAtualizar = true;
        }
    }
}
