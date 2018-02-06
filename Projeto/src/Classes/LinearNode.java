/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.Serializable;

/**
 *
 * @author anaal
 */
public class LinearNode<T> implements Serializable{
     /**
     * Reference to next node in list
     */
    private LinearNode<T> next;
    
    /**
     * Element stored ad this node
     */
    private T element;
    
    /**
     * Creates an empty node.
     */
    public LinearNode(){
        this.next = null;
        this.element = null;
    }
    
    public LinearNode(T el){
        this.next = null; 
        this.element = el;
    } 

    /**
     * Returns the node that follows this one.
     * @return LinearNode<T> reference to next node.
     */
    public LinearNode<T> getNext() {
        return next;
    }

    /**
     * Sets the node that follows this one.
     * @param next node to follow this one
     */
    public void setNext(LinearNode<T> next) {
        this.next = next;
    }

    /**
     * Return the element stored in this node.
     * @return T element stored at this node
     */
    public T getElement() {
        return element;
    }

    /**
     * Sets the element stored in this node.
     * @param element element to be stored at this node
     */
    public void setElement(T element) {
        this.element = element;
    }

    @Override
    public String toString() {
        return "LinearNode{" + "next=" + next + ", element=" + element + '}';
    }
    
}
