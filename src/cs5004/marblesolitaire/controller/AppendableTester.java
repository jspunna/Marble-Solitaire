package cs5004.marblesolitaire.controller;

import java.io.IOException;

/**
 * This class AppendableTester implements the Appendable Interface.
 * This class is created for testing purposes to see if the Appendable can/will throw IOException.
 * Test conducted in MarbleSolitaireControllerImplTest under "public void appendableException1()".
 * Within the controller, this IOException will be caught & an IllegalStateException will be thrown.
 */
public class AppendableTester implements Appendable {

  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("for testing purposes");
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("for testing purposes");
  }

  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("for testing purposes");
  }
}
