package com.example.yurif.estruturadedados_animacoes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yurif.estruturadedados_animacoes.estruturas.FilaSeq;

public class FilasActivity extends AppCompatActivity {

    private TextView[] textViews;
    private FilaSeq fila;

    private Button adicionar_bt;
    private Button remover_bt;
    private TextView tamanhoFila;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filas);

        textViews = new TextView[10];
        textViews[0] = findViewById(R.id.pos0);
        textViews[1] = findViewById(R.id.pos1);
        textViews[2] = findViewById(R.id.pos2);
        textViews[3] = findViewById(R.id.pos3);
        textViews[4] = findViewById(R.id.pos4);
        textViews[5] = findViewById(R.id.pos5);
        textViews[6] = findViewById(R.id.pos6);
        textViews[7] = findViewById(R.id.pos7);
        textViews[8] = findViewById(R.id.pos8);
        textViews[9] = findViewById(R.id.pos9);

        tamanhoFila = findViewById(R.id.fila_tam_tv);

        fila = new FilaSeq(10);
        fila.insere(21);
        fila.insere(567);
        fila.insere(222);

        atualizarLista();


        adicionar_bt = findViewById(R.id.button_adicionar);
        remover_bt = findViewById(R.id.button_remover);

        adicionar_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                final Context context = FilasActivity.this;

                if(fila.cheia()){
                    Toast.makeText(context, "Fila Cheia", Toast.LENGTH_SHORT).show();
                    return;
                }

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Adicionar Elemento");
                alertDialog.setCancelable(true);

                //config AlertDialog
                LinearLayout layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);


                final EditText valorBox = new EditText(context);
                valorBox.setHint("Valor");
                valorBox.setInputType(InputType.TYPE_CLASS_NUMBER);

                layout.addView(valorBox);
                alertDialog.setView(layout);

                //botoes
                alertDialog.setPositiveButton("ADICIONAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        try{
                            int valor = Integer.parseInt(valorBox.getText().toString());
                            adicionarElemento(valor);
                        }catch (Exception e){
                            Toast.makeText(context, "Erro ao adicionar", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                alertDialog.setNegativeButton("CANCELAR", null);

                //criar e exibir
                alertDialog.create().show();


            }
        });

        remover_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Context context = FilasActivity.this;
                if(fila.vazia()){
                    Toast.makeText(context, "Fila Vazia", Toast.LENGTH_SHORT).show();
                    return;
                }

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Remover");
                alertDialog.setCancelable(true);

                TextView tv = new TextView(context);
                tv.setText("Deseja remover o elemento:" + fila.primeiro() + " do inicio da fila?");

                //config AlertDialog
                LinearLayout layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);

                layout.addView(tv);

                alertDialog.setView(layout);

                //botoes
                alertDialog.setPositiveButton("REMOVER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            removerElemento();
                        }catch (Exception e){
                            Toast.makeText(context, "Erro ao Remover", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                alertDialog.setNegativeButton("CANCELAR", null);

                //criar e exibir
                alertDialog.create().show();


            }
        });


    }

    public void atualizarLista(){

        for(int i=1;i<=10;i++){

            if(i==fila.tamanho()){

                textViews[i-1].setText(String.valueOf(fila.primeiro()));
                textViews[i-1].setTextColor(Color.WHITE);
                textViews[i-1].setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            }

            else if(i<=fila.tamanho()){
                textViews[i-1].setBackgroundColor(getResources().getColor(R.color.colorPrimary));

            }else{
                textViews[i-1].setBackgroundColor(0x00000000);
                textViews[i-1].setText("");
            }

        }

        tamanhoFila.setText(String.valueOf(fila.tamanho()));

        if(fila.vazia()){
            textViews[0].setText("Fila Vazia");
            textViews[0].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            textViews[0].setTextSize(18);
        }


    }

    public void adicionarElemento(int valor){

        fila.insere(valor);
        Toast.makeText(this, "Dado Adicionado: " + valor + " no fim da fila", Toast.LENGTH_SHORT).show();
        atualizarLista();

    }

    public void removerElemento(){

        int remove = fila.remove();

        if( remove != -1){
            Toast.makeText(this, "Dado Removido: " + remove + " do inicio da fila", Toast.LENGTH_SHORT).show();
            atualizarLista();
        }else{
            Toast.makeText(this, "Erro", Toast.LENGTH_SHORT).show();
        }

    }



}

