package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import exceptions.GridException;
import exceptions.TetrominoException;
import game.Game;
import game.Grid;
import game.tetrominos.Block;
import game.tetrominos.Tetromino;

import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class GUI extends JFrame{
	
	private JPanel panel;
	private JLabel lblTime;
	private JLabel lblInfoScore;
	private JLabel lblScore;
	private JLabel lblNext;
	private JLabel lblInfoTime;
	private JLabel background_1;
	private JPanel panel_1;
	private JPanel panelTetro;
	protected ImageIcon miniTetro;
	
	protected Cell[][] cells;
	protected Cell[][] nextTet;
	protected Game game;


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
		updateGrid();
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
		
		crearPanelJuego();
		crearInfoTetro();
		crearInfoStats();
		crearFondoVentana();
		controles();
	}
	
	private void controles() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int a=e.getKeyCode();
				System.out.println(a); //TODO
				lblInfoTime.setText(""+a); //TODO
				
				try {
					switch (a) {
					case KeyEvent.VK_LEFT:
						
						System.out.println("left"); //TODO
						game.getGrid().getFallingTetr().moveLeft(); 
						break;
					case KeyEvent.VK_RIGHT:

						System.out.println("right"); //TODO
						game.getGrid().getFallingTetr().moveRight(); 
						break;
					case KeyEvent.VK_DOWN:

						System.out.println("down"); //TODO
						game.getGrid().getFallingTetr().fall(); 
						break;
					case KeyEvent.VK_Z:

						System.out.println("rotateLev"); //TODO
						game.getGrid().getFallingTetr().rotateLev();
						break;
					case KeyEvent.VK_X:

						System.out.println("rotateDext"); //TODO
						game.getGrid().getFallingTetr().rotateDext();
						break;
						
					}
				}catch(TetrominoException ex) {
					ex.printStackTrace();
				}
				
				
			}
		});
	}
	
	/**
	 * Recorre toda la grilla y actualiza los colores de las celdas
	 */
	public void updateGrid() {
		
		try {
			Block bloque;
			for(int i = 0; i < cells.length; i++) {
				for(int j = 0; j < cells[i].length; j++) {
					bloque=game.getGrid().getBlock(i, j);
					if(bloque!=null)
						cells[i][j].setColor(game.getGrid().getBlock(i, j).getColor());
					else
						cells[i][j].setColor(null);
					panel.add(cells[i][j]);
				}
			}
		}catch(GridException ex) {
			ex.printStackTrace();
		}
		
	}
	
	/**
	 * Cambia el color de la celda con corrdenadas x e y
	 * @param x la coordenada x de la celda
	 * @param y la coordenada y de la celda
	 */
	
	public void updateCell(int x, int y) {
		try {
			Block bloque=game.getGrid().getBlock(x, y);
			if(bloque!=null)
				cells[x][y].setColor(game.getGrid().getBlock(x, y).getColor());
			else
				cells[x][y].setColor(null);
			panel.add(cells[x][y]);
		} catch (GridException ex) {
			ex.printStackTrace();
		}
		
	}
	
	/**
	 * Actualiza el valor del label que informa los puntos
	 */
	public void updatePoints() {
		lblInfoScore.setText("" + game.getPoints());
	}
	
	/**
	 * Actualiza el valor del label que informa el tiempo
	 */
	public void updateElapsedTime() {
		lblInfoTime.setText("" + game.getElapsedTime());
	}
	
	/**
	 * Muetra el tetromino siguiente en el panelTetro
	 */
	public void updateNextTetr() {
		
		int[][] shape=game.getGrid().getNextTetr().getShape();
		
		Block[] blocks = game.getGrid().getNextTetr().getBlocks();
		
		int size=shape.length;
		//si quieren ver la pantalla de "Design" comenten las dos lineas siguientes TODO
		panelTetro.setLayout(new GridLayout(size, size, 0, 0));
		panelTetro.setBounds(25 * (4 - size) / 2, 25 * (4 - size) / 2, size*25, size*25);
		
		
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(shape[i][j] != Tetromino.EMPTY) {
					nextTet[i][j].setColor(blocks[i].getColor());
				}
				panelTetro.add(nextTet[i][j]);
			}
		}
	}

	/**
	 * Crea un label con una imagen de fondo
	 */
	
	private void crearFondoVentana() {
		background_1 = new JLabel("");
		background_1.setIcon(new ImageIcon(GUI.class.getResource("/gui/img/backgrounds/bg.png")));
		background_1.setBounds(0, 0, 550, 625);
		getContentPane().add(background_1);
	}
	
	/**
	 * Crea el panel del juego, por donde caen los tetrominos e inicializa la grilla de celdas
	 */
	
	private void crearPanelJuego() {
		
		cells = new Cell[Grid.COLUMNS][Grid.ROWS];
		
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[i].length; j++) {
				cells[i][j] = new Cell();
			}
		}
		
		panel = new JPanel();
		panel.setBackground(new Color(0,0,43));
		panel.setBounds(150, 50, 250, 525);
		panel.setLayout(new GridLayout(21, 10, 0, 0));
		getContentPane().add(panel);
	}
	
	/**
	 * Crea un panel en el cual se cargara el tetromino siguiente y un cartel con el texto next
	 */
	
	private void crearInfoTetro() {
		nextTet = new Cell[4][4];
		for(int i = 0; i < nextTet.length; i++) {
			for(int j = 0; j < nextTet[i].length; j++) {
				nextTet[i][j] = new Cell();
			}
		}
		
		panel_1 = new JPanel();
		panel_1.setBounds(425, 50, 100, 100);
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(0,162,232));
		getContentPane().add(panel_1);
		
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
	 * Crea una serie de labels para mostrar los stats de juego
	 */
	private void crearInfoStats() {
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
