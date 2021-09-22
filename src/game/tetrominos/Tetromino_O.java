package game.tetrominos;

public class Tetromino_O extends Tetromino{
	public Tetromino_O(Color color) {
		super(color);
		shape=new int[2][2];
		shape[0][1]=CENTROID; 	shape[1][1]=BLOCK;
		shape[0][0]=BLOCK; 		shape[1][0]=BLOCK;
		
		/*
		 * 0000 
		 * 0210
		 * 0110
		 * 0000
		 * */
	}
	
}