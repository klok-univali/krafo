/**
 * @author Gabriel Hegler Klok
 * @since 2019/09
 */
package modal;

import java.util.ArrayList;
import java.util.Arrays;

public class GrafoNaoOrientado extends Grafo implements GrafoInterface{
    private ArrayList<Aresta> arestas;

    public GrafoNaoOrientado() {
        super();
        this.arestas = new ArrayList<>();
    }

    @Override
    public void novoVertice(String rotulo) {
        if ( ! exiteVertice(rotulo) ) {
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
        if ( exiteVertice(rotulo) ) {
            ArrayList<Aresta> as = this.arestas;
            ArrayList<Vertice> vs = null;
            for (Aresta aresta : as) {
                vs = aresta.obterExtremidades();
                if ( vs.get(0).obterRotulo().equalsIgnoreCase(rotulo) || vs.get(1).obterRotulo().equalsIgnoreCase(rotulo) ) {
                    this.arestas.remove(aresta);
                }
            }
            this.vertices.remove(indiceVertice(rotulo));
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
        if ( exiteVertice(v1) && existeAresta(v2) ) {
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
    
    private int incidencia(Vertice v, Aresta a) {
        if ( a.obterExtremidades().get(0).obterRotulo().equalsIgnoreCase(v.obterRotulo()) || 
                a.obterExtremidades().get(1).obterRotulo().equalsIgnoreCase(v.obterRotulo()) ) {
            return 1;
        }
        return 0;
    }
}
