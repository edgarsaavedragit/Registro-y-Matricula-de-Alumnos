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
import arreglos.ArregloRetiro;
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

public class frmRetiro extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JLabel lblCodigoRetiro;
	private JLabel lblCodigoMatricula;
	private JLabel lblFecha;
	private JLabel lblHora;
	private JTextField txtnumRetiro;
	private JTextField txtnumMatricula;
	private JTextField txtFecha;
	private JTextField txtHora;
	private JButton btnBuscar;
	private JButton btnOK;
	private JButton btnOpciones;
	private JButton btnAdicionar;
	private JButton btnConsultar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JScrollPane scrollPane;
	private JTable tblRetiro;
	private DefaultTableModel modelo;
	
	private int tipoOperacion;
	
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
					frmRetiro dialog = new frmRetiro();
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
	public frmRetiro() {
		getContentPane().setBackground(new Color(192, 192, 192));
		setResizable(false);
		setTitle("Mantenimiento -retiro");
		setBounds(100, 100, 811, 594);
		getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		
		lblCodigoRetiro = new JLabel("Código Retiro");
		lblCodigoRetiro.setBounds(138, 10, 110, 23);
		getContentPane().add(lblCodigoRetiro);
		
		lblCodigoMatricula = new JLabel("Código Matrícula");
		lblCodigoMatricula.setBounds(138, 35, 96, 23);
		getContentPane().add(lblCodigoMatricula);
	
		lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(494, 10, 70, 23);
		getContentPane().add(lblFecha);
		
		lblHora = new JLabel("Hora");
		lblHora.setBounds(494, 35, 70, 23);
		getContentPane().add(lblHora);
		
		txtnumRetiro = new JTextField();
		txtnumRetiro.setBounds(256, 10, 86, 23);
		getContentPane().add(txtnumRetiro);
		txtnumRetiro.setEditable(false);
		txtnumRetiro.setColumns(10);

		txtnumMatricula = new JTextField();
		txtnumMatricula.setEditable(false);
		txtnumMatricula.setBounds(256, 35, 144, 23);
		getContentPane().add(txtnumMatricula);
		txtnumMatricula.setColumns(10);

		txtFecha = new JTextField();
		txtFecha.setEditable(false);
		txtFecha.setBounds(549, 10, 86, 23);
		getContentPane().add(txtFecha);
		txtFecha.setColumns(10);
		
		txtHora = new JTextField();
		txtHora.setEditable(false);
		txtHora.setBounds(547, 35, 86, 23);
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
		scrollPane.setBounds(10, 190, 774, 355);
		getContentPane().add(scrollPane);
		
		tblRetiro = new JTable();
		tblRetiro.setBackground(new Color(255, 255, 255));
		tblRetiro.setEnabled(false);
		scrollPane.setViewportView(tblRetiro);
		tblRetiro.setFillsViewportHeight(true);

		listar();
		ajustarAnchoColumnas();
		 mostrarFechaHora();

        btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        btnCerrar.setBackground(new Color(255, 128, 128));
        btnCerrar.setBounds(8, 144, 120, 23);
        getContentPane().add(btnCerrar);
	}
	
	ArregloMatriculas am = new ArregloMatriculas();
	ArregloAlumnos aa = new ArregloAlumnos();
	ArregloCursos ac = new ArregloCursos();
	ArregloRetiro ar = new ArregloRetiro();
	
	private JButton btnCerrar;
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
	}
	protected void actionPerformedBtnBuscar(ActionEvent arg0) {
		consultarRetiro();
	}
	protected void actionPerformedBtnOK(ActionEvent arg0) {
		switch (tipoOperacion) {
			case ADICIONAR:
				adicionarRetiro();
				break;
			case CONSULTAR:
				break;
			case MODIFICAR:
				break;
			case ELIMINAR:
				eliminarRetiro();
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
	
	void eliminarRetiro() {
		try {
			int codigoRetiro = leerCodigoRetiro();
			Retiro retiro = ar.buscar(codigoRetiro);
			if (retiro != null) {
				int codigoMatricula = leerCodigoMatricula();
				Matriculas p = am.buscar(codigoMatricula);
				if (p != null) {
					int ok = confirmar("Deseas eliminar el registro?");
					if (ok == 0) {
						if (aa.estadoCorrectoAlumnoRetiro(p.getCodigoAlumno())) {
							ar.eliminar(retiro);

							limpiarDatos();
							txtnumRetiro.setText("" + ar.codigoCorrelativo());
							listar();
						} else {
							error("No se puede eliminar el retiro, revisar el estado del alumno ", txtnumRetiro);
						}
					}
				} else {
					error("El codigo " + codigoMatricula + " no existe", txtnumRetiro);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			error("Ingrese el codigo correcto ", txtnumRetiro);
		}
	}
	
	private void consultarRetiro() {
        try {
            int codigoRetiro = leerCodigoRetiro();
            Retiro retiro = ar.buscar(codigoRetiro);
            
            if (retiro != null) {
                Matriculas matricula = am.buscar(retiro.getCodigoMatricula());
                
                if (matricula != null) {
                    Cursos curso = ac.buscar(matricula.getCodigoCurso());
                    Alumnos alumno = aa.buscar(matricula.getCodigoAlumno());
                    
                    if (curso != null && alumno != null) {
                        txtnumMatricula.setText(String.valueOf(retiro.getCodigoMatricula()));
                        txtFecha.setText(retiro.getFecha());
                        txtHora.setText(retiro.getHora());
                        
                        // Mostrar información completa en la tabla
                        modelo.setRowCount(0);
                        modelo.addRow(new Object[]{
                            retiro.getCodigoRetiro(),
                            retiro.getCodigoMatricula(),
                            alumno.getNombres() + " " + alumno.getApellidos(),
                            curso.getAsignatura(),
                            retiro.getFecha(),
                            retiro.getHora()
                        });
                    } else {
                        error("Curso o alumno no encontrado", txtnumRetiro);
                    }
                } else {
                    error("Matrícula no encontrada", txtnumRetiro);
                }
            } else {
                error("Retiro no encontrado", txtnumRetiro);
            }
        } catch (Exception e) {
            error("Error al consultar retiro", txtnumRetiro);
        }
    }

	void adicionarRetiro() {

		try {

			validarEntradas();

			int codigoRetiro = leerCodigoRetiro();
			int codigoMatricula = leerCodigoMatricula();
			String fecha = leerFecha();
			String hora = leerHora();

			if (codigoRetiro != 0 && codigoMatricula != 0) {
				Matriculas matricula = am.buscar(codigoMatricula);

				if (matricula != null) {
					Alumnos alumno = aa.buscar(matricula.getCodigoAlumno());

					if (alumno != null) {

						Cursos curso = ac.buscar(matricula.getCodigoCurso());
						
						if (curso != null) {
							String nombreCompleto = alumno.getNombres() + " " + alumno.getApellidos();
							Retiro nuevo = new Retiro(codigoRetiro, codigoMatricula, alumno.getCodAlumno(),
									nombreCompleto, alumno.getDni(), curso.getAsignatura(), fecha, hora);
							ar.adicionar(nuevo);

							alumno.setEstado(2);
							aa.actualizarAlumno();
						}
						else
						{
							error("No existe curso asociado a la matrícula", txtnumMatricula);
						}
					} else {
						error("No existe detalle del alumno", txtnumMatricula);
					}
				} else {
					error("No existe la matrícula ingresada", txtnumMatricula);
				}

				limpiarDatos();
				txtnumRetiro.setText("" + ar.codigoCorrelativo());
				listar();
			} else {
				mensaje("Ingrese correctamente todos los campos");
			}
		} catch (RuntimeException e) {
			mensaje(e.getMessage());
		}
	}
	
	void mensaje(String s) {
		JOptionPane.showMessageDialog(this, s,"Informacion",0);
	}
	
	void limpiarDatos()
	{
		txtnumMatricula.setText("");
		txtnumRetiro.setText("");

	}
	
	String validarEntradas()
	{
		String codigoRetiro = txtnumRetiro.getText().trim();
		String codigoMatricula = txtnumMatricula.getText().trim();

		
		if (isNullOrEmpty(codigoRetiro)) throw new RuntimeException("codigo de Retiro incorrecto");
		if (isNullOrEmpty(codigoMatricula)) throw new RuntimeException("Ingrese el código de Matrícula");
		
		 return "OK";
	} 
	
	public boolean isNullOrEmpty(String str) {
	    return str == null || str.isEmpty();
	}
	
	void listar() {
		
		modelo = new DefaultTableModel();
		modelo.addColumn("Código Retiro");
		modelo.addColumn("Código Matrícula");
		modelo.addColumn("Código Alumno");
		modelo.addColumn("Nombre");
		modelo.addColumn("DNI");
		modelo.addColumn("Curso");
		modelo.addColumn("Fecha");
		modelo.addColumn("Hora");
		tblRetiro.setModel(modelo);
		
		Retiro retiro;
		modelo.setRowCount(0);
		for (int i = 0; i < ar.tamano(); i++) {
			retiro = ar.obtener(i);
			Object[] fila = { retiro.getCodigoRetiro(), retiro.getCodigoMatricula(), retiro.getCodigoAlumno(),
					retiro.getNombreAlumno(), retiro.getDniAlumno(), retiro.getCurso(), retiro.getFecha(),
					retiro.getHora()

			};
			modelo.addRow(fila);
		}
	}
	
	void obtenerRetiro(int codigoRetiro) {
		listar();
		Retiro retiro;
		modelo.setRowCount(0);
		for (int i = 0; i < ar.tamano(); i++) {
			retiro = ar.obtener(i);
			if (retiro.getCodigoRetiro() == codigoRetiro) {
				Object[] fila = { retiro.getCodigoRetiro(), retiro.getCodigoMatricula(), retiro.getCodigoAlumno(),
						retiro.getNombreAlumno(), retiro.getDniAlumno(), retiro.getCurso(), retiro.getFecha(),
						retiro.getHora()

				};
				modelo.addRow(fila);
				
				return;
			}		
		}
	}
	
	protected void actionPerformedBtnOpciones(ActionEvent arg0) {
		deshabilitarAceptarCancelar();
		habilitarOpciones();
		limpiarCuadroTextos();
		requestFocus();
		listar();
	}
	protected void actionPerformedBtnAdicionar(ActionEvent arg0) {
		tipoOperacion = ADICIONAR;
		txtnumRetiro.setText(""+ar.codigoCorrelativo());
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
		TableColumnModel tcm = tblRetiro.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(15));
		tcm.getColumn(1).setPreferredWidth(anchoColumna(15));
		tcm.getColumn(2).setPreferredWidth(anchoColumna(15));
		tcm.getColumn(3).setPreferredWidth(anchoColumna(25));
		tcm.getColumn(4).setPreferredWidth(anchoColumna(10));
		tcm.getColumn(5).setPreferredWidth(anchoColumna(10));
		tcm.getColumn(6).setPreferredWidth(anchoColumna(10));
		tcm.getColumn(7).setPreferredWidth(anchoColumna(10));
	}
	

	void botonesAdicionar() {
		txtnumMatricula.setEditable(true);
	}
	void botonesConsultar() {
		txtnumRetiro.setEditable(true);
		txtnumMatricula.setEditable(false);		

	}
	void botonesModificar() {
		txtnumRetiro.setEditable(true);
		btnBuscar.setEnabled(true);
		
	}
	void botonesEliminar() {
		txtnumRetiro.setEditable(true);
		btnBuscar.setEnabled(true);
		txtnumRetiro.requestFocus();
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
		txtnumRetiro.setText("");
		txtnumMatricula.setText("");
		txtFecha.setText("");
		txtHora.setText("");

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
	int leerCodigoRetiro()
	{
		try {
			int retiro = Integer.parseInt(txtnumRetiro.getText().trim());
			return retiro;
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	int leerCodigoMatricula() {
		try {
			int matricula = Integer.parseInt(txtnumMatricula.getText().trim());
			return matricula;
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	String leerFecha() {
		return txtFecha.getText().trim();
	}

	String leerHora() {
		return txtHora.getText().trim();
	}
	
	
	int confirmar(String s) {
		return JOptionPane.showConfirmDialog(this, s,"Alerta",0,1,null);
	}
	
}
