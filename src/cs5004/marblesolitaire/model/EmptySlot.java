package cs5004.marblesolitaire.model;

/**
 * This class represents the EmptySlot piece that extends the Abstract piece abstract class.
 */
public class EmptySlot extends AbstractPiece {

  @Override
  public boolean isEmpty() {
    return true;
  }

  /**
   * Creates a string that represents the marble piece on the board.
   * @return a string that represents the marble piece on the board.
   */
  @Override
  public String toString() {
    return "_";
  }

}
