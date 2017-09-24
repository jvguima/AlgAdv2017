/**
 *
 * @author João Victor L. da S. Guimarães
 */
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;

class Main {

    static class Ponto {
        public int x;
        public int y;
        
        public Ponto(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    /*
    Output 3 numbers a b c, where a,b (a<b) are the indexes (0 based) 
    of the point pair in the input and c is the distance between them. 
    Round c to 6 decimal digits.
    */
    static class DistPonto{
        Ponto p0;
        Ponto p1;
        double dist;

        public DistPonto(Ponto p0, Ponto p1, double dist) {
            this.p0 = p0;
            this.p1 = p1;
            this.dist = dist;
        }
        
    }
    
    static class ComparatorPontoX implements Comparator<Ponto>{
        @Override
        public int compare(Ponto p1, Ponto p2) {
            if(p1.x < p2.x)
                return -1;
            else if(p1.x == p2.x)
                return 0;
            else
                return 1;
        }
    }
    
    
       static class ComparatorPontoY implements Comparator<Ponto>{
        @Override
        public int compare(Ponto p1, Ponto p2) {
            if(p1.y < p2.y)
                return -1;
            else if(p1.y == p2.y)
                return 0;
            else
                return 1;
        }
    } 
    
    
    static DistPonto closestPair(Ponto lista[]){
        
        int median = lista[(lista.length/2)].x;//Pega valor mediano de X.
        
        if(lista.length==1){
            return new DistPonto(lista[0],lista[0],Integer.MAX_VALUE);
        }
        else{
            
            //Divide a lista em duas menores
            Ponto l1[] = Arrays.copyOfRange(lista, 0, lista.length/2);
            Ponto l2[] = Arrays.copyOfRange(lista, lista.length/2, lista.length);


            /*System.out.println("\tLIST1");
            for(int i=0; i<l1.length; i++){
                System.out.println("\tX " + l1[i].x + " Y " + l1[i].y);
            }
            System.out.println("\tLIST2");
            for(int i=0; i<l2.length; i++){
                System.out.println("\tX " + l2[i].x + " Y " + l2[i].y);
            }*/


            DistPonto d1 = closestPair(l1);
            /*System.out.println("\tD1 X0:" + d1.p0.x + " Y0:"+ d1.p0.y
            +" - X1:" + d1.p1.x + " Y1:"+ d1.p1.y);*/
            
            DistPonto d2 = closestPair(l2);
            /*System.out.println("\tD2 X0:" + d2.p0.x + " Y0:"+ d2.p0.y
            +" - X1:" + d2.p1.x + " Y1:"+ d2.p1.y);*/
            
            
            //Pontos para retornar, distancia = delta
            DistPonto pontosMin;
            
            //Pegue os pontos com menor distância como candidatos
            if(d1.dist<d2.dist){
                pontosMin = new DistPonto(d1.p0,d1.p1,d1.dist);
            }
            else{
                pontosMin = new DistPonto(d2.p0,d2.p1,d2.dist);
            }
            
            //Lista para pontos dentro da faixa de Delta
            ArrayList<Ponto> dentroDeDelta = new ArrayList<>();
            /*int i=lista.length/2;
            while(i<lista.length && Math.abs(lista[i].x - median) <= pontosMin.dist){
                dentroDeDelta.add(lista[i]);
                i++;
            }
    
            i=lista.length/2;
            while(i>0 && Math.abs(lista[i].x - median) <= pontosMin.dist){
                dentroDeDelta.add(lista[i]);
                i--;
            }*/
            int i;
            for(i = 0; i<lista.length; i++){
                if(Math.abs(lista[i].x - median) <= pontosMin.dist)
                    dentroDeDelta.add(lista[i]);
            }

            ComparatorPontoY c = new ComparatorPontoY();
            
            /*System.err.println("DELTA: " + pontosMin.dist);
            System.out.println("\tDentro de Delta");
            for(i=0; i<dentroDeDelta.size(); i++){
                System.out.println("\tX " + dentroDeDelta.get(i).x + 
                        " Y " + dentroDeDelta.get(i).y);
            }*/
            dentroDeDelta.sort(c);

            for(i=0; i<dentroDeDelta.size(); i++){
                for(int j=0; j<11; j++){
                    if(j+i>=dentroDeDelta.size())
                        break;
                    
                    int dist = Math.abs(dentroDeDelta.get(i).y - dentroDeDelta.get(i+j).y);
                    
                    //Pontos distintos e menores do que delta.
                    if(dentroDeDelta.get(i) != dentroDeDelta.get(i+j) && 
                            dist < pontosMin.dist ){
                        pontosMin.p0 = dentroDeDelta.get(i);
                        pontosMin.p1 = dentroDeDelta.get(i+j);
                        pontosMin.dist = dist; 
                    }
                }
            }
            //Agora com os pontos selecionados, calcula a verdadeira distancia entre eles
            double distX = Math.pow(pontosMin.p1.x - pontosMin.p0.x,2);
            double distY = Math.pow(pontosMin.p1.y - pontosMin.p0.y,2);
            
            pontosMin.dist = Math.sqrt(distX + distY);
            return pontosMin;
        }
    }
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ComparatorPontoX c = new ComparatorPontoX();
        
        int nPontos = s.nextInt();
        Ponto [] entrada = new Ponto[nPontos];
        Ponto [] lista = new Ponto[nPontos];
                
        for(int i=0; i<nPontos; i++){
            Ponto p = new Ponto(s.nextInt(),s.nextInt());
            entrada[i] = p;
        }
        //Copia para não alterar a entrada
        
        lista = Arrays.copyOf(entrada, nPontos);
        Arrays.sort(lista, c);
        
        DistPonto dp = closestPair(lista);
        int p0Index=-1, p1Index=-1;
        
        for(int i=0; i<nPontos; i++){
            if(entrada[i].equals(dp.p0))
                p0Index = i;
            else if(entrada[i].equals(dp.p1))
                p1Index = i;
        }
        
        System.out.println(p0Index + " " + p1Index + " " 
                + new DecimalFormat("#0.000000").format(dp.dist));
    }
    
}
