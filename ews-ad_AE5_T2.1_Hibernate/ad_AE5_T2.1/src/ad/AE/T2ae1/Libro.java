package ad.AE.T2ae1;

public class Libro {
	//ATRIBUTS
	private int id;
	private String titulo;
	private String autor;
	private int anyoNacimiento;
	private int anyoPublicacion;
	private String editorial;
	private int paginas;
	
	//CONSTRUCTORS
	/**Mètode: Libro
	 * Descripció: constructor per defecte de la classe Libro.
	 * Paràmetres d'entrada: no
	 * Paràmetres d'eixida:	no*/
	public Libro() {}
	
	/**Mètode: Libro
	 * Descripció: constructor de la classe Libro que inicialitza tots els atributs de la classe.
	 * Paràmetres d'entrada: id (int), titulo (String), autor (String), anyoNacimiento (String), 
	 * 						anyoPublicacion (String), editorial (String), paginas (int)
	 * Paràmetres d'eixida:	no*/
	public Libro(int id, String titulo, String autor, int anyoNacimiento, 
			int anyoPublicacion, String editorial, int paginas) {
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.anyoNacimiento = anyoNacimiento;
		this.anyoPublicacion = anyoPublicacion;
		this.editorial = editorial;
		this.paginas = paginas;
	}
	
	/**Mètode: Libro
	 * Descripció: constructor de la classe Libro que inicialitza tots els atributs de la classe, excepte l'id.
	 * Paràmetres d'entrada: titulo (String), autor (String), anyoNacimiento (String), 
	 * 						anyoPublicacion (String), editorial (String), paginas (int)
	 * Paràmetres d'eixida:	no*/
	public Libro(String titulo, String autor, int anyoNacimiento, 
			int anyoPublicacion, String editorial, int paginas) {
		this.titulo = titulo;
		this.autor = autor;
		this.anyoNacimiento = anyoNacimiento;
		this.anyoPublicacion = anyoPublicacion;
		this.editorial = editorial;
		this.paginas = paginas;
	}
	
	//GETTERS I SETTERS
	public int getId() { return this.id; }
	public String getTitulo() { return this.titulo; }
	public String getAutor() { return this.autor; }
	public int getAnyoNacimiento() { return this.anyoNacimiento; }
	public int getAnyoPublicacion() { return this.anyoPublicacion; }
	public String getEditorial() { return this.editorial; }
	public int getPaginas () { return this.paginas; }
	
	public void setId(int id) { this.id = id; }
	public void setTitulo(String titulo) { this.titulo = titulo; }
	public void setAutor(String autor) { this.autor = autor; }
	public void setAnyoNacimiento(int anyoNacimiento) { this.anyoNacimiento = anyoNacimiento; }
	public void setAnyoPublicacion(int anyoPublicacio) { this.anyoPublicacion = anyoPublicacio; }
	public void setEditorial(String editorial) { this.editorial = editorial; }
	public void setPaginas(int paginas) { this.paginas = paginas; }
		
	//ALTRES MÈTODES D'INTERFACE
	/**Mètode: toString
	 * Descripció: retorna un String amb els valors del tots els atributs del objecte de la classe Libro.
	 * Paràmetres d'entrada: no
	 * Paràmetres d'eixida:	String*/
	public String toString() {
		return "    id: "+getId()+"\n    titol: "+getTitulo()+"\n    autor: "+getAutor()
				+"\n    any de naiximent: "+getAnyoNacimiento()	+ "\n    any de publicació: "+getAnyoPublicacion()
				+"\n    editorial: "+getEditorial()+"\n    nombre de pàgines: "+getPaginas();
	}
	
	//MÈTODES D'IMPLEMENTACIÓ
}
