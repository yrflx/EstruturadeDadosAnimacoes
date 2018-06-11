package com.example.yurif.estruturadedados_animacoes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yurif.estruturadedados_animacoes.estruturas.ListaSeq;

public class ListaSequencial extends AppCompatActivity {

    private TextView[] textViews;
    private ListaSeq lista;

    private Button adicionar_bt;
    private Button remover_bt;
    private Button buscar_bt;

    private GridLayout grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_sequencial);

        textViews = new TextView[12];
        textViews[0] = findViewById(R.id.pos1);
        textViews[1] = findViewById(R.id.pos2);
        textViews[2] = findViewById(R.id.pos3);
        textViews[3] = findViewById(R.id.pos4);
        textViews[4] = findViewById(R.id.pos5);
        textViews[5] = findViewById(R.id.pos6);
        textViews[6] = findViewById(R.id.pos7);
        textViews[7] = findViewById(R.id.pos8);
        textViews[8] = findViewById(R.id.pos9);
        textViews[9] = findViewById(R.id.pos10);
        textViews[10] = findViewById(R.id.pos11);
        textViews[11] = findViewById(R.id.pos12);

        lista = new ListaSeq(12);

        lista.insere(1,131);
        lista.insere(1,12);
        lista.insere(1,21);

        atualizarLista();

        grid = findViewById(R.id.estrutura_grid);
        grid.setClickable(true);
        grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizarLista();
            }
        });

        adicionar_bt = findViewById(R.id.button_adicionar);
        remover_bt = findViewById(R.id.button_remover);
        buscar_bt = findViewById(R.id.button_buscar);

        adicionar_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                final Context context = ListaSequencial.this;

                if(lista.cheia()){
                    Toast.makeText(context, "Lista Cheia", Toast.LENGTH_SHORT).show();
                    return;
                }

                AlertDialog.Builder alertDialog = new AlertDialog.Builder   (context);
                alertDialog.setTitle("Adicionar Elemento");
                alertDialog.setCancelable(true);

                //config AlertDialog
                LinearLayout layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);

                final SeekBar seek = new SeekBar(context);
                seek.setMax(lista.tamanho());


                final TextView seekLabel = new TextView(context);
                seekLabel.setText("Posição:" + (seek.getProgress()+1) +"/" + (lista.tamanho()+1));
                seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        seekLabel.setText("Posição: " + (progress+1) + "/" + (lista.tamanho()+1));

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {}
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {}
                });


                final EditText valorBox = new EditText(context);
                valorBox.setHint("Valor");
                valorBox.setInputType(InputType.TYPE_CLASS_NUMBER);

                final TextView seekTitle = new TextView(context);
                seekTitle.setText("Posicao:");

                layout.addView(valorBox);
                layout.addView(seekTitle);
                layout.addView(seek);
                layout.addView(seekLabel);

                alertDialog.setView(layout);

                //botoes
                alertDialog.setPositiveButton("ADICIONAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        try{
                            int pos = seek.getProgress()+1;
                            int valor = Integer.parseInt(valorBox.getText().toString());
                            adicionarElemento(pos,valor);
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

                final Context context = ListaSequencial.this;
                if(lista.vazia()){
                    Toast.makeText(context, "Lista Vazia", Toast.LENGTH_SHORT).show();
                    return;
                }

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Remover Elemento");
                alertDialog.setCancelable(true);

                //config AlertDialog
                LinearLayout layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);

                final SeekBar seek = new SeekBar(context);
                seek.setMax(lista.tamanho()-1);

                final TextView seekLabel = new TextView(context);
                seekLabel.setText("Posição:" + (seek.getProgress()+1) +"/" + (lista.tamanho()));
                seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        seekLabel.setText("Posição:" + (progress+1) + "/" + (lista.tamanho()));

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {}
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {}
                });

                layout.addView(seek);
                layout.addView(seekLabel);

                alertDialog.setView(layout);

                //botoes
                alertDialog.setPositiveButton("REMOVER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            removerElemento(seek.getProgress()+1);
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

        buscar_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Context context = ListaSequencial.this;
                if(lista.vazia()){
                    Toast.makeText(context, "Lista Vazia", Toast.LENGTH_SHORT).show();
                    return;
                }

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Buscar Elemento");
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
                alertDialog.setPositiveButton("BUSCAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            buscarElemento(Integer.parseInt(valorBox.getText().toString()));
                        }catch (Exception e){
                            Toast.makeText(context, "Erro ao Buscar", Toast.LENGTH_SHORT).show();
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

        for(int i=1;i<=12;i++){
            int dado = lista.elemento(i);
            Log.i("LISTA", "POSICAO: " + i + "VALOR: " + dado);
            if(dado!=-1){
                textViews[i-1].setText(String.valueOf(dado));
                textViews[i-1].setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                textViews[i-1].setTextColor(Color.WHITE);
            }else{
                textViews[i-1].setBackgroundColor(0x00000000);
                textViews[i-1].setText("");
            }
        }



    }

    public void adicionarElemento(int pos, int valor){


        if(lista.insere(pos,valor)){
            Toast.makeText(this, "Dado Adicionado: " + valor + " na posicao: " + pos, Toast.LENGTH_SHORT).show();
            atualizarLista();
        }else {
            Toast.makeText(this, "Erro: Posição Invalida", Toast.LENGTH_SHORT).show();
        }

    }

    public void removerElemento(int pos){

        int remove = lista.remove(pos);

        if( remove != -1){
            Toast.makeText(this, "Dado Removido: " + remove + " na posicao: " + pos, Toast.LENGTH_SHORT).show();
            atualizarLista();
        }else{
            Toast.makeText(this, "Erro: Posição Invalida", Toast.LENGTH_SHORT).show();
        }

    }

    public void buscarElemento(int valor){

        int desloc = -1;
        boolean encontrado = false;
        while(desloc != lista.tamanho()){

            int posicao = lista.posicao(valor,desloc);
            Log.i("busca", "pos:" + posicao + " valor: " + valor + " desloc: " + desloc);
            if(posicao != -1){
                Toast.makeText(this, "Dado encontrado: " + valor + " na posicao: " + posicao, Toast.LENGTH_SHORT).show();
                textViews[posicao-1].setBackgroundColor(Color.GREEN);
                desloc = posicao;
                encontrado = true;
            }else{
                desloc = lista.tamanho();
            }

        }
        if(!encontrado){
            Toast.makeText(this, "Dado não encontrado", Toast.LENGTH_SHORT).show();
        }

    }

}