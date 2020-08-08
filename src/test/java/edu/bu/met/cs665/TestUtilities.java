package edu.bu.met.cs665;

import edu.bu.met.cs665.resource.Person;

public class TestUtilities {
  
  /**
   * Splits a person's full name and creates a Person object.
   * @param fullName String representation of a person's full name
   * @return Person object
   */
  public static Person getPerson(String fullName) {
    String[] splitName = fullName.split("\\s+");
    return new Person(splitName[0], splitName[1]);
  }

}
