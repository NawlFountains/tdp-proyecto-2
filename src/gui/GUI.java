package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class GUI extends JFrame{
	
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_1_1;
	private JLabel lblNewLabel_1_2;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1_1_1;
	

	public GUI() {
		initialize();
	}

	private void initialize() {
		panel = new JPanel();
		panel_2 = new JPanel();
		panel_3 = new JPanel();
		lblNewLabel = new JLabel("NEXT");
		panel_1 = new JPanel();
		lblNewLabel_1 = new JLabel("TIME");
		lblNewLabel_1_1_1 = new JLabel("9999999");
		panel_4 = new JPanel();
		lblNewLabel_1_1 = new JLabel("9999999");
		lblNewLabel_1_2 = new JLabel("SCORE");
		panel_5 = new JPanel();
		
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(133, 10, 200, 420);
		
		this.getContentPane().setBackground(new Color(30,30,30));
		this.setResizable(false);
		this.setBounds(100, 100, 480, 480);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.getContentPane().add(panel);
		this.getContentPane().add(panel_1);
		this.getContentPane().add(panel_2);
		
		infoTetro();
		infoStats();
	}
	
	private void infoTetro() {
		
		
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 94, 80, 26);
		
		panel_2.setLayout(null);
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setBounds(356, 10, 100, 130);
		panel_2.add(lblNewLabel);
		panel_2.add(panel_3);
		
		panel_3.setBackground(Color.GRAY);
		panel_3.setBounds(10, 10, 80, 80);
	}
	
	private void infoStats() {
		
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(10, 230, 100, 200);
		panel_1.setLayout(null);
		panel_1.add(panel_4);
		panel_1.add(panel_5);
		
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.ORANGE);
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblNewLabel_1.setBounds(0, 46, 80, 26);
		
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setForeground(Color.ORANGE);
		lblNewLabel_1_1_1.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(0, 10, 80, 26);
		
		panel_4.setBackground(Color.GRAY);
		panel_4.setBounds(10, 10, 80, 80);
		panel_4.setLayout(null);
		panel_4.add(lblNewLabel_1);
		panel_4.add(lblNewLabel_1_1_1);
		
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(Color.ORANGE);
		lblNewLabel_1_1.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(0, 10, 80, 26);
		
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setForeground(Color.ORANGE);
		lblNewLabel_1_2.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblNewLabel_1_2.setBounds(0, 46, 80, 26);
		
		panel_5.setBackground(Color.GRAY);
		panel_5.setBounds(10, 110, 80, 80);
		panel_5.setLayout(null);
		panel_5.add(lblNewLabel_1_1);
		panel_5.add(lblNewLabel_1_2);
		
	}
}
