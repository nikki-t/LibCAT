package edu.bu.met.cs665;

import static org.junit.Assert.*;

import edu.bu.met.cs665.library.LibraryCatalog;
import edu.bu.met.cs665.product.Book;
import edu.bu.met.cs665.product.Movie;

import java.io.FileNotFoundException;
import org.junit.Test;

/**
* Tests the setResourceListMethod of LibraryCatalog class.
* MET CS 665 Design Patterns - Class Project
* @author Nikki Tebaldi 
* @since 2020-07-31
*/
public class TestLibraryCatalog {

  @Test
  public void testSetResourceList() throws FileNotFoundException {
    // Create new Library catalog and run method
    LibraryCatalog lc = new LibraryCatalog();
    lc.setResourceList();
    
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

}
