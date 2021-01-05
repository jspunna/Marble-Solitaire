import org.junit.Before;
import org.junit.Test;

import cs5004.marblesolitaire.model.hw08.EuropeanSolitaireModelImpl;
import cs5004.marblesolitaire.model.MarbleSolitaireModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Junit Testing methods of the European Solitaire Model.
 */
public class EuropeanSolitaireModelImplTest {

  MarbleSolitaireModel model1;
  MarbleSolitaireModel model2;
  MarbleSolitaireModel model3;
  MarbleSolitaireModel model4;

  /**
   * Setting up a variety of marble solitaire models to use during testing.
   */
  @Before
  public void setUp() {
    model1 = new EuropeanSolitaireModelImpl();
    model2 = new EuropeanSolitaireModelImpl(2, 2);
    model3 = new EuropeanSolitaireModelImpl(5);
    model4 = new EuropeanSolitaireModelImpl(5, 2, 2);
  }

  /**
   * Testing constructor when parameters for empty slot are invalid.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testConstructor1() {
    MarbleSolitaireModel test1 = new EuropeanSolitaireModelImpl(0,1);
  }

  /**
   * Testing constructor when armThickness is not a positive odd number.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testConstructor2() {
    MarbleSolitaireModel test1 = new EuropeanSolitaireModelImpl(-3);
  }

  /**
   * Testing constructor when side Length is not a positive odd number.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testConstructor3() {
    MarbleSolitaireModel test1 = new EuropeanSolitaireModelImpl(2);
  }

  /**
   * Testing constructor when side Length is odd but empty slot is invalid.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testConstructor4() {
    MarbleSolitaireModel test1 = new EuropeanSolitaireModelImpl(5, 10,1);
  }

  /**
   * Testing constructor when empty slot is valid but illegal side Length.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testConstructor5() {
    MarbleSolitaireModel test1 = new EuropeanSolitaireModelImpl(2, 6,4);
  }

  /**
   * Valid horizontal move right. Moving to empty center.
   */
  @Test
  public void move1() {
    model1.move(3,1,3,3);
    assertEquals("    O O O" + "\n"
            + "  O O O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "O _ _ O O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "  O O O O O" + "\n"
            + "    O O O", model1.getGameState());
    assertEquals(35, model1.getScore());
  }

  /**
   * Valid horizontal move left.
   */
  @Test
  public void move2() {
    model1.move(3,1,3,3);
    model1.move(3,4,3,2);
    assertEquals("    O O O" + "\n"
            + "  O O O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "O _ O _ _ O O" + "\n"
            + "O O O O O O O" + "\n"
            + "  O O O O O" + "\n"
            + "    O O O", model1.getGameState());
    assertEquals(34, model1.getScore());
  }

  /**
   * Valid vertical move down.
   */
  @Test
  public void move3() {
    model1.move(3,1,3,3);
    model1.move(3,4,3,2);
    model1.move(1,1,3,1);
    assertEquals("    O O O" + "\n"
            + "  _ O O O O" + "\n"
            + "O _ O O O O O" + "\n"
            + "O O O _ _ O O" + "\n"
            + "O O O O O O O" + "\n"
            + "  O O O O O" + "\n"
            + "    O O O", model1.getGameState());
    assertEquals(33, model1.getScore());
  }

  /**
   * Valid vertical move up.
   */
  @Test
  public void move4() {
    model1.move(3,1,3,3);
    model1.move(3,4,3,2);
    model1.move(5, 1, 3, 1);
    assertEquals("    O O O" + "\n"
            + "  O O O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "O O O _ _ O O" + "\n"
            + "O _ O O O O O" + "\n"
            + "  _ O O O O" + "\n"
            + "    O O O", model1.getGameState());
    assertEquals(33, model1.getScore());
  }

  /**
   * Invalid move. Moving from invalid spot.
   */
  @Test (expected = IllegalArgumentException.class)
  public void move5() {
    model1.move(1,0,3,3);
  }

  /**
   * Invalid move. Moving from spot outside board limits.
   */
  @Test (expected = IllegalArgumentException.class)
  public void move6() {
    model1.move(-1,-2,3,3);
  }

  /**
   * Invalid move. Moving to invalid spot.
   */
  @Test (expected = IllegalArgumentException.class)
  public void move7() {
    model1.move(3,1,6,5);
  }

  /**
   * Invalid move. Moving from spot that is empty/doesn't have marble, to a marble spot.
   */
  @Test (expected = IllegalArgumentException.class)
  public void move8() {
    model1.move(3,1,3,3);
    assertEquals("    O O O" + "\n"
            + "  O O O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "O _ _ O O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "  O O O O O" + "\n"
            + "    O O O", model1.getGameState());
    model1.move(3,2, 3,4);
  }

  /**
   * Invalid move. Moving to spot that already has a marble from marble, with middle as marble.
   */
  @Test (expected = IllegalArgumentException.class)
  public void move9() {
    model1.move(3,1,3,3);
    assertEquals("    O O O" + "\n"
            + "  O O O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "O _ _ O O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "  O O O O O" + "\n"
            + "    O O O", model1.getGameState());
    model1.move(3,3, 3,5);
  }

