import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JOptionPane;

public class Photon {

	public void guardar_e_consulta(Integer advertiser_id, String texto_a, HashMap<Integer, String> e_consulta) {
		if (e_consulta.containsKey(advertiser_id)) {
			System.out.println("No se puede introducir el producto. El código esta repetido.");
		} else {
			e_consulta.put(advertiser_id, texto_a);
		}
	}

	// EVENTO CLICK
	public static void guardar_e_click(int click_id, int query_id, HashMap<Integer, Integer> e_click) {
		if (e_click.containsKey(click_id)) {
			JOptionPane.showMessageDialog(null, "No se puede introducir. El código esta repetido.");
		} else {
			e_click.put(click_id, query_id);
		}
	}

	public static void dispatcher(int click_id, HashMap<Integer, Integer> id_r1, int idr) {
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

	public static void joiner(int joiner[][], int click_id, HashMap<Integer, Integer> id_r1, int idr) {
		if (!id_r1.containsKey(idr)) {
			joiner[0][0] = Integer.valueOf(click_id);
			System.out.print(
					"\nEl valor del click_id: " + "'" + joiner[0][0] + "'" + " ha sido agregado correctamente :=)");
		} else {
			reintentar(joiner, idr, id_r1, idr);
		}
	}


	public static void reintentar(int joiner[][], int click_id, HashMap<Integer, Integer> id_r1, int idr) {
		System.out.print("Fallo al unir click_id a joiner, reintentando");
		if (!id_r1.containsKey(idr)) {
			joiner[1][1] = Integer.valueOf(click_id);
			System.out.print("El segundo valor del click_id: " + joiner[1][1] + " ha sido agregado correctamente :=)");

		} else {
			reintentar(joiner, idr, id_r1, idr);
		}
	}

	public static void eventStore(String e_store[][], int query_id, int advertiser_id, String e_consultas[][]) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		String Hora = (dtf.format(LocalDateTime.now()));
		e_store[0][0] = Hora;
		e_store[0][1] = String.valueOf(query_id);
		e_store[0][2] = String.valueOf(advertiser_id);
		e_store[0][3] = e_consultas[0][2];
		// El event_store no se muestra
		// System.out.print(Arrays.deepToString(e_store));
	}

	public static void joinerextraccion(int joiner[][], int query_id, String e_store[][], String e_consultas[][],
		int click_id, HashMap<Integer, Integer> id_r1, int idr, String Click_u[][]) throws IOException {
		ThreadLocalRandom tlr = ThreadLocalRandom.current();
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
		int extraccion = joiner[0][0];
		//System.out.println("\nJOINER EXTRACCION");
		//System.out.println(extraccion);
		if (e_consultas[0][2] != e_store[0][3]) {
			System.out.print("Error al encontrar la busqueda, re insertando en idRegistry");
			dispatcher(click_id, id_r1, idr);
		} else {
			//Texto del anuncio
			Click_u[0][4] = e_consultas[0][2];
			//Hora de ejecucion
			Click_u[0][0] = e_store[0][0];
			//Query_id
			Click_u[0][1] = e_store[0][1];
			//Advertiser_id
			Click_u[0][2] = e_store[0][2];
			//click_id
			Click_u[0][3] = String.valueOf(extraccion);
			//Precio
			Click_u[0][5] = String.valueOf(df.format(tlr.nextInt(30, 70 + 1)));
			System.out.println("\n------------------------------CLICK UNIDO COMPLETADO"+"------------------------------------------------------------------------");
			System.out.print("Texto del anuncio: "+(Click_u[0][4] ) + "\n" +"Hora de ejecución: "+ (Click_u[0][0] ) + "\n" +"Query_id: "+ (Click_u[0][1] ) + "\n" +"Advertiser_id: "+ (Click_u[0][2] )
					+ "\n"+"click_id: " + (Click_u[0][3]) + "\n" +"Precio: "+ (Click_u[0][5]));
			Impresion_v.Crear_ticket(joiner, query_id,e_store,e_consultas,click_id,id_r1,idr,Click_u);
		}
	}

}
