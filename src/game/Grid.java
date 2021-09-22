package game;

import game.tetrominos.*;

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
	protected Block [][] matrixBlocks; 
	protected Tetromino nextTetromino; 
	protected Tetromino fallingTetromino;
	protected Game game; 
	
	//Metodos
	
	/**
	 * Constructor de la clase Grid
	 * Inicializa la matriz de bloques
	 */
	public Grid(Game game) {
		this.game=game;
		matrixBlocks = new Block[rows][columns];
		fallingTetromino = randomTetromino();
		nextTetromino = randomTetromino();
	}
	
	
	/**
	 * Elimina lineas de bloques , llama a fall() y retorna 100,200,500 u 800
	 * , si se elminan 1, 2 ,3 o 4 lineas respectivamente.
	 * @return entero puntos por lineas eliminadas
	 */
	public int deleteLines() {
		int toReturn=0;
		return toReturn;
	}
	
	/**
	 * Crea y agrega un tetromino a la grilla
	 */
	public void addTetromino() {}
	
	/**
	 * Asigna un tetromino a ser el proximo tetromino
	 * @param Tetromino t a ser siguiente tetromino
	 */
	protected void setNextTetromino(Tetromino t) {} 
	
	/**
	 * Metodo que le avisa al tetromino que debe bajar
	 */
	protected void fall() {}
	
	/**
	 * Crea y retorna un tetromino con forma aleatoria
	 * @return Tetromino 
	 */
	protected Tetromino randomTetromino() {
		Tetromino toReturn=null;
		int tirada= (int) Math.random()* 7; //Retorna un numero entre [0,7), no se incluye el 7.
		switch (tirada) {
			case 0: toReturn= new Tetromino_I(Color.CYAN);
			case 1: toReturn = new Tetromino_J(Color.BLUE);
			case 2: toReturn = new Tetromino_L(Color.ORANGE);
			case 3: toReturn = new Tetromino_O(Color.YELLOW);
			case 4: toReturn = new Tetromino_S(Color.GREEN);
			case 5: toReturn= new Tetromino_T(Color.CYAN);
			default: toReturn= new Tetromino_Z(Color.CYAN);
		}
		return toReturn;
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
	
	/**
	 * Consulta el tetromino que esta cayendo
	 * @return Tetromino cayendo
	 */
	public Tetromino getFallingTetr() {
		return fallingTetromino;
	}
	
	/**
	 * Consulta el siguiente tetrominio
	 * @return Tetromino siguiente
	 */
	public Tetromino getNextTetr() {
		return nextTetromino;
	}
	
}