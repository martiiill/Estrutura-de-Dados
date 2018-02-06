/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.Serializable;

/**
 * Classe que modela o nó da árvore binária. Determina também o número que filhos
 * que a árvore contém.
 * @param <T> O tipo dos elementos a guardar nos nós da árvore.
 */
public class BinaryTreeNode<T> implements Serializable {

    private T element;
    private BinaryTreeNode<T> left, right, root;

    /**
     * Creates a new tree node with the specified data.
     *
     * @param obj the element that will become a part of thw new tree node.
     */
    public BinaryTreeNode(T obj) {
        this.element = obj;
        this.left = null;
        this.right = null;
        this.root = null;
    }

    public BinaryTreeNode() {
    }

    public BinaryTreeNode(T element, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }

    /**
     * Returns the number of non-null children of this node. This method may be
     * able to be written more efficiently.
     *
     * @return the integer number of non-null children of this node.
     */
    public int numChildren() {
        int children = 0;

        if (this.left != null) {
            children = 1 + this.left.numChildren();
        }

        if (this.right != null) {
            children = children + 1 + this.right.numChildren();
        }
        return children;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public BinaryTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }

    public BinaryTreeNode<T> getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }

    public BinaryTreeNode<T> getRoot() {
        return root;
    }

    public void setRoot(BinaryTreeNode<T> root) {
        this.root = root;
    }
    
    
}
