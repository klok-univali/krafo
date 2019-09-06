/**
 * @author Gabriel Hegler Klok
 * @since 2019/09
 */
package modal;

public class Ligacao {
    private String rotulo;
    private int valor;

    public Ligacao(String rotulo, int valor) {
        this.rotulo = rotulo;
        this.valor = valor;
    }
    
    public String obterRotulo(){
        return this.rotulo;
    }
    
    public int obterValor(){
        return this.valor;
    }
}
