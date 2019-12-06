package ejercicios;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import us.lsi.common.Files2;

public class Ejercicio3 {

	public static Set<Integer> ejercicio3(List<Integer> lista, int a, int b, Comparator<Integer> cmp, Set<Integer> res) {
		if (lista.size() == 0) {
			return res;
		} else {
			Integer num = lista.get(lista.size()-1);
			if (cmp.compare(num,a) >= 0 && cmp.compare(num,b) < 0) {
				res.add(num);
			}
			lista.remove(num);
			return ejercicio3(lista,a,b,cmp,res);
		}
	}
	
	public static Set<Integer> ejercicio3_gen(List<Integer> lista, int a, int b, Comparator<Integer> cmp) {
		Set<Integer> res = new HashSet<Integer>();
		return ejercicio3(lista,a,b,cmp,res);
	}

	public static void main(String[] args) {
		List<String> lineas = Files2.getLines("ficheros/PI2Ej3DatosEntrada.txt");
		for (String linea: lineas) {
			String[] linea_aux = linea.split(";");
			List<Integer> lista = new ArrayList<Integer>();
			for (String num : linea_aux[0].split(",")) {
				lista.add(Integer.parseInt(num));
			}
			int a = Integer.parseInt(linea_aux[1].split(" ")[0]);
			int b = Integer.parseInt(linea_aux[1].split(" ")[1]);
			Comparator<Integer> cmp = new Comparator<Integer>() {
				public int compare(Integer a, Integer b) {
					return a.compareTo(b);
				}
			};

			System.out.println("Lista: "+lista.toString());
			System.out.println("Rango: ["+a+", "+b+")");
			System.out.println("Conjunto: "+ejercicio3_gen(lista,a,b,cmp).toString());
		}
		System.out.println("\n");
	}
	
}
