package exceptions;

/**
 * Señala que ocurrio una excepcion en el juego. 
 */
@SuppressWarnings("serial")
public class GameException extends Exception {
	
	/**
	 * Construye una nueva GameException con el mensaje pasado como parametro.
	 * @param msg Un mensaje que detalla la excepcion.
	 */
	public GameException(String msg) {
		super(msg);
	}

}
