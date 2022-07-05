package game;

public class Board {
	protected Player[][] board;
	protected int n, m;

	// Create a board nXm
	public Board(int n, int m)
	{
		this.n = n;
		this.m = m;
		this.board = new Player[n][m];
	}

	// If the place (i,j) is empty, mark this spot as taken by player 'p'
	protected boolean set(int i, int j, Player p) {
		if (this.isEmpty(i, j)) {
			board[i][j] = p;
			return true;
		}
		return false;
	}

	// Checks if the place is empty
	public boolean isEmpty(int i, int j) {
		if (board[i][j] == null)
			return true;
		return false;
	}

	// Returns a pointer to the place i,j that was taken
	public Player get(int i, int j) {
		if (this.isEmpty(i, j))
			return null;
		return board[i][j];
	}

	// Checks if the board is full
	public boolean isFull() {
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				if (isEmpty(i, j))
					return false;
		return true;
	}

	// Returns the whole board as a string
	public String toString() {
		String boardString = "";
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++)
				if (isEmpty(i, j))
					boardString += ".";
				else
					boardString += String.valueOf(board[i][j].getMark());
			boardString += "\n";
		}
		return boardString;
	}

	// Returns the longest line in the board which contains the (i,j)
	// using help functions for each line type we need to check
	protected int maxLineContaining(int i, int j) {
		int max;// for maximum sequence
		int counter;// to count the types of sequences in different functions

		max = this.Column(i, j);
		counter = this.Row(i, j);
		if (counter > max)
			max = counter;

		counter = this.mainDiagonal(i, j);
		if (counter > max)
			max = counter;

		counter = this.reverseDiagonal(i, j);
		if (counter > max)
			max = counter;

		return max;
	}

	// .........................additional functions....................

	// Checks columns
	private int Column(int i, int j) {
		int k;
		int lineCount = 1;

		for (k = i - 1; k >= 0; k--) {
			if (board[i][j].equals(board[k][j]))
				lineCount++;
			else
				break;
		}
		for (k = i + 1; k < n; k++) {
			if (board[i][j].equals(board[k][j]))
				lineCount++;
			else
				break;
		}

		return lineCount;
	}

	// Check rows
	private int Row(int i, int j) {
		int k;
		int colCount = 1;

		for (k = j - 1; k >= 0; k--) {
			if (board[i][j].equals(board[i][k]))
				colCount++;
			else
				break;
		}
		for (k = j + 1; k < m; k++) {
			if (board[i][j].equals(board[i][k]))
				colCount++;
			else
				break;
		}

		return colCount;
	}

	// Check the main diagonal
	private int mainDiagonal(int i, int j) {
		int k;
		int s;
		int mainDiagonalCount = 1;

		for (k = j - 1, s = i - 1; k >= 0 && s >= 0; k--, s--) {
			if (board[i][j].equals(board[s][k]))
				mainDiagonalCount++;
			else
				break;
		}
		for (k = j + 1, s = i + 1; k < m && s < n; k++, s++) {
			if (board[i][j].equals(board[i][k]))
				mainDiagonalCount++;
			else
				break;
		}

		return mainDiagonalCount;
	}

	//Checks the anti-diagonal grid
	private int reverseDiagonal(int i, int j) {
		int s, k;
		int reverseDiagonalCount = 1;

		for (s = i + 1, k = j - 1; s < n && k >= 0; s++, k--) {
			if (board[i][j].equals(board[s][k]))
				reverseDiagonalCount++;
			else
				break;
		}
		for (s = i - 1, k = j + 1; s >= 0 && k < m; s--, k++) {
			if (board[i][j].equals(board[i][k]))
				reverseDiagonalCount++;
			else
				break;
		}

		return reverseDiagonalCount;
	}
}
