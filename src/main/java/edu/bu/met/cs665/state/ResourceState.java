package edu.bu.met.cs665.state;

import edu.bu.met.cs665.patron.Patron;

/**
 * Specifies operations that need to be handle by concrete state classes.
 * MET CS 665 Design Patterns - Class Project
 * @author Nikki Tebaldi 
 * @since 2020-07-31
 */
public interface ResourceState {
  
  /**
   * Check in a resource.
   */
  void checkIn();
  
  /**
   * Check out a resource to the patron represented by the parameter.
   * @param patron Patron to check Resource out to
   */
  void checkOut(Patron patron);
  
  /**
   * Returns the name of the state.
   * @return String name of the state
   */
  String getName();
  
  /**
   * Place a hold on a resource for the patron represented by the parameter.
   * @param patron Patron to place hold for
   */
  void placeHold(Patron patron);
  
}
