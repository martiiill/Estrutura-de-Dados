/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.Serializable;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import recursos.exceptions.ElementNotFoundException;
import recursos.exceptions.EmptyCollectionException;
import recursos.interfaces.collections.BinaryTreeADT;
import recursos.interfaces.collections.QueueADT;
import recursos.interfaces.collections.UnorderedListADT;

/**
 *
 * @author anaal
 */
public class LinkedBinaryTree<T> implements BinaryTreeADT<T>, Serializable {

    private int contador;
    private BinaryTreeNode<T> root;

    public LinkedBinaryTree() {
        contador = 0;
        root = null;
    }

    public LinkedBinaryTree(T element) {
        root = new BinaryTreeNode<>(element);
        contador = 1;
    }

    @Override
    public T getRoot() throws EmptyCollectionException {
        return this.root.getElement();
    }

    @Override
    public boolean isEmpty() {
        return (contador == 0);
    }

    @Override
    public int size() {
        return contador;
    }

    @Override
    public boolean contains(T t) {
        while (root != null) {
            if (root.equals(t)) {
                return true;
            } else {
                root = root.getLeft();
            }
        }
        return false;
    }

    private BinaryTreeNode<T> findAgain(T targetElement, BinaryTreeNode<T> next) {
        if (next == null) {
            return null;
        }
        if (next.getElement().equals(targetElement)) {
            return next;
        }
        BinaryTreeNode<T> temp = findAgain(targetElement, next.getLeft());

        if (temp == null) {
            temp = findAgain(targetElement, next.getRight());
        }
        return temp;
    }

    @Override
    public T find(T t) throws ElementNotFoundException {
        BinaryTreeNode<T> current = findAgain(t, root);

        if (current == null) {
            try {
                throw new ElementNotFoundException("binary tree");
            } catch (ElementNotFoundException ex) {
                Logger.getLogger(LinkedBinaryTree.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (current.getElement());
    }

    @Override
    public Iterator<T> iteratorInOrder() {
        UnorderedListADT<T> tempList = new UnorderedList<>();
        inorder(root, tempList);
        return tempList.iterator();
    }

    protected void inorder(BinaryTreeNode<T> node, UnorderedListADT<T> tempList) {
        if (node != null) {
            inorder(node.getLeft(), tempList);
            tempList.addToRear(node.getElement());
            inorder(node.getRight(), tempList);
        }
    }

    @Override
    public Iterator<T> iteratorPreOrder() {
        UnorderedListADT<T> tempList = new UnorderedList<>();
        preorder(root, tempList);
        return tempList.iterator();
    }

    protected void preorder(BinaryTreeNode<T> node, UnorderedListADT<T> tempList) {
        if (node != null) {
            tempList.addToRear(node.getElement());
            preorder(node.getLeft(), tempList);
            preorder(node.getRight(), tempList);
        }
    }

    @Override
    public Iterator<T> iteratorPostOrder() {
        UnorderedListADT<T> tempList = new UnorderedList<>();
        postorder(root, tempList);
        return tempList.iterator();
    }

    protected void postorder(BinaryTreeNode<T> node, UnorderedListADT<T> tempList) {
        if (node != null) {
            preorder(node.getLeft(), tempList);
            preorder(node.getRight(), tempList);
            tempList.addToRear(node.getElement());
        }
    }

    @Override
    public Iterator<T> iteratorLevelOrder() {
        QueueADT<T> nodes = new LinkedQueue<>();
        UnorderedListADT<T> results = new UnorderedList<>();
        nodes.enqueue(root.getElement());

        while (nodes.isEmpty() == false) {
            T elem = nodes.dequeue();
            if (elem != null) {
                results.addToRear(elem);
                nodes.enqueue(root.getLeft().getElement());
            } else {
                results.addToRear(null);
            }
        }
        return results.iterator();
    }
}
