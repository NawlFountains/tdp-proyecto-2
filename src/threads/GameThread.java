package threads;

import game.Game;

/**
 * Extiende un hilo de ejecucion para que se encargue de ejecutar el juego.
 */
public class GameThread extends Thread {

	protected Game game;
	
	/**
	 * Crea un nuevo GameThread con el juego pasado como parametro.
	 * @param game Un juego.
	 */
	public GameThread(Game game) {
		this.game = game;
	}
	
	/**
	 * Ejecuta el juego cada decisegundo mientras este no haya terminado.
	 */
	@Override
	public void run() {
		while(!game.lost()) {
			// TODO run game
			System.out.println("Execute game");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
