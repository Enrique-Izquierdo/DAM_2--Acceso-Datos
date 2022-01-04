package ad.NoSQL;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Insets;

public class Vista {
	//ATRIBUTS
	private JFrame frame;
	private JTextField textField_Opcio;
	private JScrollPane scrollPane_Opcio;
	private JTextArea textArea_Opcio;
	private JScrollPane scrollPane_2;
	private JTextArea textArea_2;
	private JTextPane txtpn_Opcio;
	private JButton btn_Opcio;
	private JButton btn_Dades;
	private JButton btn_ID;
	private JTextField textField_ID;
	private JPanel panel_Dades;
	private JPanel panel_ID; 
	private JTextPane txtpn_ID;
	private JTextPane txtpn_Titol;
	private JTextPane txtpn_Autor;
	private JTextPane txtpn_Editorial;
	private JTextPane txtpn_AnyNaixement;
	private JTextPane txtpn_AnyPublicacio;
	private JTextPane txtpn_NombrePagines;
	private JTextField textField_Titol;
	private JTextField textField_Autor;
	private JTextField textField_AnyNaixement;
	private JTextField textField_AnyPublicacio;
	private JTextField textField_Editorial;
	private JTextField textField_NombrePagines;

	
	
	//CONSTRUCTORS
	
	//GETTERS I SETTERS
	public JTextField getTextField_Opcio() {
		return textField_Opcio;
	}
	
	public JTextArea getTextArea_Opcio() {
		return textArea_Opcio;
	}
	
	public JTextArea getTextArea_2() {
		return textArea_2;
	}
	
	public JButton getBtn_Opcio() {
		return btn_Opcio;
	}
	
	public JTextField getTextField_ID() {
		return textField_ID;
	}
	
	public JPanel getPanel_ID() {
		return panel_ID;
	}
	
	public JButton getBtn_ID() {
		return btn_ID;
	}
	
	public JPanel getPanel_Dades() {
		return panel_Dades;
	}
	
	public JTextField getTextField_Titol() {
		return textField_Titol;
	}

	public JTextField getTextField_Autor() {
		return textField_Autor;
	}
	
	public JTextField getTextField_AnyNaixement() {
		return textField_AnyNaixement;
	}
	
	public JTextField getTextField_AnyPublicacio() {
		return textField_AnyPublicacio;
	}
	
	public JTextField getTextField_Editorial() {
		return textField_Editorial;
	}
	
	public JTextField getTextField_NombrePagines() {
		return textField_NombrePagines;
	}
	
	public JButton getBtn_Dades() {
		return btn_Dades;
	}
	
	
	//ALTRES MÈTODES DE INTERFACE
	/**
	 * Create the application.
	 */
	public Vista() {
		initialize();
	}
	
	//MÈTODES D' IMPLEMENTACIÓ	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1290, 726);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(26, 313, 1221, 329);
		frame.getContentPane().add(scrollPane_2);
		
		textArea_2 = new JTextArea();
		textArea_2.setMargin(new Insets(10, 10, 10, 10));
		textArea_2.setFont(new Font("Consolas", Font.PLAIN, 13));
		scrollPane_2.setViewportView(textArea_2);
		
		panel_ID = new JPanel();
		panel_ID.setVisible(false);
		panel_ID.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_ID.setBounds(652, 10, 595, 48);
		frame.getContentPane().add(panel_ID);
		panel_ID.setLayout(null);
		
		textField_ID = new JTextField();
		textField_ID.setColumns(10);
		textField_ID.setBounds(231, 11, 96, 27);
		panel_ID.add(textField_ID);
		
		btn_ID = new JButton("Validar ID");
		btn_ID.setFont(new Font("Arial", Font.BOLD, 14));
		btn_ID.setBounds(459, 10, 124, 27);
		panel_ID.add(btn_ID);
		
		txtpn_ID = new JTextPane();
		txtpn_ID.setText("Introduïsca el valor de l'ID:");
		txtpn_ID.setFont(new Font("Arial", Font.BOLD, 14));
		txtpn_ID.setBackground(SystemColor.menu);
		txtpn_ID.setBounds(10, 11, 193, 23);
		panel_ID.add(txtpn_ID);
		
		JPanel panel_Opcio = new JPanel();
		panel_Opcio.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_Opcio.setBounds(26, 10, 595, 277);
		frame.getContentPane().add(panel_Opcio);
		panel_Opcio.setLayout(null);
		
		scrollPane_Opcio = new JScrollPane();
		scrollPane_Opcio.setBorder(null);
		scrollPane_Opcio.setBounds(10, 10, 575, 195);
		panel_Opcio.add(scrollPane_Opcio);
		
		textArea_Opcio = new JTextArea();
		textArea_Opcio.setBorder(null);
		textArea_Opcio.setBackground(SystemColor.control);
		textArea_Opcio.setEditable(false);
		textArea_Opcio.setFont(new Font("Consolas", Font.BOLD, 15));
		scrollPane_Opcio.setViewportView(textArea_Opcio);
		
	
		txtpn_Opcio = new JTextPane();
		txtpn_Opcio.setBounds(25, 228, 151, 27);
		panel_Opcio.add(txtpn_Opcio);
		txtpn_Opcio.setFont(new Font("Arial", Font.BOLD, 14));
		txtpn_Opcio.setBackground(SystemColor.control);
		txtpn_Opcio.setText("Introduïsca l'opció:");
		
