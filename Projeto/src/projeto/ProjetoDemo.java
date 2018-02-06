/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import Classes.Comida;
import Classes.Formiga;
import Classes.Formigueiro;
import Classes.Processamento;
import Classes.Sala;
import Classes.Silo;
import java.util.Iterator;
import recursos.exceptions.ElementNotFoundException;
import recursos.exceptions.FormigaCheiaException;
import recursos.interfaces.ISala;
import recursos.utils.FormigueiroViewer;

/**
 *
 * @author anaal
 */
public class ProjetoDemo {

    /**
     * @param args the command line arguments
     * @throws recursos.exceptions.FormigaCheiaException
     * @throws recursos.exceptions.ElementNotFoundException
     */
    public static void main(String[] args) throws FormigaCheiaException, ElementNotFoundException {
        Sala sala = new Sala(0, "Sala neutra com id 1", 300, 120);
        Processamento processamento = new Processamento(1, "sala processa", 200, 50);
        Silo silo = new Silo(2, "silo 1 ", 20, 90);
        Sala salaneutra = new Sala(3, "another sala neutra", 100, 150);
        Formiga f1 = new Formiga(1, 3);
        Formiga f2 = new Formiga(2, 5);
        
        Comida c = new Comida(0, 2);
        Comida c2 = new Comida(1, 3);
        Comida c3 = new Comida(2, 2);
        f1.addComida(c);

        silo.guardaComida(c2);
        silo.entraFormiga(f1);
        processamento.acrescentaComida(c3);

        Formigueiro f = new Formigueiro(sala);
        f.addSala(processamento);
        f.addSala(silo);
        f.addSala(salaneutra);

        f.ligaSala(sala, processamento, 2);
        f.ligaSala(processamento, silo, 5);
        f.ligaSala(silo, salaneutra, 4);
        f.ligaSala(sala, salaneutra, 3);
        FormigueiroViewer fv = new FormigueiroViewer(f);
        fv.atualizaFormigueiro(f);

        
        //Iterator<ISala> i = f.iteratorBFS(); //funcionando
        //Iterator<ISala> i = f.iteratorBFS(processamento); //ok
        Iterator<ISala> i =   f.iteratorShortestPath(1, 3);
        f.dijkstra(f.getGrafo().getAdMatrix(), 1);
       // Iterator<ISala> i = f.iterator();
        fv.pintaCaminho(i);
       // System.out.println("shortest path weight:" + f.getGrafo().shortestPathWeight(sala, silo));
        fv.atualizaFormigueiro(f);
       
    }
}
