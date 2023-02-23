package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controlador.Main;

public class MariaDB {
	
	String _rutaMariaDb;
	
	public MariaDB(String ruta){
		this._rutaMariaDb = ruta;
	}
	
	public void leerMariaDB() {
		Alumno a;

		String idn;
		String apenom;
		String dir;
		String prov;
		String email;

		try {
			//Crea la conexión con MariaDB
			Connection conn = DriverManager.getConnection(this._rutaMariaDb);
			Statement sent = conn.createStatement();
			String ssql = "SELECT * FROM alumnos"; // Hacemos un select de todas las columnas de la tabla alumnos
			ResultSet rs = sent.executeQuery(ssql);
			while (rs.next()) {
				idn = rs.getString(1);
				apenom = rs.getString(2);
				dir = rs.getString(3);
				prov = rs.getString(4);
				email = rs.getString(5);

				a = new Alumno(idn, apenom, dir, prov, email); // Instanciamos un alumno con todos sus datos
				Main.hashAlumnos.put(idn, a); // Guardamos los alumnos en un HashMap
				
				
				
			}
			rs.close();

		} catch (SQLException exc) {
			exc.printStackTrace();
		}

		Asignatura asig;
		int cdn;
		String nombre;

		try {
			//Crea la conexión con MariaDB
			Connection conn = DriverManager.getConnection(this._rutaMariaDb);
			Statement sent = conn.createStatement();
			String ssql = "SELECT * FROM asignaturas"; // Hacemos un select de todas las columnas de la tabla alumnos
			ResultSet rs = sent.executeQuery(ssql);
			while (rs.next()) {
				cdn = rs.getInt(1);
				nombre = rs.getString(2);

				asig = new Asignatura(cdn, nombre); // Instanciamos las asignaturas con sus datos
				Main.hashAsignaturas.put(cdn, asig); // Guardamos las asignaturas en un HashMap independiente al de alumnos
			}

			rs.close();

		} catch (SQLException exc) {
			exc.printStackTrace();
		}
		
		//Recorremos el HashMap de alumnos
		for (Alumno alumno : Main.hashAlumnos.values()) {
			try {
				//Crea la conexión con MariaDB
				Connection conn = DriverManager.getConnection(this._rutaMariaDb);
				Statement sent = conn.createStatement();
				String ssql = "SELECT nota,cdn FROM notas WHERE idn='" + alumno.getIdn() + "'"; //Hacemos una select de nota y cdn de la tabla notas
																								//donde la clave principal del alumno sea igual que la de notas
				ResultSet rs = sent.executeQuery(ssql);
				while (rs.next()) {
					int notaAsignatura = rs.getInt(1); //Guarda en una variable las notas de la asignatura
					int cdnAsignatura = rs.getInt(2); //Guarda en una variable el cdn de las notas de la asignatura

					asig = Main.hashAsignaturas.get(cdnAsignatura); 
					alumno.aniadeAsignatura(new Asignatura(cdnAsignatura, asig.getNombre(), notaAsignatura));//Aniado al HashMap de alumnos sus asignaturas con la nota 

				}
				rs.close();
			} catch (SQLException exc) {
				exc.printStackTrace();
			}
			
			Main.listaAlumnos.add(alumno); //Agrego a mi listaAlumnos el alumno completo con todos los datos
		}
	}
}
