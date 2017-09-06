import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author João Victor L. da S. Guimarães - 8936843
 */
class Main {
    
    static class Road implements Comparable<Road>{
        int juncA;
        int juncB;
        int size;
        
        @Override
        public int compareTo(Road o) {
            if(this.size < o.size)
                return -1;
            else if (this.size == o.size)
                return 0;
            else
                return 1;
        }

        public Road(int juncA, int juncB, int size) {
            this.juncA = juncA;
            this.juncB = juncB;
            this.size = size;
        }
        
        
    }
    
    //--------------------------------------------------------------------------
    //UNION FIND PARA KRUSKAL
    //--------------------------------------------------------------------------
    static class subset
    {
        int parent, rank;
    };
    
    static int find(subset subsets[], int i)
    {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);

        return subsets[i].parent;
    }


    static void union(subset subsets[], int x, int y)
    {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

 
        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;

        else
        {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }
    //--------------------------------------------------------------------------


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while(true){
            
            PriorityQueue<Road> listOfRoads = new PriorityQueue<>();
            ArrayList<Road> litRoads = new ArrayList<>();
            int normalCost = 0;
            
            int numJunc = scan.nextInt();
            int numRoads = scan.nextInt();
            
            if(numJunc == 0 && numRoads ==0)
                break;
            ///-----------------------------------------------------------------
            subset subsets[] = new subset[numJunc];
            for(int i=0; i<numJunc; i++)
                subsets[i]=new subset();

            for (int v = 0; v < numJunc; v++)
            {
                subsets[v].parent = v;
                subsets[v].rank = 0;
            }


            ///-----------------------------------------------------------------
            for(int i=0; i<numRoads; i++){
                int o = scan.nextInt();
                int d = scan.nextInt();
                int w = scan.nextInt();
                Road r = new Road(o,d,w);
                normalCost += w;
                listOfRoads.add(r);
            }
          
            /*while(!listOfRoads.isEmpty()){
                Road r = listOfRoads.remove();
                System.out.println("O " + r.juncA + " D " + r.juncB + " L " + r.size);
            }*/
          
            //System.out.println("------------------------");
            while(litRoads.size() < numJunc-1){
                Road r = listOfRoads.remove();
                //System.out.println("O " + r.juncA + " D " + r.juncB + " L " + r.size);

                int x = find(subsets, r.juncA);
                int y = find(subsets, r.juncB);
                
                if(x != y){
                    //System.out.println("inc");
                    litRoads.add(r);
                    union(subsets, x, y);
                }                
            }
            
            int newCost = 0;
            for(int r = 0; r<litRoads.size(); r++){
                //System.out.println("O " + r.juncA + " D " + r.juncB + " L " + r.size);
                newCost += litRoads.get(r).size;
            }
            
            System.out.println(normalCost - newCost);
        }
    }
    
}
