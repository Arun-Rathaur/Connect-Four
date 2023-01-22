public class Board {

	private final int NUM_OF_COLUMNS = 7;
	private final int NUM_OF_ROW = 6;

	private char boardUpdate[][] = {{' ', ' ', ' ', ' ', ' ', ' ', ' '},  // the board state and how it will be updated
									{' ', ' ', ' ', ' ', ' ', ' ', ' '},
									{' ', ' ', ' ', ' ', ' ', ' ', ' '},
									{' ', ' ', ' ', ' ', ' ', ' ', ' '},
									{' ', ' ', ' ', ' ', ' ', ' ', ' '},
									{' ', ' ', ' ', ' ', ' ', ' ', ' '}};
	
	/* 
	 * The board object must contain the board state in some manner.
	 * You must decide how you will do this.
	 * 
	 * You may add addition private/public methods to this class is you wish.
	 * However, you should use best OO practices. That is, you should not expose
	 * how the board is being implemented to other classes. Specifically, the
	 * Player classes.
	 * 
	 */
	
	public Board() {
		for (int row = 0; row < boardUpdate.length; row++) {
			for (int column = 0; column < boardUpdate[0].length; column++) {
				boardUpdate[row][column] = ' ';		// board state in the constructor in a loop version instead of hardcoded
			}
		}
	}
	
	public void printBoard() {
		for (int i = 0; i < boardUpdate.length; i++) {
			System.out.print("|");
			for (int j = 0; j < boardUpdate[i].length; j++) {
				System.out.print(boardUpdate[i][j]);
				System.out.print("|");   // prints the line through each column up and down, makes the board
			}
			System.out.println();
		}
	}
	
	public boolean containsWin() {
        for (int i = 0; i < NUM_OF_COLUMNS - 3; i++) {
			for (int j = 0; j < NUM_OF_ROW; j++) {
				if (boardUpdate[j][i] == boardUpdate[j][i + 1] && boardUpdate[j][i] == boardUpdate[j][i + 2] 
				&& boardUpdate[j][i] == boardUpdate[j][i + 3] && boardUpdate[j][i] != ' ') {
					return true;
				}
			}      // chcecks for a horizontal win, returns true if there is one
		}

		for (int i = 0; i < NUM_OF_ROW - 3; i++) {
			for (int j = 0; j < NUM_OF_COLUMNS; j++) {
				if (boardUpdate[i][j] == boardUpdate[i + 1][j] && boardUpdate[i][j] == boardUpdate[i + 2][j] 
				&& boardUpdate[i][j] == boardUpdate[i + 3][j] && boardUpdate[i][j] != ' ') {
					return true;
				}
			}	 // checks for a vertical win, returns true if there is one
		}

		for (int i = 3; i < NUM_OF_ROW; i++) {
			for (int j = 0; j < NUM_OF_COLUMNS - 3; j++) {
				if (boardUpdate[i][j] == boardUpdate[i - 1][j + 1] && boardUpdate[i][j] == boardUpdate[i - 2][j + 2] 
				&& boardUpdate[i][j] == boardUpdate[i - 3][j + 3] && boardUpdate[i][j] != ' ') {
					return true;
				}

			}   // checks for a upward diagonal win, returns true if there is one
		}

		for (int i = 3; i < NUM_OF_ROW; i++) {
			for (int j = 3; j < NUM_OF_COLUMNS; j++) {
				if (boardUpdate[i][j] == boardUpdate[i - 1][j - 1] && boardUpdate[i][j] == boardUpdate[i - 2][j - 2] 
				&& boardUpdate[i][j] == boardUpdate[i - 3][j - 3] && boardUpdate[i][j] != ' ') {
					return true;
				}
			}   // checks for a lower diagonal win, returns true if there is one
		}
		return false;
	}
	
	public boolean isTie() {
		boolean tieCheck = true;
		for (int i = 0; i < NUM_OF_COLUMNS; i++) {
			if (!tieCheckColumns(i) || tieCheckColumns(i) && containsWin()) {
				tieCheck = false;				// goes through the columns, checks if its full, if its not full and theres no win
				break;  						// or it is full and there is a win, then its not a tie and sets it to false
			}
		}
		return tieCheck;
	}
	
	public void reset() {
		for (int row = 0; row < boardUpdate.length; row++) {
			for (int column = 0; column < boardUpdate[0].length; column++) {
				boardUpdate[row][column] = ' ';  // resets the board by the same way as creating it, setting each spot empty
			}
		}	
	}

	public boolean tieCheckColumns(int columnCheck) {
		for (int i = 0; i < NUM_OF_ROW; i++) {
			if (this.boardUpdate[i][columnCheck] == ' ') {
				return false;  		// checks if each column is full or not for a tie
			}
		}
		return true;
	}

	public boolean columnFullCheck(int columnPlace) {
		for (int i = 0; i < NUM_OF_ROW; i++) {
			if (this.boardUpdate[i][columnPlace - 1] == ' ') {
				return false;					// checks if each spot is full or not to avoid invalid move
			}
		}
		return true;
	}

	public void putMove(int piece, char symbol) {
		for (int i = NUM_OF_ROW - 1; i >= 0; i--) {
			if (boardUpdate[i][piece - 1] == ' ') {
				boardUpdate[i][piece - 1] = symbol;
				break;									// places the move on the actual board with the symbol
			}
		}
	}

	public boolean checkHorizontal(char symbol) {
		for (int i = 0; i < NUM_OF_ROW; i++) {
			for (int j = 1; j < NUM_OF_COLUMNS - 2; j++) {
				if ((this.boardUpdate[i][j] == symbol && this.boardUpdate[i][j + 1] == symbol
				&& this.boardUpdate[i][j + 2] == symbol && this.boardUpdate[i][j - 1] == ' ') || 

				(this.boardUpdate[i][j - 1] == symbol && this.boardUpdate[i][j + 1] == symbol
				&& this.boardUpdate[i][j + 2] == symbol && this.boardUpdate[i][j] == ' ') || 

				(this.boardUpdate[i][j - 1] == symbol && this.boardUpdate[i][j] == symbol
				&& this.boardUpdate[i][j + 2] == symbol && this.boardUpdate[i][j + 1] == ' ') || 

				(this.boardUpdate[i][j - 1] == symbol && this.boardUpdate[i][j] == symbol
				&& this.boardUpdate[i][j + 1] == symbol && this.boardUpdate[i][j + 2] == ' ') ) {
					return true;
				}							// checks each horiztonal spot if there is a win possible, if there is, returns true
			}
		}
		return false;
	}

	public int winHoriztonal(char symbol) {
		for (int i = 0; i < NUM_OF_ROW; i++) {
			for (int j = 1; j < NUM_OF_COLUMNS - 2; j++) {
				if (this.boardUpdate[i][j] == symbol && this.boardUpdate[i][j + 1] == symbol
				&& this.boardUpdate[i][j + 2] == symbol && this.boardUpdate[i][j - 1] == ' ') {
					return j;
				} 

				else if (this.boardUpdate[i][j - 1] == symbol && this.boardUpdate[i][j + 1] == symbol
				&& this.boardUpdate[i][j + 2] == symbol && this.boardUpdate[i][j] == ' ') {
					return (j + 1);
				}

				else if (this.boardUpdate[i][j - 1] == symbol && this.boardUpdate[i][j] == symbol
				&& this.boardUpdate[i][j + 2] == symbol && this.boardUpdate[i][j + 1] == ' ') {
					return (j + 2);
				} 

				else if (this.boardUpdate[i][j - 1] == symbol && this.boardUpdate[i][j] == symbol
				&& this.boardUpdate[i][j + 1] == symbol && this.boardUpdate[i][j + 2] == ' ')  {
					return (j + 3); 			// checks each horiztonal spot if there is a win possible, if there is, place it there
				}
			}
		}
		return 0;
	}

	public boolean checkVertical(char symbol) {
		for (int i = 1; i < NUM_OF_ROW - 2; i++) {
			for (int j = 0; j < NUM_OF_COLUMNS; j++) {
				if (this.boardUpdate[i][j] == symbol && this.boardUpdate[i + 1][j] == symbol
				&& this.boardUpdate[i + 2][j] == symbol && this.boardUpdate[i - 1][j] == ' ') {
					return true;
				}
			}	 // checks each vertical spot for a win chance, if there is, returns true
		}
		
		return false;
	}

	public int winVertical(char symbol) {
		for (int i = 1; i < NUM_OF_ROW - 2; i++) {
			for (int j = 0; j < NUM_OF_COLUMNS; j++) {
				if (this.boardUpdate[i][j] == symbol && this.boardUpdate[i + 1][j] == symbol
				&& this.boardUpdate[i + 2][j] == symbol && this.boardUpdate[i - 1][j] == ' ') {
					return (j + 1);
				}
			}	 // places the vertical win thats available if there is one
		}
		return 0;
	}

	public boolean checkDiagonalOne(char symbol) {
		for (int i = 1; i < NUM_OF_ROW - 2; i++) {
			for (int j = 1; j < NUM_OF_COLUMNS - 2; j++) {
				if ((this.boardUpdate[i][j] == symbol && this.boardUpdate[i + 1][j + 1] == symbol
				&& this.boardUpdate[i + 2][j + 2] == symbol && this.boardUpdate[i - 1][j - 1] == ' ') || 

				(this.boardUpdate[i - 1][j - 1] == symbol && this.boardUpdate[i + 1][j + 1] == symbol
				&& this.boardUpdate[i + 2][j + 2] == symbol && this.boardUpdate[i][j] == ' ') || 

				(this.boardUpdate[i - 1][j - 1] == symbol && this.boardUpdate[i][j] == symbol
				&& this.boardUpdate[i + 2][j + 2] == symbol && this.boardUpdate[i + 1][j + 1] == ' ') || 

				(this.boardUpdate[i - 1][j - 1] == symbol && this.boardUpdate[i][j] == symbol
				&& this.boardUpdate[i + 1][j + 1] == symbol && this.boardUpdate[i + 2][j + 2] == ' ') ) {
					return true; // checks each diagonal spot if there is a win possible, if there is, returns true
				}
			}
		}
		return false;
	}

	public boolean checkDiagonalTwo(char symbol) {
		for (int i = 2; i < NUM_OF_ROW - 1; i++) {
			for (int j = 1; j < NUM_OF_COLUMNS - 2; j++) {
				if ((this.boardUpdate[i][j] == symbol && this.boardUpdate[i - 1][j + 1] == symbol
				&& this.boardUpdate[i - 2][j + 2] == symbol && this.boardUpdate[i + 1][j - 1] == ' ') || 

				(this.boardUpdate[i + 1][j - 1] == symbol && this.boardUpdate[i - 1][j + 1] == symbol
				&& this.boardUpdate[i - 2][j + 2] == symbol && this.boardUpdate[i][j] == ' ') || 

				(this.boardUpdate[i + 1][j - 1] == symbol && this.boardUpdate[i][j] == symbol
				&& this.boardUpdate[i - 2][j + 2] == symbol && this.boardUpdate[i - 1][j + 1] == ' ') || 

				(this.boardUpdate[i + 1][j - 1] == symbol && this.boardUpdate[i][j] == symbol
				&& this.boardUpdate[i - 1][j + 1] == symbol && this.boardUpdate[i - 2][j + 2] == ' ') ) {
					return true; // checks each diagonal spot if there is a win possible, if there is, returns true
				}
			}
		}
		return false;
	}

	public int winDiagonalOne(char symbol) {
		for (int i = 1; i < NUM_OF_ROW - 2; i++) {
			for (int j = 1; j < NUM_OF_COLUMNS - 2; j++) {
				if (this.boardUpdate[i][j] == symbol && this.boardUpdate[i + 1][j + 1] == symbol
				&& this.boardUpdate[i + 2][j + 2] == symbol && this.boardUpdate[i - 1][j - 1] == ' ') {
					return j;
				} 

				else if (this.boardUpdate[i - 1][j - 1] == symbol && this.boardUpdate[i + 1][j + 1] == symbol
				&& this.boardUpdate[i + 2][j + 2] == symbol && this.boardUpdate[i][j] == ' ') {
					return (j + 1);
				}

				else if (this.boardUpdate[i - 1][j - 1] == symbol && this.boardUpdate[i][j] == symbol
				&& this.boardUpdate[i + 2][j + 2] == symbol && this.boardUpdate[i + 1][j + 1] == ' ') {
					return (j + 2);
				} 

				else if (this.boardUpdate[i - 1][j - 1] == symbol && this.boardUpdate[i][j] == symbol
				&& this.boardUpdate[i + 1][j + 1] == symbol && this.boardUpdate[i + 2][j + 2] == ' ')  {
					return (j + 3);
				} 						// checks each diagonal spot if there is a win possible, if there is, returns the move there
			}
		}
		return 0;
	}

	public int winDiagonalTwo(char symbol) {
		for (int i = 2; i < NUM_OF_ROW - 1; i++) {
			for (int j = 1; j < NUM_OF_COLUMNS - 2; j++) {
				if (this.boardUpdate[i][j] == symbol && this.boardUpdate[i - 1][j + 1] == symbol
				&& this.boardUpdate[i - 2][j + 2] == symbol && this.boardUpdate[i + 1][j - 1] == ' ') {
					return j;
				} 

				else if (this.boardUpdate[i + 1][j - 1] == symbol && this.boardUpdate[i - 1][j + 1] == symbol
				&& this.boardUpdate[i - 2][j + 2] == symbol && this.boardUpdate[i][j] == ' ') {
					return (j + 1);
				}

				else if (this.boardUpdate[i + 1][j - 1] == symbol && this.boardUpdate[i][j] == symbol
				&& this.boardUpdate[i - 2][j + 2] == symbol && this.boardUpdate[i - 1][j + 1] == ' ') {
					return (j + 2);
				} 

				else if (this.boardUpdate[i + 1][j - 1] == symbol && this.boardUpdate[i][j] == symbol
				&& this.boardUpdate[i - 1][j + 1] == symbol && this.boardUpdate[i - 2][j + 2] == ' ')  {
					return (j + 3);		// checks each diagonal spot if there is a win possible, if there is, returns the move there
				}
			}
		}
		return 0;
	}

	public boolean checkHorizontalBlock(char symbol) {
		for (int i = 0; i < NUM_OF_ROW; i++) {
			for (int j = 1; j < NUM_OF_COLUMNS - 2; j++) {
				if (((this.boardUpdate[i][j] != symbol && this.boardUpdate[i][j] != ' ') && 
				(this.boardUpdate[i][j + 1] != symbol && this.boardUpdate[i][j + 1] != ' ')
				&& (this.boardUpdate[i][j + 2] != symbol && this.boardUpdate[i][j + 2] !=  ' ') && this.boardUpdate[i][j - 1] == ' ') || 

				((this.boardUpdate[i][j - 1] != symbol && this.boardUpdate[i][j - 1] != ' ') && 
				(this.boardUpdate[i][j + 1] != symbol && this.boardUpdate[i][j + 1] != ' ')
				&& (this.boardUpdate[i][j + 2] != symbol && this.boardUpdate[i][j + 2] != ' ') && this.boardUpdate[i][j] == ' ') || 

				((this.boardUpdate[i][j - 1] != symbol && this.boardUpdate[i][j - 1] != ' ') && 
				(this.boardUpdate[i][j] != symbol && this.boardUpdate[i][j] != ' ')
				&& (this.boardUpdate[i][j + 2] != symbol && this.boardUpdate[i][j + 2] != ' ') && this.boardUpdate[i][j + 1] == ' ') || 

				((this.boardUpdate[i][j - 1] != symbol && this.boardUpdate[i][j - 1] != ' ') && 
				(this.boardUpdate[i][j] != symbol && this.boardUpdate[i][j] != ' ')
				&& (this.boardUpdate[i][j + 1] != symbol && this.boardUpdate[i][j + 1] != ' ') && this.boardUpdate[i][j + 2] == ' ') ) {
					return true;
				}							// checks each horiztonal spot if there is a block possible, if there is, returns true
			}
		}
		return false;
	}

	public int blockHorizontal(char symbol) {
		for (int i = 0; i < NUM_OF_ROW; i++) {
			for (int j = 1; j < NUM_OF_COLUMNS - 2; j++) {
				if ((this.boardUpdate[i][j] != symbol && this.boardUpdate[i][j] != ' ') && 
				(this.boardUpdate[i][j + 1] != symbol && this.boardUpdate[i][j + 1] != ' ')
				&& (this.boardUpdate[i][j + 2] != symbol && this.boardUpdate[i][j + 2] !=  ' ') && this.boardUpdate[i][j - 1] == ' ') {
					return j;
				} 

				else if ((this.boardUpdate[i][j - 1] != symbol && this.boardUpdate[i][j - 1] != ' ') && 
				(this.boardUpdate[i][j + 1] != symbol && this.boardUpdate[i][j + 1] != ' ')
				&& (this.boardUpdate[i][j + 2] != symbol && this.boardUpdate[i][j + 2] != ' ') && this.boardUpdate[i][j] == ' ') {
					return (j + 1);
				}

				else if ((this.boardUpdate[i][j - 1] != symbol && this.boardUpdate[i][j - 1] != ' ') && 
				(this.boardUpdate[i][j] != symbol && this.boardUpdate[i][j] != ' ')
				&& (this.boardUpdate[i][j + 2] != symbol && this.boardUpdate[i][j + 2] != ' ') && this.boardUpdate[i][j + 1] == ' ') {
					return (j + 2);
				}

				else if ((this.boardUpdate[i][j - 1] != symbol && this.boardUpdate[i][j - 1] != ' ') && 
				(this.boardUpdate[i][j] != symbol && this.boardUpdate[i][j] != ' ')
				&& (this.boardUpdate[i][j + 1] != symbol && this.boardUpdate[i][j + 1] != ' ') && this.boardUpdate[i][j + 2] == ' ') {
					return (j + 3);
				}							// checks each horiztonal spot if there is a block possible, if there is, place the block
			}
		}
		return 0;
	}

	public boolean checkVerticalBlock(char symbol) {
		for (int i = 1; i < NUM_OF_ROW - 2; i++) {
			for (int j = 0; j < NUM_OF_COLUMNS; j++) {
				if ((this.boardUpdate[i][j] != symbol && this.boardUpdate[i][j] != ' ') && 
				(this.boardUpdate[i + 1][j] != symbol && this.boardUpdate[i + 1][j] != ' ')
				&& (this.boardUpdate[i + 2][j] != symbol && this.boardUpdate[i + 2][j] != ' ') && this.boardUpdate[i - 1][j] == ' ') {
					return true;
				}
			}	 // checks if there is a vertical block available, if there is, returns true
		}
		
		return false;
	}

	public int blockVertical(char symbol) {
		for (int i = 1; i < NUM_OF_ROW - 2; i++) {
			for (int j = 0; j < NUM_OF_COLUMNS; j++) {
				if ((this.boardUpdate[i][j] != symbol && this.boardUpdate[i][j] != ' ') && 
				(this.boardUpdate[i + 1][j] != symbol && this.boardUpdate[i + 1][j] != ' ')
				&& (this.boardUpdate[i + 2][j] != symbol && this.boardUpdate[i + 2][j] != ' ') && this.boardUpdate[i - 1][j] == ' ') {
					return (j + 1);
				}
			}	 // checks for a vertical block chance, puts one if there is
		}
		
		return 0;
	}

	public boolean checkDiagonalOneBlock(char symbol) {
		for (int i = 1; i < NUM_OF_ROW - 2; i++) {
			for (int j = 1; j < NUM_OF_COLUMNS - 2; j++) {
				if (((this.boardUpdate[i][j] != symbol && this.boardUpdate[i][j] != ' ') && 
				(this.boardUpdate[i + 1][j + 1] != symbol && this.boardUpdate[i + 1][j + 1] != ' ')
				&& (this.boardUpdate[i + 2][j + 2] != symbol && this.boardUpdate[i + 2][j + 2] != ' ') && 
				this.boardUpdate[i - 1][j - 1] == ' ') || 

				((this.boardUpdate[i - 1][j - 1] != symbol && this.boardUpdate[i - 1][j - 1] != ' ') && 
				(this.boardUpdate[i + 1][j + 1] != symbol && this.boardUpdate[i + 1][j + 1] != ' ')
				&& (this.boardUpdate[i + 2][j + 2] != symbol && this.boardUpdate[i + 2][j + 2] != ' ') && 
				this.boardUpdate[i][j] == ' ') || 

				((this.boardUpdate[i - 1][j - 1] != symbol && this.boardUpdate[i - 1][j - 1] != ' ') && 
				(this.boardUpdate[i][j] != symbol && this.boardUpdate[i][j] != ' ')
				&& (this.boardUpdate[i + 2][j + 2] != symbol && this.boardUpdate[i + 2][j + 2] != ' ') && 
				this.boardUpdate[i + 1][j + 1] == ' ') || 

				((this.boardUpdate[i - 1][j - 1] != symbol && this.boardUpdate[i - 1][j - 1] != ' ') && 
				(this.boardUpdate[i][j] != symbol && this.boardUpdate[i][j] != ' ')
				&& (this.boardUpdate[i + 1][j + 1] != symbol && this.boardUpdate[i + 1][j + 1] != ' ') && 
				this.boardUpdate[i + 2][j + 2] == ' ') ) {
					return true; // checks each diagonal spot if there is a block possible, if there is, returns true
				}
			}
		}
		return false;
	}

	public int blockDiagonalOne(char symbol) {
		for (int i = 1; i < NUM_OF_ROW - 2; i++) {
			for (int j = 1; j < NUM_OF_COLUMNS - 2; j++) {
				if ((this.boardUpdate[i][j] != symbol && this.boardUpdate[i][j] != ' ') && 
				(this.boardUpdate[i + 1][j + 1] != symbol && this.boardUpdate[i + 1][j + 1] != ' ')
				&& (this.boardUpdate[i + 2][j + 2] != symbol && this.boardUpdate[i + 2][j + 2] != ' ') && 
				this.boardUpdate[i - 1][j - 1] == ' ') {
					return j;
				}

				else if ((this.boardUpdate[i - 1][j - 1] != symbol && this.boardUpdate[i - 1][j - 1] != ' ') && 
				(this.boardUpdate[i + 1][j + 1] != symbol && this.boardUpdate[i + 1][j + 1] != ' ')
				&& (this.boardUpdate[i + 2][j + 2] != symbol && this.boardUpdate[i + 2][j + 2] != ' ') && 
				this.boardUpdate[i][j] == ' ') {
					return (j + 1);
				}

				else if ((this.boardUpdate[i - 1][j - 1] != symbol && this.boardUpdate[i - 1][j - 1] != ' ') && 
				(this.boardUpdate[i][j] != symbol && this.boardUpdate[i][j] != ' ')
				&& (this.boardUpdate[i + 2][j + 2] != symbol && this.boardUpdate[i + 2][j + 2] != ' ') && 
				this.boardUpdate[i + 1][j + 1] == ' ') {
					return (j + 2);
				} 

				else if ((this.boardUpdate[i - 1][j - 1] != symbol && this.boardUpdate[i - 1][j - 1] != ' ') && 
				(this.boardUpdate[i][j] != symbol && this.boardUpdate[i][j] != ' ')
				&& (this.boardUpdate[i + 1][j + 1] != symbol && this.boardUpdate[i + 1][j + 1] != ' ') && 
				this.boardUpdate[i + 2][j + 2] == ' ') {
					return (j + 3); 	// checks each diagonal spot if there is a block possible, if there is, return the block spot
				}
			}
		}
		return 0;
	}

	public boolean checkDiagonalTwoBlock(char symbol) {
		for (int i = 2; i < NUM_OF_ROW - 1; i++) {
			for (int j = 1; j < NUM_OF_COLUMNS - 2; j++) {
				if (((this.boardUpdate[i][j] != symbol && this.boardUpdate[i][j] != ' ') && 
				(this.boardUpdate[i - 1][j + 1] != symbol && this.boardUpdate[i - 1][j + 1] != ' ')
				&& (this.boardUpdate[i - 2][j + 2] != symbol && this.boardUpdate[i - 2][j + 2] != ' ') && 
				this.boardUpdate[i + 1][j - 1] == ' ') || 

				((this.boardUpdate[i + 1][j - 1] != symbol && this.boardUpdate[i + 1][j - 1] != ' ') && 
				(this.boardUpdate[i - 1][j + 1] != symbol && this.boardUpdate[i - 1][j + 1] != ' ')
				&& (this.boardUpdate[i - 2][j + 2] != symbol && this.boardUpdate[i - 2][j + 2] != ' ') && 
				this.boardUpdate[i][j] == ' ') || 

				((this.boardUpdate[i + 1][j - 1] != symbol && this.boardUpdate[i + 1][j - 1] != ' ') && 
				(this.boardUpdate[i][j] != symbol && this.boardUpdate[i][j] != ' ')
				&& (this.boardUpdate[i - 2][j + 2] != symbol && this.boardUpdate[i - 2][j + 2] != ' ') && 
				this.boardUpdate[i - 1][j + 1] == ' ') || 

				((this.boardUpdate[i + 1][j - 1] != symbol && this.boardUpdate[i + 1][j - 1] != ' ') && 
				(this.boardUpdate[i][j] != symbol && this.boardUpdate[i][j] != ' ')
				&& (this.boardUpdate[i - 1][j + 1] != symbol && this.boardUpdate[i - 1][j + 1] != ' ') && 
				this.boardUpdate[i - 2][j + 2] == ' ') ) {
					return true; // checks each diagonal spot if there is a block possible, if there is, returns true
				}
			}
		}
		return false;
	}
	
	public int blockDiagonalTwo(char symbol) {
		for (int i = 2; i < NUM_OF_ROW - 1; i++) {
			for (int j = 1; j < NUM_OF_COLUMNS - 2; j++) {
				if ((this.boardUpdate[i][j] != symbol && this.boardUpdate[i][j] != ' ') && 
				(this.boardUpdate[i - 1][j + 1] != symbol && this.boardUpdate[i - 1][j + 1] != ' ')
				&& (this.boardUpdate[i - 2][j + 2] != symbol && this.boardUpdate[i - 2][j + 2] != ' ') && 
				this.boardUpdate[i + 1][j - 1] == ' ') {
					return j;
				}

				else if ((this.boardUpdate[i + 1][j - 1] != symbol && this.boardUpdate[i + 1][j - 1] != ' ') && 
				(this.boardUpdate[i - 1][j + 1] != symbol && this.boardUpdate[i - 1][j + 1] != ' ')
				&& (this.boardUpdate[i - 2][j + 2] != symbol && this.boardUpdate[i - 2][j + 2] != ' ') && 
				this.boardUpdate[i][j] == ' ') {
					return (j + 1);
				}

				else if ((this.boardUpdate[i + 1][j - 1] != symbol && this.boardUpdate[i + 1][j - 1] != ' ') && 
				(this.boardUpdate[i][j] != symbol && this.boardUpdate[i][j] != ' ')
				&& (this.boardUpdate[i - 2][j + 2] != symbol && this.boardUpdate[i - 2][j + 2] != ' ') && 
				this.boardUpdate[i - 1][j + 1] == ' ') {
					return (j + 2);
				}

				else if ((this.boardUpdate[i + 1][j - 1] != symbol && this.boardUpdate[i + 1][j - 1] != ' ') && 
				(this.boardUpdate[i][j] != symbol && this.boardUpdate[i][j] != ' ')
				&& (this.boardUpdate[i - 1][j + 1] != symbol && this.boardUpdate[i - 1][j + 1] != ' ') && 
				this.boardUpdate[i - 2][j + 2] == ' ')  {
					return (j + 3); // checks each diagonal spot if there is a block possible, if there is, returns the block spot
				}
			}
		}
		return 0;
	}

}

