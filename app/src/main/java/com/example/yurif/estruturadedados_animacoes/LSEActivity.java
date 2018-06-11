package com.example.yurif.estruturadedados_animacoes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yurif.estruturadedados_animacoes.estruturas.LSE;

public class LSEActivity extends AppCompatActivity {


    private TextView[] textViews;
    private ImageView[] setas;
    private LSE lse;
    private Button adicionar_bt;
    private Button remover_bt;
    private Button buscar_bt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lse);

        lse = new LSE();

        textViews = new TextView[12];
        textViews[0] = findViewById(R.id.elem0);
        textViews[1] = findViewById(R.id.elem1);
        textViews[2] = findViewById(R.id.elem2);
        textViews[3] = findViewById(R.id.elem3);
        textViews[4] = findViewById(R.id.elem4);
        textViews[5] = findViewById(R.id.elem5);
        textViews[6] = findViewById(R.id.elem6);
        textViews[7] = findViewById(R.id.elem7);
        textViews[8] = findViewById(R.id.elem8);
        textViews[9] = findViewById(R.id.elem9);
        textViews[10] = findViewById(R.id.elem10);

        setas = new ImageView[11];
        setas[0] = findViewById(R.id.seta0);
        setas[1] = findViewById(R.id.seta1);
        setas[2] = findViewById(R.id.seta2);
        setas[3] = findViewById(R.id.seta3);
        setas[4] = findViewById(R.id.seta4);
        setas[5] = findViewById(R.id.seta5);
        setas[6] = findViewById(R.id.seta6);
        setas[7] = findViewById(R.id.seta7);
        setas[8] = findViewById(R.id.seta8);
        setas[9] = findViewById(R.id.seta9);
        setas[10] = findViewById(R.id.seta10);

        adicionar_bt = findViewById(R.id.button_adicionar);
        buscar_bt = findViewById(R.id.button_buscar);
        remover_bt = findViewById(R.id.button_remover);

        lse.insere(1, 12);
        lse.insere(1, 22);
        lse.insere(1, 112);


        atualizarLista();

        adicionar_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizarLista();
                final Context context = LSEActivity.this;

                if(lse.tamanho()==11){ //simulacao de fim de memoria
                    Toast.makeText(context, "Limite de memoria atingido", Toast.LENGTH_SHORT).show();
                    return;
                }

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Adicionar Elemento");
                alertDialog.setCancelable(true);

                //config AlertDialog
                LinearLayout layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);

                final SeekBar seek = new SeekBar(context);
                seek.setMax(lse.tamanho());


                final TextView seekLabel = new TextView(context);
                seekLabel.setText("Entre:" + (seek.getProgress()+1) +"/" + (lse.tamanho()+1));
                seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        seekLabel.setText("Entre: " + (progress+1) + "/" + (lse.tamanho()+1));

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
                atualizarLista();
                final Context context = LSEActivity.this;
                if(lse.vazia()){
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
                seek.setMax(lse.tamanho()-1);

                final TextView seekLabel = new TextView(context);
                seekLabel.setText("Posição:" + (seek.getProgress()+1) +"/" + (lse.tamanho()));
                seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        seekLabel.setText("Posição: " + (progress+1) + "/" + (lse.tamanho()));

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
                atualizarLista();
                final Context context = LSEActivity.this;
                if(lse.vazia()){
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
                            Toast.makeText(context, "Erro ao Buscar:" + e.toString(), Toast.LENGTH_SHORT).show();
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

        for(int i=1;i<=11;i++){
            int dado = lse.elemento(i);
            Log.i("LISTA", "POSICAO: " + i + "VALOR: " + dado);
            if(dado!=-1){
                textViews[i-1].setText(String.valueOf(dado));
                textViews[i-1].setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                setas[i-1].setImageResource(R.drawable.ic_arrow_forward_black_24dp);
            }else{
                textViews[i-1].setBackgroundColor(0x00000000);
                textViews[i-1].setText("");
                setas[i-1].setImageResource(android.R.color.transparent);
            }
        }


    }

    public void adicionarElemento(int pos, int valor){

        if(lse.insere(pos,valor)){
            Toast.makeText(this, "Dado Adicionado: " + valor + " na posicao: " + pos, Toast.LENGTH_SHORT).show();
            atualizarLista();
        }else {
            Toast.makeText(this, "Erro: Posição Invalida", Toast.LENGTH_SHORT).show();
        }

    }

    public void removerElemento(int pos){

        int remove = lse.remove(pos);

        if( remove != -1){
            Toast.makeText(this, "Dado Removido: " + remove + " na posicao: " + pos, Toast.LENGTH_SHORT).show();
            atualizarLista();
        }else{
            Toast.makeText(this, "Erro: Posição Invalida", Toast.LENGTH_SHORT).show();
        }

    }

    public void buscarElemento(int valor){

        int[] encontrados = lse.posicoes(valor);


        for(int i=0;i<encontrados.length;i++){

            if(encontrados[i]==0){
                Toast.makeText(this, "Nenhum dado encontrado", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, "Dado encontrado: " + valor + " na posicao: " + encontrados[i], Toast.LENGTH_SHORT).show();

            textViews[encontrados[i]-1].setBackgroundColor(getResources().getColor(R.color.colorAccent));


        }


    }


}
