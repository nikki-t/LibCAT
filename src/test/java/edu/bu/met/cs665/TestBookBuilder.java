package edu.bu.met.cs665;

import static org.junit.Assert.*;

import edu.bu.met.cs665.builder.BookBuilder;
import edu.bu.met.cs665.builder.ConcreteBookBuilder;
import edu.bu.met.cs665.product.Book;
import edu.bu.met.cs665.product.Electronic;
import edu.bu.met.cs665.product.Physical;
import org.junit.Test;

/**
 * Tests the creation of Electronic and Physical Book objects by concrete 
 * BookBuilder sub-class objects..
 * MET CS 665 Design Patterns - Class Project
 * @author Nikki Tebaldi 
 * @since 2020-07-31
 */
public class TestBookBuilder {
  
  /**
   * Build Electronic Book.
   * @param bookBuilder BookBuilder object
   */
  private static void buildElectronicBook(BookBuilder bookBuilder) {
    bookBuilder.setCity("New York");
    bookBuilder.setCreator(TestUtilities.getPerson("Iain Pears"));
    bookBuilder.setFormat(new Electronic("Kindle Book", 
        "https://www.overdrive.com/media/3210644/arcadia", "952.9KB"));
    bookBuilder.setId(158903);
    bookBuilder.setLength(565);
    bookBuilder.setPublisher("Vintage Books");
    bookBuilder.setTitle("Arcadia");
    bookBuilder.setType("book");
    bookBuilder.setYear(2017);
  }
  
  /**
   * Build Physical Book.
   * @param bookBuilder BookBuilder object
   */
  private static void buildPhysicalBook(BookBuilder bookBuilder) {
    bookBuilder.setCity("New York");
    bookBuilder.setCreator(TestUtilities.getPerson("Iain Pears"));
    bookBuilder.setFormat(new Physical("regular print", "PR6066.E167 A89 2016", "25cm"));
    bookBuilder.setId(158903);
    bookBuilder.setLength(565);
    bookBuilder.setPublisher("Vintage Books");
    bookBuilder.setTitle("Arcadia");
    bookBuilder.setType("book");
    bookBuilder.setYear(2017);
  }
  
  
  /**
   * Test the building of an Electronic Book including a test of the getResult
   * method.
   */
  @Test
  public void testElectronicBook() {
    // Create the BookBuider object
    BookBuilder bookBuilder = new ConcreteBookBuilder();
    
    // Build the Book
    buildElectronicBook(bookBuilder);
   
    // Get the Book product
    Book book = (Book) bookBuilder.getResult();
    
    // Assert expected book attributes
    String expected = "\nID: 158903"
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
   * Test the building of a Physical Book including a test of the getResult
   * method.
   */
  @Test
  public void testPhysicalBook() {
    // Create the BookBuider object
    BookBuilder bookBuilder = new ConcreteBookBuilder();
    
    // Build the Book
    buildPhysicalBook(bookBuilder);
   
    // Get the Book product
    Book book = (Book) bookBuilder.getResult();
    
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
   * Test the reset method.
   */
  @Test
  public void testReset() {
    // Create MovieBuilder object
    BookBuilder bookBuilder = new ConcreteBookBuilder();
    
    // Build the Book
    buildPhysicalBook(bookBuilder);
    
    // Assert not null
    assertNotNull(bookBuilder.getResult());
    
    // Reset and assert attributes
    bookBuilder.reset();
    Book book = (Book) bookBuilder.getResult();
    assertNull(book.getCity());
    assertNull(book.getCreator());
    assertNull(book.getFormat());
    assertEquals(0, book.getId());
    assertEquals(0.0, book.getLength(), 0.01);
    assertNull(book.getPublisher());
    assertNull(book.getTitle());
    assertNull(book.getType());
    assertEquals(0, book.getYear());
  }

}
