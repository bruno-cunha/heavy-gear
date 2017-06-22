package br.com.bcunha.heavygear.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import br.com.bcunha.heavygear.R;
import br.com.bcunha.heavygear.model.db.HeavyGearAssetsHelper;
import br.com.bcunha.heavygear.model.pojo.Acao;
import br.com.bcunha.heavygear.model.service.HeavyGearService;
import br.com.bcunha.heavygear.ui.adapters.HeavyGearRecycleViewAdapter;

public class DetalheActivity extends AppCompatActivity {

    private Acao acao;
    private Toolbar toolbar;
    private CardView cardView;
    private ImageView logo;
    private TextView codigo;
    private TextView empresa;
    private TextView cotacao;
    //private TextView variacao;
    private TextView minimaDia;
    private TextView maximaDia;
    private TextView minimaAno;
    private TextView maximaAno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        Intent intent = getIntent();
        if(intent.hasExtra("acao")){
            acao = intent.getParcelableExtra("acao");
        }

        // ToolBar
        toolbar = (Toolbar) findViewById(R.id.inc_toolbar);
        //toolbar.setNavigationIcon(R.drawable.ic_menu_black_36dp);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        iniciaCardView();
    }

    private void iniciaCardView() {
        cardView = (CardView) findViewById(R.id.cardview_detahe);
        logo = (ImageView) findViewById(R.id.imgAcao);
        codigo = (TextView) findViewById(R.id.codigo);
        empresa = (TextView) findViewById(R.id.empresa);
        cotacao = (TextView) findViewById(R.id.cotacao);
        //variacao = (CardView) findViewById(R.id.cardview_detahe);
        minimaDia = (TextView) findViewById(R.id.MinimaDia);
        maximaDia = (TextView) findViewById(R.id.MaximaDia);
        minimaAno = (TextView) findViewById(R.id.MinimaAno);
        maximaAno = (TextView) findViewById(R.id.MaximaAno);

        if(acao != null){
            logo.setImageResource(acao.getImgId(this));
            codigo.setText(acao.getCodigo());
            empresa.setText(acao.getEmpresa());
            cotacao.setText(String.format("%.2f",acao.getCotacao()));
            //variaca
            minimaDia.setText(String.format("%.2f",acao.getMinimaDia()));
            maximaDia.setText(String.format("%.2f",acao.getMaximaDia()));
            minimaAno.setText(String.format("%.2f",acao.getMinimaAno()));
            maximaAno.setText(String.format("%.2f",acao.getMaximaAno()));
        } else {
            Toast.makeText(this, "Impossível visualizar os detalhes da ação", Toast.LENGTH_LONG).show();
            finish();
        }
    }
}
