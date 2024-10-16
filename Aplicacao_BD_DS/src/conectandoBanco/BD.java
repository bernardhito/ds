package conectandoBanco;
import java.sql.*;

public class BD {
    public Connection connection = null;  // Variável que armazenará a conexão com o banco de dados
    private final String DRIVER = "com.mysql.jdbc.Driver";  // Nome do driver JDBC para MySQL
    private final String DBNAME = "dados";  // Nome do banco de dados a ser utilizado
    private final String URL = "jdbc:mysql://localhost:3306/" + DBNAME;  // URL de conexão com o banco de dados
    private final String LOGIN = "root";  // Nome de usuário para acessar o banco de dados
    private final String SENHA = "";  // Senha para acessar o banco de dados

    /*
     * Método que faz a conexão com o banco de dados.
     * Retorna true se a conexão foi bem-sucedida, ou false em caso de falha.
     */
    public boolean getConnection() {
        try {
            Class.forName(DRIVER);  // Carrega o driver JDBC
            connection = DriverManager.getConnection(URL, LOGIN, SENHA);  // Estabelece a conexão
            System.out.println("Conectou com sucesso ao banco de dados: " + DBNAME);  // Mensagem de sucesso
            return true;  // Retorna true indicando que a conexão foi bem-sucedida
        } catch (ClassNotFoundException e) {  // Captura exceção caso o driver não seja encontrado
            System.out.println("Driver não encontrado: " + e.toString());
            return false;  // Retorna false indicando que houve falha na conexão
        } catch (SQLException e) {  // Captura exceção caso ocorra um erro na conexão
            System.out.println("Falha ao conectar: " + e.toString());
            return false;  // Retorna false indicando que houve falha na conexão
        }
    }

    // Método que fecha a conexão com o banco de dados
    public void close() {
        try {
            // Verifica se a conexão não é nula e não está fechada
            if (connection != null && !connection.isClosed()) {
                connection.close();  // Fecha a conexão
                System.out.println("Desconectou do banco de dados");  // Mensagem de desconexão
            }
        } catch (SQLException erro) {  // Captura exceção caso ocorra um erro ao tentar fechar a conexão
            System.out.println("Erro ao desconectar: " + erro.toString());
        }
    }
}
