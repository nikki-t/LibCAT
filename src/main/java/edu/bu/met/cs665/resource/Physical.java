package edu.bu.met.cs665.resource;

/**
 * Represents a physical resource that exists inside of the library building.
 * MET CS 665 Design Patterns - Class Project
 * @author Nikki Tebaldi 
 * @since 2020-07-31
 */
public class Physical implements Format {
  
  // Instance variables
  private String format;
  private String location;
  private String size;
  
  /**
   * Initializes all Physical Resource attributes.
   * @param format String that represents physical format (i.e. large print)
   * @param location String call number of resource
   * @param size String physical size of resource
   */
  public Physical(String format, String location, String size) {
    this.format = format;
    this.location = location;
    this.size = size;
  }
  
  @Override
  public String getResourceFormat() {
    return format;
  }
  
  @Override
  public String getLocation() {
    return location;
  }

  @Override
  public String getSize() {
    return size;
  }
  
  @Override
  public String toString() {
    return String.format("%nFormat: %s"
        + "%nCall Number: %s"
        + "%nSize: %s", format, location, size);
  }

}
