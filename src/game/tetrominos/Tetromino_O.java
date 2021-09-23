package game.tetrominos;

/**
 * Modela un tetromino tipo O.
 */
public class Tetromino_O extends Tetromino{
	
	/**
	 * Crea un nuevo tetromino de tipo O con bloques del color dado.
	 * @param color El color de los bloques del nuevo tetromino.
	 */
	public Tetromino_O(Color color) {
		super(color);
		shape = new int[2][2];
		
		shape[0][1] = CENTROID;	shape[1][1] = BLOCK;
		shape[0][0] = BLOCK;	shape[1][0] = BLOCK;
	}


	/**
	 * {@inheritDoc}
	 * Debido a la simetria rotacional del tetromino tipo O, este metodo no hace nada.
	 */
	@Override
	public void rotateDext() {}

	/**
	 * {@inheritDoc}
	 * Debido a la simetria rotacional del tetromino tipo O, este metodo no hace nada.
	 */
	@Override
	public void rotateLev() {}
	
}