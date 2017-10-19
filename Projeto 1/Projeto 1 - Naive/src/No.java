
public class No {
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

            this.numMovFut = calcMovFuturos(this.tabuleiro);
        }

    }
        
}
