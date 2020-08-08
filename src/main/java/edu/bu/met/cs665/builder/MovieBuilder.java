package edu.bu.met.cs665.builder;

import edu.bu.met.cs665.resource.Person;
import java.util.List;

/**
 * Specifies all operations required to build a Movie.
 * MET CS 665 Design Patterns - Class Project
 * @author Nikki Tebaldi 
 * @since 2020-07-31
 */
public interface MovieBuilder extends ResourceBuilder {
  
  void setCast(List<Person> castList);
  
  void setRating(String rating);
}
