package br.com.bcunha.heavygear.ui.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.bcunha.heavygear.R;
import br.com.bcunha.heavygear.model.pojo.Acao;
import br.com.bcunha.heavygear.ui.activities.HeavyGearActivity;

/**
 * Created by bruno on 17/11/16.
 */

public class RvPesquisaAdapter extends RecyclerView.Adapter<RvPesquisaAdapter.RvPesquisaViewHolder> {

    public static class RvPesquisaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView textView;
        final ViewHolderClicks listener;

        public RvPesquisaViewHolder(View view, ViewHolderClicks listener) {
            super(view);
            this.listener = listener;
            textView = (TextView) view.findViewById(R.id.textView);
            textView.setOnClickListener(this);
        }

        @Override
        public void onClick (View view) {
            if (view instanceof TextView) {
                listener.onSelect((TextView) view);
            }
        }

        public static interface ViewHolderClicks {
            public void onSelect(TextView textView);
        }
    }

    private List<Acao> resultados;

    public RvPesquisaAdapter(List<Acao> resultados) {
        this.resultados = resultados;
    }

    public RvPesquisaViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        RvPesquisaViewHolder rvPesquisaViewHolder = new RvPesquisaViewHolder(v, new RvPesquisaViewHolder.ViewHolderClicks() {
            @Override
            public void onSelect(TextView textView) {
                Intent intent = new Intent(parent.getContext(), HeavyGearActivity.class);
                intent.putExtra("sender", "PesquisaActivity");
                intent.putExtra("codigo", textView.getText());
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                parent.getContext().startActivity(intent);
            }
        });
        return rvPesquisaViewHolder;
    }

    @Override
    public void onBindViewHolder(RvPesquisaViewHolder rvPesquisaViewHolder, int position) {
        rvPesquisaViewHolder.textView.setText(resultados.get(position).getCodigo());
    }

    @Override
    public int getItemCount() {
        if (resultados == null) {
            return 0;
        }
        return resultados.size();
    }

    public void add(Acao acao) {

    }

    public void remove(int position) {}

    public void update(List<Acao> novasAcoes) {
        resultados.clear();
        resultados.addAll(novasAcoes);
        notifyDataSetChanged();
    }
}