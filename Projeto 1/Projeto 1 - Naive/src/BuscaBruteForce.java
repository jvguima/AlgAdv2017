/**
 *
 * @authors João Victor Lopes da Silva Guimarães - 8936843
 *          Danilo Marques Araujo dos Santos	 - 8598670
 */
 
import java.util.ArrayList;
 
class BuscaBruteForce {
	/**
     * Busca tradicional, backtracking cego, bruteforce.
     * @param tabuleiro
     * @param nRecursao
     * @param ListaMovimento
     * @param pos0
     * @param UltMov Ultimo movimento realizado. Null se é o primeiro movimento
     * @return 
     */
    static boolean buscaBruteForce(int tabuleiro[][], int nRecursao,
	ArrayList<Auxiliar.Movimentos> ListaMovimento, Pos pos0, Auxiliar.Movimentos ultMov) {
                
        /* So you will not be allowed to use more than 50 steps to solve a puzzle. If the given initial
        configuration is not solvable you just need to print the line ‘This puzzle is not solvable.’*/
        if(nRecursao >= 50)
            return false;
        else if (Auxiliar.emOrdem(tabuleiro))//Tabuleiro em ordem
            return true;
        //Se não está ordenado, move o espaço vazio para a próxima posição válida
        else{
            //Gera lista de movimentos válidos baseado na posição do zero
            ArrayList<Auxiliar.Movimentos> movimentosValidos;
            movimentosValidos = Auxiliar.geraMovimentosValidos(tabuleiro, ultMov, pos0);
            
            /*System.out.println("Movimentos Validos em " + nRecursao);
            for (Movimentos i : movimentosValidos){
                System.out.print(i+" ");
            }System.out.println("\n");*/
                        
            for (Auxiliar.Movimentos i : movimentosValidos){
				Auxiliar.mover(tabuleiro, pos0, i);
			   
				/*System.out.println("DEPOIS MOV " + i + " REC " + nRecursao);
				for (int j =0; j<4; j++){
					for(int k = 0; k<4; k++){
						System.out.print(tabuleiro[j][k]+" ");
					}
					System.out.print("\n");
				}*/
				
				
				if(buscaBruteForce(tabuleiro, nRecursao+1, ListaMovimento, pos0, i)){
					ListaMovimento.add(i);
					return true;
				}
				//Não deu certo, desfaz o movimento
				switch(i){
					case U:
						Auxiliar.mover(tabuleiro, pos0, Auxiliar.Movimentos.D);
						break;
					case D:
						Auxiliar.mover(tabuleiro, pos0, Auxiliar.Movimentos.U);
						break;
					case L:
						Auxiliar.mover(tabuleiro, pos0, Auxiliar.Movimentos.R);
						break;
					case R:
						Auxiliar.mover(tabuleiro, pos0, Auxiliar.Movimentos.L);
						break;                                
				}
            }
            //Gastou todos os movimentos válidos
            return false;
        }
    }
}
