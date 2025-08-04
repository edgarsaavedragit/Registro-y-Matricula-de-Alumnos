// Paquete que contiene interfaces gráficas
package guis;

// Importación de librerías necesarias para la GUI y eventos
import java.awt.*;
import javax.swing.*;

// Importación de clases de arreglos personalizadas
import arreglos.*;
import clases.*;

// Importación de clases para manejar eventos
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// Clase principal que hereda de JFrame para crear una ventana
public class frmConsultaAlumnosyCursos extends JFrame {

	// Declaración de campos de texto para ingreso de códigos
	private JTextField txtCodigoAlumno, txtCodigoCurso;

	// Área de texto para mostrar resultados
	private JTextArea txtResultado;

	// Instancia de arreglo de alumnos
	private ArregloAlumnos aa = new ArregloAlumnos();

	// Instancia de arreglo de cursos
	private ArregloCursos ac = new ArregloCursos();

	// Instancia de arreglo de matrículas
	private ArregloMatriculas am = new ArregloMatriculas();

	// Constructor de la clase (Método sin retorno, sin parámetros)
	public frmConsultaAlumnosyCursos() {
		// Establece el color de fondo del panel principal
		getContentPane().setBackground(new Color(193, 193, 193));

		// Establece el ícono de la ventana
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmConsultaAlumnosyCursos.class.getResource("/resources/imagen/libro-abierto.png")));

		// Establece el título de la ventana
		setTitle("Consulta de Alumnos y Cursos");

		// Establece el tamaño de la ventana
		setSize(550, 400);

		// Define el diseño del contenido como libre (sin layout manager)
		getContentPane().setLayout(null);

		// Crea etiqueta para el código del alumno
		JLabel lbl1 = new JLabel("Código de Alumno:");
		// Posición y tamaño de la etiqueta
		lbl1.setBounds(20, 20, 150, 25);
		// Agrega la etiqueta al panel
		getContentPane().add(lbl1);

		// Crea campo de texto para ingresar código de alumno
		txtCodigoAlumno = new JTextField();
		// Posición y tamaño del campo
		txtCodigoAlumno.setBounds(160, 20, 150, 25);
		// Agrega el campo al panel
		getContentPane().add(txtCodigoAlumno);

		// Crea botón para consultar alumno
		JButton btnAlumno = new JButton("Consultar Alumno");
		// Posición y tamaño del botón
		btnAlumno.setBounds(330, 20, 170, 25);
		// Agrega el botón al panel
		getContentPane().add(btnAlumno);

		// Crea etiqueta para código de curso
		JLabel lbl2 = new JLabel("Código de Curso:");
		// Posición y tamaño de la etiqueta
		lbl2.setBounds(20, 60, 150, 25);
		// Agrega la etiqueta al panel
		getContentPane().add(lbl2);

		// Crea campo de texto para ingresar código de curso
		txtCodigoCurso = new JTextField();
		// Posición y tamaño del campo
		txtCodigoCurso.setBounds(160, 60, 150, 25);
		// Agrega el campo al panel
		getContentPane().add(txtCodigoCurso);

		// Crea botón para consultar curso
		JButton btnCurso = new JButton("Consultar Curso");
		// Posición y tamaño del botón
		btnCurso.setBounds(330, 50, 170, 25);
		// Agrega el botón al panel
		getContentPane().add(btnCurso);

		// Crea un área de texto para mostrar resultados
		txtResultado = new JTextArea();
		// Agrega scroll al área de texto
		JScrollPane scroll = new JScrollPane(txtResultado);
		// Posición y tamaño del scroll
		scroll.setBounds(20, 110, 480, 230);
		// Agrega el scroll (con área de texto) al panel
		getContentPane().add(scroll);
		
		// Crea botón para cerrar la ventana
		JButton btnCerrar = new JButton("Cerrar");
		// Establece el color de fondo del botón
		btnCerrar.setBackground(new Color(255, 128, 128));
		// Agrega evento para cerrar la ventana al presionar el botón
		btnCerrar.addActionListener(new ActionListener() {
			// Método que se ejecuta al hacer clic (Método sin retorno, con parámetros)
			public void actionPerformed(ActionEvent e) {
				// Cierra la ventana actual
				dispose();
			}
		});
		// Posición y tamaño del botón cerrar
		btnCerrar.setBounds(397, 80, 103, 23);
		// Agrega el botón al panel
		getContentPane().add(btnCerrar);

		// Asigna evento al botón para consultar alumno (método sin retorno, sin parámetros)
		btnAlumno.addActionListener(e -> consultarAlumno());

		// Asigna evento al botón para consultar curso (método sin retorno, sin parámetros)
		btnCurso.addActionListener(e -> consultarCurso());

		// Define que la ventana se cierra al presionar la "X"
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// Hace visible la ventana
		setVisible(true);
	}

	// Método para consultar información del alumno (Método sin retorno, sin parámetros)
	void consultarAlumno() {
		// Limpia el área de texto antes de mostrar resultados
		txtResultado.setText("");

		try {
			// Convierte el texto ingresado en número
			int codAlumno = Integer.parseInt(txtCodigoAlumno.getText());

			// Busca al alumno por su código
			Alumnos a = aa.buscar(codAlumno);

			// Si no se encuentra, muestra mensaje
			if (a == null) {
				txtResultado.setText("Alumno no encontrado.");
				return;
			}

			// Muestra los datos del alumno encontrado
			txtResultado.append("Nombre: " + a.getNombres() + " " + a.getApellidos() + "\n");
			txtResultado.append("DNI: " + a.getDni() + "\n"); 
			txtResultado.append("Edad: " + a.getEdad() + "\n");
			txtResultado.append("Celular: " + a.getCelular() + "\n"); 
			txtResultado.append("Estado: " + a.getEstado() + "\n");

			// Si el alumno está activo (estado == 1), se buscan sus cursos
			if (a.getEstado() == 1) {
				// Recorre todas las matrículas
				for (int i = 0; i < am.tamano(); i++) {
					// Obtiene una matrícula
					Matriculas m = am.obtener(i);

					// Si el código de alumno coincide
					if (m.getCodigoAlumno() == codAlumno) {
						// Busca el curso correspondiente
						Cursos c = ac.buscar(m.getCodigoCurso());

						// Si el curso existe, muestra información
						if (c != null) {
							txtResultado.append("\nCurso Matriculado:\n");
							txtResultado.append("Asignatura: " + c.getAsignatura() + ", Ciclo: " + c.getCiclo() + "\n");
							txtResultado.append("Créditos: " + c.getCreditos() + ", Horas: " + c.getHoras());
						}
					}
				}
			}
		} catch (Exception ex) {
			// En caso de error (por ejemplo, texto inválido), se muestra mensaje
			txtResultado.setText("Código inválido.");
		}
	}

	// Método para consultar información del curso (Método sin retorno, sin parámetros)
	void consultarCurso() {
		// Limpia el área de texto antes de mostrar resultados
		txtResultado.setText("");

		try {
			// Convierte el texto ingresado en número
			int codCurso = Integer.parseInt(txtCodigoCurso.getText());

			// Busca el curso por su código
			Cursos c = ac.buscar(codCurso);

			// Si no se encuentra, muestra mensaje
			if (c == null) {
				txtResultado.setText("Curso no encontrado.");
				return;
			}

			// Muestra información del curso encontrado
			txtResultado.append("Curso Encontrado:\n");
			txtResultado.append("Asignatura: " + c.getAsignatura() + "\n");
			txtResultado.append("Ciclo: " + c.getCiclo() + "\n");
			txtResultado.append("Créditos: " + c.getCreditos() + "\n");
			txtResultado.append("Horas: " + c.getHoras());
		} catch (Exception ex) {
			// En caso de error (por ejemplo, texto inválido), se muestra mensaje
			txtResultado.setText("Código inválido.");
		}
	}

	// Método principal para ejecutar el programa (Método con retorno, con parámetros)
	public static void main(String[] args) {
		// Crea una nueva instancia de la ventana
		new frmConsultaAlumnosyCursos();
	}
}
