/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.Serializable;
import recursos.exceptions.ElementNotFoundException;
import recursos.interfaces.collections.UnorderedListADT;

/**
 *
 * @author anaal
 */
public class UnorderedList<T> extends DoublyLinkedList<T> implements UnorderedListADT<T>, Serializable {

    public UnorderedList() {
        super();
    }

    @Override
    public void addToFront(T t) {
        DoubleNode<T> noTemporario = new DoubleNode<>(t);

        if (super.isEmpty()) {
            super.setCabeca(noTemporario);
            super.setCauda(super.getCabeca());
        } else {
            super.getCabeca().setAnterior(noTemporario);
            noTemporario.setProximo(getCabeca());
            super.setCabeca(noTemporario);
        }
        super.setNumeroElementos(getNumeroElementos() + 1);
    }

    @Override
    public void addToRear(T t) {
        DoubleNode<T> noTemporario = new DoubleNode<>(t);

        if (isEmpty()) {
            setCabeca(noTemporario);
            setCauda(getCabeca());
        } else {
           getCauda().setProximo(noTemporario);
            noTemporario.setAnterior(getCauda());
            setCauda(noTemporario);
        }
        setNumeroElementos(getNumeroElementos() + 1);
    }

    @Override
    public void addAfter(T t, T t1) throws ElementNotFoundException {
        if (!isEmpty()) {
            if (super.contains(t1)) {
                DoubleNode<T> atual = getCabeca();
                DoubleNode<T> noTemporario = new DoubleNode<>(t);

                while (!t1.equals(atual.getElemento())) {
                    atual = atual.getProximo();
                }

                if (atual.getProximo() != null) {
                    noTemporario.setAnterior(atual);
                    noTemporario.setProximo(atual.getProximo());
                    atual.getProximo().setAnterior(noTemporario);
                    atual.setProximo(noTemporario);

                } else {
                    noTemporario.setAnterior(atual);
                    atual.setProximo(noTemporario);
                    super.setCauda(noTemporario);
                }
                setNumeroElementos(getNumeroElementos() + 1);
            } else {
                throw new ElementNotFoundException("Esse elemento não se encontra na Lista.");
            }
        }
    }
}
