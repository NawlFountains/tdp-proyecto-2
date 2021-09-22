package game.tetrominos;

public abstract class Tetromino {
	protected int[][] shape;
	protected boolean collided;
	protected static final int EMPTY=0;
	protected static final int BLOCK=1;
	protected static final int CENTROID=2;
	
//	public Tetromino(Color color) {
//		collided=false;
//	} TODO
	public void rotateLev() {
		
	}
	public void rotateDext() {
		
	}
	public void fall() {
		
	}
	public boolean collided() {
		return collided;
	}
//	public void setGrid(Grid grid) {
//		
//	} TODO
	public void moveLeft() {
		
	}
	public void moveRight() {
		
	}
	public void notifyGUI(int[][] coordinates) {
		
	}
}
