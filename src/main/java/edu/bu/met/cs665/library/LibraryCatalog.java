package edu.bu.met.cs665.library;

import edu.bu.met.cs665.builder.BookBuilder;
import edu.bu.met.cs665.builder.ConcreteBookBuilder;
import edu.bu.met.cs665.builder.ConcreteMovieBuilder;
import edu.bu.met.cs665.builder.Director;
import edu.bu.met.cs665.builder.MovieBuilder;
import edu.bu.met.cs665.product.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a library catalog that contains records on movies and books.
 * CSV methods adapted from: https://www.baeldung.com/java-csv-file-array.
 * MET CS 665 Design Patterns - Class Project
 * @author Nikki Tebaldi 
 * @since 2020-07-31
 */
public class LibraryCatalog {
  
  // Instance variables
  BookBuilder bookBuilder;
  Director director;
  MovieBuilder movieBuilder;
  List<Resource> resourceList;
  
  /**
   * Initializes all attributes of the Library catalog.
   */
  public LibraryCatalog() {
    this.bookBuilder = new ConcreteBookBuilder();
    this.director = new Director();
    this.movieBuilder = new ConcreteMovieBuilder();
    this.resourceList = new ArrayList<>();
  }
  
  /**
   * Creates and sets a List of Resource objects for the resourceList attribute
   * via the Director object which uses a ResourceBuilder object and a List
   * of String objects. 
   * @param records List of String objects
   */
  private void createResourceList(List<List<String>> records) {
    for (List<String> record : records) {
      
      if (record.get(5).equals("book")) {
        resourceList.add(director.constructBook(bookBuilder, record));
      // Record is for a movie
      } else {
        resourceList.add(director.constructMovie(movieBuilder, record));
      }
    }
  }
  
  /**
   * Display all the titles of resources present in the library catalog.
   */
  public void displayCatalog() {
    for (Resource resource : resourceList) {
      System.out.println(resource);
    }
  }
  
  public List<Resource> getResourceList() {
    return resourceList;
  }
  
  /**
   * Sets the resourceList which is a list of resources that belong to the 
   * library.
   * @throws FileNotFoundException when csv file cannot be located
   */
  public void setResourceList() throws FileNotFoundException {
    List<List<String>> records = retrieveData();
    createResourceList(records);
  }
  
  /**
   * Creates a record from the String parameter.
   * @param row String row from the database
   * @return List of String objects.
   */
  private static List<String> getRecord(String row) {
    List<String> record = new ArrayList<>();
    
    try (Scanner rowScanner = new Scanner(row)) {
      rowScanner.useDelimiter(",");
      while (rowScanner.hasNext()) {
        record.add(rowScanner.next());
      }
    }
    
    return record;
  }
  
  /**
   * Create a List of records from 'resources.csv' file to simulate the 
   * retrieval of data from a database.
   * @return List of List of String objects
   * @throws FileNotFoundException when csv file cannot be located
   */
  private static List<List<String>> retrieveData() throws FileNotFoundException {
    List<List<String>> records = new ArrayList<>();
    
    try (Scanner scanner = new Scanner(new File("resources.csv"));) {
      while (scanner.hasNextLine()) {
        records.add(getRecord(scanner.nextLine()));
      }
    }
    
    return records;
  }
  
}
