package game;

import java.util.Random;

import exceptions.GridException;
import game.tetrominos.*;

/**
 * Modela una grilla.
 * @author Nahuel Fuentes
 */
public class Grid {
	
	//Atributos de clase
	public static final int ROWS = 21;
	public static final int COLUMNS = 10;
	
	//Atributos de instancia
	protected Block [][] blockMatrix; 
	protected Tetromino nextTetromino; 
	protected Tetromino fallingTetromino;
	protected Game game; 
	
	//Metodos
	
	/**
	 * Crea una nueva grilla, inicializando su matriz de bloques y preparando tetrominos para empezar el juego.
	 */
	public Grid(Game game) {
		this.game = game;
		blockMatrix = new Block[COLUMNS][ROWS]; //Simula sistema cartesiano de coordeandas
		fallingTetromino = randomTetromino();
		fallingTetromino.setGrid(this);
		nextTetromino = randomTetromino();
	}
	
	
	/**
	 * Elimina lineas de bloques , llama a fall() y retorna 100,200,500 u 800, si se elimina 1, 2 ,3 o 4 lineas respectivamente.
	 * @return Un entero positivo, representando los puntos obtenidos por lineas eliminadas.
	 */
	public synchronized void deleteLines() {
		int completedLines = 0;
		int points = 0;
		int minRow = ROWS;
		int maxRow = 0;
		
		Block[] finalBlocks = fallingTetromino.getBlocks(); //Si o si son 4 bloques ya q se trata de un tetromino
		
		for (int i = 0; i < 4; i++) { //Buscamos la menor y mayor fila donde hay bloques del tetromnio cayendo
			if ( finalBlocks[i].getY() > maxRow ) {
				maxRow = finalBlocks[i].getY();
			} else if ( finalBlocks[i].getY() < minRow ) {
				minRow = finalBlocks[i].getY();
			}
		}
		
		for (int y = minRow; y <= maxRow; y++) { //Vemos si entre las filas donde estaba el tetromino se completaron
			try {
				if ( checkLine(y) ) {
					completedLines++;
					removeLine(y); //remover la linea si esta completa
				}
			} catch ( GridException e ) {
				e.printStackTrace();
			}
		}
		
		fall(); 
		
		switch (completedLines) { //Dependiendo de cuantas lineas se completaron retornamos ciertos puntos
			case 1:	points = 100;	break;
			case 2:	points = 200;	break;
			case 3:	points = 500;	break;
			case 4:	points = 800;	break;
			default: points = 0;
		}
		
		game.addPoints(points);
		game.getGUI().updatePoints();
	}
	
