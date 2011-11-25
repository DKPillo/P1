package serie4;

/******************************************************************************
 * Programmierung 1 (HS 11)
 * Serie 4 
 *  
 * Salim Hermidas 
 * 11-125-382
 *
 */ 

import java.util.Arrays;
import java.util.Scanner;

public class VierGewinnt {
	
	public static final int COLS = 7;
	public static final int ROWS = 6;
	
	// Enum for the different tokens (empty, O, X)
	public enum Token { 
		empty(" "),
		player1("O"),
		player2("X");
		private String representation; // string representation of value
		Token(String s) { this.representation = s; }
		public String toString() { return this.representation; }
	}
	
	private Token[][] board = new Token[COLS][ROWS]; // 7 columns with 6 fields each
	private IPlayer[] players = new IPlayer[2]; // two players
	
	/** initialize board and players and start the game */
	public void play() {
		// initialize the board
		for (Token[] column : this.board) {
			Arrays.fill(column, Token.empty);
		}
		// initialize players
		players[0] = new HumanPlayer();
		System.out.print("Play against a human opponent? (y / n) ");
		String opponent = new Scanner(System.in).nextLine();
		if(opponent.toLowerCase().equals("y")){
			players[1] = new HumanPlayer();
		}else{
			players[1] = new ComputerPlayer();
		}
		players[0].setToken(Token.player1);
		players[1].setToken(Token.player2);
		// play...
		boolean solved = false;
		int currentPlayer = (new java.util.Random()).nextInt(2);  //choose randomly who begins
		System.out.println("current player: "+currentPlayer);
		int insertCol, insertRow; // starting from 0
		while (!solved && !this.isBoardFull()){
			// get player's next "move"
			insertCol = players[currentPlayer].getNextColumn(this.board);
			// insert the token and get the row where it landed
			insertRow = this.insertToken(insertCol, players[currentPlayer].getToken());
			// check if the game is over
			solved = this.checkVierGewinnt(insertCol, insertRow);
			//switch to other player
			if (!solved) currentPlayer = (currentPlayer+1) % 2;
		}
		System.out.println(displayBoard(this.board));
		if (solved) System.out.println("Player "+players[currentPlayer].getToken() + " wins!");
		else System.out.println("Draw! Game over.");
	}
	
	
	/** Inserts the token at the specified column (if possible)
	    and returns the row where the token landed */
	private int insertToken(int column, Token tok){
		int rowToWrite;
		if (!board[column][ROWS - 1].equals(Token.empty)) {
			System.out.println("Column is full");
			rowToWrite = -1;
			System.exit(1);
		} else {
			rowToWrite = biggestEmptyRow(column);
			//System.out.println(rowToWrite);
			putTokenIn(column, rowToWrite, tok);
		}
		return rowToWrite;
	}
	
	
	/** Checks if every position is occupied */
	private boolean isBoardFull() {
		boolean foundEmptyPlace = false;
		for (int i = 0; i < COLS; i++) {
			for (int j = 0; j < ROWS; j++) {
				if (board[i][j].equals(Token.empty)) {
					foundEmptyPlace = true;
				}
			}
		}
		return !foundEmptyPlace;
	}
	
	
	/** Checks for at least four equal tokens in a row in either direction,
	    starting from the given position. */
	private boolean checkVierGewinnt(int col, int row){
		boolean finished = false;
		boolean a = checkA(col, row);	//Wagerecht
		boolean b = checkB(col, row);	//Senkrecht
		boolean c = checkC(col, row);	//links unten nach rechts oben
		boolean d = checkD(col, row);	//links oben nach rechts unten
		if (a || b || c || d) {
			finished = true;
		}
		return finished;
	}


	//My Helper Methods
	private boolean checkA(int colToCheck, int rowToCheck) {
		boolean status = false;
		int size = 1;
		Token actualPlayersToken;
		actualPlayersToken = board[colToCheck][rowToCheck];
		
		int position = colToCheck + 1;
		while (position < COLS) {
			if (board[position][rowToCheck].equals(actualPlayersToken)) {
				size++;
			} else {
				position = COLS;
			}
			position++;
		}
		
		position = colToCheck - 1;
		while (position >= 0) {
			if (board[position][rowToCheck].equals(actualPlayersToken)) {
				size++;
			} else {
				position = -1;
			}
			position--;
		}
		
		if (size >= 4) {
			status = true;
		}
		return status;
	}

