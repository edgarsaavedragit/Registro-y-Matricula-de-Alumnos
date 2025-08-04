// Declaración del paquete en el que se encuentra la clase
package arreglos;

// Importación de clases necesarias para manejo de archivos
import java.io.BufferedReader; // Para leer archivos línea por línea
import java.io.FileReader;     // Para abrir archivos en modo lectura
import java.io.FileWriter;     // Para abrir archivos en modo escritura
import java.io.PrintWriter;    // Para escribir en archivos

// Importación de clase ArrayList para almacenar objetos dinámicamente
import java.util.ArrayList;

// Importación de la clase Matriculas del paquete clases
import clases.Matriculas;

// Declaración de clase pública ArregloMatriculas
public class ArregloMatriculas {

    // Atributo privado: lista que almacena objetos de tipo Matriculas
    private ArrayList<Matriculas> matriculas;

    // Constructor (sin retorno, sin parámetros)
    public ArregloMatriculas() {
        matriculas = new ArrayList<>();  // Inicializa la lista
        cargarMatriculas();              // Llama al método que carga desde archivo
    }

    // Método público para añadir matrícula (sin retorno, con parámetros)
    public void adicionar(Matriculas p) {
        matriculas.add(p);     // Agrega el objeto Matriculas a la lista
        grabarMatriculas();    // Guarda la lista actualizada en el archivo
    }

    // Método público para obtener el tamaño de la lista (con retorno int, sin parámetros)
    public int tamano() {
        return matriculas.size();  // Retorna el número de elementos en la lista
    }

    // Método público para obtener una matrícula por índice (con retorno Matriculas, con parámetros)
    public Matriculas obtener(int i) {
        return matriculas.get(i);  // Retorna el objeto en la posición i
    }

    // Método público para buscar una matrícula por su código (con retorno Matriculas, con parámetros)
    public Matriculas buscar(int codigo) {
        Matriculas p;                          // Variable temporal
        for (int i = 0; i < tamano(); i++) {   // Recorre toda la lista
            p = obtener(i);                    // Obtiene la matrícula en posición i
            if (p.getCodigoMatricula() == codigo) // Compara código
                return p;                      // Retorna la matrícula encontrada
        }
        return null;   // Retorna null si no se encuentra
    }

    // Método público para verificar si existe matrícula con un código de alumno (con retorno boolean, con parámetros)
    public boolean existeAlumno(int codigo) {
        Matriculas p;                          // Variable temporal
        for (int i = 0; i < tamano(); i++) {   // Recorre la lista
            p = obtener(i);                    // Obtiene matrícula
            if (p.getCodigoAlumno() == codigo) // Compara código de alumno
                return true;                   // Existe alumno
        }
        return false;   // No existe alumno
    }

    // Método público para verificar si existe matrícula con un código de curso (con retorno boolean, con parámetros)
    public boolean existeCurso(int codigo) {
        Matriculas p;                          // Variable temporal
        for (int i = 0; i < tamano(); i++) {   // Recorre la lista
            p = obtener(i);                    // Obtiene matrícula
            if (p.getCodigoCurso() == codigo)  // Compara código de curso
                return true;                   // Existe curso
        }
        return false;   // No existe curso
    }

    // Método público para eliminar una matrícula (sin retorno, con parámetros)
    public void eliminar(Matriculas p) {
        matriculas.remove(p);   // Elimina la matrícula de la lista
        grabarMatriculas();     // Guarda cambios en el archivo
    }

    // Método público para sobrescribir archivo con cambios (sin retorno, sin parámetros)
    public void actualizarMatricula() {
        grabarMatriculas();     // Guarda toda la lista en el archivo
    }

    // Método privado para guardar la lista de matrículas en archivo (sin retorno, sin parámetros)
    private void grabarMatriculas() {
        try {
            PrintWriter pw;              // Objeto para escribir en archivo
            String linea;                // Línea de texto a escribir
            Matriculas p;                // Variable para recorrer lista
            pw = new PrintWriter(new FileWriter("matriculas.txt"));  // Abre archivo

            for (int i = 0; i < tamano(); i++) {    // Recorre toda la lista
                p = obtener(i);                     // Obtiene matrícula
                // Crea línea de texto separada por ";"
                linea = p.getCodigoMatricula() + ";" +
                        p.getCodigoAlumno() + ";" +
                        p.getNombreAlumno() + ";" +
                        p.getCodigoCurso() + ";" +
                        p.getCurso() + ";" +
                        p.getFecha() + ";" +
                        p.getHora();

                pw.println(linea);     // Escribe la línea en el archivo
            }

            pw.close();  // Cierra archivo

        } catch (Exception e) {
            // Captura errores pero no los maneja actualmente
        }
    }

    // Método privado para cargar matrículas desde archivo (sin retorno, sin parámetros)
    private void cargarMatriculas() {
        try {
            BufferedReader br;     // Objeto para leer archivo
            String linea;          // Línea leída
            String[] s;            // Partes de la línea
            int codigoMatricula, codigoAlumno, codigoCurso;
            String nombreAlumno, curso, fecha, hora;

            br = new BufferedReader(new FileReader("matriculas.txt")); // Abre archivo

            while ((linea = br.readLine()) != null) {   // Lee línea por línea
                s = linea.split(";");                   // Divide por ";"
                // Convierte y asigna valores
                codigoMatricula = Integer.parseInt(s[0].trim());
                codigoAlumno = Integer.parseInt(s[1].trim());
                nombreAlumno = s[2].trim();
                codigoCurso = Integer.parseInt(s[3].trim());
                curso = s[4].trim();
                fecha = s[5].trim();
                hora = s[6].trim();

                // Crea objeto y lo agrega a la lista
                adicionar(new Matriculas(codigoMatricula, codigoAlumno, nombreAlumno, codigoCurso, curso, fecha, hora));
            }

            br.close(); // Cierra archivo

        } catch (Exception e) {
            // Captura errores pero no los maneja actualmente
        }
    }

    // Método público para generar código correlativo (con retorno int, sin parámetros)
    public int codigoCorrelativo() {
        if (tamano() == 0) {
            return 100001;   // Si lista vacía, empieza desde este valor
        } else {
            return obtener(tamano() - 1).getCodigoMatricula() + 1;  // Retorna el siguiente código
        }
    }

    // Método público para obtener toda la lista de matrículas (con retorno ArrayList<Matriculas>, sin parámetros)
    public ArrayList<Matriculas> obtenerLista() {
        return matriculas;   // Devuelve toda la lista de objetos
    }
}
