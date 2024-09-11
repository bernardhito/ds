package códigos;
import java.sql.*;
public class ConsultaFilmesComBD {
    public static void main(String[] args) {
        BD bd = new BD();
        if (bd.getConnection()) { //Conexão ok
            try {
                String sql = "SELECT codigo,titulo FROM Filmes WHERE codigo>? AND codigo<?"
                        + "ORDER BY codigo";
                PreparedStatement statment = bd.connection.prepareStatement(sql);
                statment.setString(1, "03120");
                statment.setString(2, "03140");
                ResultSet resultSet = statment.executeQuery();
                System.out.println("CÓDIGO   TÍTULO");
                System.out.println("-----     -------------------------------------------");
                while (resultSet.next()) {
                    String codigo = resultSet.getString("codigo");
                    String titulo = resultSet.getString("titulo");
                    System.out.println(codigo + "   " + titulo);
                }
                resultSet.close();
                statment.close();
                bd.close();
            } catch (SQLException erro) {
                System.out.println(erro.toString());
            }
        } else {
            System.out.println("Erro ao conectar!");
        }
    }
}
