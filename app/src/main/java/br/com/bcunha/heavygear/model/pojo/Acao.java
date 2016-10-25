package br.com.bcunha.heavygear.model.pojo;

/**
 * Created by BRUNO on 18/09/2016.
 */
public class Acao {
    private String codigo;
    private String empresa;
    private String tipo;
    private double cotacao;

    public Acao(String codigo, String empresa, String tipo, double cotacao) {
        this.codigo = codigo;
        this.empresa = empresa;
        this.tipo = tipo;
        this.cotacao = cotacao;
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

    public double getCotacao() {
        return cotacao;
    }

    public void setCotacao(double cotacao) {
        this.cotacao = cotacao;
    }
}
