package br.com.bcunha.heavygear.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import br.com.bcunha.heavygear.R;
import br.com.bcunha.heavygear.model.pojo.Acao;
import br.com.bcunha.heavygear.model.pojo.RespostaSimplesMultipla;

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
    private List<Acao> acoes;

    public RvAdapter() {}

    public RvAdapter(List<Acao> acoes){
        this.acoes = acoes;
    }

    public static RvAdapter createFromQuote(List<RespostaSimplesMultipla.Quote> quoteAcoes){
        List<Acao> acoes  = new ArrayList<Acao>();

        for (RespostaSimplesMultipla.Quote quote: quoteAcoes) {
            acoes.add(new Acao (quote.getsymbol(),
                                quote.getName(),
                                "", //da erro quando vai pegar o preco da acao selecionada na pesquisa
                                Double.parseDouble(quote.getLastTradePriceOnly())));
        }

        return new RvAdapter(acoes);
    }

    @Override
    public int getItemCount() {
        if (acoes == null) {
            return 0;
        }
        return acoes.size();
    }

    @Override
    public void onBindViewHolder(RvViewHolder rvViewHolder, int position) {
        rvViewHolder.codigo.setText(acoes.get(position).getCodigo());
        rvViewHolder.empresa.setText(acoes.get(position).getEmpresa());
        rvViewHolder.cotacao.setText(String.format("%.2f", acoes.get(position).getCotacao()));
    }

    @Override
    public RvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new RvViewHolder(v);
    }

    public void add(Acao acao) {
        acoes.add(acao);
        notifyDataSetChanged();
    }

    public void remove(int position) {

    }

    public void update(List<Acao> novasAcoes) {
        acoes.clear();
        acoes.addAll(novasAcoes);
        notifyDataSetChanged();
    }
}
