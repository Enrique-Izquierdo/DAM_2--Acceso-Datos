package ad.AE.T1ae4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Modelo {
	//atributs
	//constructors
	//getters y setters
	//altres mètodes de interface
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner entradaTeclat = new Scanner(System.in);
		Boolean continuar = true;
		do {
			System.out.print("\n  Aplicació SGBD Biblioteca  "
					+ "\n============================="
					+ "\n1.Migrar dades archiu CSV a bd (MySQL) Biblioteca"
					+ "\n2.Mostrar llibres d'autors nascuts abans 1950"
					+ "\n3.Mostrar editorials que han publicat llibres en el segle XXI"
					+ "\n4.Efectuar consulta lliure"
					+ "\n5.EIXIR de l'aplicació"
					+ "\n\nIntroduïsca l'opció triada: ");
			switch (entradaTeclat.nextLine()) {
			case "1":
				System.out.print("Ha triat l'opció 1: Migrar dades archiu CSV a bd (MySQL) Biblioteca.");
				break;
			case "2":
				System.out.print("Ha triat l'opció 2: Mostrar llibres d'autors nascuts abans 1950.");
				break;
			case "3":
				System.out.print("Ha triat l'opció 3: Mostrar editorials que han publicat llibres en el segle XXI.");
				break;
			case "4":
				System.out.print("Ha triat l'opció 4: Efectuar consulta lliure.");
				break;
			case "5":
				System.out.print("Ha triat l'opció 5: EIXIR de l'aplicació.");
				continuar = false;
				break;
			default:
				System.out.print("El valor teclejat no és vàlid.");
				break;
			}
			
			if(continuar) {
				System.out.print("\nPressione ENTER per a continuar...");
				try {
					System.in.read();					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				entradaTeclat.nextLine();
			}
		}while(continuar);		
		
		
//		Boolean cont = true;
//		do {
//			System.out.print("Introduïsca la ruta de l'arxiu CSV: ");
//			String rutaCSV = entradaTeclat.nextLine();			
//			File archiuCSV = new File(rutaCSV);
//			if (!archiuCSV.exists()) {
//				cont=false;				
//			}			
//		} while(cont);
		
		
		//migrarDades_DesdeCSV(archiuCSV);
		realitzacioConsulta_Predefinida();
		realitzacioConsulta_Lliure();
	}
	
	//mètodes de implementació
//	private static void migrarDades_DesdeCSV(File pArchiuCSV) {		
//		try {
//			FileReader fr = new FileReader(pArchiuCSV);
//			BufferedReader br = new BufferedReader(fr);
//			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	private static void realitzacioConsulta_Predefinida() {}
	
	private static void realitzacioConsulta_Lliure() {}
}
