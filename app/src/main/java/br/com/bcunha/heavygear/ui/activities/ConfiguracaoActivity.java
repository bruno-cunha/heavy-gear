package br.com.bcunha.heavygear.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import br.com.bcunha.heavygear.R;
import br.com.bcunha.heavygear.ui.fragment.ConfiguracaoFragment;

public class ConfiguracaoActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao);

        toolbar = (Toolbar) findViewById(R.id.inc_toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new ConfiguracaoFragment()).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
