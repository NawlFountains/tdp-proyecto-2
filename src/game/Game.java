package game;

import gui.GUI;

/**
 * Modela el juego.
 * @author Islas Agustin
 */

public class Game {
	
	//Atributos de instancia
	
	protected Grid grid;
	protected int points = 0;
	protected int speed;
	protected int elapsedTime = 0;
	protected boolean lost;
	protected int timeSinceRun = 0;

	protected GUI gui;
	
	/**
	 * Crea una nueva instancia del juego.
	 */
	public Game() {
		grid = new Grid(this);
		gui = new GUI(this);
		this.speed = 0;
		this.lost = false;
	}
	
//	public void run() {
//		
//	} TODO
	
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
	
}
