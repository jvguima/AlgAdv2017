
/**
 *
 * @author João Victor Lopes da Silva Guimarães - 8936843
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    
    //Classe auxiliar para a posição do espaço vazio.
    static class Pos{
        int lin;
        int col;
        
        Pos(int lin, int col){
            this.lin = lin;
            this.col = col;
        }
        
        public void setPos(int lin, int col){
            this.lin = lin;
            this.col = col;
        }
    }
    
    
    static enum Movimentos {L, R, U, D}
    
    static boolean emOrdem(int tabuleiro[][]){
        
	for(int i=0;i<15;i++)
		if(tabuleiro[i/4][i%4] != i)
			return false;
	return true;
    }

    static ArrayList<Movimentos> geraMovimentosValidos(int tabuleiro[][], Pos pos0){
	//Verifica se tem espaço acima, abaixo e nos lados.
        ArrayList movimentos = new ArrayList<>();
	if(0<pos0.col)//left
		movimentos.add(Movimentos.L);
	if(pos0.col<3)//right
		movimentos.add(Movimentos.R);
	if(0<pos0.lin)//up
		movimentos.add(Movimentos.U);
	if(pos0.lin<3)//down
		movimentos.add(Movimentos.D);
		
	return movimentos;
    }
    
    /***
     * Função para alterar o tabuleiro movimentando o espaço vazio em uma dada direção
     * OBS: Considera que o movimento recebido é valido
     * @param tabuleiro
     * @param pos0
     * @param mov Direção para mover
     */
    static void mover(int tabuleiro[][], Pos pos0, Movimentos mov){
        int aux;//variável auxiliar para mover o espaço vazio
        int newLine=-1, newCol=-1; //Nova linha e coluna do espaço vazio
        
        if(mov != null){
            switch (mov) {
                case U:
                    newLine = pos0.lin-1;//Linhas mais altas -> mais proximo de zero
                    newCol = pos0.col;
                    break;
                case D:
                    newLine = pos0.lin+1;
                    newCol = pos0.col;
                    break;
                case L:
                    newLine = pos0.lin;
                    newCol = pos0.col-1;
                    break;
                case R:
                    newLine = pos0.lin;
                    newCol = pos0.col+1;
                    break;
            }
        }
        
        aux = tabuleiro[newLine][newCol];
        tabuleiro[newLine][newCol] = 0;
        tabuleiro[pos0.lin][pos0.col] = aux;
        
        pos0.setPos(newLine, newCol);//Atualiza a posição do zero       
    }
   
    static boolean buscaTradicional(int tabuleiro[][], int nRecursao, ArrayList<Movimentos> ListaMovimento, Pos pos0){
        
        System.out.println("Rec:" + nRecursao);
        
        /* So you will not be allowed to use more than 50 steps to solve a puzzle. If the given initial
        configuration is not solvable you just need to print the line ‘This puzzle is not solvable.’*/
        if(nRecursao >= 50) //
                return false;
        else if (emOrdem(tabuleiro))//Tabuleiro em ordem
                return true;
        //Se não está ordenado, move o espaço vazio para a próxima posição válida
        else{
            
            //Gera lista de movimentos válidos baseado na posição do zero
            ArrayList<Movimentos> movimentosValidos = geraMovimentosValidos(tabuleiro, pos0);
            
            for (Movimentos i : movimentosValidos){

                    mover(tabuleiro, pos0, i);

                    if(buscaTradicional(tabuleiro, nRecursao+1, ListaMovimento, pos0)){
                            ListaMovimento.add(i);
                            return true;
                    }
                    //Não deu certo, desfaz o movimento
                    switch(i){
                        case U:
                            mover(tabuleiro, pos0, Movimentos.D);
                            break;
                        case D:
                            mover(tabuleiro, pos0, Movimentos.U);
                            break;
                        case L:
                            mover(tabuleiro, pos0, Movimentos.R);
                            break;
                        case R:
                            mover(tabuleiro, pos0, Movimentos.L);
                            break;                                
                    }
            }
            //Gastou todos os movimentos válidos
            return false;
        }
    }    
   
    public static void main(String[] args) {
	Scanner s = new Scanner(System.in);
        int nproblemas = s.nextInt();                
                
	for(int i=0;i<nproblemas;i++){
            
            //Usar uma matriz 4 por 4 com o espaço vazio como número zero
            int tabuleiro[][] = new int[4][4]; //<= input da configuração

            Pos pos0=null; // Onde o zero está no tabuleiro

            for (int j =0; j<4; j++){
                for(int k = 0; k<4; k++){
                    int aux = s.nextInt();
                    tabuleiro[j][k] = aux;
                    if(aux==0)
                        pos0 = new Pos(j,k);
                }
            }
                
            ArrayList listaMovimento = new ArrayList<>(); //Lista que vai armazenar os movimentos feitos

            //ArrayList moveValidos = geraMovimentosValidos(tabuleiro, pos0);
            //moveValidos.forEach((t) -> {System.out.println(t);});
            
            /*System.out.println("ANTES");
            
            for (int j =0; j<4; j++){
                for(int k = 0; k<4; k++){
                    System.out.print(tabuleiro[j][k]+" ");
                }
                System.out.print("\n");
            }
            
            mover(tabuleiro, pos0, Movimentos.D);
            System.out.println("DEPOIS");
            for (int j =0; j<4; j++){
                for(int k = 0; k<4; k++){
                    System.out.print(tabuleiro[j][k]+" ");
                }
                System.out.print("\n");
            }*/
            
            if(buscaTradicional(tabuleiro, 0, listaMovimento, pos0)){
                listaMovimento.forEach((t) -> {System.out.println(t);});
                System.out.println("Tempo de processamento");
            }
            else
                System.out.println( "‘This puzzle is not solvable.’ Tempo de processamento");
	}
    }
    
}
