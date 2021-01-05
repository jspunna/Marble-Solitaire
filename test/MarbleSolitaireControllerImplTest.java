import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

import cs5004.marblesolitaire.controller.AppendableTester;
import cs5004.marblesolitaire.controller.MarbleSolitaireController;
import cs5004.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs5004.marblesolitaire.controller.MockModel;
import cs5004.marblesolitaire.model.MarbleSolitaireModel;
import cs5004.marblesolitaire.model.MarbleSolitaireModelImpl;

import static org.junit.Assert.assertEquals;

/**
 * Creating JUnit tests to test MarbleSolitaireControllerImpl class.
 * Testing exceptions and various inputs/outputs that may happen when using controller.
 */
public class MarbleSolitaireControllerImplTest {

  MarbleSolitaireController controller;
  MarbleSolitaireModel model;

  /**
   * Set up model to use in testing controller.
   */
  @Before
  public void setUp() {
    model = new MarbleSolitaireModelImpl();
  }

  /**
   * Checking if IllegalArgument is thrown when null readable is passed into constructor.
   */
  @Test (expected = IllegalArgumentException.class)
  public void constructor1() {
    Appendable ap = new StringBuilder();
    controller = new MarbleSolitaireControllerImpl(null, ap);
    controller.playGame(model);
  }

  /**
   * Checking if IllegalArgument is thrown when null appendable is passed into constructor.
   */
  @Test (expected = IllegalArgumentException.class)
  public void constructor2() {
    controller = new MarbleSolitaireControllerImpl(new StringReader("4 2 4 4"), null);
    controller.playGame(model);
  }

  /**
   * Testing IllegalArgument when null model passed into playGame.
   */
  @Test (expected = IllegalArgumentException.class)
  public void playGameModel() {
    Appendable ap = new StringBuilder();
    controller = new MarbleSolitaireControllerImpl(new StringReader("4 2 4 4"), ap);
    controller.playGame(null);
  }

  /**
   * Testing Quit, lower case, by itself.
   */
  @Test
  public void quit1() {
    Appendable ap = new StringBuilder();
    controller = new MarbleSolitaireControllerImpl(new StringReader("q"), ap);
    controller.playGame(model);
    String expected = "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O" + "\n"
            + "    O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "O O O _ O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "    O O O" + "\n"
            + "    O O O"
            + "\nScore: 32" + "\n";
    assertEquals(expected, ap.toString().substring(ap.toString().length() - expected.length()));
  }

  /**
   * Testing Quit, upper case, by itself.
   */
  @Test
  public void quit2() {
    Appendable ap = new StringBuilder();
    controller = new MarbleSolitaireControllerImpl(new StringReader("Q\n"), ap);
    controller.playGame(model);
    String expected = "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O" + "\n"
            + "    O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "O O O _ O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "    O O O" + "\n"
            + "    O O O"
            + "\nScore: 32" + "\n";
    assertEquals(expected, ap.toString().substring(ap.toString().length() - expected.length()));
  }

  /**
   * Testing Quit, lower case, after only 3 numbers inputted for move.
   */
  @Test
  public void quit3() {
    Appendable ap = new StringBuilder();
    controller = new MarbleSolitaireControllerImpl(new StringReader("4\n2\n4\nq\n"), ap);
    controller.playGame(model);
    String expected = "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O" + "\n"
            + "    O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "O O O _ O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "    O O O" + "\n"
            + "    O O O"
            + "\nScore: 32" + "\n";
    assertEquals(expected, ap.toString().substring(ap.toString().length() - expected.length()));
  }

  /**
   * Testing Quit, upper case, after a valid 4 or more numbers for move.
   */
  @Test
  public void quit4() {
    Appendable ap = new StringBuilder();
    controller = new MarbleSolitaireControllerImpl(new StringReader("4 2 4 4 2 Q"), ap);
    controller.playGame(model);
    String expected = "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O" + "\n"
            + "    O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "O _ _ O O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "    O O O" + "\n"
            + "    O O O"
            + "\nScore: 31" + "\n";
    assertEquals(expected, ap.toString().substring(ap.toString().length() - expected.length()));
  }

