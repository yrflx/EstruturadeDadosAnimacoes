package com.example.yurif.estruturadedados_animacoes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.print.PrintAttributes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yurif.estruturadedados_animacoes.estruturas.ListaSeq;
import com.example.yurif.estruturadedados_animacoes.estruturas.PilhaSeq;

public class PilhaActivity extends AppCompatActivity {

    private TextView[] textViews;
    private PilhaSeq pilha;

    private Button adicionar_bt;
    private Button remover_bt;

    private GridLayout grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilha);

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

        grid = findViewById(R.id.estrutura_grid);
        grid.setClickable(true);
        grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizarLista();
            }
        });

        pilha = new PilhaSeq(10);

        pilha.push(123);
        pilha.push(324);
        pilha.push(76);

        atualizarLista();

        adicionar_bt = findViewById(R.id.button_adicionar);
        remover_bt = findViewById(R.id.button_remover);


        adicionar_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                final Context context = PilhaActivity.this;

                if(pilha.cheia()){
                    Toast.makeText(context, "Lista Cheia", Toast.LENGTH_SHORT).show();
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

                final Context context = PilhaActivity.this;
                if(pilha.vazia()){
                    Toast.makeText(context, "Lista Vazia", Toast.LENGTH_SHORT).show();
                    return;
                }

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Remover");
                alertDialog.setCancelable(true);

                TextView tv = new TextView(context);
                tv.setText("Deseja remover o elemento:" + pilha.top() + " do topo da pilha?");


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
            int dado = pilha.elemento(i);
            Log.i("LISTA", "POSICAO: " + i + "VALOR: " + dado);
            if(dado!=-1){
                textViews[i-1].setText(String.valueOf(dado));
                textViews[i-1].setBackgroundColor(Color.YELLOW);
            }else{
                textViews[i-1].setBackgroundColor(Color.GRAY);
                textViews[i-1].setText("");
            }
        }



    }

    public void adicionarElemento(int valor){

        pilha.push(valor);
        Toast.makeText(this, "Dado Adicionado: " + valor + " no topo da pilha", Toast.LENGTH_SHORT).show();
        atualizarLista();

    }

    public void removerElemento(){

        int remove = pilha.pop();

        if( remove != -1){
            Toast.makeText(this, "Dado Removido: " + remove + " do topo da pilha", Toast.LENGTH_SHORT).show();
            atualizarLista();
        }else{
            Toast.makeText(this, "Erro", Toast.LENGTH_SHORT).show();
        }

    }

    public void buscar(View v){

        Toast.makeText(this, "O ELEMENTO DO TOPO É " + pilha.top(), Toast.LENGTH_SHORT).show();





    }





}
