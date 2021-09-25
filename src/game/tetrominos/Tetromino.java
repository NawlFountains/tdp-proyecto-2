package game.tetrominos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		this.grid = grid; //TODO set coordinates
	}
	
	/**
	 * Rota este tetromino en sentido levogiro.
	 */
	public void rotateLev() {
		int side = shape.length;
		for(int cycle = 0; cycle < side / 2; cycle++) {
			int lastIndex = side - 1 - cycle;
			for(int index = 0; index < lastIndex - cycle; index++) {
				int temp = shape[cycle][cycle + index];
				shape[cycle][cycle + index] = shape[cycle + index][lastIndex];
				shape[cycle + index][lastIndex] = shape[lastIndex][lastIndex - index];
				shape[lastIndex][lastIndex - index] = shape[lastIndex - index][cycle];
				shape[lastIndex - index][cycle] = temp;
			}
		}
	}
	
	/**
	 * Rota este tetromino en sentido dextrogiro.
	 */
	public void rotateDext() {
		int side = shape.length;
		for(int cycle = 0; cycle < side / 2; cycle++) {
			int lastIndex = side - 1 - cycle;
			for(int index = 0; index < lastIndex - cycle; index++) {
				int temp = shape[cycle][cycle + index];
				shape[cycle][cycle + index] = shape[lastIndex - index][cycle];
				shape[lastIndex - index][cycle] = shape[lastIndex][lastIndex - index];
				shape[lastIndex][lastIndex - index] = shape[cycle + index][lastIndex];
				shape[cycle + index][lastIndex] = temp;
			}
		}
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
			Set<List<Integer>> toUpdate = new HashSet<List<Integer>>(8);
			for(Block b : tetrBlocks) {
				grid.removeBlock(b.getX(), b.getY());
				List<Integer> coords = new ArrayList<Integer>(2);
				coords.add(b.getX());
				coords.add(b.getY());
				toUpdate.add(coords);
			}
			for(Block b : tetrBlocks) {
				b.setCoordinates(b.getX(), b.getY() - 1);
				grid.addBlock(b);
				List<Integer> coords = new ArrayList<Integer>(2);
				coords.add(b.getX());
				coords.add(b.getY());
				toUpdate.add(coords);
			}
			notifyGUI(toUpdate);
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
				collisionBlocks[i] = grid.getBlock(tetrBlocks[i].getX(), tetrBlocks[i].getY() - 1);
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
				collisionBlocks[i] = grid.getBlock(tetrBlocks[i].getX() - 1, tetrBlocks[i].getY());
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
				collisionBlocks[i] = grid.getBlock(tetrBlocks[i].getX() + 1, tetrBlocks[i].getY());
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
		if(collidesLeft(tetrBlocks)) {
			Set<List<Integer>> toUpdate = new HashSet<List<Integer>>(8);
			for(Block b : tetrBlocks) {
				grid.removeBlock(b.getX(), b.getY());
				List<Integer> coords = new ArrayList<Integer>(2);
				coords.add(b.getX());
				coords.add(b.getY());
				toUpdate.add(coords);
			}
			for(Block b : tetrBlocks) {
				b.setCoordinates(b.getX() - 1, b.getY());
				grid.addBlock(b);
				List<Integer> coords = new ArrayList<Integer>(2);
				coords.add(b.getX());
				coords.add(b.getY());
				toUpdate.add(coords);
			}
			notifyGUI(toUpdate);
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
		if(collidesLeft(tetrBlocks)) {
			Set<List<Integer>> toUpdate = new HashSet<List<Integer>>(8);
			for(Block b : tetrBlocks) {
				grid.removeBlock(b.getX(), b.getY());
				List<Integer> coords = new ArrayList<Integer>(2);
				coords.add(b.getX());
				coords.add(b.getY());
				toUpdate.add(coords);
			}
			for(Block b : tetrBlocks) {
				b.setCoordinates(b.getX() + 1, b.getY());
				grid.addBlock(b);
				List<Integer> coords = new ArrayList<Integer>(2);
				coords.add(b.getX());
				coords.add(b.getY());
				toUpdate.add(coords);
			}
			notifyGUI(toUpdate);
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
	 * Retorna los un arreglo con los cuatro bloques de este tetromino.
	 * @return Un arreglo con los cuatro bloques de este tetromino.
	 */
	public Block[] getBlocks() {
		Block[] allBlocks = {blocks[0], blocks[1], blocks[2], centroid};
		return allBlocks;
	}
	
	public void notifyGUI(Set<List<Integer>> toUpdate) {
//		for(List<Integer> coords : toUpdate) {
//			grid.getGame().getGUI() TODO
//		}
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
