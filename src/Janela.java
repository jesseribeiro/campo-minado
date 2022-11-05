import com.sun.javafx.font.FontFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Dimension2D;

public class Janela extends JFrame implements ActionListener {

    private Tabuleiro t = new Tabuleiro();
    private Botao botao[];
    private String str;

    public Janela() {
        super("Campo Minado");

        Container container = getContentPane();
        container.setLayout(new GridLayout(15,15));

        t.preencheMatriz();
        t.sorteiaMinas();
        t.imprimeMatriz();

        botao = new Botao[totalBotoes(t)];

        int i=0;
        int array[] = new int[2];
        for (int x=1; x<(t.getLinhas()-1);x++) {
            for (int j=1; j<(t.getColunas()-1);j++) {
                array[0]=x;
                array[1]=j;
                botao[i] = new Botao(array);
                botao[i].addActionListener(this);
                container.add(botao[i]);
                i++;
            }
        }
        setSize(700,700);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public int totalBotoes(Tabuleiro t) {
        return (t.getLinhas() -1) * (t.getColunas() -1);
    }

    public void actionPerformed(ActionEvent ae) {
        Botao b = (Botao)ae.getSource();
        Icon bomba = new ImageIcon("bomba.png","bomba");

        int[] vetor = b.getArray();

        b.setBackground(Color.WHITE);
        if (t.retornaValor(vetor[0],vetor[1]).equalsIgnoreCase("X")) {
            b.setIcon(bomba);
        }
        else {
            int numeroBombas = t.verificaVizinho(vetor[0],vetor[1]);
            if (numeroBombas > 0) {
                b.setText(str = String.valueOf(t.verificaVizinho(vetor[0],vetor[1])));
                b.setForeground(verificaCor(numeroBombas));
                b.setFont(new Font("Tahoma", Font.BOLD, 18));
            }
        }
    }

    private Color verificaCor (int numeroBombas) {
        switch (numeroBombas) {
            case 1:
                Color roxo = new Color(153,51,153);
                return roxo;
            case 2:
                Color azul = new Color(0,0,205);
                return azul;
            case 3:
                Color verde = new Color(0,100,0);
                return verde;
            case 4:
                Color amarelo = new Color(255,215,0);
                return amarelo;
            case 5:
                Color laranja = new Color(255,140,0);
                return laranja;
            case 6:
                Color vermelho = new Color(255,36,0);
                return vermelho;
            case 7:
                Color bordo = new Color(128,0,0);
                return bordo;
            case 8:
                Color rosa = new Color(255,20,147);
                return rosa;
        }
        return null;
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