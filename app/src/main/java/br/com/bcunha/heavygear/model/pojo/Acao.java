package br.com.bcunha.heavygear.model.pojo;

/**
 * Created by BRUNO on 18/09/2016.
 */
public class Acao {
    private String codigo;
    private String empresa;
    private String tipo;

    public Acao(String codigo, String empresa, String tipo) {
        this.codigo = codigo;
        this.empresa = empresa;
        this.tipo = tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
