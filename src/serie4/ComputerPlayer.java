package serie4;

/* Programmierung 1 (HS 11)
 * Serie 4 
 *  
 * Salim Hermidas 
 * 11-125-382
 *
 */ 

/** A very intelligent computer player */
public class ComputerPlayer implements IPlayer{
	private VierGewinnt.Token token;
	private int[] scores = new int[VierGewinnt.COLS];
	
	public int getNextColumn(VierGewinnt.Token[][] board){
		int col = 0;
		int actual = 0;
		
		for (int i = 0; i < board.length; i++) {
			scores[i] = calculateScoreForColumn(i, board);
		}
		
		int max = Integer.MIN_VALUE;
		for (int j : scores) {
			/*
			 * Debug outline for calculated scores.
			 * Uncomment for debugging.
			 * */
			//System.out.println("Col: " + (actual + 1) + " Score: " + scores[actual]);
			if (max<j) {
				 max=j;
				 col = actual;
			}
			actual++;
		}
		
		return col;
	}
	
	private int calculateScoreForColumn(int col, VierGewinnt.Token[][] boardToCheck){
		int score = 0;
		VierGewinnt.Token myToken, otherToken;
		if (token.equals(VierGewinnt.Token.player1)) {
			myToken = VierGewinnt.Token.player1;
			otherToken = VierGewinnt.Token.player2;
		} else {
			myToken = VierGewinnt.Token.player2;
			otherToken = VierGewinnt.Token.player1;
		}
		if (isColFull(col, boardToCheck)) {
			score = -1000;
		} else {
			//some checks to ad to score
			if (iWinWithThis(col, boardToCheck)) score = score + 2500;
			if (otherWinWithThis(col, boardToCheck)) score = score + 700;

			score = score + (countTokens(col, 3, myToken, boardToCheck) * 4);
			score = score + (countTokens(col, 2, myToken, boardToCheck) * 4);

			score = score + (countTokens(col, 3, otherToken, boardToCheck) * 2);
			score = score + (countTokens(col, 2, otherToken, boardToCheck) * 2);

			score = score + (countTokensWithEmpty(col, 2, myToken, boardToCheck) * 4);
			score = score + (countTokensWithEmpty(col, 1, myToken, boardToCheck) * 6);

			score = score + (countTokensWithEmpty(col, 2, otherToken, boardToCheck) * 5);
			score = score + (countTokensWithEmpty(col, 1, otherToken, boardToCheck) * 7);

			score = score - (shouldWaitForNext(col, 4, myToken, otherToken, boardToCheck) * 24);
			score = score - (shouldWaitForNext(col, 3, myToken, otherToken, boardToCheck) * 6);
			score = score - (shouldWaitForNext(col, 2, myToken, otherToken, boardToCheck) * 3);

			score = score - (shouldDoThisOnlyToWin(col, 4, myToken, otherToken, boardToCheck) * 500);
			
			score = score + scoreOfPosition(col, boardToCheck);
		}		
		return score;
	}
	
	private int scoreOfPosition(int col, VierGewinnt.Token[][] boardToCheck) {
		int score = 0;
		int size = boardToCheck.length - 1;
		int mid = (size/2) + 1;
		if (col < mid) {
			score = col + 1;
		}
		if (col >= mid) {
			int a = mid - 1;
			int dist =  col - a;
			int x = (2 * dist) -1;
			score = col - x;
		}
		return score;
	}
	
	private boolean iWinWithThis(int col, VierGewinnt.Token[][] boardToCheck) {
		boolean score = false;
		VierGewinnt.Token tok = token;
		int row = biggestEmptyRow(col, boardToCheck);
		boolean a = checkA(col, row, tok, boardToCheck, 4);	//Wagerecht
		boolean b = checkB(col, row, tok, boardToCheck, 4);	//Senkrecht
		boolean c = checkC(col, row, tok, boardToCheck, 4);	//links unten nach rechts oben
		boolean d = checkD(col, row, tok, boardToCheck, 4);	//links oben nach rechts unten
		if (a || b || c || d) {
			score = true;
		}
		return score;
	}
	
