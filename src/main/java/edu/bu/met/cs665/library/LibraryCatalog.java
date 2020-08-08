package edu.bu.met.cs665.library;

import edu.bu.met.cs665.builder.Director;
import edu.bu.met.cs665.database.Database;
import edu.bu.met.cs665.patron.Patron;
import edu.bu.met.cs665.resource.Resource;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Represents a library catalog that contains records on movies and books.
 * CSV methods adapted from: https://www.baeldung.com/java-csv-file-array.
 * MET CS 665 Design Patterns - Class Project
 * @author Nikki Tebaldi 
 * @since 2020-07-31
 */
public class LibraryCatalog {
  
  // Class variable
  private static Logger logger = Logger.getLogger(LibraryCatalog.class);
  
  // Instance variables
  List<Resource> catalog;
  Director director;
  List<Patron> patronList;
  
  
  /**
   * Initializes all attributes of the Library catalog.
   */
  public LibraryCatalog() {
    this.catalog = new ArrayList<>();
    this.director = new Director();
    this.patronList = new ArrayList<>(); 
  }
  
  /**
   * Creates and sets a List of Patron objects for the patronList attribute
   * using the director and a record. 
   * @param records List of a List of String objects
   */
  private void createPatronList(List<List<String>> records) {
    for (List<String> record : records) {
      patronList.add(director.constructPatron(record));
    }
  }
  
  /**
   * Creates and sets a List of Resource objects for the catalog attribute
   * using the director and a record. 
   * @param records List of a List of String objects
   */
  private void createResourceList(List<List<String>> records) {
    for (List<String> record : records) {
      
      if (record.get(5).equals("book")) {
        catalog.add(director.constructBook(record));
      // Record is for a movie
      } else {
        catalog.add(director.constructMovie(record));
      }
    }
  }
  
  /**
   * Display (logs) all the titles of resources present in the library catalog.
   */
  public void displayCatalog() {
    for (Resource resource : catalog) {
      logger.info(resource);
    }
  }
  
  /**
   * Display (logs) all the titles of resources present in the library catalog.
   */
  public void displayPatrons() {
    for (Patron patron : patronList) {
      logger.info(patron);
    }
  }
  
  /**
   * Searches for patron in the catalog list by integer ID parameter. Returns
   * null if the patron cannot be found.
   * @param id int id of resource to find
   * @return Resource or null
   */
  public Patron findPatron(int id) {
    for (Patron patron : patronList) {
      if (patron.getId() == id) {
        return patron;
      }
    }
    return null;
  }
  
  /**
   * Searches for resource in the catalog list by integer ID parameter. Returns
   * null if the resource cannot be found.
   * @param id int id of resource to find
   * @return Resource or null
   */
  public Resource findResource(int id) {
    for (Resource resource : catalog) {
      if (resource.getId() == id) {
        return resource;
      }
    }
    return null;
  }
  
  public List<Patron> getPatronList() {
    return patronList;
  }
  
  public List<Resource> getResourceList() {
    return catalog;
  }
  
  /**
   * Populates the catalog attribute which is a list of resources that belong to
   * the library.
   * @throws FileNotFoundException when csv file cannot be located
   */
  public void populateCatalog() throws FileNotFoundException {
    String dbFile = "src/main/java/edu/bu/met/cs665/database/resources.csv";
    List<List<String>> records = Database.retrieveRecords(dbFile);
    createResourceList(records);
  }
  
  /**
   * Populates the patronList with a list of patrons that belong to the library 
   * by retrieving a list of patrons from the database.
   * @throws FileNotFoundException when csv file cannot be located
   */
  public void retrievePatrons() throws FileNotFoundException {
    String dbFile = "src/main/java/edu/bu/met/cs665/database/patron.csv";
    List<List<String>> records = Database.retrieveRecords(dbFile);
    createPatronList(records);
  }
  
}
