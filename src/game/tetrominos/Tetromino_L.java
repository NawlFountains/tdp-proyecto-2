package game.tetrominos;

/**
 * Modela un tetromino tipo L.
 */
public class Tetromino_L extends Tetromino{
	
	/**
	 * Crea un nuevo tetromino de tipo L con bloques del color dado.
	 * @param color El color de los bloques del nuevo tetromino.
	 */
	public Tetromino_L(Color color) {
		super(color);
		shape = new int[3][3];
		
		shape[0][2] = EMPTY;	shape[1][2] = BLOCK;	shape[2][2] = EMPTY;
		shape[0][1] = EMPTY;	shape[1][1] = CENTROID;	shape[2][1] = EMPTY;
		shape[0][0] = EMPTY;	shape[1][0] = BLOCK;	shape[2][0] = BLOCK;
	}
	
}