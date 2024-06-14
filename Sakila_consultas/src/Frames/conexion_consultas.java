package Frames;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Button;

public class conexion_consultas extends JFrame{
	
	private DefaultTableModel dtm;
	private Object[]o=new Object[4];
	

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					conexion_consultas window = new conexion_consultas();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
			//..............CONEXIÓN................

			public static final String URL = "jdbc:mysql://localhost:3306/sakila";
			public static final String USERNAME = "root";
			public static final String PASSWORD = "1234";
			
			//Variables para realizar consultas SQL y ejecutar codigo SQL---
			PreparedStatement ps;
			ResultSet rs;
			private JTextArea tMostrar;
			private JTable tC;
			//--------------------------------------------------------------
			
			public static Connection getConection()//los metodos pueden ser de tipo int, String, etc. En este caso será de tipo Connection
			{
			 Connection con = null;
			 
			 try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con= (Connection)DriverManager.getConnection(URL,USERNAME,PASSWORD);
				JOptionPane.showMessageDialog(null, "Conexion exitosa");
				
			 }catch(Exception e) {
				 System.out.println(e);
			 }
			return con;
			}

			//..............CONEXIÓN................
	public conexion_consultas() {
		initialize();
		dtm = (DefaultTableModel)tC.getModel();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 482, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Conectar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = null;//variable de tipo Connection
					con = getConection();	
					con.close();
				}catch(Exception e2) {
					System.out.println(e2);
				}
			}
		});
		btnNewButton.setBounds(366, 527, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton bCount = new JButton("Count");
		bCount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Connection con =null;
				try {
					
				con = getConection();
				ps = con.prepareStatement("select count(actor_id) from actor");
				//ps.setString(1, txtClv.getText());
				rs = ps.executeQuery();
				if(rs.next()) {
					tMostrar.setText(rs.getString("count(actor_id)"));
				}
				}catch(Exception e1){
					System.err.println(e1);
				}
				
			}
		});
		bCount.setBounds(34, 27, 151, 23);
		frame.getContentPane().add(bCount);
		
		tMostrar = new JTextArea();
		tMostrar.setBounds(217, 26, 184, 51);
		frame.getContentPane().add(tMostrar);
		
		JButton bCT = new JButton("Consulta Tabla");
		bCT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Connection con =null;
				try {
					
				con = getConection();
				ps = con.prepareStatement("SELECT * FROM actor");
				//ps.setString(1, txtClv.getText());
				
				rs = ps.executeQuery();
				String lista="";
				while(rs.next()) {
					o[0]=rs.getString("actor_id").trim();
					o[1]=rs.getString("first_name").trim();
					o[2]=rs.getString("last_name").trim();
					o[3]=rs.getString("last_update").trim();
					dtm.addRow(o);
				}
				tMostrar.setText(lista);
				}catch(Exception e1){
					System.err.println(e1);
				}
				
			}
		});
		bCT.setBounds(34, 61, 151, 23);
		frame.getContentPane().add(bCT);
		
		tC = new JTable();
		tC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tC.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"actor_id", "first_name", "last_name", "last_update"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tC.getColumnModel().getColumn(0).setPreferredWidth(119);
		tC.getColumnModel().getColumn(1).setPreferredWidth(134);
		tC.getColumnModel().getColumn(2).setPreferredWidth(138);
		tC.getColumnModel().getColumn(3).setPreferredWidth(150);
		tC.setBounds(10, 95, 445, 421);
		frame.getContentPane().add(tC);
		
		Button button = new Button("Join");
		button.setBounds(0, -2, 169, 23);
		frame.getContentPane().add(button);
		
		Button button_1 = new Button("Group by");
		button_1.setBounds(169, -2, 169, 23);
		frame.getContentPane().add(button_1);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Group_by g= new Group_by();
				g.setVisible(true);
				setVisible(false);
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Join j= new Join();
				j.setVisible(true);
				setVisible(false);
			}
		});
		
	}
}
