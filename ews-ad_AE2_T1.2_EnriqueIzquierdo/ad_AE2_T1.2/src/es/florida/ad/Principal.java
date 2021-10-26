package es.florida.ad;

public class Principal {
	
	/**Mètode: main
	 * Descripció: declara i inicialitza tres objectes de les classes Modelo, Vista y Controlador
	 * Paràmetres d'entrada: un array de Strings passats per arguments..
	 * Paràmetres d'eixida: no	*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Modelo modelo = new Modelo();
		Vista vista = new Vista();		
		Controlador controlador = new Controlador(modelo, vista);
	}

}
