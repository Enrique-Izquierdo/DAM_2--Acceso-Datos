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
	//atributs
	//constructors
	//mètodes de interface

	public static void main(String[] args) {
		//Carregam configuració i cream objecte sessionFactory
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml"); //Creem nova configuració per a la conexió amb la bd relacional.
		configuration.addClass(Libro.class); //Afegim la classe que hem definit en l'arxiu de mapeig (classe que volem fer persistent).
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build(); //Construïm (preparem) la configuració per a poder treballar amb ella.
		//SessionFactory sessionFactory = configuration.buildSessionFactory(registry); //Construïm objecte sessionFactory, passant-li la configuració.
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		
		//Obrim nova sessió del objecte sessionFactory per a poder iniciar la transacció i fer persistents les operacions CRUD.
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		//Operacions CRUD sobre la bd relacional
		Principal principal = new Principal();
		principal.menu(session);
		
		//Fem presistent la transacció (registrar en la bd relacional), i tanquem session per que no es puga desfer el registre de la transacció.
		session.getTransaction().commit();
		session.close();
	}

	//mètodes d'implementació
	
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
	
	
	private void mostrarTitolsRegistrats(Session pSession) {		
		//Recuperar llista d'objectes (registres)
		List llistaLlibres = new ArrayList();
		llistaLlibres = pSession.createQuery("FROM Libro").list();
		System.out.println();
		for (Object llibre : llistaLlibres) {
			System.out.println("Id: "+((Libro)llibre).getId()+", titol: "+((Libro)llibre).getTitulo());
		}
	}
	
	private void mostrarRegistre(Session pSession, Scanner pEntradaTeclat) {
		System.out.print("\nIntroduïsca l'id del registre que vulga recuperar i mostrar per pantalla: ");
		int id = Integer.parseInt(pEntradaTeclat.nextLine());
		//Recuperar un objecte (registre en la bd) a partir del seu id
		try {
			Libro libro = (Libro) pSession.get(Libro.class, id);
			System.out.println(libro.toString());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("\nNo existeix cap registre amb l'id indicada.");
		}
	}
	
	private void crearRegistre(Session pSession, Scanner pEntradaTeclat) {
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
	}
	
	private void actualitzarRegistre(Session pSession, Scanner pEntradaTeclat) {
		System.out.print("\nIntroduïsca l'id del registre que vulga actualitzar: ");
		int id = Integer.parseInt(pEntradaTeclat.nextLine());
		System.out.print("Introduïsca el titol de la cançó: ");
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
		// Actualitzar la informació d'un objecte (registre en la bd) donat el seu id
		Libro libro = (Libro) pSession.load(Libro.class, id);
		libro.setTitulo(titulo);
		libro.setAutor(autor);
		libro.setAnyoNacimiento(anyoNacimiento);
		libro.setAnyoPublicacion(anyoPublicacion);
		libro.setEditorial(editorial);
		libro.setPaginas(paginas);
		pSession.update(libro);
	}
	
	private void esborrarRegistre(Session pSession, Scanner pEntradaTeclat) {
		System.out.print("\nIntroduïsca l'id del registre que vulga borrar: ");
		int id = Integer.parseInt(pEntradaTeclat.nextLine());
		//Borrar objecte (registre en la bd)
		Libro libro = new Libro();
		libro.setId(id);
		pSession.delete(libro);
	}
}
