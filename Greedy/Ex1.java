
import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.ArrayList;

/**
 *
 * @author João Victor Lopes da Silva Guimarães - 8936843
 */
class Main {
    
    private static class Atividade implements Comparable<Atividade>{
        int start;
        int end;

        public Atividade(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Atividade o) {
            if(this.end < o.end)
                return -1;
            else if (this.end == o.end)
                return 0;
            else
                return 1;
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {


        Scanner s = new Scanner (System.in);
        int nTestes = s.nextInt();
        
        int i;
        ArrayList<Integer> respostas = new ArrayList<>();
        
        //System.out.println(nTestes);
        //Executa o algorítmo nTestes vezes
        for(i=0;i<nTestes;i++){//-----------------------------------------------
            
            //Código para nAtividades
            PriorityQueue<Atividade> pq = new PriorityQueue<>();
            int nAtividades = s.nextInt();
            int j;
            for(j=0;j<nAtividades;j++){
                int tStart = s.nextInt();
                int tEnd = s.nextInt();
                Atividade a = new Atividade(tStart, tEnd);
                pq.add(a);
            }
            Atividade escolhida=null;
            int numMax=0;
            while(!pq.isEmpty()){
                Atividade b = pq.remove();
                //Compatível
                if(escolhida == null || b.start >= escolhida.end){
                    escolhida = b;
                    numMax++;
                }
            }
        //System.out.println("NM " + numMax);
        respostas.add(numMax);
        }
        for(i=0;i<respostas.size();i++)
            System.out.println(respostas.get(i));
    }

}
