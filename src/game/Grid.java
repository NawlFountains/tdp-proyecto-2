package game;

import game.tetrominos.Tetromino;

/**
 * Clase Grilla
 * @author Nahuel Fuentes
 *
 */
public class Grid {
	//Atributos de clase
	protected static final int rows= 21;
	protected static final int columns = 10;
	
	//Atributos de instancia
//	protected [rows][columns] Block matrixBlocks;
	protected Tetromino nextTetromino; 
	protected Tetromino fallingTetromino;
//	protected Game game;
	
	public Grid() {
		//matrixBlocks = new Blocks[rows][columns]; TODO
	}
	
	public boolean checkLine(int row) {
		boolean full=false;
		return full;
	}
	
	public void deleteLines() {}
	
	protected void fall() {}
	
	public void addTetromino() {}
	
	protected void setNextTetromino(Tetromino t) {} 
	
	protected Tetromino randomTetromino() {
		Tetromino toReturn=null;
		return toReturn;
	} 
	
	public Tetromino getFallingTetr() {
		return fallingTetromino;
	}
	
	public Tetromino getNextTetr() {
		return nextTetromino;
	}
	
}