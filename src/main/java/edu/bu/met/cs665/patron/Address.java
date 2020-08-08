package edu.bu.met.cs665.patron;

/**
 * Represents an address.
 * MET CS 665 Design Patterns - Assignment 4
 * @author Nikki Tebaldi 
 * @since 2020-07-27
 */
public class Address {
  
  //Instance variables
  private String city;
  private String state;
  private String street;
  private String zip;
  
  /**
   * Initializes all Address properties.
   * @param city String
   * @param state String
   * @param street String
   * @param zip String
   */
  public Address(String city, String state, String street, String zip) {
    this.city = city;
    this.state = state;
    this.street = street;
    this.zip = zip;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    
    if (obj == null) {
      return false;
    }
    
    if (getClass() != obj.getClass()) {
      return false;
    }
    
    Address other = (Address) obj;
    if (city == null) {
      if (other.city != null) {
        return false;
      }
    } else if (!city.equals(other.city)) {
      return false;
    }
   
    if (state == null) {
      if (other.state != null) {
        return false;
      }
    } else if (!state.equals(other.state)) {
      return false;
    }
    
    if (street == null) {
      if (other.street != null) {
        return false;
      }
    } else if (!street.equals(other.street)) {
      return false;
    }
   
    if (zip == null) {
      if (other.zip != null) {
        return false;
      }
    } else if (!zip.equals(other.zip)) {
      return false;
    }
    
    return true;
  }
  
  public String getCity() {
    return city;
  }
  
  public String getState() {
    return state;
  }
  
  public String getStreet() {
    return street;
  }
  
  public String getZip() {
    return zip;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((city == null) ? 0 : city.hashCode());
    result = prime * result + ((state == null) ? 0 : state.hashCode());
    result = prime * result + ((street == null) ? 0 : street.hashCode());
    result = prime * result + ((zip == null) ? 0 : zip.hashCode());
    return result;
  }

  /*
   * Creates a String representation of all address attributes.
   */
  @Override
  public String toString() {
    return String.format("Street: %s, City: %s, State: %s, Zip: %s",
        street,city, state, zip);
  }

}