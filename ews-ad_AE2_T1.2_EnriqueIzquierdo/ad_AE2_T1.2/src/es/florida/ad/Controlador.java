package es.florida.ad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controlador {
	
	//atributs
	private Modelo modelo;
	private Vista vista;
	private String rutaFichLectura, rutaFichEscritura;
	private ActionListener actionListenerBuscar, actionListenerReemplazar;

	
	//mètodes getters y setters		
	//constructors
	
	/**Mètode: Controlador
	 * Descripció: assigna a les variables de tipus "Model" i "Vista" els objectes passats per paràmetre (instanciandolos),
	 *  i flama al mètode "control".
	 * Paràmetres d'entrada: Modelo pModelo, Vista vista
	 * Paràmetres d'eixida: no */
	public Controlador(Modelo pModelo, Vista vista) {
		modelo = pModelo;
		this.vista = vista;
		control();
	}
	
	
	//mètodos interface
	
	/**Mètode: control
	 * Descripció: defineix les accions a realitzar per la classe Model en realitzar una acció sobre els botons
	 *  "Buscar" i "Reemplazar" de la classe Vista; assignant cadascuna de les accions a l'esdeveniment de l'element
	 *   de la classe Vista corresponent.
	 * Paràmetres d'entrada: 
	 * Paràmetres d'eixida:	*/
	public void control() {
		rutaFichLectura = modelo.getRutaFicheroLectura();
		rutaFichEscritura = modelo.getRutaFicheroEscritura();
		mostrarFichero(rutaFichLectura, 1);
		
		actionListenerBuscar = new ActionListener() {					
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String textoBuscar = vista.getTextFieldBuscar().getText();
				modelo.buscarTexto(textoBuscar);
				//mostrarFichero(rutaFichEscritura, 2);
			}
		};
		
		actionListenerReemplazar = new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String textoBuscar = vista.getTextFieldBuscar().getText();
				String textoReemplazador = vista.getTextFieldReemplazar().getText();
				modelo.reemplazarTexto(textoBuscar, textoReemplazador);
				mostrarFichero(rutaFichEscritura, 2);
			}
		};
		
		vista.getBtnBuscar().addActionListener(actionListenerBuscar);
		vista.getBtnReemplazar().addActionListener(actionListenerReemplazar);
	}
	
	
	//mètodes implementació	
	/**Mètode: mostrarFichero
	 * Descripció: afig a l'àrea de text, indicada mitjançant el paràmetre "pNumeroTextArea" de tipus int, el contingut del
	 *  fitxer obtingut mitjançant el mètode contenidoFichero de la classe Modelo i la ruta d'accés al fitxer indicada 
	 *  mitjançant el paràmetre "pRutaFichero".
	 * Paràmetres d'entrada: String pRutaFichero (ruta d'accés al fitxer), int pNumeroTextArea (enter associat a les àrees
	 *  de text).
	 * Paràmetres d'eixida:	no*/
	private void mostrarFichero(String pRutaFichero, int pNumeroTextArea) {
		ArrayList<String> arrayLineas = modelo.contenidoFichero(pRutaFichero);
		for (String linea : arrayLineas) {
			//System.out.print(linea);
			if (pNumeroTextArea == 1) {
				vista.getTextAreaOriginal().append(linea+"\n");
			} else {
				vista.getTextAreaModificado().append(linea+"\n");
			}
		}
	}
	

}
