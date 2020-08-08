package edu.bu.met.cs665.resource;

/**
 * Represents a person.
 * MET CS 665 Design Patterns - Class Project
 * @author Nikki Tebaldi 
 * @since 2020-07-31
 */
public class Person {
  // Instance variables
  private String firstName;
  private String lastName;
  
  /**
   * Initializes Person's first and last name attributes.
   * @param firstName String first name
   * @param lastName String last name
   */
  public Person(String firstName, String lastName) {
    this.setFirstName(firstName);
    this.setLastName(lastName);
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  
  @Override
  public String toString() {
    return firstName + " " + lastName;
  }
}
