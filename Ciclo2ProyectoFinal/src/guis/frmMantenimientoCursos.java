package guis;
import arreglos.ArregloCursos;
import arreglos.ArregloMatriculas;
import clases.Cursos;

import java.awt.BorderLayout;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Toolkit;
import java.awt.Color;

public class frmMantenimientoCursos extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JLabel lblCodigoCurso;
	private JLabel lblAsignatura;
	private JLabel lblCiclo;
	private JLabel lblCreditos;
	private JLabel lblHoras;
	private JTextField txtCodigoCurso;
	private JButton btnBuscar;
	private JTextField txtAsignatura;
	private JTextField txtCiclo;
	private JTextField txtCreditos;
	private JTextField txtHoras;
	private JButton btnOK;
	private JButton btnOpciones;
	private JButton btnAdicionar;
	private JButton btnConsultar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnCerrar;
	
	private DefaultTableModel modelo;

	// TIPO DE OPERACIONES
	private int tipoOperacion;
	// CONSTANTES PARA LOS TIPOS DE OPERACIONES
	public final static int ADICIONAR = 0;
	public final static int CONSULTAR = 1;
	public final static int MODIFICAR = 2;
	public final static int ELIMINAR = 3;
	private JScrollPane scrollPane;
	private JTable tblCursos;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmMantenimientoCursos frame = new frmMantenimientoCursos();
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
	public frmMantenimientoCursos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmMantenimientoCursos.class.getResource("/resources/imagen/libro-abierto.png")));
		setTitle("Mantenimiento | Cursos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 709, 595);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(193, 193, 193));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblCodigoCurso = new JLabel("Codigo");
		lblCodigoCurso.setBounds(10, 11, 110, 23);
		contentPane.add(lblCodigoCurso);
		
		lblAsignatura = new JLabel("Asignatura");
		lblAsignatura.setBounds(10, 36, 70, 23);
		contentPane.add(lblAsignatura);
		
		lblCiclo = new JLabel("Ciclo");
		lblCiclo.setBounds(10, 67, 70, 23);
		contentPane.add(lblCiclo);
		
		lblCreditos = new JLabel("Créditos");
		lblCreditos.setBounds(10, 97, 70, 23);
		contentPane.add(lblCreditos);
		
		lblHoras = new JLabel("Horas");
		lblHoras.setBounds(10, 122, 70, 23);
		contentPane.add(lblHoras);
		
		txtCodigoCurso = new JTextField();
		txtCodigoCurso.setEditable(false);
		txtCodigoCurso.setColumns(10);
		txtCodigoCurso.setBounds(90, 11, 86, 23);
		contentPane.add(txtCodigoCurso);
		
		btnBuscar = new JButton("Buscar 🔍");
		btnBuscar.addActionListener(this);
		btnBuscar.setEnabled(false);
		btnBuscar.setBounds(240, 11, 101, 23);
		contentPane.add(btnBuscar);
		
		txtAsignatura = new JTextField();
		txtAsignatura.setEditable(false);
		txtAsignatura.setColumns(10);
		txtAsignatura.setBounds(90, 36, 251, 23);
		contentPane.add(txtAsignatura);
		
		txtCiclo = new JTextField();
		txtCiclo.setEditable(false);
		txtCiclo.setColumns(10);
		txtCiclo.setBounds(90, 67, 251, 23);
		contentPane.add(txtCiclo);
		
		txtCreditos = new JTextField();
		txtCreditos.setEditable(false);
		txtCreditos.setColumns(10);
		txtCreditos.setBounds(90, 97, 251, 23);
		contentPane.add(txtCreditos);
		
		txtHoras = new JTextField();
		txtHoras.setEditable(false);
		txtHoras.setColumns(10);
		txtHoras.setBounds(90, 122, 156, 23);
		contentPane.add(txtHoras);
		
		btnOK = new JButton("OK");
		btnOK.addActionListener(this);
		btnOK.setBounds(241, 149, 100, 23);
		contentPane.add(btnOK);
		
		btnOpciones = new JButton("Opciones");
		btnOpciones.addActionListener(this);
		btnOpciones.setEnabled(false);
		btnOpciones.setBounds(403, 36, 121, 123);
		contentPane.add(btnOpciones);
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(this);
		btnAdicionar.setEnabled(true);
		btnAdicionar.setBounds(548, 36, 121, 23);
		contentPane.add(btnAdicionar);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(this);
		btnConsultar.setEnabled(true);
		btnConsultar.setBounds(548, 70, 121, 23);
		contentPane.add(btnConsultar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(this);
		btnModificar.setEnabled(true);
		btnModificar.setBounds(548, 102, 121, 23);
		contentPane.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setEnabled(true);
		btnEliminar.setBounds(548, 136, 121, 23);
		contentPane.add(btnEliminar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 212, 673, 333);
		contentPane.add(scrollPane);
		
		tblCursos = new JTable();
		tblCursos.setEnabled(false);
		tblCursos.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblCursos);
		// CODIGO PARA CARGAR EN EL JTABLE
		modelo = new DefaultTableModel();
		modelo.addColumn("Codigo");	
		modelo.addColumn("Asignatura");
		modelo.addColumn("Ciclo");	
		modelo.addColumn("Créditos");	
		modelo.addColumn("Horas");	
				
		tblCursos.setModel(modelo);
		
		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setBackground(new Color(255, 128, 128));
		btnCerrar.setBounds(548, 167, 121, 23);
		contentPane.add(btnCerrar);
		ajustarAnchoColumnas();
		listar();
		habilitarEntradas(false);
		habilitarBotones(true);
		
	}
	
	//INSTANCIANDO AL OBJETO
	ArregloCursos ap=new ArregloCursos();
	ArregloMatriculas am = new ArregloMatriculas();
	
	
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBuscar) {
			actionPerformedBtnBuscar(e);
		}
		if (e.getSource() == btnOK) {
			actionPerformedBtnOK(e);
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
		consultarCursos();
	}
	protected void actionPerformedBtnOK(ActionEvent e) {
		switch(tipoOperacion) {
		case ADICIONAR:
			adicionarProducto();
			break;
		case CONSULTAR:
			consultarCursos();
			break;
		case MODIFICAR:
			modificarProducto();
			break;
		case ELIMINAR:
			eliminarCurso();		
		}
	}
	protected void actionPerformedBtnOpciones(ActionEvent e) {
		txtCodigoCurso.setText("");
		txtAsignatura.setText("");
		txtCiclo.setText("");
		txtCreditos.setText("");
		txtHoras.setText("");
		txtCodigoCurso.setEditable(false);
		habilitarEntradas(false);
		habilitarBotones(true);
	}
	protected void actionPerformedBtnAdicionar(ActionEvent e) {
		tipoOperacion = ADICIONAR;
		txtCodigoCurso.setText(""+ap.codigoCorrelativo());
		habilitarEntradas(true);
		habilitarBotones(false);
		txtAsignatura.requestFocus();
	}
	protected void actionPerformedBtnConsultar(ActionEvent e) {
		tipoOperacion = CONSULTAR;
		txtCodigoCurso.setEditable(true);
		habilitarBotones(false);
		txtCodigoCurso.requestFocus();
	}
	protected void actionPerformedBtnModificar(ActionEvent e) {
		tipoOperacion=MODIFICAR;
		txtCodigoCurso.setEditable(true);
		habilitarBotones(false);
		txtCodigoCurso.requestFocus();
	}
	protected void actionPerformedBtnEliminar(ActionEvent e) {
		tipoOperacion=ELIMINAR;
		txtCodigoCurso.setEditable(true);
		habilitarBotones(false);
		txtCodigoCurso.requestFocus();
	}


	//METODOS TIPO VOID SIN PARAMETROS
	//-------------------------------------------------------------------------------------------------
	void ajustarAnchoColumnas() {
		TableColumnModel tcm=tblCursos.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(10)); 
		tcm.getColumn(1).setPreferredWidth(anchoColumna(30)); 
		tcm.getColumn(2).setPreferredWidth(anchoColumna(10)); 
		tcm.getColumn(3).setPreferredWidth(anchoColumna(10)); 
		tcm.getColumn(4).setPreferredWidth(anchoColumna(10)); 
		
	}

	
	void listar() {
		Cursos p;
		modelo.setRowCount(0);
		for(int i=0; i<ap.tamano(); i++) {
			p=ap.obtener(i);
			Object[] fila = {p.getCodigoCurso(),
							 p.getAsignatura(),
							 p.getCiclo(),
							 p.getCreditos(),
							 p.getHoras()	
			
			};
			modelo.addRow(fila);
		}
	}
	
	
	void adicionarProducto() {

		try {

			validarEntradas();

			int codigoCurso = leerCodigo();
			String asignatura = leerAsignatura();
			int ciclo = leerCiclo();
			int creditos = leerCreditos();
			int horas = leerHoras();

			if (btnAdicionar.isEnabled() == false) {
				Cursos nuevo = new Cursos(codigoCurso, asignatura, ciclo, creditos, horas);
				ap.adicionar(nuevo);
				limpiarDatos();
				txtCodigoCurso.setText("" + ap.codigoCorrelativo());
			}

			listar();


		} catch (RuntimeException e) {
			mensaje(e.getMessage());
		}
	}
	
	void limpiarDatos()
	{
		txtAsignatura.setText("");
		txtCiclo.setText("");
		txtCreditos.setText("");
		txtHoras.setText("");
	}
	
	String validarEntradas()
	{
		String codigoCurso = txtCodigoCurso.getText().trim();
		String asignatura = txtAsignatura.getText().trim();
		String ciclo = txtCiclo.getText().trim();
		String creditos = txtCreditos.getText().trim();
		String horas = txtHoras.getText().trim();
		
		if (isNullOrEmpty(codigoCurso)) throw new RuntimeException("Código de Curso incorrecto");
		if (isNullOrEmpty(asignatura)) throw new RuntimeException("Ingrese el nombre de la asignatura");
		if (isNullOrEmpty(ciclo)) throw new RuntimeException("Ingrese el ciclo correcto");
		if (isNullOrEmpty(creditos)) throw new RuntimeException("Ingrese la cantidad de creditos correctos");
		if (isNullOrEmpty(horas)) throw new RuntimeException("Ingrese la cantidad de horas correctas");
		
		 return "OK";
	} 
	
	public boolean isNullOrEmpty(String str) {
	    return str == null || str.isEmpty();
	}
	
	void consultarCursos() {
		try {
			int codigo=leerCodigo();
			Cursos p=ap.buscar(codigo);
			if(p !=null) {
				txtAsignatura.setText(p.getAsignatura());
				txtCiclo.setText(Integer.toString(p.getCiclo()));
				txtCreditos.setText(Integer.toString(p.getCreditos()));
				txtHoras.setText(Integer.toString(p.getHoras()));
				if(tipoOperacion==MODIFICAR) {
					habilitarEntradas(true);
					txtCodigoCurso.setEditable(false);
					btnBuscar.setEnabled(false);
					btnOK.setEnabled(true);
					txtAsignatura.requestFocus();
				}
				if(tipoOperacion==ELIMINAR) {
					txtCodigoCurso.setEditable(false);
					btnBuscar.setEnabled(false);
					btnOK.setEnabled(true);
				}				
			}else {
				error("El codigo " +codigo+ " no existe", txtCodigoCurso);
			}
		} catch (Exception e) {
			// TODO: handle exception
			error("Ingresar el codigo correcto", txtCodigoCurso);
		}
	}
	
	
	void modificarProducto() {
		try {

			validarEntradas();

			int codigoCurso = leerCodigo();
			String asignatura = leerAsignatura();
			int ciclo = leerCiclo();
			int creditos = leerCreditos();
			int horas = leerHoras();

			if (btnModificar.isEnabled() == false) {
				Cursos p = ap.buscar(codigoCurso);
				p.setAsignatura(asignatura);
				p.setCiclo(ciclo);
				p.setCreditos(creditos);
				p.setHoras(horas);
				ap.actualizarCursos();
				
				txtAsignatura.requestFocus();
			}
			listar();
			habilitarEntradas(false);

		} catch (RuntimeException e) {
			mensaje(e.getMessage());
		}
	}

	void eliminarCurso() {
		try {
			int codigo = leerCodigo();
			Cursos p = ap.buscar(codigo);
			if (p != null) {
				int ok = confirmar("Deseas eliminar el registro?");
				if (am.existeCurso(codigo)) {
					error("El curso " + p.getAsignatura() + " esta asociado a un alumno matriculado", txtCodigoCurso);
				} else {
					if (ok == 0) {
						ap.eliminar(p);
						listar();
						btnOK.setEnabled(false);
					}
				}
			} else
				error("El codigo " + codigo + " no existe", txtCodigoCurso);
		} catch (Exception e) {
			// TODO: handle exception
			error("Ingrese el codigo correcto ", txtCodigoCurso);
		}
	}
	

	
	//METODOS TIPO VOID CON PARAMETROS
	
	void habilitarEntradas(boolean sino) {
		txtAsignatura.setEditable(sino);
		if(tipoOperacion == ADICIONAR)
			txtCodigoCurso.setEditable(!sino);
		txtAsignatura.setEditable(sino);
		txtCiclo.setEditable(sino);
		txtCreditos.setEditable(sino);
		txtHoras.setEditable(sino);
	}
	
	void habilitarBotones(boolean sino) {
		if(tipoOperacion==ADICIONAR)
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
		JOptionPane.showMessageDialog(this, s,"Informacion",0);
	}
	
	void error(String s, JTextField txt) {
		mensaje(s);
		txt.setText("");
		txt.requestFocus();
	}
	
	
	
	//METODOS QUE RETORNAN VALOR SIN PARAMETROS
	int leerCodigo() {
		return Integer.parseInt(txtCodigoCurso.getText().trim());
	}
	
	String leerAsignatura() {
		return txtAsignatura.getText().trim();
	}

	int leerCiclo() {
		return Integer.parseInt(txtCiclo.getText().trim());
	} 
	int leerCreditos() {
		return Integer.parseInt(txtCreditos.getText().trim());
	}
	int leerHoras() {
		return Integer.parseInt(txtHoras.getText().trim());
	}
	
	
	
	//METODOS QUE RETORNAN VALOR ( CON PARAMETROS )
	int anchoColumna(int porcentaje) {
		return porcentaje *scrollPane.getWidth()/100;
	}
	double ajustar(double numero) {
		return (int) (numero *10)/10.0;
	}
	int confirmar(String s) {
		return JOptionPane.showConfirmDialog(this, s,"Alerta",0,1,null);
	}

}






















