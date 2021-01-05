package cs5004.marblesolitaire.model;

import cs5004.marblesolitaire.model.hw08.AbstractModel;

/**
 * This class represents the MarbleSolitaireModelImpl class that extends the Abstract model.
 * This class will help the model maintain/get the state of the game
 * and allow a client to specify moves for the marble solitaire model.
 * (Previous HW did not have abstract model class. Code refactored a bit to fit abstract class).
 */
public class MarbleSolitaireModelImpl extends AbstractModel {

  /**
   * Constructs a new board, that take no parameters,
   * and initialize the game board with arm thickness 3 with the empty slot at the center.
   * Added in Abstract model, thus changing to pass in new English board as parameter to super.
   */
  public MarbleSolitaireModelImpl() {
    super(new EnglishBoard());
  }

  /**
   * Constructs a new board, that take two parameters: sRow and sCol and initialize the game board,
   * so that the arm thickness is 3 and the empty slot is at the position (sRow, sCol).
   * Added in Abstract model, thus changing to pass in new English board as parameter to super.
   * @param sRow The row of the initial empty slot.
   * @param sCol The column of the initial empty slot.
   * @throws IllegalArgumentException if the specific position is invalid.
   */
  public MarbleSolitaireModelImpl(int sRow, int sCol) throws IllegalArgumentException {
    super(new EnglishBoard(sRow, sCol));
  }

  /**
   * Constructs a new board, that take the arm thickness as its only parameter,
   * and initialize a game board with the empty slot at the center.
   * Added in Abstract model, thus changing to pass in new English board as parameter to super.
   * @param armThickness the number of marbles in the top and bottom rows & left and right columns.
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number.
   */
  public MarbleSolitaireModelImpl(int armThickness) throws IllegalArgumentException {
    super(new EnglishBoard(armThickness));
  }

  /**
   * Constructs a new board, that take the arm thickness,
   * row and column of the empty slot in that order.
   * Added in Abstract model, thus changing to pass in new English board as parameter to super.
   * @param armThickness the number of marbles in the top and bottom rows & left and right columns.
   * @param emptyRow The row of the initial empty slot.
   * @param emptyCol The column of the initial empty slot.
   * @throws IllegalArgumentException if arm thickness is not a positive odd number,
   *          or the empty cell position is invalid.
   */
  public MarbleSolitaireModelImpl(int armThickness, int emptyRow, int emptyCol)
          throws IllegalArgumentException {
    super(new EnglishBoard(armThickness, emptyRow, emptyCol));
  }

}
