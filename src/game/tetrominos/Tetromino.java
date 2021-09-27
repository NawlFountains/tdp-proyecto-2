package game.tetrominos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import exceptions.GridException;
import exceptions.TetrominoException;
import game.Grid;

/**
 * Modela un tetromino.
 */
public abstract class Tetromino {
	
	public static final int EMPTY = 0;
	public static final int BLOCK = 1;
	public static final int CENTROID = 2;
	
	protected int[][] shape;
	protected boolean falling;
	
	protected Block centroid;
	protected Block[] blocks = new Block[3];
	
	protected Grid grid;
	
	/**
	 * Crea un nuevo tetromino con el color dado.
	 * @param color El color del nuevo tetromino.
	 */
	public Tetromino(Color color) {
		falling = true;
		centroid  = new Block(color);
		blocks[0] = new Block(color);
		blocks[1] = new Block(color);
		blocks[2] = new Block(color);
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
		
		int[] centroidCoordsInShape = getCentroidPosInShape(shape);
		centroid.setCoordinates(Grid.COLUMNS / 2, Grid.ROWS - 1 - (shape.length - 1 - centroidCoordsInShape[1]));
		
		int i = 0;
		for(int x = 0; x < shape.length && i < 3; x++)
			for(int y = 0; y < shape[0].length && i < 3; y++)
				if(shape[x][y] == BLOCK) {
					int blockX = centroid.getX() - centroidCoordsInShape[0] + x;
					int blockY = centroid.getY() - centroidCoordsInShape[1] + y;
					blocks[i++].setCoordinates(blockX, blockY);
				}
		for(Block block : getBlocks()) {
			grid.addBlock(block);
		}
	}
	
	/**
	 * Rota este tetromino en sentido levogiro.
	 */
	public void rotateLev() {
		int[][] shapeMatrix = getShape();
		rotateMatrixLev(shapeMatrix);
		rotate(shapeMatrix);
	}
	
	/**
	 * Rota este tetromino en sentido dextrogiro.
	 */
	public void rotateDext() {
		int[][] shapeMatrix = getShape();
		rotateMatrixDext(shapeMatrix);
		rotate(shapeMatrix);
	}
	
	/**
	 * Rota este tetromino segun la matriz dada.
	 */
	public void rotate(int[][] shapeMatrix) {
		Block[] tetro = getBlocks();
		List<int[]> rotatedCollisionCoords = new ArrayList<int[]>(tetro.length);
		int[] centroidPos = getCentroidPosInShape(shapeMatrix);
		for(int i = 0; i < shapeMatrix.length; i++)
			for(int j = 0; j < shapeMatrix[0].length; j++)
				if(shapeMatrix[i][j] != EMPTY) {
					int rotatedX = centroid.getX() - centroidPos[0] + i;
					int rotatedY = centroid.getY() - centroidPos[1] + j;
					rotatedCollisionCoords.add(new int[] {rotatedX, rotatedY});
				}
		if( !outOfBounds(rotatedCollisionCoords) && !collidesWith(tetro, blocksFromCoords(rotatedCollisionCoords)) ) {
			reposition((int[][])rotatedCollisionCoords.toArray());
		} else {
			shift(rotatedCollisionCoords, -1);
			if( !outOfBounds(rotatedCollisionCoords) && !collidesWith(tetro, blocksFromCoords(rotatedCollisionCoords)) ) {
				reposition((int[][])rotatedCollisionCoords.toArray());
			} else {
				shift(rotatedCollisionCoords, 2);
				if( !outOfBounds(rotatedCollisionCoords) && !collidesWith(tetro, blocksFromCoords(rotatedCollisionCoords)) ) {
					reposition((int[][])rotatedCollisionCoords.toArray());
				}
			}
		}
	}
	
	/**
	 * Chequea si alguna coordenada de la lista pasada como parametro esta fuera de los limites de la grilla.
	 * @param coords Una lista de coordenadas expresadas como arreglo arr de dos componentes tal que arr[0] == x, arr[1] == y.
	 * @return True si alguna de las coordenadas esta fuera de los limites de la grilla, false si no.
	 */
	private boolean outOfBounds(List<int[]> coords) {
		boolean outOfBounds = false;
		int[] pair;
		for(int i = 0; i < coords.size() && !outOfBounds; i++) {
			pair = coords.get(i);
			if(0 < pair[0] && pair[0] < Grid.COLUMNS && 0 < pair[1] && pair[1] < Grid.ROWS)
				outOfBounds = true;
		}
		return outOfBounds;
	}
	
