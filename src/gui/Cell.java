package gui;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import game.tetrominos.Color;

/**
 * Subclase de JLabel que añade funcionalidad para mostrar un bloque del juego y cambiar su color.
 */
@SuppressWarnings("serial")
public class Cell extends JLabel {
	
	protected static final String EMPTY_CELL_IMAGE_PATH = "gui/img/bloques/dummy.png";
	
	/**
	 * Crea una nueva celda con color vacio.
	 */
	public Cell() {
		super();
		try {
			this.setIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream(EMPTY_CELL_IMAGE_PATH))));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Crea una nueva celda con el color pasado como parametro.
	 * @param c El color de la nueva celda.
	 */
	public Cell(Color c) {
		this();
		setColor(c);
	}
 
	/**
	 * Cambia el color de esta celda.
	 * @param c El nuevo color de la celda.
	 */
	public void setColor(Color c) {
		String colorPath;
		if(c != null)
			colorPath = new StringBuilder("gui/img/bloques/")
							.append(c.toString().toLowerCase())
							.append(".png")
							.toString();
		else
			colorPath = EMPTY_CELL_IMAGE_PATH;
		
		try {
			this.setIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream(colorPath))));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Deprecated
	public String getImagePath() {
		return null;
	}
	
}
