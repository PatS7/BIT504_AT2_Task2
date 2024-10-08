import java.awt.*;

public class Board {
	// grid line width
	public static final int GRID_WIDTH = 8;
	// grid line half width
	public static final int GRID_WIDTH_HALF = GRID_WIDTH / 2;
	
	//2D array of ROWS-by-COLS Cell instances
	Cell [][] cells;
	
	/** Constructor to create the game board and set it up with cell components */
	public Board() {
		
	 	//New array using constants COLS and ROWS
		cells = new Cell[GameMain.ROWS][GameMain.COLS];
		
		for (int row = 0; row < GameMain.ROWS; ++row) {
			for (int col = 0; col < GameMain.COLS; ++col) {
				cells[row][col] = new Cell(row, col);
			}
		}
	}
	

	 /** Return true if it is a draw (i.e., no more EMPTY cells) */ 
	//Loop to check if game result is a draw
	//Checks if each cell is empty
	public boolean isDraw() {
		 
		for (int row = 0; row < GameMain.ROWS; ++row) {
            for (int col = 0; col < GameMain.COLS; ++col) {
                if (cells[row][col].content == Player.Empty) {
                    return false; // Not a draw as there are empty cells
                }
            }
        }
        return true; // Game is a draw as no empty cells left  
				
	}
	
	/** Return true if the current player "thePlayer" has won after making their move  */
	//All cells are checked for a win condition
	
	public boolean hasWon(Player thePlayer, int playerRow, int playerCol) {
		 // check if player has 3-in-that-row
		if(cells[playerRow][0].content == thePlayer 
			&& cells[playerRow][1].content == thePlayer 
			&& cells[playerRow][2].content == thePlayer )
			return true; 
		
		 //check if player has won with with 3 in a column
		
		if (cells[0][playerCol].content == thePlayer && 
	        cells[1][playerCol].content == thePlayer && 
	        cells[2][playerCol].content == thePlayer)
	        return true;
		
		
		 // 3-in-the-diagonal (top left to bottom right)
		if( cells[0][0].content == thePlayer && 
			cells[1][1].content == thePlayer && 
			cells[2][2].content == thePlayer)
			return true;
		 
		
		//3 in a diagonal in opposite direction (top right to bottom left)
		if (cells[0][2].content == thePlayer && 
	        cells[1][1].content == thePlayer && 
	        cells[2][0].content == thePlayer)
	        return true;
		
		
		//no winner, keep playing
		return false;
	}
	
	/**
	 * Draws the grid (rows then columns) using constant sizes, then call on the
	 * Cells to paint themselves into the grid
	 */
	public void paint(Graphics g) {
		//draw the grid
		g.setColor(Color.gray);
		for (int row = 1; row < GameMain.ROWS; ++row) {          
			g.fillRoundRect(0, GameMain.CELL_SIZE * row - GRID_WIDTH_HALF,                
					GameMain.CANVAS_WIDTH - 1, GRID_WIDTH,                
					GRID_WIDTH, GRID_WIDTH);       
			}
		for (int col = 1; col < GameMain.COLS; ++col) {          
			g.fillRoundRect(GameMain.CELL_SIZE * col - GRID_WIDTH_HALF, 0,                
					GRID_WIDTH, GameMain.CANVAS_HEIGHT - 1,                
					GRID_WIDTH, GRID_WIDTH);
		}
		
		//Draw the cells
		for (int row = 0; row < GameMain.ROWS; ++row) {          
			for (int col = 0; col < GameMain.COLS; ++col) {  
				cells[row][col].paint(g);
			}
		}
	}
	

}
