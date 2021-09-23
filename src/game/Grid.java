package game;

import java.util.Random;

import game.tetrominos.*;

/**
 * Modela una grilla.
 * @author Nahuel Fuentes
 */
public class Grid { //TODO: remplazar con eje de coordenadas
	
	//Atributos de clase
	protected static final int ROWS = 21;
	protected static final int COLUMNS = 10;
	
	//Atributos de instancia
	protected Block [][] blockMatrix; 
	protected Tetromino nextTetromino; 
	protected Tetromino fallingTetromino;
	protected Game game; 
	
	//Metodos
	
	/**
	 * Crea una nueva grilla, inicializando su matriz de bloques.
	 */
	public Grid(Game game) {
		this.game=game;
		blockMatrix = new Block[COLUMNS][ROWS]; //Simula sistema cartesiano de coordeandas
		fallingTetromino = randomTetromino();
		nextTetromino = randomTetromino();
	}
	
	
	/**
	 * Elimina lineas de bloques , llama a fall() y retorna 100,200,500 u 800, si se elimina 1, 2 ,3 o 4 lineas respectivamente.
	 * @return Un entero positivo, representando los puntos obtenidos por lineas eliminadas.
	 */
	public int deleteLines() { //	TODO: chequear lineas desde el menor bloque al mayor bloque y retornar cuantos puntos hizo y elimina las lineas que se completaron.
		int completedLines = 0;
		int points = 0;
		int minRow = ROWS;
		int maxRow = 0;
		
		Block[] finalBlocks = fallingTetromino.getBlocks(); //Si o si son 4 bloques ya q se trata de un tetromino
		
		for (int i = 0; i <= 5; i++) { //Buscamos la menor y mayor fila donde hay bloques del tetromnio cayendo
			if (finalBlocks[i].getY() > maxRow) {
				maxRow=finalBlocks[i].getY();
			} else if (finalBlocks[i].getY() < minRow) {
				minRow = finalBlocks[i].getY();
			}
		}
		
		for (int i = minRow; i <= maxRow; i++) { //Vemos si entre las filas donde estaba el tetromino se completaron
			if (checkLine(i)) {
				completedLines++;
				removeLine(i); //remover la linea si esta completa
			}
		}
		
		reAdjustGrid(); //Rejustamos luego de eliminar las lineas ya que asi no perdemos las posiciones
		
		switch (completedLines) { //Dependiendo de cuantas lineas se completaron retornamos ciertos puntos
			case 1:	points = 100;	break;
			case 2:	points = 200;	break;
			case 3:	points = 500;	break;
			case 4:	points = 800;	break;
			default: points = 0;
		}
		return points;
	}
	
	/**
	 * Remueve todos los elementos de la linea pasada por parametro
	 * @param linea a remover
	 */
	private void removeLine(int line) { // TODO: necesito avisarle a los bloques que se vayan de la grilla o basta con quitar su referencia?
		for (int x=0; x < COLUMNS; x++) {
			removeBlock(x,line);
		}
	}
	
	/**
	 * Reajusta la grilla para que no haya filas vacia entre filas no vacias.
	 */
	private void reAdjustGrid() {
		for (int y = 0; y < ROWS; y++ ) {
			if (isEmptyLine(y)) { 
				int nxtNonEmptyLine = searchNextNonEmptyLine(y);
				if (nxtNonEmptyLine == -1){ //NO encontro linea siguiente no vacia
					y = ROWS;
				} else {
					for (int x = 0; x < COLUMNS; x++ ) {
						if (blockMatrix[x][nxtNonEmptyLine]!=null) {
							blockMatrix[x][nxtNonEmptyLine].setCoordinates(x, y); //Cambio coordenadas de bloque a fila vacia
							addBlock(blockMatrix[x][nxtNonEmptyLine]); //Grilla agrega bloque 
							removeBlock(x,nxtNonEmptyLine); //Grilla borra el bloque relocado.
						}
					}
				}
			}
		}
	}
	
	/**
	 * Consulta si la linea pasada por parametro esta vacia
	 * @param linea a consultar
	 * @return true si esta vacia, false sino
	 */
	private boolean isEmptyLine(int line) {
		boolean empty=true;
		int x=0;
		while (empty && x < COLUMNS) {
			if (blockMatrix[x][line]!=null) {
				empty=false;
			}
			x++;
		}
		return empty;
	}
	
	
	/**
	 * Busca la proxima linea no vacia partiendo de la pasada por parametro
	 * @param linea desde donde buscar 
	 * @return entero linea encontrada que no es vacia, -1 si no encontro.
	 */
	private int searchNextNonEmptyLine(int line) {
		int nxtLine = -1;
		int y = line;
		while (y < ROWS && nxtLine == -1) {
			if (!isEmptyLine(y)) {
				nxtLine=y;
			}
			y++;
		}
		return nxtLine;
	}
	
