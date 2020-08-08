package edu.bu.met.cs665.builder;

import edu.bu.met.cs665.patron.Address;
import edu.bu.met.cs665.patron.Patron;
import edu.bu.met.cs665.resource.Electronic;
import edu.bu.met.cs665.resource.Person;
import edu.bu.met.cs665.resource.Physical;
import edu.bu.met.cs665.resource.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Uses a ResourceBuilder object to build Resources of different types.
 * MET CS 665 Design Patterns - Class Project
 * @author Nikki Tebaldi 
 * @since 2020-07-31
 */
public class Director {
  
  // Class variables
  private BookBuilder bookBuilder = new ConcreteBookBuilder();
  private MovieBuilder movieBuilder = new ConcreteMovieBuilder();
  private PatronBuilder patronBuilder = new ConcretePatronBuilder();
  
  /**
   * Constructs and returns a Book in either electronic or physical form.
   * @param record List of String objects that represent a record
   * @return Resource
   */
  public Resource constructBook(List<String> record) {
    
    // Build the Book
    bookBuilder.setCity(record.get(8));
    bookBuilder.setCreator(getPerson(record.get(1)));
    // Format - test for electronic otherwise physical
    if (record.get(2).equals("electronic")) {
      bookBuilder.setFormat(new Electronic(record.get(13), record.get(14), record.get(15)));
    } else {
      bookBuilder.setFormat(new Physical(record.get(13), record.get(14), record.get(15)));
    }
    bookBuilder.setId(Integer.parseInt(record.get(0)));
    bookBuilder.setLength(Double.parseDouble(record.get(3)));
    bookBuilder.setPublisher(record.get(7));
    bookBuilder.setTitle(record.get(4));
    bookBuilder.setType(record.get(5));
    bookBuilder.setYear(Integer.parseInt(record.get(6)));
    
    // Store resource and reset the builder
    Resource resource = bookBuilder.getResult();
    bookBuilder.reset();

    return resource;
  }
  
  /**
   * Constructs and returns a Movie in electronic or physical form.
   * @param record List of String objects that represent a record
   * @return Resource
   */
  public Resource constructMovie(List<String> record) {
    
    // Build the Movie
    movieBuilder.setCast(createCastList(record.get(10), record.get(11), record.get(12)));
    movieBuilder.setCreator(getPerson(record.get(1)));
    // Format - test for electronic otherwise physical
    if (record.get(2).equals("electronic")) {
      movieBuilder.setFormat(new Electronic(record.get(13), record.get(14), record.get(15)));
    } else {
      movieBuilder.setFormat(new Physical(record.get(13), record.get(14), record.get(15)));
    }
    movieBuilder.setId(Integer.parseInt(record.get(0)));
    movieBuilder.setLength(Double.parseDouble(record.get(3)));
    movieBuilder.setRating(record.get(9));
    movieBuilder.setTitle(record.get(4));
    movieBuilder.setType(record.get(5));
    movieBuilder.setYear(Integer.parseInt(record.get(6)));    
    
    // Store resource and reset the builder
    Resource resource = movieBuilder.getResult();
    movieBuilder.reset();
    
    return resource;
  }
  
  /**
   * Constructs and returns a Patron.
   * @param record List of String objects that represent a record
   * @return Patron
   */
  public Patron constructPatron(List<String> record) {
    
    // Build the Patron
    patronBuilder.setAddress(new Address(record.get(0), record.get(1), record.get(2), 
        record.get(3)));
    patronBuilder.setEmail(record.get(4));
    patronBuilder.setId(Integer.parseInt(record.get(5)));
    patronBuilder.setName(record.get(6));
    patronBuilder.setPhone(record.get(7));
    
    // Store the Patron object and reset the builder
    Patron patron = patronBuilder.getResult();
    patronBuilder.reset();
    
    return patron;
  }
  
  /**
   * Create and return a cast list of Person objects.
   * @param cast1 String cast member 1
   * @param cast2 String cast member 2
   * @param cast3 String cast member 3
   * @return List of Person objects
   */
  private static List<Person> createCastList(String cast1, String cast2, String cast3) {
    List<Person> castList = new ArrayList<>();
    castList.add(getPerson(cast1));
    castList.add(getPerson(cast2));
    castList.add(getPerson(cast3));
    
    return castList;
  }
  
  /**
   * Splits a person's full name and creates a Person object.
   * @param fullName String representation of a person's full name
   * @return Person object
   */
  private static Person getPerson(String fullName) {
    String[] splitName = fullName.split("\\s+");
    return new Person(splitName[0], splitName[1]);
  }
  
  public void setBookBuilder(BookBuilder bb) {
    this.bookBuilder = bb;
  }
  
  public void setMovieBuilder(MovieBuilder mb) {
    this.movieBuilder = mb;
  }
  
  public void setPatronBuilder(PatronBuilder pb) {
    this.patronBuilder = pb;
  }
  
}
