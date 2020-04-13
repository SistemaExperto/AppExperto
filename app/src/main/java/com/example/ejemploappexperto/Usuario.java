package com.example.ejemploappexperto;

import android.os.Parcel;
import android.os.Parcelable;

public class Usuario implements Parcelable {

    private int id;
    private String correo;
    private String contraseña;
    private  String estado;
    private int idtipo;
    private String nombre_tipo;
    private String nombreUsuario;
    private String apaternoUsuario;
    private String amaternoUsuario;
    private String telefonoUsuario;
    private String fechaNacUsuario;

    public Usuario() {
    }

    public Usuario(int id, String correo, String contraseña, String estado, int idtipo, String nombre_tipo, String nombreUsuario, String apaternoUsuario, String amaternoUsuario, String telefonoUsuario, String fechaNacUsuario) {
        this.id = id;
        this.correo = correo;
        this.contraseña = contraseña;
        this.estado = estado;
        this.idtipo = idtipo;
        this.nombre_tipo = nombre_tipo;
        this.nombreUsuario = nombreUsuario;
        this.apaternoUsuario = apaternoUsuario;
        this.amaternoUsuario = amaternoUsuario;
        this.telefonoUsuario = telefonoUsuario;
        this.fechaNacUsuario = fechaNacUsuario;
    }

    public int getId() {
        return id;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getEstado() {
        return estado;
    }

    public int getIdtipo() {
        return idtipo;
    }

    public String getNombre_tipo() {
        return nombre_tipo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getApaternoUsuario() {
        return apaternoUsuario;
    }

    public String getAmaternoUsuario() {
        return amaternoUsuario;
    }

    public String getTelefonoUsuario() {
        return telefonoUsuario;
    }

    public String getFechaNacUsuario() {
        return fechaNacUsuario;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setIdtipo(int idtipo) {
        this.idtipo = idtipo;
    }

    public void setNombre_tipo(String nombre_tipo) {
        this.nombre_tipo = nombre_tipo;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setApaternoUsuario(String apaternoUsuario) {
        this.apaternoUsuario = apaternoUsuario;
    }

    public void setAmaternoUsuario(String amaternoUsuario) {
        this.amaternoUsuario = amaternoUsuario;
    }

    public void setTelefonoUsuario(String telefonoUsuario) {
        this.telefonoUsuario = telefonoUsuario;
    }

    public void setFechaNacUsuario(String fechaNacUsuario) {
        this.fechaNacUsuario = fechaNacUsuario;
    }

    @Override
    public String toString() {
        return ""  + id + "     " + correo ;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.correo);
        dest.writeString(this.contraseña);
        dest.writeString(this.estado);
        dest.writeInt(this.idtipo);
        dest.writeString(this.nombre_tipo);
        dest.writeString(this.nombreUsuario);
        dest.writeString(this.apaternoUsuario);
        dest.writeString(this.amaternoUsuario);
        dest.writeString(this.telefonoUsuario);
        dest.writeString(this.fechaNacUsuario);
    }

    protected Usuario(Parcel in) {
        this.id = in.readInt();
        this.correo = in.readString();
        this.contraseña = in.readString();
        this.estado = in.readString();
        this.idtipo = in.readInt();
        this.nombre_tipo = in.readString();
        this.nombreUsuario = in.readString();
        this.apaternoUsuario = in.readString();
        this.amaternoUsuario = in.readString();
        this.telefonoUsuario = in.readString();
        this.fechaNacUsuario = in.readString();
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel source) {
            return new Usuario(source);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };
}
