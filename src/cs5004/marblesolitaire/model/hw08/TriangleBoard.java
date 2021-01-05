package cs5004.marblesolitaire.model.hw08;

import cs5004.marblesolitaire.model.EmptySlot;
import cs5004.marblesolitaire.model.IPiece;
import cs5004.marblesolitaire.model.InvalidPiece;
import cs5004.marblesolitaire.model.MarblePiece;

/**
 * This class represents the Triangle Board that extends the Abstract Board.
 * THis contains all the operations unique to the Triangle board.
 */
public class TriangleBoard extends AbstractBoard {

  /**
   * Constructs a new board, that take no parameters,
   * and initialize the game board with dimension of 5 with the empty slot at (0,0).
   * Passes in dimension to the super, which will use the constructor in Abstract Board class.
   * Also initializes the starting score of the game, by calling scoreSetter method.
   */
  public TriangleBoard() {
    super(5, true);
    board[0][0] = new EmptySlot();
    scoreSetter(5);
  }

  /**
   * Constructs a new board, that take two parameters: emptyRow and emptyCol
   * and initialize the game board, so that the dimension is 5 and the empty slot is at the
   * position (emptyRow, emptyCol).
   * Passes in dimension to the super, which will use the constructor in Abstract Board class.
   * Also initializes the score of the game or the number of pieces on the board.
   * @param emptyRow The row of the initial empty slot.
   * @param emptyCol The column of the initial empty slot.
   * @throws IllegalArgumentException if the specific position is invalid.
   */
  public TriangleBoard(int emptyRow, int emptyCol) throws IllegalArgumentException {
    super(5, true);
    setGivenEmpty(emptyRow, emptyCol);
    scoreSetter(5);
  }

  /**
   * Constructs a new board, that take the dimension as its only parameter,
   * and initialize a game board with the empty slot at (0,0).
   * Passes in dimension to the super, which will use the constructor in Abstract Board class.
   * Also initializes the score of the game, or the number of pieces on the board.
   * @param dimensions number of slots in the bottom-most row.
   * @throws IllegalArgumentException if the dimension is a non-positive number.
   */
  public TriangleBoard(int dimensions) throws IllegalArgumentException {
    super(dimensions, true);
    board[0][0] = new EmptySlot();
    scoreSetter(dimensions);
  }

  /**
   * Constructs a new board, that take the arm thickness,
   * row and column of the empty slot in that order.
   * Passes in dimension to the super, which will use the constructor in Abstract Board class.
   * Also initializes the score of the game, or the number of pieces on the board.
   * @param dimensions number of slots in the bottom-most row.
   * @param emptyRow The row of the initial empty slot.
   * @param emptyCol The column of the initial empty slot.
   * @throws IllegalArgumentException if dimension is a non-positive number,
   *          or the empty cell position is invalid.
   */
  public TriangleBoard(int dimensions, int emptyRow, int emptyCol) throws IllegalArgumentException {
    super(dimensions, true);
    setGivenEmpty(emptyRow, emptyCol);
    scoreSetter(dimensions);
  }

  /**
   * This abstract helper method makes the specific english board requested by constructor.
   */
  protected void makeBoard(int size) {
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (j < (i + 1)) {
          board[i][j] = new MarblePiece();
        } else {
          board[i][j] = new InvalidPiece();
        }
      }
    }
  }

  /**
   * This helper method gets the initial starting score based on the dimension.
   * It initializes the variable to the score.
   */
  private void scoreSetter(int size) {
    int total = 0;
    for (int i = 0; i <= size; i++) {
      total += i;
    }
    this.initialScore = total - 1;
  }

  @Override
  public boolean gameOverChecker() {
    return (!horizontalRMoveChecker() && !horizontalLMoveChecker()
            && !verticalUpMoveChecker() && !verticalDownMoveChecker()
            && !diagonalSEMoveChecker() && !diagonalSWMoveChecker()
            && !diagonalNEMoveChecker() && !diagonalNWMoveChecker());
  }

  /**
   * This method checks to see if there are any moves that can be made diagonally southeast.
   * @return a true or false of if there are any more moves that can be made.
   */
  private boolean diagonalSEMoveChecker() {

    // Return true if there is another opportunity to make a move.
    for (int i = 0; i < board.length - 2; i++) {
      for (int j = 0; j < board.length - 2; j++) {
        if (board[i][j].isMarble()
                && board[i + 1][j + 1].isMarble()
                && board[i + 2][j + 2].isEmpty()) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * This method checks to see if there are any moves that can be made diagonally southwest.
   * @return a true or false of if there are any more moves that can be made.
   */
  private boolean diagonalSWMoveChecker() {

    // Return true if there is another opportunity to make a move.
    for (int i = 0; i < board.length - 2; i++) {
      for (int j = 2; j < board.length; j++) {
        if (board[i][j].isMarble()
                && board[i + 1][j - 1].isMarble()
                && board[i + 2][j - 2].isEmpty()) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * This method checks to see if there are any moves that can be made diagonally northeast.
   * @return a true or false of if there are any more moves that can be made.
   */
  private boolean diagonalNEMoveChecker() {

    // Return true if there is another opportunity to make a move.
    for (int i = 2; i < board.length; i++) {
      for (int j = 0; j < board.length - 2; j++) {
        if (board[i][j].isMarble()
                && board[i - 1][j + 1].isMarble()
                && board[i - 2][j + 2].isEmpty()) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * This method checks to see if there are any moves that can be made diagonally northwest.
   * @return a true or false of if there are any more moves that can be made.
   */
  private boolean diagonalNWMoveChecker() {

    // Return true if there is another opportunity to make a move.
    for (int i = 2; i < board.length; i++) {
      for (int j = 2; j < board.length; j++) {
        if (board[i][j].isMarble()
                && board[i - 1][j - 1].isMarble()
                && board[i - 2][j - 2].isEmpty()) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * This method creates/prints a string of the triangle board.
   * @return the game state of the board.
   */
  @Override
  public String toString() {

    int spaces = board.length - 1;
    int k = 0;
    String boardString = "";
    for (IPiece[] iPieces : board) {
      for (k = 0; k < spaces; k++) {
        boardString += " ";
      }
      for (int j = 0; j < board.length; j++) {
        if (j == 0) {
          boardString += iPieces[j];
        } else if (iPieces[j].isInvalid()) {
          if (j == board.length - 1) {
            boardString += "\n";
          } else {
            boardString += "";
          }
        } else {
          boardString += " ";
          boardString += iPieces[j];
        }
      }
      k = 0;
      spaces--;
    }
    return boardString;
  }
}
