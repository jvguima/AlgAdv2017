
/**
 *
 * @author João Victor Lopes da Silva Guimarães - 8936843
 */


import java.util.ArrayList;
import java.util.Scanner;

class Main {
    
    static class Roupa{
        ArrayList<Integer> precos;
        
        Roupa(int [] vet){
            for(int i=0 ; i<vet.length ; i++)
                precos.add(i, vet[i]);
        }
    }
    
    static void pd (int money, int f){
        
    }
    
    public static void main(String[] args) {
       Scanner s = new Scanner(System.in);
       int nproblemas = s.nextInt();                

       for(int i=0;i<nproblemas;i++){
           
           int money = s.nextInt();
           int nRoupas = s.nextInt();
           Roupa [] vetRoupas = new Roupa[nRoupas];
           
           
           for (int j =0 ; j<nRoupas ; j++){
               
               int nVariedade = s.nextInt();
               int [] vetPrecos = new int [nVariedade];
               
               for (int k=0; k<nVariedade; k++){
                  vetPrecos[k] = s.nextInt();
               }
               
               Roupa r = new Roupa(vetPrecos);
               vetRoupas[j] = r;
           }
           
           
       }
    }
    
}
