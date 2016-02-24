/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siecineuronowe;

import com.my.lin.neuron.Perceptron;

/**
 *
 * @author serv
 */
public class SieciNeuronowe {

    
    public static void main(String[] args) {
     
        Perceptron p= new Perceptron(3); // wektor 
        
      
        double [][] and={{1,-1,-1,-1},{1,-1,1,-1},{1,1,-1,-1},{1,1,1,1}};
        
        p.trenujNeuron(and);
    
        System.out.print(p.toString());
        p.drawNeuron();
        
    }
    
}
