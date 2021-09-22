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
//	protected [rows][columns] Block matrixBlocks; TODO
	protected Tetromino nextTetromino; 
	protected Tetromino fallingTetromino;
//	protected Game game; TODO
	
	public Grid() {
		//matrixBlocks = new Blocks[rows][columns]; TODO
	}
	
	/**
	 * Chequea que si la linea esta completa
	 * @param entero numero de fila a chequear
	 * @return boolean true si esta llena, false sino.
	 */
	public boolean checkLine(int row) {
		boolean full=false;
		return full;
	}
	
	public int deleteLines() {
		int toReturn=-1;
		return toReturn;
	}
	
	
	public void addTetromino() {}
	
	protected void fall() {}
	
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