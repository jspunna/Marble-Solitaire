import org.junit.Before;
import org.junit.Test;

import cs5004.marblesolitaire.model.MarbleSolitaireModel;
import cs5004.marblesolitaire.model.MarbleSolitaireModelImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Junit Testing methods of the Marble Solitaire Model.
 */
public class MarbleSolitaireModelTest {

  MarbleSolitaireModel model1;
  MarbleSolitaireModel model2;
  MarbleSolitaireModel model3;
  MarbleSolitaireModel model4;
  MarbleSolitaireModel model5;
  MarbleSolitaireModel model6;

  /**
   * Setting up a variety of marble solitaire models to use during testing.
   */
  @Before
  public void setUp() {
    model1 = new MarbleSolitaireModelImpl();
    model2 = new MarbleSolitaireModelImpl(2, 2);
    model3 = new MarbleSolitaireModelImpl(5);
    model4 = new MarbleSolitaireModelImpl(5, 2, 5);
    model5 = new MarbleSolitaireModelImpl(7);
    model6 = new MarbleSolitaireModelImpl(7, 15, 10);
  }

  /**
   * Testing constructor when parameters for empty slot are invalid.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testConstructor1() {
    MarbleSolitaireModel test1 = new MarbleSolitaireModelImpl(0,1);
  }

  /**
   * Testing constructor when armThickness is not a positive odd number.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testConstructor2() {
    MarbleSolitaireModel test1 = new MarbleSolitaireModelImpl(-3);
  }

  /**
   * Testing constructor when armThickness is not a positive odd number.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testConstructor3() {
    MarbleSolitaireModel test1 = new MarbleSolitaireModelImpl(2);
  }

  /**
   * Testing constructor when armThickness is odd but empty slot is invalid.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testConstructor4() {
    MarbleSolitaireModel test1 = new MarbleSolitaireModelImpl(5, 10,1);
  }

  /**
   * Testing constructor when empty slot is valid but illegal arm Thickness.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testConstructor5() {
    MarbleSolitaireModel test1 = new MarbleSolitaireModelImpl(2, 6,4);
  }

  /**
   * Valid move. Moving to empty center.
   */
  @Test
  public void move1() {
    model1.move(3,1,3,3);
    assertEquals("    O O O" + "\n"
            + "    O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "O _ _ O O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "    O O O" + "\n"
            + "    O O O", model1.getGameState());
    assertEquals(31, model1.getScore());
  }

  /**
   * Invalid move. Moving from invalid spot.
   */
  @Test (expected = IllegalArgumentException.class)
  public void move2() {
    model1.move(0,0,3,3);
  }

  /**
   * Invalid move. Moving from spot outside board limits.
   */
  @Test (expected = IllegalArgumentException.class)
  public void move3() {
    model1.move(-1,-2,3,3);
  }

  /**
   * Invalid move. Moving to invalid spot.
   */
  @Test (expected = IllegalArgumentException.class)
  public void move4() {
    model1.move(3,1,6,5);
  }

  /**
   * Invalid move. Moving from spot that is empty/doesn't have marble, to a marble spot.
   */
  @Test (expected = IllegalArgumentException.class)
  public void move5() {
    model1.move(3,1,3,3);
    assertEquals("    O O O" + "\n"
            + "    O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "O _ _ O O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "    O O O" + "\n"
            + "    O O O", model1.getGameState());
    model1.move(3,2, 3,4);
  }

  /**
   * Invalid move. Moving to spot that already has a marble from marble, with middle as marble.
   */
  @Test (expected = IllegalArgumentException.class)
  public void move6() {
    model1.move(3,1,3,3);
    assertEquals("    O O O" + "\n"
            + "    O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "O _ _ O O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "    O O O" + "\n"
            + "    O O O", model1.getGameState());
    model1.move(3,3, 3,5);
  }

  /**
   * Invalid move. Moving vertically more than two spots.
   */
  @Test (expected = IllegalArgumentException.class)
  public void move7() {
    model1.move(6,6, 3,3);
  }

  /**
   * Invalid move. Moving vertically to empty spot next to it.
   */
  @Test (expected = IllegalArgumentException.class)
  public void move8() {
    model1.move(2,3, 3,3);
  }

  /**
   * Invalid move. Moving horizontally more than two spots.
   */
  @Test (expected = IllegalArgumentException.class)
  public void move9() {
    model1.move(3,6, 3,3);
  }

