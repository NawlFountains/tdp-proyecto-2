package gui;

import game.tetrominos.Color;

public class Cell {
	protected String emptyCellImagePath;
	
	public Cell() {
		
	}
 
	public void setColor(Color c) {
		switch (c) {
			case BLUE: emptyCellImagePath="/gui/img/bloques/J.png";
			case CYAN: emptyCellImagePath="/gui/img/bloques/I.png";
			case ORANGE: emptyCellImagePath="/gui/img/bloques/L.png";
			case YELLOW: emptyCellImagePath="/gui/img/bloques/O.png";
			case GREEN: emptyCellImagePath="/gui/img/bloques/S.png";
			case PURPLE: emptyCellImagePath="/gui/img/bloques/T.png";
			case RED: emptyCellImagePath="/gui/img/bloques/Z.png";
		}
	}
	
}
