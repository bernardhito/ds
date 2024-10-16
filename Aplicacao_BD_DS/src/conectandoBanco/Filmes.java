package conectandoBanco;

public class Filmes {
    private String codigo; // Código único do filme
    private String genero; // Gênero do filme (ex: ação, comédia, etc.)
    private String titulo; // Título do filme
    private String produtora; // Nome da produtora do filme
    private String dataCompra; // Data em que o filme foi comprado

    // Método para obter o código do filme
    public String getCodigo() {
        return codigo;
    }

    // Método para definir o código do filme
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    // Método para obter o gênero do filme
    public String getGenero() {
        return genero;
    }

    // Método para definir o gênero do filme
    public void setGenero(String genero) {
        this.genero = genero;
    }

    // Método para obter o título do filme
    public String getTitulo() {
        return titulo;
    }

    // Método para definir o título do filme
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    // Método para obter o nome da produtora do filme
    public String getProdutora() {
        return produtora;
    }

    // Método para definir o nome da produtora do filme
    public void setProdutora(String produtora) {
        this.produtora = produtora;
    }

    // Método para obter a data de compra do filme
    public String getDataCompra() {
        return dataCompra;
    }

    // Método para definir a data de compra do filme
    public void setDataCompra(String dataCompra) {
        this.dataCompra = dataCompra;
    }
}