  /**
   * Invalid move. Moving horizontally to empty spot next to it.
   */
  @Test (expected = IllegalArgumentException.class)
  public void move10() {
    model1.move(3,2, 3,3);
  }

  /**
   * Invalid move. Moving to spot that is empty from marble spot, with middle empty.
   */
  @Test (expected = IllegalArgumentException.class)
  public void move11() {
    model1.move(3,1,3,3);
    assertEquals("    O O O" + "\n"
            + "    O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "O _ _ O O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "    O O O" + "\n"
            + "    O O O", model1.getGameState());
    model1.move(3,0, 3,2);
  }

  /**
   * Invalid move. Moving to spot that already has a marble from marble spot, while middle is empty.
   */
  @Test (expected = IllegalArgumentException.class)
  public void move12() {
    assertEquals("    O O O" + "\n"
            + "    O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "O O O _ O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "    O O O" + "\n"
            + "    O O O", model1.getGameState());
    model1.move(3,2, 3,4);
  }

  /**
   * Invalid move. Making same move twice.
   */
  @Test (expected = IllegalArgumentException.class)
  public void move13() {
    model1.move(3,1,3,3);
    model1.move(3,1,3,3);
    assertEquals("    O O O" + "\n"
            + "    O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "O _ _ O O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "    O O O" + "\n"
            + "    O O O", model1.getGameState());
  }

  /**
   * Valid move. Making more than one move.
   */
  @Test
  public void move14() {
    model1.move(3,1,3,3);
    model1.move(3,4,3,2);
    assertEquals("    O O O" + "\n"
            + "    O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "O _ O _ _ O O" + "\n"
            + "O O O O O O O" + "\n"
            + "    O O O" + "\n"
            + "    O O O", model1.getGameState());
  }

  /**
   * after 1 move when arm thickness of 3.
   */
  @Test
  public void isGameOver1() {
    model1.move(3,1,3,3);
    assertEquals("    O O O" + "\n"
            + "    O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "O _ _ O O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "    O O O" + "\n"
            + "    O O O", model1.getGameState());
    assertFalse(model1.isGameOver());
  }

  /**
   * Get after 4 moves when arm thickness of 5.
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
   * Playing the whole game, down to 1 peg. arm thickness 3.
   */
  @Test
  public void isGameOver3() {
    model1.move(3,1,3,3);
    model1.move(5,2,3,2);
    model1.move(4,0,4,2);
    model1.move(4,3,4,1);
    model1.move(4,5,4,3);
    model1.move(6,4,4,4);
    model1.move(3,4,5,4);
    model1.move(6,2,6,4);
    model1.move(6,4,4,4);
    model1.move(2,2,4,2);
    model1.move(0,2,2,2);
    model1.move(1,4,3,4);
    model1.move(3,4,5,4);
    model1.move(5,4,5,2);
    model1.move(5,2,3,2);
    model1.move(3,2,1,2);
    model1.move(2,0,4,0);
    model1.move(4,0,4,2);
    model1.move(4,2,4,4);
    model1.move(2,6,2,4);
    model1.move(2,3,2,5);
    model1.move(4,6,2,6);
    model1.move(2,6,2,4);
    model1.move(0,4,0,2);
    model1.move(0,2,2,2);
    model1.move(2,1,2,3);
    model1.move(2,3,2,5);
    model1.move(2,5,4,5);
    model1.move(4,5,4,3);
    model1.move(4,3,2,3);
    model1.move(1,3,3,3);
    assertTrue(model1.isGameOver());
    assertEquals(1, model1.getScore());
  }

  /**
   * At initial state, of model 1. Game has not started.
   */
  @Test
  public void isGameOver4() {
    assertFalse(model1.isGameOver());
  }

  /**
   * When down to no more legals moves, but not score of 1.
   */
  @Test
  public void isGameOver5() {
    model1.move(3,1,3,3);
    model1.move(3,4,3,2);
    model1.move(3,6,3,4);
    model1.move(5,3,3,3);
    model1.move(4,5,4,3);
    model1.move(4,2,4,4);
    model1.move(2,2,4,2);
    model1.move(4,1,4,3);
    model1.move(3,4,3,2);
    model1.move(4,3,4,5);
    model1.move(4,6,4,4);
    model1.move(5,4,3,4);
    model1.move(2,4,4,4);
    model1.move(0,4,2,4);
    model1.move(6,2,4,2);
    model1.move(3,2,5,2);
    model1.move(6,4,6,2);
    model1.move(6,2,4,2);
    model1.move(2,4,2,2);
    model1.move(2,1,2,3);
    model1.move(1,3,3,3);
    model1.move(0,2,2,2);
    model1.move(2,6,2,4);
    assertTrue(model1.isGameOver());
    assertEquals(9, model1.getScore());
  }

