package cs5004.marblesolitaire.model.hw08;

/**
 * This class represents the TriangleSolitaireModelImpl class that extends the Abstract model.
 * This class will help the model maintain/get the state of the game
 * and allow a client to specify moves for the triangle solitaire model.
 */
public class TriangleSolitaireModelImpl extends AbstractModel {

  /**
   * Constructs a new board, that take no parameters,
   * and initialize the game board as a 5 row game empty slot at (0,0).
   * Added in Abstract model, thus passing in new Triangle board as parameter to super.
   */
  public TriangleSolitaireModelImpl() {
    super(new TriangleBoard());
  }

  /**
   * Constructs a new board, that take the dimension as its only parameter,
   * and initialize a game board with the empty slot at (0,0).
   * Added in Abstract model, thus passing in new Triangle board as parameter to super.
   * @param dimensions number of slots in the bottom-most row.
   * @throws IllegalArgumentException if the dimension is a non-positive number.
   */
  public TriangleSolitaireModelImpl(int dimensions) throws IllegalArgumentException {
    super(new TriangleBoard(dimensions));
  }

  /**
   * Constructs a new board, that take two parameters: emptyRow and emptyCol
   * and initialize the game board, so that the dimension is 5 and the empty slot is at the
   * position (emptyRow, emptyCol).
   * Added in Abstract model, thus passing in new Triangle board as parameter to super.
   * @param emptyRow The row of the initial empty slot.
   * @param emptyCol The column of the initial empty slot.
   * @throws IllegalArgumentException if the specific position is invalid.
   */
  public TriangleSolitaireModelImpl(int emptyRow, int emptyCol) throws IllegalArgumentException {
    super(new TriangleBoard(emptyRow, emptyCol));
  }

  /**
   * Constructs a new board, that take the arm thickness,
   * row and column of the empty slot in that order.
   * Added in Abstract model, thus passing in new Triangle board as parameter to super.
   * @param dimensions number of slots in the bottom-most row.
   * @param emptyRow The row of the initial empty slot.
   * @param emptyCol The column of the initial empty slot.
   * @throws IllegalArgumentException if dimension is a non-positive number,
   *          or the empty cell position is invalid.
   */
  public TriangleSolitaireModelImpl(int dimensions, int emptyRow, int emptyCol)
          throws IllegalArgumentException {
    super(new TriangleBoard(dimensions, emptyRow, emptyCol));
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
    if (!(rowDiff == 0 && colDiff == 2) && !(rowDiff == 2 && colDiff == 0)
            && !(rowDiff == 2 && colDiff == 2)) {
      throw new IllegalArgumentException("From & To locations aren't 2 spots away from each other");
    }

    // Get the middle marble position.
    int middleRow = getTriangleMiddleRow(fromRow, fromCol, toRow, toCol);
    int middleCol = getTriangleMiddleCol(fromRow, fromCol, toRow, toCol);

    // Make the move by setting correct pieces in the positions.
    makeMove(fromRow, fromCol, middleRow, middleCol, toRow, toCol);

    this.score = this.score - 1;

  }

  private int getTriangleMiddleRow(int fromRow, int fromCol, int toRow, int toCol) {
    if ((fromRow - toRow == 2) && (fromCol - toCol == 2)) {
      return fromRow - 1;
    } else if ((fromRow - toRow == -2) && (fromCol - toCol == -2)) {
      return fromRow + 1;
    } else if ((fromRow - toRow == 2) && (fromCol - toCol == -2)) {
      return fromRow - 1;
    } else if ((fromRow - toRow == -2) && (fromCol - toCol == 2)) {
      return fromRow + 1;
    } else if ((fromRow - toRow == 0) && (fromCol - toCol == -2)) {
      return fromRow;
    } else if ((fromRow - toRow == 0) && (fromCol - toCol == 2)) {
      return fromRow;
    } else if ((fromRow - toRow == 2) && (fromCol - toCol == 0)) {
      return fromRow - 1;
    } else {
      return fromRow + 1;
    }
  }

  protected int getTriangleMiddleCol(int fromRow, int fromCol, int toRow, int toCol) {
    if ((fromRow - toRow == 2) && (fromCol - toCol == 2)) {
      return fromCol - 1;
    } else if ((fromRow - toRow == -2) && (fromCol - toCol == -2)) {
      return fromCol + 1;
    } else if ((fromRow - toRow == 2) && (fromCol - toCol == -2)) {
      return fromCol + 1;
    } else if ((fromRow - toRow == -2) && (fromCol - toCol == 2)) {
      return fromCol - 1;
    } else if ((fromRow - toRow == 0) && (fromCol - toCol == -2)) {
      return fromCol + 1;
    } else if ((fromRow - toRow == 0) && (fromCol - toCol == 2)) {
      return fromCol - 1;
    } else if ((fromRow - toRow == 2) && (fromCol - toCol == 0)) {
      return fromCol;
    } else {
      return fromCol;
    }
  }

}
