package com.scape.ufv.scape;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;

public class Logout extends AppCompatActivity {

    private static final String PREFS_NAME = "DadosPessoais";
    private TextView nomeTV;
    private Profile userProfile;
    private LoginButton logoutButton;
    private Button logoutButton2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logout);
        nomeTV = (TextView) findViewById(R.id.nomeText);
        userProfile = Profile.getCurrentProfile();
        logoutButton = (LoginButton) findViewById(R.id.BTFacebookLogin);
        logoutButton2 = (Button) findViewById(R.id.logoutButton2);
        if(userProfile != null) {
            logoutButton2.setVisibility(View.INVISIBLE);
            logoutButton.setVisibility(View.VISIBLE);
            nomeTV.setText(userProfile.getName());
            logoutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LoginManager.getInstance().logOut();
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
        else {
            logoutButton.setVisibility(View.INVISIBLE);
            logoutButton2.setVisibility(View.VISIBLE);
            SharedPreferences myAccount = getSharedPreferences(PREFS_NAME, 0);
            String nome = myAccount.getString("Nome", "NULL");
            String email = myAccount.getString("Email", "NULL");
            nomeTV.setText(nome + " (" + email + ")");
            logoutButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    SharedPreferences myAccount = getSharedPreferences(PREFS_NAME, 0);
                    SharedPreferences.Editor editor = myAccount.edit();
                    editor.clear();
                    editor.commit();
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
                    finish();
                }
            });

        }

    }

}
