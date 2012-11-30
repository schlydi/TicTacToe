package main;

import java.io.IOException;
import java.util.HashMap;


/**
 * This is the class containing the main method.
 * @author Lydia
 */
public class TTTConsole {

	/**
	 * An array containing the board for the console.
	 */
	private String[] board = {"  A B C",
			"1  | | ",
			"  -+-+-",
			"2  | | ",
			"  -+-+-",
	        "3  | | "};

	/**
	 * The array containing the score.
	 */
	private char[][] score = new char[3][3];

	/**
	 * This is a map containing the mapping
	 * where to put the score into the board.
	 */
	private HashMap<Character, Integer> mappingBoard = new HashMap<Character, Integer>();

	/**
	 * This is a map containing the mapping
	 * where to put the score into the board.
	 */
	private HashMap<Character, Integer> mappingScore = new HashMap<Character, Integer>();

	/**
	 *
	 */
	public TTTConsole() {
		fillMap(mappingBoard, 'A', 2);
		fillMap(mappingBoard, 'B', 4);
		fillMap(mappingBoard, 'C', 6);
		fillMap(mappingBoard, '1', 1);
		fillMap(mappingBoard, '2', 3);
		fillMap(mappingBoard, '3', 5);

		fillMap(mappingScore, 'A', 0);
		fillMap(mappingScore, 'B', 1);
		fillMap(mappingScore, 'C', 2);
		fillMap(mappingScore, '1', 0);
		fillMap(mappingScore, '2', 1);
		fillMap(mappingScore, '3', 2);
	}

	/**
	 * This puts the key and value into the specified map.
	 * @return the new map.
	 */
	private final HashMap<Character, Integer> fillMap(HashMap<Character, Integer> map, Character key, Integer value) {
		map.put(key, value);

		return map;
	}

	/**
	 * This gets the mapping of the playing board.
	 * @return  the mapping of the playing board
	 */
	public final HashMap<Character,Integer> getMappingBoard() {
		return this.mappingBoard;
	}

	/**
	 * This gets the mapping of the score.
	 * @return  the mapping of the score
	 */
	public final HashMap<Character,Integer> getMappingScore() {
		return this.mappingScore;
	}

	/**
	 * This Method gets us the stringarray of the board.
	 * @return  the board
	 */
	public final String[] getBoard() {
		return this.board;
	}

	/**
	 * This method gets us the chararray of the score.
	 * @return  the score
	 */
	public final char[][] getScore() {
		return this.score;
	}

	/**
	 *
	 */
	public final void printBoard() {
		for (int i = 0; i < board.length; i++) {
			System.out.println(board[i]);
		}
	}

	/**
	 * This method starts the actual game.
	 * @param tttc         the instance of this class
	 * @throws IOException the exception is caught in the
	 *                     Communication.readPlayer method
	 */
	public final void startGame(final TTTConsole tttc) throws IOException {
		printBoard();
		Communication c = new Communication(tttc);
		c.readPlayer(1);
	}

	/**
	 * @param args         the usual arguments
	 * @throws IOException the exception is caught in the
	 *                     Communication.readPlayer method
	 */
	public static void main(final String[] args) throws IOException {
		TTTConsole tttc = new TTTConsole();
		tttc.startGame(tttc);
	}

}
