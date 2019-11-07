package ejercicios;

import java.util.ArrayList;
import java.util.List;

import us.lsi.common.Files2;
import us.lsi.geometria.Punto2D;

public class Ejercicio4 {
	
	// Solución iterativa
	public static List<Punto2D> ejercicio4_itera(List<Punto2D> puntos, String pred) {
		List<Punto2D> res = new ArrayList<Punto2D>();
		int index = 0;
		while (index < puntos.size()) {
			if (puntos.get(index).getCuadrante() == PredToCuadrante(pred)) {
				res.add(puntos.get(index));
			}
			index++;
		}
		return res;
	}
	
	// Solución recursiva final
	public static List<Punto2D> ejercicio4_recur_final(List<Punto2D> puntos, String pred, int i, List<Punto2D> res) {
		assert(i >= 0);
		if (i == puntos.size()-1 && puntos.get(i).getCuadrante() == PredToCuadrante(pred)) {
			res.add(puntos.get(i));
		} else if (i < puntos.size()-1 && puntos.get(i).getCuadrante() == PredToCuadrante(pred)) {
			res.add(puntos.get(i));
			ejercicio4_recur_final(puntos, pred, i+1, res);
		} else if (i < puntos.size()-1 && puntos.get(i).getCuadrante() != PredToCuadrante(pred)) {
			ejercicio4_recur_final(puntos, pred, i+1, res);
		}
		return res;
	}
	
	public static List<Punto2D> ejercicio4_recur_final_gen(List<Punto2D> puntos, String pred) {
		List<Punto2D> res = new ArrayList<Punto2D>();
		return ejercicio4_recur_final(puntos,pred,0,res);
	}
	
	// Solución recursiva no final
	public static List<Punto2D> ejercicio4_recur_no_final(List<Punto2D> puntos, String pred, int i) {
		List<Punto2D> res = new ArrayList<Punto2D>();
		assert(i >= 0);
		if (i == puntos.size()-1 && puntos.get(i).getCuadrante() == PredToCuadrante(pred)) {
			res.add(puntos.get(i));
		} else if (i < puntos.size()-1 && puntos.get(i).getCuadrante() == PredToCuadrante(pred)) {
			res.add(puntos.get(i));
			res.addAll(ejercicio4_recur_no_final(puntos, pred, i+1));
		} else if (i < puntos.size()-1 && puntos.get(i).getCuadrante() != PredToCuadrante(pred)) {
			res.addAll(ejercicio4_recur_no_final(puntos, pred, i+1));
		}
		return res;
	}

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
			System.out.println("Salida:  "+ejercicio4_itera(puntos, predicado));
			System.out.println("========================================");
		}
		System.out.println("");
		
		System.out.println("-------------------- TEST DEL MÉTODO RECURSIVO FINAL --------------------");
		for (String predicado: predicados) {
			System.out.println("Entrada: "+puntos+"; Selecc. puntos del "+predicado);
			System.out.println("Salida:  "+ejercicio4_recur_final_gen(puntos, predicado));
			System.out.println("========================================");
		}
		System.out.println("");
		
		System.out.println("-------------------- TEST DEL MÉTODO RECURSIVO NO FINAL --------------------");
		for (String predicado: predicados) {
			System.out.println("Entrada: "+puntos+"; Selecc. puntos del "+predicado);
			System.out.println("Salida:  "+ejercicio4_recur_no_final(puntos, predicado, 0));
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
	
	public static Punto2D.Cuadrante PredToCuadrante(String pred) {
		Punto2D.Cuadrante res = null;
		
		if (pred.contains("primer cuadrante")) {
			res = Punto2D.Cuadrante.PRIMER_CUADRANTE;
		} else if (pred.contains("segundo cuadrante")) {
			res = Punto2D.Cuadrante.SEGUNDO_CUADRANTE;
		} else if (pred.contains("tercer cuadrante")) {
			res = Punto2D.Cuadrante.TERCER_CUADRANTE;
		} else if (pred.contains("cuarto cuadrante")) {
			res = Punto2D.Cuadrante.CUARTO_CUADRANTE;
		}
		
		return res;
	}
}
