package game;

import java.util.Scanner;

public class Game extends Board {
	protected Player[] players;
	protected Scanner s;

	// Create table nXm and save the players
	public Game(int n, int m, Player p1, Player p2) {
		super(n, m);
		this.s = new Scanner(System.in);
		this.players = new Player[2];
		players[0] = p1;
		players[1] = p2;
	}

	// If last move was a winning one, return true, else return false
	protected boolean doesWin(int i, int j) {
		if (i == 0 && j == 0)
			return true;
		else
			return false;
	}

	// Ask the player to enter his move, if it was a winning move return true
	protected boolean onePlay(Player p) {
		int i, j;
		System.out.println(p.toString() + ", please enter x and y:");
		i = s.nextInt();
		j = s.nextInt();

		while (isEmpty(i, j) == false) {
			System.out.println("There is a piece there alredy,try again.");
			System.out.println(p.toString() + ", please enter x and y:");
			i = s.nextInt();
			j = s.nextInt();
		}

		set(i, j, p);
		if (doesWin(i, j) == true)
			return true;
		else
			return false;
	}

	// Ask the two players to play one after the other until somebody wins or the table is full
	public Player play() {

		while (true) {
			if (isFull() == true) {
				System.out.println("The board is full, End of the game."); 
				return null;
			}

			if (onePlay(players[0]) == true) {
				System.out.println(players[0].toString() + "Won!");
				return players[0];
			}

			if (onePlay(players[1]) == true) {
				System.out.println(players[1].toString() + "Won!"); 
				return players[1];
			}
		}
	}
}
