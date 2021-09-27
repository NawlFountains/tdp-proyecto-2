package exceptions;

/**
 * Señala que ocurrio una excepcion en la grilla. 
 */
@SuppressWarnings("serial")
public class GridException extends Exception {
	
	/**
	 * Construye una nueva excepcion con el mensaje pasado como parametro.
	 * @param msg Un mensaje que detalla la excepcion.
	 */
	public GridException(String msg) {
		super(msg);
	}

}
