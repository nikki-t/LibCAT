package edu.bu.met.cs665;

import edu.bu.met.cs665.library.LibraryCatalog;
import edu.bu.met.cs665.patron.Patron;
import edu.bu.met.cs665.resource.Resource;
import java.io.FileNotFoundException;
import org.apache.log4j.Logger;

/**
 * Class used to run the library catalog application.
 * MET CS 665 Design Patterns - Class Project
 * @author Nikki Tebaldi 
 * @since 2020-07-31
 */
public class Main {
  
  // Class variables
  private static Logger logger = Logger.getLogger(Main.class);
  private static LibraryCatalog lc;
  private static Patron patron1;
  private static Patron patron2;
  private static Patron patron3;
  private static Resource resource1;
  private static Resource resource2;
  
  /**
   * Create a library.
   */
  private static boolean populateLibrary() {
    lc = new LibraryCatalog();
    try {
      // Get Library data
      lc.retrievePatrons();
      lc.populateCatalog();
      
      // Find some library patrons and resources
      patron1 = lc.findPatron(1001);
      patron2 = lc.findPatron(1002);
      patron3 = lc.findPatron(1003);
      
      resource1 = lc.findResource(158906);
      resource2 = lc.findResource(158910);
      
      return true;
      
    } catch (FileNotFoundException e) {
      logger.error("Could not generate catalog.");
      logger.error(e);
    }
    
    return false;
    
  }


  /**
   * A main method that runs the library catalog and simulates patrons
   * checking resources in and out while also placing holds on resources.
   * @param args not used
   */
  public static void main(String[] args) {
    
    // Simulate the check in, check out, and hold processes for patrons and resources   
    if (populateLibrary()) {
      
      logger.info("\n====Patron1 checks in and then checks out resource1====");
      resource1.checkOut(patron1);
      resource1.checkIn();
      
      logger.info("\n====Patron1 places hold on resource1====");
      resource1.placeHold(patron1);
      
      logger.info("\n====Patron2 tries to check resource1 out====");
      resource1.checkOut(patron2);
      
      logger.info("\n====Patron1 picks up and checks out resource1====");
      resource1.checkOut(patron1);
      
      logger.info("\n====Patron2 places a hold on resource1 and checks out resource2====");
      resource1.placeHold(patron2);
      resource2.checkOut(patron2);
      
      logger.info("\n====Patron3 tries to check out resource1====");
      resource1.checkOut(patron3);
      
      logger.info("\n====Patron3 places a hold on both resource 1 and 2====");
      resource1.placeHold(patron3);
      resource2.placeHold(patron3);
      
      logger.info("\n====Patron3 tries to place another hold on resource2====");
      resource2.placeHold(patron3);
      
      logger.info("\n====Patron1 checks in resource1====");
      resource1.checkIn();
      
      logger.info("\n====Patron2 checks out resource1 and checks in resource2====");
      resource1.checkOut(patron2);
      resource2.checkIn();
      
      logger.info("\n====Patron3 checkouts resource2====");
      resource2.checkOut(patron3);
      
      logger.info("\n====Patron2 checks in resource1====");
      resource1.checkIn();
      
      logger.info("\n====Patron3 checks in resource2 and checks out resource1====");
      resource2.checkIn();
      resource1.checkOut(patron3);
      
      logger.info("\n====Patron3 checks in resource1====");
      resource1.checkIn();
      
      // All items should be checked in
      logger.info("\n====All items are in====");
      logger.info("\n\tResource1: " + resource1.getCurrentState().getName());
      logger.info("\n\tResource2: " + resource2.getCurrentState().getName());
    }
    
  }
  
}
