package com.scape.ufv.scape.Bases;

/**
 * Created by pedro on 07/11/2015.
 */
public class Dado {
    private int idUsuario;
    private String horario;
    private double longitude;
    private double latitude;
    private Object tipoDado;

    public Dado(int idUsuario, String horario, double longitude, double latitude, Object tipoDado) {
        this.idUsuario = idUsuario;
        this.horario = horario;
        this.longitude = longitude;
        this.latitude = latitude;
        this.tipoDado = tipoDado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Object getTipoDado() {
        return tipoDado;
    }

    public void setTipoDado(Object tipoDado) {
        this.tipoDado = tipoDado;
    }
}