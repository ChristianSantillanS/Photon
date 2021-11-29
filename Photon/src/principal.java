import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;
import javax.swing.JOptionPane;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class principal {
	String e_consultas[][] = new String[1][3];
	int query_id;
	int click_id;
	int advertiser_id;
	static ThreadLocalRandom tlr = ThreadLocalRandom.current();

	public static void guardarProducto(Integer advertiser_id, String texto_a, HashMap<Integer, String> e_consulta) {
		if (e_consulta.containsKey(advertiser_id)) {
			System.out.println("No se puede introducir el producto. El código esta repetido.");
		} else {
			e_consulta.put(advertiser_id, texto_a);
		}
	}

	public static void modificatexto_a(Integer advertiser_id, HashMap<Integer, String> e_consulta) {

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		if (e_consulta.containsKey(advertiser_id)) {
			System.out.println("Introduce el texto_a :");
			e_consulta.put(advertiser_id, sc.next());
		} else {
			System.out.println("No hay ningun código.");
		}
	}

	public static void mostrar(HashMap<Integer, String> e_consulta) {
		Integer clave;
		Iterator<Integer> e_consultas = e_consulta.keySet().iterator();
		System.out.println("Hay los siguientes:\n");
		while (e_consultas.hasNext()) {
			clave = e_consultas.next();
			System.out.println(clave + " - " + e_consulta.get(clave));
		}
	}

	public static void eliminar(int advertiser_id, HashMap<Integer, String> e_consulta) {
		if (e_consulta.containsKey(advertiser_id)) {
			e_consulta.remove(advertiser_id);
		} else {
			System.out.println("No hay ningun código.");
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

	public static void mostrarresultados(HashMap<Integer, Integer> e_click) {
		int clave;
		Iterator<Integer> res = e_click.keySet().iterator();
		System.out.println("\nResultados del evento click:\n");
		while (res.hasNext()) {
			clave = res.next();
			System.out.print(clave);
			System.out.println("\n--------------------------------------------------------");

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
		float rand_val;
		Random rand = new Random();
		int min_val = 30;
        int max_val = 70;
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
			Click_u[0][5] = String.valueOf(df.format(rand_val= rand.nextFloat()*(max_val-min_val)));
			System.out.println("\n------------------------------CLICK UNIDO COMPLETADO"+"------------------------------------------------------------------------");
			System.out.print("Texto del anuncio "+(Click_u[0][0]) + "\n" +"Hora de ejecución "+ (Click_u[0][1]) + "\n" +"Query_id "+ (Click_u[0][2]) + "\n" +"Advertiser_id "+ (Click_u[0][3])
					+ "\n"+"click_id " + (Click_u[0][4]) + "\n" +"Precio "+ (Click_u[0][5]));
			Crear_ticket(joiner, query_id,e_store,e_consultas,click_id,id_r1,idr,Click_u);
		}
	}
	public static void Crear_ticket(int joiner[][], int query_id, String e_store[][], String e_consultas[][],
			int click_id, HashMap<Integer, Integer> id_r1, int idr, String Click_u[][]) throws IOException {

		File directorio = new File ("C:\\Users\\santi\\Desktop\\Photon\\");
		try {
            //Si no existe el directorio
			if(!directorio.exists()) {
				if(directorio.mkdirs()) {
				} else {
	                System.out.println("Error al crear directorio");
	            }
			}
			String archivo = e_store[0][0].replace("/", "-").replace(":", "-");
			String ruta = "C:\\Users\\santi\\Desktop\\Photon\\ticket-"+archivo+".txt";
            String contenido1 = "-------------------------------------------------------------------------------------------------------";
            String contenido2 = "\n|  Texto del anuncio  | Hora de ejecución   | Query_id |      Advertiser_id      | click_id  | Precio | ";
            String contenido3 = "\n-------------------------------------------------------------------------------------------------------\n";
            String contenido4 = "|   "+(Click_u[0][4]) +"    | " +(Click_u[0][0]) +" |" + (Click_u[0][1])+"   |" +  (Click_u[0][2])
            		+"                 | " + (Click_u[0][3]) +"        | " + (Click_u[0][5])+"  |";
            String contenido5 = "\n-------------------------------------------------------------------------------------------------------\n";
            String contenido = contenido1+contenido2+contenido3+contenido4+contenido5;
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
                Runtime runtime = Runtime.getRuntime(); 
        		Process process = runtime.exec("C:\\Windows\\notepad.exe "+ruta); 
            }
            
            
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contenido);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		
    }
            

	public static void main(String[] args) throws IOException, URISyntaxException {
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
			guardar_e_click(click_id, query_id_c, e_click);
			urls = e_consultas[0][2].replace(" ", "-");
			url = new URL("https://listado.mercadolibre.com.mx/" + urls + "-rojas");
			break;

		case 2:
			click_id = tlr.nextInt(311, 410 + 1);
			guardar_e_click(click_id, query_id_c, e_click);
			urls = e_consultas[0][2].replace(" ", "+");
			url = new URL("https://www.amazon.com.mx/s?k=" + urls + "+blancas" + "&__mk_es_MX=ÅMÅŽÕÑ&ref=nb_sb_noss_2");
			break;
		case 3:
			click_id = tlr.nextInt(411, 510 + 1);
			guardar_e_click(click_id, query_id_c, e_click);
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
		mostrarresultados(e_click);
		// Abrir Link
		Desktop.getDesktop().browse(url.toURI());
		// Uso del dispatcher
		dispatcher(click_id, id_r1, idr);
		joiner(joiner, click_id, id_r1, idr);
		eventStore(e_store, query_id, advertiser_id, e_consultas);
		joinerextraccion(joiner, query_id, e_store, e_consultas, click_id, id_r1, idr, Click_u);
	}
}
