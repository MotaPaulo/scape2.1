package com.scape.ufv.scape.conexaoPHP;

import android.os.AsyncTask;
import android.util.Log;

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
public class LogInActivity extends AsyncTask<String, Void , String> {


    public interface AsyncResponse{
        void processFinish(String output);
    }

    public AsyncResponse delegate = null;


    public LogInActivity(AsyncResponse delegate){

        this.delegate = delegate;
    }

    protected void onPreExecute(){

    }


    @Override
    protected String doInBackground(String... params) {
        try {
            String email = (String)params[0];
            String senha = (String)params[1];

            String link = "http://scape.pe.hu/App/loginApp.php";
            String dados = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
            dados += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(senha, "UTF-8");
            Log.v("Dados: ", dados);

            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

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
            Log.v("SB:", sb.toString());
            return sb.toString();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result){
        if(result.equals("1"))
        {
            delegate.processFinish("1");
        }
        else{
            delegate.processFinish(result);
        }
    }
}
