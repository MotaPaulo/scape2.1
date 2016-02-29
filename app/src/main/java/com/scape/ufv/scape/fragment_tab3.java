package com.scape.ufv.scape;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

/**
 * Created by souzadomingues on 01/10/2015.
 */
public class fragment_tab3 extends AppCompatActivity {

    private ListView agenda;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tab3);
        agenda = (ListView) findViewById(R.id.LVagenda);
    }

}
