package Othello;

public class Board {
	private int blackCount = 0;
	private int whiteCount = 0;
	private Piece[][] board;
	private int rows;
	private int columns;
	
	public Board(int rows, int columns)
	{
		this.rows = rows;
		this.columns = columns;
		board = new Piece[rows][columns];
	}
	
	public void initialize()
	{
		/* initialize center black and white pieces*/
		int rows = board.length;
		int columns = board[0].length;
		
		board[rows/2 - 1][columns/2 - 1] = new Piece(Color.Black);
		board[rows/2 - 1][columns/2] = new Piece(Color.White);
		board[rows/2][columns/2 - 1] = new Piece(Color.White);
		board[rows/2][columns/2] = new Piece(Color.Black);
		
		blackCount = 2;
		whiteCount = 2;
	}
	
	public boolean placeColor(int r, int c, Color color)
	{
		if(r >= rows || c >= columns) return false;
		if(board[r][c] != null) return false;
		board[r][c] = new Piece(color);
		switch(color)
		{
			case White:
				whiteCount++;
				break;
			case Black:
				blackCount++;
				break;
		}
		return true;
	}
	
	/* flip from position(row, col) to four directions */
	public boolean flipSection(int r, int c)
	{
		if(board[r][c] == null) return false;
		
		if(r-2 < rows && c < columns && board[r-2][c] != null && board[r-1][c] != null)
		{
			if(board[r-1][c].getColor() != board[r][c].getColor() && board[r][c].getColor() == board[r-2][c].getColor())
			{
				board[r-1][c].flip();
				if(board[r-1][c].getColor() == Color.White) whiteCount++;
				else blackCount++;
				flipSection(r-1, c);
			}
		}
		
		if(r+2 < rows && c < columns && board[r+2][c] != null && board[r+1][c] != null)
		{
			if(board[r+1][c].getColor() != board[r][c].getColor() && board[r][c].getColor() == board[r+2][c].getColor())
			{
				board[r+1][c].flip();
				if(board[r-1][c].getColor() == Color.White) whiteCount++;
				else blackCount++;
				flipSection(r+1, c);
			}
		}
		
		if(r < rows && c-2 < columns && board[r][c-2] != null && board[r][c-1] != null)
		{
			if(board[r][c-1].getColor() != board[r][c].getColor() && board[r][c].getColor() == board[r][c-2].getColor())
			{
				board[r][c-1].flip();
				if(board[r-1][c].getColor() == Color.White) whiteCount++;
				else blackCount++;
				flipSection(r, c-1);
			}
		}
		
		if(r < rows && c+2 < columns && board[r][c+2] != null && board[r][c+1] != null)
		{
			if(board[r][c+1].getColor() != board[r][c].getColor() && board[r][c].getColor() == board[r][c+2].getColor())
			{
				board[r][c+1].flip();
				if(board[r-1][c].getColor() == Color.White) whiteCount++;
				else blackCount++;
				flipSection(r, c+1);
			}
		}
		
		return true;
	}
	
	public int getScoreForColor(Color c)
	{
		if(c == Color.White) return whiteCount;
		else return blackCount;
	}

}
