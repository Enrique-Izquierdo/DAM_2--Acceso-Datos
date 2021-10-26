package es.florida.ad;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Modelo {
	//atributs
	private String rutaFicheroLectura;
	private String rutaFicheroEscritura;
	
	//mètodes getters y setters
	public String getRutaFicheroLectura(){
		return rutaFicheroLectura;		
	}
	
	public String getRutaFicheroEscritura() {
		return rutaFicheroEscritura;
	}
	
	//constructores
	/**Mètode: mètode constructor de la clase Modelo. 
	 * Descripció: asigna a dos Strings, las rutas relativas de acceso a dos archivos .txt.
	 * Paràmetres d'entrada: no
	 * Paràmetres d'eixida: no	*/
	public Modelo() {
		rutaFicheroLectura = "AE02_T1_2_Streams_Groucho.txt";
		rutaFicheroEscritura ="AE02_T1_2_Streams_Groucho_Mod.txt";
	}
	
	//mètodes implementació
	//mètodes interface
	
	/**Mètode: contenidoFichero
	 * Descripció: mètode que llig el contingut del fitxer, la ruta relativa del qual d'accés és passada per paràmetre 
	 *  mitjançant un String, i emmagatzema en un ArrayList de Strings el contingut del fitxer abans indicat.
	 * Paràmetres d'entrada: String amb la ruta del fitxer.
	 * Paràmetres d'eixida:	ArrayList de Strings amb el contingut del fitxer.*/
	public ArrayList<String> contenidoFichero(String pRutaFichero) {
		ArrayList<String> contenidoFichero = new ArrayList<String>();
		File fichero = new File(pRutaFichero); /**Declarem un objecte "fitxer" que apunta 
		a la ubicació en memòria del fitxer indicat mitjançant la ruta relativa.*/
		try {
			FileReader fr = new FileReader(fichero, StandardCharsets.UTF_8); /**Obrim un flux d'ENTRADA
			de caràcters (grandària = 16 bits = 1 caràcter).No és necessari utilitzar StandardCharsets.*/
			BufferedReader br = new BufferedReader(fr); /**Obrim un flux d'ENTRADA de buffers de caràcters 
			(agrupacions de caràcters), amb capacitat igual a la grandària de la memòria intermèdia.*/
			String linea = br.readLine(); /**Assignem a un String la primera línia de caràcters, 
			no llegits anteriorment per ".*readLine()". Aquest mètode retorna "*null" quan no queden 
			mes línies per llegir.*/
			while(linea != null) {
				contenidoFichero.add(linea);
				linea = br.readLine(); /**Llegim la següent línia i l'assignem al *String "linea".*/
			}
			br.close(); /**Tanquem el flux d'entrada de buffers de caràcters.*/
			fr.close(); /**Tanquem el flux d'entrada de caràcters.*/		
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);			
		}
		return contenidoFichero;
	}
	
	
	/**Mètode: buscarTexto
	 * Descripció: cerca les concurrències del String, passat per paràmetre, dins del fitxer, indicat mitjançant 
	 *  la ruta d'accés, i mostra el nombre de concurrències trobades amb un missatge tipus pop up.
	 * Paràmetres d'entrada: String amb el text buscat. 
	 * Paràmetres d'*eixida: no.*/
	public void buscarTexto(String pTextoBuscado) {
		File f1 = new File(getRutaFicheroLectura());		
		try {
			FileReader fr = new FileReader(f1, StandardCharsets.UTF_8); 
			BufferedReader br = new BufferedReader(fr); 
			int contador=0;
			String linea = br.readLine();			
			while (linea != null) {				
				while(linea.indexOf(pTextoBuscado)>-1) {
					linea = linea.substring(linea.indexOf(pTextoBuscado)+pTextoBuscado.length());
					contador++;
				}
				linea = br.readLine();
			}
			JOptionPane.showMessageDialog(new JFrame(), contador , "Número de Coincidencias", JOptionPane.INFORMATION_MESSAGE);
			br.close();
			fr.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		} 
	}
	
	
	/**Mètode: reemplazarTexto
	 * Descripció: cerca les concurrències del String "pTextoBuscar", passat per paràmetre, dins del fitxer, indicat mitjançant 
	 *  la ruta d'accés, i les substitueix per el String "pTextoReemplazar", escrivint el text resultant en un nou arxiu, 
	 *  indicat mitjançant la ruta d'accés.
	 * Paràmetres d'entrada: String amb el text buscat, String amb el text de reemplaçament. 
	 * Paràmetres d'*eixida: no.*/
	public void reemplazarTexto(String pTextoBuscar, String pTextoReemplazador) {
		try {
			FileReader fr = new FileReader(getRutaFicheroLectura());
			BufferedReader br = new BufferedReader(fr);
			FileWriter fw = new FileWriter(getRutaFicheroEscritura());
			BufferedWriter bw = new BufferedWriter(fw);
			String linea = br.readLine();
			while (linea != null) {				
				String lineaMod = linea.replaceAll(pTextoBuscar, pTextoReemplazador);
				//System.out.print(pTextoBuscar+"\n"+pTextoReemplazador+"\n"+lineaMod+"\n");
				bw.write(lineaMod);
				bw.newLine();
				linea = br.readLine();
			}
			br.close();
			fr.close();
			bw.close();
			fw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			//e.printStackTrace();
		}
	}
}
