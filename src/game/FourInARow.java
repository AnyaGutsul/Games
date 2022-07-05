package game;

public class FourInARow extends Game {

	// Create a FourInARow game with table 6X7 and players names that were given
	public FourInARow(String player1, String player2) {
		super(6, 7, new Player(player1, 'W'), new Player(player2, 'B'));
	}

	@Override
	protected boolean doesWin(int i, int j) {
		if (maxLineContaining(i, j) == 4)
			return true;
		else
			return false;
	}

	//Ask the player to enter a column for the move,if the column is full ask for choosing another. 
	@Override
	protected boolean onePlay(Player p) {
		int c, i;
		System.out.println(p.toString() + ", please enter column:");
		c = s.nextInt();

		// if the column that was chosen is full,ask for another column.
		while (isEmpty(0, c) == false) {
			System.out.println("The column is full,try another one.");
			System.out.println(p.toString() + ", please enter column:");
			c = s.nextInt();
		}

		// Find the first empty place in the column and mark this spot as taken by player 'p'
		for (i = 5; i >= 0; i--) {
			if (isEmpty(i, c) == true) {
				set(i, c, p);
				break;
			}
		}

		if (doesWin(i, c) == true)
			return true;
		else
			return false;
	}

}
