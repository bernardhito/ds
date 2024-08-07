package Codigos;
import javax.swing.JOptionPane;
public class IfResumido {
    public static void main(String[] args) {
        int a = 10, b = 15, maior;
        if (a > b) {
            maior = a;
        } else {
            maior = b;
        }
        JOptionPane.showMessageDialog(null, "Usando um if comum: " + maior);

        int c = 10, d = 8;
        maior = (c > d) ? c : d;
        JOptionPane.showMessageDialog(null, "Usando um if resumido: " + maior);
    }
}
