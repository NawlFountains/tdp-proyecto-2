package game.tetrominos;

import game.Grid;

public abstract class Tetromino {
	
	protected int[][] shape;
	protected boolean collided;
	
	protected Block centroid;
	protected Block[] blocks = new Block[3];
	
	protected static final int EMPTY = 0;
	protected static final int BLOCK = 1;
	protected static final int CENTROID = 2;
	
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
	
	public void rotateLev() {
		
	}
	
	public void rotateDext() {
		
	}
	
	public void fall() {
		
	}
	
	public boolean collided() {
		return collided;
	}
	
	public void setGrid(Grid grid) {
		
	}
	
	public void moveLeft() {
		
	}
	
	public void moveRight() {
		
	}
	
	public void notifyGUI(int[][] coordinates) {
		
	}
	
}
