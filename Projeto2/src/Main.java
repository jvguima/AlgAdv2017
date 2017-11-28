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
            //Condição de parada
            if(length==0)
                break;
            int nCuts = s.nextInt();
            
            int [] cutPos = new int [nCuts+1];
            cutPos[0] = 0;  
            
            for(int i=1; i<nCuts+1; i++){
                cutPos[i]=s.nextInt();
            }
            /***
             * Tabela de memoização a ser utilizada no programa
             * 
             * Ela armazena em [i][j] o custo mínimo para realizar um corte
             * entre as posições dos cortes i e j
             * 
             */
            int [][] minCost = new int [nCuts+1][nCuts+1];
            
            for(int j=1; j<= nCuts; j++){ //POS FINAL
                for(int i=j-1; i >= 0; i--){  //POS INICIAL
                    
                    /**
                     * Casos base: Se não há cortes entre i e j, 
                     * o custo também é Zero.
                     */
                    if(i+1 == j)
                        minCost[i][j] = 0;
                 
                    else{
                        //Tamanho máximo calculável
                        minCost[i][j] = Integer.MAX_VALUE;
                        /**
                         * Verifica linearmente todos os cortes possíveis entre j e i.
                         * Seleciona o melhor possível
                         */
                        for (int k = i+1; k < j; k++){
                           //ans = Math.min(ans, minCost[i][k] + minCost[k][j] );
                           if(minCost[i][k] + minCost[k][j] < minCost[i][j])
                               minCost[i][j] = minCost[i][k] + minCost[k][j];
                        }
                        //Custo do tamanho da barra atual mais o custo mínimo entre elas
                        minCost[i][j] += cutPos[j] - cutPos[i];
                    }
                }
            }
            
            /**
             * Imprime a Matriz Resposta
             */
            for(int i=0; i<nCuts+1; i++){
                System.out.println("");
                for(int j=0; j<nCuts+1; j++){
                    System.out.print(" " + minCost[i][j]);
                }
            }
            
            System.out.println("\n\n"+minCost[0][nCuts]);
        }
        
    }
    
}
