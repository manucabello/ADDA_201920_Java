package ejercicios;

import java.util.List;
import java.util.function.Function;

import us.lsi.common.Files2;
import us.lsi.common.Pair;
import us.lsi.tiposrecursivos.Tree;

public class Ejercicio9 {
	
	public static Pair<Integer,Integer> ejercicio9_gen(Tree<Integer> tree, Pair<Integer,Integer> res) {
		if (!tree.isEmpty() && tree.isLeaf()) {
			res = Pair.of(Math.min(res.a, tree.getLabel()), Math.max(res.b, tree.getLabel()));
		} else if (!tree.isEmpty() && tree.isNary()) {
			res = Pair.of(Math.min(res.a, tree.getLabel()), Math.max(res.b, tree.getLabel()));
			List<Tree<Integer>> children = tree.getChildren();
			for (Tree<Integer> child : children) {
				res = ejercicio9_gen(child,res);
			}
		}
		return res;
	}
	
	public static Pair<Integer,Integer> ejercicio9(Tree<Integer> tree) {
		Pair<Integer,Integer> res = Pair.of(null, null);
		if (!tree.isEmpty()) {
			res = Pair.of(tree.getLabel(), tree.getLabel());
		}
		return ejercicio9_gen(tree, res);
	}

	public static void main(String[] args) {
		List<String> lineas_n = Files2.getLines("ficheros/PI2Ej9DatosEntrada.txt");
		Function<String,Integer> function1 = (String cadena)-> { return Integer.valueOf(cadena); };
		for (String linea: lineas_n) {
			System.out.println("Árbol: "+linea);
			Tree<String> tree_str = Tree.parse(linea);
			Tree<Integer> tree = Ejercicio6.ejercicio6_nario(tree_str, function1);
			Pair<Integer,Integer> res = ejercicio9(tree);
			System.out.println("Min: "+res.a+", Max: "+res.b+"\n");
		}
	}

}
