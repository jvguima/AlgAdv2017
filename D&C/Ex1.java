/**
 *
 * @author João Victor L. da S. Guimarães - 8936843
 */
import java.util.Arrays;
import java.util.Scanner;

class Main {
    
    static class ArrayHolder{
        public int []vet;//Vetor
        public int inv;//Quantidade de inversões no vet.

        public ArrayHolder(int[] vet, int inv) {
            this.vet = vet;
            this.inv = inv;
        }
        
    }
    
    public static ArrayHolder invcnt(int vet[]){
        //Condição de parada, retornar a Lista e 0
        if(vet.length == 1)
            return new ArrayHolder(vet,0);
        
        else{//Divide o vetor em 2. Conta inversões em cada um.
            
            int [] v1 = Arrays.copyOfRange(vet, 0, vet.length/2);
            int [] v2 = Arrays.copyOfRange(vet, vet.length/2, vet.length);
            
            ArrayHolder ah1 = invcnt(v1);
            ArrayHolder ah2 = invcnt(v2);
            
            
            /*Recebe ambos os vetores separados ordenados e com a contagem de 
            suas inversões*/            
           /* System.out.print("AH1[");
            for(int j = 0; j<ah1.vet.length;j++)
                System.out.print(ah1.vet[j]+",");
            System.out.println("]");
            
            System.out.print("AH2[");
            for(int j = 0; j<ah2.vet.length;j++)
                System.out.print(ah2.vet[j]+",");
            System.out.println("]");*/

            
            //Contagem de inversão.
            int count=ah1.inv + ah2.inv;     
            
            for(int i=0; i<ah1.vet.length; i++){                
                for(int j=0; j<ah2.vet.length; j++){
                    //System.out.println("\tcmp: " + ah1.vet[i]+ " e " + ah2.vet[j]);
                    if(ah1.vet[i] > ah2.vet[j]){
                        //System.out.println("\t" + ah1.vet[i]+ " > " + ah2.vet[j]);
                        count++;
                    }
                }
            }
            
            //System.out.println("COUNT: " + count+"\n");
            
            //União dos dois vetores ordenados.
            int [] vf = new int[vet.length];//Vetor final
            System.arraycopy(ah1.vet, 0, vf, 0, ah1.vet.length);
            System.arraycopy(ah2.vet, 0, vf, ah1.vet.length, ah2.vet.length);
            
            Arrays.sort(vf);
            
            return new ArrayHolder(vf, count);
        }
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner s = new Scanner(System.in);
        
        int nTests = s.nextInt();
        
        //Loop para os nTests casos de teste
        for(int i = 0; i<nTests; i++){
            int [] vet = new int[s.nextInt()];
            
            for(int j = 0; j<vet.length;j++)
                vet[j] = s.nextInt();
            
            /*System.out.print("[");
            for(int j = 0; j<vet.length;j++)
                System.out.print(vet[j]+",");
            System.out.println("]");*/
            ArrayHolder ah = invcnt(vet);
            System.out.println(ah.inv);
        }
    }
    
}
