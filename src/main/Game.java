package main;

import java.io.IOException;
import java.util.HashMap;


/**
 * This class contains methods for the actual game.
 * @author Lydia
 */
public class Game {

	/**
	 * This method sets the score into the array.
	 *
	 * @param tttc         the instance of the current TicTacToe game
	 * @param move         the move the player made
	 * @param player       player 1 or player 2
	 * @return             the new board
	 * @throws IOException the exception is caught in the
	 *                     Communication.readPlayer method
	 */
	public final char[][] setScore(final TTTConsole tttc, final String move, final int player) throws IOException {

		char[][] score = tttc.getScore();
		HashMap<Character, Integer> mapping = tttc.getMappingScore();
		char column = move.charAt(0);
		char line = move.charAt(1);
		int columnNumber = mapping.get(column);
		int lineNumber = mapping.get(line);
		Communication c = new Communication(tttc);

		if (testMove(columnNumber, lineNumber, score)) {
			switch (player) {
			case 1: score[columnNumber][lineNumber] = 'X';
			break;
			case 2: score[columnNumber][lineNumber] = 'O';
			break;
			default: break;
			}
			changeBoard(tttc, move, player);
			tttc.printBoard();
		} else {
			c.errorMove(player);
			tttc.printBoard();
		}
		if (testWin(score)) {
			c.win(player);
			stopGame(c);
		}
		return score;
	}

	/**
	 * This method sets a move a player made and returns the new board.
	 *
	 * @param tttc         the instance of the current TicTacToe game
	 * @param move         the move the player made
	 * @param player       player 1 or player 2
	 * @return             the new board
	 */
	public final String[] changeBoard(final TTTConsole tttc, final String move, final int player) {

		String[] board = tttc.getBoard();
		HashMap<Character, Integer> mapping = tttc.getMappingBoard();
		char column = move.charAt(0);
		char line = move.charAt(1);
		int columnNumber = mapping.get(column);
		int lineNumber = mapping.get(line);

		StringBuffer changeIt = new StringBuffer(board[lineNumber]);
		switch (player) {
			case 1: changeIt.setCharAt(columnNumber, 'X');
			break;
			case 2: changeIt.setCharAt(columnNumber, 'O');
			break;
			default: break;
		}
		board[lineNumber] = changeIt.toString();

		return board;
	}

	/**
	 * This method tests if an entered command is a valid move.
	 *
	 * @param lineIn  the command from the console
	 * @return        valid or not
	 */
	public final Boolean testValidity(final String lineIn) {
		Boolean moveValid = false;
		String[] validMoves = {"A2", "B2", "C2",
				               "A1", "B1", "C1",
				               "A3", "B3", "C3"};
		for (int i = 0; i < validMoves.length; i++) {
			if (lineIn.equalsIgnoreCase(validMoves[i])) {
				moveValid = true;
				break;
			}
		}
		return moveValid;
	}

	/**
	 * This method tests if a move wants to be made on a blocked field.
	 *
	 * @param columnNumber   giving position of the character in the string
	 * @param lineNumber     getting the correct line from the array board
	 * @param score          the array which contains the score
	 * @return               true or false
	 */
	public final Boolean testMove(final int columnNumber, final int lineNumber, final char[][] score) {
		Boolean valid = true;
		char testChar = score[columnNumber][lineNumber];
		if (testChar == 'X' || testChar == 'O') {
			valid = false;
		}
		return valid;
	}

	/**
	 * This method tests if any of the players have won.
	 *
	 * @param score     the array which contains the score
	 * @return          true or false
	 */
	public final Boolean testWin(final char[][] score) {
		Boolean win = false;
		if (testWinRows(score)) {
			win = true;
		} else if (testWinColumns(score)) {
			win = true;
		} else if (testWinCrosslines(score)) {
			win = true;
		}
		return win;
	}

	/**
	 * This method tests the rows for a winner.
	 *
	 * @param score     the array which contains the score
	 * @return          true or false
	 */
	public final Boolean testWinRows(final char[][] score) {
		Boolean win = false;
		for (int i = 0; i < score.length; i++) {
			char[] scoreRow = score[i];
			int counter = 0;
			for (int j = 0; j < scoreRow.length; j++) {
				if (scoreRow[j] == 'X') {
					counter += 1;
				} else if (scoreRow[j] == 'O') {
					counter -= 1;
				}
			}
			if (counter == 3 || counter == -3) {
				win = true;
				break;
			}
		}
		return win;
	}

	/**
	 * This method tests the lines for a winner.
	 *
	 * @param score     the array which contains the score
	 * @return          true or false
	 */
	public final Boolean testWinColumns(final char[][] score) {
		Boolean win = false;
		for (int i = 0; i < score.length; i++) {
			int counter = 0;
			for (int j = 0; j < score.length; j++) {
				if (score[j][i] == 'X') {
					counter += 1;
				} else if (score[j][i] == 'O') {
					counter -= 1;
				}
			}
			if (counter == 3 || counter == -3) {
				win = true;
				break;
			}
		}
		return win;
	}

	/**
	 * This method tests the crosslines for a winner.
	 *
	 * @param score     the array which contains the score
	 * @return          true or false
	 */
	public final Boolean testWinCrosslines(final char[][] score) {
		Boolean win = false;
		char testChar = score[0][0];
		if (Character.isLetter(testChar) && score[1][1] == testChar && score[2][2] == testChar) {
			win = true;
		} else {
			testChar = score[2][0];
			if (Character.isLetter(testChar) && score[1][1] == testChar && score[0][2] == testChar) {
				win = true;
			}
		}
		return win;
	}

	/**
	 * This method stops the game.
	 *
	 * @param c            an instance of the class Communication.java
	 * @throws IOException the exception is caught in the
	 *                     Communication.readPlayer method
	 */
	public final void stopGame(final Communication c) throws IOException {
		c.endMessage();
	}
}
