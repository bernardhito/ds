package conectandoBanco;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class GuiCadastroFilmes extends JFrame {
    // Componentes da interface gráfica
    JLabel label1, label2, label3, label4, label5; // Rótulos para os campos de entrada

    JButton btGravar, btAlterar, btExcluir, btNovo, btLocalizar, btCancelar, btSair; // Botões da interface

    static JTextField tfCodigo, tfTitulo, tfGenero, tfProdutora, tfDataCompra; // Campos de entrada de texto

    private FilmesDAO filmes; // Objeto responsável pela interação com o banco de dados de filmes

    // Método principal para inicializar a aplicação
    public static void main(String[] args) {
        JFrame janela = new GuiCadastroFilmes(); // Cria uma nova instância da janela
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true); // Exibe a janela
    }

    // Construtor da classe
    public GuiCadastroFilmes() {
        inicializarComponentes(); // Inicializa os componentes da interface
        definirEventos(); // Define os eventos para os botões
    }

    // Método para inicializar os componentes da interface
    public void inicializarComponentes() {
        setLayout(new FlowLayout(FlowLayout.LEFT)); // Define o layout da janela
        setTitle("Cadastramento de Filmes"); // Título da janela
        setBounds(200, 100, 650, 120); // Define o tamanho e a posição da janela

        // Criação dos rótulos
        label1 = new JLabel("Código");
        label2 = new JLabel("Título");
        label3 = new JLabel("Gênero");
        label4 = new JLabel("Produtora");
        label5 = new JLabel("Data de Compra");

        // Criação dos campos de entrada
        tfCodigo = new JTextField(10);
        tfTitulo = new JTextField(35);
        tfGenero = new JTextField(10);
        tfProdutora = new JTextField(15);
        tfDataCompra = new JTextField(10); // Ampliado para acomodar datas

        // Criação dos botões
        btGravar = new JButton("Gravar");
        btAlterar = new JButton("Alterar");
        btExcluir = new JButton("Excluir");
        btLocalizar = new JButton("Localizar");
        btNovo = new JButton("Novo");
        btCancelar = new JButton("Cancelar");
        btSair = new JButton("Sair");

        // Adiciona todos os componentes à janela
        add(label1);
        add(tfCodigo);
        add(label2);
        add(tfTitulo);
        add(label3);
        add(tfGenero);
        add(label4);
        add(tfProdutora);
        add(label5);
        add(tfDataCompra);
        add(btNovo);
        add(btLocalizar);
        add(btGravar);
        add(btAlterar);
        add(btExcluir);
        add(btCancelar);
        add(btSair);

        setResizable(false); // A janela não pode ser redimensionada
        setBotoes(true, true, false, false, false, false); // Configura a inicialização dos botões

        filmes = new FilmesDAO(); // Inicializa a DAO de filmes

        // Tenta conectar ao banco de dados
        if (!filmes.bd.getConnection()) {
            JOptionPane.showMessageDialog(null, "Falha na conexão, o sistema será fechado!"); // Mensagem de erro
            System.exit(0); // Fecha a aplicação se não conseguir conectar
        }
    }

    private void setBotoes(boolean b, boolean b1, boolean b2, boolean b3, boolean b4, boolean b5) {
    }

    // Método para definir os eventos dos botões
    public void definirEventos() {
        btSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filmes.bd.close(); // Fecha a conexão com o banco de dados
                System.exit(0); // Fecha a aplicação
            }
        });

        btNovo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos(); // Limpa os campos de entrada
                setBotoes(false, false, true, false, false, true); // Habilita os botões apropriados
            }
        });

        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos(); // Limpa os campos de entrada
            }
        });

        btGravar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Valida se os campos obrigatórios estão preenchidos
                if (tfCodigo.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "O código não pode ser vazio!"); // Mensagem de erro
                    tfCodigo.requestFocus(); // Foca no campo de código
                    return;
                }

                if (tfTitulo.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "O título não pode ser vazio!"); // Mensagem de erro
                    tfTitulo.requestFocus(); // Foca no campo de título
                    return;
                }

                if (tfGenero.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "O gênero não pode ser vazio!"); // Mensagem de erro
                    tfGenero.requestFocus(); // Foca no campo de gênero
                    return;
                }

                if (tfProdutora.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "A produtora não pode ser vazia!"); // Mensagem de erro
                    tfProdutora.requestFocus(); // Foca no campo de produtora
                    return;
                }

                if (tfDataCompra.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "A data de compra não pode ser vazia!"); // Mensagem de erro
                    tfDataCompra.requestFocus(); // Foca no campo de data de compra
                    return;
                }

                // Define os atributos do filme a partir dos campos de entrada
                filmes.filme.setCodigo(tfCodigo.getText());
                filmes.filme.setTitulo(tfTitulo.getText());
                filmes.filme.setGenero(tfGenero.getText());
                filmes.filme.setProdutora(tfProdutora.getText());
                filmes.filme.setDataCompra(tfDataCompra.getText());

                // Executa a operação de inclusão e mostra a mensagem de resultado
                JOptionPane.showMessageDialog(null, filmes.atualizar(FilmesDAO.INCLUSAO));
                limparCampos(); // Limpa os campos após a operação
            }
        });

        btAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Define os atributos do filme a partir dos campos de entrada para alteração
                filmes.filme.setCodigo(tfCodigo.getText());
                filmes.filme.setTitulo(tfTitulo.getText());
                filmes.filme.setGenero(tfGenero.getText());
                filmes.filme.setProdutora(tfProdutora.getText());
                filmes.filme.setDataCompra(tfDataCompra.getText());

                // Executa a operação de alteração e mostra a mensagem de resultado
                JOptionPane.showMessageDialog(null, filmes.atualizar(FilmesDAO.ALTERACAO));
                limparCampos(); // Limpa os campos após a operação
            }
        });

        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filmes.filme.setCodigo(tfCodigo.getText()); // Define o código do filme a ser excluído
                filmes.localizar(); // Localiza o filme para confirmar se existe
                int n = JOptionPane.showConfirmDialog(null, filmes.filme.getTitulo(),
                        "Excluir o Filme?", JOptionPane.YES_NO_OPTION); // Confirma a exclusão
                if (n == JOptionPane.YES_OPTION) {
                    // Executa a operação de exclusão e mostra a mensagem de resultado
                    JOptionPane.showMessageDialog(null, filmes.atualizar(FilmesDAO.EXCLUSAO));
                    limparCampos(); // Limpa os campos após a operação
                }
            }
        });

        btLocalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarCampos(); // Atualiza os campos com as informações do filme localizado
            }
        });
    }

    // Método para limpar os campos de entrada
    public void limparCampos() {
        tfCodigo.setText(""); // Limpa o campo de código
        tfTitulo.setText(""); // Limpa o campo de título
        tfGenero.setText(""); // Limpa o campo de gênero
        tfProdutora.setText(""); // Limpa o campo de produtora
        tfDataCompra.setText(""); // Limpa o campo de data de compra

        tfCodigo.requestFocus(); // Foca no campo de código para o próximo input

        // Configura os botões após a limpeza
        setBotoes(true, true, false, false, false, false);
    }

    // Método para atualizar os campos com as informações do filme localizado
    public void atualizarCampos() {
        filmes.filme.setCodigo(tfCodigo.getText()); // Define o código do filme a ser localizado

        if (filmes.localizar()) { // Tenta localizar o filme
            // Se encontrado, atualiza os campos de entrada com os dados do filme
            tfCodigo.setText(filmes.filme.getCodigo());
            tfTitulo.setText(filmes.filme.getTitulo());
            tfGenero.setText(filmes.filme.getGenero());

        }
    }
}