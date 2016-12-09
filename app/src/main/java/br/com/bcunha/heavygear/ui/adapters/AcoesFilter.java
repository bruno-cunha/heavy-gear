package br.com.bcunha.heavygear.ui.adapters;

import android.util.Log;
import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;

import br.com.bcunha.heavygear.model.pojo.Acao;

/**
 * Created by bruno on 08/12/16.
 */

public class AcoesFilter extends Filter {

    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        List<Acao> acoes = new ArrayList<Acao>();
        FilterResults filterResults = new FilterResults();
        String substr = charSequence.toString().toLowerCase();

        if(substr == null || substr.length() == 0) {
            filterResults.values = acoes;
            filterResults.count = acoes.size();
        } else {
            final ArrayList<Acao> retAcoes = new ArrayList<Acao>();
            for (Acao acao : acoes) {
                try {
                if(acao.getCodigo().toLowerCase().contains(charSequence))  {
                   retAcoes.add(acao);
                }
                } catch (Exception e) {
                    Log.i(Acao.class.toString(), e.getMessage());
                }
            }
            filterResults.values = retAcoes;
            filterResults.count = retAcoes.size();
        }
        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        //searchAdapater.Clear();
        if (filterResults.count > 0) {
            for (Acao acao : (ArrayList<Acao>) filterResults.values) {
                //searchAdapter.add(acao);
            }
        }

    }
}