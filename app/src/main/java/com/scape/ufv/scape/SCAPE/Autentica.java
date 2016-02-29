package com.scape.ufv.scape.SCAPE;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.scape.ufv.scape.R;

import com.scape.ufv.scape.Bases.Participante;

public class Autentica extends Activity{

    TextView nome;
    TextView email;
    TextView instituicao;
    TextView inscricao;
    TextView senha;
    TextView evento;

    Button trocar;
    Intent i;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autentica);


        final Participante participante = (Participante)getIntent().getSerializableExtra("objeto");

        nome = (TextView) findViewById(R.id.auntnome);
        email = (TextView) findViewById(R.id.auntemail);
        instituicao = (TextView) findViewById(R.id.auntinst);
        inscricao = (TextView) findViewById(R.id.auntinscri);
        senha = (TextView) findViewById(R.id.auntfsenha);
        evento = (TextView) findViewById(R.id.auntevento);

        trocar = (Button) findViewById(R.id.aunttroca);


        nome.setText(participante.getNome());
        email.setText(participante.getEmail());
        instituicao.setText(participante.getInstituicao());
        inscricao.setText(participante.getInscricao());
        senha.setText(participante.getSenha());
        evento.setText(participante.getEvento());


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

