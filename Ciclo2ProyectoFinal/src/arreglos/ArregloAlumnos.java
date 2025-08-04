// Declaración del paquete al que pertenece esta clase
package arreglos;

// Importa todas las clases del paquete java.io para trabajar con archivos
import java.io.*;

// Importa la clase ArrayList para crear listas dinámicas
import java.util.ArrayList;

// Importa la clase Alumnos desde el paquete clases
import clases.Alumnos;

// Importa la clase Matriculas desde el paquete clases (aunque no se usa en este archivo)
import clases.Matriculas;

// Declaración de la clase pública ArregloAlumnos
public class ArregloAlumnos {

	// Declaración de atributo privado: lista dinámica de objetos Alumnos
	private ArrayList<Alumnos> alumnos;

	// Constructor de la clase (no tiene retorno, sin parámetros)
	public ArregloAlumnos() {
		alumnos = new ArrayList<Alumnos>(); // Inicializa la lista de alumnos vacía
		cargarClientes(); // Llama al método que carga alumnos desde el archivo
	}

	// Método público para añadir un alumno (sin retorno, con parámetro)
	public void adicionar(Alumnos x) {
		alumnos.add(x); // Agrega el alumno a la lista
		grabarAlumnos(); // Guarda los alumnos en el archivo
	}

	// Método público para obtener el tamaño de la lista (con retorno int, sin parámetros)
	public int tamano() {
		return alumnos.size(); // Devuelve el número de alumnos en la lista
	}

	// Método público para obtener un alumno por índice (con retorno Alumnos, con parámetro)
	public Alumnos obtener(int i) {
		return alumnos.get(i); // Devuelve el alumno en la posición i
	}

	// Método público para buscar un alumno por código (con retorno Alumnos, con parámetro)
	public Alumnos buscar(int codigo) {
		Alumnos x; // Variable auxiliar
		for (int i = 0; i < tamano(); i++) { // Recorre la lista de alumnos
			x = obtener(i); // Obtiene el alumno en la posición i
			if (x.getCodAlumno() == codigo) // Compara el código del alumno
				return x; // Si lo encuentra, lo retorna
		}
		return null; // Si no lo encuentra, retorna null
	}

	// Método público para buscar un alumno por DNI (con retorno Alumnos, con parámetro)
	public Alumnos buscar(String dni) {
		Alumnos x; // Variable auxiliar
		for (int i = 0; i < tamano(); i++) { // Recorre la lista de alumnos
			x = obtener(i); // Obtiene el alumno en la posición i
			if (x.getDni().equals(dni)) // Compara el DNI
				return x; // Si lo encuentra, lo retorna
		}
		return null; // Si no lo encuentra, retorna null
	}

	// Método público para validar si el alumno está activo (estado != 2) (con retorno boolean, con parámetro)
	public boolean estadoCorrectoAlumnoMatricula(int codigo) {
		Alumnos x; // Variable auxiliar
		for (int i = 0; i < tamano(); i++) { // Recorre la lista de alumnos
			x = obtener(i); // Obtiene el alumno
			if (x.getCodAlumno() == codigo) { // Compara el código
				if (x.getEstado() != 2) // Verifica si el estado es diferente de 2
					return true; // Es válido para matrícula
			}
		}
		return false; // No es válido
	}

	// Método público para validar si el alumno está retirado (estado == 2) (con retorno boolean, con parámetro)
	public boolean estadoCorrectoAlumnoRetiro(int codigo) {
		Alumnos x; // Variable auxiliar
		for (int i = 0; i < tamano(); i++) { // Recorre la lista
			x = obtener(i); // Obtiene el alumno
			if (x.getCodAlumno() == codigo) { // Compara el código
				if (x.getEstado() == 2) // Verifica si el estado es 2 (retirado)
					return true; // Es válido para retiro
			}
		}
		return false; // No es válido
	}

	// Método público para eliminar un alumno de la lista (sin retorno, con parámetro)
	public void eliminar(Alumnos x) {
		alumnos.remove(x); // Elimina el alumno de la lista
		grabarAlumnos(); // Guarda la lista actualizada
	}

	// Método público que reescribe el archivo con la información actual (sin retorno, sin parámetros)
	public void actualizarAlumno() {
		grabarAlumnos(); // Llama al método de guardado
	}

	// Método público (vacío por ahora) para validar un DNI (sin retorno, sin parámetros)
	public void validarDNI() {
		// Método aún no implementado
	}

	// Método privado que guarda la lista de alumnos en el archivo (sin retorno, sin parámetros)
	private void grabarAlumnos() {
		try {
			PrintWriter pw; // Declaración del escritor de archivo
			String linea; // Variable para formar la línea a escribir
			Alumnos x; // Variable temporal para recorrer la lista
			pw = new PrintWriter(new FileWriter("alumnos.txt")); // Abre el archivo en modo escritura
			for (int i = 0; i < tamano(); i++) { // Recorre todos los alumnos
				x = obtener(i); // Obtiene el alumno en la posición i
				// Construye la línea con los datos separados por ";"
				linea = x.getCodAlumno() + ";" +
						x.getNombres() + ";" +
						x.getApellidos() + ";" +
						x.getDni() + ";" +
						x.getEdad() + ";" +
						x.getCelular() + ";" +
						x.getEstado();
				pw.println(linea); // Escribe la línea en el archivo
			}
			pw.close(); // Cierra el archivo
		} catch (Exception e) {
			// Manejo de errores (actualmente vacío)
		}
	}

	// Método privado que carga los alumnos desde un archivo a la lista (sin retorno, sin parámetros)
	private void cargarClientes() {
		try {
			BufferedReader br; // Lector de archivos
			String linea; // Variable para cada línea del archivo
			String[] s; // Arreglo para dividir los datos
			Alumnos alumno = new Alumnos(); // Objeto temporal para almacenar datos
			int codigoCliente; // Variable no usada
			br = new BufferedReader(new FileReader("alumnos.txt")); // Abre archivo para lectura
			while ((linea = br.readLine()) != null) { // Lee línea por línea
				s = linea.split(";"); // Separa los datos por ";"
				alumno.setCodAlumno(Integer.parseInt(s[0].trim())); // Asigna código
				alumno.setNombres(s[1].trim()); // Asigna nombres
				alumno.setApellidos(s[2].trim()); // Asigna apellidos
				alumno.setDni(s[3].trim()); // Asigna DNI
				alumno.setEdad(Integer.parseInt(s[4].trim())); // Asigna edad
				alumno.setCelular(Integer.parseInt(s[5].trim())); // Asigna celular
				alumno.setEstado(Integer.parseInt(s[6].trim())); // Asigna estado
				// Añade un nuevo alumno con esos datos a la lista
				adicionar(new Alumnos(
					alumno.getCodAlumno(),
					alumno.getNombres(),
					alumno.getApellidos(),
					alumno.getDni(),
					alumno.getEdad(),
					alumno.getCelular(),
					alumno.getEstado()));
			}
			br.close(); // Cierra el archivo
		} catch (Exception e) {
			// Manejo de errores (actualmente vacío)
		}
	}

	// Método público que devuelve un código correlativo para el siguiente alumno (con retorno int, sin parámetros)
	public int codigoCorrelativo() {
		if (tamano() == 0) { // Si no hay alumnos registrados
			return 202010001; // Devuelve el primer código
		} else {
			return obtener(tamano() - 1).getCodAlumno() + 1; // Devuelve el código siguiente al último
		}
	}
}
