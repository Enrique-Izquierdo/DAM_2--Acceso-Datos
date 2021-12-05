package ad.AE.T2ae1;

public class Libro {
//atributs
	private int id;
	private String titulo;
	private String autor;
	private int anyoNacimiento;
	private int anyoPublicacion;
	private String editorial;
	private int pagina;
	
//constructors
//getters y setters
	public int getId() { return id; }
	public String getTitulo() { return titulo; }
	public String getAutor() { return autor; }
	public int getAnyoNacimiento() { return anyoNacimiento; }
	public int getAnyoPublicacio() { return anyoPublicacion; }
	public String getEditorial() { return editorial; }
	public int getPagina () { return pagina; }
	
	public void setId(int id) { this.id = id; }
	public void setTitulo(String titulo) { this.titulo = titulo; }
	public void setAutor(String autor) { this.autor = autor; }
	public void setAnyoNacimiento(int anyoNacimiento) { this.anyoNacimiento = anyoNacimiento; }
	public void setAnyoPublicacio(int anyoPublicacio) { this.anyoPublicacion = anyoPublicacio; }
	public void setEditorial(String editorial) { this.editorial = editorial; }
	public void setPagina(int pagina) { this.pagina = pagina; }
		
//altres mètodes d'interface
	public String toString() {
		return "id: "+id+", titulo: "+titulo+", autor: "+autor+", año nacimiento: "+anyoNacimiento
				+ ", año publicación: "+anyoPublicacion+", editorial: "+"número páginas: "+pagina;
	}
	
//mètodes d'implementació
}
