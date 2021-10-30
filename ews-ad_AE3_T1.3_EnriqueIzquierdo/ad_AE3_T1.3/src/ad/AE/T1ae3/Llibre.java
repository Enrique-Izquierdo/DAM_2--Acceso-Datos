package ad.AE.T1ae3;

public class Llibre {
	//atributs
	private String identificador, titol, autor, anyPublicacio, editorial, nombrePagines;		
	//constructors
	/**Mètode: Llibre
	 * Descripció: inicialitza els atributs de la classe.
	 * Paràmetres d'entrada: identificador (String), titol (String), artista (String),  
	 * 						anyPublicacio (String), editorial (String), nombrePagines (String)
	 * Paràmetres d'eixida:	no*/
	public Llibre(String identificador, String titol, String artista, String anyPublicacio, 
			String editorial, String nombrePagines) {
		this.identificador = identificador;
		this.titol = titol;
		this.autor = artista;
		this.anyPublicacio = anyPublicacio;
		this.editorial = editorial;
		this.nombrePagines = nombrePagines;
	}		
	//getters y setters
	public String getIdentificador() {
		return identificador;
	}
		
	public String getTitol() {
		return titol;
	}
		
	public String getAutor() {
		return autor;
	}
		
	public String getAnyPublicacio() {
		return anyPublicacio;
	}
		
	public String getEditorial() {
		return editorial;
	}
	
	public String getNombrePagines() {
		return nombrePagines;
	}
	
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
		
	public void setTitol(String titol) {
		this.titol = titol;
	}
		
	public void setAutor(String artista) {
		this.autor = artista;
	}
		
	public void setAnyPublicacio(String anyPublicacio) {
		this.anyPublicacio = anyPublicacio;
	}
		
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	
	public void setNombrePagines(String nombrePagines) {
		this.nombrePagines = nombrePagines;
	}
	//altres mètodes de interface
	//mètodes de implementació
}
