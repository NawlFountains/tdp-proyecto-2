package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import game.tetrominos.Color;
import game.tetrominos.Tetromino;
import game.tetrominos.Tetromino_Z;

public class TetrominoTest {

	private Tetromino tetro;
	
	@Before
	public void before() {
		tetro = new Tetromino_Z(Color.BLUE);
	}
	
	@Test
	public void test() {
		
	}

}
