/**
 * @author Gabriel Hegler Klok
 * @since 2019/09
 */
package modal;

import java.util.ArrayList;

public interface GrafoInterface {    
    public void novoVertice(String rotulo);
    public void novaLigacao(String ligacao, int peso, Vertice v1, Vertice v2);
    public void removerVertice(String rotulo);
    public void removerLigacao(String rotulo);
    public boolean verificaAdjacenciaVertices(String v1, String v2);
    public int valorLigacao(String rotulo);
    public ArrayList<Vertice> extremidadesLigacao(String rotulo);
    public int[][] matrizAdjacencia();
    public int[][] matrizIncidencia();
}
