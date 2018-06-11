package com.example.yurif.estruturadedados_animacoes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yurif.estruturadedados_animacoes.estruturas.ABP.ABP;
import com.example.yurif.estruturadedados_animacoes.estruturas.ABP.No;

import java.util.ArrayList;

public class ArvoreActivity extends AppCompatActivity {

    private TextView raiz;

    private TextView no1;
    private TextView no2;
    private TextView f1;
    private TextView f2;
    private TextView f3;
    private TextView f4;

    private ImageView seta0;
    private ImageView seta1;
    private ImageView seta2;
    private ImageView seta3;
    private ImageView seta4;
    private ImageView seta5;

    private TextView textLista;

    private ArrayList<Integer> lista;

    private ArrayList<TextView> elementos;


    private ABP arv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arvore);

        /*recebendo objs de layout*/

        elementos = new ArrayList<>();
        raiz = findViewById(R.id.raiz);
        elementos.add(raiz);

        //nos
        no1 = findViewById(R.id.n1);
        no2 = findViewById(R.id.n2);

        elementos.add(no1);
        elementos.add(no2);


        //folhas
        f1 = findViewById(R.id.f1);
        f2 = findViewById(R.id.f2);
        f3 = findViewById(R.id.f3);
        f4 = findViewById(R.id.f4);

        elementos.add(f1);
        elementos.add(f2);
        elementos.add(f3);
        elementos.add(f4);


        //setas
        seta0 = findViewById(R.id.seta0);
        seta1 = findViewById(R.id.seta1);
        seta2 = findViewById(R.id.seta2);
        seta3 = findViewById(R.id.seta3);
        seta4 = findViewById(R.id.seta4);
        seta5 = findViewById(R.id.seta5);

        textLista = findViewById(R.id.textLista);


        raiz.setVisibility(View.INVISIBLE);
        no1.setVisibility(View.INVISIBLE);
        no2.setVisibility(View.INVISIBLE);

        f1.setVisibility(View.INVISIBLE);
        f2.setVisibility(View.INVISIBLE);
        f3.setVisibility(View.INVISIBLE);
        f4.setVisibility(View.INVISIBLE);

        seta0.setVisibility(View.INVISIBLE);
        seta1.setVisibility(View.INVISIBLE);
        seta2.setVisibility(View.INVISIBLE);
        seta3.setVisibility(View.INVISIBLE);
        seta4.setVisibility(View.INVISIBLE);
        seta5.setVisibility(View.INVISIBLE);



        //arvore

       arv = new ABP();


        atualizarLista();


    }


    @Override
    protected void onStart() {
        super.onStart();


    }

    public void atualizarLista(){

        for(TextView textView : elementos){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                textView.setBackgroundTintList(getResources().getColorStateList(R.color.colorPrimaryDark));
            }
        }

        try{
            No rz = arv.getRaiz();
            if(rz==null){
                return;
            }

            raiz.setVisibility(View.VISIBLE);
            raiz.setText(String.valueOf(rz.getConteudo()));

            //NO 1
            No n1 = rz.getEsq();
            if(n1!=null){
                no1.setVisibility(View.VISIBLE);
                no1.setText(String.valueOf(n1.getConteudo()));
                seta0.setVisibility(View.VISIBLE);


                //folha esquerda
                No folha1 = n1.getEsq();
                if(folha1!=null){
                    f1.setVisibility(View.VISIBLE);
                    f1.setText(String.valueOf(folha1.getConteudo()));
                    seta2.setVisibility(View.VISIBLE);
                }

                //folha direita
                No folha2 = n1.getDir();
                if(folha2!=null){
                    f2.setVisibility(View.VISIBLE);
                    f2.setText(String.valueOf(folha2.getConteudo()));
                    seta3.setVisibility(View.VISIBLE);
                }


            }

            //NO2
            No n2 = rz.getDir();

            if(n2!=null){
                no2.setVisibility(View.VISIBLE);
                no2.setText(String.valueOf(n2.getConteudo()));
                seta1.setVisibility(View.VISIBLE);

                //folha esquerda
                No folha1 = n2.getEsq();
                if(folha1!=null){
                    f3.setVisibility(View.VISIBLE);
                    f3.setText(String.valueOf(folha1.getConteudo()));
                    seta4.setVisibility(View.VISIBLE);
                }

                //folha direita
                No folha2 = n2.getDir();
                if(folha2!=null){
                    f4.setVisibility(View.VISIBLE);
                    f4.setText(String.valueOf(folha2.getConteudo()));
                    seta5.setVisibility(View.VISIBLE);
                }


            }

            arv.exibe();
            ArrayList<Integer> lista = arv.getInOrderList();


            String escrever = "IN-ORDER:";
            for(int i=0;i<lista.size();i++){
                escrever+=" " + lista.get(i);
            }
            textLista.setText(escrever);


        }catch (Exception e) {}

    }

    public void insere(final View v){

        final Context context = ArvoreActivity.this;

        if(arv.getInOrderList().size() == 7){
            Toast.makeText(context, "Não é possivel adicionar mais elemetentos", Toast.LENGTH_SHORT).show();
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

                    Boolean result = arv.insere(valor);
                    if(result){
                        Toast.makeText(context, "Valor adicionado com sucesso", Toast.LENGTH_SHORT).show();
                        atualizarLista();
                    }else{
                        Toast.makeText(context, "Erro: Altura máxima atingida", Toast.LENGTH_SHORT).show();
                    }


                }catch (Exception e){
                    Toast.makeText(context, "Erro ao adicionar", Toast.LENGTH_SHORT).show();
                }

            }
        });

        alertDialog.setNegativeButton("CANCELAR", null);

        //criar e exibir
        alertDialog.create().show();
    }

    public void buscar(View v){
        atualizarLista();
        final Context context = ArvoreActivity.this;
        if(arv.vazia()){
            Toast.makeText(context, "Arvore Vazia", Toast.LENGTH_SHORT).show();
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
                    Boolean busca = false;
                    for(TextView textView : elementos){
                        if(textView.getText().equals(valorBox.getText().toString())){
                            busca = true;
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                textView.setBackgroundTintList(getResources().getColorStateList(R.color.colorAccent));
                            }
                        }
                    }

                    if(!busca){
                        Toast.makeText(context, "No não encontrado", Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){
                    Toast.makeText(context, "Erro ao Buscar:" + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        alertDialog.setNegativeButton("CANCELAR", null);

        //criar e exibir
        alertDialog.create().show();

    }


}


