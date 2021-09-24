package main;

import gui.GUI;

/**
 * Clase principal del juego.
 */
public class Main {
	
	/**
	 * Inicia la aplicacion.
	 * @param args Argumentos.
	 */
	public static void main(String[] args) {
		GUI ventana = new GUI();
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}

}
