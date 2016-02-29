package com.scape.ufv.scape;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.scape.ufv.scape.Bases.Adapter;

/**
 * Created by souzadomingues on 01/10/2015.
 */
public class fragment_tab2 extends AppCompatActivity {


    private Adapter listViewAdapter;
    private ListView atividadesDisponiveis;

    public fragment_tab2(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tab2);
        atividadesDisponiveis = (ListView) findViewById(R.id.LVatividadesDisponiveis);

    }

}
