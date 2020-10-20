package Avtobus;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;


import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
public class Linija extends JFrame {

	private JPanel contentPane;
    Connection con;
    PreparedStatement pst;
    


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Linija frame = new Linija();
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
	public Linija() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 290, 307);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(255, 204, 102));
		panel.setBackground(new Color(204, 0, 0));
		panel.setBounds(0, 0, 274, 49);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Додај линија");
		lblNewLabel.setForeground(new Color(255, 204, 102));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(63, 11, 151, 27);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(10, 71, 254, 197);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Линија бр");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(32, 31, 62, 14);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Дата");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(48, 85, 46, 14);
		panel_1.add(lblNewLabel_2);
		
		JComboBox txtBus = new JComboBox();
		txtBus.setModel(new DefaultComboBoxModel(new String[] {"1","2", "3", "4", "5", "6"}));
		txtBus.setBounds(104, 27, 107, 22);
		
		panel_1.add(txtBus);
		
		
		JDateChooser txtDate = new JDateChooser();
		txtDate.setBounds(103, 85, 107, 20);
		panel_1.add(txtDate);
		
		JButton btnNewButton = new JButton("Додај");
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/_bus_db","root","");
				
				String busNo = txtBus.getSelectedItem().toString();
				SimpleDateFormat Date_Format = new SimpleDateFormat("yyyy-MM-dd");
				String date = Date_Format.format(txtDate.getDate());
				
				for(int i= 1; i<=30; i++) {
					
				
					int seats = i;
					String status = "unbooked";
					
					pst = con.prepareStatement("INSERT INTO _booking_bus(BusNo,Seat,Date,Status)values(?,?,?,?);");
					pst.setString(1,busNo);
					pst.setInt(2,seats);
					pst.setString(3,date);
					pst.setString(4,status);
					pst.executeUpdate();
					
				}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(contentPane,"Автобуската линија е успешно додадена!");
				}
			
		});
			
		
		btnNewButton.setBounds(27, 140, 89, 31);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Откажи");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

			}
		});
		btnNewButton_1.setBounds(155, 140, 89, 31);
		panel_1.add(btnNewButton_1);
	    
	}
}
