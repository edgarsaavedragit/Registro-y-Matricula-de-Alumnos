package guis;

import arreglos.ArregloAlumnos;
import clases.Alumnos;

//import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Color;

public class frmMantenimientoAlumnos extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JLabel lblNombre;
	private JLabel lblCodigo;
	private JLabel lblDni;
	private JLabel lblPeso;
	private JLabel lblEstatura;
	private JTextField txtCodigoAlumno;
	private JTextField txtNombresAlumno;
	private JTextField txtDniAlumno;
	private JTextField txtEdadAlumno;
	private JTextField txtCelularAlumno;
	private JButton btnBuscar;
	private JButton btnOK;
	private JLabel lblApellidos;
	private JTextField txtApellidosAlumno;
	private JButton btnOpciones;
	private JButton btnAdicionar;
	private JButton btnConsultar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JScrollPane scrollPane;
	private JTable tblAlumno;
	private JButton btnCerrar;
	
	private DefaultTableModel modelo;

	// TIPO DE OPERACIONES
	private int tipoOperacion;
	// CONSTANTES PARA LOS TIPOS DE OPERACIONES
	public final static int ADICIONAR = 0;
	public final static int CONSULTAR = 1;
	public final static int MODIFICAR = 2;
	public final static int ELIMINAR = 3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmMantenimientoAlumnos frame = new frmMantenimientoAlumnos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frmMantenimientoAlumnos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmMantenimientoAlumnos.class.getResource("/resources/imagen/libro-abierto.png")));
		setTitle("Mantenimiento Alumnos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 657);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(193, 193, 193));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNombre = new JLabel("Nombres");
		lblNombre.setBounds(10, 47, 70, 23);
		contentPane.add(lblNombre);

		lblCodigo = new JLabel("Código");
		lblCodigo.setBounds(10, 22, 110, 23);
		contentPane.add(lblCodigo);

		lblDni = new JLabel("DNI");
		lblDni.setBounds(20, 160, 70, 23);
		contentPane.add(lblDni);

		lblPeso = new JLabel("Edad");
		lblPeso.setBounds(10, 108, 70, 23);
		contentPane.add(lblPeso);

		lblEstatura = new JLabel("Celular");
		lblEstatura.setBounds(10, 133, 70, 23);
		contentPane.add(lblEstatura);

		txtCodigoAlumno = new JTextField();
		txtCodigoAlumno.setEditable(false);
		txtCodigoAlumno.setColumns(10);
		txtCodigoAlumno.setBounds(90, 22, 86, 23);
		contentPane.add(txtCodigoAlumno);

		txtNombresAlumno = new JTextField();
		txtNombresAlumno.setEditable(false);
		txtNombresAlumno.setColumns(10);
		txtNombresAlumno.setBounds(90, 47, 251, 23);
		contentPane.add(txtNombresAlumno);

		txtDniAlumno = new JTextField();
		txtDniAlumno.setColumns(10);
		txtDniAlumno.setBounds(90, 160, 86, 23);
		contentPane.add(txtDniAlumno);

		txtEdadAlumno = new JTextField();
		txtEdadAlumno.setEditable(false);
		txtEdadAlumno.setColumns(10);
		txtEdadAlumno.setBounds(90, 108, 156, 23);
		contentPane.add(txtEdadAlumno);

		txtCelularAlumno = new JTextField();
		txtCelularAlumno.setEditable(false);
		txtCelularAlumno.setColumns(10);
		txtCelularAlumno.setBounds(90, 133, 156, 23);
		contentPane.add(txtCelularAlumno);

		btnBuscar = new JButton("Buscar 🔍");
		btnBuscar.addActionListener(this);
		btnBuscar.setEnabled(false);
		btnBuscar.setBounds(240, 22, 101, 23);
		contentPane.add(btnBuscar);

		btnOK = new JButton("OK");
		btnOK.addActionListener(this);
		btnOK.setBounds(241, 160, 100, 23);
		contentPane.add(btnOK);

		lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(10, 78, 70, 23);
		contentPane.add(lblApellidos);

		txtApellidosAlumno = new JTextField();
		txtApellidosAlumno.setEditable(false);
		txtApellidosAlumno.setColumns(10);
		txtApellidosAlumno.setBounds(90, 78, 251, 23);
		contentPane.add(txtApellidosAlumno);

		btnOpciones = new JButton("Opciones");
		btnOpciones.addActionListener(this);
		btnOpciones.setBounds(551, 33, 121, 123);
		contentPane.add(btnOpciones);

		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(this);
		btnAdicionar.setBounds(696, 33, 121, 23);
		contentPane.add(btnAdicionar);

		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(this);
		btnConsultar.setBounds(696, 67, 121, 23);
		contentPane.add(btnConsultar);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(this);
		btnModificar.setBounds(696, 99, 121, 23);
		contentPane.add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(696, 133, 121, 23);
		contentPane.add(btnEliminar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 215, 864, 392);
		contentPane.add(scrollPane);

		tblAlumno = new JTable();
		tblAlumno.setEnabled(false);
		tblAlumno.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblAlumno);
		// CODIGO PARA CARGAR EN EL JTABLE
		modelo = new DefaultTableModel();
		modelo.addColumn("Codigo");
		modelo.addColumn("Nombres");
		modelo.addColumn("Apellidos");
		modelo.addColumn("DNI");
		modelo.addColumn("Edad");
		modelo.addColumn("Celular");
		modelo.addColumn("Estado");

		tblAlumno.setModel(modelo);
		
		btnCerrar = new JButton("Cerrar");
		btnCerrar.setBackground(new Color(255, 128, 128));
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setBounds(696, 163, 121, 23);
		contentPane.add(btnCerrar);
		ajustarAnchoColumnas();
		listar();
		habilitarEntradas(false);
		habilitarBotones(true);

	}

	
	ArregloAlumnos aa = new ArregloAlumnos();
	

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnOK) {
			actionPerformedBtnOK(e);
		}
		if (e.getSource() == btnBuscar) {
			actionPerformedBtnBuscar(e);
		}
		if (e.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(e);
		}
		if (e.getSource() == btnModificar) {
			actionPerformedBtnModificar(e);
		}
		if (e.getSource() == btnConsultar) {
			actionPerformedBtnConsultar(e);
		}
		if (e.getSource() == btnAdicionar) {
			actionPerformedBtnAdicionar(e);
		}
		if (e.getSource() == btnOpciones) {
			actionPerformedBtnOpciones(e);
		}
	}

	protected void actionPerformedBtnBuscar(ActionEvent e) {
		consultarAlumno();
	}

	protected void actionPerformedBtnOK(ActionEvent e) {
		switch (tipoOperacion) {
		case ADICIONAR:
			adicionarAlumno();
			break;
		case CONSULTAR:
			consultarAlumno();
			break;

		case MODIFICAR:
			modificarAlumno();
			break;
		
	   case ELIMINAR:
		  eliminarAlumno();
		 
		}
	}

	protected void actionPerformedBtnOpciones(ActionEvent e) {
		txtCodigoAlumno.setText("");
		txtNombresAlumno.setText("");
		txtApellidosAlumno.setText("");
		txtEdadAlumno.setText("");
		txtCelularAlumno.setText("");
		txtDniAlumno.setText("");
		txtCodigoAlumno.setEditable(false);
		habilitarEntradas(false);
		habilitarBotones(true);

	}

	protected void actionPerformedBtnAdicionar(ActionEvent e) {
		tipoOperacion = ADICIONAR;
		txtCodigoAlumno.setText("" + aa.codigoCorrelativo());
		habilitarEntradas(true);
		habilitarBotones(false);
		txtNombresAlumno.requestFocus();
	}

	protected void actionPerformedBtnConsultar(ActionEvent e) {
		tipoOperacion = CONSULTAR;
		txtCodigoAlumno.setEditable(true);
		habilitarBotones(false);
		txtCodigoAlumno.requestFocus();
	}

	protected void actionPerformedBtnModificar(ActionEvent e) {
		tipoOperacion = MODIFICAR;
		txtCodigoAlumno.setEditable(true);
		habilitarBotones(false);
		txtCodigoAlumno.requestFocus();
	}

	protected void actionPerformedBtnEliminar(ActionEvent e) {
		tipoOperacion=ELIMINAR;
		txtCodigoAlumno.setEditable(true);
		habilitarBotones(false);
		txtCodigoAlumno.requestFocus();
	}

	// METODOS TIPO VOID SIN
	// PARAMETROS-------------------------------------------------------------------------
	void ajustarAnchoColumnas() {
		TableColumnModel tcm = tblAlumno.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(10)); // codigo
		tcm.getColumn(1).setPreferredWidth(anchoColumna(10)); // Nombres
		tcm.getColumn(2).setPreferredWidth(anchoColumna(10)); // DNI
		tcm.getColumn(3).setPreferredWidth(anchoColumna(10)); // edad
		tcm.getColumn(4).setPreferredWidth(anchoColumna(10)); // celular
		tcm.getColumn(5).setPreferredWidth(anchoColumna(10)); // estado
		tcm.getColumn(6).setPreferredWidth(anchoColumna(10)); // estado

	}

	void listar() {
		Alumnos c;
		modelo.setRowCount(0);
		for (int i = 0; i < aa.tamano(); i++) {
			c = aa.obtener(i);
			String estado = obtenerEstado(c.getEstado());
			Object[] fila = { c.getCodAlumno(), c.getNombres(), c.getApellidos(), c.getDni(), c.getEdad(),
					c.getCelular(), estado };
			modelo.addRow(fila);
		}
	}

	String obtenerEstado(int estado)
	{
		String nuevoEstado = "";
		
		switch (estado) {
		case 0: {
			nuevoEstado = "Registrado";
			break;
		}
		case 1: {
			nuevoEstado = "Matriculado";
			break;
		}
		case 2: {
			nuevoEstado = "Retirado";
			break;
		}
		default:
			nuevoEstado = "Sin asignar";
		}
		
		return nuevoEstado;
	}
	void adicionarAlumno() {
		int codigo = leerCodigo();

		String nombres = leerNombres();
		String apellidos = leerApellidos();
		int edad = leerEdad();
		int celular = leerCelular();
		String dni = leerDni();
		int estado = 0;

		if (!validarCelular(celular) || !validarEdad(edad) || !validarEstado(estado) || !validarDNI(dni)
				|| (isNullOrEmpty(nombres) && isNullOrEmpty(apellidos))) {

			mensaje("Ingrese correctamente todos los campos");

		}
		else if (aa.buscar(dni) != null) {
			error("DNI ya registrado, ingresar otro ", txtDniAlumno);
		} else {
			try {

				Alumnos nuevo = new Alumnos(codigo, nombres, apellidos, dni, edad, celular, estado);
				aa.adicionar(nuevo);
				listar();
				txtCodigoAlumno.setText("" + aa.codigoCorrelativo());
				txtNombresAlumno.setText("");
				txtApellidosAlumno.setText("");
				txtDniAlumno.setText("");
				txtEdadAlumno.setText("");
				txtCelularAlumno.setText("");
				txtNombresAlumno.requestFocus();
			} catch (Exception e) {
				// TODO: handle exception
				error("Ocurrió un error al registrar ", txtDniAlumno);
			}

		}
	}

	void consultarAlumno() {
		try {
			int codigo = leerCodigo();
			Alumnos c = aa.buscar(codigo);
			if (c != null) {
				txtNombresAlumno.setText(c.getNombres());
				txtApellidosAlumno.setText(c.getApellidos());
				txtEdadAlumno.setText(Integer.toString(c.getEdad()));
				txtCelularAlumno.setText(Integer.toString(c.getCelular()));
				txtDniAlumno.setText(c.getDni());
				if (tipoOperacion == MODIFICAR) {
					habilitarEntradas(true);
					txtCodigoAlumno.setEditable(false);
					btnBuscar.setEnabled(false);
					btnOK.setEnabled(true);
					txtNombresAlumno.requestFocus();
				}
				if (tipoOperacion == ELIMINAR) {
					txtCodigoAlumno.setEditable(false);
					btnBuscar.setEnabled(false);
					btnOK.setEnabled(true);
				}
			} else {
				error("El codigo " + codigo + " no existe", txtCodigoAlumno);
			}
		} catch (Exception e) {
			// TODO: handle exception
			error("Ingresar el codigo correcto ", txtCodigoAlumno);
		}
	}

	void modificarAlumno()
	{
		int codigo = leerCodigo();
		Alumnos c = aa.buscar(codigo);
		if (c != null) {
			
			String nombres = leerNombres();
			String apellidos = leerApellidos();
			int edad = leerEdad();
			int celular = leerCelular();
			String dni = leerDni();
			int estado = 0;
			
			if (isNullOrEmpty(nombres) && isNullOrEmpty(apellidos) && !validarCelular(celular)
					&& !validarDNI(dni) && !validarEstado(estado) && !validarEdad(edad)) {
				
				mensaje("Debe completar todos los campos");

			}
			else
			{
				try {
					c.setNombres(nombres);
					c.setApellidos(apellidos);
					c.setEdad(edad);
					c.setCelular(celular);
					c.setDni(dni);
					c.setEstado(estado);
					aa.actualizarAlumno();
					listar();
					txtNombresAlumno.requestFocus();
				} catch (Exception e) {
					// TODO: handle exception
					error("Ingrese el dni correcto ", txtDniAlumno);
				}
			}
		
		}
	}
	

	
	void eliminarAlumno() {
		try {
			int codigo = leerCodigo();
			Alumnos c = aa.buscar(codigo);
			if (c != null) {
				if (c.getEstado() == 0) {
					int ok = confirmar("Deseas eliminar el registro?");
					if (ok == 0) {
						aa.eliminar(c);
						listar();
						btnOK.setEnabled(false);
					}
				}
				else
				{
					error("El alumno " + c.getNombres() + " " + c.getApellidos() +" no puede ser eliminado, verifique su estado.", txtCodigoAlumno);
				}
			} else
				error("El codigo " + codigo + " no existe", txtCodigoAlumno);
		} catch (Exception e) {
			// TODO: handle exception
			error("Ingrese el codigo correcto", txtCodigoAlumno);
		}
	}
	
	// METODOS TIPO VOID CON
	// PARAMETROS-------------------------------------------------------------------------
	void habilitarEntradas(boolean sino) {
		txtNombresAlumno.setEditable(sino);
		if (tipoOperacion == ADICIONAR)
			txtDniAlumno.setEditable(sino);
		txtNombresAlumno.setEditable(sino);
		txtApellidosAlumno.setEditable(sino);
		txtEdadAlumno.setEditable(sino);
		txtCelularAlumno.setEditable(sino);
	}

	void habilitarBotones(boolean sino) {
		if (tipoOperacion == ADICIONAR)
			btnOK.setEnabled(!sino);
		else {
			btnBuscar.setEnabled(!sino);
			btnOK.setEnabled(false);
		}
		btnAdicionar.setEnabled(sino);
		btnConsultar.setEnabled(sino);
		btnModificar.setEnabled(sino);
		btnEliminar.setEnabled(sino);
		btnOpciones.setEnabled(!sino);
	}

	void mensaje(String s) {
		JOptionPane.showMessageDialog(this, s, "Informacion", 0);
	}

	void error(String s, JTextField txt) {
		mensaje(s);
		txt.setText("");
		txt.requestFocus();
	}

	// METODOS QUE RETORNA VALOR (SIN PARAMETROS
	// )---------------------------------------------------------------
	int leerCodigo() {
		return Integer.parseInt(txtCodigoAlumno.getText().trim());
	}

	String leerNombres() {
		return txtNombresAlumno.getText().trim();
	}

	String leerApellidos() {
		return txtApellidosAlumno.getText().trim();
	}

	int leerEdad() {
		try {
			int edad = Integer.parseInt(txtEdadAlumno.getText().trim());
			return edad;
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	int leerCelular() {
		try {
			int celular = Integer.parseInt(txtCelularAlumno.getText().trim());
			return celular;
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	String leerDni() {
		try {
			int dni = Integer.parseInt(txtDniAlumno.getText().trim());
			return "" + dni;
		} catch (NumberFormatException e) {
			return "";
		}
	}
	
	// METODOS QUE RETORNAN VALOR (CON
	// PARAMETROS)------------------------------------------------------------------
	int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}

	double ajustar(double numero) {
		return (int) (numero * 10) / 10.0;
	}

	int confirmar(String s) {
		return JOptionPane.showConfirmDialog(this, s, "Alerta", 0, 1, null);
	}
	
	public boolean isNullOrEmpty(String str) {
	    return str == null || str.isEmpty();
	}

	public boolean validarEdad(int edad) {
		return edad >= 5;
	}
	
	public boolean validarCelular(int celular)
	{
		return String.valueOf(celular).length() == 9 && String.valueOf(celular).startsWith("9") && !String.valueOf(celular).startsWith("0") && celular != 0;
	}
	
	public boolean validarDNI(String dni)
	{
		return String.valueOf(dni).length() == 8 && !String.valueOf(dni).startsWith("0") && dni != "";
	}
	
	public boolean validarEstado(int estado)
	{
		return estado >=0 && estado <=2;
	}

}
