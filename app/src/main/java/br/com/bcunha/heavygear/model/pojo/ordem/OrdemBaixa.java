package br.com.bcunha.heavygear.model.pojo.ordem;

import java.util.Comparator;

import br.com.bcunha.heavygear.model.pojo.Acao;

/**
 * Created by bruno on 06/06/17.
 */

public class OrdemBaixa implements Comparator<Acao> {
    @Override
    public int compare(Acao o1, Acao o2) {
        if (o1.getVariacao() == o2.getVariacao()) {
            return 0;
        } else if (o1.getVariacao() < o2.getVariacao()) {
            return -1;
        }
        return 1;
    }
}
