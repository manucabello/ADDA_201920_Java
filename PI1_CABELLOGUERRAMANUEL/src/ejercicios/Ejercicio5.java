package ejercicios;

import java.util.ArrayList;
import java.util.List;

import us.lsi.common.Files2;

// TODO Cambiar los List<Integer> por el tipo Tuple
public class Ejercicio5 {
	
	// Soluci�n iterativa
	public static List<Integer> ejercicio5_itera(int a, int b) {
		List<Integer> res = new ArrayList<Integer>();
		res.add(0);
		res.add(a);
		
		while (a >= b) {
			res.set(0,res.get(0)+1);
			a = a-b;
			res.set(1, a);
		}
		
		return res;
	}
	
	// Soluci�n recursiva final
	public static List<Integer> ejercicio5_recur_final(int a, int b, List<Integer> res) {
		if (a < b) {
			return res;
		} else {
			res.set(0,res.get(0)+1);
			res.set(1, a-b);
			return ejercicio5_recur_final(res.get(1),b,res);
		}
	}
	
	public static List<Integer> ejercicio5_recur_final_gen(int a, int b) {
		List<Integer> res = new ArrayList<Integer>();
		res.add(0);
		res.add(a);
		return ejercicio5_recur_final(a,b,res);
	}
	
	// Soluci�n recursiva no final
	public static List<Integer> ejercicio5_recur_no_final(int a, int b) {
		List<Integer> res = new ArrayList<Integer>();
		if (a < b) {
			res.add(0);
			res.add(a);
		} else {
			res.add(ejercicio5_recur_no_final(a-b,b).get(0)+1);
			res.add(ejercicio5_recur_no_final(a-b,b).get(1));
		}
		return res;
	}
	
	// Soluci�n funcional
	// Stream.iterate({a,c}, cond.continuidad, op.unario) -> op.unario puede ser funci�n auxiliar t->siguiente(t)
	
	
	// #######################################################################################################################
	// Resultados
	public static void main(String[] args) {
		List<String> lineas = Files2.getLines("ficheros/PI1Ej5DatosEntrada.txt");
		
		System.out.println("-------------------- TEST DEL M�TODO ITERATIVO --------------------");
		for (String linea: lineas) {
			String[] linea_array = linea.split(",");
			int a = Integer.parseInt(linea_array[0]);
			int b = Integer.parseInt(linea_array[1]);
			List<Integer> res = ejercicio5_itera(a,b);
			System.out.println("Entrada: ("+a+","+b+")");
			System.out.println("Salida:  ("+res.get(0)+","+res.get(1)+")");
			System.out.println("========================================");
		}
		System.out.println("");
		
		System.out.println("-------------------- TEST DEL M�TODO FINAL --------------------");
		for (String linea: lineas) {
			String[] linea_array = linea.split(",");
			int a = Integer.parseInt(linea_array[0]);
			int b = Integer.parseInt(linea_array[1]);
			List<Integer> res = ejercicio5_recur_final_gen(a,b);
			System.out.println("Entrada: ("+a+","+b+")");
			System.out.println("Salida:  ("+res.get(0)+","+res.get(1)+")");
			System.out.println("========================================");
		}
		System.out.println("");
		
		System.out.println("-------------------- TEST DEL M�TODO NO FINAL --------------------");
		for (String linea: lineas) {
			String[] linea_array = linea.split(",");
			int a = Integer.parseInt(linea_array[0]);
			int b = Integer.parseInt(linea_array[1]);
			List<Integer> res = ejercicio5_recur_no_final(a,b);
			System.out.println("Entrada: ("+a+","+b+")");
			System.out.println("Salida:  ("+res.get(0)+","+res.get(1)+")");
			System.out.println("========================================");
		}
		System.out.println("");
	}
	
}
