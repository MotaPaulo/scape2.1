package com.scape.ufv.scape;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;


public class fragment_tabEvento extends AppCompatActivity {

    private ListView eventosDisponiveis;



    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tab_evento);
        eventosDisponiveis = (ListView) findViewById(R.id.LVeventos);
    }

}
