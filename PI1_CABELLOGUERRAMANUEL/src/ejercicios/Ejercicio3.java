package ejercicios;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import us.lsi.common.Files2;
import us.lsi.common.Pair;

public class Ejercicio3 {
	
	// Soluci�n iterativa
	public static boolean ejercicio3_itera(String cadena) {
		boolean res = true;
		int i = 0;
		int j = cadena.length()-1;
		
		while (i <= j) {
			if (cadena.charAt(i) != cadena.charAt(j)) {
				res = false;
				break;
			}
			i++; j--;
		}
		
		return res;
	}
	
	// Soluci�n recursiva final
	public static boolean ejercicio3_recur_final(String cadena, int i, int j, boolean w) {
		if (i >= j || !w) {
			return w;
		} else {
			return ejercicio3_recur_final(cadena, i+1, j-1, w && cadena.charAt(i) == cadena.charAt(j)); 
		}
	}
	
	public static boolean ejercicio3_recur_final_gen(String cadena) {
		return ejercicio3_recur_final(cadena,0,cadena.length()-1,true);
	}
	
	// Soluci�n recursiva no final
	public static boolean ejercicio3_recur_no_final(String cadena, int i, int j) {
		boolean res = cadena.charAt(i) == cadena.charAt(j);
		if (i >= j || !res) {
			return res;
		} else {
			return ejercicio3_recur_no_final(cadena, i+1, j-1) && res;
		}
	}
	
	// Soluci�n funcional
	public static boolean ejercicio3_funcional(String cadena) {
		Pair<Integer,Integer> seed = Pair.of(0, cadena.length()-1);
		Predicate<Pair<Integer,Integer>> pred = p-> p.a <= p.b;
		UnaryOperator<Pair<Integer,Integer>> xor = aux -> Pair.of(aux.a+1, aux.b-1);
		Predicate<Pair<Integer,Integer>> pred2 = p-> cadena.charAt(p.a) == cadena.charAt(p.b);
		return Stream.iterate(seed, pred, xor).allMatch(pred2);
	}

	// #######################################################################################################################
	// Resultados
	public static void main(String[] args) {
		List<String> lineas = Files2.getLines("ficheros/PI1Ej3DatosEntrada.txt");
		
		// Resultado iterativo
		int cont = 0;
		for (String linea: lineas) {
			boolean res = ejercicio3_itera(linea);
			if (res) {
				cont++;
			}
			System.out.println("esPalindromoIterativa("+linea+")="+res);
		}
		System.out.println(cont+" pal�ndromos de "+lineas.size()+" palabras\n");
		
		// Resultado recursivo no final
		cont = 0;
		for (String linea: lineas) {
			int i = 0; int j = linea.length()-1;
			boolean res = ejercicio3_recur_no_final(linea,i,j);
			if (res) {
				cont++;
			}
			System.out.println("esPalindromoNoFinal("+linea+")="+res);
		}
		System.out.println(cont+" pal�ndromos de "+lineas.size()+" palabras\n");
		
		// Resultado recursivo final
		cont = 0;
		for (String linea: lineas) {
			boolean res = ejercicio3_recur_final_gen(linea);
			if (res) {
				cont++;
			}
			System.out.println("esPalindromoFinal("+linea+")="+res);
		}
		System.out.println(cont+" pal�ndromos de "+lineas.size()+" palabras\n");
		
		// Resultado funcional
		cont = 0;
		for (String linea: lineas) {
			boolean res = ejercicio3_funcional(linea);
			if (res) {
				cont++;
			}
			System.out.println("esPalindromoStream("+linea+")="+res);
		}
		System.out.println(cont+" pal�ndromos de "+lineas.size()+" palabras\n");
	}
}