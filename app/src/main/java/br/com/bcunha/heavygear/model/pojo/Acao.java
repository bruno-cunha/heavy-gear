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
    private double variacao;
    private double maximaDia;
    private double minimaDia;
    private double maximaAno;
    private double minimaAno;
    private int volumeNegociacao;
    private boolean inWatch;

    public Acao(String codigo){
        this.codigo = codigo;
    }

    public Acao(String codigo, String empresa, String tipo, double cotacao) {
        this.codigo = codigo;
        this.empresa = empresa;
        this.tipo = tipo;
        this.cotacao = cotacao;
    }

    public Acao(String codigo, String empresa, String tipo, double cotacao, boolean inWatch) {
        this.codigo = codigo;
        this.empresa = empresa;
        this.tipo = tipo;
        this.cotacao = cotacao;
        this.inWatch = inWatch;
    }

    public Acao(String codigo, String empresa, String tipo, double cotacao, double variacao, boolean inWatch) {
        this.codigo = codigo;
        this.empresa = empresa;
        this.tipo = tipo;
        this.cotacao = cotacao;
        this.variacao = variacao;
        this.inWatch = inWatch;
    }

    public Acao(String codigo, String empresa, String tipo, double cotacao, double variacao, double maximaDia, double minimaDia, double maximaAno, double minimaAno, int volumeNegociacao, boolean inWatch) {
        this.codigo = codigo;
        this.empresa = empresa;
        this.tipo = tipo;
        this.cotacao = cotacao;
        this.variacao = variacao;
        this.maximaDia = maximaDia;
        this.minimaDia = minimaDia;
        this.maximaAno = maximaAno;
        this.minimaAno = minimaAno;
        this.volumeNegociacao = volumeNegociacao;
        this.inWatch = inWatch;
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

    public double getVariacao() {
        return variacao;
    }

    public void setVariacao(double variacao) {
        this.variacao = variacao;
    }
    public double getMaximaDia() {
        return maximaDia;
    }

    public void setMaximaDia(double maximaDia) {
        this.maximaDia = maximaDia;
    }

    public double getMinimaDia() {
        return minimaDia;
    }

    public void setMinimaDia(double minimaDia) {
        this.minimaDia = minimaDia;
    }

    public double getMaximaAno() {
        return maximaAno;
    }

    public void setMaximaAno(double maximaAno) {
        this.maximaAno = maximaAno;
    }

    public double getMinimaAno() {
        return minimaAno;
    }

    public void setMinimaAno(double minimaAno) {
        this.minimaAno = minimaAno;
    }

    public int getVolumeNegociacao() {
        return volumeNegociacao;
    }

    public void setVolumeNegociacao(int volumeNegociacao) {
        this.volumeNegociacao = volumeNegociacao;
    }


    public boolean isInWatch() {
        return inWatch;
    }

    public void setInWatch(boolean inWatch) {
        this.inWatch = inWatch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Acao acao = (Acao) o;

        return codigo.equals(acao.codigo);

    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
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
        parcel.writeDouble(variacao);
        parcel.writeDouble(maximaDia);
        parcel.writeDouble(minimaDia);
        parcel.writeDouble(maximaAno);
        parcel.writeDouble(minimaAno);
        parcel.writeInt(volumeNegociacao);
        parcel.writeInt(inWatch ? 1 : 0);
    }

    // Creator
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Acao createFromParcel(Parcel in) {
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
        this.variacao = in.readDouble();
        this.maximaDia = in.readDouble();
        this.minimaDia = in.readDouble();
        this.maximaAno = in.readDouble();
        this.minimaAno = in.readDouble();
        this.volumeNegociacao = in.readInt();
        this.inWatch = (in.readInt() == 0) ? false : true;
    }
}