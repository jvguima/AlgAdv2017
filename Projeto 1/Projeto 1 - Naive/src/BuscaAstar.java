/**
 *
 * @authors João Victor Lopes da Silva Guimarães - 8936843
 *          Danilo Marques Araujo dos Santos	 - 8598670
 */

import java.util.PriorityQueue;
import java.util.ArrayList;

public class BuscaAstar {
    /**
     * Método que percorre a rota na árvore do A*, da folha até a raiz.
     * @param node
     * @param listaMovimentos 
     */
    private static void montaCaminho(No node, ArrayList<Auxiliar.Movimentos> listaMovimentos){
        while(node.pai!=null && node.movFeito != null){
            listaMovimentos.add(node.movFeito);
            node = node.pai;
        }
    }
    
    /**
     * Método de busca em A*.
     * @param tabuleiro Matriz contendo o estado inicial do tabuleiro
     * @param pos0  objeto contendo as coordenadas do espaço vazio.
     * @param listaMovimentos   Lista que armazenará a resposta final
     * @return true se achou a resposta, false se não. 
     */
    public static boolean buscaAstar(int tabuleiro[][], Pos pos0, ArrayList<Auxiliar.Movimentos> listaMovimentos){
        
        No noInicial = new No(tabuleiro, pos0);
        
        //Fila de prioridade usada no A*
        PriorityQueue<No> pq = new PriorityQueue<>();
        pq.add(noInicial);
        
        while(!pq.isEmpty()){
            No noAtual = pq.poll();

            //Não solucionável.
            if(noAtual.numMovAnt>=50){
                return false;
            }
            
            //Nó resposta
            if(noAtual.numMovFut==0){
                montaCaminho(noAtual, listaMovimentos);
                return true;
            }
            
            //Gera lista de movimentos válidos baseado na posição do zero.
            ArrayList<Auxiliar.Movimentos> movimentosValidos;
            movimentosValidos = Auxiliar.geraMovimentosValidos(noAtual.tabuleiro, noAtual.movFeito, noAtual.pos0);      
            
            //Para cada filho do nó atual
            for (Auxiliar.Movimentos i : movimentosValidos){
                //Cria o novo tabuleiro
                No noFilho = new No(noAtual, i);
                
                //Nó filho é uma resposta
                if(noFilho.numMovFut==0){
                    montaCaminho(noFilho, listaMovimentos);
                    System.out.println("\n");                
                    return true;
                }                
                pq.offer(noFilho);
            }
        }
        return false;
    }
}