	private boolean otherWinWithThis(int col, VierGewinnt.Token[][] boardToCheck) {
		boolean score = false;
		VierGewinnt.Token tok;
		if (token.equals(VierGewinnt.Token.player1)) {
			tok = VierGewinnt.Token.player2;
		} else {
			tok = VierGewinnt.Token.player1;
		}
		int row = biggestEmptyRow(col, boardToCheck);
		boolean a = checkA(col, row, tok, boardToCheck, 4);	//Wagerecht
		boolean b = checkB(col, row, tok, boardToCheck, 4);	//Senkrecht
		boolean c = checkC(col, row, tok, boardToCheck, 4);	//links unten nach rechts oben
		boolean d = checkD(col, row, tok, boardToCheck, 4);	//links oben nach rechts unten
		if (a || b || c || d) {
			score = true;
		}
		return score;
	}
	
	private int countTokens(int col, int numbs, VierGewinnt.Token tok, VierGewinnt.Token[][] boardToCheck) {
		int size = 0;
		int row = biggestEmptyRow(col, boardToCheck);
		if (checkA(col, row, tok, boardToCheck, numbs)) size ++;
		if (checkB(col, row, tok, boardToCheck, numbs)) size ++;
		if (checkC(col, row, tok, boardToCheck, numbs)) size ++;
		if (checkD(col, row, tok, boardToCheck, numbs)) size ++;
		return size;
	}

	private int countTokensWithEmpty(int col, int numbs, VierGewinnt.Token tok, VierGewinnt.Token[][] boardToCheck) {
		int size = 0;
		int row = biggestEmptyRow(col, boardToCheck);
		if (emptyCheckA(col, row, tok, boardToCheck, numbs)) size ++;
		if (emptyCheckB(col, row, tok, boardToCheck, numbs)) size ++;
		if (emptyCheckC(col, row, tok, boardToCheck, numbs)) size ++;
		if (emptyCheckD(col, row, tok, boardToCheck, numbs)) size ++;
		return size;
	}
	
	private int shouldWaitForNext(int col, int numbs, VierGewinnt.Token myTok, VierGewinnt.Token otherTok, VierGewinnt.Token[][] boardToCheck) {
		int size = 0;
		int rowPre = biggestEmptyRow(col, boardToCheck);
		VierGewinnt.Token[][] boardToWork = new VierGewinnt.Token[VierGewinnt.COLS][VierGewinnt.ROWS];
		
		for (int i = 0; i < VierGewinnt.COLS; i++) {
			for (int j = 0; j < VierGewinnt.ROWS; j++) {
				boardToWork[i][j] = boardToCheck[i][j];
			}
		}

		boardToWork[col][rowPre] = otherTok;
		int rowPast = biggestEmptyRow(col, boardToWork);
		if (checkA(col, rowPast, myTok, boardToWork, numbs)) size ++;
		if (checkB(col, rowPast, myTok, boardToWork, numbs)) size ++;
		if (checkC(col, rowPast, myTok, boardToWork, numbs)) size ++;
		if (checkD(col, rowPast, myTok, boardToWork, numbs)) size ++;
		return size;
	}
	
