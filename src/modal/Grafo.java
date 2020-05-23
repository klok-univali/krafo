/**
 * @author Gabriel Hegler Klok
 * @since 2019/09
 */
package modal;

import java.util.ArrayList;

public class Grafo {
    protected ArrayList<ArrayList<Vertice>> vertices;

    public Grafo() {
        this.vertices = new ArrayList<>();
    }
    
    public boolean existeVertice(String rotulo){
        for (ArrayList<Vertice> vertice : this.vertices) {
            if ( vertice.get(0).obterRotulo().equalsIgnoreCase(rotulo) ) {
                return true;
            }
        }
        return false;
    }
    
    public Vertice obterVertice(String rotulo){
        if ( existeVertice(rotulo) ) {
            return this.vertices.get( indiceVertice(rotulo) ).get(0);
        }
        return null;
    }
    
    protected int indiceVertice(String rotulo){
        for (int i = 0; i < this.vertices.size(); i++) {
            if ( this.vertices.get(i).get(0).obterRotulo().equalsIgnoreCase(rotulo) ) {
                return i;
            }
        }
        return -1;
    } 
}
