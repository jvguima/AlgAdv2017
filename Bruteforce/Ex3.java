
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 You are allowed to move the queens vertically and thus you can ONLY CHANGE THE ROW POSITIONS OF EACH QUEEN AND NOT THE COLUMN.
 A move consists of moving a queen from (R1, C) to (R2, C) where 1 ≤ R1, R2 ≤ 8 and R1 ̸= R2. You have to find the MINIMUM NUMBER OF MOVES REQUIRED to complete the task.
*/
/**
 *
 * @author João Victor L. da S. Guimarães 8936843
 */
class Main {
    
    //Wrapper para contagem de movimentos, valeu Java
    static class MoveCount{
            public int moves;

            MoveCount(int x){
                    this.moves = x;
            }
    }

    static class Rainha{
            public int x;
            public int y;//Não alterável

            Rainha(int x, int y){
                    this.x = x;
                    this.y = y;
            }
    }
    
    //Função que verifica se uma rainha pode ser posta naquela posição do tabuleiro
    static boolean canPlace(boolean[][] tabuleiro, int linha, int coluna){
        int i, j;

        for (i = 0; i < coluna; i++)
                if (tabuleiro[linha][i] == true)
                        return false;

        for (i=linha, j=coluna; i>=0 && j>=0; i--, j--)
                if (tabuleiro[i][j] == true)
                        return false;

        for (i=linha, j=coluna; j>=0 && i<8; i++, j--)
                if (tabuleiro[i][j] == true)
                        return false;

        return true;
    }

    /**
     * Função de backtracking
     * @param rec Número da recursão. Começa em 0 e acaba em 7
     * @param rainhas ArrayList armazenando as rainhas
     * @param tabuleiro 
     * @param mc Contagem de movimentos
     * @return true se a posição é válida, false se é inválida
     */
    static boolean bcktrk(int rec, ArrayList<Rainha> rainhas, boolean[][] tabuleiro, MoveCount mc){

        //Moveu todas OU a atual pode permanecer onde está
        if(rec>=7 || canPlace(tabuleiro, rainhas.get(rec).x, rainhas.get(rec).y) ){
                return true; 
        }
        
        else{
            int oldRow = rainhas.get(rec).x;
            int oldCol = rainhas.get(rec).y;    
            
            //System.out.println("REC: " + rec + "lin " + oldRow + " col " + oldCol);
            //Verifica para todas as linhas da coluna 'rec', já que há uma rainha por coluna
            for(int i = 0; i<8; i++){
                //System.out.println("\t REC: " + rec + " It " + i);
                
                //Verifica se pode colocar a rainha na posição analisada
                if(canPlace(tabuleiro, i, rainhas.get(rec).y)){
                    //System.out.println("\tREC: " + rec + " CanPlace");
                    
                    //Atualiza as posições
                    tabuleiro[oldRow][oldCol] = false;
                    tabuleiro[i][oldCol] = true;
                    
                    rainhas.get(rec).x = i;//Nova linha
                    //Faz a contagem do movimento incrementa movimento
                    mc.moves = mc.moves+1;
                    
                    //Verifica se a próxima rainha tem uma posição valida
                    boolean nextColPlace = bcktrk(rec+1, rainhas, tabuleiro, mc);
                    if(nextColPlace==true)//Se tiver, sucesso
                        return true;

                    //Deu ruim, desfaz o movimento. Tenta a próxima posição
                    mc.moves = mc.moves-1;
                    tabuleiro[oldRow][oldCol] = true;
                    tabuleiro[i][oldCol] = false;
                    rainhas.get(rec).x = oldRow;
                }
            }
            //Sem mais posições para tentar, falhou
            return false;
        }
    }

    public static void main(String[] args) {
	int nCaso=0;
	Scanner s = new Scanner(System.in);

	while(nCaso<2){
            nCaso++;

            ArrayList<Rainha> rainhas = new ArrayList<>();


            //TRUE = Ocupado, FALSE = Livre
            boolean [][] tabuleiro = new boolean [8][8];
            //Inicializar tudo com falso

            for(int i = 0; i<8; i++){

                //as colunas das rainhas são fixas
                int row = s.nextInt();
                row = row - 1;
                Rainha r = new Rainha(row, i);
                rainhas.add(r);

                Arrays.fill(tabuleiro[row], false);//Transforma a coluna em que a rainha está em "ocupada"

                //System.out.println("lin " + row + " col " + i );
            }
            MoveCount mc = new MoveCount(0);
            
            for(Rainha r : rainhas)
                 tabuleiro[r.x][r.y] = true;//OCUPADO

            
            /*for(int i =0 ; i<8; i++){
                for(int j =0; j<8; j++){
                    System.out.print(" " + tabuleiro[i][j]);
                }
                System.out.println("");
            }*/
            
            bcktrk(0, rainhas, tabuleiro, mc);
            System.out.println("Case "+ nCaso + ": "  + mc.moves);
	}
        s.close();
    }
    
}
