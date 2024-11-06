package conectandoBanco;

// Importações necessárias para trabalhar com SQL e interface gráfica
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NavegaFilmes extends JFrame {
    // Declaração dos componentes da interface gráfica
    private JLabel label1, label2, label3, label4, label5;
    private JTextField tfCodigo, tfTitulo, tfGenero, tfProdutora, tfdatCom;
    private JButton btProximo, btAnterior, btPrimeiro, btUltimo, btMais10, btMenos10, btSai;
    private BD bd; // Classe BD para conexão com o banco de dados
    private PreparedStatement statement; // Para executar consultas SQL
    private ResultSet resultSet; // Para armazenar os resultados das consultas

    // Mensagens de erro para uso posterior
    private static final String ERRO_CONEXAO = "Falha ao conectar, o sistema será fechado!";
    private static final String ERRO_SQL = "Erro na consulta: ";

    public static void main(String[] args) {
        // Cria uma instância da interface e a torna visível
        JFrame frame = new NavegaFilmes();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public NavegaFilmes() {
        inicializarComponentes(); // Chama o método para inicializar os componentes
        definirEventos(); // Chama o método para definir os eventos dos botões
    }

    public void inicializarComponentes() {
        // Define o layout da interface gráfica
        setLayout(new FlowLayout(FlowLayout.LEFT));

        // Cria os rótulos para os campos de entrada
        label1 = new JLabel("Código ");
        label2 = new JLabel("Título ");
        label3 = new JLabel("Gênero ");
        label4 = new JLabel("Produtora ");
        label5 = new JLabel("Data de Compra ");

        // Cria os campos de texto para a entrada de dados
        tfCodigo = new JTextField(10);
        tfTitulo = new JTextField(35);
        tfGenero = new JTextField(10);
        tfProdutora = new JTextField(15);
        tfdatCom = new JTextField(10);

        // Cria os botões com ícones e tooltips
        btProximo = new JButton(null, new ImageIcon("icones/proximo.gif"));
        btProximo.setToolTipText("Próximo");

        btAnterior = new JButton(null, new ImageIcon("icones/anterior.gif"));
        btAnterior.setToolTipText("Anterior");

        btPrimeiro = new JButton(null, new ImageIcon("icones/primeiro.gif"));
        btPrimeiro.setToolTipText("Primeiro");

        btUltimo = new JButton(null, new ImageIcon("icones/ultimo.gif"));
        btUltimo.setToolTipText("Último");

        btMais10 = new JButton(null, new ImageIcon("icones/mais.png"));
        btMais10.setToolTipText("+10");

        btMenos10 = new JButton(null, new ImageIcon("icones/menos.png"));
        btMenos10.setToolTipText("-10");

        btSai = new JButton(null, new ImageIcon("icones/sair.png"));
        btSai.setToolTipText("Sair");

        // Adiciona os componentes ao painel
        add(label1);
        add(tfCodigo);
        add(label2);
        add(tfTitulo);
        add(label3);
        add(tfGenero);
        add(label4);
        add(tfProdutora);
        add(label5);
        add(tfdatCom);

        add(btPrimeiro);
        add(btAnterior);
        add(btProximo);
        add(btUltimo);
        add(btMais10);
        add(btMenos10);
        add(btSai);

        // Define o título da janela e suas propriedades
        setTitle("Navegando na tabela de Filmes");
        setBounds(200, 400, 620, 120); // Define a posição e o tamanho da janela
        setResizable(false); // Impede que a janela seja redimensionada

        bd = new BD(); // Cria uma nova instância da classe BD
        if (!bd.getConnection()) { // Tenta estabelecer a conexão com o banco de dados
            JOptionPane.showMessageDialog(null, ERRO_CONEXAO); // Mostra mensagem de erro
            System.exit(0); // Encerra o programa se a conexão falhar
        }

        carregarTabela(); // Carrega os dados da tabela
        atualizarCampos(); // Atualiza os campos com os dados iniciais
    }

    public void definirEventos() {
        // Define os eventos de clique para cada botão
        btProximo.addActionListener(e -> navegar(1)); // Navega para o próximo registro
        btAnterior.addActionListener(e -> navegar(-1)); // Navega para o registro anterior
        btPrimeiro.addActionListener(e -> navegarParaPosicao("FIRST")); // Navega para o primeiro registro
        btUltimo.addActionListener(e -> navegarParaPosicao("LAST")); // Navega para o último registro
        btMais10.addActionListener(e -> navegar(10)); // Navega 10 registros para frente
        btMenos10.addActionListener(e -> navegar(-10)); // Navega 10 registros para trás
        btSai.addActionListener(e -> sair()); // Encerra o aplicativo
    }


    private void navegar(int deslocamento) {
        // Navega para um registro em relação à posição atual
        try {
            if (resultSet != null && !resultSet.isClosed()) {
                if (resultSet.next()) {
                    atualizarCampos(); // Atualiza os campos com os dados do novo registro
                } else {
                    resultSet.previous(); // Reverte se não for possível ir para frente
                }
            }
        } catch (SQLException e) {
            exibirErro(ERRO_SQL + e.getMessage()); // Exibe erro em caso de exceção
        }
    }

    private void navegarParaPosicao(String posicao) {
        // Navega para uma posição específica (primeiro ou último registro)
        try {
            if (resultSet != null && !resultSet.isClosed()) {
                if (posicao.equals("FIRST")) {
                    resultSet.first(); // Move para o primeiro registro
                } else if (posicao.equals("LAST")) {
                    resultSet.last(); // Move para o último registro
                }
                atualizarCampos(); // Atualiza os campos com os dados do novo registro
            }
        } catch (SQLException e) {
            exibirErro(ERRO_SQL + e.getMessage()); // Exibe erro em caso de exceção
        }
    }


    private void sair() {
        // Fecha recursos e encerra o aplicativo
        try {
            if (resultSet != null) resultSet.close(); // Fecha o ResultSet
            if (statement != null) statement.close(); // Fecha o PreparedStatement
        } catch (SQLException e) {
            exibirErro(ERRO_SQL + e.getMessage()); // Exibe erro em caso de exceção
        } finally {
            bd.close(); // Fecha a conexão com o banco de dados
            System.exit(0); // Encerra o programa
        }
    }

    public void carregarTabela() {
        // Carrega os dados da tabela filmes do banco de dados
        String sql = "SELECT * FROM filmes";
        try {
            statement = bd.connection.prepareStatement(sql); // Prepara a consulta SQL
            resultSet = statement.executeQuery(); // Executa a consulta e obtém os resultados
        } catch (SQLException e) {
            exibirErro(ERRO_SQL + e.getMessage()); // Exibe erro em caso de exceção
        }
    }

    public void atualizarCampos() {
        // Atualiza os campos de texto com os dados do registro atual
        try {
            // Verifica se o ResultSet está em uma posição válida
            if (resultSet.isAfterLast()) {
                resultSet.last(); // Move para o último registro se estiver além do final
            }
            if (resultSet.isBeforeFirst()) {
                resultSet.first(); // Move para o primeiro registro se estiver antes do início
            }

            // Preenche os campos de texto com os dados do registro atual
            tfCodigo.setText(resultSet.getString("codigo"));
            tfTitulo.setText(resultSet.getString("titulo"));
            tfGenero.setText(resultSet.getString("genero"));
            tfProdutora.setText(resultSet.getString("produtora"));
            tfdatCom.setText(resultSet.getDate("datacompra").toString());
        } catch (SQLException e) {
            exibirErro(ERRO_SQL + e.getMessage()); // Exibe erro em caso de exceção
        }
    }

    private void exibirErro(String mensagem) {
        // Método para exibir mensagens de erro
        JOptionPane.showMessageDialog(null, mensagem); // Mostra a mensagem em um diálogo
    }
}
