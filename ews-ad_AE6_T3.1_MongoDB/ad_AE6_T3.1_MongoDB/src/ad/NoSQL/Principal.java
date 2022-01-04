package ad.NoSQL;

public class Principal {
	//ATRIBUTS
	//CONSTRUCTORS
	//GETTERS I SETTERS
	//ALTRES MÈTODES DE INTERFACE
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vista vista = new Vista();
		Modelo modelo = new Modelo();
		Controlador controlador = new Controlador(modelo, vista);
	}
	
	//MÈTODES D' IMPLEMENTACIÓ	

}