	private boolean checkB(int colToCheck, int rowToCheck) {
		boolean status = false;
		int size = 1;
		Token actualPlayersToken;
		actualPlayersToken = board[colToCheck][rowToCheck];
		
		int position = rowToCheck + 1;
		while (position < ROWS) {
			if (board[colToCheck][position].equals(actualPlayersToken)) {
				size++;
			} else {
				position = ROWS;
			}
			position++;
		}
		
		position = rowToCheck - 1;
		while (position >= 0) {
			if (board[colToCheck][position].equals(actualPlayersToken)) {
				size++;
			} else {
				position = -1;
			}
			position--;
		}
		
		if (size >= 4) {
			status = true;
		}
		return status;
	}

	private boolean checkC(int colToCheck, int rowToCheck) {
		boolean status = false;
		int size = 1;
		Token actualPlayersToken;
		actualPlayersToken = board[colToCheck][rowToCheck];

		int positionCol = colToCheck + 1;
		int positionRow = rowToCheck + 1;
		while (positionCol < COLS && positionRow < ROWS) {
			if (board[positionCol][positionRow].equals(actualPlayersToken)) {
				size++;
			} else {
				positionCol = COLS;
				positionRow = ROWS;
			}
			positionCol++;
			positionRow++;
		}
		
		positionCol = colToCheck - 1;
		positionRow = rowToCheck - 1;
		while (positionCol >= 0 && positionRow >= 0) {
			if (board[positionCol][positionRow].equals(actualPlayersToken)) {
				size++;
			} else {
				positionCol = -1;
				positionRow = -1;
			}
			positionCol--;
			positionRow--;
		}
		
		if (size >= 4) {
			status = true;
		}
		return status;
	}
	
	private boolean checkD(int colToCheck, int rowToCheck) {
		boolean status = false;
		int size = 1;
		Token actualPlayersToken;
		actualPlayersToken = board[colToCheck][rowToCheck];

		int positionCol = colToCheck + 1;
		int positionRow = rowToCheck - 1;
		while (positionCol < COLS && positionRow >= 0) {
			if (board[positionCol][positionRow].equals(actualPlayersToken)) {
				size++;
			} else {
				positionCol = COLS;
				positionRow = -1;
			}
			positionCol++;
			positionRow--;
		}
		
		positionCol = colToCheck - 1;
		positionRow = rowToCheck + 1;
		while (positionCol >= 0 && positionRow < ROWS) {
			if (board[positionCol][positionRow].equals(actualPlayersToken)) {
				size++;
			} else {
				positionCol = -1;
				positionRow = ROWS;
			}
			positionCol--;
			positionRow++;
		}
		
		if (size >= 4) {
			status = true;
		}
		return status;
	}
	
	private int biggestEmptyRow(int colToFind) {
		int rowOut = 0;
		
		int counter = 0;
		boolean foundBiggest = false;
		while (counter < ROWS && !foundBiggest) {
			if (board[colToFind][counter].equals(Token.empty)) {
				rowOut = counter;
				foundBiggest = true;
			}
			counter++;
		}
		
		return rowOut;
	}
	
	private void putTokenIn(int columnToSetToken, int rowToSetToken, Token playersToken) {
		board[columnToSetToken][rowToSetToken] = playersToken;
	}
	
	
	/** returns a graphical representation of the board */
	public static String displayBoard(Token[][] myBoard){
		String rowDelimiter = "+";
		String rowNumbering = " ";
		for(int col=0; col<myBoard.length; col++){
			rowDelimiter += "---+";
			rowNumbering += " "+(col+1)+"  ";
		}
		rowDelimiter += "\n";
		
		String rowStr;
		String presentation = rowDelimiter;
		for(int row=myBoard[0].length-1; row >= 0; row--){
			rowStr = "| ";
			for(int col=0; col < myBoard.length; col++){
				rowStr += myBoard[col][row].toString() + " | ";
			}
			presentation += rowStr + "\n" + rowDelimiter;
		}
		presentation += rowNumbering;
		return presentation;
	}
	
	
	
	/** main method, starts the program */
	public static void main(String args[]){
		VierGewinnt game = new VierGewinnt();
		game.play();
	}
}

