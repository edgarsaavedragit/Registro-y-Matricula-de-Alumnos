package clases;

public class Alumnos {

	//atributos
	private int codAlumno; //Autogenerado y correlativo a partir de 1001
	private String nombres;
	private String apellidos;
	private int edad;
	private int estado;
	private int celular;
	private String dni;
	
	//construtor

	public Alumnos()
	{
		
	}
	
	public Alumnos(int codAlumno, String nombres, String apellidos, String dni, int edad,int celular, int estado) {
		super();
		this.codAlumno = codAlumno;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.edad = edad;
		this.estado = estado;
		this.celular = celular;
		this.dni = dni;
	}

	//Metodos getter and setter
	public int getCodAlumno() {
		return codAlumno;
	}


	public void setCodAlumno(int codAlumno) {
		this.codAlumno = codAlumno;
	}


	public String getNombres() {
		return nombres;
	}


	public void setNombres(String nombres) {
		this.nombres = nombres;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public int getEdad() {
		return edad;
	}


	public void setEdad(int edad) {
		this.edad = edad;
	}


	public int getEstado() {
		return estado;
	}


	public void setEstado(int estado) {
		this.estado = estado;
	}


	public int getCelular() {
		return celular;
	}


	public void setCelular(int celular) {
		this.celular = celular;
	}


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


}
	
	

