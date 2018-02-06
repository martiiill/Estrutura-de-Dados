/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.Serializable;
import recursos.exceptions.EmptyCollectionException;
import recursos.interfaces.collections.StackADT;

/**
 *
 * @author anaal
 * @param <T>
 */
public class LinkedStack<T> implements StackADT<T>, Serializable {

    /**
     * Node that represents the top of the stack.
     */
    private LinearNode<T> top;

    /**
     * Size of stack;
     */
    private int size;

    /**
     *
     */
    public LinkedStack() {
        top = null;
        size = 0;
    }

    /**
     *
     * @param top
     * @param size
     */
    public LinkedStack(LinearNode<T> top, int size) {
        this.top = top;
        this.size = size;
    }

    public LinearNode<T> getTop() {
        return top;
    }

    public void setTop(LinearNode<T> top) {
        this.top = top;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public void push(T t) {
        LinearNode<T> newNode = new LinearNode<>(t);
        if (isEmpty()) {
            top = newNode;
            size++;
        } else {
            newNode.setNext(top);
            top = newNode;
            size++;
        }
    }

    @Override
    public T pop() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Stack Vazia");
        } else {
            T element = top.getElement();
            top.setElement(null);
            size--;
            return element;
        }
    }

    @Override
    public T peek() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Stack Vazia");
        } else {
            return top.getElement();
        }
    }

    @Override
    public boolean isEmpty() {
        return (size() == 0);
    }

    @Override
    public int size() {
        return size;
    }
}
