package game.tetrominos;

import game.Grid;

/**
 * Modela un bloque del juego.
 */
public class Block {

	protected int x;
	protected int y;
	protected Color color;
	protected Grid grid;
	
	/**
	 * Crea un nuevo bloque.
	 * @param color El color del nuevo bloque.
	 */
	protected Block(Color color) {
		this.color = color;
		x = y = 0;
	}
	
	/**
	 * Setea las coordenadas de este bloque en su grilla. Si este bloque no tiene una grilla, no se hace nada.
	 * @param x La nueva coordenada x para este bloque.
	 * @param y La nueva coordenada y para este bloque.
	 */
	public void setCoordinates(int x, int y) {
		if(grid != null) {
			//TODO remover de la grilla
			this.x = x;
			this.y = y;
			//TODO añadir a la grilla
		}
	}
	
	/**
	 * Setea la grilla en la que se encuentra este bloque.
	 * @param grid Una grilla.
	 */
	public void setGrid(Grid grid) {
		this.grid = grid;
	}
	
	/**
	 * Setea la coordenada x de este bloque.
	 * @param x La nueva cooredenada x para este bloque.
	 */
	@Deprecated
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Setea la coordenada y de este bloque.
	 * @param y La nueva cooredenada y para este bloque.
	 */
	@Deprecated
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Retorna la coordenada x de este bloque.
	 * @return La coordenada x de este bloque.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Retorna la coordenada y de este bloque.
	 * @return La coordenada y de este bloque.
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Retorna el color de este bloque.
	 * @return El color de este bloque.
	 */
	public Color getColor() {
		return color;
	}
	
}
