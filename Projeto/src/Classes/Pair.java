/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.Serializable;
import recursos.interfaces.IPair;

/**
 *
 * @author anaal
 * @param <V>
 * @param <E>
 */
public class Pair<V,E> implements IPair<V, E>{
    private V first;
    private E second;

    /**
     *
     * @param first
     * @param second
     */
    public Pair(V first, E second) {
        this.first = first;
        this.second = second;
    }

    public Pair() {}
    
    /**
     *
     * @param first
     */
    public void setFirst(V first) {
        this.first = first;
    }

    /**
     *
     * @param second
     */
    public void setSecond(E second) {
        this.second = second;
    }

    /**
     *
     * @return
     */
    @Override
    public V getFirst() {
       return first;
    }

    /**
     *
     * @return
     */
    @Override
    public E getSecond() {
        return second;
    }   
}