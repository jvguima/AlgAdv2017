/**
 * @authors João Victor Lopes da Silva Guimarães - 8936843
 *          Danilo Marques Araujo dos Santos	 - 8598670
 */

import java.util.Arrays;
import java.util.Scanner;

public class Main {	


    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        
        while(true){

            int length = s.nextInt();
            
            //Condição de parada do problema
            if(length==0)
                break;
            
            int nCuts = s.nextInt();
            
            
            //Inclui um corte para o começo e um corte para o final
            int[] cutPos = new int [nCuts+2];

            /***
             * Tabela de memoização a ser utilizada no programa
             * 
             * Ela armazena em [i][j] o custo mínimo para realizar um corte
             * entre as posições dos cortes i e j
             * 
             */        

            int[] [] minCost = new int [51][51];


            for (int beg = 1; beg <= nCuts; beg++)
                cutPos[beg]=s.nextInt();

            cutPos[0] = 0;
            
            /**Deixa o tamanho de nCuts = ao tamanho do vetor cutPos
             * Isso facilita a iteração por eles
            */
            nCuts++;
            cutPos[nCuts] = length;

            for (int end = 1; end <= nCuts; end++){
                for (int beg = end - 1; beg >= 0; beg--) {
                    
                    /**
                     * Casos base: Se não há cortes entre i e j, 
                     * o custo também é Zero.
                     */                    
                    if (beg + 1 == end)
                        minCost[beg][end] = 0;

                    else {
                        //Tamanho máximo calculável
                        minCost[beg][end] = Integer.MAX_VALUE;
                        
                        /**
                         * Verifica linearmente todos os cortes possíveis entre 
                         * fim e começo. Seleciona o melhor possível
                         */                        
                        for (int k = beg + 1; k < end; k++) {
                            if (minCost[beg][k] + minCost[k][end] < minCost[beg][end])
                                minCost[beg][end] = minCost[beg][k] + minCost[k][end];
                        }
                        //Custo do tamanho da barra atual mais o custo mínimo entre elas
                        minCost[beg][end] += cutPos[end] - cutPos[beg];

                    }

                }
            }
                /**
                 * Imprime a Matriz Memoização
                 */
                for(int beg=0; beg<nCuts+1; beg++){
                    System.out.println("");
                    for(int end=0; end<nCuts+1; end++){
                        System.out.print(" " + minCost[beg][end]);
                    }
                }
                
                //Resposta final
                System.out.println("\n"+ minCost[0][nCuts]);

        }
        
    }
    
}
