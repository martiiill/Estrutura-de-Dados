/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import recursos.exceptions.ElementNotFoundException;
import recursos.interfaces.IPair;
import recursos.interfaces.ISala;
import recursos.interfaces.ITunel;
import recursos.interfaces.collections.NetworkADT;

/**
 *
 * @author anaal
 * @param <T>
 */
public class NetworkGraph<T> extends Graph<T> implements NetworkADT<T> {

    ITunel[][] adMatrix;
    ISala[] vertices;
    int numverBertices;

    public NetworkGraph() {
        this.adMatrix = new ITunel[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (ISala[]) new ISala[DEFAULT_CAPACITY];
        this.numverBertices = 0;
    }

    public NetworkGraph(int numberVertices) {
        this.numverBertices = numberVertices;
        this.adMatrix = new Tunel[numberVertices][numberVertices];
        this.vertices = (Sala[]) new Sala[numberVertices];
    }

    public NetworkGraph(Tunel[][] adMatrix) {
        this.adMatrix = adMatrix;
    }

    @Override
    public void addEdge(T t, T t1, ITunel itunel) throws ElementNotFoundException {
        addEdge(getIndexSala((ISala) t), getIndexSala((ISala) t1), itunel);
    }

    public void addEdge(int index1, int index2, ITunel tunel) {
        this.adMatrix[index1][index2] = (Tunel) tunel;
        this.adMatrix[index2][index1] = (Tunel) tunel;
    }

    //Sempre que quiser usar um metodo que esteja num grafo, tenho de usar um override
    @Override
    public void addVertex(T vertex) {
        if (this.numverBertices == this.vertices.length) {
            super.expandCapacity();
        }
        this.vertices[this.numverBertices] = (Sala) vertex;
        for (int i = 0; i <= this.numverBertices; i++) {
            adMatrix[this.numverBertices][i] = new Tunel();
            adMatrix[i][this.numverBertices] = new Tunel();
        }
        this.numverBertices++;
    }

    /**
     *
     * @param t
     */
    @Override
    public void removeVertex(T t) {
        int i = getIndexSala((ISala) t);

        // Move the last vertex up to fill the hole made by vertex i
        this.vertices[i] = this.vertices[this.numverBertices - 1];
        this.vertices[this.numverBertices - 1] = null;

        for (int j = 0; j < i; j++) {
            // For every edge incident on vertex i, decrement numEdges
            if (this.adMatrix[i][j] != null) {
                this.numverBertices--;
            }
            // Move the first i elements of the last row in the adj. matrix into the ith row		
            this.adMatrix[i][j] = this.adMatrix[this.numverBertices - 1][j];
        }

        for (int j = i + 1; j < this.numverBertices - 1; j++) {
            // For every edge incident on vertex i, decrement numEdges
            if (this.adMatrix[j][i] != null) {
                this.numverBertices--;
            }
            // Move the remaining elements of the last row in the adj. matrix into the ith collumn
            this.adMatrix[j][i] = this.adMatrix[this.numverBertices - 1][j];
        }

        // Delete the last row in the matrix
        for (int k = 0; k < this.numverBertices; k++) {
            this.adMatrix[this.numverBertices - 1][k] = new Tunel();
        }

        this.numverBertices--;
    }

    /**
     *
     * @param t
     * @param t1
     */
    @Override
    public void removeEdge(T t, T t1) {
        int i = getIndexSala((ISala) t);
        if (i == -1) {
            System.out.print("Remover aresta falhou porque a areata " + i + " não existe");
            return;
        }

        int j = getIndexSala((ISala) t1);
        if (j == -1) {
            System.out.print("Remover aresta falhou porque a areata " + j + " não existe");
            return;
        }

        if (i >= j) {
            if (this.adMatrix[i][j].getId() != 0) {
                this.adMatrix[i][j] = new Tunel(); //Rede não direcionada. Remover nos dois
                this.adMatrix[j][i] = new Tunel();
            }
        } else {
            if (this.adMatrix[j][i].getId() != 0) {
                this.adMatrix[j][i] = new Tunel(); //Rede não direcionada. Remover nos dois. Todos os paramentros a 0.
                this.adMatrix[i][j] = new Tunel();
            }
        }
    }

    @Override
    public double shortestPathWeight(T t, T t1) throws ElementNotFoundException {
        int custo = 0;
        Iterator<ISala> i = iteratorShortestPath(t, t1);

        while (i.hasNext()) {
            ISala s = (ISala) i.next();
            if (adMatrix[getIndexSala((ISala) t)][getIndexSala((ISala) t1)].getDistance() > 0) {
                custo = custo + adMatrix[getIndexSala((ISala) t)][getIndexSala((ISala) t1)].getDistance();
            }
        }
        return custo;
    }

    public ITunel[][] getAdMatrix() {
        return adMatrix;
    }

    public void setAdMatrix(ITunel[][] adMatrix) {
        this.adMatrix = (Tunel[][]) adMatrix;
    }

    public int getIndexSala(ISala vertex) {
        for (int i = 0; i <= this.numverBertices; i++) {
            if (vertex.getId() == vertices[i].getId()) {
                return i;
            }
        }
        return -1;
    }

    public ISala getVertex(int i) {
        return this.vertices[i];
    }

    public boolean indexIsValidSala(int startIndex) {
        return startIndex <= this.numverBertices;
    }

    /**
     * Verifica se existe uma sala no grafo.
     *
     * @param sala
     * @return
     */
    private boolean contem(Sala sala) {
        for (int i = 0; i < this.numverBertices; i++) {
            if (this.vertices[i] != null && vertices.equals(sala)) {
                return true;
            }
        }
        return false;
    }

}
