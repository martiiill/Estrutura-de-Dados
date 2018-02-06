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
import recursos.exceptions.FormigaCheiaException;
import recursos.interfaces.IComida;
import recursos.interfaces.IFormiga;

/**
 *
 * @author anaal
 */
public class Formiga implements IFormiga, Serializable {

    private int capacidadeCarga;
    private int id;
    private UnorderedList<IComida> comidaFormiga;

    public Formiga(int id,int capacidadeCarga) {
        this.id = id;
        this.capacidadeCarga = capacidadeCarga;
        this.comidaFormiga = new UnorderedList<>();
    }

    public Formiga(int id) {
        this.id = id;
        this.capacidadeCarga = 0;
        this.comidaFormiga = new UnorderedList<>();
    }

    @Override
    public int getCapacidadeCarga() {
        return this.capacidadeCarga;
    }

    /**
     *
     * @param i
     */
    @Override
    public void setCapacidadeCarga(int i) {
        this.capacidadeCarga = i;
    }

    /**
     *
     * @return
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     *
     * @param i
     */
    @Override
    public void setId(int i) {
        this.id = i;
    }

    /**
     *
     * @param ic
     * @throws FormigaCheiaException
     */
    @Override
    public void addComida(IComida ic) throws FormigaCheiaException {
        if (this.capacidadeCarga > this.comidaFormiga.size()) {
            this.comidaFormiga.addToFront(ic);
        } else {
            throw new FormigaCheiaException();
        }
    }

    /**
     *
     * @param i
     * @return
     * @throws EmptyCollectionException
     * @throws ElementNotFoundException
     */
    @Override
    public IComida removeComida(int i) throws EmptyCollectionException, ElementNotFoundException {
        IComida c = null;
        Iterator iterate = this.comidaFormiga.iterator();

        if (iterate.hasNext()) {
            c = (IComida) iterate.next();
            if (c.getId() == i) {
                this.comidaFormiga.remove(c);
                return c;
            } else {
                throw new ElementNotFoundException("A comida com esse id não existe");
            }
        } else {
            throw new EmptyCollectionException("Nao existe comida para remover");
        }
    }

    /**
     *
     * @return @throws EmptyCollectionException
     */
    @Override
    public IComida removeComida() throws EmptyCollectionException {
        IComida c = null;
        Iterator iterate = this.comidaFormiga.iterator();

        if (iterate.hasNext()) {
            c = (IComida) iterate.next();
            this.comidaFormiga.removeFirst();
            return c;
        } else {
            throw new EmptyCollectionException("Não existe elementos IComida");
        }
    }

    /**
     *
     * @return
     */
    @Override
    public int getCarga() {
        return this.comidaFormiga.size();
    }

    public UnorderedList<IComida> getComidaFormiga() {
        return comidaFormiga;
    }

    public void setComidaFormiga(UnorderedList<IComida> comidaFormiga) {
        this.comidaFormiga = comidaFormiga;
    }
}
