/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krafo;

import modal.GrafoNaoOrientado;
import modal.GrafoOrientado;

/**
 *
 * @author klok
 */
public class Krafo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        GrafoOrientado g = new GrafoOrientado();
//        GrafoNaoOrientado g = new GrafoNaoOrientado();
        
        g.novoVertice("v1");
        g.novoVertice("v2");
        g.novoVertice("v3");
        
        g.novaLigacao("a", 1, g.obterVertice("v1"), g.obterVertice("v2"));
        g.novaLigacao("b", 3, g.obterVertice("v1"), g.obterVertice("v3"));
        g.novaLigacao("c", 6, g.obterVertice("v2"), g.obterVertice("v3"));
        g.novaLigacao("d", 2, g.obterVertice("v3"), g.obterVertice("v1"));
        g.novaLigacao("e", 1, g.obterVertice("v3"), g.obterVertice("v2"));
        
        imprimeMatrizAdjacencia(g.matrizAdjacencia());
        imprimeMatrizIncidencia(g.matrizIncidencia());
        imprimeMatrizPesos(g.matrizPesos());
        
        g.removerLigacao("c");
        
        imprimeMatrizAdjacencia(g.matrizAdjacencia());
        imprimeMatrizIncidencia(g.matrizIncidencia());
        imprimeMatrizPesos(g.matrizPesos());
    }
    
    private static void imprimeMatrizAdjacencia(int[][] matriz) {
        System.out.println("Matriz de Adjacencia\n");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if ( i == j ) {
                    System.out.print("x ");
                } else {
                    System.out.print( matriz[i][j] + " " );
                }
            }
            System.out.print("\n");
        }
    }
    
    private static void imprimeMatrizIncidencia(int[][] matriz) {
        System.out.println("Matriz de Incidencia\n");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print( matriz[i][j] + " " );
            }
            System.out.print("\n");
        }
    }
    
    private static void imprimeMatrizPesos(int[][] matriz) {
        System.out.println("Matriz de Pesos\n");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if ( i == j ) {
                    System.out.print("x ");
                } else {
                    System.out.print( matriz[i][j] + " " );
                }
            }
            System.out.print("\n");
        }
    }
    
}