  /**
   * Testing correct output/message after game is over.
   */
  @Test
  public void gameOver() {
    Appendable ap = new StringBuilder();
    Reader rd = new StringReader("2 4 4 4 "
            + "3 6 3 4 "
            + "1 5 3 5 "
            + "4 5 2 5 "
            + "6 5 4 5 "
            + "5 7 5 5 "
            + "5 4 5 6 "
            + "3 7 5 7 "
            + "5 7 5 5 "
            + "3 3 3 5 "
            + "3 1 3 3 "
            + "5 2 5 4 "
            + "5 4 5 6 "
            + "5 6 3 6 "
            + "3 6 3 4 "
            + "3 4 3 2 "
            + "7 3 5 3 "
            + "4 3 6 3 "
            + "7 5 7 3 "
            + "7 3 5 3 "
            + "5 1 3 1 "
            + "3 1 3 3 "
            + "1 3 1 5 "
            + "1 5 3 5 "
            + "3 5 5 5 "
            + "2 3 4 3 "
            + "4 3 6 3 "
            + "6 3 6 5 "
            + "6 5 4 5 "
            + "4 5 4 3 "
            + "4 2 4 4 ");
    controller = new MarbleSolitaireControllerImpl(rd, ap);
    controller.playGame(model);
    String expected = "Game over!\n"
            + "    _ _ _" + "\n"
            + "    _ _ _" + "\n"
            + "_ _ _ _ _ _ _" + "\n"
            + "_ _ _ O _ _ _" + "\n"
            + "_ _ _ _ _ _ _" + "\n"
            + "    _ _ _" + "\n"
            + "    _ _ _"
            + "\nScore: 1" + "\n";
    assertEquals(expected, ap.toString().substring(ap.toString().length() - expected.length()));
  }

  /**
   * Testing correct output/message after game is over, but not one piece left.
   * Using newlines and spaces for inputs.
   */
  @Test
  public void gameOver2() {
    Appendable ap = new StringBuilder();
    Reader rd = new StringReader("2 4 4 4 "
            + "3 6 3 4 "
            + "1\n5\n3\n5\n"
            + "4 5 2 5 "
            + "6 5 4 5 "
            + "5\n7\n5\n5\n"
            + "5 4 5 6 "
            + "3 7 5 7 "
            + "5 7 5 5 "
            + "3 3 3 5 "
            + "3\n1\n3\n3\n"
            + "5 2 5 4 "
            + "5 4 5 6 "
            + "5 6 3 6 "
            + "3\n6\n3\n4\n"
            + "3 4 3 2 "
            + "7 3 5 3 "
            + "4 3 6 3 "
            + "7 5 7 3 "
            + "7 3 5 3 "
            + "5\n1\n3\n1\n"
            + "3 1 3 3 "
            + "1 3 1 5 "
            + "1 5 3 5 "
            + "3 5 5 5 "
            + "2\n3\n4\n3\n"
            + "4 3 6 3 "
            + "6 3 6 5 "
            + "6 5 4 5 "
            + "4 4 4 6");
    controller = new MarbleSolitaireControllerImpl(rd, ap);
    controller.playGame(model);
    String expected = "Game over!\n"
            + "    _ _ _" + "\n"
            + "    _ _ _" + "\n"
            + "_ _ _ _ _ _ _" + "\n"
            + "_ O _ _ _ O _" + "\n"
            + "_ _ _ _ _ _ _" + "\n"
            + "    _ _ _" + "\n"
            + "    _ _ _"
            + "\nScore: 2" + "\n";
    assertEquals(expected, ap.toString().substring(ap.toString().length() - expected.length()));
  }

  /**
   * Throw Illegal State for being unable to read from Readable, no more user inputs for game.
   * Testing correct exception after game is not over, as user did not quit.
   */
  @Test (expected = IllegalStateException.class)
  public void readableException() {
    Appendable ap = new StringBuilder();
    Reader rd = new StringReader("2 4 4 4 "
            + "3 6 3 4 "
            + "1 5 3 5 "
            + "4 5 2 5 "
            + "6 5 4 5 ");
    controller = new MarbleSolitaireControllerImpl(rd, ap);
    controller.playGame(model);
  }

