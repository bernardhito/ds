package Codigos;
import javax.swing.*;

public class IfComElseAlinhado {
    public static void main(String[] args) {
        String aux = JOptionPane.showInputDialog("Forneça o número do mês");
        if (aux != null) {
            try {
                int mes = Integer.parseInt(aux);
                if (mes == 1) {
                    aux = "Janeiro";
                } else if (mes == 2) {
                    aux = "Fevereiro";
                } else if (mes == 3) {
                    aux = "Março";
                } else if (mes == 4) {
                    aux = "Abril";
                } else if (mes == 5) {
                    aux = "Maio";
                } else if (mes == 6) {
                    aux = "Junho";
                } else if (mes == 7) {
                    aux = "Julho";
                } else if (mes == 8) {
                    aux = "Agosto";
                } else if (mes == 9) {
                    aux = "Setembro";
                } else if (mes == 10){
                    aux = "Outubro";
                } else if (mes == 11){
                    aux = "Novembro";
                } else if (mes == 12){
                    aux = "Dezembro";
                } else {
                    aux = "Mês Desconhecido!";
                }
                JOptionPane.showMessageDialog(null, aux);
            } catch (NumberFormatException erro) {
                JOptionPane.showMessageDialog(null, "Digite apenas valores inteiros" + erro);
            }
        }
        System.exit(0);
    }
}
