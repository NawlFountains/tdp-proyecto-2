package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.Game;
import game.Grid;
import game.tetrominos.Block;
import game.tetrominos.Color;
import game.tetrominos.Tetromino;
import game.tetrominos.Tetromino_I;

class GridTest {
	
	private Grid grid;
	private Tetromino tetro1 = new Tetromino_I(Color.BLUE);
	Block[] blocks = tetro1.getBlocks();
	Block block1 = blocks[0];
	Block block2 = blocks[0];
	Block block3 = blocks[0];
	Block block4 = blocks[0];

	@BeforeEach
	public void beforeEach() {
		grid = new Grid(new Game());
	}
	
	@Test
	public void testAdd() {
		System.out.println("testAdd()");
		add();
	}
	
	private void add() {
		block1.setCoordinates(0, 0);
		block2.setCoordinates(5, 3);
		block3.setCoordinates(9, 20);
		block4.setCoordinates(7, 14);
		for(Block b : blocks) {
			grid.addBlock(b);
		}
	}
	
	@Test
	public void testGet() {
		System.out.println("testGet()");
		add();
		printGrid();
		assertEquals(block1, grid.getBlock(0, 0));
		assertEquals(block2, grid.getBlock(5, 3));
		assertEquals(block3, grid.getBlock(9, 20));
		assertEquals(block4, grid.getBlock(7, 14));
		assertNull(grid.getBlock(1, 1));
	}
	
	@Test
	public void testRemove() {
		System.out.println("testRemove()");
		add();
		grid.removeBlock(0, 0);
		grid.removeBlock(5, 3);
		grid.removeBlock(9, 20);
		grid.removeBlock(7, 14);
		assertNull(grid.getBlock(0, 0));
		assertNull(grid.getBlock(5, 3));
		assertNull(grid.getBlock(9, 20));
		assertNull(grid.getBlock(7, 14));
	}
	
	private void printGrid() {
		int x;
		int y;
		for(y = Grid.ROWS - 1; y >= 0; y--) {
			for(x = 0; x < Grid.COLUMNS; x++) {
				System.out.print( (grid.getBlock(x, y) != null ? 1 : 0) + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	@Test
	public void testDeleteLines() {
		System.out.println("testDeleteLines()");
		
		List<Block> blocks = new ArrayList<Block>();
		int x, y;

		
		/***************************************************************************************************************************************************/
		
		for(int i = 0; i < 10; i++) {
			Tetromino t = new Tetromino_I(Color.BLUE);
			for(Block b : t.getBlocks()) {
				blocks.add(b);
			}
		}
		x = 0;
		y = 0;
		for(int i = 0; i < blocks.size(); i++) {
			blocks.get(i).setCoordinates(x, y);
			grid.addBlock(blocks.get(i));
			if(++x >= Grid.COLUMNS) {
				x = 0;
				y++;
			}
		}
		assertEquals(800, grid.deleteLines());		// borrar 4 filas
		
		/***************************************************************************************************************************************************/
		
		grid = new Grid(new Game());
		blocks.clear();
		for(int i = 0; i < 3; i++) {
			Tetromino t = new Tetromino_I(Color.BLUE);
			for(Block b : t.getBlocks()) {
				blocks.add(b);
			}
		}
		x = 0;
		y = 0;
		for(int i = 0; i < blocks.size(); i++) {
			blocks.get(i).setCoordinates(x, y);
			grid.addBlock(blocks.get(i));
			if(++x >= Grid.COLUMNS) {
				x = 0;
				y++;
			}
		}
		assertEquals(100, grid.deleteLines());		// borrar 1 fila

		/***************************************************************************************************************************************************/
		
		grid = new Grid(new Game());
		blocks.clear();
		for(int i = 0; i < 5; i++) {
			Tetromino t = new Tetromino_I(Color.BLUE);
			for(Block b : t.getBlocks()) {
				blocks.add(b);
			}
		}
		x = 0;
		y = 0;
		for(int i = 0; i < blocks.size(); i++) {
			blocks.get(i).setCoordinates(x, y);
			grid.addBlock(blocks.get(i));
			if(++x >= Grid.COLUMNS) {
				x = 0;
				y++;
			}
		}
		assertEquals(200, grid.deleteLines());		// borrar 2 filas

		/***************************************************************************************************************************************************/
		
		grid = new Grid(new Game());
		blocks.clear();
		for(int i = 0; i < 8; i++) {
			Tetromino t = new Tetromino_I(Color.BLUE);
			for(Block b : t.getBlocks()) {
				blocks.add(b);
			}
		}
		x = 0;
		y = 0;
		for(int i = 0; i < blocks.size(); i++) {
			blocks.get(i).setCoordinates(x, y);
			grid.addBlock(blocks.get(i));
			if(++x >= Grid.COLUMNS) {
				x = 0;
				y++;
			}
		}
		assertEquals(500, grid.deleteLines());		// borrar 3 filas

		/***************************************************************************************************************************************************/
		
		grid = new Grid(new Game());
		assertEquals(500, grid.deleteLines());		// borrar 0 filas

		/***************************************************************************************************************************************************/
		
	}

}
