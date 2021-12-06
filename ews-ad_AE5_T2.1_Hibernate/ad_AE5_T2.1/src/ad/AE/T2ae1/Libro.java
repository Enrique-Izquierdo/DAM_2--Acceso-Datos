package ad.AE.T2ae1;

public class Libro {
//atributs
	private int id;
	private String titulo;
	private String autor;
	private int anyoNacimiento;
	private int anyoPublicacion;
	private String editorial;
	private int paginas;
	
//constructors
	public Libro() {}
	
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
	
	public Libro(String titulo, String autor, int anyoNacimiento, 
			int anyoPublicacion, String editorial, int paginas) {
		this.titulo = titulo;
		this.autor = autor;
		this.anyoNacimiento = anyoNacimiento;
		this.anyoPublicacion = anyoPublicacion;
		this.editorial = editorial;
		this.paginas = paginas;
	}
	
//getters y setters
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
		
//altres mètodes d'interface
	public String toString() {
		return "id: "+getId()+", titulo: "+getTitulo()+", autor: "+getAutor()+", año de nacimiento: "+getAnyoNacimiento()
				+ ", año de publicación: "+getAnyoPublicacion()+", editorial: "+getEditorial()+", número de páginas: "+getPaginas();
	}
	
//mètodes d'implementació
}
