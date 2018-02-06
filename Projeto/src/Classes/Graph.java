/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.Serializable;
import java.util.Iterator;
import recursos.interfaces.ISala;
import recursos.interfaces.collections.GraphADT;

/**
 *
 * @author anaal
 * @param <T>
 */
public class Graph<T> implements GraphADT<T>{

    /**
     *
     */
    final int DEFAULT_CAPACITY = 10;

    /**
     *
     */
    int numVertices; // number of vertices in the graph

    /**
     *
     */
    boolean[][] adjMatrix; // adjacency matrix

    /**
     *
     */
    T[] vertices; // values of vertices

    /**
     * Creates an empty graph.
     */
    public Graph() {
        this.numVertices = 0;
        this.adjMatrix = new boolean[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    /**
     * Inserts an edge between two vertices of the graph.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     */
    @Override
    public void addEdge(T vertex1, T vertex2) {
        addEdge(getIndex(vertex1), getIndex(vertex2));
    }

    public int getIndex(T vertex) {
        for (int i = 0; i < this.numVertices; i++) {
            if (vertices[i].equals(vertex)) {
                return i;
            }
        }
       return -1;
    }

    /**
     * Inserts an edge between two vertices of the graph.
     *
     * @param index1 the first index
     * @param index2 the second index
     */
    public void addEdge(int index1, int index2) {
        if (!this.adjMatrix[index1][index2]) {
            this.adjMatrix[index1][index2] = true;
            this.adjMatrix[index2][index1] = true;
        }
    }

    /**
     * Adds a vertex to the graph, expanding the capacity of the graph if
     * necessary. It also associates an object with the vertex.
     *
     * @param vertex the vertex to add to the graph
     */
    @Override
    public void addVertex(T vertex) {
        if (this.numVertices == this.vertices.length) {
            expandCapacity();
            expandCapacityAdjMatrix();
        }
        this.vertices[numVertices] = vertex;
        for (int i = 0; i <= this.numVertices; i++) {
            this.adjMatrix[this.numVertices][i] = false;
            this.adjMatrix[i][this.numVertices] = false;
        }
        this.numVertices++;
    }

    void expandCapacity() {
        T[] arrayExpands = (T[]) (new Object[this.vertices.length + 1]);

        /**
         * for(int i = 0; i < vertices.length; i++){ arrayExpands[i] =
         * vertices[i]; } *
         */
        System.arraycopy(this.vertices, 0, arrayExpands, 0, this.vertices.length);
        this.vertices = arrayExpands;
    }

    void expandCapacityAdjMatrix() {
        boolean[][] arrayExpands = new boolean[this.adjMatrix.length + 1][this.adjMatrix.length + 1];

        /**
         * for(int i = 0; i < vertices.length; i++){ arrayExpands[i] =
         * vertices[i]; } *
         */
        System.arraycopy(this.adjMatrix, 0, arrayExpands, 0, this.adjMatrix.length);
        this.adjMatrix = arrayExpands;
    }

    /**
     *
     * @param t
     */
    @Override
    public void removeVertex(T t) {
        int i = getIndex(t);

        // Move the last vertex up to fill the hole made by vertex i
        this.vertices[i] = this.vertices[this.numVertices - 1];
        this.vertices[this.numVertices - 1] = null;

        for (int j = 0; j < i; j++) {
            // For every edge incident on vertex i, decrement numEdges
            if (this.adjMatrix[i][j]) {
                this.numVertices--;
            }
            // Move the first i elements of the last row in the adj. matrix into the ith row		
            this.adjMatrix[i][j] = this.adjMatrix[this.numVertices - 1][j];
        }

        for (int j = i + 1; j < this.numVertices - 1; j++) {
            // For every edge incident on vertex i, decrement numEdges
            if (this.adjMatrix[j][i]) {
                this.numVertices--;
            }
            // Move the remaining elements of the last row in the adj. matrix into the ith collumn
            this.adjMatrix[j][i] = this.adjMatrix[this.numVertices - 1][j];
        }

        // Delete the last row in the matrix
        for (int k = 0; k < this.numVertices; k++) {
            this.adjMatrix[this.numVertices - 1][k] = false;
        }

        this.numVertices--;
    }

    /**
     *
     * @param t
     * @param t1
     */
    @Override
    public void removeEdge(T t, T t1) {
        int i = getIndex(t);
        if (i == -1) {
            System.out.print("Remover aresta falhou porque a areata " + i + " não existe");
            return;
        }

        int j = getIndex(t1);
        if (j == -1) {
            System.out.print("Remover aresta falhou porque a areata " + j + " não existe");
            return;
        }

        if (i >= j) {
            if (this.adjMatrix[i][j]) {
                this.adjMatrix[i][j] = false;
            }
        } else {
            if (this.adjMatrix[j][i]) {
                this.adjMatrix[j][i] = false;
            }
        }
    }

    /**
     *
     * @param t
     * @return
     */
    
    //Este tambem está bem a meu ver 
   
    @Override
    public Iterator iteratorBFS(T t) {
        T x;
        LinkedQueue<T> traversalQueue = new LinkedQueue<>();
        UnorderedList<T> resultsList = new UnorderedList<>();

        if (!indexIsValid(getIndex(t))) {
            return resultsList.iterator();
        }

        boolean[] visitado = new boolean[this.numVertices];
        for (int i = 0; i < this.numVertices; i++) {
            visitado[i] = false;
        }
        traversalQueue.enqueue(t);
        visitado[getIndex(t)] = true;

        while (!traversalQueue.isEmpty()) {
            x = traversalQueue.dequeue();
            resultsList.addToRear(this.vertices[getIndex(x)]);

            //Encontra todos os vertices adjacentes a x que nao tenham sido visitados e adiciona a queue
            for (int i = 0; i < this.numVertices; i++) {
                if (this.adjMatrix[getIndex(x)][i] && !visitado[i]) {
                    traversalQueue.enqueue(getVertices()[i]);
                    visitado[i] = true;
                }
            }
        }
        return resultsList.iterator();
    }

    /**
     *
     * @param t
     * @return
     */
    
    /**mandei mail ao prof sobre este iterador porque não sei o que alterar 
     * por ele está bem a meu ver
     * 
     * @param t
     * @return 
     */
    @Override
    public Iterator iteratorDFS(T t) {
        T x;
        boolean found;
        LinkedStack<T> traversalStack = new LinkedStack<>();
        UnorderedList<T> resultList = new UnorderedList<>();
        boolean[] visited = new boolean[this.numVertices];
        if (!indexIsValid(getIndex(t))) {
            return resultList.iterator();
        }
        for (int i = 0; i < this.numVertices; i++) {
            visited[i] = false;
        }
        traversalStack.push(t);
        resultList.addToRear(this.vertices[getIndex(t)]);
        visited[getIndex(t)] = true;
        while (!traversalStack.isEmpty()) {
            x = traversalStack.peek();
            found = false;

            //Encontra o vertice adjacente a x que nao tenha sido visitado e faz push
            for (int i = 0; (i < this.numVertices) && !found; i++) {
                if (this.adjMatrix[getIndex(x)][i] && !visited[i]) {
                    traversalStack.push(getVertices()[i]);
                    resultList.addToRear(this.vertices[i]);
                    visited[i] = true;
                    found = true;
                }
            }
            if (!found && !traversalStack.isEmpty()) {
                traversalStack.pop();
            }
        }
        return resultList.iterator();
    }

    /**
     *
     * @param t
     * @param t1
     * @return
     */
    @Override
    public Iterator iteratorShortestPath(T t, T t1) {
        T x;
        LinkedQueue<T> traversalQueue = new LinkedQueue<>();
        UnorderedList<T> resultsList = new UnorderedList<>();

        if (!indexIsValid(getIndex(t))) {
            return resultsList.iterator();
        }

        boolean[] visitado = new boolean[this.numVertices];
        for (int i = 0; i < this.numVertices; i++) {
            visitado[i] = false;
        }
        traversalQueue.enqueue(t);
        visitado[getIndex(t)] = true;

        while (!traversalQueue.isEmpty()) {
            x = traversalQueue.dequeue();
            resultsList.addToRear(vertices[getIndex(x)]);

            //Encontra todos os vertices adjacentes a x que nao tenham sido visitados e adiciona a queue
            for (int i = 0; i < this.numVertices; i++) {
                if (this.adjMatrix[getIndex(x)][i] && !visitado[i]) {
                    traversalQueue.enqueue(getVertices()[i]);
                    visitado[i] = true;
                }
            }
        }
        return resultsList.iterator();
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return (numVertices == 0);
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isConnected() {
        boolean connect = false;
        for (int i = 0; i < this.numVertices; i++) {
            for (int j = 0; j < this.numVertices; j++) {
                /**
                 * if(adjMatrix[i][j] == true){ connect= true; } else { connect
                 * = false; }
                 */
                connect = this.adjMatrix[i][j] == true;
            }
        }
        return connect;
    }

    /**
     *
     * @return
     */
    @Override
    public int size() {
        return this.numVertices;
    }

    /**
     *
     * @return
     */
    @Override
    public T[] getVertices() {
        return this.vertices;
    }

    /**
     *
     * @return
     */
    public int getNumVertices() {
        return this.numVertices;
    }

    /**
     *
     * @param numVertices
     */
    public void setNumVertices(int numVertices) {
        this.numVertices = numVertices;
    }

    /**
     *
     * @return
     */
    public boolean[][] getAdjMatrix() {
        return adjMatrix;
    }

    /**
     *
     * @param adjMatrix
     */
    public void setAdjMatrix(boolean[][] adjMatrix) {
        this.adjMatrix = adjMatrix;
    }

    public boolean indexIsValid(int startIndex) {
        return startIndex <= this.numVertices;
    }
}
