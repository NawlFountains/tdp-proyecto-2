package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import game.Game;
import game.tetrominos.Block;
import game.tetrominos.Tetromino;

import javax.swing.ImageIcon;
import java.awt.GridLayout;

public class GUI extends JFrame{
	
	private JPanel panel;
	private JLabel lblTime;
	private JLabel lblInfoScore;
	private JLabel lblScore;
	private JLabel lblNext;
	private JLabel lvlInfoTime;
	private JLabel lblInfoNextTetro;
	private JLabel background_1;
	protected Game game;
	protected Cell[][] cells;
	protected Cell[][] nextTet;

	public GUI() {
		initialize();
	}
	
	/**
	 * Metodos de la GUI
	 */
	
	public void updateGrid() {
		
	}
	
	public void updateCell() {
		
	}

	public void updatePoints() {
		lblInfoScore.setText(""+game.getPoints());
	}
	
	public void updateElapsedTime() {
		
	}
	
	public void updateNextTetr() { 
		int x=0; 
		int y=0;
		Block[] blocks=game.getGrid().getNextTetr().getBlocks();
		for (int i=0; i<blocks.length; i++) {
			x=blocks[i].getX();
			y=blocks[i].getY();
			nextTet[x][y].setColor(blocks[i].getColor());;
		}
		
	}
	
	
	private void initialize() {
		
		getContentPane().setBackground(new Color(30,30,30));
		setResizable(false);
		setBounds(100, 100, 565, 663);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		panelJuego();
		infoTetro();
		infoStats();
		background();
		
		
	}
	
	private void background() {
		background_1 = new JLabel("");
		background_1.setIcon(new ImageIcon(GUI.class.getResource("/gui/img/backgrounds/bg.png")));
		background_1.setBounds(0, 0, 550, 625);
		getContentPane().add(background_1);
	}
	
	private void panelJuego() {
		panel = new JPanel();
		panel.setBackground(new Color(0,0,43));
		panel.setBounds(150, 50, 250, 525);
		panel.setLayout(new GridLayout(21, 10, 0, 0));
		getContentPane().add(panel);
	}
	
	private void infoTetro() {
		lblInfoNextTetro = new JLabel("");
		lblInfoNextTetro.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfoNextTetro.setBounds(425, 50, 100, 100);
		getContentPane().add(lblInfoNextTetro);
		
		lblNext = new JLabel("NEXT");
		lblNext.setBounds(425, 175, 100, 25);
		lblNext.setForeground(new Color(0,0,43));
		lblNext.setHorizontalAlignment(SwingConstants.CENTER);
		lblNext.setFont(new Font("SansSerif", Font.BOLD, 20));
		getContentPane().add(lblNext);
	}
	
	private void infoStats() {
		lblTime = new JLabel("TIME");
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setForeground(new Color(0,0,43));
		lblTime.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblTime.setBounds(25, 350, 100, 25);
		getContentPane().add(lblTime);
		
		lvlInfoTime = new JLabel("999999");
		lvlInfoTime.setHorizontalAlignment(SwingConstants.CENTER);
		lvlInfoTime.setForeground(new Color(0,0,43));
		lvlInfoTime.setFont(new Font("SansSerif", Font.BOLD, 22));
		lvlInfoTime.setBounds(25, 400, 100, 50);
		getContentPane().add(lvlInfoTime);
		
		lblScore = new JLabel("SCORE");
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setForeground(new Color(0,0,43));
		lblScore.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblScore.setBounds(25, 475, 100, 25);
		getContentPane().add(lblScore);
		
		lblInfoScore = new JLabel("999999");
		lblInfoScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfoScore.setForeground(new Color(0,0,43));
		lblInfoScore.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblInfoScore.setBounds(25, 525, 100, 50);
		getContentPane().add(lblInfoScore);
		
	}
}
