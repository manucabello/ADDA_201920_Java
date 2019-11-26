package ejercicios;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import us.lsi.common.Files2;
import us.lsi.common.Pair;

public class Ejercicio5 {
	
	// Solución iterativa
	public static Pair<Integer,Integer> ejercicio5_itera(int a, int b) {
		Pair<Integer,Integer> res = Pair.of(0, a);
		while(a >= b) {
			a = a-b;
			res = Pair.of(res.a+1, a);
		}
		return res;
	}
	
	// Solución recursiva final
	public static Pair<Integer,Integer> ejercicio5_recur_final(int a, int b, Pair<Integer,Integer> res) {
		if (a < b) {
			return res;
		} else {
			res = Pair.of(res.a+1, a-b);
			return ejercicio5_recur_final(res.b,b,res);
		}
	}
	
	public static Pair<Integer,Integer> ejercicio5_recur_final_gen(int a, int b) {
		Pair<Integer,Integer> res = Pair.of(0, a);
		return ejercicio5_recur_final(a,b,res);
	}
	
	// Solución recursiva no final
	public static Pair<Integer,Integer> ejercicio5_recur_no_final(int a, int b) {
		Pair<Integer,Integer> res = Pair.of(0, a);
		if (a < b) {
			res = Pair.of(0, a);
		} else {
			Pair<Integer,Integer> aux = ejercicio5_recur_no_final(a-b,b);
			res = Pair.of(aux.a+1, aux.b);
		}
		return res;
	}
	
	// Solución funcional
	public static Pair<Integer,Integer> ejercicio5_funcional(int a, int b) {
		Pair<Integer,Integer> seed = Pair.of(0,a);
		Predicate<Pair<Integer,Integer>> pred = p-> p.b >= b;
		UnaryOperator<Pair<Integer,Integer>> xor = aux -> Pair.of(aux.a+1, aux.b-b);
		List<Pair<Integer,Integer>> list_res = Stream.iterate(seed, pred, xor).collect(Collectors.toList());
		try {
			return list_res.get(list_res.size()-1);
		} catch (Exception e) {
			return seed;
		}
	}
	
	// #######################################################################################################################
	// Resultados
	public static void main(String[] args) {
		List<String> lineas = Files2.getLines("ficheros/PI1Ej5DatosEntrada.txt");
		
		System.out.println("-------------------- TEST DEL MÉTODO ITERATIVO --------------------");
		for (String linea: lineas) {
			String[] linea_array = linea.split(",");
			int a = Integer.parseInt(linea_array[0]);
			int b = Integer.parseInt(linea_array[1]);
			Pair<Integer,Integer> res = ejercicio5_itera(a,b);
			System.out.println("Entrada: ("+a+","+b+")");
			System.out.println("Salida:  ("+res.a+","+res.b+")");
			System.out.println("========================================");
		}
		System.out.println("");
		
		System.out.println("-------------------- TEST DEL MÉTODO FINAL --------------------");
		for (String linea: lineas) {
			String[] linea_array = linea.split(",");
			int a = Integer.parseInt(linea_array[0]);
			int b = Integer.parseInt(linea_array[1]);
			Pair<Integer,Integer> res = ejercicio5_recur_final_gen(a,b);
			System.out.println("Entrada: ("+a+","+b+")");
			System.out.println("Salida:  ("+res.a+","+res.b+")");
			System.out.println("========================================");
		}
		System.out.println("");
		
		System.out.println("-------------------- TEST DEL MÉTODO NO FINAL --------------------");
		for (String linea: lineas) {
			String[] linea_array = linea.split(",");
			int a = Integer.parseInt(linea_array[0]);
			int b = Integer.parseInt(linea_array[1]);
			Pair<Integer,Integer> res = ejercicio5_recur_no_final(a,b);
			System.out.println("Entrada: ("+a+","+b+")");
			System.out.println("Salida:  ("+res.a+","+res.b+")");
			System.out.println("========================================");
		}
		System.out.println("");
		
		System.out.println("-------------------- TEST DEL MÉTODO FUNCIONAL --------------------");
		for (String linea: lineas) {
			String[] linea_array = linea.split(",");
			int a = Integer.parseInt(linea_array[0]);
			int b = Integer.parseInt(linea_array[1]);
			Pair<Integer,Integer> res = ejercicio5_funcional(a,b);
			System.out.println("Entrada: ("+a+","+b+")");
			System.out.println("Salida:  ("+res.a+","+res.b+")");
			System.out.println("========================================");
		}
	}
	
}
