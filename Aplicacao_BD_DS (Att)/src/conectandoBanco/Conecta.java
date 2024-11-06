package conectandoBanco;
import java.sql.*;
import javax.swing.*;

public class Conecta {
    public static void main(String[] args) {
        final String DRIVER = "com.mysql.jdbc.Driver";  // Nome do driver JDBC para MySQL
        final String URL = "jdbc:mysql://localhost:3306/dados"; // URL para conectar ao banco 'dados'

        try {
            Class.forName(DRIVER);  // Carrega o driver JDBC especificado
            // Estabelece a conexão com o banco de dados usando o URL, login e senha (sem senha por padrão)
            Connection connection = DriverManager.getConnection(URL, "root", "");
            // Exibe uma mensagem de sucesso para o usuário
            JOptionPane.showMessageDialog(null, "Conexão realizada com sucesso");
            connection.close();  // Fecha a conexão após a verificação
        } catch (ClassNotFoundException erro) {  // Captura exceção caso o driver não seja encontrado
            // Exibe uma mensagem de erro informando que o driver não foi encontrado
            JOptionPane.showMessageDialog(null, "Driver não encontrado!\n" + erro.toString());
        } catch (SQLException erro) {  // Captura exceção caso ocorra um erro na conexão
            // Exibe uma mensagem de erro informando que houve um problema na conexão
            JOptionPane.showMessageDialog(null, "Problemas na conexão com a fonte de dados!\n" + erro.toString());
        }
    }
}
