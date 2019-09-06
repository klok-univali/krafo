/**
 * @author Gabriel Hegler Klok
 * @since 2019/09
 */
package modal;

import java.util.ArrayList;

public class Aresta extends Ligacao{
    private ArrayList<Vertice> extremidades;

    public Aresta(String rotulo, int valor, ArrayList<Vertice> extremidades) {
        super(rotulo, valor);
        this.extremidades = extremidades;
    }
    
    public ArrayList<Vertice> obterExtremidades(){
        return this.extremidades;
    }    
}