  /**
   * Throw Illegal State for being unable to read from Readable, no more user inputs for game.
   * Testing correct exception after game is not over, as user did not quit. Using newlines.
   */
  @Test (expected = IllegalStateException.class)
  public void readableException2() {
    Appendable ap = new StringBuilder();
    Reader rd = new StringReader("2\n4\n4\n4\n "
            + "3\n6\n3\n4\n"
            + "1\n5\n3\n5\n"
            + "4\n5\n2\n5\n"
            + "6\n5\n4\n5\n");
    controller = new MarbleSolitaireControllerImpl(rd, ap);
    controller.playGame(model);
  }

  /**
   * Throw Illegal State for being unable to read from Readable, no more user inputs for game.
   * Testing correct exception after game is not over, as user did not quit.
   */
  @Test (expected = IllegalStateException.class)
  public void readableException3() {
    Appendable ap = new StringBuilder();
    Reader rd = new StringReader("2 4");
    controller = new MarbleSolitaireControllerImpl(rd, ap);
    controller.playGame(model);
  }

  /**
   * Throw Illegal State for being unable to append.
   * THis is every similar to one of the quit test methods, which works.
   * The only thing that is different is that appendable is changed, so exception should be thrown.
   */
  @Test (expected = IllegalStateException.class)
  public void appendableException1() {
    Appendable ap = new AppendableTester();
    Reader rd = new StringReader("4 2 4 4 q");
    controller = new MarbleSolitaireControllerImpl(rd, ap);
    controller.playGame(model);
    String expected = "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O" + "\n"
            + "    O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "O O O _ O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "    O O O" + "\n"
            + "    O O O"
            + "\nScore: 32" + "\n";
    assertEquals(expected, ap.toString().substring(ap.toString().length() - expected.length()));
  }

  /**
   * Testing invalid input, something other than q, Q, or positive number.
   */
  @Test
  public void playGameInvalidInput1() {
    Appendable ap = new StringBuilder();
    controller = new MarbleSolitaireControllerImpl(new StringReader("4 2 w 4 q"), ap);
    controller.playGame(model);
    String expected = "Not a valid input. Try again from where you had the invalid input!\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O" + "\n"
            + "    O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "O O O _ O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "    O O O" + "\n"
            + "    O O O"
            + "\nScore: 32" + "\n";
    assertEquals(expected, ap.toString().substring(ap.toString().length() - expected.length()));
  }

  /**
   * Testing invalid input, something other than q, Q, or positive number.
   */
  @Test
  public void playGameInvalidInput2() {
    Appendable ap = new StringBuilder();
    controller = new MarbleSolitaireControllerImpl(new StringReader("4 2 wsdf Q"), ap);
    controller.playGame(model);
    String expected = "Not a valid input. Try again from where you had the invalid input!\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O" + "\n"
            + "    O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "O O O _ O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "    O O O" + "\n"
            + "    O O O"
            + "\nScore: 32" + "\n";
    assertEquals(expected, ap.toString().substring(ap.toString().length() - expected.length()));
  }

  /**
   * Testing invalid input, something other than q, Q, or positive number.
   */
  @Test
  public void playGameInvalidInput3() {
    Appendable ap = new StringBuilder();
    controller = new MarbleSolitaireControllerImpl(new StringReader("4 2 4 -3 Q"), ap);
    controller.playGame(model);
    String expected = "Not a valid input. Try again from where you had the invalid input!\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O" + "\n"
            + "    O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "O O O _ O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "    O O O" + "\n"
            + "    O O O"
            + "\nScore: 32" + "\n";
    assertEquals(expected, ap.toString().substring(ap.toString().length() - expected.length()));
  }

  /**
   * Testing invalid input, something other than q, Q, or positive number.
   */
  @Test
  public void playGameInvalidInput4() {
    Appendable ap = new StringBuilder();
    controller = new MarbleSolitaireControllerImpl(new StringReader("4 2 4 0 q"), ap);
    controller.playGame(model);
    String expected = "Not a valid input. Try again from where you had the invalid input!\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O" + "\n"
            + "    O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "O O O _ O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "    O O O" + "\n"
            + "    O O O"
            + "\nScore: 32" + "\n";
    assertEquals(expected, ap.toString().substring(ap.toString().length() - expected.length()));
  }

