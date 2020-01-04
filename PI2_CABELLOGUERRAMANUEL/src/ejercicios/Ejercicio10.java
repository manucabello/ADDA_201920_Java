package ejercicios;

import java.util.ArrayList;
import java.util.List;

import us.lsi.common.Files2;
import us.lsi.tiposrecursivos.BinaryTree;

public class Ejercicio10 {

	public static List<String> ejercicio10_gen(BinaryTree<String> tree, String cadena, List<String> res) {
		if (tree.getLabel().compareTo(cadena) >= 0 && tree.isLeaf()) {
			res.add(tree.getLabel());
		} else if (tree.getLabel().compareTo(cadena) < 0 && tree.isBinary()) {
			ejercicio10_gen(tree.getLeft(),cadena,res);
			ejercicio10_gen(tree.getRight(),cadena,res);
		} else if (tree.getLabel().compareTo(cadena) >= 0 && tree.isBinary()) {
			res.add(tree.getLabel());
			ejercicio10_gen(tree.getLeft(),cadena,res);
			ejercicio10_gen(tree.getRight(),cadena,res);
		}
		return res;
	}
	
	public static List<String> ejercicio10(BinaryTree<String> tree, String cadena) {
		return ejercicio10_gen(tree,cadena,new ArrayList<String>());
	}

	public static void main(String[] args) {
		List<String> lineas_n = Files2.getLines("ficheros/PI2Ej10DatosEntrada.txt");
		System.out.println("                                                       TEST DEL EJERCICIO 10"); 
		System.out.println("=======================================================================================================================================================");
		for (String linea: lineas_n) {
			BinaryTree<String> tree = BinaryTree.parse(linea.split(";")[0]);
			System.out.println("Entrada: "+linea.split(";")[0]);
			List<String> res = ejercicio10(tree,linea.split(";")[1]);
			System.out.println("Salida: Los elementos mayores o iguales que "+linea.split(";")[1]+" son "+res.size()+": "+res);
			System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
		}
	}

}
