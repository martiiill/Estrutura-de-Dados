/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.Random;
import recursos.interfaces.ITunel;


public class Tunel implements ITunel {

    private int distance;
    private int radious;
    private int id;

    public Tunel(int distance, int radious) {
        this.distance = distance;
        this.radious = radious;
        Random r = new Random(); //Gera um id aletatorio.
        this.id = r.nextInt(12);
    }

    public Tunel(int radios) {
        this.radious = radios; 
        Random r = new Random(); //Gera um id aletatorio.
        this.id = r.nextInt(13);
        this.distance = 0;
    }
    
      public Tunel() {
        this.radious = 0; 
        Random r = new Random(); //Gera um id aletatorio.
        this.id = r.nextInt(10);
        this.distance = 0; 
    }

    /**
     *
     * @return
     */
    @Override
    public int getDistance() {
        return this.distance;
    }

    /**
     *
     * @param i
     */
    @Override
    public void setDistance(int i) {
        this.distance = i;
    }

    /**
     *
     * @return
     */
    @Override
    public int getRadious() {
        return radious;
    }

    /**
     *
     * @param i
     */
    @Override
    public void setRadious(int i) {
        this.radious = i;
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
        this.id = i;
    }
}
