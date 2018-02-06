/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.Serializable;
import recursos.interfaces.collections.QueueADT;

/**
 *
 * @author anaal
 * @param <T>
 */
public class LinkedQueue<T> implements QueueADT<T>, Serializable {

    /**
     * The first element in this queue.
     */
    private LinearNode<T> first;

    /**
     * The last element in thhis queue.
     */
    private LinearNode<T> rear;

    /**
     * Number of elements in this queue.
     */
    private int counter;

    /**
     * Método construtor que permite instanciar uma LinkedQueue.
     */
    public LinkedQueue() {
        this.first = null;
        this.rear = null;
        this.counter = 0;
    }

    public LinkedQueue(LinearNode<T> f, LinearNode<T> r, int c) {
        this.first = f;
        this.rear = r;
        this.counter = c;
    }

    public LinkedQueue(LinkedQueue<T> queue) {
        this.first = queue.getFirst();
        this.rear = queue.getRear();
    }

    /**
     * Retorna o primeiro elemento da Linked Queue.
     *
     * @return primeiro elemeento da linked queue.
     */
    public LinearNode<T> getFirst() {
        return first;
    }

    /**
     * Permite definir o primeiro elemento da Linked Queue.
     *
     * @param first elemento que quer definir na Linked Queue.
     */
    public void setFirst(LinearNode<T> first) {
        this.first = first;
    }

    /**
     * Retorna o último elemento da Linked Queue.
     *
     * @return último elemento da Linked Queue.
     */
    public LinearNode<T> getRear() {
        return rear;
    }

    /**
     * Permite definir o primeiro elemento da
     *
     * @param rear
     */
    public void setRear(LinearNode<T> rear) {
        this.rear = rear;
    }

    /**
     *
     * @return
     */
    public int getCounter() {
        return counter;
    }

    /**
     *
     * @param counter
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }

    /**
     * Adds one element to the rear of this queue.
     *
     * @param element the element to be added to the rear of this queue
     */
    @Override
    public void enqueue(T element) {
        LinearNode<T> nodeTemp = new LinearNode<>(element);

        if (isEmpty()) {
            rear =first = nodeTemp;
            counter++;
        } else {
            //nodeTemp = rear.getNext();
            rear.setNext(nodeTemp);
            rear = nodeTemp;
            counter++;
        }
    }

    /**
     * Removes and returns the element at the front of this queue.
     *
     * @return the element at the front os this queue.
     */
    @Override
    public T dequeue() {
        T elemento;
        if (isEmpty()) {
            throw new NullPointerException("LinkedQueue empty");
        } else {
            elemento = first.getElement();
            //first.setNext(first);
            first = first.getNext();
            counter--;
        }
        if (isEmpty()) {
            rear = null;
        }
        return elemento;
    }

    /**
     * Returns without removing the element at the front of this queue.
     *
     * @return the first element of this queue.
     */
    @Override
    public T first() throws NullPointerException {
        if (isEmpty()) {
            throw new NullPointerException("LinearQueue vazia");
        } else {
            return this.first.getElement();
        }
    }

    /**
     * Returns true if this queue contains no elements.
     *
     * @return true if this queue is empty.
     */
    @Override
    public boolean isEmpty() {
        return counter == 0;
    }

    /**
     * Returns the number of elements int this queue.
     *
     * @return the integer representations of the size of this queue.
     */
    @Override
    public int size() {
        return counter;
    }

    /**
     * Returns a string representation os this queue.
     *
     * @return the string representation os this queue.
     */
    @Override
    public String toString() {

        String result = "";
        LinearNode atual = this.first;

        while (atual != null) {
            result += atual.getElement().toString() + "\n";
            atual = atual.getNext();
        }
        return result;
    }
}
