package vista;

import javax.swing.JOptionPane;

import controlador.Main;
import modelo.Alumno;

public class Menu {
	
	public String muestraMenu() {
		String aux = JOptionPane.showInputDialog("1.Leer datos de MAriaDB\n" + "2.Mostrar Alumnos\n"
				+ "3.Generar JSON\n" + "4.Migrar de base de datos\n" + "5.Salir");
		return aux;
	}
	
	public void muestraAlumnos() {
		int cont = 0;
		for (Alumno alumno : Main.listaAlumnos) {
			System.out.println(alumno.toString());
			cont++;
		}
		System.out.println("Se han mostrado con exito " + cont + " alumnos");
	}
}
