package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import controlador.Main;

public class MongoDB {
	
	String conexion_MongoDB;
	
	public MongoDB(String conexion) {
		this.conexion_MongoDB = conexion;
	}
	
	public void transferenciaDatos() {
		// Todo este parrafo es para la conexion a mongoDB
		String MONGO_DB = "2amt";
		String DB_COLL = "Alumnos";
		Logger mLog = Logger.getLogger(this.conexion_MongoDB);
		mLog.setLevel(java.util.logging.Level.SEVERE);
		MongoClient cliente = new MongoClient();
		MongoDatabase db = cliente.getDatabase(MONGO_DB);
		MongoCollection<Document> coleccion = db.getCollection(DB_COLL);

		List<Document> listaDocsAlum = new ArrayList<Document>();
		List<Document> listaDocsAsig;
		ArrayList<Asignatura> listaAsignaturas;

		for (Alumno alumno : Main.listaAlumnos) {
			listaAsignaturas = alumno.getAsignaturas();
			listaDocsAsig = new ArrayList<Document>();

			for (Asignatura asignatura : listaAsignaturas) {
				listaDocsAsig.add(new Document("CDN", asignatura.getCdn()).append("nombre", asignatura.getNombre())
						.append("nota", asignatura.getNota()));
			}
			
			listaDocsAlum.add(new Document("IDN", alumno.getIdn()).append("apenom", alumno.getApenom())
					.append("direccion", alumno.getDirecc()).append("provincia", alumno.getProv())
					.append("email", alumno.getEmail()).append("asignaturas", listaDocsAsig));

		}
		coleccion.insertMany(listaDocsAlum);
	}
}
