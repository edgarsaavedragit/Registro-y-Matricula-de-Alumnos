// Declaración del paquete
package arreglos;

// Importaciones necesarias para trabajar con archivos y listas
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

// Importación de la clase personalizada
import clases.Retiro;

// Declaración de la clase
public class ArregloRetiro {

    // Atributo privado: lista dinámica que almacena objetos de tipo Retiro
    private ArrayList<Retiro> retiros;

    // Constructor: inicializa la lista y carga los retiros desde el archivo
    public ArregloRetiro() {
        retiros = new ArrayList<>();  // Crea la lista vacía
        cargarRetiros();              // Carga datos desde el archivo "retiros.txt"
    }

    // Método público: agrega un retiro a la lista y lo guarda en el archivo (sin retorno, con parámetro)
    public void adicionar(Retiro r) {
        retiros.add(r);     // Agrega el retiro a la lista
        grabarRetiros();    // Guarda la lista actualizada en el archivo
    }

    // Método público: devuelve el tamaño de la lista (con retorno int, sin parámetros)
    public int tamano() {
        return retiros.size();  // Devuelve la cantidad de retiros almacenados
    }

    // Método público: obtiene un retiro por índice (con retorno Retiro, con parámetro int)
    public Retiro obtener(int i) {
        return retiros.get(i);  // Retorna el retiro en la posición i
    }

    // Método público: busca un retiro por su código (con retorno Retiro, con parámetro int)
    public Retiro buscar(int codigo) {
        Retiro r;
        for (int i = 0; i < tamano(); i++) {
            r = obtener(i);  // Obtiene el retiro en la posición i
            if (r.getCodigoRetiro() == codigo)
                return r;    // Retorna el retiro si el código coincide
        }
        return null;         // Retorna null si no se encuentra
    }

    // Método público: elimina un retiro de la lista y actualiza el archivo (sin retorno, con parámetro Retiro)
    public void eliminar(Retiro r) {
        retiros.remove(r);    // Elimina el retiro
        grabarRetiros();      // Actualiza el archivo
    }

    // Método público: graba toda la lista de retiros nuevamente en el archivo (sin retorno, sin parámetros)
    public void actualizaRetiros() {
        grabarRetiros();  // Llama al método privado para sobrescribir el archivo
    }

    // Método privado: guarda todos los retiros actuales en el archivo de texto (sin retorno, sin parámetros)
    private void grabarRetiros() {
        try {
            PrintWriter pw;
            String linea;
            Retiro p;
            pw = new PrintWriter(new FileWriter("retiros.txt")); // Crea archivo (sobrescribe)
            for (int i = 0; i < tamano(); i++) {
                p = obtener(i); // Obtiene el retiro
                // Construye la línea con todos los atributos separados por ";"
                linea = p.getCodigoRetiro() + ";" +
                        p.getCodigoMatricula() + ";" +
                        p.getCodigoAlumno() + ";" +
                        p.getNombreAlumno() + ";" +
                        p.getDniAlumno() + ";" +
                        p.getCurso() + ";" +
                        p.getFecha() + ";" +
                        p.getHora();
                pw.println(linea); // Escribe la línea en el archivo
            }
            pw.close(); // Cierra el archivo
        } catch (Exception e) {
            // Si ocurre un error al escribir el archivo, se ignora silenciosamente
        }
    }

    // Método privado: carga los datos del archivo de texto a la lista (sin retorno, sin parámetros)
    private void cargarRetiros() {
        try {
            BufferedReader br;
            String linea;
            String[] s;
            int codigoRetiro, codigoMatricula, codigoAlumno;
            String nombresAlumno, dniAlumno, curso, fecha, hora;

            br = new BufferedReader(new FileReader("retiros.txt")); // Abre archivo de texto
            while ((linea = br.readLine()) != null) {
                s = linea.split(";"); // Divide la línea por ";"
                // Asigna y convierte los valores desde el archivo
                codigoRetiro = Integer.parseInt(s[0].trim());
                codigoMatricula = Integer.parseInt(s[1].trim());
                codigoAlumno = Integer.parseInt(s[2].trim());
                nombresAlumno = s[3].trim();
                dniAlumno = s[4].trim();
                curso = s[5].trim();
                fecha = s[6].trim();
                hora = s[7].trim();

                // Crea un objeto Retiro con esos datos y lo agrega
                adicionar(new Retiro(codigoRetiro, codigoMatricula, codigoAlumno, nombresAlumno, dniAlumno, curso, fecha, hora));
            }
            br.close(); // Cierra el archivo
        } catch (Exception e) {
            // Si hay error (por ejemplo, el archivo no existe), se ignora
        }
    }

    // Método público: genera un código correlativo automático (con retorno int, sin parámetros)
    public int codigoCorrelativo() {
        if (tamano() == 0) {
            return 200001;  // Primer código si no hay retiros aún
        } else {
            return obtener(tamano() - 1).getCodigoRetiro() + 1; // Incrementa el último
        }
    }
}
