import org.junit.Before;
import org.junit.Test;

import cs5004.marblesolitaire.model.MarbleSolitaireModel;
import cs5004.marblesolitaire.model.hw08.TriangleSolitaireModelImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Junit Testing methods of the Triangle Solitaire Model.
 */
public class TriangleSolitaireModelImplTest {

  MarbleSolitaireModel model1;
  MarbleSolitaireModel model2;
  MarbleSolitaireModel model3;
  MarbleSolitaireModel model4;
  MarbleSolitaireModel model5;

  /**
   * Setting up a variety of marble solitaire models to use during testing.
   */
  @Before
  public void setUp() throws Exception {
    model1 = new TriangleSolitaireModelImpl();
    model2 = new TriangleSolitaireModelImpl(2, 2);
    model3 = new TriangleSolitaireModelImpl(7);
    model4 = new TriangleSolitaireModelImpl(7, 5, 3);
    model5 = new TriangleSolitaireModelImpl(4);
  }

  /**
   * Testing constructor when parameters for empty slot are invalid.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testConstructor1() {
    MarbleSolitaireModel test1 = new TriangleSolitaireModelImpl(0,1);
  }

  /**
   * Testing constructor when dimensions is non-positive.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testConstructor2() {
    MarbleSolitaireModel test1 = new TriangleSolitaireModelImpl(-3);
  }

  /**
   * Testing constructor when dimensions is is non-positive.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testConstructor3() {
    MarbleSolitaireModel test1 = new TriangleSolitaireModelImpl(0);
  }

  /**
   * Testing constructor when dimensions is odd but empty slot is invalid.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testConstructor4() {
    MarbleSolitaireModel test1 = new TriangleSolitaireModelImpl(5, 5,1);
  }

  /**
   * Testing constructor when empty slot is valid but illegal side Length.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testConstructor5() {
    MarbleSolitaireModel test1 = new TriangleSolitaireModelImpl(0, 6,4);
  }

  /**
   * Valid vertical move up.
   */
  @Test
  public void move1() {
    model1.move(2,0,0,0);
    assertEquals("    O" + "\n"
            + "   _ O" + "\n"
            + "  _ O O" + "\n"
            + " O O O O" + "\n"
            + "O O O O O", model1.getGameState());
    assertEquals(13, model1.getScore());
  }

  /**
   * Valid horizontal move left, 2nd move.
   */
  @Test
  public void move2() {
    model1.move(2,0,0,0);
    model1.move(2,2,2,0);
    assertEquals("    O" + "\n"
            + "   _ O" + "\n"
            + "  O _ _" + "\n"
            + " O O O O" + "\n"
            + "O O O O O", model1.getGameState());
    assertEquals(12, model1.getScore());
  }

  /**
   * Valid diagonal move southeast, 3rd move.
   */
  @Test
  public void move3() {
    model1.move(2,0,0,0);
    model1.move(2,2,2,0);
    model1.move(0,0,2,2);
    assertEquals("    _" + "\n"
            + "   _ _" + "\n"
            + "  O _ O" + "\n"
            + " O O O O" + "\n"
            + "O O O O O", model1.getGameState());
    assertEquals(11, model1.getScore());
  }

  /**
   * Valid diagonal move northwest, 3rd move.
   */
  @Test
  public void move4() {
    model1.move(2,2,0,0);
    assertEquals("    O" + "\n"
            + "   O _" + "\n"
            + "  O O _" + "\n"
            + " O O O O" + "\n"
            + "O O O O O", model1.getGameState());
    assertEquals(13, model1.getScore());
  }

  /**
   * Valid diagonal move northeast, 2nd move.
   */
  @Test
  public void move5() {
    model1.move(2,2,0,0);
    model1.move(4,0,2,2);
    assertEquals("    O" + "\n"
            + "   O _" + "\n"
            + "  O O O" + "\n"
            + " O _ O O" + "\n"
            + "_ O O O O", model1.getGameState());
    assertEquals(12, model1.getScore());
  }

  /**
   * Valid horizontal move right, 4th move.
   */
  @Test
  public void move6() {
    model1.move(2,0,0,0);
    model1.move(2,2,2,0);
    model1.move(4,2,2,2);
    model1.move(4,0,4,2);
    assertEquals("    O" + "\n"
            + "   _ O" + "\n"
            + "  O _ O" + "\n"
            + " O O _ O" + "\n"
            + "_ _ O O O", model1.getGameState());
    assertEquals(10, model1.getScore());
  }

  /**
   * Valid vertical move down, 5th move.
   */
  @Test
  public void move7() {
    model1.move(2,0,0,0);
    model1.move(2,2,2,0);
    model1.move(4,2,2,2);
    model1.move(4,0,4,2);
    model1.move(2,0,4,0);
    assertEquals("    O" + "\n"
            + "   _ O" + "\n"
            + "  _ _ O" + "\n"
            + " _ O _ O" + "\n"
            + "O _ O O O", model1.getGameState());
    assertEquals(9, model1.getScore());
  }

  /**
   * Valid diagonal move southwest, 5th move.
   */
  @Test
  public void move8() {
    model1.move(2,0,0,0);
    model1.move(2,2,2,0);
    model1.move(4,2,2,2);
    model1.move(4,0,4,2);
    model1.move(2,2,4,0);
    assertEquals("    O" + "\n"
            + "   _ O" + "\n"
            + "  O _ _" + "\n"
            + " O _ _ O" + "\n"
            + "O _ O O O", model1.getGameState());
    assertEquals(9, model1.getScore());
  }

