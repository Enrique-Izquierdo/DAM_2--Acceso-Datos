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
//			case "02":
//				llegirTotsCampsTotsRegistres(pColeccio);
//				break;
//			case "03":
//				llegirTotsCampsRegistresCoincidents(pColeccio, entradaTeclat);
//				break;
//			case "04":
//				mostrarCampIndicatTotsRegistres(pColeccio, entradaTeclat);
//				break;
//			case "05":
//				mostrarCampIndicatRegistresCoincidents(pColeccio, entradaTeclat);
//				break;
//			case "06":
//				actualitzarPrimerRegistreCoincident(pColeccio, entradaTeclat);
//				break;
//			case "07":
//				eliminarPrimerRegistreCoincident(pColeccio, entradaTeclat);
//				break;
			case "0":
				continuar = false;
				break;
			default:
				System.out.println("No existeix l'acció seleccionada");
				break;
			}
			
			if (continuar) {
				System.out.println("Pressione qualsevol tecla per a continuar.");
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
		System.out.println(" ID  "+" Titol "+" _id_alfanumèric");
		while (cursor.hasNext()) {
			JSONObject obj = new JSONObject(cursor.next().toJson());
			System.out.println(obj.getString("Id")+" "+obj.getString("Titol")+" "+obj.get("_id"));
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
		System.out.println(" ID  "+" Titol "+" Autor "+" Any_naixement "+" Any_publicacio "+" Editorial "
				+" Nombre_pagines "+" _id_alfanumèric ");
		while (cursor.hasNext()) {
			JSONObject obj = new JSONObject(cursor.next().toJson());
			String id="", titol="", autor ="", anyNaixement="", anyPublicacio="", 
					editorial="", nombrePagines="", idAlfaNumeric = "";
			
			try {	id = obj.getString("Id");	} catch (JSONException e) {	}
			try {	titol = obj.getString("Titol");	} catch (JSONException e) {	}
			try {	autor = obj.getString("Autor");	} catch (JSONException e) {	}
			try {	anyNaixement = obj.getString("Any_naixement");	} catch (JSONException e) {	}
			try {	anyPublicacio = obj.getString("Any_publicacio");	} catch (JSONException e) {	}
			try {	editorial = obj.getString("Editorial");	} catch (JSONException e) {	}
			try {	nombrePagines = obj.getString("Nombre_pagines");	} catch (JSONException e) {	}
			try {	idAlfaNumeric = obj.getString("_id");	} catch (JSONException e) {	}
			
			System.out.println(id+" "+titol+" "+autor+" "+anyNaixement+" "+anyPublicacio+" "
					+editorial+" "+nombrePagines+" "+idAlfaNumeric);
			
//			System.out.println(obj.getString("Id")+" "+obj.getString("Titol")+" "+obj.getString("Autor")+" "
//					+obj.getString("Any_naixement")+" "+obj.getString("Any_publicacio")+" "+obj.getString("Editorial")+" "
//					+" "+obj.getString("Nombre_pagines")+" "+obj.getString("_id"));
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
	

	
//	//ejercicis	
//	private static void llegirTotsCampsTotsRegistres(MongoCollection<Document> pColeccio) {
//		MongoCursor<Document> cursor = pColeccio.find().iterator();
//		while (cursor.hasNext()) {
//			System.out.println(cursor.next().toJson());
//		}
//	}
//			
//	private static void llegirTotsCampsRegistresCoincidents(MongoCollection<Document> pColeccio, Scanner pEntradaTeclat) {
//		System.out.print("Introduïsca el nom y el valor del camp que identifica els registre a mostrar.\n"
//				+ "\tNom del camp: ");
//		String campo1 = pEntradaTeclat.nextLine();
//		System.out.print("\tValor del camp: ");
//		String valor1 = pEntradaTeclat.nextLine();
//		Bson query = Filters.eq(campo1, valor1);
//		MongoCursor<Document> cursor = pColeccio.find(query).iterator();
//		while (cursor.hasNext()) {
//			System.out.println(cursor.next().toJson());
//		}
//	}
//	
//	private static void mostrarCampIndicatTotsRegistres(MongoCollection<Document> pColeccio, Scanner pEntradaTeclat) {
//		System.out.print("Introduïsca el camp a mostrar de tots els registres: ");
//		String campo1 = pEntradaTeclat.nextLine();
//		MongoCursor<Document> cursor = pColeccio.find().iterator();
//		while (cursor.hasNext()) {
//			JSONObject obj = new JSONObject(cursor.next().toJson());
//			System.out.println(obj.getString(campo1));
//		}
//	}
//	
//	private static void mostrarCampIndicatRegistresCoincidents(MongoCollection<Document> pColeccio, Scanner pEntradaTeclat) {
//		System.out.print("Introduïsca el nom y el valor del camp que identifica els registre a mostrar.\n"
//				+ "\tNom del camp: ");
//		String campo1 = pEntradaTeclat.nextLine();
//		System.out.print("\tValor del camp: ");
//		String valor1 = pEntradaTeclat.nextLine();
//		System.out.print("Introduïsca el camp a mostrar de tots els registres: ");
//		String campo2 = pEntradaTeclat.nextLine();
//		Bson query = Filters.eq(campo1, valor1);
//		MongoCursor<Document> cursor = pColeccio.find(query).iterator();
//		while (cursor.hasNext()) {
//			JSONObject obj = new JSONObject(cursor.next().toJson());
//			System.out.println(obj.getString(campo2));
//		}
//	}
//	
//	private static void actualitzarPrimerRegistreCoincident(MongoCollection<Document> pColeccio, Scanner pEntradaTeclat) {
//		System.out.print("Introduïsca el nom y el valor del camp que identifica el primer registre a actualitzar de la taula.\n"
//				+ "\tNom del camp: ");
//		String campo1 = pEntradaTeclat.nextLine();
//		System.out.print("\tValor del camp: ");
//		String valor1 = pEntradaTeclat.nextLine();
//		System.out.print("Introduïsca el nou valor del camp \""+ campo1+"\" a actualitzar en el primer registre coincident de la taula\n"
//				+ "\tNou valor del camp \""+campo1+"\": ");
//		String valor2 = pEntradaTeclat.nextLine();
//		
//		pColeccio.updateOne(Filters.eq(campo1, valor1), new Document("$set", new Document(campo1, valor2)));
//	}
//	
//	private static void eliminarPrimerRegistreCoincident(MongoCollection<Document> pColeccio, Scanner pEntradaTeclat) {
//		System.out.print("Introduïsca el nom y el valor del camp que identifica el primer registre a esborrar de la taula.\n"
//				+ "\tNom del camp: ");
//		String campo1 = pEntradaTeclat.nextLine();
//		System.out.print("\tValor del camp: ");
//		String valor1 = pEntradaTeclat.nextLine();
//		pColeccio.deleteOne(Filters.eq(campo1, valor1));
//	}
	
}
