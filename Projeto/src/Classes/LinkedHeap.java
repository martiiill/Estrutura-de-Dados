/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.Serializable;
import recursos.exceptions.EmptyCollectionException;
import recursos.interfaces.collections.HeapADT;

/**
 *
 * @author anaal
 * @param <T>
 */
public class LinkedHeap<T> extends LinkedBinaryTree<T> implements HeapADT<T>, Serializable {

    private HeapNode<T> lastNode;
    private int count = 0;

    public LinkedHeap() {
        super();
        this.count = 0;
    }

    /**
     * Adds the specified element to this heap in the appropriate position
     * according to its key value Note that equal elements are added to the
     * right.
     *
     * @param t the element to be added to this head
     *
     */
    @Override
    public void addElement(T t) {
        HeapNode<T> node = new HeapNode<>(t);
        if (this.getRoot() == null) {
            node.setRoot(node);
        } else {
            HeapNode<T> next_parent = getNextParentAdd();
            if (next_parent.getLeft() == null) {
                next_parent.setLeft(node);
            } else {
                next_parent.setRight(node);
            }
            node.setParent(next_parent);
        }
        this.lastNode = node;
        this.count++;
        if (this.count > 1) {
            heapifyAdd();
        }
    }

    /**
     * Returns the node that will be the parent of the new node
     *
     * @return the node that will be a parent of the new node
     */
    private HeapNode<T> getNextParentAdd() {
        HeapNode<T> result = this.lastNode;
        while ((result != this.getRoot())
                && (result.getParent().getLeft() != result)) {
            result = result.getParent();
        }
        if (result != this.getRoot()) {
            if (result.getParent().getRight() == null) {
                result = result.getParent();
            } else {
                result = (HeapNode<T>) result.getParent().getRight();
                while (result.getLeft() != null) {
                    result = (HeapNode<T>) result.getLeft();
                }
            }
        } else {
            while (result.getLeft() != null) {
                result = (HeapNode<T>) result.getLeft();
            }
        }
        return result;
    }

    /**
     * Reorders this heap after adding a node.
     */
    private void heapifyAdd() {
        T temp;
        HeapNode<T> next = this.lastNode;
        temp = next.getElement();
        while ((next != this.getRoot()) && (((Comparable) temp).compareTo(next.getParent().getElement()) < 0)) {
            next.setElement(next.getParent().getElement());
            next = next.getParent();
        }
        next.setElement(temp);
    }

    /**
     * Remove the element with the lowest value in this heap and returns a
     * reference to it. Throws an EmptyCollectionException if the heap is empty.
     *
     * @return the element with the lowest value in this heap
     * @throws EmptyCollectionException if an empty collection exception occurs
     */
    @Override
    public T removeMin() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Empty Heap");
        }
        T minElement = this.getRoot();
        if (this.count == 1) {
           
            //this.getRoot() = null;
            this.lastNode = null;
        } else {
            HeapNode<T> next_last = getNewLastNode();
            if (this.lastNode.getParent().getLeft() == this.lastNode) {
                this.lastNode.getParent().setLeft(null);
            } else {
                this.lastNode.getParent().setRight(null);
            }
            lastNode.setElement(this.getRoot());
            this.lastNode = next_last;
            heapifyRemove();
        }
        this.count--;
        return minElement;
    }

    /**
     * Returns the node that will be the new last node after a remove.
     *
     * @return the node that willbe the new last node after a remove
     */
    private HeapNode<T> getNewLastNode() {
        HeapNode<T> result = this.lastNode;
        while ((result != this.getRoot()) && (result.getParent().getLeft() == result)) {
            result = result.getParent();
        }
        if (result != this.getRoot()) {
            result = (HeapNode<T>) result.getParent().getLeft();
        }
        while (result.getRight() != null) {
            result = (HeapNode<T>) result.getRight();
        }
        return result;
    }

    /**
     * Reorders this heap after removing the root element.
     */
    private void heapifyRemove() {
        T temp = null;
        HeapNode<T> node = (HeapNode<T>) this.getRoot();
        HeapNode<T> left = (HeapNode<T>) node.getLeft();
        HeapNode<T> right = (HeapNode<T>) node.getRight();
        HeapNode<T> next;
        if ((left == null) && (right == null)) {
            next = null;
        } else if (left == null) {
            next = right;
        } else if (right == null) {
            next = left;
        } else if (((Comparable) left.getElement()).compareTo(right.getElement()) < 0) {
            next = left;
        } else {
            next = right;
        }

       node.setElement(temp);
        
        while ((next != null) && (((Comparable) next.getElement()).compareTo(temp) < 0)) {
            node.setElement(next.getElement());
            node = next;
            left = (HeapNode<T>) node.getLeft();
            right = (HeapNode<T>) node.getRight();
            if ((left == null) && (right == null)) {
                next = null;
            } else if (left == null) {
                next = right;
            } else if (right == null) {
                next = left;
            } else if (((Comparable) left.getElement()).compareTo(right.getElement()) < 0) {
                next = left;
            } else {
                next = right;
            }
        }
        node.setElement(temp);
    }

    @Override
    public T findMin() throws EmptyCollectionException {
        if(isEmpty()){
            throw new EmptyCollectionException("A heap está vazia");
        }
      return this.lastNode.getElement();
    }

}
