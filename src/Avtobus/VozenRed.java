package Avtobus;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VozenRed extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VozenRed frame = new VozenRed();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VozenRed() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\eclipse_workspace\\GradskiPrevoz\\img\\red bus.png"));
		setTitle("Возен ред Битола");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 418, 307);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 0, 0));
		panel.setBounds(0, 5, 392, 52);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Патнички Сообраќај Битола");
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setForeground(new Color(255, 204, 102));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(53, 11, 317, 30);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Додај автобус");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Linija s = new Linija();
				s.setVisible(true);
				s.setLocationRelativeTo(null);
				setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(36, 92, 155, 42);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Резервација");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    Bilet b = new  Bilet();
				b.setVisible(true);
				b.setExtendedState(JFrame.MAXIMIZED_BOTH);
				setVisible(false);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(36, 179, 155, 42);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("");
		Image bus = new ImageIcon(this.getClass().getResource("/red bus.png")).getImage();
		lblNewLabel_1.setIcon(new ImageIcon(bus));
		lblNewLabel_1.setBounds(220, 92, 155, 129);
		contentPane.add(lblNewLabel_1);
	}
}
