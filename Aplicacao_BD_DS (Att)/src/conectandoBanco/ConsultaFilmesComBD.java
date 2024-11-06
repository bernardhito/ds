package conectandoBanco;

import java.sql.*;

public class ConsultaFilmesComBD {
    public static void main(String[] args) {
        BD bd = new BD();  // Cria uma instância da classe BD para gerenciar a conexão com o banco de dados
        if (bd.getConnection()) {  // Tenta estabelecer uma conexão com o banco de dados
            try {
                // Consulta SQL para selecionar os códigos e títulos dos filmes dentro de um intervalo especificado
                String sql = "SELECT codigo, titulo FROM Filmes WHERE codigo > ? AND codigo < ? ORDER BY codigo";
                PreparedStatement statement = bd.connection.prepareStatement(sql); // Prepara a consulta

                // Define os valores para os parâmetros da consulta
                statement.setString(1, "03120"); // Define o valor do primeiro parâmetro
                statement.setString(2, "03140"); // Define o valor do segundo parâmetro

                ResultSet resultSet = statement.executeQuery(); // Executa a consulta e armazena o resultado

                // Cabeçalho para exibição no console
                System.out.println("CÓDIGO  TITULO");
                System.out.println("------  -----------------------------------------------");

                // Laço para percorrer os resultados da consulta
                while (resultSet.next()) {
                    String codigo = resultSet.getString("codigo"); // Obtém o código do filme
                    String titulo = resultSet.getString("titulo"); // Obtém o título do filme

                    // Exibe o código e o título no console
                    System.out.println(codigo + "     " + titulo);
                }

                // Fecha os recursos abertos
                resultSet.close();
                statement.close();
                bd.close(); // Fecha a conexão com o banco de dados
            } catch (SQLException erro) {  // Captura exceção caso ocorra um erro na consulta
                // Exibe uma mensagem de erro informando que houve um problema na consulta
                System.out.println("Erro na consulta: " + erro.toString());
                erro.printStackTrace(); // Imprime a stack trace para depuração
            }
        } else {
            System.out.println("Erro ao conectar!"); // Mensagem informando falha na conexão
        }
    }
}