	/**
	 * Remueve todos los elementos de la linea pasada por parametro
	 * @param linea a remover
	 */
	private void removeLine(int line) {
		for (int x = 0; x < COLUMNS; x++) { //Recorremos la linea eliminando todos los elementos
			try {
				removeBlock(x, line);
			} catch (GridException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Consulta si la linea pasada por parametro esta vacia
	 * @param line linea a consultar
	 * @return true si esta vacia, false sino
	 */
	private boolean isEmptyLine(int line) {
		boolean empty = true;
		int x = 0;
		while (empty && x < COLUMNS) {
			if (blockMatrix[x][line] != null) {
				empty = false;
			}
			x++;
		}
		return empty;
	}
	
	
	/**
	 * Busca la proxima linea no vacia partiendo de la pasada por parametro
	 * @param line linea desde donde buscar 
	 * @return entero linea encontrada que no es vacia, -1 si no encontro.
	 */
	private int searchNextNonEmptyLine(int line) {
		int nxtLine = -1;
		int y = line;
		while (y < ROWS && nxtLine == -1) {
			if (!isEmptyLine(y)) {
				nxtLine = y;
			}
			y++;
		}
		return nxtLine;
	}
	
	/**
	 * Agrega el siguiente tetromino a la grilla, crea un tetromino que queda en espera y le avisa a la GUI que el siguiente tetromino cambio.
	 */
	public void addTetromino() {
		fallingTetromino = nextTetromino;
		fallingTetromino.setGrid(this); //Asigno la grilla al tetromino para que pueda empezar a caer
		setNextTetromino(randomTetromino());
		game.getGUI().updateNextTetr();
	}
	
	/**
	 * Actualiza esta grilla despues de la eliminacion de una o mas filas, bajando de posicion los bloques que se encuentren por encima,
	 *  agrega un tetromino listo para caer y avisa a la GUI el cambio.
	 */
	protected void fall() {
		for (int y = 0; y < ROWS; y++ ) {
			if ( isEmptyLine(y) ) { 
				int nxtNonEmptyLine = searchNextNonEmptyLine(y);
				if ( nxtNonEmptyLine == -1 ){ //NO encontro linea siguiente no vacia
					y = ROWS;
				} else {
					for (int x = 0; x < COLUMNS; x++ ) {
						if ( blockMatrix[x][nxtNonEmptyLine]!=null ) {
							blockMatrix[x][nxtNonEmptyLine].setCoordinates(x, y); //Cambio coordenadas de bloque a fila vacia
							addBlock(blockMatrix[x][nxtNonEmptyLine]); //Grilla agrega bloque 
							try { 
								removeBlock(x, nxtNonEmptyLine); //Grilla borra el bloque relocado.
							} catch (GridException e) {
								System.out.println(e.getMessage());
							} 
						}
					}
				}
			}
		}
		addTetromino();
		game.getGUI().updateGrid();
	}
	
	/**
	 * Asigna un tetromino a ser el proximo tetromino.
	 * @param t Tetromino a ser siguiente tetromino.
	 */
	protected void setNextTetromino(Tetromino t) {
		if ( t != null ) {
			nextTetromino = t;
		}
	} 
	
	/**
	 * Crea y retorna un tetromino con forma aleatoria.
	 * @return Un tetromino generado aleatoriamente.
	 */
	protected Tetromino randomTetromino() {
		Tetromino toReturn = null;
		Random rnd = new Random();		
		int tirada = rnd.nextInt(7); //Retorna un numero entre [0,7), no se incluye el 7.
		switch ( tirada ) {
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
	 * @param row Numero de fila a chequear.
	 * @return True si la linea esta llena, false si no.
	 * @throws GridException si la fila no pertence a la grilla.
	 */
	public boolean checkLine(int row) throws GridException {
		boolean full = true; //Asumimos que esta llena y probamos si no lo esta.
		int pointer = 0;
		if( row < 0 || row >= ROWS ) {
			throw new GridException("Fila no pertence a la grilla");
		}
		while (full && pointer < COLUMNS) {
			if (blockMatrix[pointer][row] == null)
				full = false;
			else
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
	 * Consulta el bloque en las coordenadas pasadas como parametro.
	 * @param x Coordenada x.
	 * @param y Coordenada y.
	 * @return El bloque que se encuentra en las coordenadas pasadas como parametro, null si dicho bloque no existe.
	 * @throws GridException si las coordenadas no pertenecen a la grilla
	 */
	public Block getBlock(int x, int y) throws GridException {
		if ( x < 0  || x > COLUMNS || y < 0 || y > ROWS ) {
			throw new GridException("Coordenadas fuera de los limites de la grilla");
		}
		return blockMatrix[x][y];  //Se conoce que puede ser null, la reponsabilidad es del cliente
	}
	
	/**
	 * Remueve el bloque en las coordenadas pasadas como parametro.
	 * @param x Coordenada x.
	 * @param y Coordenada y.
	 * @throws GridException si las coordenadas no pertenecen a la grilla
	 */
	public void removeBlock(int x, int y) throws GridException{
		if ( x < 0  || x > COLUMNS || y < 0 || y > ROWS ) {
			throw new GridException("Coordenadas fuera de los limites de la grilla");
		}
		blockMatrix[x][y] = null;
	}
	
	/**
	 * Agrega un bloque a la grilla.
	 * @param b Bloque a añadir.
	 */
	public void addBlock(Block b) { //El responsable de llamar a este metodo se asegura que la posicion es valida, no colisiona, y no es nula.
		blockMatrix[b.getX()][b.getY()] = b;
	}
	
	/**
	 * Retorna el juego asociado a esta grilla.
	 * @return game Juego asociado a esta grilla.
	 */
	public Game getGame() {
		return game;
	}
	
}