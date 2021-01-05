package cs5004.marblesolitaire.controller;

import cs5004.marblesolitaire.model.MarbleSolitaireModel;

/**
 * This interface represents the operations offered by the marble solitaire
 * controller.
 * This controller will “run” a game of Marble Solitaire.
 */
public interface MarbleSolitaireController {

  /**
   * This method should play a new game of Marble Solitaire using the provided model.
   * @param model The marble solitaire model.
   * @throws IllegalArgumentException if the provided model is null.
   * @throws IllegalStateException if unable to successfully receive input or transmit output.
   */
  void playGame(MarbleSolitaireModel model) throws IllegalArgumentException, IllegalStateException;

}
