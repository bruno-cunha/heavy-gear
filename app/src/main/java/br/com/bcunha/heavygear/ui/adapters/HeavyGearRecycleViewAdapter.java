package br.com.bcunha.heavygear.ui.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
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

public class HeavyGearRecycleViewAdapter extends RecyclerView.Adapter<HeavyGearRecycleViewAdapter.RvViewHolder> {

    public static class RvViewHolder extends RecyclerView.ViewHolder {


        final ImageView logo;
        final TextView codigo;
        final TextView empresa;
        final TextView cotacao;

        public RvViewHolder(View view) {
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
    public int getItemCount() {
        if (watchList == null) {
            return 0;
        }
        return watchList.size();
    }

    @Override
    public void onBindViewHolder(RvViewHolder rvViewHolder, int position) {
        Context context = rvViewHolder.itemView.getContext();
        int imgId = context.getResources().getIdentifier(watchList.get(position).getCodigo().replaceAll("\\d", "").toLowerCase(),
                                                         "drawable",
                                                         context.getPackageName());
        rvViewHolder.logo.setImageResource(imgId);
        rvViewHolder.codigo.setText(watchList.get(position).getCodigo());
        rvViewHolder.empresa.setText(watchList.get(position).getEmpresa());
        rvViewHolder.cotacao.setText(String.format("%.2f", watchList.get(position).getCotacao()));
    }

    @Override
    public RvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new RvViewHolder(v);
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