	/**
	 * Agrega el siguiente tetromino a la grilla y crea un tetromino que queda en espera.
	 */
	public void addTetromino() {
		fallingTetromino = nextTetromino;
		nextTetromino = randomTetromino();
	}
	
	/**
	 * Actualiza esta grilla despues de la eliminacion de una o mas filas, haciendo caer todos los bloques que se encuentren por encima de estas.
	 */
	protected void fall() {}
	
	/**
	 * Asigna un tetromino a ser el proximo tetromino.
	 * @param Tetromino t a ser siguiente tetromino.
	 */
	protected void setNextTetromino(Tetromino t) {
		if (t!=null) {
			nextTetromino=t;
		}
	} 
	
	/**
	 * Crea y retorna un tetromino con forma aleatoria.
	 * @return Un tetromino generado aleatoriamente.
	 */
	protected Tetromino randomTetromino() {
		Tetromino toReturn = null;
		Random rnd = new Random();		
		int tirada = rnd.nextInt(6); //Retorna un numero entre [0,7), no se incluye el 7.
		switch (tirada) {
			case 0: toReturn = new Tetromino_I(Color.CYAN);		break;
			case 1: toReturn = new Tetromino_J(Color.BLUE);		break;
			case 2: toReturn = new Tetromino_L(Color.ORANGE);	break;
			case 3: toReturn = new Tetromino_O(Color.YELLOW);	break;
			case 4: toReturn = new Tetromino_S(Color.GREEN);	break;
			case 5: toReturn = new Tetromino_T(Color.PURPLE);	break;
			case 6: toReturn = new Tetromino_Z(Color.RED);		break;
		}
		return toReturn;
	} 
	
	/**
	 * Chequea si la linea esta completa.
	 * @param Numero de fila a chequear.
	 * @return True si la linea esta llena, false si no.
	 */
	public boolean checkLine(int row) {
		boolean full = true; //Asumimos que esta llena y probamos si no lo esta.
		int pointer = 0;
		if(row < 0 || row >= ROWS) { //	TODO: 	excepcion fila invalida
			full = false; //Si la fila no pertence a la grilla declaramos full en false asi no se mete al while.
		}
		while (full && pointer < COLUMNS){
			if (blockMatrix[pointer][row] == null)
				full = false;
			pointer++;
		}
		return full;
	}
	
	/**
	 * Consulta el tetromino que esta cayendo.
	 * @return El tetromino que esta cayendo.
	 */
	public Tetromino getFallingTetr() {
		return fallingTetromino;
	}
	
	/**
	 * Consulta el siguiente tetromino.
	 * @return El siguiente tetromino.
	 */
	public Tetromino getNextTetr() {
		return nextTetromino;
	}
	
	/**
	 * Retorna la cantidad de filas de una grilla.
	 * @return Cantidad de filas de una grilla.
	 */
	public int getRows() {
		return ROWS;
	}

	/**
	 * Retorna la cantidad de columnas de una grilla.
	 * @return Cantidad de columnas de una grilla.
	 */
	public int getColumns() {
		return COLUMNS;
	}
	
	/**
	 * Consulta el bloque en las coordenadas pasadas como parametro.
	 * @param Coordenada x.
	 * @param Coordenada y.
	 * @return El bloque que se encuentra en las coordenadas pasadas como parametro, null si dicho bloque no existe.
	 */
	public Block getBlock(int x, int y) {
		return blockMatrix[x][y];  //Se conoce que puede ser null, la reponsabilidad es del cliente
	}
	
	/**
	 * Remueve el bloque en las coordenadas pasadas como parametro.
	 * @param Coordenada x.
	 * @param Coordenada y.
	 */
	public void removeBlock(int x, int y) {
		blockMatrix[x][y] = null;
	}
	
	/**
	 * Agrega un bloque a la grilla.
	 * @param Bloque a a�adir.
	 */
	public void addBlock(Block b) { //El responsable de llamar a este metodo se asegura que la posicion es valida, no colisiona, y no es nula.
		blockMatrix[b.getX()][b.getY()] = b;
	}
	
}