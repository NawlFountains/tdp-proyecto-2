package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.GridException;
import game.Game;
import game.Grid;
import game.tetrominos.Block;
import game.tetrominos.Color;
import game.tetrominos.Tetromino;
import game.tetrominos.Tetromino_I;

class GridTest {
	
	private GridForTesting grid;
	private Tetromino tetro1 = new Tetromino_I(Color.BLUE);
	Block[] blocks = tetro1.getBlocks();
	Block block1 = blocks[0];
	Block block2 = blocks[1];
	Block block3 = blocks[2];
	Block block4 = blocks[3];

	@BeforeEach
	public void beforeEach() {
		grid = new GridForTesting(new Game());
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
		try {
			assertEquals(block1, grid.getBlock(0, 0));
			assertEquals(block2, grid.getBlock(5, 3));
			assertEquals(block3, grid.getBlock(9, 20));
			assertEquals(block4, grid.getBlock(7, 14));
			assertNull(grid.getBlock(1, 1));
		} catch (GridException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRemove() {
		System.out.println("testRemove()");
		add();
		try {
			grid.removeBlock(0, 0);
			grid.removeBlock(5, 3);
			grid.removeBlock(9, 20);
			grid.removeBlock(7, 14);
			assertNull(grid.getBlock(0, 0));
			assertNull(grid.getBlock(5, 3));
			assertNull(grid.getBlock(9, 20));
			assertNull(grid.getBlock(7, 14));
		} catch (GridException e) {
			e.printStackTrace();
		}
	}
	
	private void printGrid() {
		int x;
		int y;
		for(y = Grid.ROWS - 1; y >= 0; y--) {
			for(x = 0; x < Grid.COLUMNS; x++) {
				try {
					System.out.print( (grid.getBlock(x, y) != null ? 1 : 0) + " ");
				} catch (GridException e) {
					e.printStackTrace();
				}
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private Tetromino buildTetro() {
		int y = 0;
		Tetromino tetro = new Tetromino_I(Color.BLUE);
		for(Block b : tetro.getBlocks()) {
			b.setCoordinates(Grid.COLUMNS - 1, y++);
		}
		return tetro;
	}
	
	@Test
	public void testDeleteLines() {
		System.out.println("testDeleteLines()");
		
		List<Block> blocks = new ArrayList<Block>();
		Tetromino tetro;
		int x, y;
		
		tetro = buildTetro();
		grid.setNextTetromino(tetro);
		grid.addTetromino();
		for(Block b : tetro.getBlocks()) {
			grid.addBlock(b);
		}
		
		/***************************************************************************************************************************************************/
		
		tetro = buildTetro();	//Build tetro
		
		for(int i = 0; i < 9; i++) {						// Build blocks
			Tetromino t = new Tetromino_I(Color.BLUE);	
			for(Block b : t.getBlocks()) {
				blocks.add(b);
			}
		}
		x = 0;
		y = 0;
		for(int i = 0; i < blocks.size(); i++) {		// Fill grid
			blocks.get(i).setCoordinates(x, y);
			grid.addBlock(blocks.get(i));
			if(++x >= Grid.COLUMNS - 1) {
				x = 0;
				y++;
			}
		}
		
		grid.setNextTetromino(tetro);			// Add tetro to grid 
		grid.addTetromino();
		for(Block b : tetro.getBlocks()) {
			grid.addBlock(b);
		}
		
		System.out.println("Before delete 4");
		printGrid();								// borrar 4 filas
		System.out.println("After delete 4");
		printGrid();
		
		/***************************************************************************************************************************************************/
		
		tetro = buildTetro();
		
		grid = new GridForTesting(new Game());
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
			if(++x >= Grid.COLUMNS - 1) {
				x = 0;
				y++;
			}
		}
		
		grid.setNextTetromino(tetro);
		grid.addTetromino();
		for(Block b : tetro.getBlocks()) {
			grid.addBlock(b);
		}

		System.out.println("Before delete 1");
		printGrid();										// borrar 1 fila
		System.out.println("After delete 1");
		printGrid();

		/***************************************************************************************************************************************************/
		
		tetro = buildTetro();
		
		grid = new GridForTesting(new Game());
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
			if(++x >= Grid.COLUMNS - 1) {
				x = 0;
				y++;
			}
		}
		
		grid.setNextTetromino(tetro);
		grid.addTetromino();
		for(Block b : tetro.getBlocks()) {
			grid.addBlock(b);
		}

		System.out.println("Before delete 2");
		printGrid();									// borrar 2 filas
		System.out.println("After delete 2");
		printGrid();

		/***************************************************************************************************************************************************/
		
		tetro = buildTetro();
		
		grid = new GridForTesting(new Game());
		blocks.clear();
		for(int i = 0; i < 7; i++) {
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
			if(++x >= Grid.COLUMNS - 1) {
				x = 0;
				y++;
			}
		}
		
		grid.setNextTetromino(tetro);
		grid.addTetromino();
		for(Block b : tetro.getBlocks()) {
			grid.addBlock(b);
		}

		System.out.println("Before delete 3");
		printGrid();										// borrar 3 filas
		System.out.println("After delete 3");
		printGrid();

		/***************************************************************************************************************************************************/
		
		tetro = buildTetro();
		
		grid = new GridForTesting(new Game());
		
		grid.setNextTetromino(tetro);
		grid.addTetromino();
		for(Block b : tetro.getBlocks()) {
			grid.addBlock(b);
		}

		System.out.println("Before delete 0");
		printGrid();										// borrar 0 filas
		System.out.println("After delete 0");
		printGrid();

		/***************************************************************************************************************************************************/
		
		tetro = buildTetro();
		
		grid = new GridForTesting(new Game());
		blocks.clear();
		for(int i = 0; i < 5; i++) {
			Tetromino t = new Tetromino_I(Color.BLUE);
			for(Block b : t.getBlocks()) {
				blocks.add(b);
			}
		}
		x = 0;
		y = 2;
		for(int i = 0; i < blocks.size(); i++) {
			blocks.get(i).setCoordinates(x, y);
			grid.addBlock(blocks.get(i));
			if(++x >= Grid.COLUMNS - 1) {
				x = 0;
				y++;
			}
		}
		
		grid.setNextTetromino(tetro);
		grid.addTetromino();
		for(Block b : tetro.getBlocks()) {
			grid.addBlock(b);
		}
		
		System.out.println("Before delete 2 in middle");
		printGrid();											// borrar 2 filas en el medio
		System.out.println("After delete 2 in middle");
		printGrid();
		
	}

}
