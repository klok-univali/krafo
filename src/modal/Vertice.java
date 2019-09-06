/**
 * @author Gabriel Hegler Klok
 * @since 2019/09
 */
package modal;

public class Vertice {
    private String rotulo;

    public Vertice(String rotulo) {
        this.rotulo = rotulo;
    }
    
    public String obterRotulo(){
        return this.rotulo;
    }
}
