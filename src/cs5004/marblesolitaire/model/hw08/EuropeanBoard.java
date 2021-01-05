package cs5004.marblesolitaire.model.hw08;

import cs5004.marblesolitaire.model.EmptySlot;
import cs5004.marblesolitaire.model.InvalidPiece;
import cs5004.marblesolitaire.model.MarblePiece;

/**
 * This class represents the European Board that extends the Abstract Board.
 * THis contains all the operations unique to the European board.
 */
public class EuropeanBoard extends AbstractBoard {

  /**
   * Constructs a new board, that take no parameters,
   * and initialize the game board with side length 3 with the empty slot at the center.
   * Passes in side length to the super, which will use the constructor in Abstract Board class.
   * Also initializes the starting score of the game, by calling scoreSetter method.
   */
  public EuropeanBoard() {
    super(7, true);
    board[3][3] = new EmptySlot();
    scoreSetter(3,7);
  }

  /**
   * Constructs a new board, that take two parameters: sRow and sCol and initialize the game board,
   * so that the side length is 3 and the empty slot is at the position (sRow, sCol).
   * Passes in side length to the super, which will use the constructor in Abstract Board class.
   * Also initializes the starting score of the game, by calling scoreSetter method.
   * @param sRow The row of the initial empty slot.
   * @param sCol The column of the initial empty slot.
   * @throws IllegalArgumentException if the specific position is invalid.
   */
  public EuropeanBoard(int sRow, int sCol) throws IllegalArgumentException {
    super(7, true);
    setGivenEmpty(sRow, sCol);
    scoreSetter(3,7);
  }

  /**
   * Constructs a new board, that take the side length as its only parameter,
   * and initialize a game board with the empty slot at the center.
   * Passes in side length to the super, which will use the constructor in Abstract Board class.
   * Also initializes the starting score of the game, by calling scoreSetter method.
   * @param sideLength The length each side of the board.
   * @throws IllegalArgumentException if the side length is not a positive odd number.
   */
  public EuropeanBoard(int sideLength) throws IllegalArgumentException {
    super(sideLength);

    int size = (sideLength * 3) - 2;
    int emptyCoordinate = (size - 1) / 2;
    board[emptyCoordinate][emptyCoordinate] = new EmptySlot();
    scoreSetter(sideLength, size);
  }

  /**
   * Constructs a new board, that take the side length,
   * row and column of the empty slot in that order.
   * Passes in side length to the super, which will use the constructor in Abstract Board class.
   * Also initializes the starting score of the game, by calling scoreSetter method.
   * @param sideLength The length each side of the board.
   * @param emptyRow The row of the initial empty slot.
   * @param emptyCol The column of the initial empty slot.
   * @throws IllegalArgumentException if arm thickness is not a positive odd number,
   *           or the empty cell position is invalid.
   */
  public EuropeanBoard(int sideLength, int emptyRow, int emptyCol)
          throws IllegalArgumentException {
    super(sideLength);

    int size = (sideLength * 3) - 2;
    setGivenEmpty(emptyRow, emptyCol);
    scoreSetter(sideLength, size);
  }

  /**
   * This abstract method makes the specific european board requested by constructor.
   */
  protected void makeBoard(int size) {
    int boardIndexSize = size - 1;
    int lowerLimit = boardIndexSize / 3;
    int upperLimit = boardIndexSize - (boardIndexSize / 3);

    // Calls other private helper methods to make the different parts of the board.
    makeTopRows(lowerLimit, upperLimit);
    makeMiddleRows(lowerLimit, upperLimit);
    makeBottomRows(upperLimit);
  }

  /**
   * This helper method makes the top rows of the board.
   */
  private void makeTopRows(int lower, int upper) {
    int lowerLimit = lower;
    int upperLimit = upper;
    for (int i = 0; i < lower; i++) {
      for (int j = 0; j < board.length; j++) {
        if (j >= lowerLimit && j <= upperLimit) {
          board[i][j] = new MarblePiece();
        } else {
          board[i][j] = new InvalidPiece();
        }
      }
      lowerLimit--;
      upperLimit++;
    }
  }

  /**
   * This helper method makes the middle rows of the board.
   */
  private void makeMiddleRows(int lower, int upper) {
    for (int i = lower; i < upper; i++) {
      for (int j = 0; j < board.length; j++) {
        board[i][j] = new MarblePiece();
      }
    }
  }

  /**
   * This helper method makes the bottom rows of the board.
   */
  private void makeBottomRows(int upper) {
    int jStarting = 0;
    int shrinkingLength = board.length;
    for (int i = upper; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        if (j >= jStarting && j < shrinkingLength) {
          board[i][j] = new MarblePiece();
        } else {
          board[i][j] = new InvalidPiece();
        }
      }
      jStarting++;
      shrinkingLength--;
    }
  }

  /**
   * This helper method gets the initial starting score based on the armThickness and board size.
   * It initializes the variable to the score.
   */
  private void scoreSetter(int sideLength, int size) {
    int middleRows = sideLength * size;
    int topBlock = sideLength * (sideLength - 1);
    int bottomBlock = sideLength * (sideLength - 1);
    int quadrant = 0;
    for (int i = 0; i < sideLength - 1; i++) {
      quadrant += i;
    }
    this.initialScore = middleRows + topBlock + bottomBlock + (quadrant * 4) - 1;
  }

}
