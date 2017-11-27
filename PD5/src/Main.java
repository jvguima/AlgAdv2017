
import java.util.Scanner;

class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        
        while(s.hasNext()){
            int value = s.nextInt();//Valor a ser verificado
            
            int [] coins = new int []{1,5,10,25,50};
            
            //Número de possibilidades para um valor = indice do vetor
            int [] vetPoss = new int[value+1];
            
            //Existem 0 possibilidades para fazer zero
            vetPoss[0]=0;
            
            //Para o vetor de valores possiveis
            for(int i = 1; i<value+1; i++){
                vetPoss[i]=0;
                
                //Para cada tipo de moeda
                for(int j = 0; j<coins.length; j++){
                    
                    //Maior do que o atual, nem veja pras outras moedas
                    if(coins[j] > i)
                        break;
                    
                    if(coins[j] == i){
                        //Há ao menos uma possibilidade: uma moeda atual
                        vetPoss[i]+=1;
                        break;//Nem veja pras outras moedas
                    }
                    
                    //A moeda analisada tem um valor menor do que o valor sendo analisado
                    else if(coins[j] < i){
                        
                        int resto = i%coins[j];

                        /**
                         * O número de possibilidades será o número de possibilidades da moeda atual
                         */
                        
                        vetPoss[i] =  vetPoss[coins[j]] +  vetPoss[resto];  
                        
                       /* if(resto==0){
                            vetPoss[i] = vetPoss[coins[j]];
                        }
                        else {
                            vetPoss[i] =  vetPoss[coins[j]] +  vetPoss[resto];    
                        }         */              
                       
                        //PRINTA VETOR
                        /*for(int k = 0; k<value+1; k++){
                            System.out.println("vetPoss["+k+"] = " + vetPoss[k]);
                        }*/
                        
                    }                    
                }
            
            }
            if(vetPoss[vetPoss.length-1]==1)
                System.out.println("There is only one way to produce " + value + " cents change.");
            else
                System.out.println("There are " +  vetPoss[vetPoss.length-1] + " ways to produce " + value + " cents change.");
        }
        System.out.println("");
    }
    
}
