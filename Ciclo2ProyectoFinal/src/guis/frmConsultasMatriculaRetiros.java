// Paquete que contiene esta clase
package guis;

// Importación de librerías necesarias para GUI, eventos y clases personalizadas
import java.awt.*;
import javax.swing.*;
import arreglos.*;
import clases.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// Clase principal que extiende JFrame para representar una ventana - clase con constructor y métodos sin retorno
public class frmConsultasMatriculaRetiros extends JFrame {

    // Declaración de campos de texto y área de texto
    private JTextField txtNumMatricula, txtNumRetiro;
    private JTextArea txtResultado;

    // Instancias de arreglos para acceder a datos de matrícula, retiro, alumnos y cursos
    private ArregloMatriculas am = new ArregloMatriculas();
    private ArregloRetiro ar = new ArregloRetiro();
    private ArregloAlumnos aa = new ArregloAlumnos();
    private ArregloCursos ac = new ArregloCursos();

    // Constructor de la clase - Método sin retorno y sin parámetros
    public frmConsultasMatriculaRetiros() {
        // Establecer color de fondo de la ventana
        getContentPane().setBackground(new Color(193, 193, 193));

        // Establecer icono de la ventana
        setIconImage(Toolkit.getDefaultToolkit().getImage(frmConsultasMatriculaRetiros.class.getResource("/resources/imagen/libro-abierto.png")));

        // Título de la ventana
        setTitle("Consulta Matrícula y Retiro");

        // Tamaño de la ventana
        setSize(550, 400);

        // Desactivación del layout automático
        getContentPane().setLayout(null);

        // Crear etiqueta para número de matrícula
        JLabel lbl1 = new JLabel("Número de Matrícula:");
        lbl1.setBounds(20, 20, 160, 25); // Posición y tamaño
        getContentPane().add(lbl1); // Añadir al contenedor

        // Crear campo de texto para número de matrícula
        txtNumMatricula = new JTextField();
        txtNumMatricula.setBounds(180, 20, 150, 25);
        getContentPane().add(txtNumMatricula);

        // Crear botón para consultar matrícula
        JButton btnMatricula = new JButton("Consultar Matrícula");
        btnMatricula.setBounds(340, 20, 170, 25);
        getContentPane().add(btnMatricula);

        // Crear etiqueta para número de retiro
        JLabel lbl2 = new JLabel("Número de Retiro:");
        lbl2.setBounds(20, 60, 160, 25);
        getContentPane().add(lbl2);

        // Crear campo de texto para número de retiro
        txtNumRetiro = new JTextField();
        txtNumRetiro.setBounds(180, 60, 150, 25);
        getContentPane().add(txtNumRetiro);

        // Crear botón para consultar retiro
        JButton btnRetiro = new JButton("Consultar Retiro");
        btnRetiro.setBounds(340, 50, 170, 25);
        getContentPane().add(btnRetiro);

        // Crear área de texto para mostrar resultados
        txtResultado = new JTextArea();
        txtResultado.setEditable(false); // Solo lectura
        JScrollPane scroll = new JScrollPane(txtResultado); // Añadir scroll
        scroll.setBounds(20, 110, 490, 230); // Posición
        getContentPane().add(scroll); // Añadir al contenedor

        // Crear botón para cerrar la ventana
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBackground(new Color(255, 128, 128)); // Color de fondo
        btnCerrar.addActionListener(new ActionListener() { // Listener para cerrar
            // Método con retorno void y con parámetro ActionEvent
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana
            }
        });
        btnCerrar.setBounds(417, 80, 93, 23); // Posición
        getContentPane().add(btnCerrar);

        // Asignar acciones a botones usando expresiones lambda
        btnMatricula.addActionListener(e -> consultarMatricula()); // Llama método sin parámetros y sin retorno
        btnRetiro.addActionListener(e -> consultarRetiro());       // Llama método sin parámetros y sin retorno

        // Comportamiento al cerrar la ventana
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true); // Hacer visible la ventana
    }

    // Método para consultar matrícula - Método sin retorno y sin parámetros
    void consultarMatricula() {
        txtResultado.setText(""); // Limpiar resultados
        try {
            int num = Integer.parseInt(txtNumMatricula.getText()); // Convertir texto a número
            Matriculas m = am.buscar(num); // Buscar matrícula

            if (m != null) { // Si se encontró matrícula
                Alumnos a = aa.buscar(m.getCodigoAlumno()); // Buscar alumno
                Cursos c = ac.buscar(m.getCodigoCurso());   // Buscar curso
                
                if (a != null && c != null) { // Si alumno y curso existen
                    txtResultado.append("🔹 MATRÍCULA:\n");
                    txtResultado.append("Alumno: " + a.getNombres() + " " + a.getApellidos() + "\n");
                    txtResultado.append("Curso: " + c.getAsignatura() + "\n");
                    txtResultado.append("Fecha: " + m.getFecha() + " " + m.getHora() + "\n");
                } else {
                    txtResultado.setText("Datos incompletos (alumno o curso no encontrado)");
                }
            } else {
                txtResultado.setText("Matrícula no encontrada.");
            }
        } catch (Exception ex) {
            txtResultado.setText("Número inválido."); // Manejo de error en la conversión
        }
    }

    // Método para consultar retiro - Método sin retorno y sin parámetros
    void consultarRetiro() {
        txtResultado.setText(""); // Limpiar resultados
        try {
            int num = Integer.parseInt(txtNumRetiro.getText()); // Convertir texto a número
            Retiro r = ar.buscar(num); // Buscar retiro
            
            if (r != null) { // Si se encontró retiro
                Matriculas m = am.buscar(r.getCodigoMatricula()); // Buscar matrícula asociada
                
                if (m != null) {
                    Alumnos a = aa.buscar(m.getCodigoAlumno()); // Buscar alumno
                    Cursos c = ac.buscar(m.getCodigoCurso());   // Buscar curso
                    
                    if (a != null && c != null) {
                        txtResultado.append("🔹 RETIRO:\n");
                        txtResultado.append("Alumno: " + a.getNombres() + " " + a.getApellidos() + "\n");
                        txtResultado.append("DNI: " + a.getDni() + "\n");
                        txtResultado.append("Curso: " + c.getAsignatura() + "\n");
                        txtResultado.append("Fecha: " + r.getFecha() + " " + r.getHora() + "\n");
                    } else {
                        txtResultado.setText("Datos incompletos (alumno o curso no encontrado)");
                    }
                } else {
                    txtResultado.setText("Matrícula asociada no encontrada");
                }
            } else {
                txtResultado.setText("Retiro no encontrado.");
            }
        } catch (Exception ex) {
            txtResultado.setText("Número inválido."); // Manejo de error en la conversión
        }
    }

    // Método principal para ejecutar la ventana - Método sin retorno y con parámetros
    public static void main(String[] args) {
        new frmConsultasMatriculaRetiros(); // Instancia la ventana
    }
}
