package serie4;

/******************************************************************************
 * Programmierung 1 (HS 11)
 * Serie 4 
 *  
 * Salim Hermidas 
 * 11-125-382
 *
 */ 

/** A very stupid computer player */
public class ComputerPlayer implements IPlayer{
	private VierGewinnt.Token token;
	
	public int getNextColumn(VierGewinnt.Token[][] board){
		java.util.Random generator = new java.util.Random();
		boolean columnOK = false;
		int col = 0;
		while(!columnOK){
			// random number between 0 and 6
			col = generator.nextInt(board.length);
			// check if this column is still "free"
			if(isColFull(col, board) == false) columnOK = true;
			// if not, we have to generate another random number
			else columnOK = false;
		}
		return col;
	}
	
	
	
	/** returns true if the column col is already full and false otherwise. */
	private boolean isColFull(int col, VierGewinnt.Token[][] board){
		int topRow = board[0].length-1;
		if(board[col][topRow] != VierGewinnt.Token.empty){
			return true;
		}else{
			return false;
		}
	}
	
	
	public void setToken(VierGewinnt.Token token){
		this.token = token;
	}
	
	public VierGewinnt.Token getToken(){
		return this.token;
	}
	
	public String getProgrammers(){
		return "Random Player";
	}
}

