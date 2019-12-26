package ejercicios;

import java.util.ArrayList;
import java.util.List;

import us.lsi.common.Files2;
import us.lsi.tiposrecursivos.Tree;

public class Ejercicio7 {

	public static <T> List<T> ejercicio7_gen(Tree<T> tree, List<T> res) {
		if (!tree.isEmpty() && tree.isLeaf()) {
			res.add(tree.getLabel());
		} else if (!tree.isEmpty() && tree.isNary()) {
			List<Tree<T>> children_e = tree.getChildren();
			for (Tree<T> child_e : children_e) {
				ejercicio7_gen(child_e,res);
			}
		}
		return res;
	}
	
	public static <T> List<T> ejercicio7(Tree<T> tree) {
		return ejercicio7_gen(tree, new ArrayList<T>());
	}
	
	public static void main(String[] args) {
		List<String> lineas_n = Files2.getLines("ficheros/PI2Ej7DatosEntrada.txt");
		for (String linea: lineas_n) {
			System.out.println("Arbol: "+linea);
			Tree<String> tree = Tree.parse(linea);
			System.out.println("Lista de hojas: "+ejercicio7(tree).toString()+"\n");
		}
	}

}
