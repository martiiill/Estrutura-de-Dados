/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.Serializable;
import java.util.Iterator;
import recursos.exceptions.EmptyCollectionException;
import recursos.exceptions.ProcessedException;
import recursos.interfaces.IComida;
import recursos.interfaces.IProcessamento;

/**
 *
 * @author anaal
 */
public class Processamento extends Sala implements IProcessamento, Serializable {

    private LinkedQueue<IComida> comida;

    /**
     *
     * @param id
     * @param descricao
     * @param x
     * @param y
     */
    public Processamento(int id, String descricao, int x, int y) {
        super(id, descricao, x, y);
        this.comida = new LinkedQueue<>();
    }

    /**
     *
     * @param ic
     */
    @Override
    public void acrescentaComida(IComida ic) {
        comida.enqueue(ic);
    }

    /**
     *
     * @return @throws EmptyCollectionException
     * @throws ProcessedException
     */
    @Override
    public IComida getProximaComida() throws EmptyCollectionException, ProcessedException {
        IComida comi;

        if (comida.isEmpty()) {
            throw new EmptyCollectionException("Colecao de comidas vazia");
        }

        if (this.comida.first().getTamanho() == 1) {
            return (IComida) this.comida.dequeue();
        } else {
            int t = this.comida.first().getTamanho();
            //retira a comida com um tamanho maior que 1 da queue.
            this.comida.dequeue();
            //ciclo que acrescenta comida com tamanho 1
            for (int i = 0; i < t; i++) {
                comi = new Comida(1);
                acrescentaComida(comi);
            }
        }
        if (comida.dequeue().getTamanho() > 1) {
            throw new ProcessedException(comida.first());
        } else {
            return comida.dequeue();
        }
    }

    /**
     *
     * @return
     */
    @Override
    public Iterator<IComida> iteratorComida() {
        UnorderedList<IComida> itcm = new UnorderedList<>();

        while (!comida.isEmpty()) { //Enquanto houver comida, adiciona á lista.
            itcm.addToRear((Comida) comida.dequeue());
        }
        return itcm.iterator();
    }

    public LinkedQueue<IComida> getComida() {
        return comida;
    }

    public void setComida(LinkedQueue<IComida> comida) {
        this.comida = comida;
    }
}
