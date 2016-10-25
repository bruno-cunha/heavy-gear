package br.com.bcunha.heavygear.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.bcunha.heavygear.R;
import br.com.bcunha.heavygear.model.pojo.Acao;
import br.com.bcunha.heavygear.model.pojo.RespostaValorLista;

import static br.com.bcunha.heavygear.R.id.codigo;

/**
 * Created by BRUNO on 18/10/2016.
 */

public class RvAdapterApiOffLine extends RecyclerView.Adapter<RvAdapterApiOffLine.RvViewHolder> {

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

    private List<Acao> acoes = new ArrayList<Acao>() {{add(new Acao("BRFS3","BR Foods", "Ordinaria", 57.3));
                                                       add(new Acao("GGBR3","Gerdau", "Ordinaria", 10.0));
                                                       add(new Acao("RUMO3","Rumo", "Ordinaria", 6.80));}};

    public RvAdapterApiOffLine() {
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
        rvViewHolder.codigo.setText(acoes.get(position).getCodigo().toString());
        rvViewHolder.empresa.setText(acoes.get(position).getEmpresa().toString());
        rvViewHolder.cotacao.setText(Double.toString(acoes.get(position).getCotacao()));
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
