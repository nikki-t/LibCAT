package edu.bu.met.cs665.state;

import java.time.LocalDateTime;

/**
 * Contains static methods that are helpful to state classes.
 * MET CS 665 Design Patterns - Class Project
 * @author Nikki Tebaldi 
 * @since 2020-07-31
 */
public class StateUtilities {
  
  /**
   * Returns a String representation of the LocalDateTime object.
   * @param ldt LocalDateTime object
   * @return String representation of the date
   */
  public static String getDateString(LocalDateTime ldt) {
    return ldt.getMonth() + ", " + ldt.getDayOfMonth() + " " + ldt.getYear();
  }

}
