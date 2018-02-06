/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author anaal
 */
public class PriorityQueueNode<T> implements Comparable<PriorityQueueNode> {

    private static int nextorder = 0;
    private int priority;
    private int order;
    private T element;

    /**
     * Creates a new PriorityQueueNode with the specified data.
     *
     * @param obj the element of the new priority queue node
     * @param prio the integer priority of the new queue node
     */
    public PriorityQueueNode(T obj, int prio) {
        element = obj;
        priority = prio;
        order = nextorder;
        nextorder++;
    }
    
     public PriorityQueueNode(T obj) {
        element = obj;
        priority = 0;
        order = nextorder;
        nextorder++;
    }

    public static int getNextorder() {
        return nextorder;
    }

    public static void setNextorder(int nextorder) {
        PriorityQueueNode.nextorder = nextorder;
    }

    /**
     * Returns the priority value for this node.
     *
     * @return the integer priority for this node.
     */
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Returns the order for this node.
     *
     * @return the integer order for this node.
     */
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * Returns the element in this node.
     *
     * @return the element contained within this node.
     */
    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    @Override
    public String toString() {
        String temp = (element.toString() + priority + order);
        return temp;
    }

    /**
     * Returns the 1 if the current node has higher priority than the given
     * node and -1 otherwise.
     * @param obj the node to compare to this node
     * @return the integer result of the comparison of the obj node and this one
     */
    @Override
    public int compareTo(PriorityQueueNode obj) {
        int result;
        PriorityQueueNode<T> temp = obj;
        
        if(priority > temp.getPriority()){
            result = 1;
        } else if (priority < temp.getPriority()){
            result = -1;
        } else if (order > temp.getOrder()){
            result = 1;
        } else {
            result = -1;
        }
        return result;
    }

}
