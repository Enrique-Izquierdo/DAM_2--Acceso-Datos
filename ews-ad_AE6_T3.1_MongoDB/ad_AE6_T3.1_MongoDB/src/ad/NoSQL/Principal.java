package ad.NoSQL;

import java.io.IOException;
import java.util.Scanner;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class Principal {
	//atributs
	//constructors
	//getters y setters
	//altres mètodes de interface
	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("Biblioteca");
		MongoCollection<Document> coleccio = database.getCollection("Llibres");
		mostrarMenu(coleccio);
		mongoClient.close();
	}

	
	public static void mostrarMenu(MongoCollection<Document> pColeccio) {
		Scanner entradaTeclat = new Scanner(System.in);		
		Boolean continuar = true;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		do {			
			System.out.print("\nMenú d'accions disponibles en la base de dades\n"
					+ "1 - Mostrar tots el IDs i els titolas de la biblioteca.\n"
					+ "2 - Mostrar l'informació detallada d'un llibre a partir del seu ID.\n"
					+ "3 - Afegir un nou llibre a la biblioteca.\n"
					+ "4 - Modificar atributs d'un llibre a partir del seu ID.\n"
					+ "5 - Esborrar un llibre a partir del seu ID.\n"
//					+ "02 - LLegir (i mostrar) tots els camps dels registres.\n"
//					+ "03 - LLegir (i mostrar) tots els camps dels registres coincidents amb el camp y valor indicat.\n"
//					+ "04 - Mostrar el camp indicat de tots els registres.\n"
//					+ "05 - Mostrar el camp indicat de tots els registres coincidents amb el camp y valor indicat\n"
//					+ "06 - Actualitzar el camp del primer registre coincident amb el valor indicat.\n"
//					+ "07 - Eliminar el primer registre coincident amb el valor del camp indicat\n"
					+ "0 - Eixir\n"
					+ "Seleccione l'acció a realitzar: ");
			
			switch (entradaTeclat.nextLine()) {
			case "1":
				mostrarCampsIndicats_RegistresTots(pColeccio);
				break;
			case "2":
				mostrarCampsTots_RegistreIdCoincident(pColeccio, entradaTeclat);
				break;
			case "3":
				crearRegistre(pColeccio, entradaTeclat);
				break;
			case "4":
				actualitzarRegistreIdCoincident(pColeccio, entradaTeclat);
				break;
			case "5":
				esborrarRegistreIdCoincident(pColeccio, entradaTeclat);
				break;
			case "0":
				continuar = false;
				break;
			default:
				System.out.println("No existeix l'acció seleccionada");
				break;
			}
			
			if (continuar) {
				System.out.println("\nPressione qualsevol tecla per a continuar.");
					try {
						System.in.read();
						entradaTeclat.nextLine();
					} catch (IOException e) {
						// TODO: handle exception
						System.out.println("S'ha produit un error durant l'entrada o eixida de dades.");
					}
			}
						
		} while(continuar);
		
		entradaTeclat.close();
	}
	
	//mètodes d'implementació
	private static void mostrarCampsIndicats_RegistresTots(MongoCollection<Document> pColeccio) {
		MongoCursor<Document> cursor = pColeccio.find().iterator();
		System.out.printf("\n%-5s %-32s %-32s\n", " ID", "Tìtol", "id alfaNumeric");
		while (cursor.hasNext()) {
			JSONObject obj = new JSONObject(cursor.next().toJson());
			String id = "", titol = "";
			Object idAlfaNumeric = "";
			
			try {	id = obj.getString("Id");	} catch (JSONException e) {	}
			try {	titol = obj.getString("Titol");	} catch (JSONException e) {	}
			try {	idAlfaNumeric = obj.get("_id");	} catch (JSONException e) {	}
			
			System.out.printf("%-5s %-32s %-32s\n", " "+id, titol, idAlfaNumeric);			
//			System.out.println(obj.getString("Id")+" "+obj.getString("Titol")+" "+obj.get("_id"));
		}
	}
	
	private static void mostrarCampsTots_RegistreIdCoincident(MongoCollection<Document> pColeccio, Scanner pEntradaTeclat) {
		System.out.print("Introduïsca el valor de l'ID: ");
		String valorId = pEntradaTeclat.nextLine();
		Bson query = Filters.eq("Id", valorId);
		MongoCursor<Document> cursor = pColeccio.find(query).iterator();
//		while (cursor.hasNext()) {
//			System.out.println(cursor.next().toJson());
//		}		
		System.out.printf("\n%-5s %-32s %-20s %-15s %-17s %-13s %-10s %-32s\n",
				" ID","Tìtol","Autor","Any Naixement","Any Publicació","Editorial","Pàgines", "id alfaNumeric");
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
			
			System.out.printf("%-5s %-32s %-20s %-15s %-17s %-13s %-10s %-32s\n",
					" "+id, titol, autor, anyNaixement, anyPublicacio, editorial, nombrePagines, idAlfaNumeric);
			
//			System.out.println(obj.getString("Id")+" "+obj.getString("Titol")+" "+obj.getString("Autor")+" "
//					+obj.getString("Any_naixement")+" "+obj.getString("Any_publicacio")+" "+obj.getString("Editorial")+" "
//					+" "+obj.getString("Nombre_pagines")+" "+obj.get("_id"));
		}
	}
	
	private static void crearRegistre(MongoCollection<Document> pColeccio, Scanner pEntradaTeclat) {
		System.out.print("Introduïsca les dades del llibre.\n"
				+ "Titol: ");
		String titol = pEntradaTeclat.nextLine();
		System.out.print("Autor: ");
		String autor = pEntradaTeclat.nextLine();
		System.out.print("Any_naixement: ");
		String any_naixement = pEntradaTeclat.nextLine();
		System.out.print("Any_publicacio: ");
		String any_publicacio = pEntradaTeclat.nextLine();
		System.out.print("Editorial: ");
		String editorial = pEntradaTeclat.nextLine();
		System.out.print("Nombre_pagines: ");
		String nombre_pagines = pEntradaTeclat.nextLine();
		
//		System.out.println("El mayor id es: "+cercarMajorId(pColeccio));
		String id = String.valueOf(cercarMajorId(pColeccio)+1);		
		
		Document doc = new Document();
		doc.append("Id", id);
		doc.append("Titol", titol);
		doc.append("Autor", autor);
		doc.append("Any_naixement", any_naixement);
		doc.append("Any_publicacio", any_publicacio);
		doc.append("Editorial", editorial);
		doc.append("Nombre_pagines", nombre_pagines);
		pColeccio.insertOne(doc);		
	}
		
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
		
	private static void actualitzarRegistreIdCoincident(MongoCollection<Document> pColeccio, Scanner pEntradaTeclat) {
		System.out.print("Introduïsca l'ID del llibre a actualitzar: ");
		String valorId = pEntradaTeclat.nextLine();
		System.out.print("Titol: ");
		String titol = pEntradaTeclat.nextLine();
		System.out.print("Autor: ");
		String autor = pEntradaTeclat.nextLine();
		System.out.print("Any_naixement: ");
		String any_naixement = pEntradaTeclat.nextLine();
		System.out.print("Any_publicacio: ");
		String any_publicacio = pEntradaTeclat.nextLine();
		System.out.print("Editorial: ");
		String editorial = pEntradaTeclat.nextLine();
		System.out.print("Nombre_pagines: ");
		String nombre_pagines = pEntradaTeclat.nextLine();
		
		pColeccio.updateOne(Filters.eq("Id", valorId), new Document("$set", new Document("Titol", titol)));
		pColeccio.updateOne(Filters.eq("Id", valorId), new Document("$set", new Document("Autor", autor)));
		pColeccio.updateOne(Filters.eq("Id", valorId), new Document("$set", new Document("Any_naixement", any_naixement)));
		pColeccio.updateOne(Filters.eq("Id", valorId), new Document("$set", new Document("Any_publicacio", any_publicacio)));
		pColeccio.updateOne(Filters.eq("Id", valorId), new Document("$set", new Document("Editorial", editorial)));
		pColeccio.updateOne(Filters.eq("Id", valorId), new Document("$set", new Document("Nombre_pagines", nombre_pagines)));
	}
	
	private static void esborrarRegistreIdCoincident(MongoCollection<Document> pColeccio, Scanner pEntradaTeclat) {
		System.out.print("Introduïsca l'ID del llibre a esborrar: ");
		String valorId = pEntradaTeclat.nextLine();
		pColeccio.deleteOne(Filters.eq("Id", valorId));
	}
	
}
