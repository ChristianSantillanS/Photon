import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JOptionPane;

public class acciones {

	
	public void ejecucion() throws IOException, URISyntaxException {
		ThreadLocalRandom tlr = ThreadLocalRandom.current();
		String Click_u[][] = new String[2][6];
		String e_store[][] = new String[1][4];
		int idr = tlr.nextInt(6520, 6600 + 1);
		int joiner[][] = new int[2][2];
		String e_consultas[][] = new String[1][3];
		HashMap<Integer, Integer> e_click = new HashMap<Integer, Integer>();
		URL url = null;
		int query_id = tlr.nextInt(3100000, 4100000 + 1);
		int click_id = 0;
		int advertiser_id = tlr.nextInt(1100000, 1200000 + 1);

		/*
		 * Emisión de consulta Se genera la query_id Se hace la busqueda del anuncio
		 */
		// Query_id
		e_consultas[0][0] = String.valueOf(query_id);
		// advertiser_id
		e_consultas[0][1] = String.valueOf(advertiser_id);
		// texto del anuncio
		e_consultas[0][2] = JOptionPane.showInputDialog(null, "¿Que deseas buscar?", "Busquedas",
				JOptionPane.QUESTION_MESSAGE);

		String anuncio = (("Elige un numero del anuncio que deseas comprar\n" + "1.- " + e_consultas[0][2] + " rojas\n"
				+ "2.- " + e_consultas[0][2] + " blancas\n" + "3.- " + e_consultas[0][2] + " nuevas"));
		int opciones = Integer.parseInt(JOptionPane.showInputDialog(anuncio));
		// Click_id y query_id del evento opciones
		int query_id_c = Integer.valueOf(e_consultas[0][0]);
		String urls = e_consultas[0][2].replace(" ", "-");
		switch (opciones) {
		case 1:
			click_id = tlr.nextInt(211, 310 + 1);
			Photon.guardar_e_click(click_id, query_id_c, e_click);
			urls = e_consultas[0][2].replace(" ", "-");
			url = new URL("https://listado.mercadolibre.com.mx/" + urls + "-rojas");
			break;

		case 2:
			click_id = tlr.nextInt(311, 410 + 1);
			Photon.guardar_e_click(click_id, query_id_c, e_click);
			urls = e_consultas[0][2].replace(" ", "+");
			url = new URL("https://www.amazon.com.mx/s?k=" + urls + "+blancas" + "&__mk_es_MX=ÅMÅŽÕÑ&ref=nb_sb_noss_2");
			break;
		case 3:
			click_id = tlr.nextInt(411, 510 + 1);
			Photon.guardar_e_click(click_id, query_id_c, e_click);
			urls = e_consultas[0][2].replace(" ", "-");
			String urls2 = e_consultas[0][2].replace(" ", "+");
			url = new URL("https://es.aliexpress.com/af/" + urls + "-nuevas" + ".html?d=y&origin=n&SearchText=" + urls2
					+ "+nuevas" + "&catId=0&initiative_id=SB_20211128182236");
			break;
		default:
			JOptionPane.showMessageDialog(null, "Bienvenido a: * * * :)");
			url = new URL("https://listado.mercadolibre.com.mx/" + urls);
			break;

		}
		// IdRegistry inicializado
		HashMap<Integer, Integer> id_r1 = new HashMap<Integer, Integer>();
		id_r1.put(232, 6532);
		id_r1.put(233, 6533);
		id_r1.put(234, 6534);
		id_r1.put(235, 6535);
		id_r1.put(236, 6536);
		id_r1.put(237, 6537);
		id_r1.put(238, 6538);
		id_r1.put(239, 6539);
		id_r1.put(240, 6540);
		id_r1.put(241, 6541);
		id_r1.put(242, 6542);
		id_r1.put(243, 6543);
		// Mostrar resultados del evento de consulta
		System.out.println("[[query_id, advertiser_id,texto del anuncio]]");
		System.out.println(Arrays.deepToString(e_consultas));
		System.out.println("---------------------------------------------------------------");
		// Mostrar resultados del evento click
		System.out.println("{{click_id,query_id}}");
		System.out.println(e_click);

		Impresion_v.mostrarresultados(e_click);
		// Abrir Link
		Desktop.getDesktop().browse(url.toURI());
		// Uso del dispatcher
		Photon.dispatcher(click_id, id_r1, idr);
		Photon.joiner(joiner, click_id, id_r1, idr);
		Photon.eventStore(e_store, query_id, advertiser_id, e_consultas);
		Photon.joinerextraccion(joiner, query_id, e_store, e_consultas, click_id, id_r1, idr, Click_u);
	}
}
