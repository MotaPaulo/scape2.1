package com.scape.ufv.scape;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.Profile;

import java.util.ArrayList;
import java.util.Locale;


//Set and working
public class MainActivity extends AppCompatActivity {


    private static final String PREFS_NAME = "DadosPessoais";
    private gridView_adapter mAdapter;
    private ArrayList<String> listTitles;
    private ArrayList<Integer> listIcons;
    private TextView dateText;
    private GridView gridView;
    private ArrayList<String> months;
    private Intent specialIntent;
    private Profile userProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getApplicationContext(), "On create", Toast.LENGTH_SHORT).show();

        //inicia o sdk
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.main_screen);
        setListContents();
        Log.v("App signature:", FacebookSdk.getApplicationSignature(getApplicationContext()));

        specialIntent = new Intent(getApplicationContext(), Login.class);


        dateText = (TextView) findViewById(R.id.dateText);
        Time time = new Time();
        time.setToNow();
        dateText.setText(time.monthDay + " de " + months.get(time.month) + ", " + time.year);
        if(Locale.getDefault().getDisplayLanguage().equals("English")){
            dateText.setText(months.get(time.month) + " " + time.monthDay + ", " + time.year);
        }



        mAdapter = new gridView_adapter(this,listTitles, listIcons);

        gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(mAdapter);




        gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
               // Toast.makeText(MainActivity.this, "" + position,
                       // Toast.LENGTH_SHORT).show();
                Intent intent;
                switch(position){
                    case 0:
                        //intent = new Intent(getApplicationContext(), Login.class);
                        startActivity(specialIntent);
                        break;
                    case 1:
                        intent = new Intent(getApplicationContext(), fragment_tabEvento.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(getApplicationContext(),fragment_tab2.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(getApplicationContext(), fragment_tab3.class);
                        startActivity(intent);
                        break;
                    case 6:
                        intent = new Intent(getApplicationContext(), Opinar.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        SharedPreferences myAccount = getSharedPreferences(PREFS_NAME, 0);
        String nome = myAccount.getString("Nome", "NULL");
        Log.v("Nome: ", nome);
        userProfile = Profile.getCurrentProfile();
        if(nome.equals("NULL") == false)
            specialIntent = new Intent(getApplicationContext(), Logout.class);
        else if(userProfile != null)
            specialIntent = new Intent(getApplicationContext(), Logout.class);
        else
            specialIntent = new Intent(getApplicationContext(), Login.class);
        Toast.makeText(getApplicationContext(), "Ended on Resume", Toast.LENGTH_SHORT).show();
    }


    private void setListContents(){
        listIcons = new ArrayList<>();
        listIcons.add(0,R.drawable.sample_6);
        listIcons.add(1,R.drawable.sample_2);
        listIcons.add(2,R.drawable.sample_4);
        listIcons.add(3,R.drawable.calendar146);
        listIcons.add(4,R.drawable.sample_1);
        listIcons.add(5,R.drawable.questionario);
        listIcons.add(6,R.drawable.opiniao2);
        listIcons.add(7,R.drawable.patrocinadores);

        listTitles = new ArrayList<>();
        listTitles.add(0, getString(R.string.perfil));
        listTitles.add(1, getString(R.string.eventos));
        listTitles.add(2, getString(R.string.atividades));
        listTitles.add(3, getString(R.string.agenda));
        listTitles.add(4, getString(R.string.social));
        listTitles.add(5, getString(R.string.questionarios));
        listTitles.add(6, getString(R.string.opiniao));
        listTitles.add(7, getString(R.string.patrocinadores));

        months = new ArrayList<>();
        months.add(0, getString(R.string.janeiro));
        months.add(1, getString(R.string.fevereiro));
        months.add(2, getString(R.string.marco));
        months.add(3, getString(R.string.abril));
        months.add(4, getString(R.string.maio));
        months.add(5, getString(R.string.junho));
        months.add(6, getString(R.string.julho));
        months.add(7, getString(R.string.agosto));
        months.add(8, getString(R.string.setembro));
        months.add(9, getString(R.string.outubro));
        months.add(10, getString(R.string.novembro));
        months.add(11, getString(R.string.dezembro));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(), "Settings", Toast.LENGTH_LONG).show();
            return true;
        }
        else if(id == R.id.about) {
            Toast.makeText(getApplicationContext(), "About", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



//
//    public class ImageAdapter extends BaseAdapter {
//        private Context mContext;
//
//        public ImageAdapter(Context c) {
//            mContext = c;
//        }
//
//        public int getCount() {
//            return mThumbIds.length;
//        }
//
//        public Object getItem(int position) {
//            return null;
//        }
//
//        public long getItemId(int position) {
//            return 0;
//        }
//
//        // create a new ImageView for each item referenced by the Adapter
//        public View getView(int position, View convertView, ViewGroup parent) {
//            ImageView imageView;
//            if (convertView == null) {
//                // if it's not recycled, initialize some attributes
//                imageView = new ImageView(mContext);
//                imageView.setLayoutParams(new GridView.LayoutParams(185, 185));
//                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                imageView.setPadding(8, 8, 8, 8);
//            } else {
//                imageView = (ImageView) convertView;
//            }
//
//            imageView.setImageResource(mThumbIds[position]);
//            return imageView;
//        }
//
//        // references to our images
//        private Integer[] mThumbIds = {
//                R.drawable.sample_6, R.drawable.sample_2,
//                R.drawable.sample_4, R.drawable.calendar146,
//                R.drawable.sample_1, R.drawable.sample_5
//        };
//
//        private String[] listTitles = {
//            "Entrar", "Eventos", "Atividades", "Agenda", "Social", "Ajustes"
//        };
//    }
//



}

