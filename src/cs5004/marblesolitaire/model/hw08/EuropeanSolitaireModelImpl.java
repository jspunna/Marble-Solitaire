package cs5004.marblesolitaire.model.hw08;

/**
 * This class represents the EuropeanSolitaireModelImpl class that extends the Abstract model.
 * This class will help the model maintain/get the state of the game
 * and allow a client to specify moves for the european solitaire model.
 */
public class EuropeanSolitaireModelImpl extends AbstractModel {

  /**
   * Constructs a new board, that take no parameters,
   * and initialize the game board with arm thickness 3 with the empty slot at the center.
   * Added in Abstract model, thus passing in new Triangle board as parameter to super.
   */
  public EuropeanSolitaireModelImpl() {
    super(new EuropeanBoard());
  }

  /**
   * Constructs a new board, that take two parameters: hRow and hCol and initialize the game board,
   * so that the arm thickness is 3 and the empty slot is at the position (sRow, sCol).
   * Added in Abstract model, thus passing in new Triangle board as parameter to super.
   * @param hRow The row of the initial empty slot.
   * @param hCol The column of the initial empty slot.
   * @throws IllegalArgumentException if the specific position is invalid.
   */
  public EuropeanSolitaireModelImpl(int hRow, int hCol) throws IllegalArgumentException {
    super(new EuropeanBoard(hRow, hCol));
  }


  /**
   * Constructs a new board, that take the side length as its only parameter,
   * and initialize a game board with the empty slot at the center.
   * Added in Abstract model, thus passing in new Triangle board as parameter to super.
   * @param sideLength The length each side of the board.
   * @throws IllegalArgumentException if the side length is not a positive odd number.
   */
  public EuropeanSolitaireModelImpl(int sideLength) throws IllegalArgumentException {
    super(new EuropeanBoard(sideLength));
  }

  /**
   * Constructs a new board, that take the side length,
   * row and column of the empty slot in that order.
   * Added in Abstract model, thus passing in new Triangle board as parameter to super.
   * @param sideLength The length each side of the board.
   * @param emptyRow The row of the initial empty slot.
   * @param emptyCol The column of the initial empty slot.
   * @throws IllegalArgumentException if arm thickness is not a positive odd number,
   *           or the empty cell position is invalid.
   */
  public EuropeanSolitaireModelImpl(int sideLength, int emptyRow, int emptyCol)
          throws IllegalArgumentException {
    super(new EuropeanBoard(sideLength, emptyRow, emptyCol));
  }

}
