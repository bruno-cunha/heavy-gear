package br.com.bcunha.heavygear.ui.adapters;

import android.content.Context;
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

/**
 * Created by BRUNO on 18/10/2016.
 */

public class HeavyGearRecycleViewAdapter extends RecyclerView.Adapter<HeavyGearRecycleViewAdapter.HeavyGearRecycleViewHolder> {

    public static class HeavyGearRecycleViewHolder extends RecyclerView.ViewHolder {
        final ImageView logo;
        final TextView codigo;
        final TextView empresa;
        final TextView cotacao;

        public HeavyGearRecycleViewHolder(View view) {
            super(view);
            logo = (ImageView) view.findViewById(R.id.imgAcao);
            codigo = (TextView) view.findViewById(R.id.codigo);
            empresa = (TextView) view.findViewById(R.id.empresa);
            cotacao = (TextView) view.findViewById(R.id.cotacao);
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
        int imgId = context.getResources().getIdentifier(watchList.get(position).getCodigo().replaceAll("\\d", "").toLowerCase(),
                                                         "drawable",
                                                         context.getPackageName());
        if(imgId == 0){
            imgId = context.getResources().getIdentifier("logo_indisponivel",
                                                         "drawable",
                                                         context.getPackageName());
        }

        heavyGearRecycleViewHolder.logo.setImageResource(imgId);
        heavyGearRecycleViewHolder.codigo.setText(watchList.get(position).getCodigo());
        heavyGearRecycleViewHolder.empresa.setText(watchList.get(position).getEmpresa());
        heavyGearRecycleViewHolder.cotacao.setText(String.format("%.2f", watchList.get(position).getCotacao()));
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
        watchList.clear();
        watchList.addAll(novasAcoes);
        notifyDataSetChanged();
    }
}
