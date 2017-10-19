/**
 *
 * @authors João Victor Lopes da Silva Guimarães - 8936843
 *		   	Danilo Marques Araujo dos Santos	 - 8598670
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
        int nproblemas = s.nextInt();

        for(int i=0;i<nproblemas;i++){

            // Usar uma matriz 4 por 4 com o espaço vazio como número zero
            int tabuleiro[][] = new int[4][4]; // <= input da configuração

            // Onde o zero está no tabuleiro
            Pos pos0=null;

            for(int j =0; j<4; j++) {
                for(int k = 0; k<4; k++) {
                    int aux = s.nextInt();
                    tabuleiro[j][k] = aux;
                    if(aux==0)
                        pos0 = new Pos(j,k);
                }
            }

            // Lista que vai armazenar os movimentos feitos
            ArrayList listaMovimento = new ArrayList<>();

            //ArrayList moveValidos = geraMovimentosValidos(tabuleiro, pos0);
            //moveValidos.forEach((t) -> {System.out.println(t);});

            //TESTE DE MOVE
            /*mover(tabuleiro, pos0, Movimentos.L);
            System.out.println("DEPOIS");
            for (int j =0; j<4; j++){
                    for(int k = 0; k<4; k++){
                            System.out.print(tabuleiro[j][k]+" ");
                    }
                    System.out.print("\n");
            }*/
       /* 
            ArrayList<Movimentos> movimentosValidos = geraMovimentosValidos(tabuleiro,Movimentos.L,pos0);
            for (Movimentos K : movimentosValidos){
                    System.out.print(K+" ");
            }System.out.println("\n");

            */

            long startTime = System.currentTimeMillis();
            boolean success = BuscaTradicional.buscaTradicional(tabuleiro, 0, listaMovimento, pos0, null);
            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            if(success) {
                for(int k=listaMovimento.size()-1; k>=0; k--) {
                    System.out.println(listaMovimento.get(k));
                }
            }
            else
                System.out.println( "‘This puzzle is not solvable.’");
            System.out.println("Tempo de processamento: " + totalTime + " ms");
        }
    }
}
