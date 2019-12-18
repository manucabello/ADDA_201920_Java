package ejercicios;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import us.lsi.common.Files2;
import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.Tree;

public class Ejercicio6 {
	
	public static <E,R> BinaryTree<R> ejercicio6_binario(BinaryTree<E> tree, Function<E,R> function) {
		BinaryTree<R> res = BinaryTree.empty();
		if (!tree.isEmpty() && tree.isLeaf()) {
			res = BinaryTree.leaf(function.apply(tree.getLabel()));
		} else if (!tree.isEmpty()) {
			res =  BinaryTree.binary(function.apply(tree.getLabel()),ejercicio6_binario(tree.getLeft(),function),ejercicio6_binario(tree.getRight(),function));
		}
		return res;
	}
	
	public static <E,R> Tree<R> ejercicio6_nario(Tree<E> tree, Function<E,R> function) {
		Tree<R> res = Tree.empty();
		if (!tree.isEmpty() && tree.isLeaf()) {
			res = Tree.leaf(function.apply(tree.getLabel()));
		} else if (!tree.isEmpty()) {
			List<Tree<E>> children_e = tree.getChildren();
			List<Tree<R>> children_r = new ArrayList<Tree<R>>();
			for (Tree<E> child_e : children_e) {
				children_r.add(ejercicio6_nario(child_e,function));
			}
			res = Tree.nary(function.apply(tree.getLabel()), children_r);
		}
		return res;
	}

	public static void main(String[] args) {
		Function<String,Integer> function1 = (String cadena)-> { return Integer.valueOf(cadena); };
		Function<String,Integer> function2 = (String cadena)-> { return Integer.valueOf(cadena) >= 0 ? cadena.length() : cadena.length()-1; };
		Function<String,Integer> function3 = (String cadena)-> { return (int) Math.pow(2,maxValueFromString(cadena)); };
		
		System.out.println("          TEST DEL EJERCICIO 6 PARA ARBOLES BINARIOS");
		System.out.println("==============================================================");
		
		List<String> lineas_b = Files2.getLines("ficheros/PI2Ej6DatosEntradaArbolBinario.txt");
		for (String linea: lineas_b) {
			System.out.println("Entrada: "+linea);
			BinaryTree<String> tree = BinaryTree.parse(linea);
			System.out.println("	Salida1: "+ejercicio6_binario(tree,function1).toString());
			System.out.println("	Salida2: "+ejercicio6_binario(tree,function2).toString());
			System.out.println("	Salida3: "+ejercicio6_binario(tree,function3).toString());
			System.out.println("--------------------------------------------------------------");
		}
		
		System.out.println("\n          TEST DEL EJERCICIO 6 PARA ARBOLES N-ARIOS");
		System.out.println("==============================================================");
		
		List<String> lineas_n = Files2.getLines("ficheros/PI2Ej6DatosEntradaArbolNario.txt");
		for (String linea: lineas_n) {
			System.out.println("Entrada: "+linea);
			Tree<String> tree = Tree.parse(linea);
			System.out.println("	Salida1: "+ejercicio6_nario(tree,function1).toString());
			System.out.println("	Salida2: "+ejercicio6_nario(tree,function2).toString());
			System.out.println("	Salida3: "+ejercicio6_nario(tree,function3).toString());
			System.out.println("--------------------------------------------------------------");
		}
	}
	
	public static Integer maxValueFromString(String cadena) {
		Integer res = 0;
		for (int i = 0; i < cadena.length(); i++) {
			if (cadena.charAt(i) != '-') {
				res = Math.max(res, Integer.valueOf(""+cadena.charAt(i)));
			}
		}
		return res;
	}
}
