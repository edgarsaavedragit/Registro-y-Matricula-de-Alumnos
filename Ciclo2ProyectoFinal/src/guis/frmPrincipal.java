	package guis;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Toolkit;

public class frmPrincipal extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenu mnNewMenu_1;
	private JMenu mnNewMenu_2;
	private JMenu mnNewMenu_3;
	private JMenu mnNewMenu_4;
	
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmNewMenuItem_1;
	private JMenuItem mntmNewMenuItem_2;
	private JMenuItem mntmNewMenuItem_3;
	private JMenuItem mntmNewMenuItem_4;
	private JMenuItem mntmNewMenuItem_5;
	private JMenuItem mntmNewMenuItem_6;
	private JMenuItem mntmNewMenuItem_7;
	private JMenuItem mntmNewMenuItem_8;
	private JMenuItem mntmNewMenuItem_9;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmPrincipal frame = new frmPrincipal();
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
	public frmPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmPrincipal.class.getResource("/resources/imagen/libro-abierto.png")));
		setTitle("PLAN DE PROYECTO DE INVESTIGACIÓN APLICADA");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 655, 470);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu_4 = new JMenu("Archivo");
		menuBar.add(mnNewMenu_4);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Salir");
		mntmNewMenuItem_9.setIcon(new ImageIcon("C:\\Users\\Bryan\\Desktop\\PROYECTO_BASE\\Proyecto\\src\\resources\\images\\salir.png"));
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{ System.exit(0);}
		});
		mnNewMenu_4.add(mntmNewMenuItem_9);
		
		mnNewMenu = new JMenu("Mantenimiento");
		menuBar.add(mnNewMenu);
		
		mntmNewMenuItem = new JMenuItem("Alumnos");
		mntmNewMenuItem.setIcon(new ImageIcon(frmPrincipal.class.getResource("/resources/imagen/img-alumnos.png")));
		mntmNewMenuItem.addActionListener(this);
		mnNewMenu.add(mntmNewMenuItem);
		
		mntmNewMenuItem_1 = new JMenuItem("Cursos");
		mntmNewMenuItem_1.setIcon(new ImageIcon(frmPrincipal.class.getResource("/resources/imagen/img-cursos.png")));
		mntmNewMenuItem_1.addActionListener(this);
		mnNewMenu.add(mntmNewMenuItem_1);
		
		mnNewMenu_1 = new JMenu("Registro");
		menuBar.add(mnNewMenu_1);
		
		mntmNewMenuItem_2 = new JMenuItem("Matrícula");
		mntmNewMenuItem_2.setIcon(new ImageIcon(frmPrincipal.class.getResource("/resources/imagen/img-matriculas.png")));
		mntmNewMenuItem_2.addActionListener(this);
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		mntmNewMenuItem_3 = new JMenuItem("Retiro");
		mntmNewMenuItem_3.setIcon(new ImageIcon(frmPrincipal.class.getResource("/resources/imagen/img-retiro.png")));
		mntmNewMenuItem_3.addActionListener(this);
		mnNewMenu_1.add(mntmNewMenuItem_3);

		mnNewMenu_2 = new JMenu("Consulta");
		menuBar.add(mnNewMenu_2);
		
		mntmNewMenuItem_5 = new JMenuItem("Alumnos y Cursos");
		mntmNewMenuItem_5.setIcon(new ImageIcon(frmPrincipal.class.getResource("/resources/imagen/img-alumno-curso.png")));
		mntmNewMenuItem_5.addActionListener(this);
		mnNewMenu_2.add(mntmNewMenuItem_5);
		
		mntmNewMenuItem_4 = new JMenuItem("Matricula y Retiro");
		mntmNewMenuItem_4.setIcon(new ImageIcon(frmPrincipal.class.getResource("/resources/imagen/img-matricula-retiro.png")));
		mntmNewMenuItem_4.addActionListener(this);
		mnNewMenu_2.add(mntmNewMenuItem_4);
		
		mnNewMenu_3 = new JMenu("Reporte");
		menuBar.add(mnNewMenu_3);
		
		mntmNewMenuItem_6 = new JMenuItem("Alumnos con matrícula pendiente");
		mntmNewMenuItem_6.setIcon(new ImageIcon(frmPrincipal.class.getResource("/resources/imagen/alumnos-matriculas-pendiente.png")));
		mntmNewMenuItem_6.addActionListener(this);
		mnNewMenu_3.add(mntmNewMenuItem_6);
		
		mntmNewMenuItem_7 = new JMenuItem("Alumnos con matrícula vigente");
		mntmNewMenuItem_7.setIcon(new ImageIcon(frmPrincipal.class.getResource("/resources/imagen/img-matricula-vigente.png")));
		mntmNewMenuItem_7.addActionListener(this);
		mnNewMenu_3.add(mntmNewMenuItem_7);
		
		mntmNewMenuItem_8 = new JMenuItem("Alumnos matriculados por curso");
		mntmNewMenuItem_8.setIcon(new ImageIcon(frmPrincipal.class.getResource("/resources/imagen/img-alumnos-matriculados-curso.png")));
		mntmNewMenuItem_8.addActionListener(this);
		mnNewMenu_3.add(mntmNewMenuItem_8);
		
		mntmNewMenuItem_9 = new JMenuItem("Salir");
		mntmNewMenuItem_9.addActionListener(this);
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) 
		{ System.exit(0);}
		});
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Fondo con imagen escalada
        JLabel lblNewLabel = new JLabel();
        lblNewLabel.setBounds(0, 0, 800, 600);
        lblNewLabel.setIcon(new ImageIcon(frmPrincipal.class.getResource("/resources/imagen/img_principal.png")));
        contentPane.add(lblNewLabel);
    
	}
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == mntmNewMenuItem) {
			actionPerformedMntmNewMenuItem(e);
		}
		if (e.getSource() == mntmNewMenuItem_1) {
			actionPerformedMntmNewMenuItem_1(e);
		}
		if (e.getSource() == mntmNewMenuItem_2) {
			actionPerformedMntmNewMenuItem_2(e);
		}
		if (e.getSource() == mntmNewMenuItem_3) {
			actionPerformedMntmNewMenuItem_3(e);
		}
		if (e.getSource() == mntmNewMenuItem_4) {
			actionPerformedMntmNewMenuItem_4(e);
		}
		if (e.getSource() == mntmNewMenuItem_5) {
			actionPerformedMntmNewMenuItem_5(e);
		}
		if (e.getSource() == mntmNewMenuItem_6) {
			actionPerformedMntmNewMenuItem_6(e);
		}
		if (e.getSource() == mntmNewMenuItem_7) {
			actionPerformedMntmNewMenuItem_7(e);
		}
		if (e.getSource() == mntmNewMenuItem_8) {
			actionPerformedMntmNewMenuItem_8(e);
		}
	}
	protected void actionPerformedMntmNewMenuItem(ActionEvent e) {
		frmMantenimientoAlumnos obj=new frmMantenimientoAlumnos();
		obj.setLocationRelativeTo(this);
		obj.setVisible(true);
	}
	protected void actionPerformedMntmNewMenuItem_1(ActionEvent e) {
		frmMantenimientoCursos obj1=new frmMantenimientoCursos();
		obj1.setLocationRelativeTo(this);
		obj1.setVisible(true);
	}
	protected void actionPerformedMntmNewMenuItem_2(ActionEvent e) {
		frmMatricula obj2=new frmMatricula();
		obj2.setLocationRelativeTo(this);
		obj2.setVisible(true);
	}
	protected void actionPerformedMntmNewMenuItem_3(ActionEvent e) {
		frmRetiro obj3=new frmRetiro();
		obj3.setLocationRelativeTo(this);
		obj3.setVisible(true);
	}
	
	protected void actionPerformedMntmNewMenuItem_4(ActionEvent e) {
		frmConsultasMatriculaRetiros obj4=new frmConsultasMatriculaRetiros ();
		obj4.setLocationRelativeTo(this);
		obj4.setVisible(true);
	}
	protected void actionPerformedMntmNewMenuItem_5(ActionEvent e) {
		frmConsultaAlumnosyCursos obj5=new frmConsultaAlumnosyCursos();
		obj5.setLocationRelativeTo(this);
		obj5.setVisible(true);
	}
	
	protected void actionPerformedMntmNewMenuItem_6(ActionEvent e) {
		frmReporteAlumnoMatriculaPendiente obj6=new frmReporteAlumnoMatriculaPendiente();
		obj6.setLocationRelativeTo(this);
		obj6.setVisible(true);
	}
	
	protected void actionPerformedMntmNewMenuItem_7(ActionEvent e) {
		frmReporteAlumnoMatriculaVigente obj7=new frmReporteAlumnoMatriculaVigente();
		obj7.setLocationRelativeTo(this);
		obj7.setVisible(true);
	}
	
	protected void actionPerformedMntmNewMenuItem_8(ActionEvent e) {
		frmReporteAlumnoMatriculadoPorCurso obj8=new frmReporteAlumnoMatriculadoPorCurso();
		obj8.setLocationRelativeTo(this);
		obj8.setVisible(true);
	}
}

