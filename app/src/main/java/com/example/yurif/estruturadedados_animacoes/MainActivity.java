package com.example.yurif.estruturadedados_animacoes;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import junit.framework.Test;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }

    public void abrirListaSeq(View view){
        startActivity(new Intent(this, ListaSequencial.class));
    }

    public void abrirLSE(View view){
        startActivity(new Intent(this, LSEActivity.class));
    }

    public void abrirPilha(View view){
      startActivity(new Intent(this, PilhaActivity.class));
    }

    public void abrirFila(View view){
        startActivity(new Intent(this, FilasActivity.class));
    }

    public void abrirArvore(View view){
        startActivity(new Intent(this, ArvoreActivity.class));
    }


}