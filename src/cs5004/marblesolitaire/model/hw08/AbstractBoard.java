package cs5004.marblesolitaire.model.hw08;

import cs5004.marblesolitaire.model.EmptySlot;
import cs5004.marblesolitaire.model.IBoard;
import cs5004.marblesolitaire.model.IPiece;

/**
 * This class represents the abstract board that implements the IBoard interface.
 * It contains operations that are common among the EnglishBoard, the EuropeanBoard, and the
 * TriangleBoard classes.
 * (No new public methods added to IBoard interface when making abstract class).
 */
public abstract class AbstractBoard implements IBoard {

  protected IPiece[][] board;
  protected int initialScore;

  /**
   * Constructs a new board. It takes in the size/width of the board to make.
   * It also takes in a boolean used as a dummy due to second abstract constructor also taking in
   * an single int as its parameter.
   * THis constructor does not check to see if size/arm is odd or not, thus allowing a more flexible
   * constructor to be used, for certain situations.
   * Calls the makeBoard function to create board with correct IPieces at board positions.
   * @param size the size/width of the board to make.
   * @param dummy dummy parameter due to second abstract constructor also taking in a single int.
   * @throws IllegalArgumentException if size is equal or below zero.
   */
  public AbstractBoard(int size, boolean dummy) throws IllegalArgumentException {
    if (size <= 0) {
      throw new IllegalArgumentException("Dimensions can't be a non-positive number.");
    }
    this.board = new IPiece[size][size];
    makeBoard(size);
  }

  /**
   * Constructs a new board. It takes in the size/width of the board to make.
   * This constructor is mainly "supered" by the arm thickness constructors of the
   * European/English boards. This is different from other abstract constructor since this one
   * calculates the size need based on the arm. As well as check if arm is odd.
   * Calls the makeBoard function to create board with correct IPieces at board positions.
   * @param armThickness the number of marbles in the top and bottom rows & left and right columns.
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number.
   */
  public AbstractBoard(int armThickness) throws IllegalArgumentException {

    if ((armThickness < 1) || (armThickness % 2 == 0)) {
      throw new IllegalArgumentException("Arm thickness can't be negative or an even number");
    }
    int size = (armThickness * 3) - 2;
    this.board = new IPiece[size][size];
    makeBoard(size);
  }

  /**
   * This is an abstract method that makes the board. When called it will go to the
   * appropriate boards makeBoard method to make specific board.
   * Method created as a result of makeBoard method called in the abstract constructors.
   * @param size the size/width of the board to make.
   */
  protected abstract void makeBoard(int size);

  @Override
  public int getInitialScore() {
    return this.initialScore;
  }

  @Override
  public IPiece getPiece(int row, int col) {
    return board[row][col];
  }

  @Override
  public void setPiece(int row, int col, IPiece piece) {
    board[row][col] = piece;
  }

  @Override
  public int getLength() {
    return board.length;
  }

  @Override
  public boolean gameOverChecker() {
    return (!horizontalRMoveChecker() && !horizontalLMoveChecker()
            && !verticalUpMoveChecker() && !verticalDownMoveChecker());
  }

  /**
   * This method sets the requested empty row and empty column parameters as the empty slot IPiece.
   * @param emptyRow the requested row of the empty slot.
   * @param emptyCol the requested column of the empty slot.
   * @throws IllegalArgumentException if the requested empty slot is invalid or outside the board.
   */
  protected void setGivenEmpty(int emptyRow, int emptyCol) throws  IllegalArgumentException {

    if (emptyRow >= board.length || emptyCol >= board.length) {
      throw new IllegalArgumentException("Empty slot row or column out of board limits.");
    }

    if (board[emptyRow][emptyCol].isInvalid()) {
      throw new IllegalArgumentException("Invalid empty cell position ("
              + emptyRow + ", " + emptyCol + ")");
    } else {
      board[emptyRow][emptyCol] = new EmptySlot();
    }
  }

  /**
   * This method checks to see if there are any moves that can be made horizontally to the right.
   * @return a true or false of if there are any more moves that can be made.
   */
  protected boolean horizontalRMoveChecker() {

    // Return true if there is another opportunity to make a move.
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length - 2; j++) {
        if (board[i][j].isMarble()
                && board[i][j + 2].isEmpty()
                && board[i][j + 1].isMarble()) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * This method checks to see if there are any moves that can be made horizontally to the left.
   * @return a true or false of if there are any more moves that can be made.
   */
  protected boolean horizontalLMoveChecker() {

    // Return true if there is another opportunity to make a move.
    for (int i = 0; i < board.length; i++) {
      for (int j = 2; j < board.length; j++) {
        if (board[i][j].isMarble()
                && board[i][j - 2].isEmpty()
                && board[i][j - 1].isMarble()) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * This method checks to see if there are any moves that can be made vertically upwards.
   * @return a true or false of if there are any more moves that can be made.
   */
  protected boolean verticalUpMoveChecker() {

    // Return true if there is another opportunity to make a move.
    for (int i = 2; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        if (board[i][j].isMarble()
                && board[i - 2][j].isEmpty()
                && board[i - 1][j].isMarble()) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * This method checks to see if there are any moves that can be made vertically downwards.
   * @return a true or false of if there are any more moves that can be made.
   */
  protected boolean verticalDownMoveChecker() {

    // Return true if there is another opportunity to make a move.
    for (int i = 0; i < board.length - 2; i++) {
      for (int j = 0; j < board.length; j++) {
        if (board[i][j].isMarble()
                && board[i + 2][j].isEmpty()
                && board[i + 1][j].isMarble()) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * This method creates/prints a string of the board.
   * @return the game state of the board.
   */
  @Override
  public String toString() {
    int boardIndexSize = board.length - 1;
    int upperLimit = boardIndexSize - (boardIndexSize / 3);

    String boardString = "";
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        if (j == 0) {
          boardString += board[i][j];
        } else if (i == board.length - 1 && j == board.length - 1) {
          boardString += "";
        } else if (board[i][j].isInvalid() && j >= upperLimit) {
          if (j == board.length - 1) {
            boardString += "\n";
          }
        } else if (j == board.length - 1) {
          boardString += " ";
          boardString += board[i][j];
          boardString += "\n";
        } else {
          boardString += " ";
          boardString += board[i][j];
        }
      }
    }
    return boardString;
  }

}