	/**
	 * Mueve la coordenada x de la lista de coordenadas pasada como parametro en la cantidad de unidades dada.
	 * @param coords Una lista de coordenadas expresadas como arreglo arr de dos componentes tal que arr[0] == x, arr[1] == y.
	 * @param shift El valor en el cual se desplazara la coordenada x de cada par de coordenadas.
	 */
	private void shift(List<int[]> coords, int shift) {
		for(int i = 0; i < coords.size(); i++) {
			coords.get(i)[0] += shift;
		}
	}
	
	/**
	 * Dada una lista de coordenadas, retorna un arreglo de los bloques que se encuentran en esas coordenadas en la grilla.
	 * Este metodo no chequea la validez de las coordenadas pasadas como parametro.
	 * @param coords Una lista de coordenadas expresadas como arreglo arr de dos componentes tal que arr[0] == x, arr[1] == y.
	 * @return Un arreglo con los bloques que se encuantran en las coordenadas pasadas como parametro.
	 */
	private Block[] blocksFromCoords(List<int[]> coords) {
		int[] pair;
		Block[] blocks = new Block[coords.size()];
		for(int i = 0; i < coords.size(); i++) {
			pair = coords.get(i);
			try {
				blocks[i] = grid.getBlock(pair[0], pair[1]);
			} catch(GridException e) {
				e.printStackTrace(); // TODO printstacktrace
			}
		}
		return blocks;
	}
	
	/**
	 * Retorna la posicion del centroide en la matriz dada.
	 * @param shapeMatrix Una matriz de enteros.
	 * @return Las coordenadas del centroide, como arreglo arr de 2 componentes tal que arr[0] = x, arr[1] = y. 
	 */
	protected int[] getCentroidPosInShape(int[][] shapeMatrix) {
		int[] coords = new int[2];
		boolean found = false;
		for(int x = 0; x < shapeMatrix.length && !found; x++)
			for(int y = 0; y < shapeMatrix[0].length && !found; y++)
				if(shapeMatrix[x][y] == CENTROID) {
					coords[0] = x;
					coords[1] = y;
					found = true;
				}
		return coords;
	}
	
	/**
	 * Reposiciona los bloques de este tetromino en la grilla de acuerdo a las coordenadas pasadas como parametro.
	 * @param newBlockCoords Las nuevas coordenadas de este tetromino en la grilla.
	 */
	protected void reposition(int[][] newBlockCoords) {
		Set<List<Integer>> toUpdate = new HashSet<List<Integer>>(8);
		// Remueve bloques
		for(Block block : blocks) {
			try {
				grid.removeBlock(block.getX(), block.getY());
			} catch(GridException e) {
				e.printStackTrace(); // TODO printstacktrace
			}
			toUpdate.add(Arrays.asList(new Integer[] {block.getX(), block.getY()}));
		}
		// Añade bloques
		for(int i = 0; i < blocks.length; i++) {
			blocks[i].setCoordinates(newBlockCoords[i][0], newBlockCoords[i][1]);
			grid.addBlock(blocks[i]);
			toUpdate.add(Arrays.asList(new Integer[] {blocks[i].getX(), blocks[i].getY()}));
		}
		notifyGUI(toUpdate);
	}
	
	/**
	 * Hace caer a este tetromino.
	 * @throws TetrominoException Si este tetromino no esta cayendo.
	 */
	public void fall() throws TetrominoException {
		if(!falling)
			throw new TetrominoException("Se intento hacer caer un tetromino que no esta cayendo");
		Block[] tetrBlocks = getBlocks();
		if(collidesBottom(tetrBlocks)) {
			falling = false;
		} else {
			int[][] newBlockCoords = new int[tetrBlocks.length][];
			for(int i = 0; i < tetrBlocks.length; i++) {
				newBlockCoords[i] = new int[] {tetrBlocks[i].getX(), tetrBlocks[i].getY() - 1};
			}
			reposition(newBlockCoords);
		}
	}
	
