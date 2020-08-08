package edu.bu.met.cs665.state;

import edu.bu.met.cs665.patron.Patron;
import edu.bu.met.cs665.resource.Resource;
import java.time.LocalDateTime;
import org.apache.log4j.Logger;

/**
 * Available state that indicates a hold can be placed on a resource or it can
 * be checked out.
 * MET CS 665 Design Patterns - Class Project
 * @author Nikki Tebaldi 
 * @since 2020-07-31
 */
public class Available implements ResourceState {
  
  //Class variable
  private static Logger logger = Logger.getLogger(Available.class);
  private static final String NAME = "Available";
  
  // Instance variable
  private Resource resource;
  
  /**
   * Initializes Available class member attribute to specific Resource.
   * @param resource Resource object
   */
  public Available(Resource resource) {
    this.resource = resource;
  }
  
  /**
   * Can't check item in as it is already available.
   */
  @Override
  public void checkIn() {
    logger.info("\nResource is already checked in: " + resource.getTitle());
  }
  
  /**
   * Checks item out to Patron.
   */
  @Override
  public void checkOut(Patron patron) {
    // Set Patron for resource
    resource.setPatron(patron);
    
    // Set due date
    LocalDateTime currentDate = LocalDateTime.now();
    resource.setDueDate(currentDate.plusDays(7));
    
    // Add to Patron check out list
    patron.addCheckedOutResource(resource);
    logger.info("\n" + patron.getName() + "has checked out: " 
        + resource.getTitle() + ".");
    
    // Send Patron an email
    String message = String.format("%s has been checked out and is due back: %s.",
        resource.getTitle(), StateUtilities.getDateString(resource.getDueDate()));
    patron.sendEmail(message);
    
    // Set to check out state
    resource.setCurrentState(resource.getCheckedOut());

  }
  
  public String getName() {
    return NAME;
  }
 
  /**
   * Places a hold on the item for the Patron. Item is available so no need to 
   * check for holds and patron can pickup/checkout resource.
   */
  @Override
  public void placeHold(Patron patron) {
    // Add to Resource queue and Patron hold list
    if (resource.addHold(patron) &&  patron.addHold(resource)) {
      logger.info("\n" + patron.getName() + "has placed a hold on: " 
          + resource.getTitle() + ".");
      
      // Email to pickup
      String message = String.format("%s is ready to pickup.", resource.getTitle());
      patron.sendEmail(message);

      // Set to on hold state
      resource.setCurrentState(resource.getOnHold());
      
    } else {
      logger.info("\nItem is already on hold."
          + "\nCould not place hold on: " + resource.getTitle() 
          + "\nFor: " + patron.getName() + ".");
    }

  }

}
