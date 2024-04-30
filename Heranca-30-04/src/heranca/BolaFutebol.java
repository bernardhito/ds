package heranca;

public class BolaFutebol extends Bola{
    private String tipo;
    public BolaFutebol(String cor, int tamanho, String tipo){
        super(cor, tamanho);
        this.tipo = tipo;
    }
    public void mostrar(){
        super.mostrar();
        System.out.println(tipo);
    }
}