	/**
	 * Chequea si el tetromino pasado como parametro como arreglo de sus 4 bloques colisiona con algun bloque hacia abajo.
	 * @param tetrBlocks Un arreglo de bloques.
	 * @return true si el tetromino colisiona con algun bloque hacia abajo, falso si no.
	 */
	protected boolean collidesBottom(Block[] tetrBlocks) {
		Block[] collisionBlocks = new Block[tetrBlocks.length];
		boolean collides = false;
		for(int i = 0; i < tetrBlocks.length; i++) {
			if(tetrBlocks[i].getY() - 1 >= 0)
				try {
					collisionBlocks[i] = grid.getBlock(tetrBlocks[i].getX(), tetrBlocks[i].getY() - 1);
				} catch(GridException e) {
					e.printStackTrace(); // TODO printstacktrace
				}
			else
				collides = true;
		}
		collides = collides || collidesWith(tetrBlocks, collisionBlocks);
		return collides;
	}
	
	/**
	 * Chequea si el tetromino pasado como parametro como arreglo de sus 4 bloques colisiona con algun bloque hacia la izquierda.
	 * @param tetrBlocks Un arreglo de bloques.
	 * @return true si el tetromino colisiona con algun bloque hacia la izquierda, falso si no.
	 */
	protected boolean collidesLeft(Block[] tetrBlocks) {
		Block[] collisionBlocks = new Block[tetrBlocks.length];
		boolean collides = false;
		for(int i = 0; i < tetrBlocks.length; i++) {
			if(tetrBlocks[i].getX() - 1 >= 0)
				try {
					collisionBlocks[i] = grid.getBlock(tetrBlocks[i].getX() - 1, tetrBlocks[i].getY());
				} catch(GridException e) {
					e.printStackTrace(); // TODO printstacktrace
				}
			else
				collides = true;
		}
		collides = collides || collidesWith(tetrBlocks, collisionBlocks);
		return collides;
	}
	
	/**
	 * Chequea si el tetromino pasado como parametro como arreglo de sus 4 bloques colisiona con algun bloque hacia la derecha.
	 * @param tetrBlocks Un arreglo de bloques.
	 * @return true si el tetromino colisiona con algun bloque hacia la derecha, falso si no.
	 */
	protected boolean collidesRight(Block[] tetrBlocks) {
		Block[] collisionBlocks = new Block[tetrBlocks.length];
		boolean collides = false;
		for(int i = 0; i < tetrBlocks.length; i++) {
			if(tetrBlocks[i].getX() + 1 <= Grid.COLUMNS)
				try {
					collisionBlocks[i] = grid.getBlock(tetrBlocks[i].getX() + 1, tetrBlocks[i].getY());
				} catch(GridException e) {
					e.printStackTrace(); // TODO printstacktrace
				}
			else
				collides = true;
		}
		collides = collides || collidesWith(tetrBlocks, collisionBlocks);
		return collides;
	}
	
	/**
	 * Retorna false si los bloques con los que colisiona el tetromino (arreglo) son nulos, o bien pertenecen al mismo tetromino.
	 * @param tetrBlocks Un tetromino expresado como arreglo de sus 4 bloques.
	 * @param collisionBlocks Un arreglo de bloques, adyacentes a los bloques del primer arreglo.
	 * @return False si los bloques con los que se colisiona son nulos o pertenecen al tetromino.
	 */
	protected boolean collidesWith(Block[] tetrBlocks, Block[] collisionBlocks) {
		boolean collides = false;
		boolean isAutoCollision;
		for(int i = 0; i < collisionBlocks.length && !collides; i++) {
			if(collisionBlocks[i] != null) {
				isAutoCollision = false;
				for(int j = 0; j < tetrBlocks.length && !isAutoCollision; j++)
					if(collisionBlocks[i].equals(tetrBlocks[j]))
						isAutoCollision = true;
				if(!isAutoCollision)
					collides = true;
			}
		}
		return collides;
	}
	
	/**
	 * Retorna si este tetromino esta cayendo o no.
	 * @return True si este tetromino esta cayendo, falso si no.
	 */
	public boolean isFalling() {
		return falling;
	}
	
