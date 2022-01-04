package ad.NoSQL;

import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class Modelo {
	//ATRIBUTS
	private static MongoCollection<Document> coleccio;
	private String menu = "\n\t     Accions disponibles en la base de dades\n"
			+ "  ===================================================================\n\n"
			+ "  1 - Mostrar tots el IDs i els titolas de la biblioteca.\n"
			+ "  2 - Mostrar l'informació detallada d'un llibre a partir del seu ID.\n"
			+ "  3 - Afegir un nou llibre a la biblioteca.\n"
			+ "  4 - Modificar atributs d'un llibre a partir del seu ID.\n"
			+ "  5 - Esborrar un llibre a partir del seu ID.";
	
	//CONSTRUCTORS
	/**Mètode: Modelo
	 * Descripció: instancia la clase Modelo i invoca al mètode private (encapsulament) connectarBD.
	 * Paràmetres d'entrada: no 
	 * Paràmetres d'eixida: no */	
	public Modelo() {
		connectarBD();
	}
	
	//GETTERS I SETTERS
	public String getMenu() {
		return menu;
	}
	
	//ALTRES MÈTODES DE INTERFACE	
	/**Mètode: executarOpcio
	 * Descripció: invoca el mètode privat (encapsulament) corresponent a l'opció passada per paràmetre, i 
	 * 				retorna el String retornat per el mètode invocat.
	 * Paràmetres d'entrada: dades (List<String>)
	 * Paràmetres d'eixida:	String */	
	public String executarOpcio(List<String> dades) {		
		String opcio = dades.get(0);
		String id = "";		
		String eixida = "";
		
		switch (opcio) {
		case "1":
			eixida = mostrarCampsIdTitol_RegistresTots(coleccio);			
			break;
		case "2":
			id = dades.get(1);			
		    eixida = mostrarCampsTots_RegistreIdCoincident(coleccio, id);
			break;
		case "3":
			crearRegistre(coleccio, dades);
			break;
		case "4":
			actualitzarRegistreIdCoincident(coleccio, dades);
			break;
		case "5":
			id = dades.get(1);
			esborrarRegistreIdCoincident(coleccio, id);
			break;
		default:
			eixida = "No existeix l'acció seleccionada";
			break;
		}
		System.out.println(eixida);
		return eixida;
	}
	
	
	//MÈTODES D' IMPLEMENTACIÓ	
	/**Mètode: main
	 * Descripció: connectar app amb la basa de dades i invocar el mètode menú.
	 * Paràmetres d'entrada: no utilitzats
	 * Paràmetres d'eixida:	 no */	
	private static void connectarBD() {
		// TODO Auto-generated method stub		
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("Biblioteca");
		coleccio = database.getCollection("Llibres");

//		mongoClient.close();
	}
		
	
	/**Mètode: mostrarCampsIndicats_RegistresTots
	 * Descripció: retorna un String amb els camps ID, Tìtol e id alfaNumeric de tots els registres.
	 * Paràmetres d'entrada: pColeccio (MongoCollection<Document>)
	 * Paràmetres d'eixida:	String */
	private static String mostrarCampsIdTitol_RegistresTots(MongoCollection<Document> pColeccio) {
		String eixida = String.format("%-5s %-32s %-32s\n", "ID", "Tìtol", "id alfaNumeric");	
		
		MongoCursor<Document> cursor = pColeccio.find().iterator();
		while (cursor.hasNext()) {
			JSONObject obj = new JSONObject(cursor.next().toJson());
			String id = "", titol = "";
			Object idAlfaNumeric = "";
			
			try {	id = obj.getString("Id");	} catch (JSONException e) {	}
			try {	titol = obj.getString("Titol");	} catch (JSONException e) {	}
			try {	idAlfaNumeric = obj.get("_id");	} catch (JSONException e) {	}
			
			eixida = eixida + String.format("%-5s %-32s %-32s\n", id, titol, idAlfaNumeric);			
		}
		return eixida;
	}

	
	/**Mètode: mostrarCampsTots_RegistreIdCoincident
	 * Descripció: retorna un String amb tots els camps del registre corresponent al ID passat per paràmetre.
	 * Paràmetres d'entrada: pColeccio (MongoCollection<Document>), valorId (String)
	 * Paràmetres d'eixida:	String */
	private static String mostrarCampsTots_RegistreIdCoincident(MongoCollection<Document> pColeccio, String valorId) {
		String eixida = String.format("\n%-5s %-32s %-20s %-15s %-17s %-13s %-10s %-32s\n",
				" ID","Tìtol","Autor","Any Naixement","Any Publicació","Editorial","Pàgines", "id alfaNumeric");
		
		Bson query = Filters.eq("Id", valorId);
		MongoCursor<Document> cursor = pColeccio.find(query).iterator();

		while (cursor.hasNext()) {
			JSONObject obj = new JSONObject(cursor.next().toJson());
			String id="", titol="", autor ="", anyNaixement="", anyPublicacio="", editorial="", nombrePagines="";
			Object idAlfaNumeric = "";
			
			try {	id = obj.getString("Id");	} catch (JSONException e) {	}
			try {	titol = obj.getString("Titol");	} catch (JSONException e) {	}
			try {	autor = obj.getString("Autor");	} catch (JSONException e) {	}
			try {	anyNaixement = obj.getString("Any_naixement");	} catch (JSONException e) {	}
			try {	anyPublicacio = obj.getString("Any_publicacio");	} catch (JSONException e) {	}
			try {	editorial = obj.getString("Editorial");	} catch (JSONException e) {	}
			try {	nombrePagines = obj.getString("Nombre_pagines");	} catch (JSONException e) {	}
			try {	idAlfaNumeric = obj.get("_id");	} catch (JSONException e) {	}
			
			eixida = eixida + String.format("%-5s %-32s %-20s %-15s %-17s %-13s %-10s %-32s\n",
					" "+id, titol, autor, anyNaixement, anyPublicacio, editorial, nombrePagines, idAlfaNumeric);
		}
		return eixida;
	}
		
	
	/**Mètode: crearRegistre
	 * Descripció: crea un nou registre amb les dades introduïts passats per paràmetre, assignant-li un ID autoincrementatiu.
	 * Paràmetres d'entrada: pColeccio (MongoCollection<Document>), pDades List<String> 
	 * Paràmetres d'eixida:	 no */
	private static void crearRegistre(MongoCollection<Document> pColeccio, List<String> pDades) {		
		String id = String.valueOf(cercarMajorId(pColeccio)+1);				
		Document doc = new Document();
		doc.append("Id", id);
		doc.append("Titol", pDades.get(2));
		doc.append("Autor", pDades.get(3));
		doc.append("Any_naixement", pDades.get(4));
		doc.append("Any_publicacio", pDades.get(5));
		doc.append("Editorial", pDades.get(6));
		doc.append("Nombre_pagines", pDades.get(7));
		pColeccio.insertOne(doc);		
	}
	
	
	/**Mètode: cercarMajorId
	 * Descripció: retorna el major ID.
	 * Paràmetres d'entrada: pColeccio (MongoCollection<Document>)
	 * Paràmetres d'eixida:	 int */
	private static int cercarMajorId(MongoCollection<Document> pColeccio) {
		int mayorId = 0;
		MongoCursor<Document> cursor = pColeccio.find().iterator();
		while (cursor.hasNext()) {
			JSONObject obj = new JSONObject(cursor.next().toJson());
			int id = Integer.parseInt(obj.getString("Id"));
			if(id > mayorId) {
				mayorId = id;
			}
		}		
		return mayorId;
	}
		
	
	/**Mètode: actualitzarRegistreIdCoincident
	 * Descripció: actualitza el registre amb el ID indicat per teclat, amb les dades introduïts amb el teclat.
	 * Paràmetres d'entrada: pColeccio (MongoCollection<Document>), pEntradaTeclat (Scanner)
	 * Paràmetres d'eixida:	 no */
	private static void actualitzarRegistreIdCoincident(MongoCollection<Document> pColeccio, List<String> pDades) {		
		pColeccio.updateOne(Filters.eq("Id", pDades.get(1)), new Document("$set", new Document("Titol", pDades.get(2))));
		pColeccio.updateOne(Filters.eq("Id", pDades.get(1)), new Document("$set", new Document("Autor", pDades.get(3))));
		pColeccio.updateOne(Filters.eq("Id", pDades.get(1)), new Document("$set", new Document("Any_naixement", pDades.get(4))));
		pColeccio.updateOne(Filters.eq("Id", pDades.get(1)), new Document("$set", new Document("Any_publicacio", pDades.get(5))));
		pColeccio.updateOne(Filters.eq("Id", pDades.get(1)), new Document("$set", new Document("Editorial", pDades.get(6))));
		pColeccio.updateOne(Filters.eq("Id", pDades.get(1)), new Document("$set", new Document("Nombre_pagines", pDades.get(7))));
	}
	
	
	/**Mètode: esborrarRegistreIdCoincident
	 * Descripció: esborrar el registre amb el ID indicat per teclat, de la base de dades.
	 * Paràmetres d'entrada: pColeccio (MongoCollection<Document>), pEntradaTeclat (Scanner)
	 * Paràmetres d'eixida:	 no */
	private static void esborrarRegistreIdCoincident(MongoCollection<Document> pColeccio, String valorId) {
		System.out.print("Introduïsca l'ID del llibre a esborrar: ");
		pColeccio.deleteOne(Filters.eq("Id", valorId));
	}
	
}
