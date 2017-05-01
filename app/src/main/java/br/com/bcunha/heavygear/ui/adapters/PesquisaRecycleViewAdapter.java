package br.com.bcunha.heavygear.ui.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.bcunha.heavygear.R;
import br.com.bcunha.heavygear.model.pojo.Acao;

/**
 * Created by bruno on 17/11/16.
 */

public class PesquisaRecycleViewAdapter extends RecyclerView.Adapter<PesquisaRecycleViewAdapter.PesquisaRecycleViewHolder> {

    public static class PesquisaRecycleViewHolder extends RecyclerView.ViewHolder {
        final ImageView logo;
        final TextView codigo;
        final TextView empresa;
        final ImageButton btnWatch;

        public PesquisaRecycleViewHolder(View view) {
            super(view);
            logo = (ImageView) view.findViewById(R.id.imgAcao);
            codigo = (TextView) view.findViewById(R.id.codigo);
            empresa = (TextView) view.findViewById(R.id.empresa);
            btnWatch = (ImageButton) view.findViewById(R.id.btnWatch);
        }
    }

    public List<Acao> resultados;

    public List<Acao> watchList;

    public PesquisaRecycleViewAdapter(List<Acao> resultados, List<Acao> watchList) {
        this.resultados = resultados;
        this.watchList = watchList;
        comparaResultadosEWatch();
    }

    public PesquisaRecycleViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_pesquisa, parent, false);
        return new PesquisaRecycleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final PesquisaRecycleViewHolder pesquisaRecycleViewHolder, final int position) {
        final Context context = pesquisaRecycleViewHolder.itemView.getContext();
        int imgId = context.getResources().getIdentifier(resultados.get(position).getCodigo().replaceAll("\\d", "").toLowerCase(),
                                                         "drawable",
                                                         context.getPackageName());
        if(imgId == 0){
            imgId = context.getResources().getIdentifier("logo_indisponivel",
                                                         "drawable",
                                                         context.getPackageName());
        }
        pesquisaRecycleViewHolder.logo.setImageResource(imgId);
        pesquisaRecycleViewHolder.codigo.setText(resultados.get(position).getCodigo());
        pesquisaRecycleViewHolder.empresa.setText(resultados.get(position).getEmpresa());
        if (resultados.get(position).isInWatch()) {
            pesquisaRecycleViewHolder.btnWatch.setBackgroundResource(R.drawable.ic_check_circle_black_36dp);
            pesquisaRecycleViewHolder.btnWatch.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.verdeClaro)));
        } else {
            pesquisaRecycleViewHolder.btnWatch.setBackgroundResource(R.drawable.ic_add_circle_black_36dp);
            pesquisaRecycleViewHolder.btnWatch.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.cinza)));
        }

        pesquisaRecycleViewHolder.btnWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (resultados.get(position).isInWatch()) {
                    resultados.get(position).setInWatch(false);
                    pesquisaRecycleViewHolder.btnWatch.setBackgroundResource(R.drawable.ic_add_circle_black_36dp);
                    pesquisaRecycleViewHolder.btnWatch.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(view.getContext(), R.color.cinza)));
                } else {
                    resultados.get(position).setInWatch(true);
                    pesquisaRecycleViewHolder.btnWatch.setBackgroundResource(R.drawable.ic_check_circle_black_36dp);
                    pesquisaRecycleViewHolder.btnWatch.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(view.getContext(), R.color.verdeClaro)));
                }
                if (resultados.get(position).isInWatch() && !watchList.contains(resultados.get(position))) {
                    watchList.add(resultados.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (resultados == null) {
            return 0;
        }
        return resultados.size();
    }

    public void add(Acao acao) {}

    public void remove(int position) {}

    public void update(List<Acao> novasAcoes) {
        resultados.clear();
        resultados.addAll(novasAcoes);
        comparaResultadosEWatch();
        notifyDataSetChanged();
    }

    public void comparaResultadosEWatch() {
        for (int contador = 0; contador < resultados.size(); contador++) {
            if (watchList.contains(resultados.get(contador))) {
                resultados.get(contador).setInWatch(true);
            }
        }
    }

    public void adicionaNoWatchLista(List<Acao> selecionados) {
        for (Acao acao : selecionados) {
            if (acao.isInWatch() && !watchList.contains(acao)) {
                watchList.add(acao);
            }
        }

    }
}
