package game;

/**
 * Clase Grilla
 * @author Islas Agustin
 *
 */

public class Game {
	
	//Atributos de instancia
	
	//protected Gui gui; TODO
	protected Grid grid;
	protected int points = 0;
	protected int speed;
	protected int elapsedTime = 0;
	protected boolean lost;
	protected int timeSinceRun = 0;
	
	public Game(Grid grid) {
		this.grid = grid;
		this.speed = 0;
		this.lost = false;
	}
	
//	public void run() {
//		
//	} TODO
	
	/**
	 * Incrementa el puntaje actual.
	 * @param points cantidad de puntos a agregar.
	 */
	public void addPointers(int points) {
		this.points += points;
	}
	
	/**
	 * @return el tiempo de caida de los bloques, representan segundos.
	 */
	public int getElapsedTime() {
		return elapsedTime;
	}
	
	/**
	 * @return la grid asociada a esta intancia.
	 */
	public Grid getGrid() {
		return grid;
	}
	
	/**
	 * Controla si el juego terminó.
	 * @return true si el juego terminó, false en caso contrario.
	 */
	public boolean lost() {
		return lost;
	}
}
