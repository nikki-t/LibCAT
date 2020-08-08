package edu.bu.met.cs665.builder;

import edu.bu.met.cs665.patron.Address;
import edu.bu.met.cs665.patron.Patron;

/**
 * Builds a Patron.
 * MET CS 665 Design Patterns - Class Project
 * @author Nikki Tebaldi 
 * @since 2020-07-31
 */
public class ConcretePatronBuilder implements PatronBuilder {
  
  // Instance variable
  private Patron patron;
  
  /**
   * Initializes patron member attribute to a new Patron object.
   */
  public ConcretePatronBuilder() {
    this.patron = new Patron();
  }

  @Override
  public Patron getResult() {
    return patron;
  }

  @Override
  public void reset() {
    patron = new Patron();
  }

  @Override
  public void setAddress(Address address) {
    patron.setAddress(address);
  }

  @Override
  public void setEmail(String email) {
    patron.setEmail(email);
  }

  @Override
  public void setId(int id) {
    patron.setId(id);
  }

  @Override
  public void setName(String name) {
    patron.setName(name);
  }

  @Override
  public void setPhone(String phone) {
    patron.setPhone(phone);
  }

}
