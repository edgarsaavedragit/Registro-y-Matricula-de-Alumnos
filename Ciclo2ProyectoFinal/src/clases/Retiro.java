package clases;

public class Retiro {

	//atributos privados
	private int codigoRetiro;
	private int codigoMatricula;
	private int codigoAlumno;
	private String dniAlumno;
	private String nombreAlumno;
	private String curso;
	private String fecha;
	private String hora;
	
	public Retiro(int codigoRetiro, int codigoMatricula, int codigoAlumno, String nombreAlumno,String dniAlumno,
			String curso, String fecha, String hora) {
		super();
		this.codigoRetiro = codigoRetiro;
		this.codigoMatricula = codigoMatricula;
		this.codigoAlumno = codigoAlumno;
		this.dniAlumno = dniAlumno;
		this.nombreAlumno = nombreAlumno;
		this.curso = curso;
		this.fecha = fecha;
		this.hora = hora;
	}

	public int getCodigoRetiro() {
		return codigoRetiro;
	}

	public void setCodigoRetiro(int codigoRetiro) {
		this.codigoRetiro = codigoRetiro;
	}

	public int getCodigoMatricula() {
		return codigoMatricula;
	}

	public void setCodigoMatricula(int codigoMatricula) {
		this.codigoMatricula = codigoMatricula;
	}

	public int getCodigoAlumno() {
		return codigoAlumno;
	}

	public void setCodigoAlumno(int codigoAlumno) {
		this.codigoAlumno = codigoAlumno;
	}

	public String getDniAlumno() {
		return dniAlumno;
	}

	public void setDniAlumno(String dniAlumno) {
		this.dniAlumno = dniAlumno;
	}

	public String getNombreAlumno() {
		return nombreAlumno;
	}

	public void setNombreAlumno(String nombreAlumno) {
		this.nombreAlumno = nombreAlumno;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
	
	// Método toString para representación textual del objeto
   
    public String toString() {
        return "Retiro[" +
               "Código: " + codigoRetiro +
               ", Matrícula: " + codigoMatricula +
               ", Alumno: " + nombreAlumno + " (" + dniAlumno + ")" +
               ", Curso: " + curso +
               ", Fecha: " + fecha + " " + hora +
               "]";
    }
    
    // Método para generar línea para archivo
    public String toFileString() {
        return codigoRetiro + ";" +
               codigoMatricula + ";" +
               codigoAlumno + ";" +
               nombreAlumno + ";" +
               dniAlumno + ";" +
               curso + ";" +
               fecha + ";" +
               hora;
    }
    
    // Método para verificar si el retiro es válido
    public boolean isValid() {
        return codigoRetiro > 0 && 
               codigoMatricula > 0 && 
               codigoAlumno > 0 &&
               dniAlumno != null && !dniAlumno.trim().isEmpty() &&
               nombreAlumno != null && !nombreAlumno.trim().isEmpty() &&
               curso != null && !curso.trim().isEmpty() &&
               fecha != null && !fecha.trim().isEmpty() &&
               hora != null && !hora.trim().isEmpty();
    }
}
	

	
	

