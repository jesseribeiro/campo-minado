import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Janela extends JFrame implements ActionListener {

    private Tabuleiro t = new Tabuleiro();
    private Botao botao[];
    private Icon aluno;
    private String str;
    
    public Janela() {
        super("Campo Minado");
        
        Container container = getContentPane();
        container.setLayout(new GridLayout(10,10));
        
        aluno = new ImageIcon("aluno.png","aluno");

        t.preencheMatriz();
        t.sorteiaMinas();
        t.imprimeMatriz();
        
        botao = new Botao[totalBotoes(t)];

        int i=0;
        int array[] = new int[2];
        for (int x=1;x<(t.getLinhas()-1);x++) {
            for (int j=1;j<(t.getColunas()-1);j++) {
                 if (t.retornaValor(x,j) == 7) {
                     array[0]=x;
                     array[1]=j;

                     botao[i] = new Botao(array);
                     botao[i].addActionListener(this);

                     container.add(botao[i]);
                     i++;
                 }
                 else {
                     array[0]=x;
                     array[1]=j;

                     botao[i] = new Botao(array);
                     botao[i].addActionListener(this);

                     container.add(botao[i]);
                     i++;
                 }
            }
        }
        setSize(500,500);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
        
    public int totalBotoes(Tabuleiro t) {
        return (t.getLinhas()-1) * (t.getColunas()-1);
    }

    public void actionPerformed(ActionEvent ae) {
        Botao b = (Botao)ae.getSource();
        Icon bomba = new ImageIcon("bombinha_normal.png","bomba");

        int[] vetor = b.getArray();

        if (t.retornaValor(vetor[0],vetor[1]) == -1) {
            b.setIcon(bomba);
        }
        else {
            if (t.verificaVizinho(vetor[0],vetor[1]) > 0) {
                b.setText(str = String.valueOf(t.verificaVizinho(vetor[0],vetor[1])));
            }
        }
    }
    
    private class Botao extends JButton {
        private int[] valor = new int[2];
        public Botao(int[] array) {
            super();
            valor[0]=array[0];
            valor[1]=array[1];
        }
        public int[] getArray() {
            return valor;
        }
    }

    public static void main(String args[]) {
        Janela janela = new Janela();
    }
}
 