package guis;

import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import arreglos.ArregloAlumnos;
import arreglos.ArregloCursos;
import arreglos.ArregloMatriculas;
import clases.Alumnos;
import clases.Cursos;
import clases.Matriculas;
import clases.Retiro;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.awt.Toolkit;

public class frmMatricula extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JLabel lblCodigo;
	private JLabel lblAsignatura;
	private JLabel lblCreditos;
	private JLabel lblHoras;
	private JTextField txtCodigoMatricula;
	private JTextField txtCodigoAlumno;
	private JTextField txtFecha;
	private JTextField txtHora;
	private JTextField txtCodAlu;
	private JTextField txtNomAlu;
	private JTextField txtCodCur;
	private JTextField txtNomCur;	
    private JTextField txtCodigoCurso;
	private JButton btnBuscar;
	private JButton btnOK;
	private JButton btnOpciones;
	private JButton btnAdicionar;
	private JButton btnConsultar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnBuscarAlumno;
	private JButton btnBuscarCurso;
	private JScrollPane scrollPane;
	private JTable tblAlumno;
	private DefaultTableModel modelo;
	private JButton btnCerrar;
	private int tipoOperacion;
	
	private boolean estadoCurso = false;
	
	public final static int ADICIONAR = 0;
	public final static int CONSULTAR = 1;
	public final static int MODIFICAR = 2;
	public final static int ELIMINAR  = 3;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmMatricula dialog = new frmMatricula();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public frmMatricula() {
		getContentPane().setBackground(new Color(193, 193, 193));
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmMatricula.class.getResource("/resources/imagen/libro-abierto.png")));
		setResizable(false);
		setTitle("Registro | Matriculas");
		setBounds(100, 100, 811, 594);
		getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		
		lblCodigo = new JLabel("Código Matricula");
		lblCodigo.setBounds(138, 10, 110, 23);
		getContentPane().add(lblCodigo);
		
		lblAsignatura = new JLabel("Cod alumno");
		lblAsignatura.setBounds(138, 35, 70, 23);
		getContentPane().add(lblAsignatura);
	
		lblCreditos = new JLabel("Fecha");
		lblCreditos.setBounds(139, 85, 70, 23);
		getContentPane().add(lblCreditos);
		
		lblHoras = new JLabel("Hora");
		lblHoras.setBounds(139, 110, 70, 23);
		getContentPane().add(lblHoras);
		
		txtCodigoMatricula = new JTextField();
		txtCodigoMatricula.setBounds(256, 10, 86, 23);
		getContentPane().add(txtCodigoMatricula);
		txtCodigoMatricula.setEditable(false);
		txtCodigoMatricula.setColumns(10);

		txtCodigoAlumno = new JTextField();
		txtCodigoAlumno.setEditable(false);
		txtCodigoAlumno.setBounds(256, 35, 144, 23);
		getContentPane().add(txtCodigoAlumno);
		txtCodigoAlumno.setColumns(10);

		txtFecha = new JTextField();
		txtFecha.setEditable(false);
		txtFecha.setBounds(256, 85, 86, 23);
		getContentPane().add(txtFecha);
		txtFecha.setColumns(10);
		
		txtHora = new JTextField();
		txtHora.setEditable(false);
		txtHora.setBounds(256, 110, 86, 23);
		getContentPane().add(txtHora);
		txtHora.setColumns(10);
		
		btnBuscar = new JButton("🔍");
		btnBuscar.addActionListener(this);
		btnBuscar.setEnabled(false);
		btnBuscar.setBounds(350, 10, 50, 23);
		getContentPane().add(btnBuscar);

		btnOK = new JButton("Aceptar");
		btnOK.setSize(0, 0);
		btnOK.setLocation(0, 0);
		btnOK.addActionListener(this);
		getContentPane().add(btnOK);
		
		btnOpciones = new JButton("Cancelar");
		btnOpciones.addActionListener(this);
		getContentPane().add(btnOpciones);
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(this);
		btnAdicionar.setBounds(8, 10, 120, 23);
		getContentPane().add(btnAdicionar);

		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(this);
		btnConsultar.setBounds(8, 43, 120, 23);
		getContentPane().add(btnConsultar);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(this);
		btnModificar.setBounds(8, 76, 120, 23);
		getContentPane().add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(9, 110, 120, 23);
		getContentPane().add(btnEliminar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(8, 190, 776, 355);
		getContentPane().add(scrollPane);
		
		tblAlumno = new JTable();
		tblAlumno.setBackground(new Color(255, 255, 255));
		tblAlumno.setEnabled(false);
		scrollPane.setViewportView(tblAlumno);
		tblAlumno.setFillsViewportHeight(true);

		JLabel lblCiclo = new JLabel("Cod curso");
		lblCiclo.setBounds(138, 60, 70, 23);
		getContentPane().add(lblCiclo);
		
		txtNomAlu = new JTextField();
		txtNomAlu.setEditable(false);
		txtNomAlu.setColumns(10);
		txtNomAlu.setBounds(536, 35, 251, 23);
		getContentPane().add(txtNomAlu);
		
		txtCodCur = new JTextField();
		txtCodCur.setEditable(false);
		txtCodCur.setColumns(10);
		txtCodCur.setBounds(536, 60, 126, 23);
		getContentPane().add(txtCodCur);
		
		txtNomCur = new JTextField();
		txtNomCur.setEditable(false);
		txtNomCur.setColumns(10);
		txtNomCur.setBounds(536, 85, 251, 23);
		getContentPane().add(txtNomCur);
		
		txtCodAlu = new JTextField();
		txtCodAlu.addActionListener(this);
		txtCodAlu.setEditable(false);
		txtCodAlu.setBounds(536, 10, 126, 23);
		getContentPane().add(txtCodAlu);
		txtCodAlu.setColumns(10);
				
		btnBuscarAlumno = new JButton("🔍");
		btnBuscarAlumno.setEnabled(false);
		btnBuscarAlumno.addActionListener(this);
		btnBuscarAlumno.setBounds(670, 10, 50, 23);
		getContentPane().add(btnBuscarAlumno);
		
		btnBuscarCurso = new JButton("🔍");
		btnBuscarCurso.setEnabled(false);
		btnBuscarCurso.addActionListener(this);
		btnBuscarCurso.setBounds(670, 60, 50, 23);
		getContentPane().add(btnBuscarCurso);
	
		JLabel lblCdigoAlumno = new JLabel("Código alumno");
		lblCdigoAlumno.setBounds(416, 10, 110, 23);
		getContentPane().add(lblCdigoAlumno);
		
		JLabel lblCodigo_1_1 = new JLabel("Nombre alumno");
		lblCodigo_1_1.setBounds(416, 35, 110, 23);
		getContentPane().add(lblCodigo_1_1);
		
		JLabel lblCodigo_1_2 = new JLabel("Código curso");
		lblCodigo_1_2.setBounds(416, 60, 110, 23);
		getContentPane().add(lblCodigo_1_2);
		
		JLabel lblCodigo_1_3 = new JLabel("Nombre curso");
		lblCodigo_1_3.setBounds(416, 85, 110, 23);
		getContentPane().add(lblCodigo_1_3);
		
		txtCodigoCurso = new JTextField();
		txtCodigoCurso.setEditable(false);
		txtCodigoCurso.setColumns(10);
		txtCodigoCurso.setBounds(256, 60, 144, 23);
		getContentPane().add(txtCodigoCurso);
		
		listar();
		ajustarAnchoColumnas();
		
        mostrarFechaHora();
        
        btnCerrar = new JButton("Cerrar");
        btnCerrar.setBackground(new Color(255, 128, 128));
        btnCerrar.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	dispose();
        	}
        });
        btnCerrar.setBounds(10, 144, 118, 23);
        getContentPane().add(btnCerrar);
	}
	
	ArregloMatriculas am = new ArregloMatriculas();
	ArregloAlumnos aa = new ArregloAlumnos();
	ArregloCursos ac = new ArregloCursos();
	
	
	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(arg0);
		}
		if (arg0.getSource() == btnModificar) {
			actionPerformedBtnModificar(arg0);
		}
		if (arg0.getSource() == btnConsultar) {
			actionPerformedBtnConsultar(arg0);
		}
		if (arg0.getSource() == btnAdicionar) {
			actionPerformedBtnAdicionar(arg0);
		}
		if (arg0.getSource() == btnOpciones) {
			actionPerformedBtnOpciones(arg0);
		}
		if (arg0.getSource() == btnOK) {
			actionPerformedBtnOK(arg0);
		}
		if (arg0.getSource() == btnBuscar) {
			actionPerformedBtnBuscar(arg0);
		}
		if (arg0.getSource() == btnBuscarAlumno) {
			actionPerformedBtnBuscarAlumno(arg0);
		}
		if (arg0.getSource() == btnBuscarCurso) {
			actionPerformedBtnBuscarCurso(arg0);
		}
	}
	protected void actionPerformedBtnBuscar(ActionEvent arg0) {
		consultarMatricula();
	}
	protected void actionPerformedBtnBuscarCurso(ActionEvent arg0) {
		estadoCurso = true;
		buscarCurso();
	}
	protected void actionPerformedBtnBuscarAlumno(ActionEvent arg0) {
		buscarAlumno();
		
	}
	protected void actionPerformedBtnOK(ActionEvent arg0) {
		switch (tipoOperacion) {
			case ADICIONAR:
				adicionarMatricula();
				break;
			case CONSULTAR:
				break;
			case MODIFICAR:
				modificarMatricula();
				break;
			case ELIMINAR:
				eliminarMatricula();
		}
	}
	
	void mostrarFechaHora()
	{
		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
        
		// Obtener fecha y hora actuales
        String fecha = LocalDate.now().format(formatoFecha);
        String hora = LocalTime.now().format(formatoHora);

        txtFecha.setText(fecha);
        txtHora.setText(hora);
	}
	
	void eliminarMatricula() {
		try {
			int codigoMatricula = leerCodigoMatricula();
			int codigoAlumno = leerCodigoAlumno();
			Matriculas p = am.buscar(codigoMatricula);
			if (p != null) {
				int ok = confirmar("Deseas eliminar el registro?");
				if (ok == 0) {
					if (aa.estadoCorrectoAlumnoMatricula(codigoAlumno)) {
						am.eliminar(p);
						limpiarDatos();
						txtCodigoMatricula.setText("" + am.codigoCorrelativo());
						listar();
						
						//VALIDAR CON EL PROFESOR YA QUE NO INDICA QUE SE DEBE HACER CON EL ALUMNO LUEGO DE ELIMINAR LA MATRICULA
						//POR SI ACASO LO ESTAMOS ACTUALIZANDO A ESTADO 0 LUEGO DE ELIMINAR LA MATRICULA
						Alumnos a = aa.buscar(codigoAlumno);
						a.setEstado(0);
						aa.actualizarAlumno();
						
					} else {
						error("No se puede eliminar la matricula, revisar el estado del alumno ", txtCodigoCurso);
					}
				}
			} else
				error("El codigo " + codigoMatricula + " no existe", txtCodigoCurso);
		} catch (Exception e) {
			// TODO: handle exception
			error("Ingrese el codigo correcto ", txtCodigoCurso);
		}
	}
	
	void consultarMatricula() {
		try {
			int codigo=leerCodigoMatricula();
			Matriculas p=am.buscar(codigo);
			if(p !=null) {
				txtCodigoAlumno.setText(Integer.toString(p.getCodigoAlumno()));
				txtCodigoCurso.setText(Integer.toString(p.getCodigoCurso()));
				txtFecha.setText(p.getFecha());
				txtHora.setText(p.getHora());
				
				txtCodAlu.setText(Integer.toString(p.getCodigoAlumno()));
				buscarAlumno();
				
				txtCodCur.setText(Integer.toString(p.getCodigoCurso()));
				buscarCurso();
				
				if (tipoOperacion == MODIFICAR)
				{
					txtCodCur.setEditable(true);
					btnBuscarCurso.setEnabled(true);
					txtCodigoMatricula.requestFocus();
				}
				
				obtenerMatricula(codigo);
			
			}else {
				error("El codigo " +codigo+ " no existe", txtCodigoCurso);
			}
		} catch (Exception e) {
			// TODO: handle exception
			error("Ingresar el codigo correcto", txtCodigoCurso);
		}
	}
	
	void obtenerMatricula(int codigoMatricula) {
		listar();
		Matriculas matricula;
		modelo.setRowCount(0);
		for (int i = 0; i < am.tamano(); i++) {
			matricula = am.obtener(i);
			if (matricula.getCodigoMatricula() == codigoMatricula) {
				Object[] fila = { matricula.getCodigoMatricula(), matricula.getCodigoAlumno(), matricula.getNombreAlumno(),
						matricula.getCodigoCurso(), matricula.getCurso(), matricula.getFecha(), matricula.getHora()

				};
				modelo.addRow(fila);
				
				return;
			}
		}
	}
	
	void adicionarMatricula() {

		try {

			validarEntradas();

			int codigoMatricula = leerCodigoMatricula();
			int codigoAlumno = leerCodigoAlumno();
			int codigoCurso = leerCodigoCurso();
			String fecha = leerFecha();
			String hora = leerHora();
			String nombreAlumno = "";
			String curso = "";

			if (am.existeAlumno(codigoAlumno)) {
				error("El alumno ya se encuentra matriculado.", txtCodigoAlumno);
			} else {
				Alumnos a = aa.buscar(codigoAlumno);
				Cursos cursos = ac.buscar(codigoCurso);

				if (a != null) {
					
					nombreAlumno = a.getNombres() + " " + a.getApellidos();
					
					if (cursos != null) {
						
						curso = cursos.getAsignatura();
						Matriculas nuevo = new Matriculas(codigoMatricula, codigoAlumno, nombreAlumno, codigoCurso,
								curso, fecha, hora);
						am.adicionar(nuevo);

						a.setEstado(1);
						aa.actualizarAlumno();

						limpiarDatos();
						txtCodigoMatricula.setText("" + am.codigoCorrelativo());

						listar();
					} else {
						error("Curso ingresado no existe", txtCodAlu);
					}
				} else {
					error("Alumno ingresado no existe", txtCodCur);
				}
			}

		} catch (RuntimeException e) {
			mensaje(e.getMessage());
		}
	}
	
	void modificarMatricula() {
		try {

			validarEntradas();

			int codigoMatricula = leerCodigoMatricula();
			int codigoAlumno = leerCodigoAlumno();
			int codigoCurso = leerCodigoCurso();
			String curso = leerCurso();
			String fecha = leerFecha();
			String hora = leerHora();

			boolean estadoRetiro = aa.estadoCorrectoAlumnoRetiro(codigoAlumno);

			if (estadoRetiro) {
				if (estadoCurso == true) {
					Matriculas p = am.buscar(codigoMatricula);
					p.setCodigoCurso(codigoCurso);
					p.setCurso(curso);
					am.actualizarMatricula();

					txtCodigoMatricula.requestFocus();
					txtCodCur.setEditable(false);
					btnBuscarCurso.setEnabled(false);

					estadoCurso = false;
					listar();
				} else {
					error("Debe ingresar y buscar el código del curso", txtCodCur);
					txtNomCur.setText("");
					txtCodigoCurso.setText("");
				}
			} else {
				error("Matricula activada - No puede actualizar el curso", txtCodigoMatricula);
				limpiarCuadroTextos();
				listar();
			}

		} catch (RuntimeException e) {
			mensaje(e.getMessage());
			limpiarCuadroTextos();
			listar();
		}
	}

	void mensaje(String s) {
		JOptionPane.showMessageDialog(this, s,"Informacion",0);
	}
	
	void limpiarDatos()
	{
		txtCodigoAlumno.setText("");
		txtCodigoCurso.setText("");
		txtCodAlu.setText("");
		txtCodCur.setText("");
		txtNomAlu.setText("");
		txtNomCur.setText("");

	}
	
	String validarEntradas()
	{
		String codigoMatricula = txtCodigoMatricula.getText().trim();
		String codigoAlumno = txtCodigoAlumno.getText().trim();
		String codigoCurso = txtCodigoCurso.getText().trim();

		
		if (isNullOrEmpty(codigoMatricula)) throw new RuntimeException("Debe ingresar la Matrícula");
		if (isNullOrEmpty(codigoAlumno)) throw new RuntimeException("Ingrese el nombre del alumno");
		if (isNullOrEmpty(codigoCurso)) throw new RuntimeException("Ingrese el curso correcto");
		
		 return "OK";
	} 
	
	public boolean isNullOrEmpty(String str) {
	    return str == null || str.isEmpty();
	}
	
	void listar() {
		
		modelo = new DefaultTableModel();
		modelo.addColumn("Código Matricula");
		modelo.addColumn("Código Alumno");
		modelo.addColumn("Nombres");
		modelo.addColumn("Código Curso");
		modelo.addColumn("Curso");
		modelo.addColumn("Fecha");
		modelo.addColumn("Hora");
		tblAlumno.setModel(modelo);
		
		Matriculas p;
		modelo.setRowCount(0);
		for(int i=0; i<am.tamano(); i++) {
			p=am.obtener(i);
			Object[] fila = {p.getCodigoMatricula(),
							 p.getCodigoAlumno(),
							 p.getNombreAlumno(),
							 p.getCodigoCurso(),
							 p.getCurso(),
							 p.getFecha(),
							 p.getHora()	
			
			};
			modelo.addRow(fila);
		}
	}
	
	
	void buscarAlumno() {
		try {
			int codigoAlumno=leerCodigoAlumnoRegistrado();
			Alumnos p=aa.buscar(codigoAlumno);
			if(p !=null) {
				txtNomAlu.setText(p.getNombres() + " " + p.getApellidos());
				txtCodigoAlumno.setText(""+codigoAlumno);
			}else {
				error("El codigo " +codigoAlumno+ " no existe", txtCodAlu);
			}
		} catch (Exception e) {
			// TODO: handle exception
			error("Ingresar el codigo correcto", txtCodAlu);
		}
	}
	
	void buscarCurso() {
		try {
			int codigoCurso=leerCodigoCursoRegistrado();
			Cursos p=ac.buscar(codigoCurso);
			if(p !=null) {
				txtNomCur.setText(p.getAsignatura());
				txtCodigoCurso.setText(""+codigoCurso);
			}else {
				error("El codigo " +codigoCurso+ " no existe", txtCodCur);
			}
		} catch (Exception e) {
			// TODO: handle exception
			error("Ingresar el codigo correcto", txtCodCur);
		}
	}
	
	protected void actionPerformedBtnOpciones(ActionEvent arg0) {
		deshabilitarAceptarCancelar();
		deshabilitarBotones();
		habilitarOpciones();
		limpiarCuadroTextos();
		requestFocus();
		listar();
	}
	protected void actionPerformedBtnAdicionar(ActionEvent arg0) {
		tipoOperacion = ADICIONAR;
		txtCodigoMatricula.setText(""+am.codigoCorrelativo());
		botonesAdicionar();
		deshabilitarOpciones();
		habilitarAceptarCancelar();
		mostrarFechaHora();
	}
	protected void actionPerformedBtnConsultar(ActionEvent arg0) {
		tipoOperacion = CONSULTAR;
		botonesConsultar();
		habilitarAceptarCancelar();
		deshabilitarOpciones();
	}
	protected void actionPerformedBtnModificar(ActionEvent arg0) {
		tipoOperacion = MODIFICAR;
		botonesModificar();
		habilitarAceptarCancelar();
		deshabilitarOpciones();
	}
	protected void actionPerformedBtnEliminar(ActionEvent arg0) {
		tipoOperacion = ELIMINAR;
		botonesEliminar();
		habilitarAceptarCancelar();
		deshabilitarOpciones();
	}
	void ajustarAnchoColumnas() {
		TableColumnModel tcm = tblAlumno.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna( 8));
		tcm.getColumn(1).setPreferredWidth(anchoColumna(10));
		tcm.getColumn(2).setPreferredWidth(anchoColumna(10));
		tcm.getColumn(3).setPreferredWidth(anchoColumna(10));
		tcm.getColumn(4).setPreferredWidth(anchoColumna(10));
		tcm.getColumn(5).setPreferredWidth(anchoColumna(10));
		tcm.getColumn(6).setPreferredWidth(anchoColumna(10));
	}
	

	void botonesAdicionar() {
		txtCodAlu.setEditable(true);
		btnBuscarAlumno.setEnabled(true);
		txtCodCur.setEditable(true);
		btnBuscarCurso.setEnabled(true);
		txtCodAlu.requestFocus();
	}
	void botonesConsultar() {
		txtCodigoMatricula.setEditable(true);		

	}
	void botonesModificar() {
		txtCodigoMatricula.setEditable(true);
		btnBuscar.setEnabled(true);
		
	}
	void botonesEliminar() {
		txtCodigoMatricula.setEditable(true);
		btnBuscar.setEnabled(true);
		txtCodigoMatricula.requestFocus();
	}
	void deshabilitarBotones() {
		btnBuscar.setEnabled(false);
		txtCodigoCurso.setEditable(false);
		txtCodigoMatricula.setEditable(false);
		txtCodigoAlumno.setEditable(false);
		txtCodAlu.setEditable(false);
		txtCodCur.setEditable(false);
		btnBuscarAlumno.setEnabled(false);
		btnBuscarCurso.setEnabled(false);
	}
	void deshabilitarAceptarCancelar() {
		btnOK.setVisible(false);
		btnOpciones.setVisible(false);
	}
	void habilitarAceptarCancelar() {
		btnOK.setBounds(8, 10, 120, 23);
		btnOpciones.setBounds(8, 43, 120, 23);
		btnOK.setVisible(true);
		btnOK.setEnabled(true);
		btnOpciones.setVisible(true);
		btnOpciones.setEnabled(true);
		switch(tipoOperacion) {
		case CONSULTAR:
			btnOK.setEnabled(false);
			btnBuscar.setEnabled(true);
		}
	}
	void deshabilitarOpciones() {
		btnAdicionar.setVisible(false);
		btnConsultar.setVisible(false);
		btnModificar.setVisible(false);
		btnEliminar.setVisible(false);
	}
	void habilitarOpciones() {
		btnAdicionar.setVisible(true);
		btnConsultar.setVisible(true);
		btnModificar.setVisible(true);
		btnEliminar.setVisible(true);
	}
	void limpiarCuadroTextos() {
		txtCodigoMatricula.setText("");
		txtCodigoAlumno.setText("");
		txtCodigoCurso.setText("");
		txtFecha.setText("");
		txtHora.setText("");
		txtCodAlu.setText("");
		txtNomAlu.setText("");
		txtCodCur.setText("");
		txtNomCur.setText("");
		txtFecha.setText("");
		txtCodigoMatricula.setText("");
	}
	int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}
	
	void error(String s, JTextField txt) {
		mensaje(s);
		txt.setText("");
		txt.requestFocus();
	}
	
	//METODOS QUE RETORNAN VALOR SIN PARAMETROS
	int leerCodigoMatricula() {
		return Integer.parseInt(txtCodigoMatricula.getText().trim());
	}

	int leerCodigoAlumno() {
		return Integer.parseInt(txtCodigoAlumno.getText().trim());
	}

	int leerCodigoCurso() {
		return Integer.parseInt(txtCodigoCurso.getText().trim());
	}

	String leerFecha() {
		return txtFecha.getText().trim();
	}

	String leerHora() {
		return txtHora.getText().trim();
	}
	
	String leerCurso() {
		return txtNomCur.getText().trim();
	}
	
	int leerCodigoAlumnoRegistrado()
	{
		return Integer.parseInt(txtCodAlu.getText().trim());
	}
	
	int leerCodigoCursoRegistrado()
	{
		return Integer.parseInt(txtCodCur.getText().trim());
	}
	
	int confirmar(String s) {
		return JOptionPane.showConfirmDialog(this, s,"Alerta",0,1,null);
	}
	
}