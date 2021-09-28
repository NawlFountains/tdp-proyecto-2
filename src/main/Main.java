package main;

import game.Game;
import gui.GUI;
import gui.GameOverWindow;
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
		GUI ventana = game.getGUI();
		
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
		
		game.start();
	}

}