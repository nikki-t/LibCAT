package edu.bu.met.cs665;

import static org.junit.Assert.*;

import edu.bu.met.cs665.builder.ConcretePatronBuilder;
import edu.bu.met.cs665.builder.PatronBuilder;
import edu.bu.met.cs665.patron.Address;
import edu.bu.met.cs665.patron.Patron;
import org.junit.Test;

/**
 * Tests the creation of Patron objects by concrete PatronBuilder sub-class 
 * objects.
 * MET CS 665 Design Patterns - Class Project
 * @author Nikki Tebaldi 
 * @since 2020-07-31
 */
public class TestPatronBuilder {
  
  /**
   * Build Patron.
   * @param patronBuilder PatronBuilder obect
   */
  private static void buildPatron(PatronBuilder patronBuilder) {
    patronBuilder.setAddress(new Address("Wichita", "KS", "449 Northfield Hill", "67260"));
    patronBuilder.setEmail("jquant0@liveinternet.ru");
    patronBuilder.setId(1001);
    patronBuilder.setName("Jordana Quant");
    patronBuilder.setPhone("316-844-1784");
  }
  
  /**
   * Test building a Patron object.
   */
  @Test
  public void testPatron() {
    // Create the PatronBuilder
    PatronBuilder pb = new ConcretePatronBuilder();
    
    // Build the Patron
    buildPatron(pb);
    
    // Assert expected Patron attributes
    String expected = "\nID: 1001"
        + "\nName: Jordana Quant"
        + "\nPhone: 316-844-1784"
        + "\nEmail: jquant0@liveinternet.ru"
        + "\nAddress: Street: 449 Northfield Hill, City: Wichita, "
        + "State: KS, Zip: 67260";
    assertEquals(expected, pb.getResult().toString());
    
  }
  
  /**
   * Test the reset method.
   */
  @Test
  public void testReset() {    
    // Create the PatronBuilder
    PatronBuilder pb = new ConcretePatronBuilder();
    
    // Build the Patron
    buildPatron(pb);
    
    // Assert not null
    assertNotNull(pb.getResult());
    
    // Reset and assert
    pb.reset();
    Patron p = pb.getResult();
    assertNull(p.getAddress());
    assertNull(p.getEmail());
    assertEquals(0, p.getId());
    assertNull(p.getName());
    assertNull(p.getPhone());
  }

}
