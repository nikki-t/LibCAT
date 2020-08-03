package edu.bu.met.cs665;

import edu.bu.met.cs665.library.LibraryCatalog;
import java.io.FileNotFoundException;
import org.apache.log4j.Logger;

/**
 * Class used to run the library catalog application.
 * MET CS 665 Design Patterns - Class Project
 * @author Nikki Tebaldi 
 * @since 2020-07-31
 */
public class Main {

  private static Logger logger = Logger.getLogger(Main.class);


  /**
   * A main method to run examples.
   *
   * @param args not used
   */
  public static void main(String[] args) {

    LibraryCatalog lc = new LibraryCatalog();
    try {
      lc.setResourceList();
      lc.displayCatalog();
    } catch (FileNotFoundException e) {
      logger.error("Could not generate catalog.");
      logger.error(e);
    }

  }
}
