package com.scape.ufv.scape;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;

public class Logout extends AppCompatActivity {

    private TextView nome;
    private Profile userProfile;
    private LoginButton logoutButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logout);
        nome = (TextView) findViewById(R.id.nomeText);
        userProfile = Profile.getCurrentProfile();
        nome.setText(userProfile.getName());

        logoutButton = (LoginButton) findViewById(R.id.BTFacebookLogin);
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

}
