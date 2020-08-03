package edu.bu.met.cs665.builder;

import edu.bu.met.cs665.product.Electronic;
import edu.bu.met.cs665.product.Person;
import edu.bu.met.cs665.product.Physical;
import edu.bu.met.cs665.product.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Uses a ResourceBuilder object to build Resources of different types.
 * MET CS 665 Design Patterns - Class Project
 * @author Nikki Tebaldi 
 * @since 2020-07-31
 */
public class Director {
  
  /**
   * Constructs and returns a Book in either electronic or physical form.
   * @param builder BookBuilder object used to create a Book object
   * @param record List of String objects that represent a record
   * @return Resource
   */
  public Resource constructBook(BookBuilder builder, List<String> record) {
    
    // Build the Book
    builder.setCity(record.get(8));
    builder.setCreator(getPerson(record.get(1)));
    // Format - test for electronic otherwise physical
    if (record.get(2).equals("electronic")) {
      builder.setFormat(new Electronic(record.get(13), record.get(14), record.get(15)));
    } else {
      builder.setFormat(new Physical(record.get(13), record.get(14), record.get(15)));
    }
    builder.setId(Integer.parseInt(record.get(0)));
    builder.setLength(Double.parseDouble(record.get(3)));
    builder.setPublisher(record.get(7));
    builder.setTitle(record.get(4));
    builder.setType(record.get(5));
    builder.setYear(Integer.parseInt(record.get(6)));
    
    // Store product of builder construction
    Resource resource = builder.getResult();
    // Reset the builder's reference to the resource
    builder.reset();
    // Return the resource
    return resource;
  }
  
  /**
   * Constructs and returns a Movie in electronic or physical form.
   * @param builder MovieBuilder object used to create a Movie object
   * @param record List of String objects that represent a record
   * @return Resource
   */
  public Resource constructMovie(MovieBuilder builder, List<String> record) {
    
    // Build the Movie
    builder.setCast(createCastList(record.get(10), record.get(11), record.get(12)));
    builder.setCreator(getPerson(record.get(1)));
    // Format - test for electronic otherwise physical
    if (record.get(2).equals("electronic")) {
      builder.setFormat(new Electronic(record.get(13), record.get(14), record.get(15)));
    } else {
      builder.setFormat(new Physical(record.get(13), record.get(14), record.get(15)));
    }
    builder.setId(Integer.parseInt(record.get(0)));
    builder.setLength(Double.parseDouble(record.get(3)));
    builder.setRating(record.get(9));
    builder.setTitle(record.get(4));
    builder.setType(record.get(5));
    builder.setYear(Integer.parseInt(record.get(6)));    
    
    // Store product of builder construction
    Resource resource = builder.getResult();
    // Reset the builder's reference to the resource
    builder.reset();
    // Return the resource
    return resource;
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
  
}
