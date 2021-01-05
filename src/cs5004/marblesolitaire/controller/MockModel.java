package cs5004.marblesolitaire.controller;

import java.io.IOException;

import cs5004.marblesolitaire.model.MarbleSolitaireModel;

/**
 * This class MockModel implements the MarbleSolitaireModel Interface.
 * This class is created for testing purposes to test the controller in isolation.
 * Test conducted in MarbleSolitaireControllerImplTest under "public void mockModel()".
 */
public class MockModel implements MarbleSolitaireModel {

  private Appendable log;

  public MockModel(Appendable log) {
    this.log = log;
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    try {
      log.append(fromRow + " " + fromCol + " " + toRow + " " + toCol + "\n");
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public String getGameState() {
    return "game state here";
  }

  @Override
  public int getScore() {
    return 0;
  }
}
