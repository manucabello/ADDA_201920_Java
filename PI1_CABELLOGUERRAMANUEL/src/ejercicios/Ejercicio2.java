package ejercicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import us.lsi.common.Files2;

public class Ejercicio2 {
	
	public static Map<Integer,List<String>> ejercicio2(List<String> lineas) {
		Map<Integer,List<String>> res = new HashMap<Integer,List<String>>();
		int index = 0;
		while (index < lineas.size()) {
			int key = lineas.get(index).length();
			List<String> valor = new ArrayList<String>();
			if (res.containsKey(key)) {
				valor = res.get(key);
			}
			valor.add(lineas.get(index));
			res.put(key, valor);				// Este put es redundante
			index++;
		}
		return res;
	}

	public static void main(String[] args) {
		List<String> lineas = Files2.getLines("ficheros/PI1Ej2DatosEntrada.txt");
		Map<Integer,List<String>> res = ejercicio2(lineas);
		Set<Integer> keys = res.keySet();
		for(Integer key: keys) {
			System.out.println(key+":"+res.get(key).toString());
		}
	}

}
