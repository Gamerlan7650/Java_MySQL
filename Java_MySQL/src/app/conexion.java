package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class conexion extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					conexion frame = new conexion();
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
	public static final String URL = "jdbc:mysql://localhost:3306/Escuela";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "1234";
	
	
	public conexion() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton bC = new JButton("CONECTAR");
		bC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = null;//variable de tipo conexion
					con = getConection();	
				    
					PreparedStatement ps;
					ResultSet res;
					ps = con.prepareStatement("SELECT * FROM persona");
					res = ps.executeQuery();
					
					if(res.next()) {
						JOptionPane.showMessageDialog(null, "Nombre: "+res.getString("nombre")+ "\nDomicilio:" +res.getString("domicilio"));
					}
					else {
						JOptionPane.showMessageDialog(null, "No existen datos");
					}
					
					con.close();
				}catch(Exception e2) {
					System.out.println(e2);
				}
				
				
			}
			
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
			
		});
		bC.setBounds(133, 111, 151, 23);
		contentPane.add(bC);
	}
}
