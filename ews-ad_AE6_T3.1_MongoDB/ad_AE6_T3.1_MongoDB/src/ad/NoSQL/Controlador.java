package ad.NoSQL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Controlador {
	//ATRIBUTS
	private Modelo modelo;
	private Vista vista;
	private ActionListener actionListener_Opcio, actionListener_ID, actionListener_Dades;
	private List<String> inputsTeclat = new ArrayList<>();
	
	//CONSTRUCTORS
	/**Mètode: Controlador
	 * Descripció: instancia la clase Controlador i inicialitza el atributs modelo i
	 * 				 vista amb els valor passats per paràmetre.
	 * Paràmetres d'entrada: pModelo (Modelo), pVista (Vista) 
	 * Paràmetres d'eixida: no */
	public Controlador(Modelo pModelo, Vista pVista) {
		this.modelo = pModelo;
		vista = pVista;
		control();
	}
	
	//GETTERS I SETTERS
	//ALTRES MÈTODES DE INTERFACE	
	//MÈTODES D' IMPLEMENTACIÓ
	/**Mètode: controlr
	 * Descripció: gestiona els actionListeners, interactuan amb les classes Vista i Modelo.
	 * Paràmetres d'entrada: pModelo (Modelo), pVista (Vista) 
	 * Paràmetres d'eixida: no */
	private void control() {
		mostrarTexto(modelo.getMenu(), 1);
		
		actionListener_Opcio = new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				vista.getTextField_ID().setText("");
				vista.getPanel_ID().setVisible(false);
				vista.getTextField_Titol().setText("");
				vista.getTextField_Autor().setText("");
				vista.getTextField_AnyNaixement().setText("");
				vista.getTextField_AnyPublicacio().setText("");
				vista.getTextField_Editorial().setText("");
				vista.getTextField_NombrePagines().setText("");
				vista.getPanel_Dades().setVisible(false);
				
				String opcio = vista.getTextField_Opcio().getText();
				inputsTeclat.clear();
				inputsTeclat.add(0, opcio);
				switch (inputsTeclat.get(0)) {
				case "1":					
					mostrarTexto(modelo.executarOpcio(inputsTeclat), 2);
					vista.getTextField_Opcio().setText("");
					break;
				case "2":
					vista.getPanel_ID().setVisible(true);
					break;
				case "3":
					vista.getPanel_Dades().setVisible(true);
					break;
				case "4":
					vista.getPanel_ID().setVisible(true);
					break;
				case "5":
					vista.getPanel_ID().setVisible(true);
					break;
				default:
					mostrarTexto(modelo.executarOpcio(inputsTeclat), 2);
					vista.getTextField_Opcio().setText("");
					break;
				}
			}
		};		
		vista.getBtn_Opcio().addActionListener(actionListener_Opcio);
		
		actionListener_ID = new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String id = vista.getTextField_ID().getText();
				inputsTeclat.add(1, id);
				switch (inputsTeclat.get(0)) {
				case "2":
					mostrarTexto(modelo.executarOpcio(inputsTeclat), 2);
					vista.getTextField_Opcio().setText("");
					vista.getTextField_ID().setText("");
					vista.getPanel_ID().setVisible(false);
					break;
				case "4":
					vista.getPanel_Dades().setVisible(true);
					break;
				case "5":
					mostrarTexto(modelo.executarOpcio(inputsTeclat), 2);
					vista.getTextField_Opcio().setText("");
					vista.getTextField_ID().setText("");
					vista.getPanel_ID().setVisible(false);
					break;
				default:
					break;
				}
			}
		};		
		vista.getBtn_ID().addActionListener(actionListener_ID);
		
		
		actionListener_Dades = new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub				
				String titol = vista.getTextField_Titol().getText();
				String autor = vista.getTextField_Autor().getText();
				String anyNaixement = vista.getTextField_AnyNaixement().getText();
				String anyPublicacio = vista.getTextField_AnyPublicacio().getText();
				String editorial = vista.getTextField_Editorial().getText();
				String nombrePagines = vista.getTextField_NombrePagines().getText();
				
				if (inputsTeclat.get(0).equals("3")) {
					inputsTeclat.add(1, " ");
				}				
				inputsTeclat.add(2, titol);
				inputsTeclat.add(3, autor);
				inputsTeclat.add(4, anyNaixement);
				inputsTeclat.add(5, anyPublicacio);
				inputsTeclat.add(6, editorial);
				inputsTeclat.add(7, nombrePagines);
				
				mostrarTexto(modelo.executarOpcio(inputsTeclat), 2);
				vista.getTextField_Opcio().setText("");
				vista.getTextField_ID().setText("");
				vista.getPanel_ID().setVisible(false);
				vista.getTextField_Titol().setText("");
				vista.getTextField_Autor().setText("");
				vista.getTextField_AnyNaixement().setText("");
				vista.getTextField_AnyPublicacio().setText("");
				vista.getTextField_Editorial().setText("");
				vista.getTextField_NombrePagines().setText("");
				vista.getPanel_Dades().setVisible(false);
			}
		};		
		vista.getBtn_Dades().addActionListener(actionListener_Dades);
		
	}
	
	//CONSTRUCTORS
	/**Mètode: mostrarTexto
	 * Descripció: mostra el contingut del String en el TextArea indicat, tots dos, passats per paràmetre.
	 * Paràmetres d'entrada: texto (String), numeroTextArea (int)
	 * Paràmetres d'eixida: no */
	
	private void mostrarTexto(String texto, int numeroTextArea) {
		if(numeroTextArea == 1) {
			vista.getTextArea_Opcio().setText(texto);
		} else {
			vista.getTextArea_2().setText(texto);
		}
	}
}
