package bruteforce1;

import java.util.Scanner;

/**
 *
 * @author João Victor Lopes da Silva Guimarães 8936843
 */
class Main {
    
    
    /**
     * Função backtracking recursiva para bruteforce
     * @param a = x+y+z
     * @param b = x*y*z
     * @param c = x^2+y^2+z^2
     * @param nRec = Numero de recursões, limitado de 0 a 2
     * @return true se encontrou uma solução, false se não.
	 * Cada nivel de profundidade da recursão equivale a uma das variáveis a ser encontrada.
     */
    static boolean backtracking(int a, int b, int c, int nRec){
        
        //Caso o número de recursões seja maior do que 2, falha
		//Caso a, b ou c seja menor do que zero, falha.
    	if(nRec<2 && a>0 && b>0 && c>0){
            int i;
            for(i=1; i<=10000; i++){
                    if(i==a && i==b && i==c){
                        System.out.print(i + " ");
                        return true;
                    }
                    else{
                        boolean resultado = backtracking(a-i,b/i,c-i*i, nRec++);
                        //Comba a resposta com a i atual se for valida, se n , passa pro próximo loop
                        if(resultado == true){
                            System.out.print(i + " ");
                            return true;
                        }
                    }
            }
            return false;//fracasso
	}
        return false;//fracasso
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        
        int nCasos = s.nextInt();
        
        for (int i = 0; i<nCasos; i++){
            
            int a = s.nextInt();
            int b = s.nextInt();
            int c = s.nextInt();

            int x;
            /**
             * Inicializa x com 1 para que seja o valor menos restritivo.
             * y e z então devem assumir valores tal que 
             * y + z = A - x, e assim em diante.
             */
            for(x = 1;x<=10000; x++){
                boolean res = backtracking(a-x,b/x,c-x*x,0);
                if(res == true){
                    System.out.println(x);
                    break;
                }
            }
            if(x>10000)
                System.out.println("No solution");
        }
    }
    
}
