
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
    
    static enum Movimentos {R, D, L, U}
    
    static boolean emOrdem(int tabuleiro[][]){
        
	for(int i=0;i<15;i++)
		if(tabuleiro[i/4][i%4] != i+1)
			return false;
	return true;
    }
    
    /**
     * Método que gera movimentos válidos considerando a posição do espaço vazio e o ultimo movimento válido.
     * O Movimentos válidos não devem violar os limites do tabuleiro e não devem desfazer o movimento anterior
     * @param tabuleiro
     * @param ultMov Ultimo movimento realizado
     * @param pos0
     * @return 
     */
    static ArrayList<Movimentos> geraMovimentosValidos(int tabuleiro[][], Movimentos ultMov, Pos pos0){
        
        ArrayList movimentosValidos = new ArrayList<>();

        if(ultMov != null){
            if(pos0.col<3 && ultMov!=Movimentos.L)//right
                    movimentosValidos.add(Movimentos.R);        
            if(pos0.lin<3 && ultMov!=Movimentos.U)//down
                    movimentosValidos.add(Movimentos.D);            
            if(0<pos0.col && ultMov!=Movimentos.R)//left
                    movimentosValidos.add(Movimentos.L);
            if(0<pos0.lin && ultMov!=Movimentos.D)//up
                    movimentosValidos.add(Movimentos.U);
        }else{
            if(pos0.col<3)//right
                    movimentosValidos.add(Movimentos.R);
            if(pos0.lin<3)//down
                    movimentosValidos.add(Movimentos.D);            
            if(0<pos0.col)//left
                movimentosValidos.add(Movimentos.L);
            if(0<pos0.lin)//up
                    movimentosValidos.add(Movimentos.U);
        }
	return movimentosValidos;
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
   
    /**
     * Busca tradicional, backtracking cego, bruteforce.
     * @param tabuleiro
     * @param nRecursao
     * @param ListaMovimento
     * @param pos0
     * @param UltMov Ultimo movimento realizado. Null se é o primeiro movimento
     * @return 
     */
    static boolean buscaTradicional(int tabuleiro[][], int nRecursao,
            ArrayList<Movimentos> ListaMovimento, Pos pos0, Movimentos ultMov){
        
        //System.out.println("Rec:" + nRecursao);
        
        /* So you will not be allowed to use more than 50 steps to solve a puzzle. If the given initial
        configuration is not solvable you just need to print the line ‘This puzzle is not solvable.’*/
        if(nRecursao >= 50)
            return false;
        else if (emOrdem(tabuleiro))//Tabuleiro em ordem
            return true;
        //Se não está ordenado, move o espaço vazio para a próxima posição válida
        else{
            //Gera lista de movimentos válidos baseado na posição do zero
            ArrayList<Movimentos> movimentosValidos;
            movimentosValidos = geraMovimentosValidos(tabuleiro, ultMov, pos0);
            
            /*System.out.println("Movimentos Validos em " + nRecursao);
            for (Movimentos i : movimentosValidos){
                System.out.print(i+" ");
            }System.out.println("\n");*/
                        
            for (Movimentos i : movimentosValidos){

                    mover(tabuleiro, pos0, i);
                   
                    /*System.out.println("DEPOIS MOV " + i + " REC " + nRecursao);
                    for (int j =0; j<4; j++){
                        for(int k = 0; k<4; k++){
                            System.out.print(tabuleiro[j][k]+" ");
                        }
                        System.out.print("\n");
                    }*/
                    
                    
                    if(buscaTradicional(tabuleiro, nRecursao+1, ListaMovimento, pos0, i)){
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
            
            //TESTE DE MOVE
            /*mover(tabuleiro, pos0, Movimentos.L);
            System.out.println("DEPOIS");
            for (int j =0; j<4; j++){
                for(int k = 0; k<4; k++){
                    System.out.print(tabuleiro[j][k]+" ");
                }
                System.out.print("\n");
            }*/
           /* 
            ArrayList<Movimentos> movimentosValidos = geraMovimentosValidos(tabuleiro,Movimentos.L,pos0);
            for (Movimentos K : movimentosValidos){
                System.out.print(K+" ");
            }System.out.println("\n");
            
            */
           
            long startTime = System.currentTimeMillis();
            boolean success = buscaTradicional(tabuleiro, 0, listaMovimento, pos0, null);
            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            if(success){
                for(int k=listaMovimento.size()-1; k>=0; k--){
                    System.out.println(listaMovimento.get(k));
                }
            }
            else
                System.out.println( "‘This puzzle is not solvable.’");
            System.out.println("Tempo de processamento: " + totalTime + " ms");
	}
    }
    
}
