package edu.bu.met.cs665;

import static org.junit.Assert.*;

import edu.bu.met.cs665.builder.ConcreteMovieBuilder;
import edu.bu.met.cs665.builder.MovieBuilder;
import edu.bu.met.cs665.product.Electronic;
import edu.bu.met.cs665.product.Movie;
import edu.bu.met.cs665.product.Person;
import edu.bu.met.cs665.product.Physical;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

/**
* Tests the creation of Electronic and Movie Book objects by concrete 
* MovieBuilder sub-class objects..
* MET CS 665 Design Patterns - Class Project
* @author Nikki Tebaldi 
* @since 2020-07-31
*/
public class TestMovieBuilder {
  
  /**
   * Set cast list for movie builder object.
   * @param movieBuilder MovieBuilder object
   */
  private static void setCast(MovieBuilder movieBuilder) {
    // Create cast list
    List<Person> castList = new ArrayList<Person>() {
      private static final long serialVersionUID = 1L;

      {
        add(TestUtilities.getPerson("Orson Welles"));
        add(TestUtilities.getPerson("Joseph Cotton"));
        add(TestUtilities.getPerson("Dorothy Comingore"));
      }
    };
    movieBuilder.setCast(castList);
  }
    

  /**
   * Build Electronic Movie.
   * @param movieBuilder MovieBuilder object
   */
  private static void buildElectronicMovie(MovieBuilder movieBuilder) {
    setCast(movieBuilder);
    movieBuilder.setCreator(TestUtilities.getPerson("Orson Welles"));
    movieBuilder.setFormat(new Electronic("stream", 
        "https://libraryfilms.net/citizen-kane", "20GB"));
    movieBuilder.setId(158910);
    movieBuilder.setLength(119);
    movieBuilder.setRating("PG");
    movieBuilder.setTitle("Citizen Kane");
    movieBuilder.setType("movie");
    movieBuilder.setYear(1941);
  }
  
  /**
   * Build Physical Movie.
   * @param movieBuilder MovieBuilder object
   */
  private static void buildPhysicalMovie(MovieBuilder movieBuilder) {
    setCast(movieBuilder);
    movieBuilder.setCreator(TestUtilities.getPerson("Orson Welles"));
    movieBuilder.setFormat(new Physical("DVD", "MAIN - DVD", "4.75in"));
    movieBuilder.setId(158910);
    movieBuilder.setLength(119);
    movieBuilder.setRating("PG");
    movieBuilder.setTitle("Citizen Kane");
    movieBuilder.setType("movie");
    movieBuilder.setYear(1941);
  }
  
  /**
   * Test the building of an Electronic Movie including a test of the getResult
   * method.
   */
  @Test
  public void testElectronicMovie() {
    // Create MovieBuilder object
    MovieBuilder movieBuilder = new ConcreteMovieBuilder();
    
    // Build Movie product
    buildElectronicMovie(movieBuilder);
    
    // Get the Movie product
    Movie movie = (Movie) movieBuilder.getResult();
    
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
   * Test the building of a Physical Movie including a test of the getResult
   * method.
   */
  @Test
  public void testPhysicalMovie() {
    // Create MovieBuilder object
    MovieBuilder movieBuilder = new ConcreteMovieBuilder();
    
    // Build Movie product
    buildPhysicalMovie(movieBuilder);
    
    // Get the Movie product
    Movie movie = (Movie) movieBuilder.getResult();
    
    // Assert expected movie attributes
    String expected = "\nID: 158910"
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
  
  /**
   * Test the reset method.
   */
  @Test
  public void testReset() {
    // Create MovieBuilder object
    MovieBuilder movieBuilder = new ConcreteMovieBuilder();
    
    // Build Movie product
    buildPhysicalMovie(movieBuilder);
    
    // Assert not null
    assertNotNull(movieBuilder.getResult());
    
    // Reset and assert attributes
    movieBuilder.reset();
    Movie movie = (Movie) movieBuilder.getResult();
    assertNull(movie.getCastList());
    assertNull(movie.getCreator());
    assertNull(movie.getFormat());
    assertEquals(0, movie.getId());
    assertEquals(0.0, movie.getLength(), 0.01);
    assertNull(movie.getRating());
    assertNull(movie.getTitle());
    assertNull(movie.getType());
    assertEquals(0, movie.getYear());
  }

}
