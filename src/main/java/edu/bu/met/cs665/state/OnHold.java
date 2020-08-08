package edu.bu.met.cs665.state;

import edu.bu.met.cs665.patron.Patron;
import edu.bu.met.cs665.resource.Resource;
import java.time.LocalDateTime;
import org.apache.log4j.Logger;

/**
 * On hold state that indicates a resource is ready to be checked out by a 
 * specific patron. The item cannot be checked in but holds can be placed on 
 * the resource to be fulfilled at a future time.
 * MET CS 665 Design Patterns - Class Project
 * @author Nikki Tebaldi 
 * @since 2020-07-31
 */
public class OnHold implements ResourceState {
  
  //Class variable
  private static Logger logger = Logger.getLogger(OnHold.class);
  private static final String NAME = "On Hold";
  
  // Instance variable
  private Resource resource;
  
  /**
   * Initializes Available class member attribute to specific Resource.
   * @param resource Resource object
   */
  public OnHold(Resource resource) {
    this.resource = resource;
  }
  
  /**
   * Cannot check resource out as it is already checked in and waiting for 
   * pickup.
   */
  @Override
  public void checkIn() {
    logger.info("\nCannot check item in as it is already checked in and "
        + "waiting on pickup from patron" + resource.getHoldQueue().peek() + ".");
  }
  
  /**
   * Resource can be checked out by specific patron who is first in the Resource
   * holdQueue.
   */
  @Override
  public void checkOut(Patron patron) {
    // Check if patron can check out item
    if (patron.equals(resource.getHoldQueue().peek())) {
      // Set Patron for resource
      resource.setPatron(patron);
      
      // Set due date
      LocalDateTime currentDate = LocalDateTime.now();
      resource.setDueDate(currentDate.plusDays(7));
      
      // Add to Patron check out list
      patron.addCheckedOutResource(resource);
      logger.info("\n" + patron.getName() + "has checked out: " 
          + resource.getTitle() + ".");
      
      // Remove resource from patron hold list
      patron.checkAndRemoveHold(resource);
      
      // Remove patron from resource hold queue
      resource.removeHold();
       
      // Send Patron an email
      String message = String.format("%s has been checked out and is due back: %s.",
          resource.getTitle(), StateUtilities.getDateString(resource.getDueDate()));
      resource.getPatron().sendEmail(message);
      
      // Set to check out state
      resource.setCurrentState(resource.getCheckedOut());
      
    } else {
      logger.info("\nResource on hold for another patron; cannot check out to " 
          + patron.getName() + ".");
    }

  }
  
  public String getName() {
    return NAME;
  }
  
  /**
   * Places hold on resource for patron. Item is not available so patron will
   * have to wait to pickup/checkout.
   */
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
