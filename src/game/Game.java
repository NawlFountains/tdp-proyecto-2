package game;

import exceptions.TetrominoException;
import gui.GUI;
import threads.GameThread;
import threads.TimerThread;

/**
 * Modela el juego.
 */

public class Game {
	
	//Atributos de instancia
	
	protected int points = 0;
	protected int elapsedTime = 0;
	protected boolean lost;

	protected Grid grid;
	protected GUI gui;

	/**
	 * Crea una nueva instancia del juego.
	 */
	public Game() {
		grid = new Grid(this);
		gui = new GUI(this);
		this.lost = false;

		gui.updateElapsedTime();
		gui.updatePoints();
	}
	
	public void start() {
		grid.addTetromino();
		
		gui.updateGrid();
		gui.updateNextTetr();
		
		Thread gameThread = new GameThread(this);
		Thread timerThread = new TimerThread(this);
		
		gameThread.start();
		timerThread.start();
	}
	
	/**
	 * Hace caer el tetromino que se encuantra actualmente en caida.
	 */
	public void run() {
		try {
			grid.getFallingTetr().fall();
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
		float minPause = 50;
		float maxPause = 900;
		float maxSpeedTime = 300;
		
		double toReturn = minPause;
		if(!gui.fallKeyPressed() && elapsedTime <= maxSpeedTime) {
			float slope = - (maxPause - minPause) / maxSpeedTime;
			toReturn = slope * elapsedTime + maxPause;
		}
		return (int) Math.round(toReturn);
	}
	
	/**
	 * Añade un segundo al tiempo transcurrido.
	 */
	public void addSecond() {
		elapsedTime++;
	}
	
	/**
	 * Termina este juego, seteando el valor de lost en verdadero.
	 */
	public void lose() {
		lost = true;
		gui.gameOver();
	}
	
}
