package game.tetrominos;

/**
 * Modela un bloque del juego.
 */
public class Block {

	protected int x;
	protected int y;
	protected Color color;
	
	/**
	 * Crea un nuevo bloque.
	 * @param color El color del nuevo bloque.
	 */
	protected Block(Color color) {
		this.color = color;
		x = y = 0;
	}
	
	/**
	 * Setea la coordenada x de este bloque.
	 * @param x La nueva cooredenada x para este bloque.
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Setea la coordenada y de este bloque.
	 * @param y La nueva cooredenada y para este bloque.
	 */
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
