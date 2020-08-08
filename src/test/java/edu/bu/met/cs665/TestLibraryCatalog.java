package edu.bu.met.cs665;

import static org.junit.Assert.*;

import edu.bu.met.cs665.library.LibraryCatalog;
import edu.bu.met.cs665.patron.Patron;
import edu.bu.met.cs665.resource.Book;
import edu.bu.met.cs665.resource.Movie;
import edu.bu.met.cs665.resource.Resource;
import java.io.FileNotFoundException;
import org.junit.Test;

/**
* Tests the findResource, populateCatalog and retrievePatrons methods of 
* LibraryCatalog class.
* MET CS 665 Design Patterns - Class Project
* @author Nikki Tebaldi 
* @since 2020-07-31
*/
public class TestLibraryCatalog {
  
  /**
   * Tests the findPatron method that finds a patron.
   * @throws FileNotFoundException 
   */
  @Test
  public void testFindPatronTrue() throws FileNotFoundException {
    LibraryCatalog lc = new LibraryCatalog();
    lc.retrievePatrons();
    Patron patron = lc.findPatron(1001);
    String expected = "\nID: 1001"
        + "\nName: Jordana Quant"
        + "\nPhone: 316-844-1784"
        + "\nEmail: jquant0@liveinternet.ru"
        + "\nAddress: Street: 449 Northfield Hill, City: Wichita, "
        + "State: KS, Zip: 67260";
    assertEquals(expected, patron.toString());    
  }
  
  /**
   * Tests the findPatron method that does not find a patron.
   * @throws FileNotFoundException 
   */
  @Test
  public void testFindPatronFalse() throws FileNotFoundException {
    LibraryCatalog lc = new LibraryCatalog();
    lc.retrievePatrons();
    Patron patron = lc.findPatron(2001);
    assertNull(patron);    
  }
  
  /**
   * Tests the findResource method that finds a resource.
   * @throws FileNotFoundException 
   */
  @Test
  public void testFindResourceTrue() throws FileNotFoundException {
    LibraryCatalog lc = new LibraryCatalog();
    lc.populateCatalog();
    Resource resource = lc.findResource(158904);
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
    assertEquals(expected, resource.toString());    
  }
  
  /**
   * Tests the findResource method that does not find a resource.
   * @throws FileNotFoundException 
   */
  @Test
  public void testFindResourceFalse() throws FileNotFoundException {
    LibraryCatalog lc = new LibraryCatalog();
    lc.populateCatalog();
    Resource resource = lc.findResource(130012);
    assertNull(resource);    
  }
  
  /**
   * Tests the populateCatalog method.
   * @throws FileNotFoundException
   */
  @Test
  public void testPopulateCatalog() throws FileNotFoundException {
    // Create new Library catalog and run method
    LibraryCatalog lc = new LibraryCatalog();
    lc.populateCatalog();
    
    // Assert size of resource list
    assertEquals(8, lc.getResourceList().size());
    
    // Assert book resource
    Book book = (Book) lc.getResourceList().get(5);
    // Assert expected book attributes
    String expectedBook = "\nID: 158904"
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
    assertEquals(expectedBook, book.toString());
    
    // Assert movie resource
    Movie movie = (Movie) lc.getResourceList().get(2);
    String expectedMovie = "\nID: 158909"
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
    assertEquals(expectedMovie, movie.toString());
  }
  
  /**
   * Tests the retrievePatrons method.
   * @throws FileNotFoundException
   */
  @Test
  public void testRetrievePatrons() throws FileNotFoundException {
    // Create new Library catalog and run method
    LibraryCatalog lc = new LibraryCatalog();
    lc.retrievePatrons();
    
    // Assert size of patron list
    assertEquals(10, lc.getPatronList().size());
    
    // Assert Patron resource
    Patron patron = lc.getPatronList().get(5);
    String expected = "\nID: 1006"
        + "\nName: Neile Von Oertzen"
        + "\nPhone: 336-702-7619"
        + "\nEmail: nvonoertzen5@kickstarter.com"
        + "\nAddress: Street: 269 Schlimgen Court, City: Greensboro, "
        + "State: NC, Zip: 27499";
    assertEquals(expected, patron.toString());
  }

}
