package códigos;
import java.sql.*;
public class BD {
    public Connection connection = null;
    private final String DRIVER = "com.mysqljdbc.Driver";
    private final String DBNAME = "banco";
    private final String URL = "jdbc:mysql://localhost:3306/" + DBNAME;
    private final String LOGIN = "root";
    private final String SENHA = "123456";
    /**
     * metodo que faz conexao com o banco de dados e retorna true se houve sucessão
     * ou false em caso negativo
     */
    public boolean getConnection(){
        try{
            Class.forName(DRIVER);
            connection = DriverManager.getConection(URL,LOGIN, SENHA);
            System.out.println("Conectou");
            return true;
        } catch(ClassNotFoundException erro) {
            System.out.println("Driver nao encontrado " + erro.toString());
            return false;
        } catch (SQLException erro) {
            System.out.println("Falha ao conectar " + erro.toString());
            return false;
        } catch (SQLException erro) {
            System.out.println("Falha ao conectar " + erro.toString());
            return false;
        }
        public void close() {
            try {
                connection.close();
                System.out.println("Desconectou");
            }catch (SQLException erro) {
        }
    }
}
