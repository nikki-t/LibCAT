package edu.bu.met.cs665.product;

/**
 * Contains all attributes that make up a library resource.
 * MET CS 665 Design Patterns - Class Project
 * @author Nikki Tebaldi 
 * @since 2020-07-31
 */
public abstract class Resource {
  
  // Instance variables
  private Person creator;
  private Format format;
  private int id;
  private double length;
  private String title;
  private String type;
  private int year;
  
  public Person getCreator() {
    return creator;
  }
  
  public Format getFormat() {
    return format;
  }
  
  public int getId() {
    return id;
  }
  
  public double getLength() {
    return length;
  }
  
  public String getTitle() {
    return title;
  }
  
  public String getType() {
    return type;
  }
  
  public int getYear() {
    return year;
  }
  
  public void setCreator(Person creator) {
    this.creator = creator;
  }
  
  public void setFormat(Format format) {
    this.format = format;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public void setLength(double length) {
    this.length = length;
  }
  
  public void setTitle(String title) {
    this.title = title;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setYear(int year) {
    this.year = year;
  }
  
  @Override
  public String toString() {
    return String.format("\nID: %d"
        + "\nTitle: %s"
        + "\nCreator: %s"
        + "\nLength: %.2f"
        + "\nYear: %d"
        + "\nType: %s"
        + "%s", 
        id, title, creator, length, year, type, format); 
  }
    
}
