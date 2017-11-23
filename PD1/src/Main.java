/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author João
 */
import java.util.Scanner;
 
class Main {
    
    private static class Item{
        int price;
        int weight;

        public Item(int price, int weight) {
            this.price = price;
            this.weight = weight;
        }
    }
    
    /**
     * Função que realiza a P.D. propriamente dita
     * @param itens Vetor com todos os itens, item 0 tem peso = preco = 0
     * @param maxWg Peso máximo
     * @return Valor máximo dos itens que pode ser carregado pelo peso máximo
     */
    private static int getMaxValue(Item itens[], int maxWg){

        /**Matriz de memoização - Uma linha para cada produto, Uma coluna para o peso
          * Queremos Achar todas as possíveis combinações de pesos e itens
          * 
          * Cada célula da matriz representa o valor obtido dado 
          * aquele peso e considerando aquele item e seus anteriores
         */
        
        int numItens = itens.length;

        
        //Matriz de memoização
        int memo [] [] = new int [itens.length] [maxWg+1];

        //Analisa para cada item
        for(int currItem=1; currItem<numItens; currItem++){
            //Analisa para cada peso disponível
            for(int availW=1; availW<maxWg+1; availW++){

                //Insere os valores iniciais da matriz
                if(currItem==0 || availW==0)
                    memo[currItem][availW]=0;
                
                
                //Peso do item sendo analisado é Maior do que o peso atual
                else if (itens[currItem].weight > availW){
                    //Não pega esse item, fica o mesmo valor do item anterior
                    memo[currItem][availW] = memo[currItem-1][availW];
                }

                //O item esta dentro do peso
                /**
                * Ou pega esse item, reduz o tanto de peso disponível e soma com o próximo item compatível
                * Ou não pega esse item , mantém o peso disponível, e vê o próximo item
                */
                else{
                    memo[currItem][availW] = 
                            Math.max(itens[currItem].price + memo[currItem-1][availW - itens[currItem].weight],
                            memo[currItem-1][availW]);
                }
            }
        }
        
        //Desconsidera o item nulo
        return memo[numItens-1][maxWg];
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        
        int nCasos = s.nextInt();
        
        for (int i = 0; i<nCasos; i++){
            
            int totalValue = 0; //Contador do valor total dos itens que a familia consegue levar
            Item [] itens;  //Array de Itens
            int [] famW; //Vetor de pesos que cada i-esima pessoa da familia pode carregar
            
            int numItens = s.nextInt();
            itens = new Item[numItens+1];
            itens[0] = new Item(0,0);       //ADICIONA UM ITEM NULO (para não dar merda nas iterações da matriz memo
            for (int j = 1; j<numItens+1; j++){
                itens[j] = new Item(s.nextInt(), s.nextInt());
            }
            
            //Tamanho da familia
            int famSize = s.nextInt();
            famW = new int[famSize];
            //Peso de que cada j-esimo membro consegue carregar
            for (int j = 0; j<famSize; j++){
                int maxWg = s.nextInt();//Maximum Weight
                totalValue += getMaxValue(itens, maxWg);
            }
            
            System.out.println(totalValue);
        }
    }
    
}
