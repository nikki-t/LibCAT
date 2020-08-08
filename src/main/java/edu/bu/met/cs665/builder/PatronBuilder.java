package edu.bu.met.cs665.builder;

import edu.bu.met.cs665.patron.Address;
import edu.bu.met.cs665.patron.Patron;

/**
 * Specifies all operations required to build a Patron.
 * MET CS 665 Design Patterns - Class Project
 * @author Nikki Tebaldi 
 * @since 2020-07-31
 */
public interface PatronBuilder {
  
  /**
   * Returns a Patron that has been built.
   * @return
   */
  Patron getResult();
  
  /**
   * Resets the member attribute reference of the Patron that was built.
   */
  void reset();
  
  /**
   * Sets the Patron's address.
   * @param address Address that represents Patron's address
   */
  void setAddress(Address address);
  
  /**
   * Sets the Patron's email address.
   * @param email String email address of patron
   */
  void setEmail(String email);
  
  /**
   * Sets the Patron's unique identifier in the library database.
   * @param id int identifier of patron
   */
  void setId(int id);
  
  /**
   * Sets the name of the Patron.
   * @param name String name of the patron
   */
  void setName(String name);
  
  /**
   * Sets the Patron's phone number.
   * @param phone String phone number of the patron
   */
  void setPhone(String phone);
  
}
