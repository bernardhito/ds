package heranca;
public class Bola {
    private String cor;
    private int tamanho;
    public Bola (String cor, int tamanho){
        this.cor = cor;
        this.tamanho = tamanho;
    }
    public void mostrar (){
        System.out.println(cor);
        System.out.println(tamanho);
    }
}
