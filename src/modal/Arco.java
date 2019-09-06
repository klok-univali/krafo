/**
 * @author Gabriel Hegler Klok
 * @since 2019/09
 */
package modal;

public class Arco extends Ligacao{
    private Vertice origem;
    private Vertice destino;

    public Arco(String rotulo, int valor, Vertice origem, Vertice destino) {
        super(rotulo, valor);
        this.origem = origem;
        this.destino = destino;
    }
    
    public Vertice obterOrigem(){
        return this.origem;
    }
    
    public Vertice obterDestino(){
        return this.destino;
    }
}
