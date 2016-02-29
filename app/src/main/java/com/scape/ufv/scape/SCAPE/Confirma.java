package com.scape.ufv.scape.SCAPE;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.scape.ufv.scape.MainActivity;
import com.scape.ufv.scape.R;

import com.scape.ufv.scape.BancoDeDados.DatabaseParticipante;
import com.scape.ufv.scape.Bases.Participante;

public class Confirma extends Activity{

    TextView nome;
    TextView email;
    TextView instituicao;
    TextView inscricao;
    TextView senha;
    TextView evento;
    Button criar;
    Button trocar;
    Intent i;

    DatabaseParticipante db;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_confirma);

        db = new DatabaseParticipante(this);


        final Participante participante = (Participante)getIntent().getSerializableExtra("objeto");

        nome = (TextView) findViewById(R.id.confnome);
        email = (TextView) findViewById(R.id.confemail);
        instituicao = (TextView) findViewById(R.id.confinst);
        inscricao = (TextView) findViewById(R.id.confinscri);
        senha = (TextView) findViewById(R.id.confsenha);
        evento = (TextView) findViewById(R.id.confevento);
        criar = (Button) findViewById(R.id.conconfir);
        trocar = (Button) findViewById(R.id.controca);


        nome.setText(participante.getNome());
        email.setText(participante.getEmail());
        instituicao.setText(participante.getInstituicao());
        inscricao.setText(participante.getInscricao());
        senha.setText(participante.getSenha());
        evento.setText(participante.getEvento());


        criar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                db.addParticipante(participante);

                Intent in = new Intent(getApplicationContext(),
                        MainActivity.class);
                in.putExtra("objeto", participante);
                startActivity(in);
            }
        });

        trocar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),
                        Evento.class);
                in.putExtra("participante", participante);
                startActivity(in);

            }
        });


    }

}
