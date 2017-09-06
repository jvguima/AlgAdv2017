import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author João Victor L. da S. Guimarães - 8936843
 */
class Main {

    public static void main(String[] args) {
        
        boolean quit = false;
        Scanner scan = new Scanner (System.in);

        //while(!quit){
        while(scan.hasNext()){
            ArrayList<Integer> specimens = new ArrayList<>();

            int c = scan.nextInt();//Numero de camaras/jaulas
            int s = scan.nextInt();//Numero de especimes
            double totalMass = 0;
            for(int i = 0; i<s; i++){//Adiciona as massas
                int mi = scan.nextInt();
                specimens.add(mi);
                totalMass += mi;
            }
            //Adiciona dummies para equilibrio
            while(specimens.size() < 2*c)
                specimens.add(0);

            Collections.sort(specimens);

            double averageMass = totalMass/c;
            double totalImbalance = 0;

            /*for(int f =0; f < specimens.size() ; f++)
                System.out.print("!" + specimens.get(f) + " ");
            System.out.println("\nAM = " + averageMass);*/

            for(int i=0; i<c; i++){
                double itImbalance = Math.abs(specimens.get(i) + 
                        specimens.get(specimens.size()-(i+1)) - averageMass);
                //System.out.println("i Imb = " + itImbalance);

                totalImbalance += itImbalance;
            }
            System.out.println("IMBALANCE = " + new DecimalFormat("#0.00000").format(totalImbalance));
            /*if(!scan.hasNext())
                quit = true;*/
        }
    }
    
}