	private int shouldDoThisOnlyToWin(int col, int numbs, VierGewinnt.Token myTok, VierGewinnt.Token otherTok, VierGewinnt.Token[][] boardToCheck) {
		int size = 0;
		int rowPre = biggestEmptyRow(col, boardToCheck);
		VierGewinnt.Token[][] boardToWork = new VierGewinnt.Token[VierGewinnt.COLS][VierGewinnt.ROWS];
		
		for (int i = 0; i < VierGewinnt.COLS; i++) {
			for (int j = 0; j < VierGewinnt.ROWS; j++) {
				boardToWork[i][j] = boardToCheck[i][j];
			}
		}

		boardToWork[col][rowPre] = myTok;
		int rowPast = biggestEmptyRow(col, boardToWork);
		if (checkA(col, rowPast, otherTok, boardToWork, numbs)) size ++;
		if (checkB(col, rowPast, otherTok, boardToWork, numbs)) size ++;
		if (checkC(col, rowPast, otherTok, boardToWork, numbs)) size ++;
		if (checkD(col, rowPast, otherTok, boardToWork, numbs)) size ++;
		
		if (size > 0) {
			return 1;
		} else {
			return 0;
		}
	}
	
	
	//My Helper Methods
	private int biggestEmptyRow(int colToFind, VierGewinnt.Token[][] board) {
		int rowOut = 0;
		
		int counter = 0;
		boolean foundBiggest = false;
		while (counter < VierGewinnt.ROWS && !foundBiggest) {
			if (board[colToFind][counter].equals(VierGewinnt.Token.empty)) {
				rowOut = counter;
				foundBiggest = true;
			}
			counter++;
		}
		
		return rowOut;
	}
	
