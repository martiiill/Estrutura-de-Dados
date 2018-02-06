/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.Random;
import recursos.interfaces.IComida;

/**
 * Classe que modela a comida.
 */
public class Comida implements IComida{
    
    private int id;
    private int tamanho;

    /**
     *
     * @param id
     * @param tamanho
     */
    public Comida(int id, int tamanho) {
        this.id = id;
        this.tamanho = tamanho;
    }
    
      public Comida(int tamanho) {
        this.id = new Random().nextInt(22); //Gera um id aleatorio
        this.tamanho = tamanho;
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * 
     * @param i 
     */
    @Override
    public void setId(int i) {
       this.id =i;
    }

    /**
     * 
     * @return 
     */
    @Override
    public int getTamanho() {
       return tamanho;
    }

    /**
     * 
     * @param i 
     */
    @Override
    public void setTamanho(int i) {
       this.tamanho = i;
    }
    
}
