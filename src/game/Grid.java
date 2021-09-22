package game;

import game.tetrominos.*;

/**
 * Clase Grilla
 * @author Nahuel Fuentes
 *
 */
public class Grid {
	
	//Atributos de clase
	protected static final int rows = 21;
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
	 * Metodo que le avisa al tetromino que debe bajar
	 */
	protected void fall() {}
	
	/**
	 * Asigna un tetromino a ser el proximo tetromino
	 * @param Tetromino t a ser siguiente tetromino
	 */
	protected void setNextTetromino(Tetromino t) {
		if (t!=null) {
			nextTetromino=t;
		}
	} 
	
	/**
	 * Crea y retorna un tetromino con forma aleatoria
	 * @return Tetromino 
	 */
	protected Tetromino randomTetromino() {
		Tetromino toReturn;
		int tirada= (int) Math.random()* 7; //Retorna un numero entre [0,7), no se incluye el 7.
		switch (tirada) {
			case 0: toReturn= new Tetromino_I(Color.CYAN);
			case 1: toReturn = new Tetromino_J(Color.BLUE);
			case 2: toReturn = new Tetromino_L(Color.ORANGE);
			case 3: toReturn = new Tetromino_O(Color.YELLOW);
			case 4: toReturn = new Tetromino_S(Color.GREEN);
			case 5: toReturn= new Tetromino_T(Color.PURPLE);
			default: toReturn= new Tetromino_Z(Color.RED); //Este caso aplica tanto para si sale un 6 como si sale cualquier otro numero, es por seguridad.
		}
		return toReturn;
	} 
	
	/**
	 * Chequea que si la linea esta completa
	 * @param entero numero de fila a chequear
	 * @return boolean true si esta llena, false sino.
	 */
	public boolean checkLine(int row) {
		boolean full=true; //Asumimos que esta llena y probamos si no lo esta.
		int pointer=0;
		if (row<0 || row>=rows) { //TODO: excepcion fila invalida
			full=false; //Si la fila no pertence a la grilla declaramos full en false asi no se mete al while.
		}
		while (full && pointer<columns){
			if (matrixBlocks[row][pointer]==null) {
				full=false;
			} else {
				pointer++;
			}
		}
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
	
	/**
	 * Consulta filas de la grilla
	 * @return entero filas
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * Consulta columnas de la grilla
	 * @return entero filas
	 */
	public int getColumns() {
		return columns;
	}
	
	/**
	 * Consulta el bloque en las coordenadas pasadas
	 * @param entero x columna
	 * @param enter y fila
	 * @return Referencia a Block si existe un bloque alli, null sino.
	 */
	public Block getBlock(int x, int y) {
		return matrixBlocks[x][y];
	}
	
}