/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.Serializable;

/**
 * Classe que modela um nó a gaurdar na lista.
 * @author anaal
 * @param <T> O tipo dos elementos a guardar nos nós.
 */
public class DoubleNode<T> implements Serializable{
    private DoubleNode<T> proximo;
    private T elemento;
    private DoubleNode<T> anterior;

    /**
     * Método construtor para instanciação de um DoubleNode vazio.
     */
    public DoubleNode() {
        proximo = null;
        elemento = null;
        anterior = null;
    }

    /**
     * Métdodo construtor para a instanciação de um DoubleNode com um elemento
     * especifico.
     *
     * @param e Elemento a ser guardado no novo DoubleNode
     */
    public DoubleNode(T e) {
        proximo = null;
        elemento = e;
        anterior = null;
    }

    public DoubleNode<T> getProximo() {
        return proximo;
    }

    public void setProximo(DoubleNode<T> s) {
        proximo = s;
    }

    public T getElemento() {
        return elemento;
    }

    public void setElemento(T e) {
        elemento = e;
    }

    public DoubleNode<T> getAnterior() {
        return anterior;
    }

    public void setAnterior(DoubleNode<T> a) {
        anterior = a;
    }
}
