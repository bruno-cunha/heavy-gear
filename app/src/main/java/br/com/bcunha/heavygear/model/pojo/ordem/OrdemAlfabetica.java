package br.com.bcunha.heavygear.model.pojo.ordem;

import java.util.Comparator;

import br.com.bcunha.heavygear.model.pojo.Acao;

/**
 * Created by bruno on 06/06/17.
 */

public class OrdemAlfabetica implements Comparator<Acao> {

    @Override
    public int compare(Acao o1, Acao o2) {
        return o1.getCodigo().compareToIgnoreCase(o2.getCodigo());
    }
}
