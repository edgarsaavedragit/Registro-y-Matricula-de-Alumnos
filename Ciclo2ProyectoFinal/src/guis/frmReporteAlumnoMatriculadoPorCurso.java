package guis;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import clases.Matriculas;
import arreglos.ArregloReporteMatricula;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.awt.Toolkit;
import java.awt.Color;
import java.util.HashMap;

public class frmReporteAlumnoMatriculadoPorCurso extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JButton btnEliminar;
	private JButton btnBuscar;
	private JTextArea txtArea;
	private JLabel lblSubTitulo;
	private JButton btnCerrar;
	

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmReporteAlumnoMatriculadoPorCurso frame = new frmReporteAlumnoMatriculadoPorCurso();
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
	public frmReporteAlumnoMatriculadoPorCurso() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmReporteAlumnoMatriculadoPorCurso.class.getResource("/resources/imagen/libro-abierto.png")));
		setTitle("Alumnos Matriculados por Curso");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 598, 489);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(193, 193, 193));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 136, 527, 293);
		contentPane.add(scrollPane);
		
		txtArea = new JTextArea();
		scrollPane.setViewportView(txtArea);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(81, 64, 87, 30);
		contentPane.add(btnEliminar);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(81, 21, 87, 30);
		contentPane.add(btnBuscar);
		
		lblSubTitulo = new JLabel("Los Alumnos Matriculados en cada curso:");
		lblSubTitulo.setForeground(new Color(128, 64, 64));
		lblSubTitulo.setBounds(39, 109, 442, 29);
		contentPane.add(lblSubTitulo);
		
		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setBackground(new Color(255, 128, 128));
		btnCerrar.setBounds(331, 51, 89, 32);
		contentPane.add(btnCerrar);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBuscar) {
			actionPerformedBtnNewButton_1(e);
		}
		if (e.getSource() == btnEliminar) {
			actionPerformedBtnNewButton(e);
		}
	}
	
	//Boton Buscar---------------------------------
	protected void actionPerformedBtnNewButton_1(ActionEvent e)
	{
		    ArregloReporteMatricula reporte = new ArregloReporteMatricula();

		    // lista de alumnos
		    HashMap<String, ArrayList<String>> alumnosPorCurso = reporte.obtenerAlumnosPorCurso();
		    txtArea.setText("");
		    txtArea.append("Alumnos matriculados por curso:\n\n");
		    for (String curso : alumnosPorCurso.keySet()) {
		        txtArea.append("Curso: " + curso + "\n");

		        ArrayList<String> alumnos = alumnosPorCurso.get(curso);
		        if (alumnos.isEmpty()) {
		            txtArea.append("   (Sin alumnos matriculados)\n");
		        } else {
		            for (String alumno : alumnos) {
		                txtArea.append("   - " + alumno + "\n");
		            }
		        }
		        txtArea.append("\n");
		    }
	}
	//Boton Borrar---------------------------------------
		protected void actionPerformedBtnNewButton(ActionEvent e) 
		{
			  if (txtArea.getText().trim().isEmpty()) {
			        JOptionPane.showMessageDialog(this, "¡No hay nada para eliminar!.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
			    } else {
			        txtArea.setText("Ya no se muestran resultados.");
			    }
			  
			  
			txtArea.setText("");

		}
}

