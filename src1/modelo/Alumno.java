package modelo;


import java.util.ArrayList;

public class Alumno {
	
	String idn;
	String apenom;
	String direcc;
	String prov;
	String email;
	ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();
	
	public Alumno() {	
	}
	
	public Alumno(String idn, String apenom, String dir, String prov, String email) {
		this.idn = idn;
		this.apenom = apenom;
		this.direcc = dir;
		this.prov = prov;
		this.email = email;
	}
	
	public void aniadeAsignatura(Asignatura asignatura) {
		this.asignaturas.add(asignatura);
	}

	public Asignatura getAsignatura(int cdn) {
		for (Asignatura asignatura : asignaturas) {
			if(asignatura.cdn == cdn) {
				return asignatura;
			}
		}
		return null;
	}

	public String getIdn() {
		return idn;
	}

	public void setIdn(String idn) {
		this.idn = idn;
	}

	public String getApenom() {
		return apenom;
	}

	public void setApenom(String apenom) {
		this.apenom = apenom;
	}

	public String getDirecc() {
		return direcc;
	}

	public void setDirecc(String direcc) {
		this.direcc = direcc;
	}

	public String getProv() {
		return prov;
	}

	public void setProv(String prov) {
		this.prov = prov;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<Asignatura> getAsignaturas() {
		return asignaturas;
	}

	public void setAsignaturas(ArrayList<Asignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}

	@Override
	public String toString() {
		String cadDatosAlumno = "Alumno idn=" + idn + ", apenom=" + apenom + ", direcc=" + direcc + ", prov=" + prov + ", email=" + email + "\n";
		String cadAsignaturas = "";
		
		for (Asignatura asignatura : asignaturas) {
			cadAsignaturas = cadAsignaturas + "	" + asignatura.toString() + "\n";
		}
		return cadDatosAlumno + cadAsignaturas;
	}

	
}
