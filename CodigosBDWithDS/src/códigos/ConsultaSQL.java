package códigos;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.awt.event.*;
import javax.xml.transform.Result;

public class ConsultaSQL extends JFRAME {
    private JLabel labell;
    private JTextField tfSQL;
    private JButton btExecutar;
    private JScrollPane scrollTable;
    private JTable table;
    private BD bd;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public static void main(String[] args) {
        JFrame frame = new ConsultaSQL();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public ConsultaSQL() {
        inicializarComponentes();
        definirEventos ();
    }
    public void inicializarComponentes(){
        setLayout(null);
        setTitle("Aprendendo consultas em SQL:");
        setBounds(200, 200 , 600, 500 );
        setResizable(false);
        labell = new JLabel("Digite o comando SQL:");
        labell.setBounds(50,10, 200, 25);
        tfSQL = new JTextField(50);
        tfSQL.setBounds(50, 35, 500, 25);
        btExecutar = new JButton("Executar");
        btExecutar.setBounds(50, 70, 100, 25);
        scrollTable = new JScrollPane();
        scrollTable.setBounds(50, 100, 500, 300);
        add(scrollTable);
        add(labell);
        add(tfSQL);
        add(btExecutar);
        bd = new BD();

    }
    public void definirEventos(){
        btExecutar.addActionListener(new ActionListener())  {
            public void actionPerformed(ActionEvent e) {
                if (tfSQL.getText().equals ("")) {
                    return;
                }
                try {
                    if (!bd.getConnection()) {
                        JOptionPane.showMessageDialog(null, "Falha na conexão, o sistema será fechado!");
                        System.exit(0);
                    }
                    statement = bd.connection.prepareStatement(tfSQL.getText());
                    resultSet = statement.executeQuery();
                    DefaultTableModel tableModel = new DefaultTableModel {
                        new String [ ]{ }, 0) {
                        };
                    int qtdeColunas = resultSet.getMetaData().getColumnCount();
                    for (int indice = 1; i <= qtdeColunas; i++) {
                        dados[i - 1] = (resultSet.getString(i);
                        System.out.println(resultSet.getString(i));
                    }
                    dtm.addRow(dados);
                        System.out.println();
                    } catch (SQLException erro) {
                }
                scrollTable.setViewportView(table);
            }
                resultSet.close();
                statement.close();
                bd.close();
        } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, "Comando SQL inválido !" + erro.toString());
            }
    }
});
