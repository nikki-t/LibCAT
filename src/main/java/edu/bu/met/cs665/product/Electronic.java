package edu.bu.met.cs665.product;

/**
 * Represents an electronic resource that exists in on of the databases
 * the library subscribes to.
 * MET CS 665 Design Patterns - Class Project
 * @author Nikki Tebaldi 
 * @since 2020-07-31
 */
public class Electronic implements Format {
  
  //Instance variables
  private String format;
  private String location;
  private String size;
 
  /**
   * Initializes all Physical Resource attributes.
   * @param format String file format of the resource
   * @param location String URL of the resource
   * @param size String file size of the resource
   */
  public Electronic(String format, String location, String size) {
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
    return String.format("\nFile Format: %s"
        + "\nDatabase URL: %s"
        + "\nFile Size: %s", format, location, size);
  }

}
