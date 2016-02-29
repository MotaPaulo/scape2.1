package com.scape.ufv.scape.SCAPE;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.scape.ufv.scape.BancoDeDados.DatabaseHandler;
import com.scape.ufv.scape.Bases.Ativid;
import com.scape.ufv.scape.Bases.MyBaseAdapter;
import com.scape.ufv.scape.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MinhaATEste extends Activity {
    DatabaseHandler db;
    List<Ativid> atividades;

    ListView lista;
    private ProgressDialog pDialog;
    ArrayList<HashMap<String, String>> minhasatividades;

    private static final String TAG_ID = "ID";
    private static final String TAG_NOME = "NOME";
    private static final String TAG_DATA = "Data";
    private static final String TAG_HORAIN = "Horaini";
    private static final String TAG_HORAFIM = "Horafim";
    MyBaseAdapter adapter;
    ArrayList<Ativid> teste;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_a);

        lista = (ListView) findViewById(R.id.listaa);

        //View v = lista.getChildAt(lista.getFirstVisiblePosition());

        minhasatividades = new ArrayList<HashMap<String, String>>();

        db= new DatabaseHandler(this);

        atividades = db.getAllContacts();

        db.close();

        teste = new ArrayList<Ativid>();

        for(int i=0;i<atividades.size();i++){
            teste.add(atividades.get(i));
            String log = "nome "+teste.get(i).getNome();
            Log.d("verifica: ", log);
        }
        adapter = new MyBaseAdapter(this, teste);
        adapter.notifyDataSetChanged();
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // getting values from selected ListItem
                String pos = ((TextView) view.findViewById(R.id.idat)).getText()
                        .toString();

                // Starting new intent
                Intent in = new Intent(getApplicationContext(),
                        MinhaAgenda.class);
                // sending pid to next activity
                in.putExtra(TAG_ID, pos);

                // starting new activity and expecting some response back
                startActivityForResult(in, 100);
                //db.close();

            }
        });


    }

//
//    public boolean onOptionsItemSelected(MenuItem item){
//        switch(item.getItemId())
//        {
//            case R.id.Atualiza: atividades = db.getAllContacts();
//                teste.clear();
//                for(int i=0;i<atividades.size();i++){
//                    teste.add(atividades.get(i));
//                    String log = "nome "+teste.get(i).getNome();
//                    Log.d("verifica: ", log);
//                }
//                adapter.notifyDataSetChanged();return true;
//            case R.id.Apagar: atividades=db.getAllContacts();
//                for(Ativid deleta:atividades){
//                    db.deleteContact(deleta);
//                }
//                teste.clear();
//                adapter.notifyDataSetChanged();
//
//                return true;
//            default: return super.onOptionsItemSelected(item);
//        }
//
//    }

}
