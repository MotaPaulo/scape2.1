package com.scape.ufv.scape;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestBatch;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.scape.ufv.scape.BancoDeDados.DatabaseParticipante;
import com.scape.ufv.scape.Bases.Participante;
import com.scape.ufv.scape.SCAPE.NovoUsuario;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Login extends AppCompatActivity {


    private String fbAccessToken;
    private Button loginButton;
    private EditText nomeLogin;
    private EditText senhaLogin;
    private static final String NAME = "name";
    private static final String ID = "id";
    private Button cadastrarButton;
    private TextView greeting;
    private Profile profile;


    List<Participante> cadastros = new ArrayList<Participante>();
    DatabaseParticipante db;
    private LoginButton loginButtonFace;
    // private TextView skipLoginButton;
    private SkipLoginCallback skipLoginCallback;
    private CallbackManager callbackManager;

    public interface SkipLoginCallback {
        void onSkipLoginPressed();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            profile = Profile.getCurrentProfile();
            setContentView(R.layout.login);



            loginButton = (Button) findViewById(R.id.BTlogin);
            nomeLogin = (EditText) findViewById(R.id.ETnome);
            senhaLogin = (EditText) findViewById(R.id.ETsenha);
            cadastrarButton = (Button) findViewById(R.id.BTcadastrar);


            db = new DatabaseParticipante(getApplicationContext());
            cadastros = db.getAllContacts();

            callbackManager = CallbackManager.Factory.create();
            loginButtonFace = (LoginButton) findViewById(R.id.BTFacebookLogin);
            //Aqui são as permissoes
            loginButtonFace.setReadPermissions(Arrays.asList("public_profile", "user_friends", "email"));
            loginButtonFace.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    finish();
                    Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                    GraphRequest request = GraphRequest.newMeRequest(
                            AccessToken.getCurrentAccessToken(),
                            new GraphRequest.GraphJSONObjectCallback() {
                                @Override
                                public void onCompleted(
                                        JSONObject object,
                                        GraphResponse response) {
                                    String name = object.optString("name");
                                    String id = object.optString("id");
                                    String link = object.optString("link");
                                    String email = object.optString("email");
                                    Log.d("Name",name);
                                    Log.d("id",id);
                                    Log.d("link",link);
                                    Log.d("email",email);
                                }
                            });
                    Bundle parameters = new Bundle();
                    parameters.putString("fields", "id,name,link,email");
                    request.setParameters(parameters);
                    request.executeAsync();

                    GraphRequestBatch batch = new GraphRequestBatch(
                            GraphRequest.newMeRequest(
                                    AccessToken.getCurrentAccessToken(),
                                    new GraphRequest.GraphJSONObjectCallback() {
                                        @Override
                                        public void onCompleted(
                                                JSONObject jsonObject,
                                                GraphResponse response) {
                                            // Application code for user
                                        }
                                    }),
                            GraphRequest.newMyFriendsRequest(
                                    AccessToken.getCurrentAccessToken(),
                                    new GraphRequest.GraphJSONArrayCallback() {
                                        @Override
                                        public void onCompleted(
                                                JSONArray jsonArray,
                                                GraphResponse response) {
                                            JSONObject object = response.getJSONObject();
                                            JSONObject summary = object.optJSONObject("summary");
                                            Log.d("summary",summary.optString("total_count"));
                                        }
                                    })
                    );
                    batch.addCallback(new GraphRequestBatch.Callback() {
                        @Override
                        public void onBatchCompleted(GraphRequestBatch graphRequests) {
                            // Application code for when the batch finishes
                        }
                    });
                    batch.executeAsync();
                }

                @Override
                public void onCancel() {
                    Toast.makeText(Login.this, "Login canceled", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(FacebookException exception) {
                    Toast.makeText(Login.this, "Login error", Toast.LENGTH_SHORT).show();
                }
            });


            new GraphRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/{user-id}",
                    null,
                    HttpMethod.GET,
                    new GraphRequest.Callback() {
                        public void onCompleted(GraphResponse response) {
            /* handle the result */
                        }
                    }
            ).executeAsync();


//        usuario = (EditText) rootView.findViewById(R.id.usuario);
//        senha = (EditText) rootView.findViewById(R.id.usuariosenha);
//        //novo = (Button) rootView.findViewById(R.id.novo);
//        loga = (Button) rootView.findViewById(R.id.logar);


            for (Participante ver : cadastros) {
                String log = "nome: " + ver.getEmail() + " Senha: " + ver.getSenha();
                Log.d("Data: ", log);
            }


            loginButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Participante enviar = null;
                    int contador = 0;
                    for (Participante confirma : cadastros) {
                        if (confirma.getEmail().equals(nomeLogin.getText().toString()) && confirma.getSenha().equals(senhaLogin.getText().toString())) {
                            contador++;
                            enviar = confirma;
                        }
                    }
                    if (contador == 1) {
                        Toast.makeText(Login.this, "Login Aceito!!", Toast.LENGTH_LONG).show();
                        // Intent in = new Intent(getContext(),
                        //         MainActivity.class);
                        //  in.putExtra("objeto", enviar);
                        //  startActivity(in);
                    } else {
                        Toast.makeText(Login.this, "Senha ou usuário inválidos.", Toast.LENGTH_LONG).show();
                    }


                }
            });

            cadastrarButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent in = new Intent(Login.this,
                            NovoUsuario.class);
                    startActivity(in);
                }
            });
        //}
    }

        @Override
        public void onActivityResult ( int requestCode, int resultCode, Intent data){
            super.onActivityResult(requestCode, resultCode, data);
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }

    public void setSkipLoginCallback(SkipLoginCallback callback) {
        skipLoginCallback = callback;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if(id == R.id.about) {
            Toast.makeText(getApplicationContext(), "About", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
