package com.example.ejemploappexperto;

import android.os.Parcel;
import android.os.Parcelable;

public class Familiar implements Parcelable {

    private String idfamiliar;
    private String nombre;
    private String apaterno;
    private String amaterno;
    private String correo;
    private String fechanac;
    private String municipio;
    private String telefono;

    public Familiar() {
    }

    public Familiar(String idfamiliar, String nombre, String apaterno, String amaterno, String correo, String fechanac, String municipio, String telefono) {
        this.idfamiliar = idfamiliar;
        this.nombre = nombre;
        this.apaterno = apaterno;
        this.amaterno = amaterno;
        this.correo = correo;
        this.fechanac = fechanac;
        this.municipio = municipio;
        this.telefono = telefono;
    }

    public String getIdfamiliar() {
        return idfamiliar;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApaterno() {
        return apaterno;
    }

    public String getAmaterno() {
        return amaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public String getFechanac() {
        return fechanac;
    }

    public String getMunicipio() {
        return municipio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setIdfamiliar(String idfamiliar) {
        this.idfamiliar = idfamiliar;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApaterno(String apaterno) {
        this.apaterno = apaterno;
    }

    public void setAmaterno(String amaterno) {
        this.amaterno = amaterno;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setFechanac(String fechanac) {
        this.fechanac = fechanac;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.idfamiliar);
        dest.writeString(this.nombre);
        dest.writeString(this.apaterno);
        dest.writeString(this.amaterno);
        dest.writeString(this.correo);
        dest.writeString(this.fechanac);
        dest.writeString(this.municipio);
        dest.writeString(this.telefono);
    }

    protected Familiar(Parcel in) {
        this.idfamiliar = in.readString();
        this.nombre = in.readString();
        this.apaterno = in.readString();
        this.amaterno = in.readString();
        this.correo = in.readString();
        this.fechanac = in.readString();
        this.municipio = in.readString();
        this.telefono = in.readString();
    }

    public static final Creator<Familiar> CREATOR = new Creator<Familiar>() {
        @Override
        public Familiar createFromParcel(Parcel source) {
            return new Familiar(source);
        }

        @Override
        public Familiar[] newArray(int size) {
            return new Familiar[size];
        }
    };
}
