import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author João Victor Lopes da Silva Guimarães 8936843
 */
class Main {
    
    //Classe para armazenar restrições de lugar
    private static class Restraint{
        int persA;//person A
        int persB;//Person B
        int dist; //Negativo é ao menos, Positivo é no mínimo.

        public Restraint(int persA, int persB, int dist) {
            this.persA = persA;
            this.persB = persB;
            this.dist = dist;
        }
        
    }
    
    //Classe para "passagem de Inteiro por referencia"
    public static class IntegerHolder{
        int value;

        public IntegerHolder(int value) {
            this.value = value;
        }
    }
    
    /**
     * Método para backtracking recursivo.
     * @param nRec Número de recursão. 
     * @param groupSize Tamanho do grupo
     * @param used Vetor de quais assentos foram usados
     * @param seats Vetor de assentos com o numero da pessoa sentada em cada index
     * @param restr ArrayList contendo as restrições
     * @param validComb Classe para somar quando uma combinação é válida
     * @return True se combinação válida, False se invalida. Se não há restrições, sempre é falsa.
     */
    private static boolean backtrack(int nRec, int groupSize, boolean[] used , 
            int[] seats ,ArrayList<Restraint> restr, IntegerHolder validComb){
     
        int i;
        
        //System.out.print("\n|Rec" +nRec);
        
        //Tamanho max do vetor, limite da recursão. 
        if(nRec==groupSize){
            /*System.out.print("[");
            for(i=0;i<groupSize;i++)
                System.out.print(seats[i]+" ");
            System.out.print("]\n");*/
            
            //faz verificação de restrição, uma a uma.
            for(i=0; i<restr.size(); i++){
                Restraint r = restr.get(i);
                //j e k são as posições de PersA e PersB nos assentos
                int j,k;
                for(j=0;j<groupSize;j++){
                    if(seats[j]==r.persA)
                        break;
                }

                for(k=0;k<groupSize;k++){
                    if(seats[k]==r.persB)
                        break;
                }         
                //Distância entre lugares
                int diff = Math.abs(j-k);
                
                //Verificação At most
                if(diff <= Math.abs(r.dist) && r.dist>0){
                //System.out.println("At Most ok");
                    validComb.value++;
                    return true;
                }
                //Verificação At Least
                else if(diff >= Math.abs(r.dist) && r.dist<0){
                //System.out.println("At Least ok");
                    validComb.value++;
                    return true;
                }
                else
                    return false;
            }
            //Não há restrições, incrementa possibilidade e retorna falso para
            //realizar mais combinações na recursão anterior
            validComb.value++;
            return false;//
        }
        
        //Não é o limite de recursões
        else{
            //Para cada valor possivel remanescente, chamar recursão
            for(i=0; i<groupSize;i++){//Valores possíveis
                if(used[i]!=true){//Se o valor não foi usado ainda
                //    System.out.print("\t"+ "nextNotUsed=" + i+"\n");
                    used[i]=true;//A pessoa i sentou
                    seats[nRec] = i;//Coloca i atual no assento nRec
                    boolean res = backtrack(nRec+1, groupSize, used, seats, restr, validComb);
                    if(res == false)
                        used[i] = false;//libera a pessoa i para backtrack
                }
            }
        }
        return false;
    }
    
    
    
    public static void main(String[] args) {
        
        Scanner s = new Scanner(System.in);
        
        int i;
        int groupSize = s.nextInt();
        int restraintSize = s.nextInt();
        //Encerra com 
        while(groupSize + restraintSize != 0){          
            ArrayList<Restraint> list = new ArrayList<>();
            IntegerHolder validComb = new IntegerHolder(0);//Número de comb. válidas
                        
            for (i=0; i<restraintSize;i++){
                Restraint r = new Restraint(s.nextInt(),s.nextInt(),s.nextInt());
                list.add(r);
                //System.out.println(r.dist + " " + r.persA + " " + r.persB);
            }
            //
            for(i = 0; i<groupSize; i++){
            //    System.out.println("Rec0 i=" + i);
                int [] seats = new int [groupSize];
                boolean [] used = new boolean [groupSize];
                Arrays.fill(used,false);
                
                seats[0] = i;
                used[i] = true;
                backtrack(1,groupSize,used,seats,list, validComb);
               
            }
            System.out.println(validComb.value);
            groupSize = s.nextInt();
            restraintSize = s.nextInt();
        }
    }
    
}
