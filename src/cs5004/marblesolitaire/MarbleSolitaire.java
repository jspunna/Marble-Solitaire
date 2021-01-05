package cs5004.marblesolitaire;

import java.io.InputStreamReader;

import cs5004.marblesolitaire.controller.MarbleSolitaireController;
import cs5004.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs5004.marblesolitaire.model.MarbleSolitaireModel;
import cs5004.marblesolitaire.model.MarbleSolitaireModelImpl;
import cs5004.marblesolitaire.model.hw08.EuropeanSolitaireModelImpl;
import cs5004.marblesolitaire.model.hw08.TriangleSolitaireModelImpl;

/**
 * This class Marble Solitaire contains the main method.
 * The main method help pick the model for the controller,
 * based on the command-line arguments passed in.
 * english -size 6, triangular, european -hole 1 4, triangular -size 4 -hole 2 3 are all considered
 * valid argument formats.
 */
public final class MarbleSolitaire {

  /**
   * The main method takes in the command line arguments and chooses the model.
   * @param args command-line arguments.
   */
  public static void main(String[] args) {

    String boardType = "";
    int size = 3;
    int triSize = 5;
    String row = "";
    String col = "";
    int triRow = 0;
    int triCol = 0;

    // Go through arguments and parse. Change variable initialized if needed.
    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("english") || args[i].equals("european") || args[i].equals("triangular")) {
        boardType = args[i];
      }
      if (args[i].equals("-size") && !boardType.equals("triangular")) {
        size = Integer.parseInt(args[i + 1]);
      }
      if (args[i].equals("-size") && boardType.equals("triangular")) {
        triSize = Integer.parseInt(args[i + 1]);
      }
      if (args[i].equals("-hole") && !boardType.equals("triangular")) {
        row = args[i + 1];
        col = args[i + 2];
      }
      if (args[i].equals("-hole") && boardType.equals("triangular")) {
        triRow = Integer.parseInt(args[i + 1]);
        triCol = Integer.parseInt(args[i + 2]);
      }
    }

    // For European/English board, if no arguments, then calculate the center empty spot.
    int emptyRow = 0;
    int emptyCol = 0;
    if (row.equals("") && col.equals("")) {
      int width = (size * 3) - 2;
      emptyRow = (width - 1) / 2;
      emptyCol = (width - 1) / 2;
    } else {
      emptyRow = Integer.parseInt(row);
      emptyCol = Integer.parseInt(col);
    }

    // Choose correct model based on inputs.
    MarbleSolitaireModel model = null;
    if (boardType.equals("english")) {
      model = new MarbleSolitaireModelImpl(size, emptyRow, emptyCol);
    } else if (boardType.equals("european")) {
      model = new EuropeanSolitaireModelImpl(size, emptyRow, emptyCol);
    } else if (boardType.equals("triangular")) {
      model = new TriangleSolitaireModelImpl(triSize, triRow, triCol);
    }


    // Pass model into controller to play game.
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(
            new InputStreamReader(System.in), System.out);
    controller.playGame(model);
  }
}
