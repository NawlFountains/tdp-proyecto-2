package main;

import game.Game;
import gui.GUI;
import threads.GameThread;
import threads.TimerThread;

/**
 * Clase principal del juego.
 */
public class Main {
	
	/**
	 * Inicia la aplicacion.
	 * @param args Argumentos.
	 */
	public static void main(String[] args) {
		Game game = new Game();
		GUI ventana = new GUI();
		Thread gameThread = new GameThread(game);
		Thread timerThread = new TimerThread(game);
		
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
		
		gameThread.start();
		timerThread.start();
	}

}
