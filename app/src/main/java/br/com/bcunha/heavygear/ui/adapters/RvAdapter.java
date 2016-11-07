package br.com.bcunha.heavygear.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

import br.com.bcunha.heavygear.R;
import br.com.bcunha.heavygear.model.pojo.RespostaValorLista;

/**
 * Created by BRUNO on 18/10/2016.
 */

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.RvViewHolder> {

    public static class RvViewHolder extends RecyclerView.ViewHolder{

        final TextView codigo;
        final TextView empresa;
        final TextView cotacao;

        public RvViewHolder(View view) {
            super(view);
            codigo  = (TextView) view.findViewById(R.id.codigo);
            empresa = (TextView) view.findViewById(R.id.empresa);
            cotacao = (TextView) view.findViewById(R.id.cotacao);
        }
    }
    private List<RespostaValorLista.Quote> quote;

    public RvAdapter(){
        this.quote = null;
    }

    public RvAdapter(List<RespostaValorLista.Quote> quote) {
        this.quote = quote;
    }


    @Override
    public int getItemCount() {
        if (quote == null) {
            return 0;
        }
        return quote.size();
    }

    @Override
    public void onBindViewHolder(RvViewHolder rvViewHolder, int position) {
        rvViewHolder.codigo.setText(quote.get(position).getSymbol().toString());
        rvViewHolder.empresa.setText(quote.get(position).getName().toString());
        rvViewHolder.cotacao.setText(quote.get(position).getLastTradePriceOnly().toString());
    }

    @Override
    public RvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        RvViewHolder rvViewHolder = new RvViewHolder(v);
        return rvViewHolder;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
