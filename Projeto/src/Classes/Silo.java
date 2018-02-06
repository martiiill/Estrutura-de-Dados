/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.Serializable;
import java.util.Iterator;
import recursos.exceptions.EmptyCollectionException;
import recursos.interfaces.IComida;
import recursos.interfaces.ISilo;

/**
 *
 * @author anaal
 */
public class Silo extends Sala implements ISilo, Serializable {

    private LinkedStack<IComida> comida;

    /**
     *
     * @param id
     * @param descricao
     * @param x
     * @param y
     */
    public Silo(int id, String descricao, int x, int y) {
        super(id, descricao, x, y);
        this.comida = new LinkedStack<>();
    }

    /**
     *
     * @param ic
     */
    @Override
    public void guardaComida(IComida ic) {
        this.comida.push(ic);
    }

    /**
     *
     * @return @throws EmptyCollectionException
     */
    @Override
    public IComida retiraComida() throws EmptyCollectionException {
        if (this.comida.isEmpty()) {
            throw new EmptyCollectionException("O silo está vazio.");
        } else {
            return (IComida) this.comida.pop();
        }
    }

    /**
     *
     * @return
     */
    @Override
    public Iterator<IComida> iteratorComida() {
        return new SiloIterator();
    }

    private class SiloIterator implements Iterator<IComida> {

        private LinearNode noAtual = comida.getTop();

        public SiloIterator() {
            this.noAtual = comida.getTop();
        }

        @Override
        public boolean hasNext() {
            return noAtual != null;
        }

        @Override
        public IComida next() {
            if (!hasNext()) {
                throw new NullPointerException();
            }
            IComida item = (IComida) noAtual.getElement();
            this.noAtual = this.noAtual.getNext();
            return item;
        }
    }
}
