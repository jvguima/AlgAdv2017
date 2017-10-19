/**
 *
 * @authors João Victor Lopes da Silva Guimarães - 8936843
 *          Danilo Marques Araujo dos Santos	 - 8598670
 */

public class No implements Comparable<No> {
    /***
    * Classe de Nó usada na modelagem de Árvore do A*.
    */
    public No pai;
    public int tabuleiro[][];
    public Pos pos0;
    public Auxiliar.Movimentos movFeito;//Movimento feito para deixar 
    public int numMovAnt;//Número de movimentos anteriores
    public int numMovFut;//Número previsto de movimentos futuros.
    
    
    /**
     * Método para a heurística. 
     * Calcula a estimativa de movimentos a serem realizados baseado 
     * no número de elementos fora de ordem.
     * @param tab
     * @return 
     */
    public int calcMovFuturos(int [][] tabuleiro){
        int movEstimados = 0;
        for(int i=0;i<15;i++)
            if(tabuleiro[i/4][i%4] != i+1)
		movEstimados++;
        
        return movEstimados;
    }
    
    @Override
    public int compareTo(No o) {
        
        //Menor soma de anterior com futuro = MAIOR PRIORIDADE
        if(this.numMovAnt + this.numMovFut < o.numMovAnt + o.numMovAnt)
            return -1;
        else if (this.numMovAnt + this.numMovFut == o.numMovAnt + o.numMovAnt)
            return 0;
        else
            return 1;
    }   
    
    /**
     * Construtor para o Nó raiz da árvore
     * @param tab Estado inicial do tabuleiro
     * @param pos0 Posição do zero
     */
    public No (int[][] tab, Pos pos0){
        this.pai=null;
        this.movFeito=null;
        this.numMovAnt=0;
        this.tabuleiro= tab;
        this.pos0 = pos0;

        this.numMovFut = calcMovFuturos(this.tabuleiro);
    }

    /**
     * Construtor para nós não-raiz da árvore.
     * @param pai Nó pai
     * @param mov Movimento a ser feito para reconfigurar o tabuleiro
     */
    public No (No pai, Auxiliar.Movimentos mov){
        if(pai != null && mov != null){
            int tabFilho[][] = new int[4][4];;
            for(int j=0; j<4; j++){
                tabFilho[j] = pai.tabuleiro[j].clone();
            }
            Pos pos0Filho = new Pos(pai.pos0.lin, pai.pos0.col);
            Auxiliar.mover(tabFilho, pos0Filho, mov);

            this.tabuleiro = tabFilho;
            this.pos0 = pos0Filho;
            this.movFeito = mov;
            this.numMovAnt = pai.numMovAnt+1;
            this.pai = pai;
            this.numMovFut = calcMovFuturos(this.tabuleiro);
        }
    }

    public No(int numMovAnt, int numMovFut) {
        this.pai=null;
        this.tabuleiro = null;
        this.pos0 = null;
        this.movFeito = null;
        this.numMovAnt = numMovAnt;
        this.numMovFut = numMovFut;
    }
    
    
}
