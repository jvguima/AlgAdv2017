/**
 *
 * @authors João Victor Lopes da Silva Guimarães - 8936843
 *		   	Danilo Marques Araujo dos Santos	 - 8598670
 */

import java.util.ArrayList;
import java.util.Scanner;

// Classe que contém os métodos auxiliares para o programa
class Auxiliar {
	enum Movimentos {R, D, L, U}
	
	/**
	 * Método que verifica se o tabuleiro está em ordem, i.e.,
	 * se o puzzle foi resolvido
	 * @param tabuleiro
	 * @return 
	 */
	public static boolean emOrdem(int tabuleiro[][]) {
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
	public static ArrayList<Movimentos> geraMovimentosValidos(int tabuleiro[][], Movimentos ultMov, Pos pos0) {
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
		}
		else{
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
    static void mover(int tabuleiro[][], Pos pos0, Movimentos mov) {
        // Variável auxiliar para mover o espaço vazio
        int aux;
        
        // Nova linha e coluna do espaço vazio
        int newLine=-1, newCol=-1;
        
        if(mov != null) {
            switch (mov) {
                case U:
                    newLine = pos0.lin-1; // Linhas mais altas -> mais proximo de zero
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
        
        // Atualiza a posição do zero       
        pos0.setPos(newLine, newCol);
    }
}
