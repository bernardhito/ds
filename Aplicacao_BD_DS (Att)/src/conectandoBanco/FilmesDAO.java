package conectandoBanco;

import java.sql.*;

public class FilmesDAO {
    public Filmes filme; // Objeto que representa um filme
    public BD bd; // Objeto que gerencia a conexão com o banco de dados
    private PreparedStatement statement; // Usado para executar consultas SQL
    private ResultSet resultSet; // Armazena o resultado de uma consulta SQL
    private String men, sql; // Variáveis para mensagens e comandos SQL

    // Constantes para operações de CRUD (Create, Read, Update, Delete)
    public static final byte INCLUSAO = 1;
    public static final byte ALTERACAO = 2;
    public static final byte EXCLUSAO = 3;

    // Construtor que inicializa os objetos de conexão e filme
    public FilmesDAO() {
        bd = new BD();
        filme = new Filmes();
    }

    // Método para localizar um filme pelo código
    public boolean localizar() {
        sql = "select * from filmes where codigo = ?";  // SQL para buscar um filme pelo código

        try {
            statement = bd.connection.prepareStatement(sql);  // Prepara a consulta
            statement.setString(1, filme.getCodigo()); // Define o parâmetro da consulta
            resultSet = statement.executeQuery(); // Executa a consulta
            if (resultSet.next()) {  // Verifica se há um resultado
                // Se encontrado, define os atributos do objeto filme
                filme.setCodigo(resultSet.getString(1));
                filme.setTitulo(resultSet.getString(2));
                filme.setGenero(resultSet.getString(3));
                filme.setProdutora(resultSet.getString(4));
                filme.setDataCompra("" + resultSet.getDate(5)); // Obtém a data de compra
                return true; // Retorna verdadeiro se o filme foi encontrado
            }
            return false; // Retorna falso se não encontrado
        } catch (SQLException erro) {
            return false; // Retorna falso em caso de erro
        }
    }

    // Método para atualizar informações do filme (inclusão, alteração ou exclusão)
    public String atualizar(int operacao) {
        men = "Operação realizada com sucesso!"; // Mensagem padrão de sucesso
        try {
            if (operacao == INCLUSAO) {
                sql = "insert into filmes values (?,?,?,?,?)"; // SQL para inserção

                statement = bd.connection.prepareStatement(sql); // Prepara a consulta para inserção

                // Define os parâmetros do filme
                statement.setString(1, filme.getCodigo());
                statement.setString(2, filme.getTitulo());
                statement.setString(3, filme.getGenero());
                statement.setString(4, filme.getProdutora());
                statement.setString(5, filme.getDataCompra());

            } else if (operacao == ALTERACAO) {
                sql = "update filmes set titulo = ?, genero = ?, produtora = ?,"
                        + "datacompra = ? where codigo = ?"; // SQL para atualização

                statement = bd.connection.prepareStatement(sql); // Prepara a consulta para atualização

                // Define os parâmetros do filme para atualização
                statement.setString(5, filme.getCodigo());
                statement.setString(1, filme.getTitulo());
                statement.setString(2, filme.getGenero());
                statement.setString(3, filme.getProdutora());
                statement.setString(4, filme.getDataCompra());

            } else if (operacao == EXCLUSAO) {
                sql = "delete from filmes where codigo = ?"; // SQL para exclusão

                statement = bd.connection.prepareStatement(sql); // Prepara a consulta para exclusão

                statement.setString(1, filme.getCodigo()); // Define o código do filme a ser excluído
            }
            // Executa a operação e verifica se a atualização foi bem-sucedida
            if (statement.executeUpdate() == 0) {
                men = "Falha na operação!"; // Mensagem de falha
            }
        } catch (SQLException erro) {
            men = "Falha na operação: " + erro.toString(); // Mensagem de erro
        }
        return men; // Retorna a mensagem de sucesso ou erro
    }
}
