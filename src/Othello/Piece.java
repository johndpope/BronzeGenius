package Othello;

public class Piece {
	private Color color;
	
	public Piece(Color color)
	{
		this.color = color;
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public void flip()
	{
		if(color == Color.White) color = Color.Black;
		else color = Color.White;
	}
}
