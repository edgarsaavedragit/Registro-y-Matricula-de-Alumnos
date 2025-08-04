// Declaración del paquete donde se encuentra esta clase
package arreglos;

// Importación de clases necesarias para entrada/salida de archivos
import java.io.BufferedReader;   // Para leer archivos
import java.io.FileReader;       // Para abrir archivos para lectura
import java.io.FileWriter;       // Para abrir archivos para escritura
import java.io.PrintWriter;      // Para escribir en archivos

// Importación de clase ArrayList para manejar listas dinámicas
import java.util.ArrayList;

// Importación de la clase Cursos desde el paquete clases
import clases.Cursos;

// Declaración de clase pública ArregloCursos
public class ArregloCursos {

    // Atributo privado: lista dinámica que almacena objetos de tipo Cursos
    private ArrayList<Cursos> curso;

    // Constructor de la clase (sin retorno, sin parámetros)
    public ArregloCursos() {
        curso = new ArrayList<>();    // Inicializa la lista de cursos
        cargarCursos();              // Llama al método para cargar los cursos desde el archivo
    }

    // Método público para agregar un curso a la lista (sin retorno, con parámetros)
    public void adicionar(Cursos p) {
        curso.add(p);      // Agrega el objeto curso a la lista
        grabarCursos();    // Guarda la lista actualizada en el archivo
    }

    // Método público para obtener la cantidad de cursos (con retorno int, sin parámetros)
    public int tamano() {
        return curso.size();   // Devuelve la cantidad de cursos en la lista
    }

    // Método público para obtener un curso por su índice (con retorno Cursos, con parámetros)
    public Cursos obtener(int i) {
        return curso.get(i);   // Retorna el curso en la posición i
    }

    // Método público para buscar un curso por código (con retorno Cursos, con parámetros)
    public Cursos buscar(int codigo) {
        Cursos p;                               // Variable auxiliar para recorrer la lista
        for (int i = 0; i < tamano(); i++) {    // Bucle para recorrer todos los cursos
            p = obtener(i);                     // Obtiene el curso en la posición i
            if (p.getCodigoCurso() == codigo)   // Compara si el código del curso es igual al buscado
                return p;                       // Retorna el curso encontrado
        }
        return null;    // Si no encuentra el curso, retorna null
    }

    // Método público para eliminar un curso (sin retorno, con parámetros)
    public void eliminar(Cursos p) {
        curso.remove(p);     // Elimina el curso de la lista
        grabarCursos();      // Guarda la lista actualizada en el archivo
    }

    // Método público para sobrescribir el archivo con los datos actuales (sin retorno, sin parámetros)
    public void actualizarCursos() {
        grabarCursos();      // Llama al método privado para guardar cursos en archivo
    }

    // Método privado para guardar todos los cursos en el archivo cursos.txt (sin retorno, sin parámetros)
    private void grabarCursos() {
        try {
            PrintWriter pw;       // Objeto para escribir en archivo
            String linea;         // Variable que almacenará cada línea del archivo
            Cursos p;             // Objeto temporal para recorrer la lista

            pw = new PrintWriter(new FileWriter("cursos.txt")); // Abre archivo en modo escritura

            for (int i = 0; i < tamano(); i++) {     // Recorre todos los cursos en la lista
                p = obtener(i);                      // Obtiene el curso actual

                // Crea una línea con todos los atributos del curso separados por punto y coma
                linea = p.getCodigoCurso() + ";" +
                        p.getAsignatura() + ";" +
                        p.getCiclo() + ";" +
                        p.getCreditos() + ";" +
                        p.getHoras();

                pw.println(linea);     // Escribe la línea en el archivo
            }

            pw.close();    // Cierra el archivo

        } catch (Exception e) {
            // Manejo de excepción si ocurre error al guardar (actualmente vacío)
        }
    }

    // Método privado para cargar los cursos desde el archivo cursos.txt (sin retorno, sin parámetros)
    private void cargarCursos() {
        try {
            BufferedReader br;    // Objeto para leer archivos
            String linea;         // Variable que almacena cada línea leída
            String[] s;           // Arreglo que almacena los datos separados
            int codigoCurso, ciclo, creditos, horas;   // Variables para datos numéricos
            String asignatura;                         // Variable para texto

            br = new BufferedReader(new FileReader("cursos.txt"));   // Abre el archivo en modo lectura

            while ((linea = br.readLine()) != null) {    // Lee línea por línea
                s = linea.split(";");                    // Separa los valores usando ";"

                // Convierte los datos de texto a sus tipos adecuados
                codigoCurso = Integer.parseInt(s[0].trim());
                asignatura = s[1].trim();
                ciclo = Integer.parseInt(s[2].trim());
                creditos = Integer.parseInt(s[3].trim());
                horas = Integer.parseInt(s[4].trim());

                // Crea un nuevo objeto Cursos y lo agrega a la lista
                adicionar(new Cursos(codigoCurso, asignatura, ciclo, creditos, horas));
            }

            br.close();    // Cierra el archivo

        } catch (Exception e) {
            // Manejo de excepción si ocurre error al cargar (actualmente vacío)
        }
    }

    // Método público para generar un código correlativo para el próximo curso (con retorno int, sin parámetros)
    public int codigoCorrelativo() {
        if (tamano() == 0) {
            return 2001;   // Si no hay cursos, comienza desde 2001
        } else {
            return obtener(tamano() - 1).getCodigoCurso() + 1; // Retorna el siguiente código basado en el último curso
        }
    }
}
