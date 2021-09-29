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
	
	@Override
	public void rotate(int[][] shapeMatrix) {
		/**
		 * Sobreescribe el metodo en la clase padre para considerar el caso en que al rotar se debe
		 * desplazar a esta pieza dos lugares a un lado en lugar de solo uno
		 */
		
		Block[] tetro = getBlocks();
		
		int[][] rotatedCollisionCoords = new int[tetro.length][];
		
		int[] centroidPosInShape = getCentroidPosInShape(shapeMatrix);
		int collisionCoordsIndex = 0;
		boolean rotationSuccess = false;
		
		rotatedCollisionCoords[collisionCoordsIndex++] = new int[] {centroid.getX(), centroid.getY()};
		
		for (int x = 0; x < shapeMatrix.length; x++) {
			for (int y = 0; y < shapeMatrix[0].length; y++) {
				if (shapeMatrix[x][y] == BLOCK) {
					int rotatedX = centroid.getX() - centroidPosInShape[0] + x;
					int rotatedY = centroid.getY() - centroidPosInShape[1] + y;
					rotatedCollisionCoords[collisionCoordsIndex++] = new int[] {rotatedX, rotatedY};
				}
			}
		}
		
		rotationSuccess = attemptRotation(tetro, rotatedCollisionCoords);
		if (!rotationSuccess) {
			shift(rotatedCollisionCoords, -1);											// Desplaza a la izquierda (uno a la izquierda de la posicion original)
			rotationSuccess = attemptRotation(tetro, rotatedCollisionCoords);
		}
		if (!rotationSuccess) {
			shift(rotatedCollisionCoords, 2);											// Desplaza dos a la derecha (uno a la derecha de la posicion original)
			rotationSuccess = attemptRotation(tetro, rotatedCollisionCoords);
		}
		if (!rotationSuccess) {
			shift(rotatedCollisionCoords, -3);											// Desplaza tres a la izquierda (uno a la izquierda de la posicion original)
			rotationSuccess = attemptRotation(tetro, rotatedCollisionCoords);
		}
		if (!rotationSuccess) {
			shift(rotatedCollisionCoords, 4);											// Desplaza cuatro a la derecha (dos a la derecha de la posicion original)
			rotationSuccess = attemptRotation(tetro, rotatedCollisionCoords);
		}
		
		if (rotationSuccess) {															// Si la rotacion fue exitosa
			shape = shapeMatrix;														// Actualiza la forma del tetromino
		}
	}
	
}