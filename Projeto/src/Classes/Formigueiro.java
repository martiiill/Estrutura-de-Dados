/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import recursos.exceptions.ElementNotFoundException;
import recursos.exceptions.EmptyCollectionException;
import recursos.exceptions.FormigaCheiaException;
import recursos.exceptions.ProcessedException;
import recursos.interfaces.IComida;
import recursos.interfaces.IFormiga;
import recursos.interfaces.IFormigueiro;
import recursos.interfaces.IPair;
import recursos.interfaces.IProcessamento;
import recursos.interfaces.ISala;
import recursos.interfaces.ISilo;
import recursos.interfaces.ITunel;

/**
 *
 * @author anaal
 */
public class Formigueiro implements IFormigueiro {

    ISala entrada;
    NetworkGraph grafo;

    /**
     *
     * @param entrada
     */
    public Formigueiro(ISala entrada) {
        this.entrada = entrada;
        this.grafo = new NetworkGraph();

        this.grafo.addVertex(entrada);
    }

    /**
     *
     */
    public Formigueiro() {
        this.grafo = new NetworkGraph();
    }

    /**
     *
     * @return
     */
    @Override
    public ISala getEntrada() {
        return this.entrada;
    }

    /**
     *
     * @param isala
     */
    @Override
    public void setEntrada(ISala isala) {
        entrada = isala;
    }

    /**
     *
     * @return
     */
    @Override
    public Iterator<ISala> iterator() {
        return iteratorBFS(entrada);
    }

    @Override
    public Iterator<ISala> iteratorBFS(ISala isala) {
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<>();
        UnorderedList<ISala> resultsList = new UnorderedList<>();

        int startIndex = grafo.getIndexSala(isala);

        if (!grafo.indexIsValidSala(startIndex)) {
            return resultsList.iterator();
        }

        boolean[] visitado = new boolean[this.grafo.numverBertices];
        for (int i = 0; i < this.grafo.numverBertices; i++) {
            visitado[i] = false;
        }

        traversalQueue.enqueue(startIndex);
        visitado[startIndex] = true;

        while (!traversalQueue.isEmpty()) {
            int x = traversalQueue.dequeue();

            resultsList.addToRear(grafo.vertices[x]);

            //Encontra todos os vertices adjacentes a x que nao tenham sido visitados e adiciona a queue
            for (int i = 0; i < this.grafo.numverBertices; i++) {
                if (this.grafo.adMatrix[x][i] != null && !visitado[i]) {
                    traversalQueue.enqueue(i);
                    visitado[i] = true;
                }
            }
        }
        return resultsList.iterator();
    }

    @Override
    public Iterator<ISala> iteratorBFS() {
        return iteratorBFS(entrada);
    }

    /**
     *
     * @param isala
     */
    @Override
    public void addSala(ISala isala) {
        this.grafo.addVertex(isala);
    }

