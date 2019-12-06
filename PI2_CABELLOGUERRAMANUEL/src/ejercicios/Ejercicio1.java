package ejercicios;

import java.util.List;

import us.lsi.common.Files2;

public class Ejercicio1 {
	
	public static boolean ejercicio1(int[][] matrix) {
		boolean res = true;
		int n = matrix.length;
		if (n == 2) {
			res = matrix[0][0] < matrix[n-1][n-1];
		} else if (n > 2 && res) {
			int[][] aux1 = submatrix(matrix,n-1,n-1);
			int[][] aux2 = submatrix(matrix,n-1,0);
			int[][] aux3 = submatrix(matrix,0,n-1);
			int[][] aux4 = submatrix(matrix,0,0);
			res = matrix[0][0] < matrix[n-1][n-1]&&ejercicio1(aux1)&&ejercicio1(aux2)&&ejercicio1(aux3)&&ejercicio1(aux4);
		}
		return res;
	}
	
	public static void main(String[] args) {
		List<String> lineas = Files2.getLines("ficheros/PI2Ej1DatosEntrada.txt");
		for (String linea: lineas) {
			int[][] aux = stringToMatrix(linea);
			boolean res = ejercicio1(aux);
			System.out.println("La matriz: "+linea);
			if (res) {
				System.out.println("Cumple la propiedad");
			} else {
				System.out.println("NO cumple la propiedad");
			}
		}
	}
	
	public static int[][] submatrix(int[][] matrix, int row, int col) {
		int[][] res = new int[matrix.length-1][matrix.length-1];
		int x = 0;
		for (int i = 0; i < matrix.length; i++) {
			int y = 0;
			if (i != row) {
				for (int j = 0; j < matrix[i].length; j++) {
					if (j != col) {
						res[x][y] = matrix[i][j];
						y++;
					}
				}
				x++;
			}
		}
		return res;
	}
	
	public static int[][] stringToMatrix(String cadena) {
		String[] ars = cadena.split(",");
		int[][] res = new int[ars.length][ars.length];
		int index_y = 0;
		for (String ar : ars) {
			String[] nums = ar.substring(1, ar.length()-1).split(" ");
			int index_x = 0;
			for (String num : nums) {
				res[index_y][index_x] = Integer.parseInt(num);
				index_x++;
			}
			index_y++;
		}
		
		return res;
	}
	
/*	
	{ 1  4}
	{10 20}		CUMPLE
	CUMPLE
	
	{1 4 2 0}	{1 4 2}	{4 2 0}							{1 4} {4 2}				{4 2} {2 0}	
	{3 3 7 7}	{3 3 7}	{3 7 7}		{3 3 7}	{3 7 7}		{3 3} {3 7}	{3 3} {3 7}	{3 7} {7 7} {3 7} {7 7}		{3 3} {3 7}				{3 7} {7 7}
	{6 4 2 5}	{6 4 2}	{4 2 5}		{6 4 2}	{4 2 5}					{6 4} {4 2}				{4 2} {2 5}		{6 4} {4 2}	{6 4} {4 2} {4 2} {2 5} {4 2} {2 5}
	{3 9 1 4}	CUMPLE	CUMPLE		{3 9 1}	{9 1 4}																		{3 9} {9 1} 			{9 1} {1 4}		NO CUMPLE
	CUMPLE							NO CUMPLE
	
	{10 4 2 0}	{10 4 2} {4 2 0}						{10 4} {4 2}			 {4 2} {2 0}				{3 3} {3 7}				{3 7} {7 7}
	{ 3 3 7 7}	{ 3 3 7} {3 7 7}	{3 3 7}	{3 7 7}		{ 3 3} {3 7} {3 3} {3 7} {3 7} {7 7} {3 7} {7 7}	{6 3} {4 2} {6 4} {4 2}	{4 2} {2 5}	{4 2} {2 5}
	{ 6 4 2 5}	{ 6 4 2} {4 2 5}	{6 4 2}	{4 2 5} 				 {6 4} {4 2}			 {4 2} {2 5}				{3 9} {9 1}				{9 1} {1 4}		NO CUMPLE
	{ 3 9 1 4}						{3 9 1}	{9 1 4}
	NO CUMPLE
	
	{1 4 2 0}	{1 4 2}	{4 2 0}							{1 4} {4 2}				{4 2} {2 0}					{3 3} {3 7}				{3 7} {7 7}
	{3 3 7 7}	{3 3 7}	{3 7 7}		{3 3 7}	{3 7 7}		{3 3} {3 7}	{3 3} {3 7}	{3 7} {7 7} {3 7} {7 7}		{6 4} {4 5} {6 4} {4 5} {4 5} {5 5} {4 5} {5 5}
	{6 4 5 5}	{6 4 5}	{4 5 5}		{6 4 5}	{4 5 5}					{6 4} {4 5}				{4 5} {5 5}					{3 9} {9 1}				{9 1} {1 4}		NO CUMPLE
	{3 9 1 4}	CUMPLE	CUMPLE		{3 9 1}	{9 1 4}
	CUMPLE							NO CUMPLE
	
	{1 4 2 0}
	{0 2 4 7}
	{6 0 3 5}
	{3 9 1 4}
	
	
*/
}
