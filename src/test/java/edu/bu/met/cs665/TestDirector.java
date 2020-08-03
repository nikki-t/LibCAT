package edu.bu.met.cs665;

import static org.junit.Assert.*;

import edu.bu.met.cs665.builder.ConcreteBookBuilder;
import edu.bu.met.cs665.builder.ConcreteMovieBuilder;
import edu.bu.met.cs665.builder.Director;
import edu.bu.met.cs665.product.Book;
import edu.bu.met.cs665.product.Movie;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.junit.Test;

/**
* Tests the creation of Electronic and Movie Book objects by concrete 
* MovieBuilder sub-class objects.
* MET CS 665 Design Patterns - Class Project
* @author Nikki Tebaldi 
* @since 2020-07-31
*/
public class TestDirector {
  
  Director director = new Director();
  
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
  private static List<List<String>> retrieveRecords() throws FileNotFoundException {
    List<List<String>> records = new ArrayList<>();
    
    try (Scanner scanner = new Scanner(new File("resources-test.csv"));) {
      while (scanner.hasNextLine()) {
        records.add(getRecord(scanner.nextLine()));
      }
    }
    
    return records;
  }
  
  /**
   * Tests the construction of a book by the Director class.
   * @throws FileNotFoundException 
   */
  @Test
  public void testConstructBook() throws FileNotFoundException {
    // Get a record
    List<List<String>> records = retrieveRecords();
    List<String> record = records.get(0);
    
    // Construct book using director
    Book book = (Book) director.constructBook(new ConcreteBookBuilder(), record);
    
    // Assert expected book attributes
    String expected = "\nID: 158904"
        + "\nTitle: Arcadia"
        + "\nCreator: Iain Pears"
        + "\nLength: 565.00"
        + "\nYear: 2017"
        + "\nType: book"
        + "\nFile Format: Kindle Book"
        + "\nDatabase URL: https://www.overdrive.com/media/3210644/arcadia"
        + "\nFile Size: 952.9KB"
        + "\nPublisher: Vintage Books"
        + "\nCity: New York";
    assertEquals(expected, book.toString());    
  }
  
  /**
   * Tests the construction of a Movie by the Director class.
   * @throws FileNotFoundException 
   */
  @Test
  public void testConstructMovie() throws FileNotFoundException {
    // Get a record
    List<List<String>> records = retrieveRecords();
    List<String> record = records.get(1);
    
    // Construct a movie using director
    Movie movie = (Movie) director.constructMovie(new ConcreteMovieBuilder(), record);
    
    // Assert expected movie attributes
    String expected = "\nID: 158909"
        + "\nTitle: Citizen Kane"
        + "\nCreator: Orson Welles"
        + "\nLength: 119.00"
        + "\nYear: 1941"
        + "\nType: movie"
        + "\nFormat: DVD"
        + "\nCall Number: MAIN - DVD"
        + "\nSize: 4.75in"
        + "\nCast List: Orson Welles; Joseph Cotton; Dorothy Comingore; "
        + "\nRating: PG";
    assertEquals(expected, movie.toString());    
  }

}