    /**
     *
     * @param isala
     * @param isala1
     * @param i
     */
    @Override
    public void ligaSala(ISala isala, ISala isala1, int i) {
        int valoreX = isala.getX() + isala1.getX();
        int valoreY = isala.getY() + isala1.getY();
        double distance = (valoreX * valoreX) + (valoreY * valoreY);
        double total = Math.sqrt(distance); //Teorema de Pitagoras
        Tunel t = new Tunel((int) total, i);
        try {
            this.grafo.addEdge(isala, isala1, t);
        } catch (ElementNotFoundException ex) {
            Logger.getLogger(Formigueiro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @return
     */
    @Override
    public int getMaxY() {
        Iterator it = iterator();
        int max = 0;

        while (it.hasNext()) {
            ISala s = (ISala) it.next();
            if (s.getY() > max) {
                max = s.getY();
            }
        }
        return max;
    }

    /**
     *
     * @return
     */
    @Override
    public int getMaxX() {
        Iterator it = iterator();
        int max = 0;

        while (it.hasNext()) {
            ISala s = (ISala) it.next();
            if (s.getX() > max) {
                max = s.getX();
            }
        }
        return max;
    }

    /**
     * Método que devolve os tuneis de uma sala
     *
     * @param sala
     * @return
     */
    private ITunel[] getTunels(ISala sala) {
        ITunel[] itu = new ITunel[grafo.numverBertices];
        int index = grafo.getIndexSala(sala);
        if (grafo.vertices[index].getId() == grafo.getVertex(index).getId()) {
            for (int j = 0; j < grafo.numverBertices; j++) { //Basta comparares a outra posicao, senao coloca tuneis iguais no array.
                if (grafo.adMatrix[index][j].getDistance() > 0) { //Se existir uma distancia
                    itu[j] = grafo.adMatrix[index][j]; //Adiciona no array 
                }
            }
        }
        return itu;
    }

    /**
     *
     * @param isala Sala de destino do tunel
     * @return
     */
    @Override
    public Iterator<IPair<ISala, ITunel>> ligacoesDe(ISala isala) {
        ISala[] vertices = grafo.vertices; //Obtenho todos os vertices do grafo
        ITunel[] tunels = getTunels(isala); //Obtenho todos os tuneis de uma sala
        UnorderedList<IPair<ISala, ITunel>> pairList = new UnorderedList<>();

        for (int i = 0; i < tunels.length; i++) {
            if (tunels[i] != null) { //Se o array da posicao esta preenchido.
                pairList.addToRear(new Pair<>((ISala) vertices[i], tunels[i])); //Adiciono os tuneis e as salas numa lista
            }
        }
        return pairList.iterator(); //retorno o iterator dessa lista.
    }

    /**
     *
     * @param i
     * @return
     * @throws ElementNotFoundException
     */
    @Override
    public ISala getSalaFormiga(int i) throws ElementNotFoundException {
        Iterator t = iterator();
        Sala s = null;

        while (t.hasNext()) {
            s = (Sala) t.next();
            if (s.listaFormigas() != null) {
                if (s.listaFormigas().contains(getFormiga(i))) {
                    return s;
                }
            }
        }
        return s;
    }

    /**
     *
     * @param i
     * @return
     * @throws ElementNotFoundException
     */
    @Override
    public IFormiga getFormiga(int i) throws ElementNotFoundException {
        Iterator t = iterator();
        Formiga tt = null;

        while (t.hasNext()) {
            ISala s = (Sala) t.next();
            if (s.listaFormigas() != null) {
                Iterator formi = s.listaFormigas().iterator();
                while (formi.hasNext()) {
                    tt = (Formiga) formi.next();
                    if (tt.getId() == i) {
                        return tt;
                    }
                }
            }
        }
        return tt;
    }

    /**
     *
     * @param i
     * @return
     * @throws ElementNotFoundException
     */
    @Override
    public ISala getSala(int i) throws ElementNotFoundException {
        Iterator iterate = iterator();
        ISala c = null;
        while (iterate.hasNext()) {
            c = (ISala) iterate.next();
            if (c.getId() == i) {
                return c;
            }
        }
        return c;
    }

    /**
     *
     * @param isala
     * @param isala1
     * @return
     */
    @Override
    public boolean vizinhos(ISala isala, ISala isala1) {
        int prim = this.grafo.getIndex(isala); //Indice da sala
        int sec = this.grafo.getIndex(isala1); //Indice da sala2
        return this.grafo.adMatrix[prim][sec].getDistance() != 0; //Se existir um valor para a distancia
    }
    
     // A utility function to find the vertex with minimum distance value,
    // from the set of vertices not yet included in shortest path tree
    int minDistance(int dist[], Boolean sptSet[])
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index=-1;
 
        for (int v = 0; v < grafo.numverBertices; v++)
            if (sptSet[v] == false && dist[v] <= min)
            {
                min = dist[v];
                min_index = v;
            }
 
        return min_index;
    }
 
    // A utility function to print the constructed distance array
    void printSolution(int dist[], int n)
    {
        System.out.println("Vertex   Distance from Source");
        for (int i = 0; i < grafo.numverBertices; i++)
            System.out.println(i+" tt "+dist[i]);
    }
 
    // Funtion that implements Dijkstra's single source shortest path
    // algorithm for a graph represented using adjacency matrix
    // representation
    public int[] dijkstra(ITunel graph[][], int src)
    {
        int dist[] = new int[grafo.numverBertices]; // The output array. dist[i] will hold
                                 // the shortest distance from src to i
 
        // sptSet[i] will true if vertex i is included in shortest
        // path tree or shortest distance from src to i is finalized
        Boolean sptSet[] = new Boolean[grafo.numverBertices];
 
        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < grafo.numverBertices; i++)
        {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }
 
        // Distance of source vertex from itself is always 0
        dist[src] = 0;
 
        // Find shortest path for all vertices
        for (int count = 0; count < grafo.numverBertices-1; count++)
        {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            int u = minDistance(dist, sptSet);
 
            // Mark the picked vertex as processed
            sptSet[u] = true;
 
            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < grafo.numverBertices; v++)
 
                // Update dist[v] only if is not in sptSet, there is an
                // edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of dist[v]
                if (!sptSet[v] && graph[u][v].getDistance() >0 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u]+graph[u][v].getDistance() < dist[v])
                    dist[v] = dist[u] + graph[u][v].getDistance();
        }
 
