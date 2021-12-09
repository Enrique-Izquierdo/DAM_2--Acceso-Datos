package ad.AE.T2ae1;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class Principal {
	//ATRIBUTS
	//CONSTRUCTORS
	
	//MÈTODES DE INTERFACE
	/**Mètode: main
	 * Descripció: Carrega la configuració d'hibernate; crea els objectes sessiónFactory y sessión;
	 * 				invoca al mètode menu; realizta el commit y tanca la sessió.
	 * Paràmetres d'entrada: no utilitzats
	 * Paràmetres d'eixida:	 no */
	public static void main(String[] args) {
		//Carregam configuració i cream objecte sessionFactory
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml"); //Creem nova configuració per a la conexió amb la bd relacional.
		configuration.addClass(Libro.class); //Afegim la classe que hem definit en l'arxiu de mapeig (classe que volem fer persistent).
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build(); //Construïm (preparem) la configuració per a poder treballar amb ella.
		//SessionFactory sessionFactory = configuration.buildSessionFactory(registry); //Construïm objecte sessionFactory, passant-li la configuració.
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		
		//Obrim nova sessió del objecte sessionFactory per a poder iniciar la transacció i fer persistents les operacions CRUD.
		Session session = sessionFactory.openSession();
//		session.beginTransaction();
		
		//Operacions CRUD sobre la bd relacional
		Principal principal = new Principal();
		principal.menu(session);
		
//		//Fem presistent la transacció (registrar en la bd relacional), i tanquem session per que no es puga desfer el registre de la transacció.
//		try {
//			session.getTransaction().commit();
//		} catch (Exception e) {
//			// TODO: handle exception
//			System.out.println("\nNo existeix cap registre amb l'id indicada.");
//		}
		session.close();
	}
	
	//MÈTODES D'IMPLEMENTACIÓ
	/**Mètode: menu
	 * Descripció: mostra el menú de l'aplicació i invoca al mètode de l'opció triada, fins es
	 * 				selecciona l'opció eixir.
	 * Paràmetres d'entrada: pSession (Session, objecte sessió (hibernate) creat en el mètode main)
	 * Paràmetres d'eixida:	no */
	private void menu(Session pSession) {		
		Scanner entradaTeclat = new Scanner(System.in);		
		Boolean continuar = true;
		while(continuar) {
			System.out.print("\n\n              alumne: Enrique Izquierdo Jiménez   curso:2ºDAM"
					+ "\n\n           Exercici Mapeid Objecte-Relacional (ORM) amb Hibernate"
					+ "\n=========================================================================="
					+ "\nMenú amb les operacions que es poden realitzar en la taula llibres (bd biblioteca):"
					+ "\n    1. Mostrar el titols registrats en la taula llibres."
					+ "\n    2. Mostrar un registre (objecte) de la taula llibres."
					+ "\n    3. Crear un registre (objecte) en la taula llibres."
					+ "\n    4. Actualitzar un registre (objecte) de la taula llibres."
					+ "\n    5. Borrar un registre (objecte) de la taula llibres."
					+ "\n    0. Eixir de l'aplicació."
					+ "\nIntrodueix l'opció a executar: ");
			String opcio = entradaTeclat.nextLine();
			switch (opcio) {
			case "0":
				continuar = false;
				break;
			case "1":
				mostrarTitolsRegistrats(pSession);
				break;	
			case "2":
				mostrarRegistre(pSession, entradaTeclat);
				break;
			case "3":
				crearRegistre(pSession, entradaTeclat);
				break;
			case "4":
				actualitzarRegistre(pSession, entradaTeclat);
				break;
			case "5":
				esborrarRegistre(pSession, entradaTeclat);
				break;	
			default:
				System.out.println("L'opció seleccionada no existeix");
				break;
			}
			
			if(continuar) {			
				System.out.println("\nPressione ENTER per a continuar.");
				try{
					System.in.read();
					entradaTeclat.nextLine();
					}
				catch(IOException e){
					System.out.println("\nS'ha produit un error durant l'entrada o eixida de dades.");
					e.printStackTrace();
				}
			} else {
				System.out.println("\nHa eixit de l'aplicació.");
			}
		}
				
		entradaTeclat.close();
	}
	
	/**Mètode: mostrarTitolsRegistrats
	 * Descripció: mostra per consola els valors dels camps id i titol dels registres de la base de dades.
	 * Paràmetres d'entrada: pSession (Session, objecte sessió (hibernate) creat en el mètode main)
	 * Paràmetres d'eixida:	no  */
	private void mostrarTitolsRegistrats(Session pSession) {		
		//Recuperar llista d'objectes (registres)
		List llistaLlibres = new ArrayList();
		llistaLlibres = pSession.createQuery("FROM Libro").list();
		System.out.println();
		for (Object llibre : llistaLlibres) {
			System.out.println("Id: "+((Libro)llibre).getId()+", titol: "+((Libro)llibre).getTitulo());
		}
	}
	
	/**Mètode: mostrarRegistre
	 * Descripció: mostra per consola tots els camps del registre de la base de dades buscat per l'id.
	 * Paràmetres d'entrada: pSession (Session, objecte sessió (hibernate) creat en el mètode main),
	 * 						 pEntradaTeclat (Scanner, conexió al teclat)
	 * Paràmetres d'eixida:	no  */
	private void mostrarRegistre(Session pSession, Scanner pEntradaTeclat) {
		//Iniciem la transacció de la sessió
		pSession.beginTransaction();
		System.out.print("\nIntroduïsca l'id del registre que vulga recuperar i mostrar per pantalla: ");
		int id = Integer.parseInt(pEntradaTeclat.nextLine());
		try {
			//Recuperar un objecte (registre en la bd) a partir del seu id
			Libro libro = (Libro) pSession.get(Libro.class, id);
			System.out.println(libro.toString());
			//Fem presistent la transacció (registrar en la bd relacional)
			pSession.getTransaction().commit();
			//Eliminen les dades del objecte a la sessió
			pSession.clear(); 
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("\nNo existeix cap registre amb l'id indicada.");
		}
	}
	
	/**Mètode: crearRegistre
	 * Descripció: sol·licita els valors dels atributs de l'objecte que posteriorment registra en la base de dades.
	 * Paràmetres d'entrada: pSession (Session, objecte sessió (hibernate) creat en el mètode main),
	 * 						 pEntradaTeclat (Scanner, conexió al teclat)
	 * Paràmetres d'eixida:	no  */
	private void crearRegistre(Session pSession, Scanner pEntradaTeclat) {
		//Iniciem la transacció de la sessió
		pSession.beginTransaction();
		//Demanem els dades
		System.out.print("\nIntroduïsca el titol del llibre: ");
		String titulo = pEntradaTeclat.nextLine();
		System.out.print("Introduïsca el nom de l'autor: ");
		String autor = pEntradaTeclat.nextLine();
		System.out.print("Introduïsca l'any de naixement: ");
		int anyoNacimiento = Integer.parseInt(pEntradaTeclat.nextLine());
		System.out.print("Introduïsca l'any de publicació: ");
		int anyoPublicacion = Integer.parseInt(pEntradaTeclat.nextLine());
		System.out.print("Introduïsca el nom de l'editorial: ");
		String editorial = pEntradaTeclat.nextLine();
		System.out.print("Introduïsca el nombre de pàgines: ");
		int paginas = Integer.parseInt(pEntradaTeclat.nextLine());
		//Crear nou objecte (registre en la bd)
		Libro libro = new Libro(titulo, autor, anyoNacimiento, anyoPublicacion, editorial, paginas);
		Serializable id = pSession.save(libro);
		System.out.println("\nId del nou registre: "+id);
		//Fem presistent la transacció (registrar en la bd relacional)
		pSession.getTransaction().commit();
		//Eliminen les dades del objecte a la sessió
		pSession.clear(); 
	}
	
	/**Mètode: cercaLliure
	 * Descripció: sol·licita els valors dels atributs de l'objecte que posteriorment actualitza en el 
	 * 				registre de la base de dades corresponent amb l'id indicat.
	 * Paràmetres d'entrada: pSession (Session, objecte sessió (hibernate) creat en el mètode main),
	 * 						 pEntradaTeclat (Scanner, conexió al teclat)
	 * Paràmetres d'eixida:	no  */
	private void actualitzarRegistre(Session pSession, Scanner pEntradaTeclat) {
		//Iniciem la transacció de la sessió
		pSession.beginTransaction();
		//Demanem els dades
		System.out.print("\nIntroduïsca l'id del registre que vulga actualitzar: ");
		int id = Integer.parseInt(pEntradaTeclat.nextLine());
		System.out.print("Introduïsca el titol del llibre: ");
		String titulo = pEntradaTeclat.nextLine();
		System.out.print("Introduïsca el nom de l'autor: ");
		String autor = pEntradaTeclat.nextLine();
		System.out.print("Introduïsca l'any de naixement: ");
		int anyoNacimiento = Integer.parseInt(pEntradaTeclat.nextLine());
		System.out.print("Introduïsca l'any de publicació: ");
		int anyoPublicacion = Integer.parseInt(pEntradaTeclat.nextLine());
		System.out.print("Introduïsca el nom de l'editorial: ");
		String editorial = pEntradaTeclat.nextLine();
		System.out.print("Introduïsca el nombre de pàgines: ");
		int paginas = Integer.parseInt(pEntradaTeclat.nextLine());
		
		try {
			// Actualitzar la informació d'un objecte (registre en la bd) donat el seu id
			Libro libro = (Libro) pSession.load(Libro.class, id);
			libro.setTitulo(titulo);
			libro.setAutor(autor);
			libro.setAnyoNacimiento(anyoNacimiento);
			libro.setAnyoPublicacion(anyoPublicacion);
			libro.setEditorial(editorial);
			libro.setPaginas(paginas);
			pSession.update(libro);
			//Fem presistent la transacció (registrar en la bd relacional)
			pSession.getTransaction().commit();
			//Eliminen les dades del objecte a la sessió
			pSession.clear();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("\nNo existeix cap registre amb l'id indicada.");
		}	
	}
		
	/**Mètode: esborrarRegistre
	 * Descripció: sol·licita l'id del registre que volem esborrar, i l'esborra de la base de dades.
	 * Paràmetres d'entrada: pSession (Session, objecte sessió (hibernate) creat en el mètode main),
	 * 						 pEntradaTeclat (Scanner, conexió al teclat)
	 * Paràmetres d'eixida:	no  */
	private void esborrarRegistre(Session pSession, Scanner pEntradaTeclat) {
		//Iniciem la transacció de la sessió
		pSession.beginTransaction();
		//Demanem els dades
		System.out.print("\nIntroduïsca l'id del registre que vulga borrar: ");
		int id = Integer.parseInt(pEntradaTeclat.nextLine());
		//Borrar objecte (registre en la bd)
		Libro libro = new Libro();
		try {
			libro.setId(id);
			pSession.delete(libro);
			//Fem presistent la transacció (registrar en la bd relacional)
			pSession.getTransaction().commit();
			//Eliminen les dades del objecte a la sessió
			pSession.clear();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("\nNo existeix cap registre amb l'id indicada.");
		}
	}
}
