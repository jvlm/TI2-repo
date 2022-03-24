import java.util.Scanner;

/**
 * 
 */

/**
 * @author Joao Vitor Lima de Melo
 *
 */
public class SomaDoisNumeros {
	
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		//Declaracao de variaveis
		int n1, n2, soma;
		
		//Leituras
		System.out.println("Digite um número");
		n1 = sc.nextInt();
		System.out.println("Digite outro número");
		n2 = sc.nextInt();
		
		//Somar
		soma = n1 + n2;
		
		//Mostrar na tela
		System.out.println("Soma = " + soma);
		
		
	}
	
}
