
import java.util.PriorityQueue;
import java.util.ArrayList;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author João
 */
public class BuscaAstar {
    
    /*
    IMPLEMENTAR UM COMPARADOR PARA A P-QUEUE====================================
    A maior prioridade deve ser dada à menor soma de movimentos passados e estimativa de movimentos futuros
    */
    
    public boolean buscaAstar(No noInicial){
        
        //Fila de prioridade usada no A*
        PriorityQueue<No> pq = new PriorityQueue<>();
        pq.add(noInicial);
        
        while(!pq.isEmpty()){
            No noAtual = pq.poll();
            
            //RESPOSTA
            if(noAtual.numMovFut==0){
                //Printa caminho TODO===========================================
                return true;
            }
            
            //Gera lista de movimentos válidos baseado na posição do zero.
            ArrayList<Auxiliar.Movimentos> movimentosValidos;
            movimentosValidos = Auxiliar.geraMovimentosValidos(noAtual.tabuleiro, noAtual.movFeito, noAtual.pos0);      
            
            //Para cada filho do nó atual
            for (Auxiliar.Movimentos i : movimentosValidos){
                //Cria o novo tabuleiro
                No noFilho = new No(noAtual, i);
                pq.add(noFilho);
            }
        }
        return false;
    }
}
