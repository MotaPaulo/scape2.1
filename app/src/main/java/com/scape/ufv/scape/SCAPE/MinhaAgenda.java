package com.scape.ufv.scape.SCAPE;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.scape.ufv.scape.BancoDeDados.DatabaseHandler;
import com.scape.ufv.scape.Bases.Ativid;
import com.scape.ufv.scape.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


public class MinhaAgenda extends Activity {

    DatabaseHandler db;
    List<Ativid> atividades;

    String pid;
    private static final String TAG_ID = "ID";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atividade2);


        TextView nome = (TextView) findViewById(R.id.atinome);
        TextView data = (TextView) findViewById(R.id.atidat);
        TextView hin = (TextView) findViewById(R.id.hin);
        TextView hfim = (TextView) findViewById(R.id.hfim);
        TextView tema = (TextView) findViewById(R.id.atitema);
        TextView descri = (TextView) findViewById(R.id.atidescri);

        atividades = new ArrayList<Ativid>();

        Intent i = getIntent();
        pid = i.getStringExtra(TAG_ID);

        db = new DatabaseHandler(this);
        atividades=db.getAllContacts();

        Log.d("Reading: ", "Reading all contacts..");

        for(Ativid cn:atividades){

            String log = "id: "+cn.getId()+" Nome: "+cn.getNome()+"id selecionado: "+pid;
            Log.d("Name: ", log);
        }



        int x=0;
        while(String.valueOf(atividades.get(x).getId()).equals(pid)){
            x++;
        }


        Ativid cn = atividades.get(x);

        nome.setText(cn.getNome());
        data.setText(cn.getData());
        hin.setText(cn.getHora_in());
        hfim.setText(cn.getHora_fim());
        tema.setText(cn.getTema());
        descri.setText(cn.getDescricao());




    }



    private String DevolveHora() {
        String Tempo = "";

        GregorianCalendar calendar2 = new GregorianCalendar();
        int fhora = calendar2.get(Calendar.HOUR_OF_DAY);
        int fminuto = calendar2.get(Calendar.MINUTE);
        int fsegundo = calendar2.get(Calendar.SECOND);

        String ct = "" + fhora;
        if (ct.length() == 1) {
            ct = "0" + ct;
        }
        Tempo = Tempo + ct + ":";


        ct = "" + fminuto;
        if (ct.length() == 1) {
            ct = "0" + ct;
        }
        Tempo = Tempo + ct + ":";


        ct = "" + fsegundo;
        if (ct.length() == 1) {
            ct = "0" + ct;
        }
        Tempo = Tempo + ct;


        return Tempo;
    }
    private String DevolveData() {
        String Tempo = "";

        GregorianCalendar calendar2 = new GregorianCalendar();
        int fdia = calendar2.get(Calendar.DAY_OF_MONTH);
        int fmes = calendar2.get(Calendar.MONTH)+1;
        int fano = calendar2.get(Calendar.YEAR);

        String ct = "" + fdia;
        if (ct.length() == 1) {
            ct = "0" + ct;
        }
        Tempo = Tempo + ct + "/";


        ct = "" + fmes;
        if (ct.length() == 1) {
            ct = "0" + ct;
        }
        Tempo = Tempo + ct + "/";


        ct = "" + fano;
        if (ct.length() == 1) {
            ct = "0" + ct;
        }
        Tempo = Tempo + ct;


        return Tempo;
    }
}
