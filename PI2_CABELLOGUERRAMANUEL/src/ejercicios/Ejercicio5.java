package ejercicios;

import java.util.Arrays;
import java.util.List;

import us.lsi.common.Files2;

public class Ejercicio5 {
	
	public static Integer ejercicio5(int[] histograma, int height) {
		int cont = 0;
		int max = 0;
		if (height <= 0) {
			return 0;
		} else {
			for (int i = 0; i < histograma.length; i++) {
				if (histograma[i] >= height) {
					cont++;
				} else {
					max = Integer.max(max, cont);
					cont = 0;
				}
			}
			max = Integer.max(max, cont);
			return Integer.max(height*max,ejercicio5(histograma,height-1));
		}
	}
	
	public static Integer ejercicio5_gen(int[] histograma) {
		return ejercicio5(histograma, maxValue(histograma));
	}
	
	public static void main(String[] args) {
		List<String> lineas = Files2.getLines("ficheros/PI2Ej5DatosEntrada.txt");
		for (String linea: lineas) {
			String[] histograma_aux = linea.split(",");
			int[] histograma = new int[histograma_aux.length];
			for (int i = 0; i < histograma_aux.length; i++) {
				histograma[i] = Integer.valueOf(histograma_aux[i]);
			}
			System.out.println("Histograma: "+Arrays.toString(histograma));
			System.out.println("Área máxima: "+ejercicio5_gen(histograma)+"\n");
		}
	}
	
	public static int maxValue(int[] array) {
		int res = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] > res) {
				res = array[i];
			}
		}
		return res;
	}

}
