package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.tetrominos.Block;
import game.tetrominos.Color;
import game.tetrominos.Tetromino;
import game.tetrominos.Tetromino_I;

class BlockTest {

	private Block block;
	
	@BeforeEach
	public void beforeEach() {
		Tetromino tetro = new Tetromino_I(Color.BLUE);
		block = tetro.getBlocks()[0];
	}
	
	@Test
	void testSetCoordinates() {
		
		block.setCoordinates(0, 0);
		assertEquals(0, block.getX());
		assertEquals(0, block.getY());
		
		block.setCoordinates(9, 20);
		assertEquals(9, block.getX());
		assertEquals(20, block.getY());
		
		block.setCoordinates(5, 12);
		assertEquals(5, block.getX());
		assertEquals(12, block.getY());
		
		block.setCoordinates(9, 0);
		assertEquals(9, block.getX());
		assertEquals(0, block.getY());
		
		block.setCoordinates(0, 20);
		assertEquals(0, block.getX());
		assertEquals(20, block.getY());
		
	}

}
