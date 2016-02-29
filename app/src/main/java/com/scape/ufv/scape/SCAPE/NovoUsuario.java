package com.scape.ufv.scape.SCAPE;


import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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

public class NovoUsuario extends AppCompatActivity{


    // Progress Dialog
    private ProgressDialog pDialog;

    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
    JSONParser jsonParser = new JSONParser();

    ArrayList<HashMap<String, String>> productsList;
    // url to get all products list

    private static String url_all_products = "http://10.0.2.2/scape2/getInscricao.php"; //get_all_inscri

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    //private static final String TAG_PRODUCTS = "products";
    //private static final String TAG_PID = "pid";
    //private static final String TAG_NAME = "name";
    private static final String TAG_PRODUCTS = "inscricao";
    private static final String TAG_PID = "ID";
    private static final String TAG_ID = "ID";
    private static final String TAG_NAME = "NOME";
    static String pid,pos;

    JSONArray products = null;

    EditText nome;
    EditText instituicao;
    EditText email;
    EditText senha;
    ListView lista;
    Button cria;

    RadioButton radib;
    RadioGroup radig;


    DatabaseParticipante dbparti;
    Participante participante;
    List<Participante> partici = new ArrayList<Participante>();


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro);

        //Inicio comentario

//        dbparti = new DatabaseParticipante(this);
//
//        partici=dbparti.getAllContacts();
//        for(Participante deleta:partici){
//            dbparti.deleteContact(deleta);
//        }
//
//        Intent i = getIntent();
//        pid = i.getStringExtra(TAG_PID);
//
//        nome = (EditText) findViewById(R.id.nome);
//        instituicao = (EditText) findViewById(R.id.insti);
//        email = (EditText) findViewById(R.id.email);
//        senha = (EditText) findViewById(R.id.usariossenha);
//        cria = (Button) findViewById(R.id.logar);
//
//        lista = getListView();
//
//        productsList = new ArrayList<HashMap<String, String>>();

        // Loading products in Background Thread
      //  new CarregaInscricao().execute();

     //Fim comentario



//        lista.setOnItemClickListener(new OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                // getting values from selected ListItem
//
//                LinearLayout item_view = (LinearLayout) view;
//                final RadioButton itemcheck = (RadioButton) item_view
//                        .findViewById(R.id.rdBtn);
//
//                if (itemcheck.isChecked()==false) {
//                    itemcheck.setChecked(true);
//                    pos = ((TextView) view.findViewById(R.id.pid)).getText()
//                            .toString();
//
//
//                    Toast.makeText(getApplicationContext(),"Voc� escolheu "+((TextView)view.findViewById(R.id.name)).getText().toString()+"pos"+pos, Toast.LENGTH_LONG).show();
//                } else {
//                    itemcheck.setChecked(false);
//                }
//
//                // itemcheck.setChecked(true);
//
//
//                // starting new activity and expecting some response back
//
//            }
//
//        });


//        cria.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                participante = new Participante(nome.getText().toString(),pos,pid,email.getText().toString(),instituicao.getText().toString(),senha.getText().toString());
//
//
//                Intent in = new Intent(getApplicationContext(),
//                        Confirma.class);
//
//                in.putExtra("objeto", participante);
//
//                startActivity(in);
//            }
//        });


    }
    private class CarregaInscricao extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(NovoUsuario.this);
            pDialog.setMessage("Carregando tipos inscricao. Aguarde...");
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
            if(json!= null)
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
                        String name = c.getString(TAG_NAME);

                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        map.put(TAG_ID, id);
                        map.put(TAG_NAME, name);

                        // adding HashList to ArrayList
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

//        protected void onPostExecute(String file_url) {
//            // dismiss the dialog after getting all products
//            pDialog.dismiss();
//
//            // updating UI from Background Thread
//            runOnUiThread(new Runnable() {
//                public void run() {
//
//                    ListAdapter adapter = new SimpleAdapter(
//                            NovoUsuario.this, productsList,
//                            R.layout.itensinscri, new String[] { TAG_ID,
//                            TAG_NAME,"checked"},
//                            new int[] { R.id.pid, R.id.name });
//                    // updating listview
//                    setListAdapter(adapter);
//
//                }
//            });
//
//        }

    }
}
