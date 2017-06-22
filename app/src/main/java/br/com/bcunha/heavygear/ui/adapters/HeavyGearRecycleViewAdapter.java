package br.com.bcunha.heavygear.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.bcunha.heavygear.R;
import br.com.bcunha.heavygear.model.pojo.Acao;
import br.com.bcunha.heavygear.model.pojo.Quote;
import br.com.bcunha.heavygear.ui.activities.DetalheActivity;

/**
 * Created by BRUNO on 18/10/2016.
 */

public class HeavyGearRecycleViewAdapter extends RecyclerView.Adapter<HeavyGearRecycleViewAdapter.HeavyGearRecycleViewHolder> {

    public static class HeavyGearRecycleViewHolder extends RecyclerView.ViewHolder {
        final CardView cardView;
        final ImageView logo;
        final TextView codigo;
        final TextView empresa;
        final TextView moeda;
        final TextView cotacao;
        Acao acao;

        public HeavyGearRecycleViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.cardView_heavy_gear);
            logo = (ImageView) view.findViewById(R.id.imgAcao);
            codigo = (TextView) view.findViewById(R.id.codigo);
            empresa = (TextView) view.findViewById(R.id.empresa);
            moeda = (TextView) view.findViewById(R.id.moeda);
            cotacao = (TextView) view.findViewById(R.id.cotacao);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetalheActivity.class);
                    intent.putExtra("acao", acao);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    public List<Acao> watchList;

    public HeavyGearRecycleViewAdapter(List<Acao> watchList) {
        this.watchList = watchList;
    }

    public static HeavyGearRecycleViewAdapter createFromQuote(List<Quote> quoteAcoes) {
        List<Acao> acoes = new ArrayList<Acao>();

        for (Quote quote : quoteAcoes) {
            acoes.add(new Acao(quote.getsymbol(),
            quote.getName(),
            "",
            Double.parseDouble(quote.getLastTradePriceOnly())));
        }

        return new HeavyGearRecycleViewAdapter(acoes);
    }

    @Override
    public HeavyGearRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_heavy_gear, parent, false);
        return new HeavyGearRecycleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HeavyGearRecycleViewHolder heavyGearRecycleViewHolder, int position) {
        Context context = heavyGearRecycleViewHolder.itemView.getContext();
        heavyGearRecycleViewHolder.acao = watchList.get(position);

//        int imgId = context.getResources().getIdentifier(heavyGearRecycleViewHolder.acao.getCodigo().replaceAll("\\d", "").toLowerCase(),
//                                                         "drawable",
//                                                         context.getPackageName());
//        if(imgId == 0){
//            imgId = context.getResources().getIdentifier("logo_indisponivel",
//                                                         "drawable",
//                                                         context.getPackageName());
//        }

        heavyGearRecycleViewHolder.logo.setImageResource(heavyGearRecycleViewHolder.acao.getImgId(context));
        heavyGearRecycleViewHolder.codigo.setText(heavyGearRecycleViewHolder.acao.getCodigo());
        heavyGearRecycleViewHolder.empresa.setText(heavyGearRecycleViewHolder.acao.getEmpresa());
        heavyGearRecycleViewHolder.cotacao.setText(String.format("%.2f", heavyGearRecycleViewHolder.acao.getCotacao()));
        if (heavyGearRecycleViewHolder.acao.getVariacao() > 0) {
            heavyGearRecycleViewHolder.moeda.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.verde)));
            heavyGearRecycleViewHolder.cotacao.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.verde)));
        } else if (heavyGearRecycleViewHolder.acao.getVariacao() < 0) {
            heavyGearRecycleViewHolder.moeda.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.vermelho)));
            heavyGearRecycleViewHolder.cotacao.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.vermelho)));
        } else {
            heavyGearRecycleViewHolder.moeda.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.textoSecundario)));
            heavyGearRecycleViewHolder.cotacao.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.textoSecundario)));
        }
    }

    @Override
    public int getItemCount() {
        if (watchList == null) {
            return 0;
        }
        return watchList.size();
    }

    public void add(Acao acao) {
        watchList.add(acao);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        watchList.remove(position);
        notifyItemRemoved(position);
    }

    public void update(List<Acao> novasAcoes) {
        watchList = novasAcoes;
        notifyDataSetChanged();
    }
}
