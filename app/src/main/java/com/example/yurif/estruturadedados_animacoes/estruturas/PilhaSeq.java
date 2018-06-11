package com.example.yurif.estruturadedados_animacoes.estruturas;

/**
 * Created by yurif on 14/04/2018.
 */

public class PilhaSeq {
    private int dados[]; // Vetor que contém os dados da lista
    private int topo;
    private int tamMax;

    public PilhaSeq(){
        tamMax = 100;
        dados = new int[tamMax];
        topo = -1;
    }

    public PilhaSeq(int n){
        tamMax = n;
        dados = new int[tamMax];
        topo = -1;
    }

    /** Verifica se a Pilha está vazia */
    public boolean vazia(){
        if (topo == -1)
            return true;
        else
            return false;
    }

    /**Verifica se a Pilha está cheia */
    public boolean cheia(){
        if (topo == (tamMax-1))
            return true;
        else
            return false;
    }

    /**Obtém o tamanho da Pilha*/
    public int tamanho(){
        return topo+1;
    }

    /** Consulta o elemento do topo da Pilha.
     Retorna -1 se a pilha estiver vazia,
     caso contrário retorna o valor que está no topo da pilha. */
    public int top () {
        if (vazia())
            return -1; // pilha vazia

        return dados[topo];
    }

    /** Insere um elemento no topo da pilha.
     Retorna false se a pilha estiver cheia.
     Caso contrário retorna true */
    public boolean push (int valor) {
        if (cheia())
            return false;  // err: pilha cheia

        topo++;
        dados[topo] = valor;
        return true;
    }

    /** Retira o elemento do topo da pilha.
     Retorna -1 se a pilha estiver vazia. */
    public int pop() {
        if (vazia())
            return -1; // Pilha vazia

        int valor = dados[topo];
        topo--;
        return valor;
    }

   /** Obtém o i-ésimo elemento de uma lista.
            Retorna -1 se a posição for inválida. */
    public int elemento(int pos){

    	/* Se posição estiver fora dos limites <= 0
         * ou > tamanho da lista */
        if ((pos > topo+1) || (pos <= 0))
            return -1;

        return dados[pos-1];
    }

    /**	Retorna a posição de um elemento pesquisado.
     Retorna -1 caso não seja encontrado */
    public int posicao (int valor, int desloc){
		/* Procura elemento a elemento, se o dado está na
		lista. Se estiver, retorna a sua posição no array+1 */
        for (int i = desloc+1; i < topo+1; i++){
            if (dados[i] == valor){
                return (i + 1);
            }
        }

        return -1;
    }

}

