package com.scape.ufv.scape.SCAPE;

import android.app.ActivityGroup;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.scape.ufv.scape.BancoDeDados.DatabaseHandler;
import com.scape.ufv.scape.Bases.Ativid;
import com.scape.ufv.scape.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class MinhaA extends ActivityGroup {
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
    static BaseAdapter adapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_a);

        lista = (ListView) findViewById(R.id.listaa);

        View v = lista.getChildAt(lista.getFirstVisiblePosition());



        db= new DatabaseHandler(this);

        atividades = db.getAllContacts();

        minhasatividades = new ArrayList<HashMap<String, String>>();

        new CarregaAgenda().execute();


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
            }
        });


    }

    class CarregaAgenda extends AsyncTask<String, String, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MinhaA.this);
            pDialog.setMessage("Carregando Agenda...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }


        protected String doInBackground(String... args){

            HashMap<String, String> map = new HashMap<String, String>();

            for (Ativid cn : atividades) {
                String log = "Id: "+cn.getId()+" ,Name: " + cn.getNome();
                map.put(TAG_ID, String.valueOf(cn.getId()));
                map.put(TAG_NOME,cn.getNome());
                map.put(TAG_DATA, cn.getData());
                map.put(TAG_HORAIN, cn.getHora_in());
                map.put(TAG_HORAFIM, cn.getHora_fim());


                minhasatividades.add(map);
                Log.d("Name: ", log);
            }

            return null;
        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed JSON data into ListView
                     * */
                    adapter = new SimpleAdapter(
                            MinhaA.this, minhasatividades,
                            R.layout.listatividades_layout, new String[] { TAG_ID,TAG_NOME,
                            },
                            new int[] {R.id.idat, R.id.TVatividade });



                    // updating listview

                    lista.setAdapter(adapter);



                }
            });

        }


    }
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//    public boolean onOptionsItemSelected(MenuItem item){
//        switch(item.getItemId())
//        {
//            case R.id.Atualiza: ;return true;
//            case R.id.Apagar: atividades=db.getAllContacts();
//                for(Ativid deleta:atividades){
//                    db.deleteContact(deleta);
//                }return true;
//            default: return super.onOptionsItemSelected(item);
//        }

    }



