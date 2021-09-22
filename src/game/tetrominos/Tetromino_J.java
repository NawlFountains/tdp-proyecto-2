package game.tetrominos;

public class Tetromino_J extends Tetromino{
	public Tetromino_J(Color color) {
		super(color);
		
		shape=new int[3][3];
		shape[0][2]=CENTROID;	shape[1][2]=BLOCK;	shape[2][2]=EMPTY;
		shape[0][1]=BLOCK;		shape[1][1]=EMPTY;	shape[2][1]=EMPTY;
		shape[0][0]=BLOCK;		shape[1][0]=EMPTY;	shape[2][0]=EMPTY;
	}
}
