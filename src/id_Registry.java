import java.util.HashMap;
import java.util.Iterator;

public class id_Registry {
	public static void id_registry(int click_id, HashMap<Integer, Integer> id_r1, int idr) {
		Integer clave;
		if (id_r1.containsKey(idr)) {
			System.out.print("\nEvento click ya existente\n");
		} else {
			id_r1.put(click_id, idr);
			Iterator<Integer> claves = id_r1.keySet().iterator();
			System.out.print("Muestra de id_Registry:\n");
			while (claves.hasNext()) {
				clave = claves.next();
				System.out.println(clave + " - " + id_r1.get(clave));
			}
			System.out.print("Registro al id_Registry correctamente");

		}
	}

}
