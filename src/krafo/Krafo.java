/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krafo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import modal.Arco;
import modal.Aresta;
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
    
//    static GrafoOrientado g;
    static GrafoNaoOrientado g;
    
    public static void main(String[] args) throws IOException {
//        g = new GrafoOrientado();
        g = new GrafoNaoOrientado();
        
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
//        imprimeMatrizPesos(g.matrizPesos());
        
        imprimiAdjacente("v1", "v4");
        imprimiValor("c");
        imprimeExtremidade("c");
        
//        g.removerLigacao("c");
        g.removerVertice("v1");
        
        imprimeMatrizAdjacencia(g.matrizAdjacencia());
        imprimeMatrizIncidencia(g.matrizIncidencia());
//        imprimeMatrizPesos(g.matrizPesos());
        
//        Scanner ler = new Scanner(System.in);
//        boolean continuar = true;
//        int opcao = 0;
//        
//        while (continuar) {
//            System.out.println("Krafo");
//            System.out.println("");
//            System.out.println("1 - Grafo Não Orientado");
//            System.out.println("2 - Grafo Orientado");
//            System.out.println("3 - Sair");
//            opcao = ler.nextInt();
//            
//            switch(opcao){
//                case 1:
//                    novoGrafoNaoOrientado();
//                    break;
//                    
//                case 2:
//                    novoGrafoOrientado();
//                    break;
//                    
//                case 3:
//                    continuar = false;
//                    break;
//                    
//                default:
//                    System.out.println("Opcao Invalida");
//                    System.in.read();
//            }
//        }
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
    
    private static void limparTela() throws IOException, InterruptedException{
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            Runtime.getRuntime().exec("clear");
    }

    private static void novoGrafoNaoOrientado() throws IOException {
        Scanner ler = new Scanner(System.in);
        boolean continuar = true;
        int opcao = 0;
        
        String nome = "";
        int valor = 0;
        String v1 = "";
        String v2 = "";
        
        GrafoNaoOrientado g = new GrafoNaoOrientado();
        
        while (continuar){
            System.out.println("Grafo Não Orientado");
            System.out.println("");
            System.out.println("1 - Adicionar Vertice");
            System.out.println("2 - Adicionar Aresta");
            System.out.println("3 - Remover Vertice");
            System.out.println("4 - Remover Aresta");
            System.out.println("");
            System.out.println("5 - Matriz Adjacencia");
            System.out.println("6 - Matriz Incidencia");
            System.out.println("7 - PRIM");
            System.out.println("8 - Busca em Largura");
            System.out.println("9 - Busca em Profundidade");
            System.out.println("10 - Algoritmo Roy");
            System.out.println("11 - Voltar");
            opcao = ler.nextInt();
            
            switch(opcao){
                case 1:
                    System.out.print("Label vertice: ");
                    nome = ler.next();
                    g.novoVertice(nome);
                    break;
                    
                case 2:
                    System.out.print("Label aresta: ");
                    nome = ler.next();
                    System.out.print("Valor Aresta: ");
                    valor = ler.nextInt();
                    System.out.print("Vertice 1: ");
                    v1 = ler.next();
                    System.out.print("Vertice 2: ");
                    v2 = ler.next();
                    
                    g.novaLigacao(nome, valor, g.obterVertice(v1), g.obterVertice(v2));
                    break;
                    
                case 3:
                    System.out.print("Vertice: ");
                    nome = ler.next();
                    g.removerVertice(nome);
                    break;
                    
                case 4:
                    System.out.print("Aresta: ");
                    nome = ler.next();
                    g.removerLigacao(nome);
                    break;
                    
                case 5:
                    imprimeMatrizAdjacencia(g.matrizAdjacencia());
                    System.in.read();
                    break;
                    
                case 6:
                    imprimeMatrizIncidencia(g.matrizIncidencia());
                    System.in.read();
                    break;
                    
                case 7:
                    System.out.print("Vertice inicial: ");
                    nome = ler.next();
                    ArrayList<Aresta> arvoreMin = g.PRIM(g.obterVertice(nome));
                    int somaArvore = 0;
                    System.out.println("PRIM");
                    for (Aresta aresta : arvoreMin) {
                        System.out.println("Arestas: " + aresta.obterRotulo());
                        somaArvore += aresta.obterValor();
                    }
                    System.out.println("Soma: " + somaArvore);
                    System.in.read();
                    break;
                    
                case 8:
                    System.out.print("Vertice inicial: ");
                    nome = ler.next();
                    ArrayList<Aresta> BFS = g.buscaEmLargura(g.obterVertice(nome));
        
                    for (Aresta aresta : BFS) {
                        System.out.println(aresta.obterRotulo());
                    }
                    System.in.read();
                    break;
                    
                case 9:
                    System.out.println("Busca em Profundidade não implementado!");
                    System.in.read();
                    break;
                
                case 10:
                    System.out.println("Algoritmo Roy não implementado!");
                    System.in.read();
                    break;
                    
                case 11:
                    continuar = false;
                    break;
                    
                default:
                    System.out.println("Opcao Invalida!");
                    System.in.read();
                    
            }
        }
    }

    private static void novoGrafoOrientado() throws IOException {
        Scanner ler = new Scanner(System.in);
        boolean continuar = true;
        int opcao = 0;
        
        String nome = "";
        int valor = 0;
        String v1 = "";
        String v2 = "";
        
        GrafoOrientado g = new GrafoOrientado();
        
        while (continuar){
            System.out.println("Grafo Orientado");
            System.out.println("");
            System.out.println("1 - Adicionar Vertice");
            System.out.println("2 - Adicionar Arco");
            System.out.println("3 - Remover Vertice");
            System.out.println("4 - Remover Arco");
            System.out.println("5 - Matriz Adjacencia");
            System.out.println("6 - Matriz Incidencia");
            System.out.println("7 - Busca em Largura");
            System.out.println("8 - Busca em Profundidade");
            System.out.println("9 - Algoritmo Roy");
            System.out.println("10 - Sair");
            opcao = ler.nextInt();
            
            switch(opcao){
                case 1:
                    System.out.print("Label vertice: ");
                    nome = ler.next();
                    g.novoVertice(nome);
                    break;
                    
                case 2:
                    System.out.print("Label Arco: ");
                    nome = ler.next();
                    System.out.print("Valor Arco: ");
                    valor = ler.nextInt();
                    System.out.print("Vertice 1: ");
                    v1 = ler.next();
                    System.out.print("Vertice 2: ");
                    v2 = ler.next();
                    
                    g.novaLigacao(nome, valor, g.obterVertice(v1), g.obterVertice(v2));
                    break;
                    
                case 3:
                    System.out.print("Vertice: ");
                    nome = ler.next();
                    g.removerVertice(nome);
                    break;
                    
                case 4:
                    System.out.print("Aresta: ");
                    nome = ler.next();
                    g.removerLigacao(nome);
                    break;
                    
                case 5:
                    imprimeMatrizAdjacencia(g.matrizAdjacencia());
                    System.in.read();
                    break;
                    
                case 6:
                    imprimeMatrizIncidencia(g.matrizIncidencia());
                    System.in.read();
                    break;
                    
                case 7:
                    System.out.print("Vertice inicial: ");
                    nome = ler.next();
                    ArrayList<Arco> BFS = g.buscaEmLargura(g.obterVertice(nome));
        
                    for (Arco arco : BFS) {
                        System.out.println(arco.obterRotulo());
                    }
                    System.in.read();
                    break;
                    
                case 8:
                    System.out.println("Busca em Profundidade não implementado!");
                    System.in.read();
                    break;
                
                case 9:
                    System.out.println("Algoritmo Roy não implementado!");
                    System.in.read();
                    break;
                    
                case 10:
                    continuar = false;
                    break;
                    
                default:
                    System.out.println("Opcao Invalida!");
                    System.in.read();                    
            }
        }
    }
}
