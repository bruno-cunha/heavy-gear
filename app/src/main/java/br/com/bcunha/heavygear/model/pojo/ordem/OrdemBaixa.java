package br.com.bcunha.heavygear.model.pojo.ordem;

import java.util.Comparator;

import br.com.bcunha.heavygear.model.pojo.Ativo;

/**
 * Created by bruno on 06/06/17.
 */

public class OrdemBaixa implements Comparator<Ativo> {

    @Override
    public int compare(Ativo o1, Ativo o2) {
        if (o1.getVariacao() == o2.getVariacao()) {
            return 0;
        } else if (o1.getVariacao() < o2.getVariacao()) {
            return -1;
        }
        return 1;
    }
}
