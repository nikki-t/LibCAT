package edu.bu.met.cs665.builder;

public interface BookBuilder extends ResourceBuilder {
  
  /**
   * Set the city the book was published in.
   * @param city String city the book was published in
   */
  void setCity(String city);
  
  /**
   * Set the publisher of the book.
   * @param publisher String publisher of the book.
   */
  void setPublisher(String publisher);

}
