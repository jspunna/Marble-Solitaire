package cs5004.marblesolitaire.model;

/**
 * This class represents the abstract piece that implements the IPiece interface.
 * Sets all operations from the interface to false, as the default state.
 */
public class AbstractPiece implements IPiece {

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public boolean isMarble() {
    return false;
  }

  @Override
  public boolean isInvalid() {
    return false;
  }


}
