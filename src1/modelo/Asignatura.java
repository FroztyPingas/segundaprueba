package modelo;

public class Asignatura {
	
	int cdn;
	String nombre;
	int nota;
	
	public Asignatura(int cdn, String nomb) {
		this.cdn = cdn;
		this.nombre = nomb;
	}
	
	public Asignatura(int cdn, String nomb,int nota) {
		this.cdn = cdn;
		this.nombre = nomb;
		this.nota = nota;
	}
	
	public void setNota(int nota) {
		this.nota = nota;
	}

	@Override
	public String toString() {
		return "Asignatura [cdn=" + cdn + ", nombre=" + nombre + ", nota=" + nota + "]";
	}

	public int getCdn() {
		return cdn;
	}

	public void setCdn(int cdn) {
		this.cdn = cdn;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNota() {
		return nota;
	}

	
	
}
