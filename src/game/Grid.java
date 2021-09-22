package game;

import game.tetrominos.*;

/**
 * Clase Grilla
 * @author Nahuel Fuentes
 *
 */
public class Grid { //TODO: remplazar con eje de coordenadas
	
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
		matrixBlocks = new Block[columns][rows]; //Simula sistema cartesiano de coordeandas
		fallingTetromino = randomTetromino();
		nextTetromino = randomTetromino();
	}
	
	
	/**
	 * Elimina lineas de bloques , llama a fall() y retorna 100,200,500 u 800
	 * , si se elimina 1, 2 ,3 o 4 lineas respectivamente.
	 * @return entero puntos por lineas eliminadas
	 */
	public int deleteLines() { //	TODO: chequear lineas desde el menor bloque al mayor bloque y retornar cuantos puntos hizo y elimina las lineas que se completaron.
		int completedLines=0;
		int points=0;
		int minRow = rows;
		int maxRow = 0;
		
		Block[]  finalBlocks=fallingTetromino.getBlocks(); //Si o si son 4 bloques ya q se trata de un tetromino
		
		for (int i=0; i<=5;i++) { //Buscamos la menor y mayor fila donde hay bloques del tetromnio cayendo
			if (finalBlocks[i].getY()>maxRow) {
				maxRow=finalBlocks[i].getY();
			} else if (finalBlocks[i].getY()<minRow) {
				minRow=finalBlocks[i].getY();
			}
		}
		
		for (int i=minRow; i<=maxRow; i++) { //Vemos si entre las filas donde estaba el tetromino se completaron
			if (checkLine(i)) {
				completedLines++;
//				removeLine(i); remover la linea
			}
		}
		
//		reAdjustGrid(); reajusta la grilla
		
		switch (completedLines) { //Dependiendo de cuantas lineas se completaron retornamos ciertos puntos
			case 1: points = 100; break;
			case 2: points = 200; break;
			case 3: points = 500; break;
			case 4: points = 800; break;
			default: points = 0;
		}
		return points;
	}
	
	/**
	 * Crea y agrega un tetromino a la grilla
	 */
	public void addTetromino() {
		fallingTetromino=nextTetromino;
		nextTetromino= randomTetromino();
	}
	
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
			case 0: toReturn= new Tetromino_I(Color.CYAN); break;
			case 1: toReturn = new Tetromino_J(Color.BLUE); break;
			case 2: toReturn = new Tetromino_L(Color.ORANGE); break;
			case 3: toReturn = new Tetromino_O(Color.YELLOW); break;
			case 4: toReturn = new Tetromino_S(Color.GREEN); break;
			case 5: toReturn= new Tetromino_T(Color.PURPLE); break;
			default: toReturn= new Tetromino_Z(Color.RED); break; //Este caso aplica tanto para si sale un 6 como si sale cualquier otro numero, es por seguridad.
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
		if (row<0 || row>=rows) { //	TODO: 	excepcion fila invalida
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
		return matrixBlocks[x][y];  //Se conoce que puede ser null, la reponsabilidad es del cliente
	}
	
	/**
	 * Remueve el bloque en las coordenadas pasadas
	 * @param entero x columna
	 * @param entero y fila
	 */
	public void removeBlock(int x, int y) {
		matrixBlocks[x][y]=null;
	}
	
	/**
	 * Agrega un bloque a su posicion.
	 * @param Block bloque a añadir.
	 */
	public void addBlock(Block b) { //El responsable de llamar a este metodo se asegura que la posicion es valida, no colisiona, y no es nula.
		matrixBlocks[b.getX()][b.getY()]=b;
	}
	
}