package com.example.yurif.estruturadedados_animacoes.estruturas.ABP;


import com.example.yurif.estruturadedados_animacoes.estruturas.ABP.No;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ABP {
    private No raiz;

    private ArrayList<Integer> inOrderList = new ArrayList<>();

    public ABP(){
        raiz = null;
    }

    /** Verifica se a árvore está vazia */
    public boolean vazia (){
        return (raiz == null);
    }

    /**Buscar recursivamente a partir da raiz.
     Retorna o endereço se o elemento for
     encontrado, caso contrário retorna NULL*/
    private No busca(No T, int valor) {
        if (T == null)
            return null;  // Arvore Vazia

        if(T.getConteudo() == valor)
            return T; 	// Elem. encontrado na raiz

        if (valor < T.getConteudo())
            return busca(T.getEsq(), valor);
        else
            return busca(T.getDir(), valor);
    }

    /**Buscar um elemento na ABP
     Retorna o endereço se o elemento for
     encontrado, caso contrário retorna NULL*/
    public No busca(int valor) {
        if (raiz != null)
            return busca(raiz, valor);

        return null;
    }


    /**Exibe o conteúdo de uma árvore no formato in-ordem
     (preserva a ordenação)*/
    private void exibe (No T){
        if (T != null) {

            exibe(T.getEsq());
            inOrderList.add(T.getConteudo());
            System.out.print(" " + T.getConteudo());
            exibe(T.getDir());
        }
    }

    public void exibe() {
        inOrderList.clear();
        if (raiz == null)
            System.out.println("Arvore vazia");
        else{

            exibe(raiz);
        }
    }

    /**Insere um nó em uma árvore ABP
     Retorna 1 se a inserção for com sucesso.
     Caso contrário retorna 0*/
    public boolean insere(int valor){

        No novoNo = new No();
        novoNo.setConteudo(valor);
        novoNo.setEsq(null);
        novoNo.setDir(null);

        if (raiz == null){ // Arvore vazia
            raiz = novoNo;
            return true;
        }

        // Procura a posicao correta pra inserir o novo no
        No aux = raiz;
        No p = null;
        int altura = 0;
        while (aux != null) {
            p = aux;
            altura++;
            if(altura>2){
                return false;
            }
            if (valor < aux.getConteudo())
                aux = aux.getEsq();
            else
                aux = aux.getDir();
        }

        // Encontrou um nó folha para inserir
        if (valor < p.getConteudo())
            p.setEsq(novoNo);
        else
            p.setDir(novoNo);
        return true;
    }

    public No getRaiz() {
        return raiz;
    }

    public ArrayList<Integer> getInOrderList() {
        return inOrderList;
    }




}
