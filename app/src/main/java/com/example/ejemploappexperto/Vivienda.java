package com.example.ejemploappexperto;

import android.os.Parcel;
import android.os.Parcelable;

public class Vivienda implements Parcelable {

    private String idvivienda;
    private String materialpiso;
    private String materialpared;
    private String acabadopared;
    private String materialtecho;
    private String tiposanitario;
    private String drenaje;
    private String idfamiliar;

    public Vivienda() {
    }

    public Vivienda(String idvivienda, String materialpiso, String materialpared, String acabadopared, String materialtecho, String tiposanitario, String drenaje, String idfamiliar) {
        this.idvivienda = idvivienda;
        this.materialpiso = materialpiso;
        this.materialpared = materialpared;
        this.acabadopared = acabadopared;
        this.materialtecho = materialtecho;
        this.tiposanitario = tiposanitario;
        this.drenaje = drenaje;
        this.idfamiliar = idfamiliar;
    }

    public String getIdvivienda() {
        return idvivienda;
    }

    public String getMaterialpiso() {
        return materialpiso;
    }

    public String getMaterialpared() {
        return materialpared;
    }

    public String getAcabadopared() {
        return acabadopared;
    }

    public String getMaterialtecho() {
        return materialtecho;
    }

    public String getTiposanitario() {
        return tiposanitario;
    }

    public String getDrenaje() {
        return drenaje;
    }

    public String getIdfamiliar() {
        return idfamiliar;
    }

    public void setIdvivienda(String idvivienda) {
        this.idvivienda = idvivienda;
    }

    public void setMaterialpiso(String materialpiso) {
        this.materialpiso = materialpiso;
    }

    public void setMaterialpared(String materialpared) {
        this.materialpared = materialpared;
    }

    public void setAcabadopared(String acabadopared) {
        this.acabadopared = acabadopared;
    }

    public void setMaterialtecho(String materialtecho) {
        this.materialtecho = materialtecho;
    }

    public void setTiposanitario(String tiposanitario) {
        this.tiposanitario = tiposanitario;
    }

    public void setDrenaje(String drenaje) {
        this.drenaje = drenaje;
    }

    public void setIdfamiliar(String idfamiliar) {
        this.idfamiliar = idfamiliar;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.idvivienda);
        dest.writeString(this.materialpiso);
        dest.writeString(this.materialpared);
        dest.writeString(this.acabadopared);
        dest.writeString(this.materialtecho);
        dest.writeString(this.tiposanitario);
        dest.writeString(this.drenaje);
        dest.writeString(this.idfamiliar);
    }

    protected Vivienda(Parcel in) {
        this.idvivienda = in.readString();
        this.materialpiso = in.readString();
        this.materialpared = in.readString();
        this.acabadopared = in.readString();
        this.materialtecho = in.readString();
        this.tiposanitario = in.readString();
        this.drenaje = in.readString();
        this.idfamiliar = in.readString();
    }

    public static final Parcelable.Creator<Vivienda> CREATOR = new Parcelable.Creator<Vivienda>() {
        @Override
        public Vivienda createFromParcel(Parcel source) {
            return new Vivienda(source);
        }

        @Override
        public Vivienda[] newArray(int size) {
            return new Vivienda[size];
        }
    };
}
