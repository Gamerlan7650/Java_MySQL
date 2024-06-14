package Frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Join extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Join frame = new Join();
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
	private JTextArea tJ;
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
	public Join() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1032, 542);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton bJ = new JButton("Join");
		bJ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con =null;
				try {
					
				con = getConection();
				ps = con.prepareStatement("select * from actor inner join film_actor on actor.actor_id=film_actor.actor_id");
				//ps.setString(1, txtClv.getText());
				rs = ps.executeQuery();
				String j="";
				while(rs.next()) {
					j=j+" || "+rs.getString("actor_id")+" || "+rs.getString("first_name")+" || "+rs.getString("last_name")+" || "+rs.getString("last_update")+" || "+rs.getString("actor_id")+" || "+rs.getString("film_id")+" || "+rs.getString("last_update")+"||"+"\n";
				}
				tJ.setText(j);
				}catch(Exception e1){
					System.err.println(e1);
				}
			}
		});
		bJ.setBounds(456, 30, 89, 23);
		contentPane.add(bJ);
		
		tJ = new JTextArea();
		tJ.setBounds(10, 64, 985, 428);
		contentPane.add(tJ);
	}

}
