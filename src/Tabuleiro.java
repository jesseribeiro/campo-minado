import java.util.*;

public class Tabuleiro {
    private int[][] m = new int[12][12];
    private int l=12;
    private int c=12;
    private int i=1;
    private int linha;
    private int coluna;
    Random random = new Random();
    
    public void preencheMatriz() {

        for (linha=0; linha<getLinhas(); linha++) {
            for(coluna=0; coluna<getColunas(); coluna++) {
                m[linha][coluna]= 7;
                i++;
            }
        }
    }
    
    public int getLinhas() {
        return l;
    }
    
    public int getColunas() {
        return c;
    }
    
    public void sorteiaMinas() {
        int i = 0;
        while (i<30) {
            int x = random.nextInt(10) + 1;
            int y = random.nextInt(10) + 1;
            if (m[x][y] != -1)
            {
                m[x][y] = -1;
                i++;
            }
        }
    }
    
    public void imprimeMatriz() {
        for (linha=1; linha<(getLinhas()-1); linha++) {
            for (coluna=1; coluna<(getColunas()-1); coluna++) {
                if (coluna == 10) {
                    System.out.println("  " + m[linha][coluna]);
                }
                else {
                    System.out.print("  " + m[linha][coluna]);
                }
            }
        }
    }
    
    public int retornaValor(int a, int b) {
        return m[a][b];
    }
    
    public int verificaVizinho(int a, int b) {
        int v=0;
        if (m[a-1][b-1] == -1) {
            v += 1;
        }
        if (m[a-1][b] == -1) {
            v += 1;
        }
        if (m[a-1][b+1] == -1) {
            v += 1;
        }
        if (m[a][b-1] == -1) {
            v += 1;
        }
        if (m[a][b+1] == -1) {
            v += 1;
        }
        if (m[a+1][b-1] == -1) {
            v += 1;
        }
        if (m[a+1][b] == -1) {
            v += 1;
        }
        if (m[a+1][b+1] == -1) {
            v += 1;
        }
        return v;
    }
    
    public static void main(String args[]) {
        Tabuleiro tab = new Tabuleiro();
        tab.preencheMatriz();
        tab.sorteiaMinas();
        tab.imprimeMatriz();
    }
}

   
    