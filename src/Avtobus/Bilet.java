package Avtobus;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
public class Bilet extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtSeat;
	private JTextField txtMobile;
	private JTextField txtDate1;
	private JTable table;
    Connection con;
    PreparedStatement pst;
    PreparedStatement pst1;
    PreparedStatement pst2;
    PreparedStatement pst3;
    ResultSet rs;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bilet frame = new Bilet();
					frame.setVisible(true);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	JDateChooser txtDateChooser = new JDateChooser();
	
	public Bilet() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\eclipse_workspace\\GradskiPrevoz\\img\\red bus.png"));
		setTitle("Возен ред Битола");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1378, 722);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1362, 78);
		panel.setBackground(new Color(204, 0, 0));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Резервација на билет");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setForeground(new Color(255, 204, 102));
		lblNewLabel.setBounds(548, 11, 291, 40);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(42, 148, 315, 468);
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Име и презиме");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(23, 28, 96, 14);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Брoj на седиште");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(23, 83, 106, 14);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Телефонски број");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(23, 140, 119, 14);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Дата");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(23, 183, 68, 14);
		panel_1.add(lblNewLabel_4);
		
		txtName = new JTextField();
		txtName.setBounds(144, 26, 144, 20);
		panel_1.add(txtName);
		txtName.setColumns(10);
		
		txtSeat = new JTextField();
		txtSeat.setBounds(144, 81, 144, 20);
		panel_1.add(txtSeat);
		txtSeat.setColumns(10);
		
		txtMobile = new JTextField();
		txtMobile.setBounds(144, 138, 144, 20);
		panel_1.add(txtMobile);
		txtMobile.setColumns(10);
		
		txtDate1 = new JTextField();
		txtDate1.setBounds(144, 181, 144, 20);
		panel_1.add(txtDate1);
		txtDate1.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Додај");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
                
				DefaultTableModel d1 = (DefaultTableModel)table.getModel();
				int selected = table.getSelectedRow();
				
				String busNo = d1.getValueAt(selected,0).toString();
				
				String cusName = txtName.getText();
				String seat = txtSeat.getText();
				String mobile = txtMobile.getText();
				String date = txtDate1.getText();

					pst = con.prepareStatement("INSERT INTO _BOOKING_SEAT(BUSNO,SEATNO,CLIENT,MOBILENO,DATE)values("+busNo+","+seat+",'"+cusName+"',"+mobile+",'"+date+"');");
					pst1 = con.prepareStatement("UPDATE _BOOKING_BUS SET STATUS = 'Booked' WHERE SEAT= "+seat);
					pst.executeUpdate();
					pst1.executeUpdate();
				    if(cusName==null) {
				    	JOptionPane.showMessageDialog(contentPane, "Внесете име и презиме!");
				    }if(mobile==null) {
				    	JOptionPane.showMessageDialog(contentPane, "Внесете телефонски број!");
				    }
					JOptionPane.showMessageDialog(contentPane, "Билетот е успешно резервиран!");
	                
					Load();
	                
	                txtName.setText("");
	                txtSeat.setText("");
	                txtMobile.setText("");
	                txtDate1.setText("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
			}
			}
		
		});
		
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(40, 311, 106, 23);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Откажи");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				VozenRed vr = new VozenRed();
				vr.setVisible(true);
				vr.setLocationRelativeTo(null);
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_2.setBounds(99, 357, 106, 23);
		panel_1.add(btnNewButton_2);
		
		JButton button = new JButton("Бриши");
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
 
				DefaultTableModel d1 = (DefaultTableModel)table.getModel();
				
				int selected = table.getSelectedRow();
				
                String seatNo = d1.getValueAt(selected,1).toString();
                
					pst2 = con.prepareStatement("DELETE FROM _BOOKING_SEAT WHERE SEATNO="+seatNo);
					pst2.executeUpdate();
					pst3= con.prepareStatement("UPDATE _BOOKING_BUS SET STATUS = 'unbooked' WHERE SEAT= "+seatNo);
					pst3.executeUpdate();
					JOptionPane.showMessageDialog(contentPane, "Резервацијата е откажана!");
					Load();
					
			}catch(SQLException e1) {
					e1.printStackTrace();
					
				}
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 14));
		button.setBounds(156, 311, 106, 23);
		panel_1.add(button);
		
		JLabel lblNewLabel_5 = new JLabel("Дата");
		lblNewLabel_5.setBounds(627, 107, 46, 14);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lblNewLabel_5);
		
	
		

		txtDateChooser.setBounds(685, 107, 138, 20);
		contentPane.add(txtDateChooser);

		JButton btnNewButton = new JButton("Приказ");
			btnNewButton.setEnabled(false);
		btnNewButton.setBounds(848, 104, 76, 23);
		
	
		txtDateChooser.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if(txtDateChooser.isShowing()==true) {
					
				btnNewButton.setEnabled(true);
				}
			}
		});
       
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		                Load();
		                if(table.getRowCount() == 0 ) {
		                	JOptionPane.showMessageDialog(contentPane, "Не постои резервација на избраната дата!");
		                }

				}
		});
		contentPane.add(btnNewButton);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(379, 148, 949, 468);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel d1 = (DefaultTableModel)table.getModel();
				int selected = table.getSelectedRow();
				String status = d1.getValueAt(selected,2).toString();
				if(!status.equals("Booked")) {
					String seat = d1.getValueAt(selected, 1).toString();
					String date = d1.getValueAt(selected, 5).toString();
					txtSeat.setText(seat);
					txtDate1.setText(date);
			}
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u041B\u0438\u043D\u0438\u0458\u0430 \u0431\u0440oj", "\u0421\u0435\u0434\u0438\u0448\u0442\u0435", "\u0421\u0442\u0430\u0442\u0443\u0441", "\u0418\u043C\u0435 \u0438 \u043F\u0440\u0435\u0437\u0438\u043C\u0435", "\u0422\u0435\u043B\u0435\u0444\u043E\u043D\u0441\u043A\u0438 \u0431\u0440\u043E\u0458", "\u0414\u0430\u0442\u0430"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(91);
		table.getColumnModel().getColumn(1).setPreferredWidth(87);
		table.getColumnModel().getColumn(2).setPreferredWidth(88);
		table.getColumnModel().getColumn(3).setPreferredWidth(115);
		table.getColumnModel().getColumn(4).setPreferredWidth(103);
		table.getColumnModel().getColumn(5).setPreferredWidth(110);
	}
	
	public void Load() {
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:./data/test","root","");

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String date = df.format(txtDateChooser.getDate());
			pst = con.prepareStatement("SELECT _booking_bus.busno,_booking_bus.seat,_booking_bus.status,_booking_bus.date,_booking_seat.client,_booking_seat.mobileno from _booking_bus LEFT JOIN _booking_seat ON _booking_bus.busno=_booking_seat.busno AND _booking_bus.seat=_booking_seat.seatno AND _booking_bus.date=_booking_seat.date where _booking_bus.date='"+date+"'");
			rs = pst.executeQuery();
            
        ResultSetMetaData rsd = rs.getMetaData();
        int c;
        c=rsd.getColumnCount();
        DefaultTableModel d = (DefaultTableModel)table.getModel();
        d.setRowCount(0);
        while(rs.next()) {
        	
        	Vector v2 = new Vector();
        for(int i =1; i<= c; i++) {
        	v2.add(rs.getString("BusNo"));
        	v2.add(rs.getString("Seat"));
        	v2.add(rs.getString("Status"));
        	v2.add(rs.getString("Client"));
        	v2.add(rs.getString("MobileNo"));
        	v2.add(rs.getString("Date"));
        }
        d.addRow(v2);
        }
      
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch(NullPointerException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(contentPane, "Изберете дата!");
	}
	}
	
}

