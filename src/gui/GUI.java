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
	private JLabel lblInfoTime;
	private JLabel background_1;
	private JPanel panelTetro;
	protected Game game;
	protected Cell[][] cells;
	protected Cell[][] nextTet;
	protected ImageIcon miniTetro;

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
		lblInfoTime.setText(""+game.getElapsedTime());
	}
	
	public void updateNextTetr() { 
		int x=0; 
		int y=0;
		Block[] blocks=game.getGrid().getNextTetr().getBlocks();
		for (int i=0; i<blocks.length; i++) {
			x=blocks[i].getX();
			y=blocks[i].getY();
			nextTet[x][y]=new Cell(blocks[i].getColor());
		}

		for(int i=0;i<nextTet.length;i++) {
			for(int j=0; j<nextTet[i].length;j++) {
				if(nextTet[i][j]!=null) {
					miniTetro=new ImageIcon(GUI.class.getResource(nextTet[i][j].getImagePath()));
					JLabel cubito= new JLabel();
					cubito.setIcon(miniTetro);
					panelTetro.add(cubito);
				}
				else {
					miniTetro=new ImageIcon(GUI.class.getResource("/gui/img/backgrounds/nextTet.png"));
					JLabel cubito= new JLabel();
					cubito.setIcon(miniTetro);
					panelTetro.add(cubito);
				}
			}
		}
		
	}
	
	
	private void initialize() {
		nextTet=new Cell[4][4];
		game=new Game();
		
		getContentPane().setBackground(new Color(30,30,30));
		setResizable(false);
		setBounds(100, 100, 565, 663);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		panelJuego();
		infoTetro();
		infoStats();
		background();
		
		updateElapsedTime();
		updatePoints();
		updateNextTetr();
		
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
		panelTetro = new JPanel();
		panelTetro.setBackground(new Color(0, 128, 128));
		panelTetro.setBounds(425, 50, 100, 100);
		panelTetro.setLayout(new GridLayout(4, 4, 0, 0));
		getContentPane().add(panelTetro);
		
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
		
		lblInfoTime = new JLabel("999999");
		lblInfoTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfoTime.setForeground(new Color(0,0,43));
		lblInfoTime.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblInfoTime.setBounds(25, 400, 100, 50);
		getContentPane().add(lblInfoTime);
		
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