	/**
	 * Mueve este tetromino hacia la izquierda en su grilla.
	 * @throws TetrominoException Si este tetromino no esta cayendo, y por lo tanto no se puede mover.
	 */
	public void moveLeft() throws TetrominoException {
		if(!falling)
			throw new TetrominoException("Se intento mover un tetromino que no esta cayendo");
		Block[] tetrBlocks = getBlocks();
		if(!collidesLeft(tetrBlocks)) {
			int[][] newBlockCoords = new int[tetrBlocks.length][];
			for(int i = 0; i < tetrBlocks.length; i++) {
				newBlockCoords[i] = new int[] {tetrBlocks[i].getX() - 1, tetrBlocks[i].getY()};
			}
			reposition(newBlockCoords);
		}
	}
	
	/**
	 * Mueve este tetromino hacia la derecha en su grilla.
	 * @throws TetrominoException Si este tetromino no esta cayendo, y por lo tanto no se puede mover.
	 */
	public void moveRight() throws TetrominoException {
		if(!falling)
			throw new TetrominoException("Se intento mover un tetromino que no esta cayendo");
		Block[] tetrBlocks = getBlocks();
		if(!collidesRight(tetrBlocks)) {
			int[][] newBlockCoords = new int[tetrBlocks.length][];
			for(int i = 0; i < tetrBlocks.length; i++) {
				newBlockCoords[i] = new int[] {tetrBlocks[i].getX() + 1, tetrBlocks[i].getY()};
			}
			reposition(newBlockCoords);
		}
	}
	
	/**
	 * Retorna una copia de la matriz que representa la forma de este tetromino.
	 * @return Una matriz de enteros que representa la forma de este tetromino.
	 */
	public int[][] getShape(){
		int side = shape.length;
		int[][] matrix = new int[side][side];
		for(int i = 0; i < side; i++)
			for(int j = 0; j < side; j++)
				matrix[i][j] = shape[i][j];
		return matrix;
	}
	
	/**
	 * Retorna los un arreglo con los cuatro bloques de este tetromino, el centroide se encuantra en la posicion 0.
	 * @return Un arreglo con los cuatro bloques de este tetromino.
	 */
	public Block[] getBlocks() {
		Block[] allBlocks = {centroid, blocks[0], blocks[1], blocks[2]};
		return allBlocks;
	}
	
	public void notifyGUI(Set<List<Integer>> toUpdate) {
		for(List<Integer> coords : toUpdate) {
			grid.getGame().getGUI().updateCell(coords.get(0), coords.get(1));
		}
	}
	
	/**
	 * Rota la matriz pasada como parametro en sentido dextrogiro.
	 * @param matrix Una matriz cuadrada de enteros.
	 */
	protected static void rotateMatrixDext(int[][] matrix) {
		int side = matrix.length;
		for(int cycle = 0; cycle < side / 2; cycle++) {
			int lastIndex = side - 1 - cycle;
			for(int index = 0; index < lastIndex - cycle; index++) {
				int temp = matrix[cycle][cycle + index];
				matrix[cycle][cycle + index] = matrix[lastIndex - index][cycle];
				matrix[lastIndex - index][cycle] = matrix[lastIndex][lastIndex - index];
				matrix[lastIndex][lastIndex - index] = matrix[cycle + index][lastIndex];
				matrix[cycle + index][lastIndex] = temp;
			}
		}
	}
	
	/**
	 * Rota la matriz pasada como parametro en sentido levogiro.
	 * @param matrix Una matriz cuadrada de enteros.
	 */
	protected static void rotateMatrixLev(int[][] matrix) {
		int side = matrix.length;
		for(int cycle = 0; cycle < side / 2; cycle++) {
			int lastIndex = side - 1 - cycle;
			for(int index = 0; index < lastIndex - cycle; index++) {
				int temp = matrix[cycle][cycle + index];
				matrix[cycle][cycle + index] = matrix[cycle + index][lastIndex];
				matrix[cycle + index][lastIndex] = matrix[lastIndex][lastIndex - index];
				matrix[lastIndex][lastIndex - index] = matrix[lastIndex - index][cycle];
				matrix[lastIndex - index][cycle] = temp;
			}
		}
	}
	
}
