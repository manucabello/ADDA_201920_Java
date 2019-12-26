package ejercicios;

import java.util.List;
import java.util.function.Function;

import us.lsi.common.Files2;
import us.lsi.tiposrecursivos.BinaryTree;

public class Ejercicio8 {
	
	public static Integer ejercicio8_gen(BinaryTree<Integer> tree, int n, int res) {
		if (n < tree.getLabel() && tree.isBinary()) {
			return ejercicio8_gen(tree.getLeft(),n, res+1);
		} else if (n > tree.getLabel() && tree.isBinary()) {
			return ejercicio8_gen(tree.getRight(),n, res+1);
		} else if (n == tree.getLabel()){
			return res;
		} else {
			return -1;
		}
	}
	
	public static Integer ejercicio8(BinaryTree<Integer> tree, int n) {
		return ejercicio8_gen(tree, n, 0);
	}

	public static void main(String[] args) {
		List<String> lineas_n = Files2.getLines("ficheros/PI2Ej8DatosEntrada.txt");
		Function<String,Integer> function1 = (String cadena)-> { return Integer.valueOf(cadena); };
		for (String linea: lineas_n) {
			BinaryTree<String> binTree_string = BinaryTree.parse(linea.split(";")[0]);
			BinaryTree<Integer> tree = Ejercicio6.ejercicio6_binario(binTree_string, function1);;
			System.out.println("Entrada: "+linea.split(";")[0]);
			System.out.println("Salida: El nivel del elemento "+linea.split(";")[1]+" es: "+ejercicio8(tree,Integer.valueOf(linea.split(";")[1])));
		}
	}

}
