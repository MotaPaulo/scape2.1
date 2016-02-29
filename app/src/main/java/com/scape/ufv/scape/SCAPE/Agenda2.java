package com.scape.ufv.scape.SCAPE;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.scape.ufv.scape.BancoDeDados.DatabaseHandler;
import com.scape.ufv.scape.Bases.Ativid;
import com.scape.ufv.scape.R;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Agenda2 extends Activity {

    TextView nome;
    TextView data;
    TextView hin;
    TextView hfim;
    TextView tema;
    TextView descri;

    Button save;

    private ProgressDialog pDialog;

    // JSON parser class
    JSONParser jsonParser = new JSONParser();

    String pid;

    JSONObject product;
    Ativid atividade;
    DatabaseHandler db;
    List<Ativid> atividades = new ArrayList<Ativid>();


    private static final String url_product_detials = "http://10.0.2.2/scape2/get_product_details.php";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCT = "atividade";
    private static final String TAG_ID = "ID";
    private static final String TAG_ID_EVENTO = "ID_EVENTO";
    private static final String TAG_NAME = "NOME";
    private static final String TAG_DATA = "Data";
    private static final String TAG_HORAIN = "Horaini";
    private static final String TAG_HORAFIM = "Horafim";
    private static final String TAG_Descricao = "Descricao";
    private static final String TAG_Tema = "Tema";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atividade);

        Intent i = getIntent();
        pid = i.getStringExtra(TAG_ID);

        save = (Button) findViewById(R.id.atisave);


        db = new DatabaseHandler(this);
        atividades = db.getAllContacts();

        Log.d("Reading: ", "Reading all contacts..");

        for(Ativid cn:atividades){

            String log = "id: "+cn.getId()+"Nome: "+cn.getNome();
            Log.d("Name: ", log);
        }




        new GetProductDetails().execute();

        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                atividade = new Ativid(nome.getText().toString(), tema.getText().toString(), data.getText().toString(),
                        hin.getText().toString(), hfim.getText().toString(), descri.getText().toString());

                Log.d("Insert: ", "Inserting ..");
                int count = 0;
                for (Ativid cn : atividades) {
                    if (cn.getNome().equals(atividade.getNome())) {
                        count++;
                    }

                }
                if (count == 0) {
                    db.addContact(atividade);
                    Toast.makeText(getApplicationContext(), "Salvo na sua agenda", Toast.LENGTH_LONG).show();
                    GeraAlarme();
                }
                db.close();
            }
        });

    }
    class GetProductDetails extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Agenda2.this);
            pDialog.setMessage("Carregando atividade selecionada. Aguarde...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Getting product details in background thread
         * */
        protected String doInBackground(String... params) {

            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    // Check for success tag
                    int success;
                    try {
                        // Building Parameters
                        List<NameValuePair> params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("pid", pid));

                        // getting product details by making HTTP request
                        // Note that product details url will use GET request
                        JSONObject json = jsonParser.makeHttpRequest(
                                url_product_detials, "GET", params);

                        // check your log for json response
                        Log.d("Single Product Details", json.toString());

                        // json success tag
                        success = json.getInt(TAG_SUCCESS);
                        if (success == 1) {
                            // successfully received product details
                            JSONArray productObj = json
                                    .getJSONArray(TAG_PRODUCT); // JSON Array

                            // get first product object from JSON Array
                            product = productObj.getJSONObject(0);

                            // product with this pid found
                            // Edit Text
                            nome = (TextView) findViewById(R.id.atinome);
                            data = (TextView) findViewById(R.id.atidat);
                            hin = (TextView) findViewById(R.id.hin);
                            hfim = (TextView) findViewById(R.id.hfim);
                            tema = (TextView) findViewById(R.id.atitema);
                            descri = (TextView) findViewById(R.id.atidescri);



                            // display product data in EditText
                            nome.setText(product.getString(TAG_NAME));
                            data.setText(product.getString(TAG_DATA));
                            hin.setText(product.getString(TAG_HORAIN));
                            hfim.setText(product.getString(TAG_HORAFIM));
                            tema.setText(product.getString(TAG_Tema));
                            descri.setText(product.getString(TAG_Descricao));


                        }else{
                            // product with pid not found
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            return null;
        }


        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once got all details
            pDialog.dismiss();
        }
    }
    private void GeraAlarme(){
        Intent intent = new Intent("ALARME_DISPARADO");
        PendingIntent p = PendingIntent.getBroadcast(this, 0, intent, 0);

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        c.add(Calendar.SECOND, 3);

        AlarmManager alarme = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarme.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), p);
        //alarme.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 5000, p);
    }


}
