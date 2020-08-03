package edu.bu.met.cs665.product;

/**
 * Contains all attributes that make up a library resource that is a book.
 * MET CS 665 Design Patterns - Class Project
 * @author Nikki Tebaldi 
 * @since 2020-07-31
 */
public class Book extends Resource {
  
  // Instance variables
  private String publisher;
  private String city;
  
  public String getCity() {
    return city;
  }
  
  public String getPublisher() {
    return publisher;
  }
  
  public void setCity(String city) {
    this.city = city;
  }
  
  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }
  
  @Override
  public String toString() {
    return super.toString() + String.format("\nPublisher: %s"
        + "\nCity: %s", publisher, city);
    
  }
  
}
