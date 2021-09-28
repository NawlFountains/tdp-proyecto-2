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
		Block[] tetro = getBlocks();
		
		int[][] rotatedCollisionCoords = new int[tetro.length][];
		
		int[] centroidPosInShape = getCentroidPosInShape(shapeMatrix);
		int collisionCoordsIndex = 0;
		boolean rotationSuccess = false;
		
		rotatedCollisionCoords[collisionCoordsIndex++] = new int[] {centroid.getX(), centroid.getY()};
		
		for(int x = 0; x < shapeMatrix.length; x++)
			for(int y = 0; y < shapeMatrix[0].length; y++)
				if(shapeMatrix[x][y] == BLOCK) {
					int rotatedX = centroid.getX() - centroidPosInShape[0] + x;
					int rotatedY = centroid.getY() - centroidPosInShape[1] + y;
					rotatedCollisionCoords[collisionCoordsIndex++] = new int[] {rotatedX, rotatedY};
				}
		
		if( !outOfBounds(rotatedCollisionCoords) && !collidesWith(tetro, blocksFromCoords(rotatedCollisionCoords)) ) {
			reposition(rotatedCollisionCoords);
			rotationSuccess = true;
		} else {
			shift(rotatedCollisionCoords, -1);
			if( !outOfBounds(rotatedCollisionCoords) && !collidesWith(tetro, blocksFromCoords(rotatedCollisionCoords)) ) {
				reposition(rotatedCollisionCoords);
				rotationSuccess = true;
			} else {
				shift(rotatedCollisionCoords, 2);
				if( !outOfBounds(rotatedCollisionCoords) && !collidesWith(tetro, blocksFromCoords(rotatedCollisionCoords)) ) {
					reposition(rotatedCollisionCoords);
					rotationSuccess = true;
				} else {
					shift(rotatedCollisionCoords, -3);
					if( !outOfBounds(rotatedCollisionCoords) && !collidesWith(tetro, blocksFromCoords(rotatedCollisionCoords)) ) {
						reposition(rotatedCollisionCoords);
						rotationSuccess = true;
					} else {
						shift(rotatedCollisionCoords, 4);
						if( !outOfBounds(rotatedCollisionCoords) && !collidesWith(tetro, blocksFromCoords(rotatedCollisionCoords)) ) {
							reposition(rotatedCollisionCoords);
							rotationSuccess = true;
						}
					}
				}
			}
		}
		if(rotationSuccess)
			shape = shapeMatrix;
	}
	
}