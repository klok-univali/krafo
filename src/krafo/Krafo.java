/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krafo;

import java.util.ArrayList;
import modal.GrafoNaoOrientado;
import modal.GrafoOrientado;
import modal.Vertice;

/**
 *
 * @author klok
 */
public class Krafo {

    /**
     * @param args the command line arguments
     */
    
    static GrafoOrientado g;
//    static GrafoNaoOrientado g;
    
    public static void main(String[] args) {
        
        g = new GrafoOrientado();
//      g = new GrafoNaoOrientado();
        
        g.novoVertice("v1");
        g.novoVertice("v2");
        g.novoVertice("v3");
        g.novoVertice("v4");
        
        g.novaLigacao("a", 1, g.obterVertice("v1"), g.obterVertice("v2"));
        g.novaLigacao("b", 3, g.obterVertice("v1"), g.obterVertice("v3"));
        g.novaLigacao("c", 6, g.obterVertice("v2"), g.obterVertice("v3"));
        g.novaLigacao("d", 2, g.obterVertice("v3"), g.obterVertice("v1"));
        g.novaLigacao("e", 1, g.obterVertice("v3"), g.obterVertice("v2"));
                
        imprimeMatrizAdjacencia(g.matrizAdjacencia());
        imprimeMatrizIncidencia(g.matrizIncidencia());
        imprimeMatrizPesos(g.matrizPesos());
        
        imprimiAdjacente("v1", "v4");
        imprimiValor("c");
        imprimeExtremidade("c");
        
//        g.removerLigacao("c");
        g.removerVertice("v1");
        
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
    
    private static void imprimiAdjacente(String v1, String v2) {
        if ( g.verificaAdjacenciaVertices(v1, v2) ) {
            System.out.println("Os vertices " + v1 + " e " + v2 + " são adjacentes.");
        } else {
            System.out.println("Os vertices " + v1 + " e " + v2 + " não são adjacentes.");
        }
    }
    
    private static void imprimiValor(String a){
        System.out.println("O valor da ligação " + a + " é " + g.valorLigacao(a));
    }
    
    private static void imprimeExtremidade(String a) {
        ArrayList<Vertice> e = g.extremidadesLigacao(a);
        System.out.println("As extremidades da ligação " + a + " são: " + e.get(0).obterRotulo() + " e " + e.get(1).obterRotulo());
    }
}
