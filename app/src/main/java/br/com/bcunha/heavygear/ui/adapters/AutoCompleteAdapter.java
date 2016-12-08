package br.com.bcunha.heavygear.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;

import java.util.List;

import br.com.bcunha.heavygear.R;
import br.com.bcunha.heavygear.model.pojo.Acao;

/**
 * Created by bruno on 07/12/16.
 */

public class AutoCompleteAdapter extends ArrayAdapter<Acao> {

    public AutoCompleteAdapter(Context context, int resource, List<Acao> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.autocomplete_item,parent, false);
        };
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return super.getFilter();
    }
}
