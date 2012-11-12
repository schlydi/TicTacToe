package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class contains all methods for communicating with the players.
 * @author Lydia
 */
public class Communication {

	/**
	 * This is the String whichs is read from the console.
	 */
	private String lineIn = "";

	/**
	 * This is the instance of the TTTConsole class
	 * which will be initialized in the constructor.
	 */
	private TTTConsole tttc;

	/**
	 * This is an instance of the Game class.
	 */
	private Game game = new Game();

	/**
	 * The constructor which initializes the TTTConsole.
	 * @param tttc   the instace of the TTTConsole
	 */
	public Communication(final TTTConsole tttc) {
		this.tttc = tttc;
	}

	/**
	 * This method communicates with the player and reads its input.
	 * @param player       player 1 or player 2
	 * @throws IOException the exception is caught
	 *                     in the method (try..catch)
	 */
	public final void readPlayer(int player) throws IOException {

		System.out.println("Player " + player + ": ");
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);

		try {
			lineIn = in.readLine();
			if (lineIn.equalsIgnoreCase("quit")) {
				endMessage();
			}
			if (game.testValidity(lineIn)) {
				game.setScore(tttc, lineIn, player);
			} else {
				errorMove(player);
			}
		} catch (IOException e) {
			System.out.println(e.toString());
		}

		switch (player) {
		case 1: player = 2;
				break;
		case 2: player = 1;
				break;
		default:
			break;
		}

		readPlayer(player);
	}

	/**
	 * This method tells the player it should try another move.
	 * @param player       player 1 or player 2
	 * @throws IOException the exception is caught in the
	 *                     Communication.readPlayer method
	 */
	public final void errorMove(final int player) throws IOException {
		System.out.println("This is no valid move, please try again");
		readPlayer(player);
	}

	/**
	 * This method tell the player of its win.
	 * @param player      player 1 or player 2
	 */
	public final void win(final int player) {
		System.out.println("You won this game, player " + player + "!!");
	}

	/**
	 * This method prints out the end message and asks
	 * for the start of a new game.
	 * @throws IOException the exception is caught in the
	 *                     Communication.readPlayer method
	 */
	public final void endMessage() throws IOException {
		System.out.println("Do you want to start a new game? ");

		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);

		try {
			lineIn = in.readLine();
		} catch (IOException e) {
			System.out.println(e.toString());
		}

		if (lineIn.equalsIgnoreCase("Yes")) {
			tttc = new TTTConsole();
			tttc.startGame(tttc);
		}
		else {
			System.exit(0);
		}
	}

}
