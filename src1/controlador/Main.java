package controlador;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.io.File;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import org.bson.Document;

import modelo.Alumno;
import modelo.Asignatura;
import modelo.Json;
import modelo.MariaDB;
import modelo.MongoDB;
import vista.Menu;

public class Main {

	public static ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>();
	public static HashMap<String, Alumno> hashAlumnos = new HashMap<String, Alumno>();
	public static HashMap<Integer, Asignatura> hashAsignaturas = new HashMap<Integer, Asignatura>();

	public static void main(String[] args) throws IOException {
		
		//Con esta ruta nos conecta a nuestra base de datos de MariaDB en local, tenemos que tener las tablas insertadas
		MariaDB mariadb = new MariaDB("jdbc:mariadb://localhost:3305/db2amt?user=root&password=root");
		MongoDB mongodb = new MongoDB("org.mongodb.driver");
		Json json = new Json("./alumnos.json");
		
		Menu menu = new Menu();
		
		String elecc = "";
		boolean continua = true;
		do {
			elecc = menu.muestraMenu();
			if(elecc != null && !elecc.isEmpty() && elecc.length() != 0) {
				if(elecc.charAt(0) >=49 && elecc.charAt(0) <=53 && elecc.length() == 1) {
					
					switch (Integer.parseInt(elecc)) {
					case 1:
						// SacarInformacionDeMariaDB();
						mariadb.leerMariaDB();
						System.out.println("Los datos se han leido correctamente\n");
						break;
					case 2:
						// mostrar alumnos;
						menu.muestraAlumnos();
						break;
					case 3:
						// Genera JSON;
						json.generaJson();
						break;
					case 4:
						// Migrar la base;
						mongodb.transferenciaDatos();
					case 5:
						continua = false;
						break;
					}	
				}
				else {
					System.out.println("Introduzca una opcion correcta");
				}
			}
			else {
				System.out.println("Introduzca una opcion");
			}
			
		} while (continua);
		//elecc.charAt(0) >= 1 && elecc.charAt(0) <= 4
		System.out.println("Programa terminado......");
	}
}
