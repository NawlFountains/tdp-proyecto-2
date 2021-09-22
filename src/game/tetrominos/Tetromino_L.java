package game.tetrominos;

public class Tetromino_L extends Tetromino{
	public Tetromino_L(Color color) {
		super(color);
		
		shape=new int[3][3];
		shape[0][2]=EMPTY; shape[1][2]=BLOCK; 		shape[2][2]=CENTROID;
		shape[0][1]=EMPTY; shape[1][1]=EMPTY; 		shape[2][1]=BLOCK;
		shape[0][0]=EMPTY; shape[1][0]=EMPTY; 		shape[2][0]=BLOCK;
	}
}