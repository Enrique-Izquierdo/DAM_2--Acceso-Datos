package es.florida.ad;

import java.io.File;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.DirectoryStream.Filter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Identity;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import javax.print.attribute.standard.PrinterInfo;

public class AE1_T1 {
	
	private static void getInformacio(String rutaRelativaFitxer) {		
		try {
			File fitxer = new File(rutaRelativaFitxer);	
			try {			
				System.out.println("Nom: " + fitxer.getName());
				System.out.println("Ubicaci� absoluta: " + fitxer.getAbsolutePath());
				//C�lcul data �ltima modificaci�.
				long ultimaModificacion = fitxer.lastModified(); //Data en mil�lisegons comptats des de 01/01/1970.
				Date fechaUltimaModif = new Date(ultimaModificacion); //Data en format yyyy--MM-dd.
				String patron = "yyyy-MM-dd HH:mm"; //Amb SimpleDateFormat, creem l'objecte convertidor de formats: Date.
				SimpleDateFormat formatoFechaPersonalizado = new SimpleDateFormat(patron);			
				System.out.println("�ltima modificaci� (format data): " + formatoFechaPersonalizado.format(fechaUltimaModif));
				
				System.out.println("Ocult: " + fitxer.isHidden());			
				if (fitxer.isFile()) {
					System.out.println("Tipo: arxiu");
					System.out.println("Grandaria (bytes): " + fitxer.length());
				} else if (fitxer.isDirectory()) {
					System.out.println("Tipos: directori");
					System.out.println("Nombre d�elements: " + fitxer.list().length);
					System.out.println("Espai lliure (GB): " + fitxer.getFreeSpace()/1e9);
					System.out.println("Espai ocupat (GB): " + (fitxer.getTotalSpace() - fitxer.getFreeSpace())/1e9);
					System.out.println("Espai total (GB): " + fitxer.getTotalSpace()/1e9);
				} else {
					System.out.println("El fitxer no existeix o, no ha ingressat la ruta relativa del fitxer.");
				}
			} catch (SecurityException e) {
				if(fitxer.canRead()) {
					System.out.println("No t� perm�s d'execuci�.");
					e.printStackTrace();
				} else {
					System.out.println("No t� perm�s de lectura.");
					e.printStackTrace();
				}			
			} catch (Exception e) {
				System.out.println("S'ha produ�t un error durant l'execuci�.");
				e.printStackTrace();
			}
		} catch (NullPointerException e) {			
			System.out.println("Error de programaci�. Una de les variables no t� assignat cap objecte.");
			e.printStackTrace();
		}
	}
	
	private static void creaCarpeta(Scanner pEntradaTeclat) {
		System.out.println("\nHa seleccionat: opci� 2");				
		System.out.print("Introdu�sca el nom de la carpeta a crear: ");
		String nomCarpeta = pEntradaTeclat.next();	
		try {
			File carpeta = new File(nomCarpeta);
			if(carpeta.mkdir()) {
				System.out.println("La carpeta ha sigut creada.");
			} else {
				System.out.println("La carpeta NO ha sigut creada. No t� perm�s d'escriptura.");
			}
		} catch (SecurityException e) {
			System.out.println("No t� perm�s d'escriptura.");
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("Error de programaci�. Una de les variables no t� assignat cap objecte.");
			e.printStackTrace();
		}
	}
		
