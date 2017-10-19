/**
 *
 * @authors João Victor Lopes da Silva Guimarães - 8936843
 *          Danilo Marques Araujo dos Santos	 - 8598670
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        Scanner s = new Scanner(System.in);
        String alg;
        
        while(true){
            System.out.println("SELECIONE O ALGORITMO\n A = A* \t B = BRUTE FORCE");
            alg = s.nextLine();
            
            if(alg.equalsIgnoreCase("A") || alg.equalsIgnoreCase("B"))
                break;
            else
                System.out.println("Try Again");
        }
        System.out.println("DIGITE O NÚMERO DE TESTES E OS ESTADOS INICIAIS DOS TABULEIROS");
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
            
            //==================================================================
            long startTime = System.currentTimeMillis();
           
            boolean success=false;
            // success = BuscaAstar.buscaAstar(tabuleiro, pos0, listaMovimento);
            if(alg.equalsIgnoreCase("B"))
                success = BuscaBruteForce.buscaBruteForce(tabuleiro, 0, listaMovimento, pos0, null);
            else if(alg.equalsIgnoreCase("A"))
                success = BuscaAstar.buscaAstar(tabuleiro, pos0, listaMovimento);
            
            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            if(success) {
                System.out.println("Tempo de processamento: " + totalTime + " ms");                
                //Imprime a lista de movimentos feitos.                
                for(int k=listaMovimento.size()-1; k>=0; k--) {
                    System.out.print(listaMovimento.get(k));
                }
                System.out.println("\n");
            }
            else
                System.out.println( "This puzzle is not solvable.");
        }
    }
}
