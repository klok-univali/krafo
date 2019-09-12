/**
 * @author Gabriel Hegler Klok
 * @since 2019/09
 */
package modal;

import java.util.ArrayList;

public class GrafoOrientado extends Grafo implements GrafoInterface {
    private ArrayList<Arco> arcos;

    public GrafoOrientado() {
        super();
        this.arcos = new ArrayList<>();
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
    public void novaLigacao(String ligacao, int valor, Vertice origem, Vertice destino) {
        if ( ! existeArco(ligacao) ) {
            Arco a = new Arco(ligacao, valor, origem, destino);
            this.arcos.add(a);
            int x = indiceVertice(origem.obterRotulo());
            ArrayList<Vertice> vs = this.vertices.get(x);
            vs.add(destino);
            this.vertices.set(x, vs);
        }
    }

    @Override
    public void removerVertice(String rotulo) {
        if ( exiteVertice(rotulo) ) {  
            ArrayList<Arco> as = this.arcos;
            for (Arco arco : as) {
                if ( arco.obterOrigem().obterRotulo().equalsIgnoreCase(rotulo) || arco.obterDestino().obterRotulo().equalsIgnoreCase(rotulo) ) {
                    this.arcos.remove(arco);
                }
            }
            this.vertices.remove(indiceVertice(rotulo));
        }
    }

    @Override
    public void removerLigacao(String rotulo) {
        if ( existeArco(rotulo) ) {
            Arco a = buscaArco(rotulo);
            this.arcos.remove(a);
            int x = indiceVertice(a.obterOrigem().obterRotulo());
            ArrayList<Vertice> vs = this.vertices.get(x);
            vs.remove(a.obterDestino());
            this.vertices.set(x, vs);
        }
    }

    @Override
    public boolean verificaAdjacenciaVertices(String v1, String v2) {
        if ( exiteVertice(v1) && exiteVertice(v2) ) {
            for (Arco arco : this.arcos) {
                if ( arco.obterOrigem().obterRotulo().equalsIgnoreCase(v1) && arco.obterDestino().obterRotulo().equalsIgnoreCase(v2) ) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int valorLigacao(String rotulo) {
        if ( existeArco(rotulo) ) {
            return buscaArco(rotulo).obterValor();
        }
        return 0;
    }

    @Override
    public ArrayList<Vertice> extremidadesLigacao(String rotulo) {
        ArrayList<Vertice> extremidades = new ArrayList<>();
        if ( existeArco(rotulo) ) {
            Arco a = buscaArco(rotulo);
            extremidades.add(a.obterOrigem());
            extremidades.add(a.obterDestino());
        }
        return extremidades;
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
            for (int j = 1; j < this.vertices.get(i).size() ; j++) {
                matriz[i][ indiceVertice(this.vertices.get(i).get(j).obterRotulo()) ] = 1;
            }
        }
        
        return matriz;
    }

    @Override
    public int[][] matrizIncidencia() {
        int row = this.vertices.size();
        int column = this.arcos.size();
        int[][] matriz = new int[row][column];
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                matriz[i][j] = 0;
            }
        }
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                matriz[i][j] = incidencia(this.vertices.get(i).get(0), this.arcos.get(j));
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
                matriz[i][ indiceVertice(this.vertices.get(i).get(j).obterRotulo()) ] = buscaArco(this.vertices.get(i).get(0), this.vertices.get(i).get(j)).obterValor() ;
            }
        }
        
        return matriz;
    }
    
    private boolean existeArco(String rotulo){
        for (Arco arco : this.arcos) {
            if ( arco.obterRotulo().equalsIgnoreCase(rotulo) ) {
                return true;
            }
        }
        return false;
    }
    
    private Arco buscaArco(String rotulo){
        for (Arco arco : this.arcos) {
            if ( arco.obterRotulo().equalsIgnoreCase(rotulo) ) {
                return arco;
            }
        }
        return null;
    }
    
    private Arco buscaArco(Vertice v1, Vertice v2){
        for (Arco arco : this.arcos) {
            if ( arco.obterOrigem().obterRotulo().equalsIgnoreCase(v1.obterRotulo()) && arco.obterDestino().obterRotulo().equalsIgnoreCase(v2.obterRotulo()) ) {
                return arco;
            }
        }
        return null;
    }
    
    private int incidencia(Vertice v, Arco a){
        if ( a.obterOrigem().obterRotulo().equalsIgnoreCase(v.obterRotulo()) ) {
            return 1;
        }
        if ( a.obterDestino().obterRotulo().equalsIgnoreCase(v.obterRotulo()) ) {
            return -1;
        }
        return 0;
    }    
}