		textField_Opcio = new JTextField();
		textField_Opcio.setBounds(176, 228, 96, 27);
		panel_Opcio.add(textField_Opcio);
		textField_Opcio.setColumns(10);
		
		btn_Opcio = new JButton("Validar Opció");
		btn_Opcio.setBounds(416, 228, 151, 27);
		panel_Opcio.add(btn_Opcio);
		btn_Opcio.setFont(new Font("Arial", Font.BOLD, 14));
		
		panel_Dades = new JPanel();
		panel_Dades.setVisible(false);
		panel_Dades.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_Dades.setBounds(652, 68, 596, 219);
		frame.getContentPane().add(panel_Dades);
		panel_Dades.setLayout(null);
		
		JTextPane txtpn_Dades = new JTextPane();
		txtpn_Dades.setBounds(196, 10, 193, 23);
		panel_Dades.add(txtpn_Dades);
		txtpn_Dades.setText("Introduïsca les dades:");
		txtpn_Dades.setFont(new Font("Arial", Font.BOLD, 14));
		txtpn_Dades.setBackground(SystemColor.menu);
		
		txtpn_Titol = new JTextPane();
		txtpn_Titol.setText("Tìtol:");
		txtpn_Titol.setFont(new Font("Arial", Font.BOLD, 14));
		txtpn_Titol.setBackground(SystemColor.menu);
		txtpn_Titol.setBounds(10, 43, 43, 23);
		panel_Dades.add(txtpn_Titol);
		
		txtpn_Autor = new JTextPane();
		txtpn_Autor.setText("Autor/a:");
		txtpn_Autor.setFont(new Font("Arial", Font.BOLD, 14));
		txtpn_Autor.setBackground(SystemColor.menu);
		txtpn_Autor.setBounds(10, 89, 63, 23);
		panel_Dades.add(txtpn_Autor);
		
		txtpn_Editorial = new JTextPane();
		txtpn_Editorial.setText("Editorial:");
		txtpn_Editorial.setFont(new Font("Arial", Font.BOLD, 14));
		txtpn_Editorial.setBackground(SystemColor.menu);
		txtpn_Editorial.setBounds(10, 132, 70, 23);
		panel_Dades.add(txtpn_Editorial);
		
		txtpn_AnyNaixement = new JTextPane();
		txtpn_AnyNaixement.setText("Any naixement:");
		txtpn_AnyNaixement.setFont(new Font("Arial", Font.BOLD, 14));
		txtpn_AnyNaixement.setBackground(SystemColor.menu);
		txtpn_AnyNaixement.setBounds(379, 89, 119, 23);
		panel_Dades.add(txtpn_AnyNaixement);
		
		txtpn_AnyPublicacio = new JTextPane();
		txtpn_AnyPublicacio.setText("Any publicació:");
		txtpn_AnyPublicacio.setFont(new Font("Arial", Font.BOLD, 14));
		txtpn_AnyPublicacio.setBackground(SystemColor.menu);
		txtpn_AnyPublicacio.setBounds(379, 132, 116, 23);
		panel_Dades.add(txtpn_AnyPublicacio);
		
		txtpn_NombrePagines = new JTextPane();
		txtpn_NombrePagines.setText("Nombre de pàgines:");
		txtpn_NombrePagines.setFont(new Font("Arial", Font.BOLD, 14));
		txtpn_NombrePagines.setBackground(SystemColor.menu);
		txtpn_NombrePagines.setBounds(10, 169, 157, 23);
		panel_Dades.add(txtpn_NombrePagines);
		
		textField_Titol = new JTextField();
		textField_Titol.setColumns(10);
		textField_Titol.setBounds(61, 43, 514, 27);
		panel_Dades.add(textField_Titol);
		
		textField_Autor = new JTextField();
		textField_Autor.setColumns(10);
		textField_Autor.setBounds(83, 85, 273, 27);
		panel_Dades.add(textField_Autor);
		
		textField_AnyNaixement = new JTextField();
		textField_AnyNaixement.setColumns(10);
		textField_AnyNaixement.setBounds(505, 85, 70, 27);
		panel_Dades.add(textField_AnyNaixement);
		
		textField_AnyPublicacio = new JTextField();
		textField_AnyPublicacio.setColumns(10);
		textField_AnyPublicacio.setBounds(505, 128, 70, 27);
		panel_Dades.add(textField_AnyPublicacio);
		
		textField_Editorial = new JTextField();
		textField_Editorial.setColumns(10);
		textField_Editorial.setBounds(93, 128, 248, 27);
		panel_Dades.add(textField_Editorial);
		
		textField_NombrePagines = new JTextField();
		textField_NombrePagines.setColumns(10);
		textField_NombrePagines.setBounds(168, 169, 96, 27);
		panel_Dades.add(textField_NombrePagines);
		
		btn_Dades = new JButton("Validar Dades");
		btn_Dades.setFont(new Font("Arial", Font.BOLD, 14));
		btn_Dades.setBounds(451, 182, 135, 27);
		panel_Dades.add(btn_Dades);
		
		frame.setVisible(true);
	}
}
