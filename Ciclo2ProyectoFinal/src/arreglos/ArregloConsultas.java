// Declaración del paquete al que pertenece esta clase
package arreglos;

// Importación de la clase ArrayList para trabajar con listas dinámicas
import java.util.ArrayList;

// Importación de la clase Consultas desde el paquete clases
import clases.Consultas;

// Declaración de la clase pública ArregloConsultas
public class ArregloConsultas {

    // Atributo privado: lista dinámica de objetos de tipo Consultas
    private ArrayList<Consultas> lista = new ArrayList<>();

    // Constructor de la clase (sin retorno, sin parámetros)
    public ArregloConsultas() {
        // Agrega una consulta predefinida a la lista al instanciar la clase
        lista.add(new Consultas(200001, 100001, "26/06/2025", "11:00:00"));
    }

    // Método público para agregar una consulta a la lista (sin retorno, con parámetros)
    public void agregar(Consultas r) {
        // Agrega el objeto r (de tipo Consultas) a la lista
        lista.add(r);
    }

    // Método público para buscar una consulta por número de retiro (con retorno Consultas, con parámetros)
    public Consultas buscar(int numRetiro) {
        // Bucle for-each para recorrer la lista de consultas
        for (Consultas r : lista)
            // Compara si el número de retiro de la consulta actual es igual al buscado
            if (r.getNumRetiro() == numRetiro)
                // Si lo encuentra, devuelve el objeto Consultas correspondiente
                return r;
        // Si no encuentra la consulta, devuelve null
        return null;
    }

    // Método público que devuelve el tamaño de la lista (con retorno int, sin parámetros)
    public int tamanio() {
        // Devuelve el número de elementos en la lista
        return lista.size();
    }

    // Método público que devuelve una consulta según su posición en la lista (con retorno Consultas, con parámetros)
    public Consultas obtener(int i) {
        // Devuelve el objeto Consultas que está en la posición i de la lista
        return lista.get(i);
    }
}
