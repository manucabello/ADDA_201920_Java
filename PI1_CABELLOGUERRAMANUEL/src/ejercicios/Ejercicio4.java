package ejercicios;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import us.lsi.common.Files2;
import us.lsi.geometria.Punto2D;

public class Ejercicio4 {
	
	// Solución iterativa
	public static List<Punto2D> ejercicio4_itera(List<Punto2D> puntos, Predicate<Punto2D> pred) {
		List<Punto2D> res = new ArrayList<Punto2D>();
		int index = 0;
		while (index < puntos.size()) {
			if (pred.test(puntos.get(index))) {
				res.add(puntos.get(index));
			}
			index++;
		}
		return res;
	}
	
	// Solución recursiva final
	public static List<Punto2D> ejercicio4_recur_final(List<Punto2D> puntos, Predicate<Punto2D> pred, int i, List<Punto2D> res) {
		assert(i >= 0);
		if (i == puntos.size()-1 && pred.test(puntos.get(i))) {
			res.add(puntos.get(i));
		} else if (i < puntos.size()-1 && pred.test(puntos.get(i))) {
			res.add(puntos.get(i));
			ejercicio4_recur_final(puntos, pred, i+1, res);
		} else if (i < puntos.size()-1 && !pred.test(puntos.get(i))) {
			ejercicio4_recur_final(puntos, pred, i+1, res);
		}
		return res;
	}
	
	public static List<Punto2D> ejercicio4_recur_final_gen(List<Punto2D> puntos, Predicate<Punto2D> pred) {
		List<Punto2D> res = new ArrayList<Punto2D>();
		return ejercicio4_recur_final(puntos,pred,0,res);
	}
	
	// Solución recursiva no final
	public static List<Punto2D> ejercicio4_recur_no_final(List<Punto2D> puntos, Predicate<Punto2D> pred, int i) {
		List<Punto2D> res = new ArrayList<Punto2D>();
		assert(i >= 0);
		if (i == puntos.size()-1 && pred.test(puntos.get(i))) {
			res.add(puntos.get(i));
		} else if (i < puntos.size()-1 && pred.test(puntos.get(i))) {
			res.add(puntos.get(i));
			res.addAll(ejercicio4_recur_no_final(puntos, pred, i+1));
		} else if (i < puntos.size()-1 && !pred.test(puntos.get(i))) {
			res.addAll(ejercicio4_recur_no_final(puntos, pred, i+1));
		}
		return res;
	}
	
	// Solución funcional
	public static List<Punto2D> ejercicio4_funcional(List<Punto2D> puntos, Predicate<Punto2D> pred) {
		List<Punto2D> res = new ArrayList<Punto2D>();
		Stream<Punto2D> aux = puntos.stream().filter(pred);
		res = aux.collect(Collectors.toList());
		return res;
	}

	// #######################################################################################################################
	// Resultados
	public static void main(String[] args) {
		List<String> lineas = Files2.getLines("ficheros/PI1Ej4DatosEntrada.txt");
		List<Punto2D> puntos = new ArrayList<Punto2D>();
		List<String> predicados = new ArrayList<String>();
		
		for (String linea: lineas) {
			try {
				if (linea.charAt(0) == '(' && linea.charAt(linea.length()-1) == ')') {
					puntos.add(StringToPunto2D(linea));
				} else if (linea.charAt(0) == '/') {
					predicados.add(linea.split("al ")[1]);
				}
			} catch (Exception e) {
				// Nothing
			}
		}
		
		System.out.println("-------------------- TEST DEL MÉTODO ITERATIVO --------------------");
		for (String predicado: predicados) {
			System.out.println("Entrada: "+puntos+"; Selecc. puntos del "+predicado);
			System.out.println("Salida:  "+ejercicio4_itera(puntos, StringToPredicate(predicado)));
			System.out.println("========================================");
		}
		System.out.println("");
		
		System.out.println("-------------------- TEST DEL MÉTODO RECURSIVO FINAL --------------------");
		for (String predicado: predicados) {
			System.out.println("Entrada: "+puntos+"; Selecc. puntos del "+predicado);
			System.out.println("Salida:  "+ejercicio4_recur_final_gen(puntos, StringToPredicate(predicado)));
			System.out.println("========================================");
		}
		System.out.println("");
		
		System.out.println("-------------------- TEST DEL MÉTODO RECURSIVO NO FINAL --------------------");
		for (String predicado: predicados) {
			System.out.println("Entrada: "+puntos+"; Selecc. puntos del "+predicado);
			System.out.println("Salida:  "+ejercicio4_recur_no_final(puntos, StringToPredicate(predicado), 0));
			System.out.println("========================================");
		}
		System.out.println("");
		
		System.out.println("-------------------- TEST DEL MÉTODO FUNCIONAL --------------------");
		for (String predicado: predicados) {
			System.out.println("Entrada: "+puntos+"; Selecc. puntos del "+predicado);
			System.out.println("Salida:  "+ejercicio4_funcional(puntos, StringToPredicate(predicado)));
			System.out.println("========================================");
		}
		System.out.println("");
	}
	
	// Métodos adicionales
	public static Punto2D StringToPunto2D(String punto) {
		String puntoStr = punto.substring(1,punto.length()-1);
		String[] coords = puntoStr.split(",");
		Double CoordX = Double.valueOf(coords[0]);
		Double CoordY = Double.valueOf(coords[1]);
		Punto2D res = Punto2D.create(CoordX, CoordY);
		return res;
	}
	
	public static Predicate<Punto2D> StringToPredicate(String pred) {
		Predicate<Punto2D> res = new Predicate<Punto2D>() {
			public boolean test(Punto2D p) {
				boolean pred_res = false;
				if (pred.contains("primer cuadrante")) {
					pred_res = p.getCuadrante() == Punto2D.Cuadrante.PRIMER_CUADRANTE;
				} else if (pred.contains("segundo cuadrante")) {
					pred_res = p.getCuadrante() == Punto2D.Cuadrante.SEGUNDO_CUADRANTE;
				} else if (pred.contains("tercer cuadrante")) {
					pred_res = p.getCuadrante() == Punto2D.Cuadrante.TERCER_CUADRANTE;
				} else if (pred.contains("cuarto cuadrante")) {
					pred_res = p.getCuadrante() == Punto2D.Cuadrante.CUARTO_CUADRANTE;
				}
				return pred_res;
			}
		};
		
		return res;
	}
}
