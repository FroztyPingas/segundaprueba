package modelo;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import controlador.Main;

public class Json {
	
	String ruta;
	
	public Json(String ruta) {
		this.ruta = ruta;
	}
	
	public void generaJson() throws IOException {
		String ruta = this.ruta;
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new File(ruta), Main.listaAlumnos);
	}
}
