package conectandoBanco;
import java.sql.*;
import javax.swing.*;

public class Consultafilmes {
    public static void main(String[] args) {
        final String DRIVER = "com.mysql.jdbc.Driver";  // Nome do driver JDBC para MySQL
        final String URL = "jdbc:mysql://localhost:3306/dados"; // URL para conectar ao banco 'dados'

        try {
            Class.forName(DRIVER);  // Carrega o driver JDBC especificado
            Connection connection = DriverManager.getConnection(URL, "root", ""); // Estabelece a conexão com o banco

            // Consulta SQL para selecionar os códigos e títulos dos filmes, ordenados por código
            String sql = "SELECT codigo, titulo FROM Filmes ORDER BY codigo";

            PreparedStatement statement = connection.prepareStatement(sql); // Prepara a consulta
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
            connection.close();
        } catch (ClassNotFoundException erro) {  // Captura exceção caso o driver não seja encontrado
            // Exibe uma mensagem de erro informando que o driver não foi encontrado
            JOptionPane.showMessageDialog(null, "Driver não encontrado:\n" + erro.toString());
        } catch (SQLException eerro) {  // Captura exceção caso ocorra um erro na conexão ou execução da consulta
            // Exibe uma mensagem de erro informando que houve um problema na conexão
            JOptionPane.showMessageDialog(null, "Problemas na conexão com a fonte de dados\n" + eerro.toString());
        }
    }
}
