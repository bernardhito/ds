package Codigos;
import javax.swing.*;

public class SwitchCase {
    public static void main(String[] args) {
        String mes = (String) JOptionPane.showInputDialog("Forneça o número do mês");
        if (mes != null) {
            switch (mes) {
                case "1":
                  mes = "Janeiro";
                  break;
                case "2":
                    mes = "Fevereiro";
                    break;
                case "3":
                    mes = "Março";
                    break;
                //inserir todos os outros meses
                default:
                    mes = "Mês desconhecido!";
            }
            JOptionPane.showMessageDialog(null, mes);
        }
        System.exit(0);
    }
}