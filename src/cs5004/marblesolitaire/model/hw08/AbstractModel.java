package cs5004.marblesolitaire.model.hw08;

import cs5004.marblesolitaire.model.EmptySlot;
import cs5004.marblesolitaire.model.IBoard;
import cs5004.marblesolitaire.model.MarblePiece;
import cs5004.marblesolitaire.model.MarbleSolitaireModel;

/**
 * This class is the abstract model that implements the MarbleSolitaireModel Interface.
 * It contains operations common among classes that extend this class.
 * This class is extended by the MarbleSolitaireModelImpl, the EuropeanSolitaireModelImpl,
 * and the TriangleSolitaireModelImpl.
 * (No new public methods added to MarbleSolitaireModel interface when making abstract class).
 */
public abstract class AbstractModel implements MarbleSolitaireModel {

  protected IBoard board;
  protected int score;

  /**
   * Constructs a new board, based on the type of IBoard that is passed in.
   * Also initializes the score of the game, or the number of pieces on the board.
   */
  public AbstractModel(IBoard boardType) {
    this.board = boardType;
    this.score = board.getInitialScore();
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {

    // Check if locations are outside board size.
    if (checkOutOfBounds(fromRow, fromCol, toRow, toCol)) {
      throw new IllegalArgumentException("Can't have location outside the board limits");
    }

    // Check if locations, from and to, are invalid in the board.
    if (board.getPiece(fromRow, fromCol).isInvalid() || board.getPiece(toRow, toCol).isInvalid()) {
      throw new IllegalArgumentException("Can't move from or to invalid position");
    }

    // Check if the move from and to locations are two spots away for horizontal and vertical moves.
    int rowDiff = Math.abs(fromRow - toRow);
    int colDiff = Math.abs(fromCol - toCol);
    if (!(rowDiff == 0 && colDiff == 2) && !(rowDiff == 2 && colDiff == 0)) {
      throw new IllegalArgumentException("From & To locations aren't 2 spots away from each other");
    }

    // Get the middle marble position.
    int middleRow = getMiddleRow(fromRow, fromCol, toRow, toCol);
    int middleCol = getMiddleCol(fromRow, fromCol, toRow, toCol);

    // Make the move by setting correct pieces in the positions.
    makeMove(fromRow, fromCol, middleRow, middleCol, toRow, toCol);

    this.score = this.score - 1;

  }

  /**
   * Helper method that gets the row of the middle marble between the from and to.
   * @param fromRow the row of the marble.
   * @param fromCol the col of the marble.
   * @param toRow the destination row for the marble.
   * @param toCol the destination col for the marble.
   * @return the row of the middle marble.
   */
  protected int getMiddleRow(int fromRow, int fromCol, int toRow, int toCol) {
    if ((fromRow - toRow == 0) && (fromCol - toCol == -2)) {
      return fromRow;
    } else if ((fromRow - toRow == 0) && (fromCol - toCol == 2)) {
      return fromRow;
    } else if ((fromRow - toRow == 2) && (fromCol - toCol == 0)) {
      return fromRow - 1;
    } else {
      return fromRow + 1;
    }
  }

  /**
   * Helper method that gets the column of the middle marble between the from and to.
   * @param fromRow the row of the marble.
   * @param fromCol the col of the marble.
   * @param toRow the destination row for the marble.
   * @param toCol the destination col for the marble.
   * @return the column of the middle marble.
   */
  protected int getMiddleCol(int fromRow, int fromCol, int toRow, int toCol) {
    if ((fromRow - toRow == 0) && (fromCol - toCol == -2)) {
      return fromCol + 1;
    } else if ((fromRow - toRow == 0) && (fromCol - toCol == 2)) {
      return fromCol - 1;
    } else if ((fromRow - toRow == 2) && (fromCol - toCol == 0)) {
      return fromCol;
    } else {
      return fromCol;
    }
  }

  /**
   * This helper method, checks to see if the parameters passed into the move method,
   * are within the size boundaries of the board.
   */
  protected boolean checkOutOfBounds(int fromRow, int fromCol, int toRow, int toCol) {
    if ((fromCol < 0) || (fromRow < 0) || (toRow < 0) || (toCol < 0)) {
      return true;
    }
    return ((fromCol >= board.getLength()) || (fromRow >= board.getLength())
            || (toRow >= board.getLength()) || (toCol >= board.getLength()));
  }

  /**
   * This helper makes the marble move. Makes sure positions are correct pieces,
   * then sets the board with correct pieces at destination(toRow, toCol) positions.
   * @param fromRow the row in which the move is made from.
   * @param fromCol the col in which the move is made from.
   * @param middleRow the middle row in between from and to.
   * @param middleCol the middle col in between from and to.
   * @param toRow the destination row of the move.
   * @param toCol the destination col of the move.
   * @throws IllegalArgumentException if unable to move to that position.
   */
  protected void makeMove(int fromRow, int fromCol, int middleRow,
                          int middleCol, int toRow, int toCol) throws IllegalArgumentException {
    if (board.getPiece(fromRow, fromCol).isMarble()
            && board.getPiece(middleRow, middleCol).isMarble()
            && board.getPiece(toRow, toCol).isEmpty()) {

      board.setPiece(toRow, toCol, new MarblePiece());
      board.setPiece(middleRow, middleCol, new EmptySlot());
      board.setPiece(fromRow, fromCol, new EmptySlot());
    } else {
      throw new IllegalArgumentException("Can't move there!");
    }
  }

  @Override
  public boolean isGameOver() {
    if (this.score == 1) {
      return true;
    } else if (board.gameOverChecker()) {
      return true;
    }
    return false;
  }

  @Override
  public String getGameState() {
    return board.toString();
  }

  @Override
  public int getScore() {
    return this.score;
  }
}
