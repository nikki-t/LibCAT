package edu.bu.met.cs665.product;

import java.util.List;

/**
 * Contains all attributes that make up a library resource that is a movie.
 * MET CS 665 Design Patterns - Class Project
 * @author Nikki Tebaldi 
 * @since 2020-07-31
 */
public class Movie extends Resource {
  
  // Instance variables
  private List<Person> castList;
  private String rating;
  
  /**
   * Create a String representation of the cast list.
   * @return String
   */
  private String createCastString() {
    StringBuilder sb = new StringBuilder();
    for (Person person : castList) {
      sb.append(person.toString() + "; ");
    }
    return sb.toString();
  }
  
  public List<Person> getCastList() {
    return castList;
  }
  
  public String getRating() {
    return rating;
  }
  
  public void setCastList(List<Person> castList) {
    this.castList = castList;
  }
  
  public void setRating(String rating) {
    this.rating = rating;
  }
  
  @Override
  public String toString() {
    return super.toString() + String.format("\nCast List: %s"
        + "\nRating: %s", createCastString(), rating);
  }

}
