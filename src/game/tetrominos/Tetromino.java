package game.tetrominos;

import game.Grid;

/**
 * Modela un tetromino.
 */
public abstract class Tetromino {
	
	public static final int EMPTY = 0;
	public static final int BLOCK = 1;
	public static final int CENTROID = 2;
	
	protected int[][] shape;
	protected boolean collided;
	
	protected Block centroid;
	protected Block[] blocks = new Block[3];
	
	protected Grid grid;
	
	/**
	 * Crea un nuevo tetromino con el color dado.
	 * @param color El color del nuevo tetromino.
	 */
	public Tetromino(Color color) {
		collided = false;
		centroid = new Block(color);
		blocks[0] = new Block(color);
		blocks[1] = new Block(color);
		blocks[2] = new Block(color);
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
	
	public void fall() {
		
	}
	
	public boolean collided() {
		return collided;
	}
	
	public void setGrid(Grid grid) {
		this.grid = grid;
	}
	
	public void moveLeft() {
		
	}
	
	public void moveRight() {
		
	}
	
	/**
	 * Retorna los un arreglo con los cuatro bloques de este tetromino.
	 * @return Un arreglo con los cuatro bloques de este tetromino.
	 */
	public Block[] getBlocks() {
		Block[] allBlocks = {blocks[0], blocks[1], blocks[2], centroid};
		return allBlocks;
	}
	
	public void notifyGUI(int[][] coordinates) {
		
	}
	
}
