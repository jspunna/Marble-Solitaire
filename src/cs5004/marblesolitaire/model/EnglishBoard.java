package cs5004.marblesolitaire.model;

import cs5004.marblesolitaire.model.hw08.AbstractBoard;

/**
 * This class represents the English Board that extends the Abstract Board.
 * THis contains all the operations unique to the English board.
 * (Previous HW did not have abstract board class. Code refactored a bit to fit abstract class).
 */
public class EnglishBoard extends AbstractBoard {

  /**
   * Constructs a new board, that take no parameters,
   * and initialize the game board with arm thickness 3 (size 7) with the empty slot at the center.
   * Passes in size to the super, which will use the constructor in Abstract Board class.
   * Also initializes the starting score of the game, by calling scoreSetter method.
   */
  public EnglishBoard() {
    super(7, true);
    board[3][3] = new EmptySlot();
    scoreSetter(3,7);
  }

  /**
   * Constructs a new board, that take two parameters: sRow and sCol and initialize the game board,
   * so that the arm thickness is 3 (size 7) and the empty slot is at the position (sRow, sCol).
   * Passes in size to the super, which will use the constructor in Abstract Board class.
   * Also initializes the starting score of the game, by calling scoreSetter method.
   * @param sRow The row of the initial empty slot.
   * @param sCol The column of the initial empty slot.
   * @throws IllegalArgumentException if the specific position is invalid.
   */
  public EnglishBoard(int sRow, int sCol) throws IllegalArgumentException {
    super(7, true);
    setGivenEmpty(sRow, sCol);
    scoreSetter(3,7);
  }

  /**
   * Constructs a new board, that take the arm thickness as its only parameter,
   * and initialize a game board with the empty slot at the center.
   * Passes in size to the super, which will use the constructor in Abstract Board class.
   * Also initializes the starting score of the game, by calling scoreSetter method.
   * @param armThickness the number of marbles in the top and bottom rows & left and right columns.
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number.
   */
  public EnglishBoard(int armThickness) throws IllegalArgumentException {
    super(armThickness);

    int size = (armThickness * 3) - 2;
    int emptyCoordinate = (size - 1) / 2;
    board[emptyCoordinate][emptyCoordinate] = new EmptySlot();
    scoreSetter(armThickness, size);
  }

  /**
   * Constructs a new board, that take the arm thickness,
   * row and column of the empty slot in that order.
   * Passes in size to the super, which will use the constructor in Abstract Board class.
   * Also initializes the starting score of the game, by calling scoreSetter method.
   * @param armThickness the number of marbles in the top and bottom rows & left and right columns.
   * @param emptyRow The row of the initial empty slot.
   * @param emptyCol The column of the initial empty slot.
   * @throws IllegalArgumentException if arm thickness is not a positive odd number,
   *          or the empty cell position is invalid.
   */
  public EnglishBoard(int armThickness, int emptyRow, int emptyCol)
          throws IllegalArgumentException {
    super(armThickness);

    int size = (armThickness * 3) - 2;
    setGivenEmpty(emptyRow, emptyCol);
    scoreSetter(armThickness, size);
  }

  /**
   * This abstract method makes the specific english board requested by constructor.
   */
  protected void makeBoard(int size) {
    int boardIndexSize = size - 1;
    int lowerLimit = boardIndexSize / 3;
    int upperLimit = boardIndexSize - (boardIndexSize / 3);

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        if ((i < lowerLimit && j < lowerLimit) || (i < lowerLimit && j > upperLimit)
                || (i > upperLimit && j < lowerLimit) || (i > upperLimit && j > upperLimit)) {
          board[i][j] = new InvalidPiece();
        } else {
          board[i][j] = new MarblePiece();
        }
      }
    }
  }

  /**
   * This helper method gets the initial starting score based on the armThickness and board size.
   * It initializes the variable to the score.
   */
  private void scoreSetter(int armThickness, int size) {
    int middleRows = armThickness * size;
    int topBlock = armThickness * (armThickness - 1);
    int bottomBlock = armThickness * (armThickness - 1);
    this.initialScore = middleRows + topBlock + bottomBlock - 1;
  }

}
