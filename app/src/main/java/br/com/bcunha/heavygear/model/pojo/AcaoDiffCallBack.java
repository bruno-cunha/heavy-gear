package br.com.bcunha.heavygear.model.pojo;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * Created by bruno on 24/07/17.
 */

public class AcaoDiffCallBack extends DiffUtil.Callback {

    public final List<Acao> newAcoes;
    public final List<Acao> oldAcoes;

    public AcaoDiffCallBack(List<Acao> newAcoes, List<Acao> oldAcoes) {
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
        final Acao oldAcao = oldAcoes.get(oldItemPosition);
        final Acao newAcao = newAcoes.get(newItemPosition);

        return oldAcao.getCotacao() == newAcao.getCotacao();
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
