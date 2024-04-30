package heranca;

public class Funcionario extends PessoaFisica{
    private String cartao;
    public void setCartao(String cartao){
        this.cartao = cartao;
    }
    public String getCartao(){
        return (cartao);
    }
}