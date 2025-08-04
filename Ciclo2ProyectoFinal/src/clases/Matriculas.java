package clases;

public class Matriculas {
	
	private int codigoMatricula;
	private int codigoAlumno;
	private int codigoCurso;
	private String nombreAlumno;
	private String curso;
	private String fecha;
	private String hora;
	private String estado;
	
	public Matriculas(int codigoMatricula, int codigoAlumno, String nombreAlumno, int codigoCurso, String curso,
			String fecha, String hora) {
		super();
		this.codigoMatricula = codigoMatricula;
		this.codigoAlumno = codigoAlumno;
		this.codigoCurso = codigoCurso;
		this.nombreAlumno = nombreAlumno;
		this.curso = curso;
		this.fecha = fecha;
		this.hora = hora;
		this.estado = estado;
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

	public int getCodigoCurso() {
		return codigoCurso;
	}

	public void setCodigoCurso(int codigoCurso) {
		this.codigoCurso = codigoCurso;
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
	
	//-----------
	public String getEstado() {
		return estado;
	}
		
	public boolean isPendiente() {
		return estado != null && estado.equalsIgnoreCase("pendiente");
	}
	
	
	

}
