package br.com.bcunha.heavygear.model.pojo.ordem;

import java.util.Comparator;

import br.com.bcunha.heavygear.model.pojo.Ativo;

/**
 * Created by bruno on 06/06/17.
 */

public class OrdemAlfabetica implements Comparator<Ativo> {

    @Override
    public int compare(Ativo o1, Ativo o2) {
        return o1.getCodigo().compareToIgnoreCase(o2.getCodigo());
    }
}