  /**
   * Testing getState for default board with arm thickness of 3 and default empty spot in center.
   */
  @Test
  public void getGameState1() {
    assertEquals("    O O O" + "\n"
            + "    O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "O O O _ O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "    O O O" + "\n"
            + "    O O O", model1.getGameState());
  }

  /**
   * Testing getState for default board with arm thickness of 3, but new initial empty spot.
   */
  @Test
  public void getGameState2() {
    assertEquals("    O O O" + "\n"
            + "    O O O" + "\n"
            + "O O _ O O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "O O O O O O O" + "\n"
            + "    O O O" + "\n"
            + "    O O O", model2.getGameState());
  }

  /**
   * Testing getState for board with arm thickness of 5 and default empty spot in center.
   */
  @Test
  public void getGameState3() {
    assertEquals("        O O O O O" + "\n"
            + "        O O O O O" + "\n"
            + "        O O O O O" + "\n"
            + "        O O O O O" + "\n"
            + "O O O O O O O O O O O O O" + "\n"
            + "O O O O O O O O O O O O O" + "\n"
            + "O O O O O O _ O O O O O O" + "\n"
            + "O O O O O O O O O O O O O" + "\n"
            + "O O O O O O O O O O O O O" + "\n"
            + "        O O O O O" + "\n"
            + "        O O O O O" + "\n"
            + "        O O O O O" + "\n"
            + "        O O O O O", model3.getGameState());
  }

  /**
   * Testing getState for board with arm thickness of 5 but new chosen initial empty spot.
   */
  @Test
  public void getGameState4() {
    assertEquals("        O O O O O" + "\n"
            + "        O O O O O" + "\n"
            + "        O _ O O O" + "\n"
            + "        O O O O O" + "\n"
            + "O O O O O O O O O O O O O" + "\n"
            + "O O O O O O O O O O O O O" + "\n"
            + "O O O O O O O O O O O O O" + "\n"
            + "O O O O O O O O O O O O O" + "\n"
            + "O O O O O O O O O O O O O" + "\n"
            + "        O O O O O" + "\n"
            + "        O O O O O" + "\n"
            + "        O O O O O" + "\n"
            + "        O O O O O", model4.getGameState());
  }

  /**
   * Get after 4 moves when arm thickness of 5.
   */
  @Test
  public void getGameState5() {
    model3.move(6,4,6,6);
    model3.move(6,7,6,5);
    model3.move(8,4,6,4);
    model3.move(4,7,6,7);
    assertEquals("        O O O O O" + "\n"
            + "        O O O O O" + "\n"
            + "        O O O O O" + "\n"
            + "        O O O O O" + "\n"
            + "O O O O O O O _ O O O O O" + "\n"
            + "O O O O O O O _ O O O O O" + "\n"
            + "O O O O O O _ O O O O O O" + "\n"
            + "O O O O _ O O O O O O O O" + "\n"
            + "O O O O _ O O O O O O O O" + "\n"
            + "        O O O O O" + "\n"
            + "        O O O O O" + "\n"
            + "        O O O O O" + "\n"
            + "        O O O O O", model3.getGameState());
  }

  /**
   * Get initial score when arm thickness of 3.
   */
  @Test
  public void getScore1() {
    assertEquals(32, model1.getScore());
  }

  /**
   * Get initial score when arm thickness of 5.
   */
  @Test
  public void getScore2() {
    assertEquals(104, model3.getScore());
  }

  /**
   * Get initial score when arm thickness of 7.
   */
  @Test
  public void getScore3() {
    assertEquals(216, model5.getScore());
  }

  /**
   * Get score after one move when arm thickness of 7.
   */
  @Test
  public void getScore4() {
    model5.move(9,7,9,9);
    assertEquals(215, model5.getScore());
  }

  /**
   * Get after 4 moves when arm thickness of 5.
   */
  @Test
  public void getScore5() {
    model3.move(6,4,6,6);
    model3.move(6,7,6,5);
    model3.move(8,4,6,4);
    model3.move(4,7,6,7);
    assertEquals(100, model3.getScore());
  }

  /**
   * Get initial score when arm thickness of 5 but not center empty.
   */
  @Test
  public void getScore6() {
    assertEquals(104, model4.getScore());
  }

}