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
import recursos.interfaces.IFormiga;
import recursos.interfaces.ISala;

/**
 *
 * @author anaal
 */
public class Sala implements ISala, Serializable{
    private int id;
    private int x;
    private int y;
    private String descricao;
    private UnorderedList<IFormiga> formigas;

    /**
     *
     * @param id
     * @param descricao
     * @param x
     * @param y
     */
    public Sala(int id,String descricao, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.descricao = descricao;
        this.formigas = new UnorderedList<>();
    }

    /**
     *
     */
    public Sala() {}
    
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
     * @return
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     *
     * @param i
     */
    @Override
    public void setX(int i) {
        this.x = i;
    }

    /**
     *
     * @return
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     *
     * @param i
     */
    @Override
    public void setY(int i) {
       this.y = i;
    }

    /**
     *
     * @param i
     */
    @Override
    public void entraFormiga(IFormiga i) {
       formigas.addToFront(i);
    }

    /**
     *
     * @param i
     * @return
     * @throws EmptyCollectionException
     * @throws ElementNotFoundException
     */
    @Override
    public IFormiga saiFormiga(int i) throws EmptyCollectionException, ElementNotFoundException {
        Iterator iterate = this.formigas.iterator();

        if (iterate.hasNext()) {
            IFormiga f = (IFormiga) iterate.next();
            if (f.getId() == i) {
                formigas.remove(f);
                return f;
            } else {
                throw new ElementNotFoundException("A formiga com esse id não existe");
            }
        } else {
            throw new EmptyCollectionException("Nao existe formigas para remover");
        }
    }

    /**
     *
     * @return
     */
    @Override
    public UnorderedList<IFormiga> listaFormigas() {
        return formigas;
    }

    @Override
    public void setDescricao(String string) {
       descricao = string;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }
}
