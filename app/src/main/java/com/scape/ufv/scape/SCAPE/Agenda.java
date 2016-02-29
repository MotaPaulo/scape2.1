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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.scape.ufv.scape.BancoDeDados.DatabaseParticipante;
import com.scape.ufv.scape.Bases.Participante;
import com.scape.ufv.scape.R;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Agenda extends ActivityGroup {

    // Progress Dialog
    private ProgressDialog pDialog;

    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();

    ArrayList<HashMap<String, String>> productsList;

    // url to get all products list
    private static String url_all_products = "http://10.0.2.2/scape2/get_all_ativid2.php";

    String pid;
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCTS = "atividade";
    private static final String TAG_ID = "ID";
    private static final String TAG_PID = "ID";
    private static final String TAG_ID_EVENTO = "ID_EVENTO";
    private static final String TAG_NAME = "NOME";
    private static final String TAG_DATA = "Data";
    private static final String TAG_HORAIN = "Horaini";
    private static final String TAG_HORAFIM = "Horafim";
    ListView lv;
    // products JSONArray
    JSONArray products = null;

    DatabaseParticipante db;
    Participante participante;
    List<Participante> cadastros = new ArrayList<Participante>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tab3);



        // Hashmap for ListView
        productsList = new ArrayList<HashMap<String, String>>();


        // Loading products in Background Thread


        final Participante participante = (Participante)getIntent().getSerializableExtra("objeto");

        pid=participante.getEvento();


        // Get listview
        lv = (ListView)findViewById(R.id.LVagenda);

        new LoadAllProducts().execute();

        // on seleting single product
        // launching Edit Product Screen
        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // getting values from selected ListItem
                String pos = ((TextView) view.findViewById(R.id.TVatividadeagenda)).getText()
                        .toString();

                // Starting new intent
                Intent in = new Intent(getApplicationContext(),
                        Agenda2.class);
                // sending pid to next activity
                in.putExtra(TAG_ID, pos);

                // starting new activity and expecting some response back
                startActivityForResult(in, 100);
            }
        });

    }

    // Response from Edit Product Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // if result code 100
        if (resultCode == 100) {
            // if result code 100 is received
            // means user edited/deleted product
            // reload this screen again
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }

    }

    /**
     * Background Async Task to Load all product by making HTTP Request
     * */
    class LoadAllProducts extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Agenda.this);
            pDialog.setMessage("Carregando palestras, Aguarde...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting All products from url
         * */
        protected String doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(url_all_products, "GET", params);

            // Check your log cat for JSON reponse
            Log.d("All Products: ", json.toString());

            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // products found
                    // Getting Array of Products
                    products = json.getJSONArray(TAG_PRODUCTS);

                    // looping through All Products
                    for (int i = 0; i < products.length(); i++) {
                        JSONObject c = products.getJSONObject(i);

                        // Storing each json item in variable
                        String id = c.getString(TAG_ID);
                        String ie = c.getString(TAG_ID_EVENTO);
                        String name = c.getString(TAG_NAME);
                        String data = c.getString(TAG_DATA);
                        String in = c.getString(TAG_HORAIN);
                        String fim = c.getString(TAG_HORAFIM);

                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        map.put(TAG_ID, id);
                        map.put(TAG_ID_EVENTO, ie);
                        map.put(TAG_NAME, name);
                        map.put(TAG_DATA, data);
                        map.put(TAG_HORAIN, in);
                        map.put(TAG_HORAFIM, fim);

                        // adding HashList to ArrayList
                        if(ie.equals(pid))
                            productsList.add(map);
                    }
                }/* else {
						// no products found
						// Launch Add New product Activity
						Intent i = new Intent(getApplicationContext(),
								NewProductActivity.class);
						// Closing all previous activities
						i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(i);
					}*/
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed JSON data into ListView
                     * */
                    ListAdapter adapter = new SimpleAdapter(
                            Agenda.this, productsList,
                            R.layout.listagenda_layout, new String[] { TAG_ID,TAG_ID_EVENTO,TAG_NAME,
                            TAG_HORAIN},
                            new int[] {R.id.TVidAtiv, R.id.TVidEvento,R.id.TVatividadeagenda,R.id.TVhora,});
                    // updating listview
                    lv.setAdapter(adapter);
                }
            });

        }

    }

}