        // print the constructed distance array
        printSolution(dist, grafo.numverBertices);
        return dist;
    }

    /**
     *
     * @param i
     * @param i1
     * @return
     * @throws ElementNotFoundException
     */
    @Override
    public Iterator<ISala> iteratorShortestPath(int i, int i1) throws ElementNotFoundException {
        int caminhos[] = dijkstra(grafo.getAdMatrix(), i);
        int maior = -1;
        int index = -1;
        for(int j=0;j<caminhos.length;j++){
            if(j==i1){
                maior = caminhos[j];
                index = j;
            }
        }
        ISala sala = getSala(index);
        System.out.println(sala.getDescricao());
        return iterator();
    }

    /**
     *
     * @param i
     * @param i1
     * @return
     * @throws ElementNotFoundException
     */
    @Override
    public Iterator<ISala> iteratorMoveFormigaShortestPath(int i, int i1) throws ElementNotFoundException {
        Iterator<ISala> it = iteratorShortestPath(grafo.getIndexSala(getSalaFormiga(i)), i1); //Salas entre a sala da formiga e a sala destino
        ITunel[][] copiaAdMAtrix = grafo.getAdMatrix();
        ITunel y = null;

        while (it.hasNext()) { //Enquanto houver salas no iterator
            ISala s = (ISala) it.next(); //Obtem a sala atual
            if (s.getId() == i1) { //Se a sala de destino for igual á sala atual do iterator
                IFormiga f = getFormiga(i); //Obtem formiga
                while (it.hasNext()) { //Enquanto houver formigas
                    Iterator<IPair<ISala, ITunel>> yT = ligacoesDe(getSalaFormiga(i)); //Obtem os tuneis
                    while (yT.hasNext()) {
                        if (yT.next().getFirst() == getSala(i1)) {//Se a sala destino do pair é igual á sala destino enviada
                            if (y.getRadious() >= f.getCarga()) { //Se o raio foi maior ou igual que a carga atual da formigita
                                getSalaFormiga(i).saiFormiga(f.getId()); //a formiga sai da sala
                                getSala(i1).entraFormiga(f); //adiciono a formiga na sala de destino     
                                return it;
                            }
                        }
                    }
                }
            }
        }
        return it;
    }

    /**
     *
     * @param i id da formiga
     * @param i1 id da sala de destino
     * @return
     * @throws ElementNotFoundException
     */
    @Override
    public Iterator<ISala> iteratorCarregaEMoveFormigaShortestPath(int i, int i1) throws ElementNotFoundException { //falta tirar a comida da sala em questao
        Iterator<ISala> iter = null;
        ITunel y = null;

        if (getSalaFormiga(i) instanceof Processamento) { //Se a sala da formiga for um processamento
            IProcessamento p = (IProcessamento) getSalaFormiga(i);
            while (getFormiga(i).getCapacidadeCarga() != getFormiga(i).getCarga()) {
                try {
                    p.acrescentaComida(p.getProximaComida()); //acrescenta a proxima comida
                } catch (EmptyCollectionException | ProcessedException ex) {
                    Logger.getLogger(Formigueiro.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            iter = iteratorShortestPath(getSalaFormiga(i).getId(), i1);
            if (iter != null) {
                while (iter.hasNext()) {
                    if (getSala(iter.next().getId()) == getSala(i1)) { //se for a sala destino   
                        Iterator<IPair<ISala, ITunel>> yT = ligacoesDe(getSalaFormiga(i));
                        while (yT.hasNext()) {
                            if (yT.next().getFirst() == getSala(i1)) { //Se a sala destino do pair é igual á sala destino enviada
                                y = yT.next().getSecond();
                                if (y.getRadious() >= getFormiga(i).getCarga()) { //comparar raio do tunel com a carga da formiga
                                    p.entraFormiga(getFormiga(i)); //a formiga entre na sala destino
                                    Formiga ef = (Formiga) getFormiga(i);
                                    while (!ef.getComidaFormiga().isEmpty()) { //enquando tiver comida na formiga
                                        p.acrescentaComida(ef.getComidaFormiga().removeFirst()); //guardo no processamento
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return iter;

        } else if (getSalaFormiga(i) instanceof Silo) { //Se a sala da formiga for um silo
            ISilo s = (ISilo) getSalaFormiga(i);
            for (IFormiga fo : s.listaFormigas()) {//Itero sobre as formigas
                if (fo.getId() == i) {  //Se encontrar a nossa formiga
                    while (fo.getCapacidadeCarga() != fo.getCarga()) {
                        try {
                            fo.addComida(s.iteratorComida().next()); //adiciona comida
                        } catch (FormigaCheiaException ex) {
                            Logger.getLogger(Formigueiro.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    s.saiFormiga(i);
                }
            }
            iter = iteratorShortestPath(getSalaFormiga(i).getId(), i1);

            if (iter != null) {
                while (iter.hasNext()) {
                    if (getSala(iter.next().getId()) == getSala(i1)) { //se for a sala destino    
                        Iterator<IPair<ISala, ITunel>> yT = ligacoesDe(getSalaFormiga(i));
                        while (yT.hasNext()) {
                            if (yT.next().getFirst() == getSala(i1)) {
                                y = yT.next().getSecond();
                                if (y.getRadious() >= getFormiga(i).getCarga()) { //comparar raio do tunel com a carga da formiga
                                    s.entraFormiga(getFormiga(i)); //a formiga entre na sala destino
                                    Formiga ef = (Formiga) getFormiga(i);
                                    while (!ef.getComidaFormiga().isEmpty()) { //enquando tiver comida na formiga
                                        s.guardaComida(ef.getComidaFormiga().removeFirst()); //guardo no silo
                                    }
                                }
                            }
                        }
                    }
                }
                return iter;
            }
        } else { //Se for uma sala normal
            iter = iteratorShortestPath(getSalaFormiga(i).getId(), i1);

            if (iter != null) {
                while (iter.hasNext()) {
                    if (getSala(iter.next().getId()) == getSala(i1)) { //se for a sala destino   
                        Iterator<IPair<ISala, ITunel>> yT = ligacoesDe(getSalaFormiga(i));
                        while (yT.hasNext()) {
                            if (yT.next().getFirst() == getSala(i1)) {
                                y = yT.next().getSecond();
                                if (y.getRadious() >= getFormiga(i).getCarga()) { //comparar raio do tunel com a carga da formiga
                                    getSalaFormiga(i).entraFormiga(getFormiga(i)); //a formiga entra na sala destino, mas fica com a carga
                                }
                            }
                        }
                    }
                    return iter;
                }
            }
        }
        return iter;
    }

    @Override
    public int custoDoCaminho(Iterator<ISala> itrtr) {
        int custoTotal = 0;

        if (itrtr != null) {
            custoTotal += itrtr.next().getX();
        }

        return custoTotal;
    }

    @Override
    public IFormiga criaFormiga(int i, int i1
    ) {
        return new Formiga(i, i1);
    }

    @Override
    public IComida criaComida(int i, int i1
    ) {
        return new Comida(i, i1);
    }

    @Override
    public ISala criaSala(int i, String string,
            int i1, int i2
    ) {
        return new Sala(i, string, i1, i2);
    }

    @Override
    public ISilo criaSilo(int i, String string,
            int i1, int i2
    ) {
        return new Silo(i, string, i1, i2);
    }

    @Override
    public IProcessamento criaProcessamento(int i, String string,
            int i1, int i2
    ) {
        return new Processamento(i, string, i1, i2);
    }

    public NetworkGraph getGrafo() {
        return grafo;
    }

    public void setGrafo(NetworkGraph grafo) {
        this.grafo = grafo;
    }
}
