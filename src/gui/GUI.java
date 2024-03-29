package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import exceptions.GridException;
import exceptions.TetrominoException;
import game.Game;
import game.Grid;
import game.tetrominos.Block;
import game.tetrominos.Color;
import game.tetrominos.Tetromino;

import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class GUI extends JFrame{
	
	protected ImageIcon miniTetro;
	
	protected Cell[][] cells;
	protected Cell[][] nextTet;
	protected Game juego;
	
	protected boolean rotateKeyPressed = false;
	protected boolean fallKeyPressed = false;
	
	private JPanel panel;
	private JLabel lblTime;
	private JLabel lblInfoScore;
	private JLabel lblScore;
	private JLabel lblNext;
	private JLabel lblInfoTime;
	private JLabel background_1;
	private JPanel panel_1;
	private JPanel panelTetro;

	/**
	 * Crea una nueva GUI y la asocia al juego pasado como parametro.
	 * @param game El juego asociado a esta GUI.
	 */
	public GUI(Game juego) {
		this.juego = juego;
		initialize();
	}
	
	/**
	 * Metodo encargado de inicializar la GUI.
	 */
	private void initialize() {
		getContentPane().setBackground(new java.awt.Color(30,30,30));
		setResizable(false);
		setBounds(100, 100, 565, 663);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setIconImage(new ImageIcon(getClass().getResource("/gui/img/icon/iconTetr.png")).getImage());
		setTitle("Tetris");
		
		crearPanelJuego();
		crearInfoTetro();
		crearInfoStats();
		crearFondoVentana();
		addControls();
	}
	
	private void addControls() {
		this.addKeyListener(new KeyAdapter() {								// A�ade listener para el movimiento lateral

			@Override
			public void keyPressed(KeyEvent e) {
				try {
					switch (e.getKeyCode()) {
					case KeyEvent.VK_LEFT:
						juego.getGrid().getFallingTetr().moveLeft(); 
						break;
					case KeyEvent.VK_RIGHT:
						juego.getGrid().getFallingTetr().moveRight(); 
						break;
					}
				} catch(TetrominoException ex) {
					ex.printStackTrace();
				}
			}

		});
		this.addKeyListener(new KeyAdapter() {								// A�ade listener para la caida

			@Override
			public void keyPressed(KeyEvent e) {
				if (!fallKeyPressed) {
					if (e.getKeyCode() == KeyEvent.VK_DOWN) {
						fallKeyPressed = true;
					}
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					fallKeyPressed = false;
				}
			}

		});
		this.addKeyListener(new KeyAdapter() {								// A�ade listener para la rotacion

			@Override
			public void keyPressed(KeyEvent e) {
				if (!rotateKeyPressed) {
					try {
						switch (e.getKeyCode()) {
						case KeyEvent.VK_Z:
							juego.getGrid().getFallingTetr().rotateLev();
							rotateKeyPressed = true;
							break;
						case KeyEvent.VK_X:
							juego.getGrid().getFallingTetr().rotateDext();
							rotateKeyPressed = true;
							break;
						}
					} catch (TetrominoException e1) {
						e1.printStackTrace();
					}
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_Z:
				case KeyEvent.VK_X:
					rotateKeyPressed = false;
					break;
				}
			}

		});
	}
	
	/**
	 * Recorre toda la grilla y actualiza los colores de las celdas.
	 */
	public void updateGrid() {
		try {
			Block bloque;
			for (int i = 0; i < cells.length; i++) {
				for (int j = 0; j < cells[i].length; j++) {
					bloque=juego.getGrid().getBlock(i, j);
					if (bloque != null) {
						cells[i][j].setColor(juego.getGrid().getBlock(i, j).getColor());
					} else {
						cells[i][j].setColor(null);
					}
				}
			}
		} catch (GridException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Cambia el color de la celda con corrdenadas x e y.
	 * @param x la coordenada x de la celda.
	 * @param y la coordenada y de la celda.
	 */
	public synchronized void updateCell(int x, int y) {
		try {
			Block bloque=juego.getGrid().getBlock(x, y);
			if (bloque != null) {
				cells[x][y].setColor(juego.getGrid().getBlock(x, y).getColor());
			} else {
				cells[x][y].setColor(null);
			}
		} catch (GridException ex) {
			ex.printStackTrace();
		}
		
	}
	
	/**
	 * Actualiza el valor del label que informa los puntos.
	 */
	public void updatePoints() {
		lblInfoScore.setText("" + juego.getPoints());
	}
	
	/**
	 * Actualiza el valor del label que informa el tiempo.
	 */
	public void updateElapsedTime() {
		int seg = juego.getElapsedTime();
		int min = seg / 60;
		seg = seg % 60;
		String rta = new StringBuilder(String.format("%02d", min))
				.append(" : ")
				.append(String.format("%02d", seg))
				.toString();
		lblInfoTime.setText(rta);
	}
	
	/**
	 * Muetra el tetromino siguiente en el panelTetro.
	 */
	public void updateNextTetr() {
		int[][] shape = juego.getGrid().getNextTetr().getShape();
		Color color = juego.getGrid().getNextTetr().getBlocks()[0].getColor();
		
		int size = shape.length;
		
		nextTet = new Cell[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				nextTet[i][j] = new Cell();
			}
		}
		
		panelTetro.removeAll();
		panelTetro.setLayout(new GridLayout(size, size, 0, 0));
		panelTetro.setBounds(25 * (4 - size) / 2, 25 * (4 - size) / 2, size * 25, size * 25);
		
		for (int y = size - 1; y >= 0; y--) {
			for (int x = 0; x < size; x++) {
				if (shape[x][y] != Tetromino.EMPTY) {
					nextTet[x][y].setColor(color);
				} else {
					nextTet[x][y].setColor(null);
				}
				panelTetro.add(nextTet[x][y]);
			}
		}
		
		panelTetro.revalidate();
		panelTetro.repaint();
	}
	
	/**
	 * Consulta si la tecla de aceleracion de caida esta presionada.
	 * @return True si la tecla de aceleracion de caida esta presionada, false si no.
	 */
	public boolean fallKeyPressed() {
		return fallKeyPressed;
	}
	
	/**
	 * Crea una nueva ventana de gameOver, la muestra en pantalla, y desactiva este frame.
	 */
	public void gameOver() {
		GameOverWindow ventanaGameOver = new GameOverWindow(this);
		ventanaGameOver.setVisible(true);
		this.setEnabled(false);
	}
	
	/**
	 * Crea un label con una imagen de fondo.
	 */
	private void crearFondoVentana() {
		background_1 = new JLabel("");
		background_1.setIcon(new ImageIcon(GUI.class.getResource("/gui/img/backgrounds/bg.png")));
		background_1.setBounds(0, 0, 550, 625);
		getContentPane().add(background_1);
	}
	
	/**
	 * Crea el panel del juego, por donde caen los tetrominos e inicializa la grilla de celdas.
	 */
	private void crearPanelJuego() {
		panel = new JPanel();
		panel.setBackground(new java.awt.Color(0,0,43));
		panel.setBounds(150, 50, 250, 525);
		panel.setLayout(new GridLayout(21, 10, 0, 0));
		getContentPane().add(panel);
		
		cells = new Cell[Grid.COLUMNS][Grid.ROWS];
		
		for(int y = Grid.ROWS - 1; y >= 0; y--) {
			for(int x = 0; x < Grid.COLUMNS; x++) {
				cells[x][y] = new Cell();
				panel.add(cells[x][y]);
			}
		}	
	}
	
	/**
	 * Crea un panel en el cual se cargara el tetromino siguiente y un cartel con el texto "next".
	 */
	private void crearInfoTetro() {
		nextTet = new Cell[4][4];
		
		for(int x = 0; x < nextTet.length; x++) {
			for(int y = 0; y < nextTet[x].length; y++) {
				nextTet[x][y] = new Cell();
			}
		}
		
		panel_1 = new JPanel();
		panel_1.setBounds(425, 50, 100, 100);
		panel_1.setLayout(null);
		panel_1.setBackground(new java.awt.Color(0, 0, 43));
		getContentPane().add(panel_1);
		
		panelTetro = new JPanel();
		panelTetro.setBounds(0, 0, 100, 100);
		panelTetro.setBackground(new java.awt.Color(0, 0, 43));
		panelTetro.setLayout(new GridLayout(4, 4, 0, 0));
		panel_1.add(panelTetro);
		
		
		lblNext = new JLabel("NEXT");
		lblNext.setBounds(425, 175, 100, 25);
		lblNext.setForeground(new java.awt.Color(0, 0, 43));
		lblNext.setHorizontalAlignment(SwingConstants.CENTER);
		lblNext.setFont(new Font("SansSerif", Font.BOLD, 20));
		getContentPane().add(lblNext);
	}
	
	/**
	 * Crea una serie de labels para mostrar los stats de juego.
	 */
	private void crearInfoStats() {
		lblTime = new JLabel("TIME");
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setForeground(new java.awt.Color(0, 0, 43));
		lblTime.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblTime.setBounds(25, 350, 100, 25);
		getContentPane().add(lblTime);
		
		lblInfoTime = new JLabel("999999");
		lblInfoTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfoTime.setForeground(new java.awt.Color(0, 0, 43));
		lblInfoTime.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblInfoTime.setBounds(25, 400, 100, 50);
		getContentPane().add(lblInfoTime);
		
		lblScore = new JLabel("SCORE");
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setForeground(new java.awt.Color(0, 0, 43));
		lblScore.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblScore.setBounds(25, 475, 100, 25);
		getContentPane().add(lblScore);
		
		lblInfoScore = new JLabel("999999");
		lblInfoScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfoScore.setForeground(new java.awt.Color(0,0,43));
		lblInfoScore.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblInfoScore.setBounds(25, 525, 100, 50);
		getContentPane().add(lblInfoScore);
		
	}
}
