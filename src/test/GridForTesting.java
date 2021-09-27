package test;

import game.Game;
import game.Grid;
import game.tetrominos.Tetromino;

public class GridForTesting extends Grid{

	public GridForTesting(Game game) {
		super(game);
	}
	
	@Override
	protected void setNextTetromino(Tetromino t) {
		super.setNextTetromino(t);
	}

}
