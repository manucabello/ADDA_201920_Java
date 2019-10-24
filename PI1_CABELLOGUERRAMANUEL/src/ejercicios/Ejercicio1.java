package ejercicios;

import java.util.ArrayList;
import java.util.List;

import us.lsi.common.Files2;;

public class Ejercicio1 {
	
	public static List<Integer> pares_al_cuadrado(String linea) {
		List<Integer> res = new ArrayList<Integer>();
		String[] elementos = linea.split(",");
		int index = 0;
		while (index < elementos.length && !elementos[index].isBlank()) {
			int numero = Integer.valueOf(elementos[index]);
			if (numero%2 == 0) {
				res.add((int) Math.pow(numero,2));
			}
			index++;
		}
		return res;
	}

	public static void main(String[] args) {
		List<String> lineas = Files2.getLines("ficheros/PI1Ej1DatosEntrada.txt");
		for (String linea: lineas) {
			System.out.println("Entrada: ["+linea+"]");
			System.out.println("Salida: "+pares_al_cuadrado(linea));
			System.out.println("========================================");
		}
	}

}
