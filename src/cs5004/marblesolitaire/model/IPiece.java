package cs5004.marblesolitaire.model;

/**
 * This interface represents the operations offered by the marble solitaire pieces.
 */
public interface IPiece {

  /**
   * The method checks to see if the IPiece is empty.
   * @return a true or false of if the IPiece is empty.
   */
  boolean isEmpty();

  /**
   * The method checks to see if the IPiece is a marble.
   * @return a true or false of if the IPiece is a marble.
   */
  boolean isMarble();

  /**
   * The method checks to see if the IPiece is invalid.
   * @return a true or false of if the IPiece is invalid.
   */
  boolean isInvalid();

}
