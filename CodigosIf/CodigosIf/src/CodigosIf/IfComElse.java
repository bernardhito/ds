package CodigosIf;
import javax.swing.*;
public class IfComElse {
    public static void main(String[] args){
        String aux = JOptionPane.showInputDialog("Forneça o numero do mês ");
        if (aux != null) {
            try {
                int mes = Integer.parseInt(aux);
                if (mes >= 1 && mes <= 12) {
                    JOptionPane.showMessageDialog(null, "numero do mês valido!\n " + mes);
                } else {
                    JOptionPane.showMessageDialog(null, "Numero do mês invalido!\n " + mes);
                }
            } catch (NumberFormatException erro) {
                JOptionPane.showMessageDialog(null, "Digite apenas valores inteiro!" + erro);
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Opção Cancelada.");
        }
        System.exit(0);
    }
}