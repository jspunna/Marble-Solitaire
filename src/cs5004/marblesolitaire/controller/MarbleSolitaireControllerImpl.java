package cs5004.marblesolitaire.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cs5004.marblesolitaire.model.MarbleSolitaireModel;

/**
 * This class represents the MarbleSolitaireControllerImpl class,
 * which accepts and stores objects for doing input and output. It implements the operations in
 * the MarbleSolitaireController interface.
 * In addition to storing the inputs and outputs, the class contains the playGame method.
 * which helps "run" the game using the MarbleSolitaire model.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {

  private final Readable rd;
  private final Appendable ap;

  /**
   * Constructs the controller using Readable and Appendable,
   * which are two existing interfaces in Java that abstract input and output respectively.
   * @param rd The readable input for the controller.
   * @param ap The appendable output for the controller.
   * @throws IllegalArgumentException if the readable or appendable objects are null.
   */
  public MarbleSolitaireControllerImpl(Readable rd, Appendable ap) throws IllegalArgumentException {
    if (rd == null || ap == null) {
      throw new IllegalArgumentException();
    }
    this.rd = rd;
    this.ap = ap;
  }

  @Override
  public void playGame(MarbleSolitaireModel model) throws IllegalArgumentException,
          IllegalStateException {
    if (model == null) {
      throw new IllegalArgumentException();
    }

    Scanner scan = new Scanner(this.rd);
    List<Integer> inputs = new ArrayList<Integer>(4);

    // This is would be usually be the display for the user in the view, but based on the
    // assignment prompt & piazza, transmitting initial game state when starting game.
    try {
      this.ap.append(model.getGameState());
      this.ap.append("\nScore: ").append(String.valueOf(model.getScore())).append("\n");
    } catch (IOException f) {
      throw new IllegalStateException();
    }

    while (scan.hasNext()) {
      String output = scan.next();

      // Check if output is q or Quit using private checkQuit method.
      if (checkQuit(output, model)) {
        return;

        // Check if output is a number, using private isNumeric method. If so, add to inputs list.
      } else if (isNumeric(output)) {
        inputs.add(Integer.parseInt(output));

        // Check if list has four numbers, in order to make move.
        // Make the inputs more user-friendly, row and columns numbers in the input begin from 1.
        if (inputs.size() == 4) {
          int num1 = inputs.get(0);
          int num2 = inputs.get(1);
          int num3 = inputs.get(2);
          int num4 = inputs.get(3);

          int fromR = num1 - 1;
          int fromC = num2 - 1;
          int toR = num3 - 1;
          int toC = num4 - 1;

          // Make game move using from and to moves, using private method.
          // Method also transmits game state after valid move, showing updated board.
          makeMove(model, fromR, fromC, toR, toC);

          // Check if the game has been won, using private checkGameOver method.
          if (checkGameOver(model)) {
            break;
          }

          inputs.clear();
        }

        // If the scan output is not quit or a number, then re-try.
      } else {
        try {
          this.ap.append("Not a valid input. Try again from where you had the invalid input!\n");
        } catch (IOException e) {
          throw new IllegalStateException();
        }
      }
    }

    // When there is no more inputs to scan and game is not over.
    if (!model.isGameOver()) {
      throw new IllegalStateException("Ran out of inputs");
    }
  }


  /**
   * Method checks to see if the output from readable scanned is a 'q' or 'Q', to exit the game.
   * Also if quit, method appends appropriate messages to user.
   * @param output The output that was scanned from the readable.
   * @param model The marble solitaire model.
   * @return a true or false of if the output is a 'q' or 'Q'.
   * @throws IllegalStateException if cannot transmit/append output.
   */
  private boolean checkQuit(String output, MarbleSolitaireModel model)
          throws IllegalStateException {
    if (output.equals("q") || output.equals("Q")) {
      try {
        this.ap.append("Game quit!\n");
        this.ap.append("State of game when quit:\n");
        this.ap.append(model.getGameState());
        this.ap.append("\nScore: ").append(String.valueOf(model.getScore())).append("\n");
      } catch (IOException e) {
        throw new IllegalStateException("Could not transmit output\n");
      }
      return true;
    }
    return false;
  }

  /**
   * Method checks to see if the output from readable scanned is a number.
   * @param output The output that was scanned from the readable.
   * @return a true or false of if the output read is a number or not.
   */
  private boolean isNumeric(String output) {
    try {
      int num = Integer.parseInt(output);
      return num >= 1;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  /**
   * Make game move using from and to moves.
   * It also transmits game state after valid move, in order to show updated board.
   * @param model The marble solitaire model.
   * @param fromRow "user-friendly" from row.
   * @param fromCol "user-friendly" from column.
   * @param toRow "user-friendly" to row.
   * @param toCol "user-friendly" to column.
   */
  private void makeMove(MarbleSolitaireModel model, int fromRow, int fromCol,
                        int toRow, int toCol) {
    try {
      model.move(fromRow, fromCol, toRow, toCol);
      try {
        this.ap.append(model.getGameState());
        this.ap.append("\nScore: ").append(String.valueOf(model.getScore())).append("\n");
      } catch (IOException f) {
        throw new IllegalStateException();
      }
    } catch (IllegalArgumentException e) {
      try {
        this.ap.append("Invalid move. Play again.\n");
      } catch (IOException f) {
        throw new IllegalStateException();
      }
    }
  }

  /**
   * Method checks to if game is over and the append appropriate message to user, if over.
   * @param model The marble solitaire model.
   * @return a true or false of if the game is over.
   * @throws IllegalStateException if cannot transmit/append output.
   */
  private boolean checkGameOver(MarbleSolitaireModel model) throws IllegalStateException {
    if (model.isGameOver()) {
      try {
        this.ap.append("Game over!\n");
        this.ap.append(model.getGameState());
        this.ap.append("\nScore: ").append(String.valueOf(model.getScore())).append("\n");
      } catch (IOException e) {
        throw new IllegalStateException("Could not transmit output\n");
      }
      return true;
    }
    return false;
  }

}




