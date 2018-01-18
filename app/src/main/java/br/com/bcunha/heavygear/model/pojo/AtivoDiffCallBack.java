package br.com.bcunha.heavygear.model.pojo;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * Created by bruno on 24/07/17.
 */

public class AtivoDiffCallBack extends DiffUtil.Callback {

    public final List<Ativo> newAcoes;
    public final List<Ativo> oldAcoes;

    public AtivoDiffCallBack(List<Ativo> newAcoes, List<Ativo> oldAcoes) {
        this.newAcoes = newAcoes;
        this.oldAcoes = oldAcoes;
    }

    @Override
    public int getOldListSize() {
        return oldAcoes.size();
    }

    @Override
    public int getNewListSize() {
        return newAcoes.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldAcoes.get(oldItemPosition).getCodigo().equals(newAcoes.get(newItemPosition).getCodigo());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final Ativo oldAtivo = oldAcoes.get(oldItemPosition);
        final Ativo newAtivo = newAcoes.get(newItemPosition);

        if (oldAtivo.isViewExpanded()) {
            newAcoes.get(newItemPosition).setViewExpanded(true);
        }

        return oldAtivo.getCotacao() == newAtivo.getCotacao();
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }

}
