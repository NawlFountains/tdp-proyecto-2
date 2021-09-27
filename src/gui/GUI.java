package gui;

import javax.swing.JFrame;
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

@SuppressWarnings("serial")
public class GUI extends JFrame{
	
	private JPanel panel;
	private JLabel lblTime;
	private JLabel lblInfoScore;
	private JLabel lblScore;
	private JLabel lblNext;
	private JLabel lblInfoTime;
	private JLabel background_1;
	protected Cell[][] cells;
	protected Cell[][] nextTet;
	protected ImageIcon miniTetro;
	
	protected Game game;
	private JPanel panel_1;
	private JPanel panelTetro;

	/**
	 * Crea una nueva GUI y la asocia al juego pasado como parametro.
	 * @param game El juego asociado a esta GUI.
	 */
	public GUI(Game game) {
		this.game = game;
		
		initialize();
		updateElapsedTime();
		updatePoints();
		updateNextTetr();
	}
	
	/**
	 * Metodo encargado de inicializar la GUI.
	 */
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
	
	/**
	 * Metodos de la GUI
	 */
	public void updateGrid() {
		
	}
	
	public void updateCell() {
		
	}

	public void updatePoints() {
		lblInfoScore.setText("" + game.getPoints());
	}
	
	public void updateElapsedTime() {
		lblInfoTime.setText("" + game.getElapsedTime());
	}
	
	/**
	 * Muetra el tetromino siguiente en el panelTetro
	 */
	public void updateNextTetr() {
		
		int[][] shape=game.getGrid().getNextTetr().getShape();
		
		Cell bloqueTetromino;
		
		Block[] blocks = game.getGrid().getNextTetr().getBlocks();
		
		int size=shape.length;
		//si quieren ver la pantalla de "Desing" comenten las dos lineas siguientes
		panelTetro.setLayout(new GridLayout(size, size, 0, 0));
		panelTetro.setBounds(25 * (4 - size) / 2, 25 * (4 - size) / 2, size*25, size*25);
		//comenten hasta este punto
		
		
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				bloqueTetromino=new Cell();
				if(shape[i][j] == Tetromino.BLOCK || shape[i][j] == Tetromino.CENTROID) {
					bloqueTetromino.setColor(blocks[i].getColor());
				}
				nextTet[i][j] = bloqueTetromino;
				panelTetro.add(nextTet[i][j]);
			}
		}
	}

	/**
	 * Crea un label con una imagen de fondo
	 */
	
	private void background() {
		background_1 = new JLabel("");
		background_1.setIcon(new ImageIcon(GUI.class.getResource("/gui/img/backgrounds/bg.png")));
		background_1.setBounds(0, 0, 550, 625);
		getContentPane().add(background_1);
	}
	
	/**
	 * Crea el panel del juego, por donde caen los tetrominos
	 */
	
	private void panelJuego() {
		panel = new JPanel();
		panel.setBackground(new Color(0,0,43));
		panel.setBounds(150, 50, 250, 525);
		panel.setLayout(new GridLayout(21, 10, 0, 0));
		getContentPane().add(panel);
	}
	
	/**
	 * Crea un panel en el cual se cargara el tetromino siguiente y un cartel con el texto next
	 */
	
	private void infoTetro() {
		nextTet = new Cell[4][4];
		
		panel_1 = new JPanel();
		panel_1.setBounds(425, 50, 100, 100);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		panelTetro = new JPanel();
		panelTetro.setBounds(0, 0, 100, 100);
		panelTetro.setBackground(new Color(0, 128, 128));
		panelTetro.setLayout(new GridLayout(4, 4, 0, 0));
		panel_1.add(panelTetro);
		
		
		lblNext = new JLabel("NEXT");
		lblNext.setBounds(425, 175, 100, 25);
		lblNext.setForeground(new Color(0,0,43));
		lblNext.setHorizontalAlignment(SwingConstants.CENTER);
		lblNext.setFont(new Font("SansSerif", Font.BOLD, 20));
		getContentPane().add(lblNext);
	}
	
	/**
	 * crea una serie de labels para mostrar los stats de juego
	 */
	
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
