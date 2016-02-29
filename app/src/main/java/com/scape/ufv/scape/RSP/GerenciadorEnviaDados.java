package com.scape.ufv.scape.RSP;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.scape.ufv.scape.Bases.Dado;
import com.scape.ufv.scape.broadcast.GerenciadorGPS;

import java.lang.Object;import java.lang.String;import java.util.ArrayList;

/**
 * Created by pedro on 07/11/2015.
 */
public abstract class GerenciadorEnviaDados extends Activity{
    ArrayList<Dado> listaDados;
    GerenciadorGPS gps;

    public GerenciadorEnviaDados() {
       this.listaDados = new ArrayList();
    }

    public void add (Dado dado){
        listaDados.add(dado);
    }

    public void GerarLocalizacao(String horario, int idUsuario, Object tipoDado){

        gps = new GerenciadorGPS(getApplicationContext());

        Dado dado;

        if (gps.canGetLocation()) {

            String latitude = String.valueOf(gps.getLatitude());
            String longitude = String.valueOf(gps.getLongitude());
            Toast.makeText(getApplicationContext(), "Sua localizacao - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
            // \n is for new line
            dado = new Dado(idUsuario,horario,gps.getLongitude(),gps.getLatitude(),tipoDado);
            add(dado);
        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }


    }

    public abstract void Enviar();



}
