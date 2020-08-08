package edu.bu.met.cs665;

import static org.junit.Assert.*;

import edu.bu.met.cs665.resource.Book;
import edu.bu.met.cs665.resource.Electronic;
import edu.bu.met.cs665.resource.Physical;
import edu.bu.met.cs665.resource.Resource;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the creation of Electronic and Physical objects which are Format
 * sub-types.
 * MET CS 665 Design Patterns - Class Project
 * @author Nikki Tebaldi 
 * @since 2020-07-31
 */
public class TestFormat {
  
  Resource resource;
  
  /**
   * Set format to Electronic.
   * @param resource Resource object
   */
  private static void setElectronic(Resource resource) {
    resource.setFormat(new Electronic("Kindle Book", 
        "https://www.overdrive.com/media/3210644/arcadia", "952.9KB"));   
  }

  /**
   * Set format to Physical.
   * @param resource Resource object
   */
  private static void setPhysical(Resource resource) {
    resource.setFormat(new Physical("regular print", "PR6066.E167 A89 2016", "25cm"));    
  }
  
  @Before
  public void setUp() throws Exception {
    // Set resource attributes for book sub-type
    resource = new Book();
    resource.setCreator(TestUtilities.getPerson("Iain Pears"));
    resource.setId(158903);
    resource.setLength(565);
    resource.setTitle("Arcadia");
    resource.setType("book");
    resource.setYear(2017);
  }
  
  /**
   * Tests Electronic format attributes.
   */
  @Test
  public void testElectronic() {
    // Set electronic attributes
    setElectronic(resource);
    
    // Assert expected attributes
    String expected = "\nFile Format: Kindle Book"
        + "\nDatabase URL: https://www.overdrive.com/media/3210644/arcadia"
        + "\nFile Size: 952.9KB";
    assertEquals(expected, resource.getFormat().toString());
  }
  
  /**
   * Tests Physical format attributes.
   */
  @Test
  public void testPhysical() {
    // Set physical attributes
    setPhysical(resource);
    
    // Assert expected attributes
    String expected = "\nFormat: regular print"
        + "\nCall Number: PR6066.E167 A89 2016"
        + "\nSize: 25cm";
    assertEquals(expected, resource.getFormat().toString());
  }

}
