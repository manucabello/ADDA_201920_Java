package ejercicios;

import java.util.List;

import us.lsi.common.Files2;

public class Ejercicio3 {
	
	// Solución iterativa
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
	
	// Solución recursiva final
	public static boolean ejercicio3_recur_final(String cadena, int i, int j, boolean w) {
		if (i >= j) {
			return w;
		} else {
			return ejercicio3_recur_final(cadena, i+1, j-1, w && cadena.charAt(i) == cadena.charAt(j)); 
		}
	}
	
	public static boolean ejercicio3_recur_final_gen(String cadena) {
		return ejercicio3_recur_final(cadena,0,cadena.length()-1,true);
	}
	
	// Solución recursiva no final
	public static boolean ejercicio3_recur_no_final(String cadena, int i, int j) {
		if (i >= j) {
			return cadena.charAt(i) == cadena.charAt(j);
		} else {
			return ejercicio3_recur_no_final(cadena, i+1, j-1) && cadena.charAt(i) == cadena.charAt(j); 
		}
	}

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
		System.out.println(cont+" palíndromos de "+lineas.size()+" palabras\n");
		
		// Resultado recursivo final
		cont = 0;
		for (String linea: lineas) {
			boolean res = ejercicio3_recur_final_gen(linea);
			if (res) {
				cont++;
			}
			System.out.println("esPalindromoIterativa("+linea+")="+res);
		}
		System.out.println(cont+" palíndromos de "+lineas.size()+" palabras\n");
		
		// Resultado recursivo no final
		cont = 0;
		for (String linea: lineas) {
			int i = 0; int j = linea.length()-1;
			boolean res = ejercicio3_recur_no_final(linea,i,j);
			if (res) {
				cont++;
			}
			System.out.println("esPalindromoIterativa("+linea+")="+res);
		}
		System.out.println(cont+" palíndromos de "+lineas.size()+" palabras\n");
	}
}