	private boolean checkA(int colToCheck, int rowToCheck, VierGewinnt.Token tokToCheck, VierGewinnt.Token[][] board, int numbsForTrue) {
		boolean status = false;
		int size = 1;
		VierGewinnt.Token actualPlayersToken;
		actualPlayersToken = tokToCheck;
		
		if (rowToCheck < VierGewinnt.ROWS) {
		
		int position = colToCheck + 1;
		while (position < VierGewinnt.COLS) {
			if (board[position][rowToCheck].equals(actualPlayersToken)) {
				size++;
			} else {
				position = VierGewinnt.COLS;
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
		
		if (size >= numbsForTrue) {
			status = true;
		}
		
		} else {
			status = false;
		}
		
		return status;
	}

	private boolean checkB(int colToCheck, int rowToCheck, VierGewinnt.Token tokToCheck, VierGewinnt.Token[][] board, int numbsForTrue) {
		boolean status = false;
		int size = 1;
		VierGewinnt.Token actualPlayersToken;
		actualPlayersToken = tokToCheck;
		
		if (rowToCheck < VierGewinnt.ROWS) {
		
		int position = rowToCheck + 1;
		while (position < VierGewinnt.ROWS) {
			if (board[colToCheck][position].equals(actualPlayersToken)) {
				size++;
			} else {
				position = VierGewinnt.ROWS;
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
		
		if (size >= numbsForTrue) {
			status = true;
		}
		
		} else {
			status = false;
		}
		
		return status;
	}

	private boolean checkC(int colToCheck, int rowToCheck, VierGewinnt.Token tokToCheck, VierGewinnt.Token[][] board, int numbsForTrue) {
		boolean status = false;
		int size = 1;
		VierGewinnt.Token actualPlayersToken;
		actualPlayersToken = tokToCheck;
		
		if (rowToCheck < VierGewinnt.ROWS) {

		int positionCol = colToCheck + 1;
		int positionRow = rowToCheck + 1;
		while (positionCol < VierGewinnt.COLS && positionRow < VierGewinnt.ROWS) {
			if (board[positionCol][positionRow].equals(actualPlayersToken)) {
				size++;
			} else {
				positionCol = VierGewinnt.COLS;
				positionRow = VierGewinnt.ROWS;
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
		
		if (size >= numbsForTrue) {
			status = true;
		}
		
		} else {
			status = false;
		}
		
		return status;
	}
	
	private boolean checkD(int colToCheck, int rowToCheck, VierGewinnt.Token tokToCheck, VierGewinnt.Token[][] board, int numbsForTrue) {
		boolean status = false;
		int size = 1;
		VierGewinnt.Token actualPlayersToken;
		actualPlayersToken = tokToCheck;
		
		if (rowToCheck < VierGewinnt.ROWS) {

		int positionCol = colToCheck + 1;
		int positionRow = rowToCheck - 1;
		while (positionCol < VierGewinnt.COLS && positionRow >= 0) {
			if (board[positionCol][positionRow].equals(actualPlayersToken)) {
				size++;
			} else {
				positionCol = VierGewinnt.COLS;
				positionRow = -1;
			}
			positionCol++;
			positionRow--;
		}
		
		positionCol = colToCheck - 1;
		positionRow = rowToCheck + 1;
		while (positionCol >= 0 && positionRow < VierGewinnt.ROWS) {
			if (board[positionCol][positionRow].equals(actualPlayersToken)) {
				size++;
			} else {
				positionCol = -1;
				positionRow = VierGewinnt.ROWS;
			}
			positionCol--;
			positionRow++;
		}
		
		if (size >= numbsForTrue) {
			status = true;
		}
		
		} else {
			status = false;
		}
		
		return status;
	}
	
	private boolean emptyCheckA(int colToCheck, int rowToCheck, VierGewinnt.Token tokToCheck, VierGewinnt.Token[][] board, int numbsForTrue) {
		boolean status = false;
		int size = 1;
		int check = 0;
		VierGewinnt.Token actualPlayersToken;
		actualPlayersToken = tokToCheck;
		
		if (rowToCheck < VierGewinnt.ROWS) {
		
		int position = colToCheck + 1;
		while (position < VierGewinnt.COLS && check <= numbsForTrue) {
			if (board[position][rowToCheck].equals(actualPlayersToken) || board[position][rowToCheck].equals(VierGewinnt.Token.empty)) {
				size++;
				if (board[position][rowToCheck].equals(VierGewinnt.Token.empty)) {
					check++;
					if (check > numbsForTrue) {
						check--;
						size--;
						position = VierGewinnt.COLS;
					}
				}
			} else {
				position = VierGewinnt.COLS;
			}
			position++;
		}
		
		position = colToCheck - 1;
		while (position >= 0) {
			if (board[position][rowToCheck].equals(actualPlayersToken) || board[position][rowToCheck].equals(VierGewinnt.Token.empty)) {
				size++;
				if (board[position][rowToCheck].equals(VierGewinnt.Token.empty)) {
					check++;
					if (check > numbsForTrue) {
						check--;
						size--;
						position = -1;
					}
				}
			} else {
				position = -1;
			}
			position--;
		}
		
		if (size >= 4 && check <= numbsForTrue) {
			status = true;
		}
		
		} else {
			status = false;
		}
		
		return status;
	}

	private boolean emptyCheckB(int colToCheck, int rowToCheck, VierGewinnt.Token tokToCheck, VierGewinnt.Token[][] board, int numbsForTrue) {
		boolean status = false;
		int size = 1;
		int check = 0;
		VierGewinnt.Token actualPlayersToken;
		actualPlayersToken = tokToCheck;
		
		if (rowToCheck < VierGewinnt.ROWS) {
		
		int position = rowToCheck + 1;
		while (position < VierGewinnt.ROWS) {
			if (board[colToCheck][position].equals(actualPlayersToken) || board[position][rowToCheck].equals(VierGewinnt.Token.empty)) {
				size++;
				if (board[position][rowToCheck].equals(VierGewinnt.Token.empty)) {
					check++;
					if (check > numbsForTrue) {
						check--;
						size--;
						position = VierGewinnt.ROWS;
					}
				}
			} else {
				position = VierGewinnt.ROWS;
			}
			position++;
		}
		
		position = rowToCheck - 1;
		while (position >= 0) {
			if (board[colToCheck][position].equals(actualPlayersToken) || board[position][rowToCheck].equals(VierGewinnt.Token.empty)) {
				size++;
				if (board[position][rowToCheck].equals(VierGewinnt.Token.empty)) {
					check++;
					if (check > numbsForTrue) {
						check--;
						size--;
						position = -1;
					}
				}
			} else {
				position = -1;
			}
			position--;
		}
		
		if (size >= 4 && check <= numbsForTrue) {
			status = true;
		}
		
		} else {
			status = false;
		}
		
		return status;
	}

	private boolean emptyCheckC(int colToCheck, int rowToCheck, VierGewinnt.Token tokToCheck, VierGewinnt.Token[][] board, int numbsForTrue) {
		boolean status = false;
		int size = 1;
		int check = 0;
		VierGewinnt.Token actualPlayersToken;
		actualPlayersToken = tokToCheck;
		
		if (rowToCheck < VierGewinnt.ROWS) {

		int positionCol = colToCheck + 1;
		int positionRow = rowToCheck + 1;
		while (positionCol < VierGewinnt.COLS && positionRow < VierGewinnt.ROWS) {
			if (board[positionCol][positionRow].equals(actualPlayersToken) || board[positionCol][positionRow].equals(VierGewinnt.Token.empty)) {
				size++;
				if (board[positionCol][positionRow].equals(VierGewinnt.Token.empty)) {
					check++;
					if (check > numbsForTrue) {
						check--;
						size--;
						positionCol = VierGewinnt.COLS;
						positionRow = VierGewinnt.ROWS;
					}
				}
			} else {
				positionCol = VierGewinnt.COLS;
				positionRow = VierGewinnt.ROWS;
			}
			positionCol++;
			positionRow++;
		}
		
		positionCol = colToCheck - 1;
		positionRow = rowToCheck - 1;
		while (positionCol >= 0 && positionRow >= 0) {
			if (board[positionCol][positionRow].equals(actualPlayersToken) || board[positionCol][positionRow].equals(VierGewinnt.Token.empty)) {
				size++;
				if (board[positionCol][positionRow].equals(VierGewinnt.Token.empty)) {
					check++;
					if (check > numbsForTrue) {
						check--;
						size--;
						positionCol = -1;
						positionRow = -1;
					}
				}
			} else {
				positionCol = -1;
				positionRow = -1;
			}
			positionCol--;
			positionRow--;
		}
		
		if (size >= 4 && check <= numbsForTrue) {
			status = true;
		}
		
		} else {
			status = false;
		}
		
		return status;
	}
	
	private boolean emptyCheckD(int colToCheck, int rowToCheck, VierGewinnt.Token tokToCheck, VierGewinnt.Token[][] board, int numbsForTrue) {
		boolean status = false;
		int size = 1;
		int check = 0;
		VierGewinnt.Token actualPlayersToken;
		actualPlayersToken = tokToCheck;
		
		if (rowToCheck < VierGewinnt.ROWS) {

		int positionCol = colToCheck + 1;
		int positionRow = rowToCheck - 1;
		while (positionCol < VierGewinnt.COLS && positionRow >= 0) {
			if (board[positionCol][positionRow].equals(actualPlayersToken) || board[positionCol][positionRow].equals(VierGewinnt.Token.empty)) {
				size++;
				if (board[positionCol][positionRow].equals(VierGewinnt.Token.empty)) {
					check++;
					if (check > numbsForTrue) {
						check--;
						size--;
						positionCol = VierGewinnt.COLS;
						positionRow = -1;
					}
				}
			} else {
				positionCol = VierGewinnt.COLS;
				positionRow = -1;
			}
			positionCol++;
			positionRow--;
		}
		
		positionCol = colToCheck - 1;
		positionRow = rowToCheck + 1;
		while (positionCol >= 0 && positionRow < VierGewinnt.ROWS) {
			if (board[positionCol][positionRow].equals(actualPlayersToken) || board[positionCol][positionRow].equals(VierGewinnt.Token.empty)) {
				size++;
				if (board[positionCol][positionRow].equals(VierGewinnt.Token.empty)) {
					check++;
					if (check > numbsForTrue) {
						check--;
						size--;
						positionCol = -1;
						positionRow = VierGewinnt.ROWS;
					}
				}
			} else {
				positionCol = -1;
				positionRow = VierGewinnt.ROWS;
			}
			positionCol--;
			positionRow++;
		}
		
		if (size >= 4 && check <= numbsForTrue) {
			status = true;
		}
		
		} else {
			status = false;
		}
		
		return status;
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
	
	//Set- and Get-Methods
	public void setToken(VierGewinnt.Token token){
		this.token = token;
	}
	
	public VierGewinnt.Token getToken(){
		return this.token;
	}
	
	public String getProgrammers(){
		return "DKPillo";
	}
}

