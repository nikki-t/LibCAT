package edu.bu.met.cs665.builder;

import edu.bu.met.cs665.product.Format;
import edu.bu.met.cs665.product.Movie;
import edu.bu.met.cs665.product.Person;
import edu.bu.met.cs665.product.Resource;
import java.util.List;

/**
 * Builds a Movie Resource.
 * MET CS 665 Design Patterns - Class Project
 * @author Nikki Tebaldi 
 * @since 2020-07-31
 */
public class ConcreteMovieBuilder implements MovieBuilder {
  
  // Instance variable
  private Movie movie;
  
  /**
   * Initializes movie member attribute to Resource sub-type Movie.
   */
  public ConcreteMovieBuilder() {
    this.movie = new Movie();
  }

  @Override
  public Resource getResult() {
    return movie;
  }
  
  @Override
  public void reset() {
    this.movie = new Movie();
  }

  @Override
  public void setCast(List<Person> castList) {
    movie.setCastList(castList);
  }

  @Override
  public void setCreator(Person creator) {
    movie.setCreator(creator);
  }

  @Override
  public void setFormat(Format format) {
    movie.setFormat(format);
  }

  @Override
  public void setId(int id) {
    movie.setId(id);
  }

  @Override
  public void setLength(double length) {
    movie.setLength(length);
  }

  @Override
  public void setRating(String rating) {
    movie.setRating(rating);
  }

  @Override
  public void setTitle(String title) {
    movie.setTitle(title);
  }

  @Override
  public void setType(String type) {
    movie.setType(type);
  }

  @Override
  public void setYear(int year) {
    movie.setYear(year);
  }

}
