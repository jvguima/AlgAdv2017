/**
 *integer N, to make a palidrome (word that reads the same when you reverse it) of length at least N. Any palindrome will do
 * 
 The problem with the code is that the strings generated are often not palindromic. 
 
 you decide to simply write some additional code that takes the output and adds just enough extra characters to it to make it a palindrome and hope for the best
 
 Your solution should take as its input a string and produce the smallest palindrome that can be formed by adding zero or more characters at its end.
 */
  
 
/**
 *
 * @author João Victor L. da S. Guimarães
 */
 
import java.util.Scanner;

 
class Main{	
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int caseCount = 0;
		
		while(caseCount <= 3/*s.hasNextString()*/){
			//System.out.println("\nTESTE " + caseCount +"--------");
			
			String str = new String (s.nextLine());
			String aux = new String();

			/*
				COMEÇA ANALISANDO DO ULTIMO CHAR. ENQUANTO O ANALISADO FOR DIFERENTE DO ULTIMO, VAI COLOCANDO EM UMA STRING AUXILIAR
				QUANDO ANALISAR TUDO, CONCATENA A AUXILIAR AO CONTRÁRIO NA PRINCIPAL
			*/
			
			int end = str.length()-1;//Aponta para o ultimo caracter de str
			int beg = 0;
			/*Last Non Palindromic = posição na string do ultimo caracter não palindrômico. Ele é usado caso um caracter não palindromico
			seja encontrado dentro do que aparentava ser um palindromo para concatenar tudo entre o ultimo encontrado e o atual
			ex: amanaplaXnacanal
			*/
			int lnp = 0;
			
			while(end>beg){//Se beg>end ou beg==end chegamos ao fim do processo
				if(str.charAt(beg) != str.charAt(end)){
					
					if(lnp==beg){//Inicio
						//System.out.println("Concat: " + str.charAt(beg));
						aux += str.charAt(beg);
						lnp++;
					}
					//Vai concatenando tudo entre beg e lnp
					else{
						int i;
						for(i = lnp; i<=beg; i++){
							//System.out.println("Concat: " + str.charAt(beg));
							aux += str.charAt(i);
						}
						lnp=i;
					}
					beg++;
				}
				//Mesmo caracter
				else{
					end--;
					beg++;
				}
			}
			//System.out.println("\tAux: " + aux);
			//str.concat(aux);
			System.out.print(str);
			if(aux.length()>0){
				for(int j = aux.length()-1; j>=0; j--){
					System.out.print("" + aux.charAt(j));
				}
			}
			System.out.print("\n");
			caseCount++;
		}
	}
 }
 
 /*
	Pega o ponteiro do ultimo adicionado no aux. Dessa forma se coisas anomalas como o X abaixo acontecerem, vai concatenando tudo desde a ultima posição até o X
	amanaplaXnacanal
	
 
 */