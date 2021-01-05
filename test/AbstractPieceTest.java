import org.junit.Before;
import org.junit.Test;

import cs5004.marblesolitaire.model.EmptySlot;
import cs5004.marblesolitaire.model.IPiece;
import cs5004.marblesolitaire.model.InvalidPiece;
import cs5004.marblesolitaire.model.MarblePiece;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Junit Testing methods of the IPiece.
 */
public class AbstractPieceTest {

  IPiece piece1;
  IPiece piece2;
  IPiece piece3;

  /**
   * Setting up pieces to use during testing.
   */
  @Before
  public void setUp() {
    piece1 = new MarblePiece();
    piece2 = new EmptySlot();
    piece3 = new InvalidPiece();
  }

  /**
   * Testing isEmpty when true.
   */
  @Test
  public void isEmpty1() {
    assertTrue(piece2.isEmpty());
  }

  /**
   * Testing isEmpty when false.
   */
  @Test
  public void isEmpty2() {
    assertFalse(piece1.isEmpty());
  }

  /**
   * Testing isEmpty toString.
   */
  @Test
  public void isEmpty3() {
    assertEquals("_", piece2.toString());
  }

  /**
   * Testing isMarble when true.
   */
  @Test
  public void isMarble1() {
    assertTrue(piece1.isMarble());
  }

  /**
   * Testing isMarble when false.
   */
  @Test
  public void isMarble2() {
    assertFalse(piece3.isMarble());
  }

  /**
   * Testing isMarble toString.
   */
  @Test
  public void isMarble3() {
    assertEquals("O", piece1.toString());
  }

  /**
   * Testing isInvalid when true.
   */
  @Test
  public void isInvalid1() {
    assertTrue(piece3.isInvalid());
  }

  /**
   * Testing isInvalid when false.
   */
  @Test
  public void isInvalid2() {
    assertFalse(piece1.isInvalid());
  }

  /**
   * Testing isInvalid toString.
   */
  @Test
  public void isInvalid3() {
    assertEquals(" ", piece3.toString());
  }

}