package cs5004.marblesolitaire.model;

/**
 * This interface represents the operations offered by the marble solitaire board.
 */
public interface IBoard {

  /**
   * This method gets the initial score, pieces, on the board at the start of the game.
   * The score gets called in the MarbleSolitaireModel Impl at the start in each constructor.
   * @return an int of the initial score based on board constructor used.
   */
  int getInitialScore();

  /**
   * This method gets the piece(Empty, Marble, Invalid) at the passed in parameter position.
   * @param row The row of the requested position.
   * @param col The column of the requested position.
   * @return The IPiece(Empty, Marble, Invalid) at the passed in row and column.
   */
  IPiece getPiece(int row, int col);

  /**
   * This method sets a piece(Empty, Marble, Invalid) at the passed in parameter position.
   * @param row The row of where to set the IPiece.
   * @param col The column of where to set the IPiece.
   * @param piece The IPiece to set at passed in row and column parameters.
   */
  void setPiece(int row, int col, IPiece piece);

  /**
   * This method gets the Length of the board.
   * @return the Length of the board.
   */
  int getLength();

  /**
   * This method checks to see if the game is over.
   * It is called in the MarbleSolitaireModel Impl, isGameOver method.
   * @return a true or false of the game is over after checking of any other moves can be made.
   */
  boolean gameOverChecker();

}
