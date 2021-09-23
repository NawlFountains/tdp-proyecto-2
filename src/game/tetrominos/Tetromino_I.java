package game.tetrominos;

/**
 * Modela un tetromino tipo I.
 */
public class Tetromino_I extends Tetromino{
	
	/**
	 * Crea un nuevo tetromino de tipo I con bloques del color dado.
	 * @param color El color de los bloques del nuevo tetromino.
	 */
	public Tetromino_I(Color color) {
		super(color);
		shape = new int[4][4];
		
		shape[0][3] = EMPTY;	shape[1][3] = BLOCK;		shape[2][3] = EMPTY;	shape[3][3] = EMPTY;
		shape[0][2] = EMPTY;	shape[1][2] = CENTROID;		shape[2][2] = EMPTY;	shape[3][2] = EMPTY;
		shape[0][1] = EMPTY;	shape[1][1] = BLOCK;		shape[2][1] = EMPTY;	shape[3][1] = EMPTY;
		shape[0][0] = EMPTY;	shape[1][0] = BLOCK;		shape[2][0] = EMPTY;	shape[3][0] = EMPTY;
	}
	
}