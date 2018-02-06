/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.Serializable;
import java.util.Iterator;
import recursos.exceptions.ElementNotFoundException;
import recursos.exceptions.EmptyCollectionException;
import recursos.interfaces.collections.ListADT;

/**
 *
 * @author anaal
 * @param <T>
 */
public class DoublyLinkedList<T> implements ListADT<T>, Serializable {

    private DoubleNode<T> cabeca;
    private DoubleNode<T> cauda;
    private int numeroElementos;

    public DoublyLinkedList() {
        this.cabeca = null;
        this.cauda = null;
        this.numeroElementos = 0;
    }

    public DoubleNode<T> getCabeca() {
        return cabeca;
    }

    public void setCabeca(DoubleNode<T> cabeca) {
        this.cabeca = cabeca;
    }

    public DoubleNode<T> getCauda() {
        return cauda;
    }

    public void setCauda(DoubleNode<T> cauda) {
        this.cauda = cauda;
    }

    public int getNumeroElementos() {
        return numeroElementos;
    }

    public void setNumeroElementos(int numeroElementos) {
        this.numeroElementos = numeroElementos;
    }

    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (this.isEmpty()) {
            throw new EmptyCollectionException("A lista duplamente ligada está vazia.");
        } else {
            T elementoTemporario = this.cabeca.getElemento();

            if (this.size() == 1) {
                this.cabeca = this.cauda = null;
            } else {
                this.cabeca = this.cabeca.getProximo();
                this.cabeca.setAnterior(null);
            }
            this.numeroElementos--;
            return elementoTemporario;
        }
    }

    @Override
    public T removeLast() throws EmptyCollectionException {
        if (this.isEmpty()) {
            throw new EmptyCollectionException("A lista duplamente ligada está vazia.");
        } else {
            T elementoTemporario = this.cauda.getElemento();

            if (this.size() == 1) {
                this.cabeca = this.cauda = null;
            } else {
                this.cauda = this.cauda.getAnterior();
                this.cauda.setProximo(null);

            }
            this.numeroElementos--;
            return elementoTemporario;
        }
    }

    @Override
    public T remove(T t) throws ElementNotFoundException {
        if (this.isEmpty()) {
            throw new EmptyCollectionException("A lista duplamente ligada está vazia.");
        }

        if (!this.contains(t)) {
            throw new ElementNotFoundException("A lista duplamente ligada não contém o elemento.");
        }
        T elementoTemporario = null;

        if (this.cabeca.getElemento().equals(t)) { //Se o elemento estiver na cabeca.
            elementoTemporario = removeFirst();
        } else if (this.cauda.getElemento().equals(t)) { //Se o elemento estiver na cauda.
            elementoTemporario = removeLast();
        } else {
            DoubleNode<T> noAtual = this.cabeca;

            while (!t.equals(noAtual.getProximo().getElemento())) {
                noAtual = noAtual.getProximo();
            }

            noAtual.setProximo(noAtual.getProximo().getProximo());
            noAtual.getProximo().setAnterior(noAtual);

            this.numeroElementos--;
        }
        return elementoTemporario;
    }

    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("A lista duplamente ligada está vazia.");
        } else {
            return this.cabeca.getElemento();
        }
    }

    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("A lista duplamente ligada está vazia.");
        } else {
            return this.cauda.getElemento();
        }
    }

    @Override
    public boolean contains(T t) {
        boolean encontrei = true;
        if (isEmpty()) {
            DoubleNode<T> noAtual = this.cabeca;
            encontrei = false;
            while (noAtual != null && !encontrei) {
                if (t.equals(noAtual.getElemento())) {
                    encontrei = true;
                } else {
                    noAtual = noAtual.getProximo();
                }
            }
           
        }
         return encontrei;
    }
    

    @Override
    public boolean isEmpty() {
        return this.cabeca == null;
    }

    @Override
    public int size() {
        return this.numeroElementos;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator<>();
    }

    public class MyIterator<T> implements Iterator<T> {

        private DoubleNode<T> NoAtual;

        public MyIterator() {
            NoAtual = (DoubleNode<T>) cabeca;
        }

        @Override
        public boolean hasNext() {
            return NoAtual!= null;
        }

        @Override
        public T next() {
            T elemento = null;
            if(hasNext()){
                DoubleNode<T> next = NoAtual.getProximo();
                elemento = NoAtual.getElemento();
                NoAtual = next;
                return elemento;
            }else{
               return null;
            }
           
        }

//        @Override
//        public void remove() {
//            //Temos de remover o anterior
//            DoubleNode<T> remvoer;
//            remvoer = NoAtual.getAnterior();
//
//            //Se formos remover a cabeça da Lista
//            if (remvoer.getAnterior() == null) {
//                try {
//                    DoublyLinkedList.this.removeFirst();
//                } catch (EmptyCollectionException ex) {
//                    System.out.println(ex.toString());
//                }
//            } else {
//                //O anterior do anterior aponta para o actual
//                remvoer.getAnterior().setProximo(NoAtual);
//                //O anterior do actual agora é o anteior do anterior
//                NoAtual.setAnterior(remvoer.getAnterior());
//
//                remvoer.setProximo(null);
//                remvoer.setAnterior(null);
//            }
//        }
    }
}
