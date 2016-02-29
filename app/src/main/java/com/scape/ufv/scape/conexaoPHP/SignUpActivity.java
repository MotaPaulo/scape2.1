package com.scape.ufv.scape.conexaoPHP;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by souzadomingues on 29/02/16.
 */
public class SignUpActivity extends AsyncTask<String, Void, String> {
    private TextView statusField, roleField;
    private Context context;


    public SignUpActivity(Context context, TextView statusField, TextView roleField){
        this.context = context;
        this.statusField = statusField;
        this.roleField = roleField;
    }

    protected void onPreExecute(){

    }

    @Override
    protected String doInBackground(String... params){
        try{
            String usuario = (String)params[0];
            String email = (String)params[1];
            String senha = (String)params[2];

            String link = "http://scape.pe.hu/App/SignUpApp.php";
            String dados = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(usuario, "UTF-8");
            dados += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(senha, "UTF-8");
            dados += "&" + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email,"UTF-8");

            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter((conn.getOutputStream()));

            wr.write(dados);
            wr.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = null;

            while((line = reader.readLine()) != null)
            {
                sb.append(line);
                break;
            }
            return sb.toString();



        } catch(MalformedURLException e){
            e.printStackTrace();
        } catch(UnsupportedEncodingException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result){
        this.statusField.setText("Sign up sucessful");
        this.roleField.setText(result);
    }


}
