package edu.bu.met.cs665.state;

import edu.bu.met.cs665.patron.Patron;
import edu.bu.met.cs665.resource.Resource;
import org.apache.log4j.Logger;

/**
 * Checked out state that indicates a resource is checked out. Resource can
 * be checked in, resource cannot be checked out because it already is, and 
 * holds can still be placed on item while it is checked out.
 * MET CS 665 Design Patterns - Class Project
 * @author Nikki Tebaldi 
 * @since 2020-07-31
 */
public class CheckedOut implements ResourceState {
  
  //Class variable
  private static Logger logger = Logger.getLogger(CheckedOut.class);
  private static final String NAME = "Checked Out";
  
  // Instance variable
  private Resource resource;
  
  /**
   * Initializes Available class member attribute to specific Resource.
   * @param resource Resource object
   */
  public CheckedOut(Resource resource) {
    this.resource = resource;
  }

  @Override
  public void checkIn() {
    // Remove resource from Patron's check out list
    resource.getPatron().removeFromCheckOut(resource);
    
    // Remove Patron from Resource and remove due date
    resource.setPatron(null);
    resource.setDueDate(null);
    logger.info("\n" + resource.getTitle() + " has been checked in.");
    
    // Check if there is a hold queue
    if (!(resource.getHoldQueue().isEmpty())) {
      // Notify patron hold is ready to be picked up
      Patron patron = resource.getHoldQueue().peek();
      String message = String.format("%s is ready for pick up.", resource.getTitle());
      patron.sendEmail(message);
          
      // Set to on hold state
      resource.setCurrentState(resource.getOnHold());
    
    } else {
      // Set to available state
      logger.info("\nNo holds on item, setting to available.");
      resource.setCurrentState(resource.getAvailable());
    }
  }
  
  /**
   * Resource is already checked out.
   */
  @Override
  public void checkOut(Patron patron) {
    logger.info("\nCannot check out " + resource.getTitle() 
        + " to: " + patron.getName()
        + "\n\tResource is already checked out to " 
        + resource.getPatron().getName() + ".");
  }
  
  public String getName() {
    return NAME;
  }

  @Override
  public void placeHold(Patron patron) {
    // Add to Resource queue and Patron hold list
    if (resource.addHold(patron) &&  patron.addHold(resource)) {
      logger.info("\n" + patron.getName() + "has placed a hold on: " 
          + resource.getTitle() + ".");
      
    } else {
      logger.info("\nItem is already on hold."
          + "\nCould not place hold on: " + resource.getTitle() 
          + "\nFor: " + patron.getName() + ".");
    }
    
  }

}
