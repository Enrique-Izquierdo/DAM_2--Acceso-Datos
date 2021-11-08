package ad.AE.T1ae4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.cj.util.EscapeTokenizer;


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
				System.out.print("Ha triat l'opció 1: Migrar dades archiu CSV a bd (MySQL) Biblioteca."
						+ "\nIntroduïsca la ruta de l'arxiu CSV: ");
				String rutaCSV = entradaTeclat.nextLine();			
				File archiuCSV = new File(rutaCSV);
				Boolean cont = true;
				if (!archiuCSV.exists()) {					
					System.out.println("La ruta indicada no existeix.");
					cont = false;
				}
				
				if (cont && archiuCSV.isDirectory()) {
					System.out.println("No ha introduït la ruta d'un arxiu.");
					cont = false;
				}
				
				if (cont && !archiuCSV.canRead()) {
					System.out.println("No té habilitat el permís de lectura.");
					cont = false;
				}
				
				if (cont) {				
					migrarDades_DesdeCSV(archiuCSV);
				}
				break;
				
			case "2":
				System.out.println("Ha triat l'opció 2: Mostrar llibres d'autors nascuts abans 1950.");
				llibres_AutorsAbans1950();
				break;
			case "3":
				System.out.println("Ha triat l'opció 3: Mostrar editorials que han publicat llibres en el segle XXI.");
				editorials_PublicacionsXXI();
				break;
			case "4":
				System.out.println("Ha triat l'opció 4: Efectuar consulta lliure.");
				menuConsultes(entradaTeclat);
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
		entradaTeclat.close();
	}
	
	//mètodes de implementació
	private static void migrarDades_DesdeCSV(File pArchiuCSV) {		
		try {
			FileReader fr = new FileReader(pArchiuCSV);
			BufferedReader br = new BufferedReader(fr);
			String registre = br.readLine();
			registre = br.readLine();
			Boolean correcte = true;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conex = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "");
				PreparedStatement psInserir = conex.prepareStatement("INSERT INTO llibres(ID, Titol, Autor, "
						+ "Any_Naixement, Any_Publicacio, Editorial, Pagines) VALUES (?,?,?,?,?,?,?)");
				while(registre != null) {
					String[] valors = registre.split(";");					
//					for (String valor : valors) {
//						if (valor.isEmpty()) {
//							System.out.println("Está vacío");
//							valor="N.C.";
////							valor.replaceAll("", "N.C.");
////							valor.replace("", "N.C.");
//						}
//					}				
					
//					for (int i=0;i<valors.length;i++) {						
//						if(valors[i].isEmpty()) {
//							System.out.println("contenido elemento: "+valors[i]);
//							valors[i]="N.C.";
//						}
//					}
					
					for (int i=0;i<valors.length;i++) {						
						if(valors[i].isEmpty()) {
							if(i<=1 || i==4) {
								valors[i]="N.C.";								
							} else {								
								valors[i]="0000";
							}							
						}
					}										
					
					psInserir.setString(1, null);
					psInserir.setString(2, valors[0]);
					psInserir.setString(3, valors[1]);					
					int anyNaixement = Integer.parseInt(valors[2]);
					psInserir.setInt(4, anyNaixement);
					int anyPublicacio = Integer.parseInt(valors[3]);
					psInserir.setInt(5, anyPublicacio);
					psInserir.setString(6, valors[4]);
					int nomPagines = Integer.parseInt(valors[5]);
					psInserir.setInt(7, nomPagines);
					int resultatInserir = psInserir.executeUpdate();
					
					if (resultatInserir == 1) {
						correcte = correcte && true;
					} else {
						correcte = false;
					}
					
					registre = br.readLine();					
				}
				
				if(correcte) {
					System.out.println("\nMigració de dades: realitzada correctament");
				} else {
					System.out.println("\nMigració de dades: no realitzada o incompleta");
				}
				
				psInserir.close();
				conex.close();				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void llibres_AutorsAbans1950() {
		try {
			//Carreguem el Driver per a accedir a bd MySQL
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Creem connexió a bd
			Connection conex = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca","root","");
			//Creem objecte consulta de tipus Statement
			Statement consulta = conex.createStatement();
			//Llancem la consulta i assignem a un objecte (punter), de tipus ResultSet,
			//la referencia prèvia al primer registre de la bd que satisfà la consulta.
			ResultSet rs = consulta.executeQuery("SELECT * FROM `llibres` WHERE `Any_Naixement` < 1950 && `Any_Naixement` != 0");
			//Recorrem amb el punter tots els registres de la bd que satisfan la consulta.
			Boolean primerRegistre = true;
			while (rs.next()) {
				if(primerRegistre) {
					System.out.printf("\n%-5s %-32s %-20s %-15s %-17s %-15s %-10s\n", "ID","Tìtol","Autor","Any_Naixement","Any_Publicació","Editorial","Pàgines");
					primerRegistre=false;
				}
				System.out.printf("%-5s %-32s %-20s %-15s %-17s %-15s %-10s\n", rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getInt(7));
			}
			rs.close();
			consulta.close();
			conex.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}
	
	private static void editorials_PublicacionsXXI() {
		try {
			//Carreguem el Driver per a accedir a bd MySQL
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Creem connexió a bd
			Connection conex = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca","root","");
			//Creem objecte consulta de tipus Statement
			Statement consulta = conex.createStatement();
			//Llancem la consulta i assignem a un objecte (punter), de tipus ResultSet,
			//la referencia prèvia al primer registre de la bd que satisfà la consulta.
			ResultSet rs = consulta.executeQuery("SELECT DISTINCT * FROM `llibres` WHERE `Any_Publicacio` > 2000");
			//Recorrem amb el punter tots els registres de la bd que satisfan la consulta.
			Boolean primerRegistre = true;
			while (rs.next()) {
				if(primerRegistre) {
					System.out.printf("\n%-5s %-32s %-20s %-15s %-17s %-15s %-10s\n", "ID","Tìtol","Autor","Any_Naixement","Any_Publicació","Editorial","Pàgines");
					primerRegistre=false;
				}
				System.out.printf("%-5s %-32s %-20s %-15s %-17s %-15s %-10s\n", rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getInt(7));
			}
			rs.close();
			consulta.close();
			conex.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private static void menuConsultes(Scanner pEntradaTeclat) {
		int numCamp, numId;
		String valorCamp;
		String[] valorsCamps = new String[6];
		System.out.print("\n\t   Tipus de consulta disponible  "
				+ "\n\t ================================="
				+ "\n\t 1 Buscar registres"
				+ "\n\t 2 Inserir registre"
				+ "\n\t 3 Actualitzar registre"
				+ "\n\t 4 Esborrar registre"
				+ "\n\t 5 EIXIR al menú principal."
				+ "\n\n\t Introduïsca el número de la consulta triada: ");
		String resposta = pEntradaTeclat.nextLine();
		switch (resposta) {
		case "1":
			System.out.print("Ha triat: 1. Buscar registres."
					+ "\nIntroduïsca el número del camp (1-ID, 2-Tìtol, 3-Autor, 4-Any_Naixement, 5-Any-Publicació,"
					+ " \n6-Editorial, 7-Págines) pel qual vulga realitzar la cerca: ");
			numCamp = pEntradaTeclat.nextInt();
			pEntradaTeclat.nextLine();
			System.out.print("Introduïsca el valor del camp: ");
			valorCamp = pEntradaTeclat.nextLine();
			cercaLliure(numCamp, valorCamp);			
			break;
			
		case "2":
			System.out.print("Ha triat: 2. Inserir registre."
					+ "\nIntroduïsca el valors dels camps del nou registre:\n Tìtol: ");
			valorsCamps[0] = pEntradaTeclat.nextLine();
			System.out.print(" Autor: ");
			valorsCamps[1] = pEntradaTeclat.nextLine();
			System.out.print(" Any_Naixement: ");
			valorsCamps[2] = pEntradaTeclat.nextLine();
			System.out.print(" Any_Publicació: ");
			valorsCamps[3] = pEntradaTeclat.nextLine();
			System.out.print(" Editorial: ");
			valorsCamps[4] = pEntradaTeclat.nextLine();
			System.out.print(" Pàgines: ");
			valorsCamps[5] = pEntradaTeclat.nextLine();
			inserirLliure(valorsCamps);			
			break;
			
		case "3":
			System.out.print("Ha triat: 3. Actualitzar registre."
					+ "\nIntroduïsca la ID del registre a actualitzar: ");
			numId = pEntradaTeclat.nextInt();
			pEntradaTeclat.nextLine();
			System.out.print("Introduïsca el número del camp (1-ID, 2-Tìtol, 3-Autor, 4-Any_Naixement, "
					+ "5-Any-Publicació,\n6-Editorial, 7-Págines) a actualitzar: ");
			numCamp = pEntradaTeclat.nextInt();
			pEntradaTeclat.nextLine();
			System.out.print("Introduïsca el nou valor del camp: ");
			valorCamp = pEntradaTeclat.nextLine();
			actualitzarLliure(numId, numCamp, valorCamp);			
			break;
			
		case "4":
			System.out.print("Ha triat: 4. Esborrar registre."
					+ "\nIntroduïsca el número del camp (1-ID, 2-Tìtol, 3-Autor, 4-Any_Naixement, 5-Any-Publicació,"
					+ " \n6-Editorial, 7-Págines) pel qual vulga realitzar la cerca per a esborrar els registres: ");
			numCamp = pEntradaTeclat.nextInt();
			pEntradaTeclat.nextLine();
			System.out.print("Introduïsca el valor del camp dels registres que vulga esborrar: ");
			valorCamp = pEntradaTeclat.nextLine();
			esborrarLliure(numCamp, valorCamp);			
			break;
			
		case "5":
			System.out.println("Ha triat: 5. EIXIR al menú principal.");
			break;

		default:
			System.out.print("El valor teclejat no és vàlid. Serà redirigit al menú principal");
			break;
		}		
	}
	
	private static void cercaLliure(int pNumCamp, String pValorCamp) {
		String[] camps = {"ID","Titol","Autor","Any_Naixement","Any_Publicacio","Editorial","Pagines"};
		String[] operadors= {"=","Like","Like","=","=","Like","="};
		try {
			//Carreguem el Driver per a accedir a bd MySQL
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Creem connexió a bd
			Connection conex = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca","root","");
			//Creem objecte consulta de tipus Statement
			Statement consulta = conex.createStatement();
			//Llancem la consulta i assignem a un objecte (punter), de tipus ResultSet,
			//la referencia prèvia al primer registre de la bd que satisfà la consulta.			
			ResultSet rs = consulta.executeQuery("SELECT * FROM `llibres` WHERE "+camps[pNumCamp-1]+" "+operadors[pNumCamp-1]+" '"+pValorCamp+"'");
			//Recorrem amb el punter tots els registres de la bd que satisfan la consulta.
			Boolean primerRegistre = true;
			while (rs.next()) {
				if(primerRegistre) {
					System.out.printf("\n%-5s %-32s %-20s %-15s %-17s %-15s %-10s\n", "ID","Tìtol","Autor","Any_Naixement","Any_Publicació","Editorial","Pàgines");
					primerRegistre=false;
				}
				System.out.printf("%-5s %-32s %-20s %-15s %-17s %-15s %-10s\n", rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getInt(7));
			}
			rs.close();
			consulta.close();
			conex.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}
	
	
	private static void actualitzarLliure(int pNumId, int pNumCamp, String pValorCamp) {
		String[] camps = {"ID","Titol","Autor","Any_Naixement","Any_Publicacio","Editorial","Pagines"};		
		try {
			//Carreguem el Driver per a accedir a bd MySQL
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Creem connexió a bd
			Connection conex = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca","root","");
			//Assignem la consulta al objecte consulta de tipus PreparedStatement
			PreparedStatement psActualitzar = conex.prepareStatement("UPDATE `llibres` SET "
					+camps[pNumCamp-1]+" = '"+pValorCamp+"' WHERE ID = "+pNumId);
			//Llancem la consulta y assignem el resultat (0 o 1) a la variable resultatEsborrar
			int resultatActualitzar = psActualitzar.executeUpdate();			
			if (resultatActualitzar==1) {
				System.out.println("Registre actualitzat.");
			} else {
				System.out.println("Error en actualitzar el registre.");
			}			
			psActualitzar.close();
			conex.close();			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}
		
	
	private static void inserirLliure(String[] pValorsCamps) {
		try {
			//Carreguem el Driver per a accedir a bd MySQL
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Creem connexió a bd
			Connection conex = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca","root","");
			//Assignem la consulta al objecte consulta de tipus PreparedStatement
			PreparedStatement psInserir = conex.prepareStatement("INSERT INTO llibres(ID, Titol, Autor, Any_Naixement,"
					+" Any_Publicacio, Editorial, Pagines) VALUES (?,?,?,?,?,?,?)");
			psInserir.setString(1, null);
			psInserir.setString(2, pValorsCamps[0]);
			psInserir.setString(3, pValorsCamps[1]);					
			int anyNaixement = Integer.parseInt(pValorsCamps[2]);
			psInserir.setInt(4, anyNaixement);
			int anyPublicacio = Integer.parseInt(pValorsCamps[3]);
			psInserir.setInt(5, anyPublicacio);
			psInserir.setString(6, pValorsCamps[4]);
			int nomPagines = Integer.parseInt(pValorsCamps[5]);
			psInserir.setInt(7, nomPagines);		
			
			//Llancem la consulta y assignem el resultat (0 o 1) a la variable resultatEsborrar
			int resultatInserir = psInserir.executeUpdate();			
			if (resultatInserir==1) {
				System.out.println("Registre inserit.");
			} else {
				System.out.println("Error en inserir el registre.");
			}			
			psInserir.close();
			conex.close();			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}
	
	
	private static void esborrarLliure(int pNumCamp, String pValorCamp) {
		String[] camps = {"ID","Titol","Autor","Any_Naixement","Any_Publicacio","Editorial","Pagines"};
		String[] operadors= {"=","Like","Like","=","=","Like","="};
		try {
			//Carreguem el Driver per a accedir a bd MySQL
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Creem connexió a bd
			Connection conex = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca","root","");
			//Assignem la consulta al objecte consulta de tipus PreparedStatement
			PreparedStatement psEsborrar = conex.prepareStatement("DELETE FROM `llibres` WHERE "
					+camps[pNumCamp-1]+" "+operadors[pNumCamp-1]+" '"+pValorCamp+"'");
			//Llancem la consulta y assignem el resultat (0 o 1) a la variable resultatEsborrar
			int resultatEsborrar = psEsborrar.executeUpdate();			
			if (resultatEsborrar==1) {
				System.out.println("Registres esborrats.");
			} else {
				System.out.println("No s'han trobat registres coincidents.");
			}			
			psEsborrar.close();
			conex.close();			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}
	
	
	
//	private static Boolean esNumero(String pCadena) {
//	Boolean esNumero = true;
//	char[] cadena = pCadena.toCharArray();
//		for (int i=0; i<cadena.length; i++) {			
//			if(!Character.isDigit(cadena[i])){
//				esNumero =esNumero && false;
//			}			
//		}
//		return esNumero;		
//	}
}