  /**
   * Invalid move. Moving vertically more than two spots.
   */
  @Test (expected = IllegalArgumentException.class)
  public void move10() {
    model1.move(6,3, 3,3);
  }

  /**
   * Invalid move. Moving vertically to empty spot next to it.
   */
  @Test (expected = IllegalArgumentException.class)
  public void move11() {
    model1.move(2,3, 3,3);
  }

  /**
   * Invalid move. Moving horizontally more than two spots.
   */
  @Test (expected = IllegalArgumentException.class)
  public void move12() {
    model1.move(3,6, 3,3);
  }

  /**
   * Invalid move. Moving to spot that is empty from marble spot, with middle empty.
   */
  @Test (expected = IllegalArgumentException.class)
  public void move13() {
    model1.move(3,1,3,3);
    assertEquals("    O O O" + "\n"
            + "  O O O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "O _ _ O O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "  O O O O O" + "\n"
            + "    O O O", model1.getGameState());
    model1.move(3,0, 3,2);
  }

  /**
   * Invalid move. Moving to spot that already has a marble from marble spot, while middle is empty.
   */
  @Test (expected = IllegalArgumentException.class)
  public void move14() {
    assertEquals("    O O O" + "\n"
            + "  O O O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "O O O _ O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "  O O O O O" + "\n"
            + "    O O O", model1.getGameState());
    model1.move(3,2, 3,4);
  }

  /**
   * Invalid move. Making same move twice.
   */
  @Test (expected = IllegalArgumentException.class)
  public void move15() {
    model1.move(3,1,3,3);
    model1.move(3,1,3,3);
    assertEquals("    O O O" + "\n"
            + "  O O O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "O _ _ O O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "  O O O O O" + "\n"
            + "    O O O", model1.getGameState());
  }

  /**
   * after 1 move when side Length of 3.
   */
  @Test
  public void isGameOver1() {
    model1.move(3,1,3,3);
    assertEquals("    O O O" + "\n"
            + "  O O O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "O _ _ O O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "  O O O O O" + "\n"
            + "    O O O", model1.getGameState());
    assertFalse(model1.isGameOver());
  }

  /**
   * Get after 4 moves when side Length of 5.
   */
  @Test
  public void isGameOver2() {
    model3.move(6,4,6,6);
    model3.move(6,7,6,5);
    model3.move(8,4,6,4);
    model3.move(4,7,6,7);
    assertFalse(model3.isGameOver());
  }

  /**
   * At initial state, of model 1. Game has not started.
   */
  @Test
  public void isGameOver4() {
    assertFalse(model1.isGameOver());
  }

  /**
   * Testing getState for default board with side length of 3 and default empty spot in center.
   */
  @Test
  public void getGameState1() {
    assertEquals("    O O O" + "\n"
            + "  O O O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "O O O _ O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "  O O O O O" + "\n"
            + "    O O O", model1.getGameState());
  }

  /**
   * Testing getState for default board with side length of 3, but new initial empty spot.
   */
  @Test
  public void getGameState2() {
    assertEquals("    O O O" + "\n"
            + "  O O O O O" + "\n"
            + "O O _ O O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "  O O O O O" + "\n"
            + "    O O O", model2.getGameState());
  }

  /**
   * Testing getState for board with side length of 5 and default empty spot in center.
   */
  @Test
  public void getGameState3() {
    assertEquals("        O O O O O" + "\n"
            + "      O O O O O O O" + "\n"
            + "    O O O O O O O O O" + "\n"
            + "  O O O O O O O O O O O" + "\n"
            + "O O O O O O O O O O O O O" + "\n"
            + "O O O O O O O O O O O O O" + "\n"
            + "O O O O O O _ O O O O O O" + "\n"
            + "O O O O O O O O O O O O O" + "\n"
            + "O O O O O O O O O O O O O" + "\n"
            + "  O O O O O O O O O O O" + "\n"
            + "    O O O O O O O O O" + "\n"
            + "      O O O O O O O" + "\n"
            + "        O O O O O", model3.getGameState());
  }

  /**
   * Testing getState for board with arm thickness of 5 but new chosen initial empty spot.
   */
  @Test
  public void getGameState4() {
    assertEquals("        O O O O O" + "\n"
            + "      O O O O O O O" + "\n"
            + "    _ O O O O O O O O" + "\n"
            + "  O O O O O O O O O O O" + "\n"
            + "O O O O O O O O O O O O O" + "\n"
            + "O O O O O O O O O O O O O" + "\n"
            + "O O O O O O O O O O O O O" + "\n"
            + "O O O O O O O O O O O O O" + "\n"
            + "O O O O O O O O O O O O O" + "\n"
            + "  O O O O O O O O O O O" + "\n"
            + "    O O O O O O O O O" + "\n"
            + "      O O O O O O O" + "\n"
            + "        O O O O O", model4.getGameState());
  }

  @Test
  public void getScore1() {
    assertEquals(36, model1.getScore());
  }

  @Test
  public void getScore2() {
    assertEquals(128, model3.getScore());
  }
}