package br.com.bcunha.heavygear.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.List;

import br.com.bcunha.heavygear.R;
import br.com.bcunha.heavygear.model.pojo.Acao;

/**
 * Created by bruno on 07/12/16.
 */

public class AutoCompleteAdapter extends ArrayAdapter<Acao> {

    private Filter filter;

    public AutoCompleteAdapter(Context context, int resource, List<Acao> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.autocomplete_item,parent, false);
        };

        TextView txtCodigo = (TextView) convertView.findViewById(R.id.codigo);

        final Acao acao = getItem(position);

        txtCodigo.setText(acao.getCodigo());

        return convertView;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        if (filter == null){
            filter = new AcoesFilter();
        }
        return super.getFilter();
    }
}
