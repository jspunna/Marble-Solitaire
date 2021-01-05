package cs5004.marblesolitaire.model;

/**
 * This class represents the Invalid piece that extends the Abstract piece abstract class.
 */
public class InvalidPiece extends AbstractPiece {

  @Override
  public boolean isInvalid() {
    return true;
  }

  /**
   * Creates a string that represents the marble piece on the board.
   * @return a string that represents the marble piece on the board.
   */
  @Override
  public String toString() {
    return " ";
  }

}
