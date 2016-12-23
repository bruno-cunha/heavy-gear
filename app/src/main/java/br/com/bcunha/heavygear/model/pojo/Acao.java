package br.com.bcunha.heavygear.model.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by BRUNO on 18/09/2016.
 */
public class Acao implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(codigo);
        parcel.writeString(empresa);
        parcel.writeString(tipo);
        parcel.writeDouble(cotacao);
    }

    // Creator
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Acao createFromParcel(Parcel in){
            return new Acao(in);
        }

        public Acao[] newArray(int size) {
            return new Acao[size];
        }
    };

    // "De-parcel object
    public Acao(Parcel in) {
        this.codigo = in.readString();
        this.empresa = in.readString();
        this.tipo = in.readString();
        this.cotacao = in.readDouble();
    }
}
