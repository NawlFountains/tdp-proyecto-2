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
	 * Setea las coordenadas de este bloque.
	 * @param x La nueva coordenada x para este bloque.
	 * @param y La nueva coordenada y para este bloque.
	 */
	public void setCoordinates(int x, int y) {
			this.x = x;
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
