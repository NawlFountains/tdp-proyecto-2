package exceptions;

/**
 * Señala que ocurrio una excepcion en un tetromino. 
 */
@SuppressWarnings("serial")
public class TetrominoException extends Exception {
	
	/**
	 * Construye una nueva excepcion con el mensaje pasado como parametro.
	 * @param msg Un mensaje que detalla la excepcion.
	 */
	public TetrominoException(String msg) {
		super(msg);
	}

}
