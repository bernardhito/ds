package Codigos;
import javax.swing.JOptionPane;
public class ListagemDaClasseIf {
    public static void main(String[] args) {
        Object [] op = {"Masculino", "Feminino", "Nenhum"};
        String resp = (String) JOptionPane.showInputDialog(null,
                "Selecione o sexo:\n", "Pesquisa",
                JOptionPane.PLAIN_MESSAGE,
                null, op, "Masculino");
        if (resp == null) {
            JOptionPane.showMessageDialog(null, "Você pressionou Cancel");
        }
        if (resp.equals("Masculino")) {
            JOptionPane.showMessageDialog(null, "Você é homem");
        }
        if (resp.equals("Feminino"))  {
            JOptionPane.showMessageDialog(null, "Você é mulher.");
        }
        if (resp.equals("Nenhum"))    {
            JOptionPane.showMessageDialog(null, "Você preferiu não dizer");
        }
        System.exit(0);
    }
}
