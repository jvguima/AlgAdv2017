/**
 * @authors João Victor Lopes da Silva Guimarães - 8936843
 *          Danilo Marques Araujo dos Santos	 - 8598670
 */

import java.util.Arrays;
import java.util.Scanner;

/**
 * Dada uma barra de tamanho L e N cortes. Queremos obter o menor custo possível
 * Custo = tamanho da barra a ser cortada.
 * 
 * O Custo mínimo de um dado corte é o menor dos cortes subsequentes nas barras 
 * resultantes + tamanho da barra atual. Se nós segmentarmos uma barra nas 
 * posições de começo, posições dos cortes, e fim, é possível chegar na seguinte
 * regra:
 * 
 * RECORRÊNCIA
 * CustoCorte(começoBarra, fimBarra)
 *      = 0 se Não há cortes a serem feitos no meio do começo e fim
 * 
 *      = menor Custo de todos os possíveis cortes entre começo e fim.
 * 
 */
public class Main {	

    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        
        while(true){
            // length: tamanho da barra
            //  nCuts: número de cortes
            int length = s.nextInt();
            int nCuts = s.nextInt();

            //Condição de parada do problema
            if(length==0)
                break;
            
            /**
             * O vetor de armazenamento das posições dos cortes, incluindo 
             * também um "corte" em Zero e um na posição final da barra.
             * 
             */
            int[] cutPos = new int [nCuts+2];

            /***
             * Tabela de memoização a ser utilizada no programa
             * 
             * Ela armazena em [beg][end] o custo mínimo para realizar um corte
             * entre as posições dos cortes beg e end
             * 
             */        
            int[] [] minCost = new int [nCuts+2][nCuts+2];
            
            /**
             * Por default toda a entrada da tabela terá o Custo máximo calculável
             */
            for (int i = 0; i<nCuts+2; i++){
                Arrays.fill(minCost[i], Integer.MAX_VALUE);
            }
            
            cutPos[0] = 0;
            for (int beg = 1; beg <= nCuts; beg++){
                cutPos[beg]=s.nextInt();
            }
            nCuts++;
            cutPos[nCuts] = length;
            
            /**Deixa o tamanho de nCuts = ao tamanho do vetor cutPos
             * Isso facilita a iteração por eles
            */


            for (int end = 1; end <= nCuts; end++){ //Posição Final do Corte
                for (int beg = end - 1; beg >= 0; beg--) {  //Posição inicial do corte
                    
                    /**
                     * Casos base: Se não há cortes entre i e j, 
                     * o custo também é Zero.
                     */                    
                    if (beg + 1 == end)
                        minCost[beg][end] = 0;

                    else {
                        
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
            /*
            for(int beg=0; beg<nCuts+1; beg++){
                System.out.println("");
                for(int end=0; end<nCuts+1; end++){
                    System.out.print(" " + minCost[beg][end]);
                }
            }*/
            //Resposta final
            System.out.println("The minimum cutting is "+ minCost[0][nCuts] + ".");
        }
    }    
}
