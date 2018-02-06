/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.ArrayList;
import java.util.Iterator;
import recursos.interfaces.collections.ListADT;

/**
 *
 * @author anaal
 */
public class List<T> implements ListADT<T> {

    public final int DEFAULT_CAPACITY = 100;
    public int index;
    public ArrayList<T> list;

    /**
     * Empty construtor that creates empty list using default capacity.
     */
    public List() {
        index = 0;
        list = new ArrayList<>(DEFAULT_CAPACITY);
    }

    /**
     * Empty construtor that creates empty list using the specified capacity.
     *
     * @param initialCapacity integer that represents initial capacity.
     */
    public List(int initialCapacity) {
        index = 0;
        list = new ArrayList<>(initialCapacity);
    }

    /**
     * Creates a construtor of list using the array index and a list.
     *
     * @param index integer that represents array index.
     * @param list array list that represents a list.
     */
    public List(int index, ArrayList<T> list) {
        this.index = index;
        this.list = list;
    }

    /**
     * Returns the number of index.
     * @return integer representation of the index.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Sets the index stored in this list.
     * @param index integer representation of the index.
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Returns the array list.
     * @return array that represents the list.
     */
    public ArrayList<T> getList() {
        return list;
    }

    /**
     * Sets the list int this array list.
     * @param list array that represents the list.
     */
    public void setList(ArrayList<T> list) {
        this.list = list;
    }   

    @Override
    public T removeFirst() {
        T result = null;
        if (isEmpty()) {
            throw new NullPointerException("Empty list.");
        }
        result = list.get(0);
        index--;
        for (int i = 0; i < index; i++) {
            list.get(i+1);
        }
        return result;
    }

    @Override
    public T removeLast() {
        T result = null;

        if (isEmpty()) {
            throw new NullPointerException("Empty list.");
        }
        index--;
        result = list.get(index);
        return result;
    }
    
    /**
     * Returns the array index of the specified element or negative value if it is 
     * not found.
     * @param element the element to be searched.
     * @return integer that represents the result of the search.
     */
    private int search(T element){
         int i = 0, result = -1;
        boolean found = false;

        if (!isEmpty()) {
            while (! found && i < index) {
                if (element.equals(list.get(i))) {
                    found = true;
                } else {
                    i++;
                }
            }
        } else {
            throw new NullPointerException("Empty list.");
        }
        if(found){
            result = i;
        }
        return result;
    }

    @Override
    public T remove(T element) {
          T result = null;
          int indexTemp = search(element);
          
          if(index == -1){
              throw new NullPointerException("Element not found int the list.");
          }

        result = list.get(indexTemp);
        index--;
        for (int i = indexTemp; i < index; i++) {
            list.get(i + 1);
        }
     
        return result;
    }

    @Override
    public T first() {
        if (isEmpty()) {
            throw new NullPointerException("Empty list.");
        }
        return list.get(0);
    }

    @Override
    public T last() {
        if (isEmpty()) {
            throw new NullPointerException("Empty list.");
        }
        return list.get(index-1);
    }

    @Override
    public boolean contains(T target) {
        int i = 0;
        boolean found = false;

        if (!isEmpty()) {
            while (! found && i < index) {
                if (target.equals(list.get(i))) {
                    found = true;
                } else {
                    i++;
                }
            }
        } else {
            throw new NullPointerException("Empty list.");
        }
        return found;
    }

    @Override
    public boolean isEmpty() {
        return (size() == 0);
    }

    @Override
    public int size() {
        return index;
    }

    @Override
    public Iterator<T> iterator() {
      return new  IteratorList();
    }
    
    @Override
    public String toString() {
        String result = "";
        System.out.println("Elements on the list:");
        
        while(first() != null){
            T next = first();
            result = result + next.toString() + " | ";
        }      
        return result;
    }
    
    private class IteratorList implements Iterator<T>{
        int position;

        @Override
        public boolean hasNext() {
            return next() != null;
        }

        @Override
        public T next() {
          T elem = list.get(position);
          position++;
          return elem;
        }

        @Override
        public void remove() {
            Iterator.super.remove(); //To change body of generated methods, choose Tools | Templates.
        }     
    }

}