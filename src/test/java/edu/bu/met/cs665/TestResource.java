package edu.bu.met.cs665;

import static org.junit.Assert.*;

import edu.bu.met.cs665.patron.Address;
import edu.bu.met.cs665.patron.Patron;
import edu.bu.met.cs665.resource.Book;
import edu.bu.met.cs665.resource.Electronic;
import edu.bu.met.cs665.resource.Movie;
import edu.bu.met.cs665.resource.Person;
import edu.bu.met.cs665.resource.Physical;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * Tests the creation of Book and Movie objects which are Resource sub-types.
 * MET CS 665 Design Patterns - Class Project
 * @author Nikki Tebaldi 
 * @since 2020-07-31
 */
public class TestResource {
  
  /**
   * Sets all Resource and Book attributes of the Resource parameter.
   * @param resource Resource object
   */
  private static void setBookAttributes(Book book) {
    book.setCity("New York");
    book.setCreator(TestUtilities.getPerson("Iain Pears"));
    book.setFormat(new Physical("regular print", "PR6066.E167 A89 2016", "25cm"));
    book.setId(158903);
    book.setLength(565);
    book.setPublisher("Vintage Books");
    book.setTitle("Arcadia");
    book.setType("book");
    book.setYear(2017);    
  }
  
  /**
   * Sets all Resource and Movie attributes of the Resource parameter.
   * @param resource Resource object
   */
  private static void setMovieAttributes(Movie movie) {
    // Create cast list
    List<Person> castList = new ArrayList<Person>() {
      private static final long serialVersionUID = 1L;

      {
        add(TestUtilities.getPerson("Orson Welles"));
        add(TestUtilities.getPerson("Joseph Cotton"));
        add(TestUtilities.getPerson("Dorothy Comingore"));
      }
    };
    movie.setCastList(castList);
    movie.setCreator(TestUtilities.getPerson("Orson Welles"));
    movie.setFormat(new Electronic("stream", 
        "https://libraryfilms.net/citizen-kane", "20GB"));
    movie.setId(158910);
    movie.setLength(119);
    movie.setRating("PG");
    movie.setTitle("Citizen Kane");
    movie.setType("movie");
    movie.setYear(1941);    
  }
  
  /**
   * Tests the creation of a Book object.
   */
  @Test
  public void testBook() {
    // Create and set book attributes
    Book book = new Book();
    setBookAttributes(book);
    
    // Assert expected book attributes
    String expected = "\nID: 158903"
        + "\nTitle: Arcadia"
        + "\nCreator: Iain Pears"
        + "\nLength: 565.00"
        + "\nYear: 2017"
        + "\nType: book"
        + "\nFormat: regular print"
        + "\nCall Number: PR6066.E167 A89 2016"
        + "\nSize: 25cm"
        + "\nPublisher: Vintage Books"
        + "\nCity: New York";
    assertEquals(expected, book.toString());    
  }
  
  /**
   * Tests the creation of a Movie object.
   */
  @Test
  public void TestMovie() {
    // Create and set movie attributes
    Movie movie = new Movie();
    setMovieAttributes(movie);
    
    // Assert expected movie attributes
    String expected = "\nID: 158910"
        + "\nTitle: Citizen Kane"
        + "\nCreator: Orson Welles"
        + "\nLength: 119.00"
        + "\nYear: 1941"
        + "\nType: movie"
        + "\nFile Format: stream"
        + "\nDatabase URL: https://libraryfilms.net/citizen-kane"
        + "\nFile Size: 20GB"
        + "\nCast List: Orson Welles; Joseph Cotton; Dorothy Comingore; "
        + "\nRating: PG";
    assertEquals(expected, movie.toString());
  }
  
  /**
   * Tests the resetHoldQueue method of the Resource class.
   */
  @Test
  public void testResetHoldQueue() {
    // Create a Patron
    Patron patron = new Patron();
    patron.setAddress(new Address("Wichita", "KS", "449 Northfield Hill", "67260"));
    patron.setEmail("jquant0@liveinternet.ru");
    patron.setId(1001);
    patron.setName("Jordana Quant");
    patron.setPhone("316-844-1784");
    
     // Create a Resource
    Book book = new Book();
    setBookAttributes(book);
    
    // Add a hold and then reset hold queue
    book.addHold(patron);
    book.resetHoldQueue();
    
    assertEquals(0, book.getHoldQueue().size());     
  }
  
  /**
   * Tests the resetState method of the Resource class.
   */
  @Test
  public void testResetState() {
    // Create a Resource
    Book book = new Book();
    setBookAttributes(book);
    
    // Set the state to checked out
    book.setCurrentState(book.getCheckedOut());
    assertEquals("Checked Out", book.getCurrentState().getName());
    
    // Reset state
    book.resetState();
    assertEquals("Available", book.getCurrentState().getName());
  }

}
