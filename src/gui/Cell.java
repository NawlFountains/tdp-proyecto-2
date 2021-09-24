package gui;

import game.tetrominos.Color;

public class Cell {
	protected String emptyCellImagePath;
	
	public Cell() {
		
	}
	
	public Cell(Color c) {
		setColor(c);
	}
 
	public void setColor(Color c) {
		switch (c) {
			case BLUE: emptyCellImagePath="/gui/img/bloques/J.png"; break;
			case CYAN: emptyCellImagePath="/gui/img/bloques/I.png"; break;
			case ORANGE: emptyCellImagePath="/gui/img/bloques/L.png"; break;
			case YELLOW: emptyCellImagePath="/gui/img/bloques/O.png"; break;
			case GREEN: emptyCellImagePath="/gui/img/bloques/S.png"; break;
			case PURPLE: emptyCellImagePath="/gui/img/bloques/T.png"; break;
			case RED: emptyCellImagePath="/gui/img/bloques/Z.png"; break;
		} 
	}
	
	public String getImagePath() {
		return emptyCellImagePath;
	}
	
}
