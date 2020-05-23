/**
 * @author Gabriel Hegler Klok
 * @since 2019/09
 */
package modal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class GrafoNaoOrientado extends Grafo implements GrafoInterface{
    private ArrayList<Aresta> arestas;

    public GrafoNaoOrientado() {
        super();
        this.arestas = new ArrayList<>();
    }

    @Override
    public void novoVertice(String rotulo) {
        if ( ! existeVertice(rotulo) ) {
            Vertice v = new Vertice(rotulo);
            ArrayList<Vertice> vs = new ArrayList<>();
            vs.add(v);
            this.vertices.add(vs);
        }
    }

    @Override
    public void novaLigacao(String ligacao, int peso, Vertice v1, Vertice v2) {
        if ( ! existeAresta(ligacao) ) {
            Aresta a = new Aresta(ligacao, peso, new ArrayList<>(Arrays.asList(v1, v2)));
            this.arestas.add(a);
            
            int x = indiceVertice(v1.obterRotulo());
            ArrayList<Vertice> vs = this.vertices.get(x);
            vs.add(v2);
            this.vertices.set(x, vs);
            
            x = indiceVertice(v2.obterRotulo());
            vs = this.vertices.get(x);
            vs.add(v1);
            this.vertices.set(x, vs);
        }
    }

    @Override
    public void removerVertice(String rotulo) {   
        if ( existeVertice(rotulo) ) {
            ArrayList<Vertice> vTmp = new ArrayList<>();
            
            this.arestas.removeIf( n -> (n.obterExtremidades().get(0).obterRotulo().equalsIgnoreCase(rotulo) || n.obterExtremidades().get(1).obterRotulo().equalsIgnoreCase(rotulo) ) );
            this.vertices.remove(indiceVertice(rotulo));
            for (int i = 0; i < this.vertices.size(); i++) {
                vTmp = this.vertices.get(i);
                vTmp.removeIf(n -> n.obterRotulo().equalsIgnoreCase(rotulo));
                this.vertices.set(i, vTmp);
            }
        }
    }

    @Override
    public void removerLigacao(String rotulo) {
        if ( existeAresta(rotulo )) {
            Aresta a = buscaAresta(rotulo);
            this.arestas.remove(a);
            
            int x = indiceVertice(a.obterExtremidades().get(0).obterRotulo());
            ArrayList<Vertice> vs = this.vertices.get(x);
            vs.remove(a.obterExtremidades().get(1));
            this.vertices.set(x, vs);
            
            x = indiceVertice(a.obterExtremidades().get(1).obterRotulo());
            vs = this.vertices.get(x);
            vs.remove(a.obterExtremidades().get(0));
            this.vertices.set(x, vs);
        }
    }

    @Override
    public boolean verificaAdjacenciaVertices(String v1, String v2) {
        if ( existeVertice(v1) && existeAresta(v2) ) {
            for (Aresta aresta : this.arestas) {
                if ( aresta.obterExtremidades().get(0).obterRotulo().equalsIgnoreCase(v1) && aresta.obterExtremidades().get(1).obterRotulo().equalsIgnoreCase(v2) ) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int valorLigacao(String rotulo) {
        if ( existeAresta(rotulo) ) {
            return buscaAresta(rotulo).obterValor();
        }
        return 0;
    }

    @Override
    public ArrayList<Vertice> extremidadesLigacao(String rotulo) {
        if ( existeAresta(rotulo) ) {
            return buscaAresta(rotulo).obterExtremidades();
        }
        return new ArrayList<Vertice>();
    }

    @Override
    public int[][] matrizAdjacencia() {
        int size = this.vertices.size();
        int[][] matriz = new int[size][size];
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matriz[i][j] = 0;
            }
        }
        
        for (int i = 0; i < size; i++) {
            for (int j = 1; j < this.vertices.get(i).size(); j++) {
                matriz[i][ indiceVertice(this.vertices.get(i).get(j).obterRotulo()) ] = 1;
            }
        }
        
        return matriz;
    }

    @Override
    public int[][] matrizIncidencia() {
        int row = this.vertices.size();
        int column = this.arestas.size();
        int[][] matriz = new int[row][column];
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                matriz[i][j] = 0;
            }
        }
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                matriz[i][j] = incidencia(this.vertices.get(i).get(0), this.arestas.get(j));
            }
        }
        
        return matriz;
    }
    
    @Override
    public int[][] matrizPesos(){
        int size = this.vertices.size();
        int[][] matriz = new int[size][size];
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matriz[i][j] = 0;
            }
        }
        
        for (int i = 0; i < size; i++) {
            for (int j = 1; j < this.vertices.get(i).size(); j++) {
                matriz[i][ indiceVertice(this.vertices.get(i).get(j).obterRotulo()) ] = buscaAresta(this.vertices.get(i).get(0), this.vertices.get(i).get(j)).obterValor() ;
            }
        }
        
        return matriz;
    }
    
    
    public ArrayList<Aresta> buscaEmLargura( Vertice v ){
        LinkedList<Vertice> fila = new LinkedList<>();
        ArrayList<Vertice> marcados = new ArrayList<>();
        ArrayList<Aresta> arvore = new ArrayList<>();
        ArrayList<Vertice> ts;
        
        marcados.add(v);
        fila.add(v);
        
        while ( ! fila.isEmpty() ) {
            v = fila.poll();
            ts = buscaAdjacentes(v);
            
            for (Vertice t : ts) {
                if ( ! marcados.contains(t) ) {
                    arvore.add( buscaAresta(v, t) );
                    fila.add(t);
                    marcados.add(t);
                } else {
                    if ( arvore.contains( buscaAresta(v, t) ) ){
                        arvore.add( buscaAresta(v, t) );
                    }
                }
            }
        }
        
        return arvore;
    }
    
    
    public ArrayList<Aresta> buscaEmProfundidade(Vertice v){
        Stack pilha = new Stack();
        ArrayList<Vertice> marcados = new ArrayList<>();
        ArrayList<Aresta> arvore = new ArrayList<>();
        ArrayList<Vertice> ts;
        
        marcados.add(v);
        
        while ( ! pilha.empty() ){
            ts = buscaAdjacentes(v);
            pilha.push(ts.get(1));
                       
            if ( ! marcados.contains(ts.get(1)) ){
                arvore.add( buscaAresta(v, ts.get(1)) );
                marcados.add(ts.get(1));
                buscaEmProfundidade(ts.get(1));
            } else {
                if (! arvore.contains(buscaAresta(v, ts.get(0)))){
                    arvore.add(buscaAresta(v, ts.get(0)));
                }
            }
        }
        
        return arvore;
    }
    
    
    private boolean existeAresta(String rotulo) {
        for (Aresta aresta : this.arestas) {
            if ( aresta.obterRotulo().equalsIgnoreCase(rotulo) ) {
                return true;
            }
        }
        return false;
    }
   
    private Aresta buscaAresta(String rotulo) {
        for (Aresta aresta : this.arestas) {
            if ( aresta.obterRotulo().equalsIgnoreCase(rotulo) ) {
                return aresta;
            }
        }
        return null;
    }
    
    private Aresta buscaAresta(Vertice v1, Vertice v2) {
        ArrayList<Vertice> vs;
        for (Aresta aresta : this.arestas) {
            vs = aresta.obterExtremidades();
            if ( (vs.get(0).obterRotulo().equalsIgnoreCase(v1.obterRotulo()) && vs.get(1).obterRotulo().equalsIgnoreCase(v2.obterRotulo())) ||
                   (vs.get(0).obterRotulo().equalsIgnoreCase(v2.obterRotulo()) && vs.get(1).obterRotulo().equalsIgnoreCase(v1.obterRotulo())) ) {
                return aresta;
            }
        }
        return null;
    }
    
    private ArrayList<Vertice> buscaArestas(Vertice v) {
        ArrayList<Vertice> adjacentes = new ArrayList<>();
        ArrayList<Vertice> vs;
        for (Aresta aresta : this.arestas) {
            vs = aresta.obterExtremidades();
            if ( vs.get(0).obterRotulo().equalsIgnoreCase(v.obterRotulo()) ) {
                adjacentes.add(vs.get(0));
                continue;
            }
            if ( vs.get(1).obterRotulo().equalsIgnoreCase(v.obterRotulo()) ) {
                adjacentes.add(vs.get(1));
            }
        }
        return adjacentes;
    }
    
    private ArrayList<Vertice> buscaAdjacentes(Vertice v) {
        ArrayList<Vertice> vs = new ArrayList<>();
        for (ArrayList<Vertice> vertice : this.vertices) {
            if ( vertice.get(0).obterRotulo().equalsIgnoreCase(v.obterRotulo()) ) {
                return vertice;
            }
        }
        return vs;
    }
    
    private int incidencia(Vertice v, Aresta a) {
        if ( a.obterExtremidades().get(0).obterRotulo().equalsIgnoreCase(v.obterRotulo()) || 
                a.obterExtremidades().get(1).obterRotulo().equalsIgnoreCase(v.obterRotulo()) ) {
            return 1;
        }
        return 0;
    }
    
    private ArrayList<Vertice> obterVertices(){
        ArrayList<Vertice> verticesTmp = new ArrayList<>();
        
        for (ArrayList<Vertice> vertice : this.vertices) {
            verticesTmp.add(vertice.get(0));
        }
        
        return verticesTmp;
    }
    
    private boolean pertence(Vertice v, ArrayList<Vertice> listaVertices){
        for (Vertice vertice : listaVertices) {
            if ( v.obterRotulo().equals(vertice.obterRotulo()) ) {
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<Aresta> PRIM(Vertice vInicial){
        ArrayList<Vertice> visitados = new ArrayList<>();
        ArrayList<Vertice> naoVisitados = obterVertices();
        ArrayList<Vertice> extremidadesAresta = null;
        ArrayList<Aresta> arvoreMinima = new ArrayList<>();
        Aresta arestaMinima = null;
        int pesoMinimo;
        
        visitados.add(naoVisitados.get(0));
        naoVisitados.remove(0);
        
        while (! naoVisitados.isEmpty()){
            pesoMinimo = 999999;
            
            for (Aresta aresta : this.arestas) {                
                extremidadesAresta = aresta.obterExtremidades();

                if ( ((pertence(extremidadesAresta.get(0), visitados) && pertence(extremidadesAresta.get(1), naoVisitados)) ||
                        (pertence(extremidadesAresta.get(1), visitados) && pertence(extremidadesAresta.get(0), naoVisitados)) ) && 
                        pesoMinimo > aresta.obterValor() ) {
                    pesoMinimo = aresta.obterValor();
                    arestaMinima = aresta;
                }
            }
            
            extremidadesAresta = arestaMinima.obterExtremidades();
            
            if ( ! pertence(extremidadesAresta.get(0), visitados) ) {
                visitados.add( extremidadesAresta.get(0) );
                naoVisitados.remove( extremidadesAresta.get(0) );
            }
            
            if ( ! pertence(extremidadesAresta.get(1), visitados) ) {
                visitados.add( extremidadesAresta.get(1) );
                naoVisitados.remove( extremidadesAresta.get(1) );
            }
            
            arvoreMinima.add(arestaMinima);
        }
        
        return arvoreMinima;
    }
    
}
