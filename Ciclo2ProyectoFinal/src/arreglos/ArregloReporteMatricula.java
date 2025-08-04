// Declaración del paquete donde se encuentra la clase
package arreglos;

// Importación de clases necesarias
import java.util.ArrayList;   // Para listas dinámicas
import java.util.HashMap;     // Para pares clave-valor

// Importación de clases personalizadas
import clases.Alumnos;
import clases.Cursos;
import clases.Matriculas;

// Declaración de clase pública
public class ArregloReporteMatricula {

    // Atributos privados: instancias de otros arreglos usados para generar reportes
    private ArregloAlumnos arregloAlumnos = new ArregloAlumnos();         // Arreglo de alumnos
    private ArregloMatriculas arregloMatriculas = new ArregloMatriculas(); // Arreglo de matrículas
    private ArregloCursos arregloCursos = new ArregloCursos();            // Arreglo de cursos

    // Método público que retorna una lista de alumnos con matrícula pendiente (con retorno ArrayList<Alumnos>, sin parámetros)
    public ArrayList<Alumnos> obtenerAlumnosConMatriculaPendiente() {
        ArrayList<Alumnos> lista = new ArrayList<>();  // Lista donde se almacenarán los alumnos con matrícula pendiente

        // Recorre todos los alumnos
        for (int i = 0; i < arregloAlumnos.tamano(); i++) {
            Alumnos alumno = arregloAlumnos.obtener(i);  // Obtiene un alumno por índice
            boolean tieneMatriculaVigente = false;       // Bandera para verificar si el alumno tiene matrícula activa

            // Recorre todas las matrículas
            for (int j = 0; j < arregloMatriculas.tamano(); j++) {
                Matriculas matricula = arregloMatriculas.obtener(j); // Obtiene una matrícula

                // Si el código de alumno coincide y la matrícula **no** está pendiente
                if (matricula.getCodigoAlumno() == alumno.getCodAlumno()
                    && !matricula.isPendiente()) {
                    tieneMatriculaVigente = true;  // El alumno tiene matrícula activa
                    break;                         // No es necesario seguir buscando
                }
            }

            // Si el alumno no tiene matrícula activa, se añade a la lista de pendientes
            if (!tieneMatriculaVigente) {
                lista.add(alumno);
            }
        }

        return lista;  // Retorna la lista de alumnos con matrícula pendiente
    }

    // Método público que retorna una lista de alumnos con matrícula vigente (con retorno ArrayList<Alumnos>, sin parámetros)
    public ArrayList<Alumnos> obtenerAlumnosConMatriculaVigente() {
        ArrayList<Alumnos> lista = new ArrayList<>();  // Lista de alumnos con matrícula vigente

        // Recorre todos los alumnos
        for (int i = 0; i < arregloAlumnos.tamano(); i++) {
            Alumnos alumno = arregloAlumnos.obtener(i); // Obtiene alumno

            // Recorre todas las matrículas
            for (int j = 0; j < arregloMatriculas.tamano(); j++) {
                Matriculas matricula = arregloMatriculas.obtener(j); // Obtiene matrícula

                // Si el código de alumno coincide y la matrícula no está pendiente
                if (matricula.getCodigoAlumno() == alumno.getCodAlumno()
                        && !matricula.isPendiente()) {
                    lista.add(alumno); // Añade a la lista de vigentes
                    break; // Evita duplicados saliendo del ciclo
                }
            }
        }

        return lista; // Retorna la lista de alumnos con matrícula vigente
    }

    // Método público que retorna un mapa con asignatura -> lista de nombres de alumnos matriculados (con retorno HashMap<String, ArrayList<String>>, sin parámetros)
    public HashMap<String, ArrayList<String>> obtenerAlumnosPorCurso() {
        HashMap<String, ArrayList<String>> mapa = new HashMap<>(); // Mapa de curso -> alumnos

        // Recorre todos los cursos
        for (int i = 0; i < arregloCursos.tamano(); i++) {
            Cursos curso = arregloCursos.obtener(i); // Obtiene curso
            ArrayList<String> alumnos = new ArrayList<>(); // Lista temporal de nombres de alumnos

            // Recorre todas las matrículas
            for (int j = 0; j < arregloMatriculas.tamano(); j++) {
                Matriculas matricula = arregloMatriculas.obtener(j); // Obtiene matrícula

                // Si el código del curso coincide y la matrícula no está pendiente
                if (matricula.getCodigoCurso() == curso.getCodigoCurso()
                        && !matricula.isPendiente()) {

                    // Busca el alumno en base al código en la matrícula
                    Alumnos alumno = arregloAlumnos.buscar(matricula.getCodigoAlumno());

                    // Si el alumno existe, añade su nombre completo a la lista
                    if (alumno != null) {
                        alumnos.add(alumno.getNombres() + " " + alumno.getApellidos());
                    }
                }
            }

            // Si hay alumnos en ese curso, se añade al mapa
            if (!alumnos.isEmpty()) {
                mapa.put(curso.getAsignatura(), alumnos); // clave: nombre del curso
            }
        }

        return mapa; // Retorna el mapa curso -> lista de alumnos
    }
}
