package guis;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.ArrayList;
import clases.Matriculas;
import arreglos.ArregloReporteMatricula;
import arreglos.ArregloReporteMatricula;
import java.awt.Toolkit;
import java.awt.Color;


public class frmReporteAlumnoMatriculaPendiente extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel;
	private JButton btnCerrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmReporteAlumnoMatriculaPendiente frame = new frmReporteAlumnoMatriculaPendiente();
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
	public frmReporteAlumnoMatriculaPendiente() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmReporteAlumnoMatriculaPendiente.class.getResource("/resources/imagen/libro-abierto.png")));
		setTitle("¡Alumnos con Matriculas Pendientes!");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 694, 476);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(193, 193, 193));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(46, 137, 583, 269);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(96, 26, 87, 30);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Eliminar");
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setBounds(96, 74, 87, 30);
		contentPane.add(btnNewButton_1);
		
		lblNewLabel = new JLabel("Los Alumnos que aún no han completado su matrícula:");
		lblNewLabel.setForeground(new Color(128, 64, 64));
		lblNewLabel.setBounds(46, 120, 482, 14);
		contentPane.add(lblNewLabel);
		
		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setBackground(new Color(255, 128, 128));
		btnCerrar.setBounds(374, 55, 89, 32);
		contentPane.add(btnCerrar);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewButton_1) {
			actionPerformedBtnNewButton_1(e);
		}
		if (e.getSource() == btnNewButton) {
			actionPerformedBtnNewButton(e);
		}
	}
	//Boton Buscar
	protected void actionPerformedBtnNewButton(ActionEvent e)
	{	
		
		    ArregloReporteMatricula reporte = new ArregloReporteMatricula();
		    ArrayList<clases.Alumnos> lista = reporte.obtenerAlumnosConMatriculaPendiente();

		    textArea.setText(""); // Limpiar el área
		    textArea.append("Alumnos con matrícula pendiente:\n\n");

		    if (lista.isEmpty()) {
		        textArea.append("Todos los alumnos tienen matrícula vigente.");
		        return;
		    }

		    for (clases.Alumnos a : lista) {
		        textArea.append("Código\t : " + a.getCodAlumno() + "\n");
		        textArea.append("Nombre\t : " + a.getNombres() + " " + a.getApellidos() + "\n");
		        textArea.append("DNI\t : " + a.getDni() + "\n");
		        textArea.append("Edad\t : " + a.getEdad() + "\n");
		        textArea.append("Celular\t : " + a.getCelular() + "\n");
		        textArea.append("-----------------------------------------------------\n");
		    }
		}


	
	//Bton Eliminar
	protected void actionPerformedBtnNewButton_1(ActionEvent e)
	{
		if (textArea.getText().trim().isEmpty()) {
	        JOptionPane.showMessageDialog(this, "¡No hay nada para eliminar!.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
	    } else {
	        textArea.setText("Ya no se muestran resultados.");
	    }
		//--------------------
     textArea.setText("");
     
	}
}
