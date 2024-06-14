package Frames;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Group_by extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Group_by frame = new Group_by();
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
	
	//..............CONEXIÓN................

	public static final String URL = "jdbc:mysql://localhost:3306/sakila";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "1234";
	
	//Variables para realizar consultas SQL y ejecutar codigo SQL---
	PreparedStatement ps;
	ResultSet rs;
	private JTextArea tMostrar;
	private JTable tC;
	private JTextArea tG;
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
	public Group_by() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 553, 557);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton bG = new JButton("Group_by");
		bG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con =null;
				try {
					
				con = getConection();
				ps = con.prepareStatement("select last_update from actor group by last_update");
				//ps.setString(1, txtClv.getText());
				rs = ps.executeQuery();
				if(rs.next()) {
					tG.setText(rs.getString("last_update"));
				}
				}catch(Exception e1){
					System.err.println(e1);
				}
			}
		});
		bG.setBounds(206, 24, 89, 23);
		contentPane.add(bG);
		
		tG = new JTextArea();
		tG.setBounds(10, 60, 517, 447);
		contentPane.add(tG);
	}

}