	/**
	 * M�tode crearFitxer(*pEntradaTeclat): sol�licita la ruta relativa del fitxer a crear 
	 * en el directori on es troba el projecte. Crea el fitxer(arxiu), informant per consola 
	 * de la finalitzaci� del proc�s. I, en cas de no ser possible crear ho, informa per consola 
	 * del motiu: el fitxer ja existeix, error per no assignaci� de variables a l'objecte, 
	 * error durant l'entrada/eixida de dades o, error per abs�ncia de permisos d'escriptura.
	 * @*param pEntradaTeclat: objecte amb la connexi� per a l'entrada/eixida de dades.
	 */
	private static void creaFitxer(Scanner pEntradaTeclat) {
		System.out.println("\nHa seleccionat: opci� 3");
		System.out.print("Introdu�sca el nom del fitxer (arxiu) a crear: ");
		String nomFitxer = pEntradaTeclat.next();
		try {
			File fitxer = new File(nomFitxer);
			if (fitxer.createNewFile()) {
				System.out.println("El fitxer (arxiu) ha sigut creat.");
			} else {
				System.out.println("El fitxer (arxiu) NO ha sigut creat, perqu� ja existeix.");
			}
		} catch (NullPointerException e) {
			System.out.println("Error de programaci�. Una de les variables no t� assignat cap objecte.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("S'ha produ�t un error durant les operacions d'entrada/eixida de dades.");
			e.printStackTrace();
		} catch (SecurityException e) {
			System.out.println("No t� perm�s d'escriptura.");
			e.printStackTrace();
		}
	}
	
	private static void elimina(Scanner pEntradaTeclat) {
		System.out.println("\nHa seleccionat: opci� 4");
		System.out.print("Introdu�sca el nom del fitxer a eliminar: ");
		String nomFitxer = pEntradaTeclat.next();
		try {
			File fitxer = new File(nomFitxer);
			if (fitxer.delete()) {
				System.out.println("El fitxer ha sigut eliminat.");
			} else {
				System.out.println("El fitxer NO ha sigut eliminat. "
						+ "El fitxer indicat no existeix, o es tracta d'un directori que cont� fitxers.");
			}
		} catch (SecurityException e) {
			System.out.println("No t� permisos d'escriptura.");
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("Error de programaci�. Una de les variables no t� assignat cap objecte.");
			e.printStackTrace();
		} 		
	}
	
	private static void renomena(Scanner pEntradaTeclat) {
		System.out.println("\nHa seleccionat: opci� 5");
		System.out.print("Introdu�sca el nom del fitxer a canviar de nom: ");
		String nomFitxer = pEntradaTeclat.next();
		System.out.print("Introdu�sca el nou nom: ");
		String nouNomFitxer = pEntradaTeclat.next();
		try {
			File fitxer = new File(nomFitxer);
			File nouFitxer = new File(nouNomFitxer);
			if(fitxer.renameTo(nouFitxer)) {
				System.out.println("El fitxer ha sigut renomenat.");
			} else {
				System.out.println("El fitxer NO ha sigut renomenat."
						+ " Comprove que la ruta relativa introdu�da, �s correcta.");
			}
		} catch (SecurityException e) {
			System.out.println("No t� permisos d'escriptura.");
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("Error de programaci�. Una de les variables no t� assignat cap objecte.");
			e.printStackTrace();
		} 
	}
	
	
	public static void main(String[] args) {						
		Scanner entradaTeclat = new Scanner(System.in);
		Boolean continuar = true;
		do {
			System.out.println("Activitats Avaluables 1 - Enrique Izquierdo Jim�nez\n"
					+ "===================================================="
					+ "\n____OPCIONS DISPONIBLES____\n1 - Veure informaci� fitxer\n"
					+ "2 - Crear carpeta\n3 - Crear fitxer\n4 - Elimina\n5 - Renomena\n0 - EIXIR");		
			System.out.print("Seleccione una opci� (del 0 al 5): ");
			String opcion = entradaTeclat.next();
			
			switch (opcion) {
			case "0":
				continuar = false;
				break;
			case "1":
				System.out.println("\nHa seleccionat: opci� 1");
				getInformacio(args[0]);
				break;
			case "2":					
				creaCarpeta(entradaTeclat);
				break;
			case "3":
				creaFitxer(entradaTeclat);
				break;
			case "4":
				elimina(entradaTeclat);
				break;
			case "5":
				renomena(entradaTeclat);
				break;
			default:
				System.out.println("\nOpci� seleccionada, no disponible.");
				break;
			}
			
			if(continuar) {			
				System.out.println("\nPressione ENTER per a continuar.");
				try{
					System.in.read();
					}
				catch(IOException e){
					System.out.println("S'ha produ�t un error durant l'entrada o eixida de dades.");
					e.printStackTrace();
				}
			} else {
				System.out.println("\nHa eixit de l'aplicaci�.");
			}			
		} while (continuar);		
		entradaTeclat.close();
	}
}
