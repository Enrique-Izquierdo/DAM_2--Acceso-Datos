package ad.AE.T1ae3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class AE3_T1_3 {

	//atributs
	//constructors
	//getters y setters
	//altres mètodes de interface
	
	/**Mètode:main
	 * Descripció: Mostra el menú de l'aplicació, i invoca al mètode de l'opció triada.
	 * Paràmetres d'entrada: no utilitzats
	 * Paràmetres d'eixida:	 no */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner entradaTeclat = new Scanner(System.in);
		Boolean continuar = true;			
		do {
			System.out.println("\nOpcions Disponibles a la Biblioteca\n"
					+ "===================================");
			System.out.println("1. Mostrar tots els títols de la biblioteca.\n"
					+ "2. Mostrar informació detallada d´un llibre.\n"
					+ "3. Crear nou llibre.\n"
					+ "4. Actualitzar llibre.\n"
					+ "5. Borrar llibre.\n"
					+ "6. Tanca la biblioteca.");
			System.out.print("Introduïsca el número de l'opció seleccionada: ");			

			switch (entradaTeclat.nextInt()) {
			case 1:
				System.out.println("\nHa seleccionat: 1.Mostrar tots els títols de la biblioteca.");
				if (recuperarTots().size()<1) {
					System.out.println("No hi ha registrat cap llibre a la biblioteca.");
				} else {
					System.out.println("  Identificador\t-->  Títol");
					for (Llibre llibre : recuperarTots()) {
						System.out.println("\t"+llibre.getIdentificador()+"\t--> "+llibre.getTitol());
					}
				}				
				break;
			case 2:
				System.out.print("\nHa seleccionat: 2.Mostrar informació detallada d´un llibre."
						+ "\nIntroduïsca el número identificador del llibre:");			
				mostrarLlibre(recuperarLlibre(entradaTeclat.nextInt()));
				break;
			case 3:
				System.out.println("\nHa seleccionat: 3.Crear nou llibre.\n"
						+ "Introdueix les dades del llibre:");
				entradaTeclat.nextLine();
				System.out.print("\t- Títol: ");
				String titol = entradaTeclat.nextLine();
				System.out.print("\t- Autor: ");
				String autor = entradaTeclat.nextLine();
				System.out.print("\t- Any de Publicació: ");
				String anyPublicacio = entradaTeclat.nextLine();
				System.out.print("\t- Editorial: ");
				String editorial = entradaTeclat.nextLine();
				System.out.print("\t- Nombre de Pàgines: ");
				String nombrePagines = entradaTeclat.nextLine();
				
				String identificador;
				if(recuperarTots().size()<=0) {
					identificador = "1";
				} else {
					int lastIndex = Biblioteca.getColeccioLlibres().size()-1;
					String ultimoIdentificador = Biblioteca.getColeccioLlibres().get(lastIndex).getIdentificador();
					int ident = Integer.parseInt(ultimoIdentificador)+1;
					identificador = ((Integer)ident).toString();
				}				
				Llibre llibre = new Llibre(identificador, titol, autor, anyPublicacio, editorial, nombrePagines);
				crearLlibre(llibre);
				break;
			case 4:
				System.out.print("\nHa seleccionat: 4.Actualitzar llibre."
						+ "\nIntroduïsca el número identificador del llibre a actualitzar:");
				actualitzarLlibre(entradaTeclat.nextInt());
				break;
			case 5:
				System.out.print("\nHa seleccionat: 5.Borrar llibre."
						+ "\nIntroduïsca el número identificador del llibre a borrar:");			
				borrarLlibre(entradaTeclat.nextInt());
				break;
			case 6:
				System.out.println("\nHa seleccionat: 6.Tanca la biblioteca.");
				continuar=false;
				break;
			default:
				System.out.println("\nL'opció seleccionada no és valguda.");
				break;				
			}
			
			if (continuar) {
				System.out.print("\nPresione ENTER para continuar...\n\n");
				try {
					System.in.read();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("\nSe ha producido un error durante la entrada o salida de datos.");
					e.printStackTrace();
				}
			} else {
				System.out.println("\nHa salido de la aplicación.");
			}					
			
		} while (continuar);
		entradaTeclat.close();
	}
	
	/**Classe: BiBlioteca
	 * Descripció: Constituïda per un atribut coleccioLlibres de tipus ArrayLista, 
	 * 				amb els seus getters i setters, i el constructor*/
	public static class Biblioteca{
		//atributes
		private static ArrayList<Llibre> coleccioLlibres= new ArrayList<>();
		
		//constructor
		public Biblioteca() {}
		
		//getters y setters
		public static ArrayList<Llibre> getColeccioLlibres(){
			return coleccioLlibres;
		}
		
		public static void setColeccioLlibres(ArrayList<Llibre> pColeccioLlibre) {
			coleccioLlibres = pColeccioLlibre;			
		}		
	}
	
	
	//mètodes de implementació
	
	/**Mètode: crearLlibre
	 * Descripció: Afig el llibre passat per paràmetre al final del ArrayList coleccioLlibres 
	 *             de la classe Biblioteca. Crea l'estructura DOM del nou ArrayList de Llibres, 
	 *             el serialitza i assegura la seua persistència, li dona format i ho guarda 
	 *             en un arxiu xml.
	 * Paràmetres d'entrada: llibre (Llibre)
	 * Paràmetres d'eixida: id (int, identificador del llibre)*/
	private static int crearLlibre(Llibre llibre) {
		Biblioteca.getColeccioLlibres().add(llibre);
		int id = Integer.parseInt(llibre.getIdentificador());
		//Creem l'estructura DOM amb els dades del atribut ColeccioLlibres (ArrayList-Llibres-) 
	    //de la classe Biblioteca.
		try {
			DocumentBuilderFactory docBF = DocumentBuilderFactory.newInstance();
			DocumentBuilder docB = docBF.newDocumentBuilder();
			Document doc = docB.newDocument();
			
			Element raiz = doc.createElement("llibres");
			doc.appendChild(raiz);
			
			for (Llibre book : Biblioteca.getColeccioLlibres()) {
				Element eLlibre = doc.createElement("llibre");				
				String identificador = String.valueOf(book.getIdentificador());
				eLlibre.setAttribute("identificador", identificador);
				raiz.appendChild(eLlibre);
				
				Element eTitol = doc.createElement("titol");
				eTitol.appendChild(doc.createTextNode(String.valueOf(book.getTitol())));
				eLlibre.appendChild(eTitol);
				
				Element eAutor = doc.createElement("autor");
				eAutor.appendChild(doc.createTextNode(String.valueOf(book.getAutor())));
				eLlibre.appendChild(eAutor);
				
				Element eAnyPublicacio = doc.createElement("anyPublicacio");
				eAnyPublicacio.appendChild(doc.createTextNode(String.valueOf(book.getAnyPublicacio())));
				eLlibre.appendChild(eAnyPublicacio);
				
				Element eEditorial = doc.createElement("editorial");
				eEditorial.appendChild(doc.createTextNode(String.valueOf(book.getEditorial())));
				eLlibre.appendChild(eEditorial);
				
				Element eNombrePagines = doc.createElement("nombrePagines");
				eNombrePagines.appendChild(doc.createTextNode(String.valueOf(book.getNombrePagines())));
				eLlibre.appendChild(eNombrePagines);
			}
			
			//Creem serialitzador			
			TransformerFactory tranFactory = TransformerFactory.newInstance();
			Transformer aTransformer = tranFactory.newTransformer();
			
			//Donem format al document
			aTransformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			aTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
			
			DOMSource source = new DOMSource(doc);
			
			//Definim el nom del fitxer i guardar			
			FileWriter fw;
			try {
				fw = new FileWriter("biblioteca.xml");
				StreamResult result = new StreamResult(fw);
				aTransformer.transform(source, result);
				fw.close();
			} catch (IOException | TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (ParserConfigurationException | TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}					
		return	id;
	}
	
	/**Mètode: recuperarLlibre
	 * Descripció: Retorna el llibre corresponen al identificador passat per paràmetre
	 * Paràmetres d'entrada: identificado (int, identificador del llibre)
	 * Paràmetres d'eixida:	llibre (Llibre)*/
	private static Llibre recuperarLlibre(int identificador) {
		String identif = ((Integer)identificador).toString();
		Llibre llibre = null;
		
		for (Llibre book : recuperarTots()) {
			if (book.getIdentificador().equals(identif)) {
				llibre = book;
			}
		}				
		return llibre;
	}
	
	/**Mètode: mostrarLlibre
	 * Descripció: Mostra el llibre passat per paràmetre
	 * Paràmetres d'entrada: llibre (Llibre)
	 * Paràmetres d'eixida:	no */
	private static void mostrarLlibre(Llibre llibre) {
		if (llibre == null) {
			System.out.println("No existeix cap llibre amb l'identificador indicat.");
		} else {
			System.out.println("\tIdentificador: "+llibre.getIdentificador()+"\n\tTítol: "+
					llibre.getTitol()+"\n\tAutor: "+llibre.getAutor()+"\n\tAny de publició: "+
					llibre.getAnyPublicacio()+"\n\tEditorial: "+llibre.getEditorial()+
					"\n\tNombre de pàgines: "+llibre.getNombrePagines());			
		}
	}
	
	/**Mètode: borrarLlibre
	 * Descripció: Esborra el llibre corresponent a l'identificador passat per paràmetre. Crea 
	 *             l'estructura DOM del nou ArrayList de Llibres que conté la classe Biblioteca, el 
	 *             serialitza i assegura la seua persistència, li dona format i ho guarda en un arxiu xml.
	 * Paràmetres d'entrada: llibre (Llibre)
	 * Paràmetres d'eixida: id (int, identificador del llibre)*/
	private static void borrarLlibre(int identificador) {
		String identif = ((Integer)identificador).toString();		
		Llibre llibre = null;
		for (Llibre book : recuperarTots()) {
			if (book.getIdentificador().equals(identif)) {
				llibre = book;				
			}
		}			
		
		if(llibre == null) {
			System.out.println("No existeix cap llibre amb l'identificador indicat.");
		} else {
			int index = Biblioteca.getColeccioLlibres().indexOf(llibre);
			ArrayList<Llibre> coleccioActualitzada= Biblioteca.getColeccioLlibres();
			coleccioActualitzada.remove(index);
			Biblioteca.setColeccioLlibres(coleccioActualitzada);
			//Creem l'estructura DOM amb els dades del atribut ColeccioLlibres (ArrayList-Llibres-) 
		    //de la classe Biblioteca.
			try {
				DocumentBuilderFactory docBF = DocumentBuilderFactory.newInstance();
				DocumentBuilder docB = docBF.newDocumentBuilder();
				Document doc = docB.newDocument();
				
				Element raiz = doc.createElement("llibres");
				doc.appendChild(raiz);
				
				for (Llibre book : Biblioteca.getColeccioLlibres()) {
					Element eLlibre = doc.createElement("llibre");				
					String ident = String.valueOf(book.getIdentificador());
					eLlibre.setAttribute("identificador", ident);
					raiz.appendChild(eLlibre);
					
					Element eTitol = doc.createElement("titol");
					eTitol.appendChild(doc.createTextNode(String.valueOf(book.getTitol())));
					eLlibre.appendChild(eTitol);
					
					Element eAutor = doc.createElement("autor");
					eAutor.appendChild(doc.createTextNode(String.valueOf(book.getAutor())));
					eLlibre.appendChild(eAutor);
					
					Element eAnyPublicacio = doc.createElement("anyPublicacio");
					eAnyPublicacio.appendChild(doc.createTextNode(String.valueOf(book.getAnyPublicacio())));
					eLlibre.appendChild(eAnyPublicacio);
					
					Element eEditorial = doc.createElement("editorial");
					eEditorial.appendChild(doc.createTextNode(String.valueOf(book.getEditorial())));
					eLlibre.appendChild(eEditorial);
					
					Element eNombrePagines = doc.createElement("nombrePagines");
					eNombrePagines.appendChild(doc.createTextNode(String.valueOf(book.getNombrePagines())));
					eLlibre.appendChild(eNombrePagines);
				}
				
				//Creem serialitzador.			
				TransformerFactory tranFactory = TransformerFactory.newInstance();
				Transformer aTransformer = tranFactory.newTransformer();
				
				//Donem format al document.
				aTransformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				aTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
				aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
				
				DOMSource source = new DOMSource(doc);
				
				//Definim el nom del fitxer i guardar			
				FileWriter fw;
				try {
					fw = new FileWriter("biblioteca.xml");
					StreamResult result = new StreamResult(fw);
					aTransformer.transform(source, result);
					fw.close();
				} catch (IOException | TransformerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} catch (ParserConfigurationException | TransformerConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			
			System.out.println("El llibre amb l'identificador "+ identificador +" ha sigut esborrat.");
		}	
	}
	
	/**Mètode: actualitzarLlibre
	 * Descripció: Substitueix el llibre corresponent a l'identificador passat per paràmetre, amb
	 *             el nou llibre creat a partir de les dades sol·licitades per teclat. Crea 
	 *             l'estructura DOM del nou ArrayList de Llibres que conté la classe Biblioteca, el 
	 *             serialitza i assegura la seua persistència, li dona format i ho guarda en un arxiu xml.
	 * Paràmetres d'entrada: llibre (Llibre)
	 * Paràmetres d'eixida: id (int, identificador del llibre)*/
	private static void actualitzarLlibre(int identificador) {
		String identif = ((Integer)identificador).toString();		
		Llibre llibre = null;		
		for (Llibre book : recuperarTots()) {
			if (book.getIdentificador().equals(identif)) {
				llibre = book;				
			}
		}			
		
		if(llibre == null) {
			System.out.println("No existeix cap llibre amb l'identificador indicat.");
		} else {
			int index = Biblioteca.getColeccioLlibres().indexOf(llibre);			
			//Llibre llibre = null;
			String ident = ((Integer)identificador).toString();
			InputStreamReader cr_entradaTeclat = new InputStreamReader(System.in);
			BufferedReader br_EntradaTeclat = new BufferedReader(cr_entradaTeclat);		
			try {
				System.out.print("Introduïsca la resta de dades:\n\t- Títol: ");
				String titol = br_EntradaTeclat.readLine();
				System.out.print("\t- Autor: ");
				String autor = br_EntradaTeclat.readLine();
				System.out.print("\t- Any de Publicació: ");
				String anyPublicacio = br_EntradaTeclat.readLine();
				System.out.print("\t- Editorial: ");
				String editorial = br_EntradaTeclat.readLine();
				System.out.print("\t- Nombre de Pàgines: ");
				String nombrePagines = br_EntradaTeclat.readLine();
				//br_EntradaTeclat.close();
				//cr_entradaTeclat.close();				
				llibre = new Llibre(ident, titol, autor, anyPublicacio, editorial, nombrePagines);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ArrayList<Llibre> collecioActualitzada =  Biblioteca.getColeccioLlibres();			
			collecioActualitzada.set(index, llibre);
		    Biblioteca.setColeccioLlibres(collecioActualitzada);
		    
			//Creem l'estructura DOM amb els dades del atribut ColeccioLlibres (ArrayList-Llibres-) 
		    //de la classe Biblioteca.
			try {
				DocumentBuilderFactory docBF = DocumentBuilderFactory.newInstance();
				DocumentBuilder docB = docBF.newDocumentBuilder();
				Document doc = docB.newDocument();
				
				Element raiz = doc.createElement("llibres");
				doc.appendChild(raiz);
				
				for (Llibre book : Biblioteca.getColeccioLlibres()) {
					Element eLlibre = doc.createElement("llibre");				
					String identifica = String.valueOf(book.getIdentificador());
					eLlibre.setAttribute("identificador", identifica);
					raiz.appendChild(eLlibre);
					
					Element eTitol = doc.createElement("titol");
					eTitol.appendChild(doc.createTextNode(String.valueOf(book.getTitol())));
					eLlibre.appendChild(eTitol);
					
					Element eAutor = doc.createElement("autor");
					eAutor.appendChild(doc.createTextNode(String.valueOf(book.getAutor())));
					eLlibre.appendChild(eAutor);
					
					Element eAnyPublicacio = doc.createElement("anyPublicacio");
					eAnyPublicacio.appendChild(doc.createTextNode(String.valueOf(book.getAnyPublicacio())));
					eLlibre.appendChild(eAnyPublicacio);
					
					Element eEditorial = doc.createElement("editorial");
					eEditorial.appendChild(doc.createTextNode(String.valueOf(book.getEditorial())));
					eLlibre.appendChild(eEditorial);
					
					Element eNombrePagines = doc.createElement("nombrePagines");
					eNombrePagines.appendChild(doc.createTextNode(String.valueOf(book.getNombrePagines())));
					eLlibre.appendChild(eNombrePagines);
				}
				
				//Creem serialitzador			
				TransformerFactory tranFactory = TransformerFactory.newInstance();
				Transformer aTransformer = tranFactory.newTransformer();
				
				//Donem format al document
				aTransformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				aTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
				aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
				
				DOMSource source = new DOMSource(doc);
				
				//Definim el nom del fitxer i guardar		
				FileWriter fw;
				try {
					fw = new FileWriter("biblioteca.xml");
					StreamResult result = new StreamResult(fw);
					aTransformer.transform(source, result);
					fw.close();
				} catch (IOException | TransformerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} catch (ParserConfigurationException | TransformerConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		    		    		    
			System.out.println("El llibre amb l'identificador "+ identificador +" ha sigut actualitzat.");
		}			
	}
	
	/**Mètode: recuperarTots
	 * Descripció: Llig l'arxiu XML que conté la llista de llibres i els assigna a l'atribut 
	 *             coleccioLlibre de la classe Biblioteca
	 * Paràmetres d'entrada: no
	 * Paràmetres d'eixida:	 ArrayList de Llibres */
	private static ArrayList<Llibre> recuperarTots() {
		ArrayList<Llibre> coleccioLlibres = new ArrayList<Llibre>();		
		File archiu = new File ("biblioteca.xml");
		if (archiu.exists()) {			
			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(archiu);
				//Element raiz = doc.getDocumentElement();
				NodeList llistaNodes = doc.getElementsByTagName("llibre");			
				for (int i = 0; i<llistaNodes.getLength(); i++) {
					Node node = llistaNodes.item(i);				
					if(node.getNodeType() == Node.ELEMENT_NODE) { //La condició verifica que l'objecte 
								//assignat a node és un node. Evita problemes amb xml mal estructurats.
						Element eElement = (Element)node;
						String identificador = eElement.getAttribute("identificador");
						String titol = eElement.getElementsByTagName("titol").item(0).getTextContent();
						String autor = eElement.getElementsByTagName("autor").item(0).getTextContent();
						String anyPublicacio = eElement.getElementsByTagName("anyPublicacio").item(0).getTextContent();
						String editorial = eElement.getElementsByTagName("editorial").item(0).getTextContent();
						String nombrePagines = eElement.getElementsByTagName("nombrePagines").item(0).getTextContent();					
						coleccioLlibres.add(new Llibre(identificador, titol, autor, anyPublicacio, editorial, nombrePagines));
						//idLlibre = Integer.parseInt(id);
					}				
				}	
				Biblioteca.setColeccioLlibres(coleccioLlibres);
			} catch (ParserConfigurationException | SAXException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}					
		}
		return Biblioteca.getColeccioLlibres();
	}
}
