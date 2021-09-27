package game;

import exceptions.TetrominoException;
import gui.GUI;

/**
 * Modela el juego.
 */

public class Game {
	
	//Atributos de instancia
	
	protected Grid grid;
	protected int points = 0;
	protected int elapsedTime = 0;
	protected boolean lost;

	protected GUI gui;

	/**
	 * Crea una nueva instancia del juego.
	 */
	public Game() {
		grid = new Grid(this);
		gui = new GUI(this);
		this.lost = false;
	}
	
	/**
	 * Checkea si el tetromino actual de la grilla está cayendo.
	 * Si está cayendo, lo hace caer nuevamente, 
	 * caso contrario elimina las lineas completas si las hay y hace caer el proximo tetromino.
	 */
	public void run() {
		try {
			if(grid.getFallingTetr().isFalling())
				grid.getFallingTetr().fall();
			else
				addPoints(grid.deleteLines());
		} catch (TetrominoException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Incrementa el puntaje actual en la cantidad pasada como parametro.
	 * @param points Cantidad de puntos a agregar.
	 */
	public void addPoints(int points) {
		this.points += points;
	}
	
	/**
	 * Retorna el tiempo transcurrido desde el inicio del juego.
	 * @return El tiempo transcurrido desde el inicio del jueg, representado en segundos.
	 */
	public int getElapsedTime() {
		return elapsedTime;
	}
	
	/**
	 * Retorna la grilla asociada a este juego.
	 * @return la grilla asociada a este juego.
	 */
	public Grid getGrid() {
		return grid;
	}
	
	/**
	 * Consulta si el juego terminó o no.
	 * @return true si el juego terminó, false en caso contrario.
	 */
	public boolean lost() {
		return lost;
	}
	
	/*
	 * Retorna los puntos acumulados
	 * @return los puntos acumulados
	 * */
	public int getPoints() {
		return points;
	}
	
	/**
	 * Retorna la GUI asociada a este juego.
	 * @return La GUI asociada a este juego.
	 */
	public GUI getGUI() {
		return gui;
	}
	
	/**
	 * 
	 * @return la cantidad de milisegundos entre ejecución, calculada con el tiempo transcurrido actual.
	 */
	public int getPauseBetweenRun() {
		if(elapsedTime < 900)
			return 1000 - elapsedTime;
		else 
			return 100;
	}
	
	/**
	 * Añade un segundo al tiempo transcurrido.
	 */
	public void addSecond() {
		elapsedTime++;
	}
}
