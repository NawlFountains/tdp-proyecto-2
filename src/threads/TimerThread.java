package threads;

import game.Game;

/**
 * Extiende un hilo de ejecucion para que se encargue de incrementar el reloj del juego.
 */
public class TimerThread extends Thread {

	protected Game game;
	
	/**
	 * Crea un nuevo TimerThread con el juego pasado como parametro.
	 * @param game Un juego.
	 */
	public TimerThread(Game game) {
		this.game = game;
	}
	
	/**
	 * Incrementa el reloj del juego en un segundo cada segundo mientras el juego no haya terminado.
	 */
	@Override
	public void run() {
		while(!game.lost()) {
			game.addSecond();
			game.getGUI().updateElapsedTime();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