  /**
   * Testing invalid input, something other than q, Q, or positive number, when newline to separate.
   */
  @Test
  public void playGameInvalidInput5() {
    Appendable ap = new StringBuilder();
    controller = new MarbleSolitaireControllerImpl(new StringReader("4\n2\n4\n-3\nQ\n"), ap);
    controller.playGame(model);
    String expected = "Not a valid input. Try again from where you had the invalid input!\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O" + "\n"
            + "    O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "O O O _ O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "    O O O" + "\n"
            + "    O O O"
            + "\nScore: 32" + "\n";
    assertEquals(expected, ap.toString().substring(ap.toString().length() - expected.length()));
  }

  /**
   * Testing invalid input, something other than q, Q, or positive number, after one valid move.
   */
  @Test
  public void playGameInvalidInput6() {
    Appendable ap = new StringBuilder();
    controller = new MarbleSolitaireControllerImpl(new StringReader("4 2 4 4 2 3 4 f q"), ap);
    controller.playGame(model);
    String expected = "Not a valid input. Try again from where you had the invalid input!\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O" + "\n"
            + "    O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "O _ _ O O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "    O O O" + "\n"
            + "    O O O"
            + "\nScore: 31" + "\n";
    assertEquals(expected, ap.toString().substring(ap.toString().length() - expected.length()));
  }

  /**
   * Testing invalid horizontal move as signalled by the model.
   */
  @Test
  public void playGameInvalidMove1() {
    Appendable ap = new StringBuilder();
    controller = new MarbleSolitaireControllerImpl(new StringReader("4 2 4 5 Q"), ap);
    controller.playGame(model);
    String expected = "Invalid move. Play again.\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O" + "\n"
            + "    O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "O O O _ O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "    O O O" + "\n"
            + "    O O O"
            + "\nScore: 32" + "\n";
    assertEquals(expected, ap.toString().substring(ap.toString().length() - expected.length()));
  }

  /**
   * Testing invalid vertical move as signalled by the model, after one move is made.
   */
  @Test
  public void playGameInvalidMove2() {
    Appendable ap = new StringBuilder();
    controller = new MarbleSolitaireControllerImpl(new StringReader("4 2 4 4 2 3 4 2 Q"), ap);
    controller.playGame(model);
    String expected = "Invalid move. Play again.\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O" + "\n"
            + "    O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "O _ _ O O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "    O O O" + "\n"
            + "    O O O"
            + "\nScore: 31" + "\n";
    assertEquals(expected, ap.toString().substring(ap.toString().length() - expected.length()));
  }

  /**
   * Testing invalid horizontal move as signalled by the model.
   */
  @Test
  public void playGameInvalidMove3() {
    Appendable ap = new StringBuilder();
    controller = new MarbleSolitaireControllerImpl(new StringReader("50 20 4 5 Q"), ap);
    controller.playGame(model);
    String expected = "Invalid move. Play again.\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O" + "\n"
            + "    O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "O O O _ O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "    O O O" + "\n"
            + "    O O O"
            + "\nScore: 32" + "\n";
    assertEquals(expected, ap.toString().substring(ap.toString().length() - expected.length()));
  }

  /**
   * Testing valid horizontal move as signalled by the model. Making one move.
   */
  @Test
  public void playGameValidMove1() {
    Appendable ap = new StringBuilder();
    controller = new MarbleSolitaireControllerImpl(new StringReader("4 2 4 4 Q"), ap);
    controller.playGame(model);
    String expected = "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O" + "\n"
            + "    O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "O _ _ O O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "    O O O" + "\n"
            + "    O O O"
            + "\nScore: 31" + "\n";
    assertEquals(expected, ap.toString().substring(ap.toString().length() - expected.length()));
  }

  /**
   * Testing valid vertical move down as signalled by the model. Making two move.
   */
  @Test
  public void playGameValidMove2() {
    Appendable ap = new StringBuilder();
    controller = new MarbleSolitaireControllerImpl(new StringReader("4 2 4 4 2 3 4 3 Q"), ap);
    controller.playGame(model);
    String expected = "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O" + "\n"
            + "    _ O O" + "\n"
            + "O O _ O O O O" + "\n"
            + "O _ O O O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "    O O O" + "\n"
            + "    O O O"
            + "\nScore: 30" + "\n";
    assertEquals(expected, ap.toString().substring(ap.toString().length() - expected.length()));
  }