  /**
   * Invalid move. Moving from invalid spot.
   */
  @Test (expected = IllegalArgumentException.class)
  public void move9() {
    model1.move(0,2,0,0);
  }

  /**
   * Invalid move. Moving from spot outside board limits.
   */
  @Test (expected = IllegalArgumentException.class)
  public void move10() {
    model1.move(6,2,0,0);
  }

  /**
   * Invalid move. Moving to invalid spot diagonally.
   */
  @Test (expected = IllegalArgumentException.class)
  public void move11() {
    model1.move(3,1,1,3);
  }

  /**
   * Invalid move. Moving from spot that is empty/doesn't have marble, to a marble spot.
   */
  @Test (expected = IllegalArgumentException.class)
  public void move12() {
    model1.move(0,0,2,2);
  }

  /**
   * Invalid move. Trying to move to empty spot from marble,
   * but not vertically, horizontally, or diagonally.
   */
  @Test (expected = IllegalArgumentException.class)
  public void move13() {
    model1.move(2,1,0,0);
  }

  /**
   * Invalid move. Moving to invalid spot vertically.
   */
  @Test (expected = IllegalArgumentException.class)
  public void move14() {
    model1.move(4,3,2,3);
  }

  /**
   * Invalid move. Moving to invalid spot horizontally.
   */
  @Test (expected = IllegalArgumentException.class)
  public void move15() {
    model1.move(2,1,2,3);
  }

  /**
   * Moving to spot that is empty but also empty middle.
   */
  @Test (expected = IllegalArgumentException.class)
  public void move16() {
    model1.move(2,0,0,0);
    model1.move(3,0,1,0);
  }

  /**
   * Invalid move. Moving more than two spots away diagonally.
   */
  @Test (expected = IllegalArgumentException.class)
  public void move17() {
    model1.move(3,3,0,0);
  }

  /**
   * Invalid move. Moving one spot away diagonally.
   */
  @Test (expected = IllegalArgumentException.class)
  public void move18() {
    model1.move(1,1,0,0);
  }

  /**
   * Invalid move. Moving to out of bounds diagonally.
   */
  @Test (expected = IllegalArgumentException.class)
  public void move19() {
    model1.move(3,3,5,5);
  }

  /**
   * after 1 move when dimension of 5.
   */
  @Test
  public void isGameOver1() {
    model1.move(2,0,0,0);
    assertEquals("    O" + "\n"
            + "   _ O" + "\n"
            + "  _ O O" + "\n"
            + " O O O O" + "\n"
            + "O O O O O", model1.getGameState());
    assertFalse(model1.isGameOver());
  }

  /**
   * when dimension of 7.
   */
  @Test
  public void isGameOver2() {
    assertEquals("      _" + "\n"
            + "     O O" + "\n"
            + "    O O O" + "\n"
            + "   O O O O" + "\n"
            + "  O O O O O" + "\n"
            + " O O O O O O" + "\n"
            + "O O O O O O O", model3.getGameState());
    assertFalse(model3.isGameOver());
  }

  /**
   * when dimension of 5, game is over, but more than 1 left.
   */
  @Test
  public void isGameOver3() {
    model1.move(2,0,0,0);
    model1.move(2,2,2,0);
    model1.move(4,1,2,1);
    model1.move(4,2,2,2);
    model1.move(4,4,4,2);
    model1.move(2,2,4,4);
    model1.move(0,0,2,2);
    model1.move(3,0,1,0);
    model1.move(2,2,2,0);
    model1.move(1,0,3,0);
    model1.move(4,0,2,0);
    assertTrue(model1.isGameOver());
  }

  /**
   * Testing getState for default board with dimension of 5 and default empty spot at (0,0).
   */
  @Test
  public void getGameState1() {
    assertEquals("    _" + "\n"
            + "   O O" + "\n"
            + "  O O O" + "\n"
            + " O O O O" + "\n"
            + "O O O O O", model1.getGameState());
  }

  /**
   * Testing getState for default board with dimension of 7 and default empty spot at (0,0).
   */
  @Test
  public void getGameState2() {
    assertEquals("      _" + "\n"
                    + "     O O" + "\n"
                    + "    O O O" + "\n"
                    + "   O O O O" + "\n"
                    + "  O O O O O" + "\n"
                    + " O O O O O O" + "\n"
                    + "O O O O O O O", model3.getGameState());
  }

  /**
   * Testing getState for default board with dimension of 5 and empty spot at (2,2).
   */
  @Test
  public void getGameState3() {
    assertEquals("    O" + "\n"
            + "   O O" + "\n"
            + "  O O _" + "\n"
            + " O O O O" + "\n"
            + "O O O O O", model2.getGameState());
  }

  /**
   * Testing getState for default board with dimension of 7 and default empty spot at (5,3).
   */
  @Test
  public void getGameState4() {
    assertEquals("      O" + "\n"
            + "     O O" + "\n"
            + "    O O O" + "\n"
            + "   O O O O" + "\n"
            + "  O O O O O" + "\n"
            + " O O O _ O O" + "\n"
            + "O O O O O O O", model4.getGameState());
  }

  /**
   * Testing getState for even dimension of 4 and default empty spot at (0,0).
   */
  @Test
  public void getGameState5() {
    assertEquals("   _" + "\n"
            + "  O O" + "\n"
            + " O O O" + "\n"
            + "O O O O", model5.getGameState());
  }

  @Test
  public void getScore1() {
    assertEquals(14, model1.getScore());
  }

  @Test
  public void getScore2() {
    assertEquals(27, model3.getScore());
  }

  @Test
  public void getScore3() {
    assertEquals(9, model5.getScore());
  }
}