  /**
   * Testing valid vertical move up as signalled by the model. Making three move total.
   * Adding extra number at end. should not affect move, as it is part of next sequence.
   */
  @Test
  public void playGameValidMove3() {
    Appendable ap = new StringBuilder();
    controller = new MarbleSolitaireControllerImpl(
            new StringReader("4 2 4 4 2 3 4 3 5 3 3 3 6 q"), ap);
    controller.playGame(model);
    String expected = "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O" + "\n"
            + "    _ O O" + "\n"
            + "O O O O O O O" + "\n"
            + "O _ _ O O O O" + "\n"
            + "O O _ O O O O" + "\n"
            + "    O O O" + "\n"
            + "    O O O"
            + "\nScore: 29" + "\n";
    assertEquals(expected, ap.toString().substring(ap.toString().length() - expected.length()));
  }

  /**
   * Testing valid horizontal move left as signalled by the model. Making three move total.
   */
  @Test
  public void playGameValidMove4() {
    Appendable ap = new StringBuilder();
    controller = new MarbleSolitaireControllerImpl(
            new StringReader("4 2 4 4 2 3 4 3 5 3 3 3 4 5 4 3 q"), ap);
    controller.playGame(model);
    String expected = "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O" + "\n"
            + "    _ O O" + "\n"
            + "O O O O O O O" + "\n"
            + "O _ O _ _ O O" + "\n"
            + "O O _ O O O O" + "\n"
            + "    O O O" + "\n"
            + "    O O O"
            + "\nScore: 28" + "\n";
    assertEquals(expected, ap.toString().substring(ap.toString().length() - expected.length()));
  }

  /**
   * Testing continuation if there is correct input.
   * "if the to-row  is incorrect, the controller should ask only for to-row before moving on to
   * to-column", not ask for whole thing again.
   */
  @Test
  public void playGameValidMove5() {
    Appendable ap = new StringBuilder();
    controller = new MarbleSolitaireControllerImpl(
            new StringReader("4 2 4 4 2 w 3 4 3 Q"), ap);
    controller.playGame(model);
    String expected = "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O" + "\n"
            + "    _ O O" + "\n"
            + "O O _ O O O O" + "\n"
            + "O _ O O O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "    O O O" + "\n"
            + "    O O O"
            + "\nScore: 30" + "\n";
    assertEquals(expected, ap.toString().substring(ap.toString().length() - expected.length()));
  }

  /**
   * Testing continuation if there is correct input.
   * "if the to-row  is incorrect, the controller should ask only for to-row before moving on to
   * to-column", not ask for whole thing again.
   */
  @Test
  public void playGameValidMove6() {
    Appendable ap = new StringBuilder();
    controller = new MarbleSolitaireControllerImpl(
            new StringReader("4 oops 2 4 4 2 3 0 4 3 -1 5 3 3 3 q"), ap);
    controller.playGame(model);
    String expected = "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O" + "\n"
            + "    _ O O" + "\n"
            + "O O O O O O O" + "\n"
            + "O _ _ O O O O" + "\n"
            + "O O _ O O O O" + "\n"
            + "    O O O" + "\n"
            + "    O O O"
            + "\nScore: 29" + "\n";
    assertEquals(expected, ap.toString().substring(ap.toString().length() - expected.length()));
  }

  /**
   * Testing mock model. Testing controller in isolation.
   * Input should decremented by 1, because of user-friendly checking set up.
   */
  @Test
  public void mockModel1() {
    Appendable mockAp = new StringBuilder();
    MarbleSolitaireModel mockModel = new MockModel(mockAp);
    controller = new MarbleSolitaireControllerImpl(
            new StringReader("4 2 4 4 q"), mockAp);
    controller.playGame(mockModel);
    assertEquals("game state here"
            + "\nScore: 0\n"
            + "3 1 3 3\n"
            + "game state here"
            + "\nScore: 0\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "game state here"
            + "\nScore: 0\n", mockAp.toString());
  }

}