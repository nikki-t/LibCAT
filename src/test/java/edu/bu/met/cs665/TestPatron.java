package edu.bu.met.cs665;

import static org.junit.Assert.*;

import edu.bu.met.cs665.patron.Address;
import edu.bu.met.cs665.patron.Patron;
import edu.bu.met.cs665.resource.Book;
import edu.bu.met.cs665.resource.Physical;
import edu.bu.met.cs665.resource.Resource;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the creation of Patron objects as well as the equals, 
 * addCheckedOutResource, addHold, and checkAndRemoveHold methods.
 * MET CS 665 Design Patterns - Class Project
 * @author Nikki Tebaldi 
 * @since 2020-07-31
 */
public class TestPatron {
  
  Patron patron;
  
  /**
   * Creates a Book Resource object.
   * @return Resource
   */
  private Resource createResource() {
    
    Book resource = new Book();
    resource.setCity("New York");
    resource.setCreator(TestUtilities.getPerson("Iain Pears"));
    resource.setFormat(new Physical("regular print", "PR6066.E167 A89 2016", "25cm"));
    resource.setId(158903);
    resource.setLength(565);
    resource.setPublisher("Vintage Books");
    resource.setTitle("Arcadia");
    resource.setType("book");
    resource.setYear(2017);  
    
    return (Resource) resource;
  }
  
  @Before
  public void setUp() throws Exception {
    // Create Patron object
    patron = new Patron();
    patron.setAddress(new Address("Wichita", "KS", "449 Northfield Hill", "67260"));
    patron.setEmail("jquant0@liveinternet.ru");
    patron.setId(1001);
    patron.setName("Jordana Quant");
    patron.setPhone("316-844-1784");
  }
  
  /**
   * Test adding a hold (Resource) to the Patron's hold list.
   */
  @Test
  public void testAddHold() {
    Resource resource = createResource();
    assertTrue(patron.addHold(resource));    
  }
  
  /**
   * Test adding a hold (Resource) to the Patron's hold list that is already
   * in the list.
   */
  @Test
  public void testAddHoldDuplicate() {
    patron.resetHoldList();
    Resource resource = createResource();
    patron.addHold(resource);
    assertFalse(patron.addHold(resource));
    
  }
  
  /**
   * Tests removing a hold (Resource) from the Patron's hold list.
   */
  @Test
  public void testCheckAndRemoveHold() {
    patron.resetHoldList();
    Resource resource = createResource();
    patron.addHold(resource);
    assertTrue(patron.checkAndRemoveHold(resource));
  }
  
  /**
   * Tests removing a hold (Resource) from the Patron's hold list that doesn't
   * exist.
   */
  @Test
  public void testCheckAndRemoveHoldNoExist() {
    patron.resetHoldList();
    Resource resource = createResource();
    assertFalse(patron.checkAndRemoveHold(resource));    
  }
  
  /**
   * Test the equals method of the Patron class on an equal Patron.
   */
  @Test
  public void testEquals() {
    
    // Create a patron object with same data for comparison
    Patron patron2 = new Patron();
    Address address = new Address("Wichita", "KS", "449 Northfield Hill", 
        "67260");
    patron2.setAddress(address);
    patron2.setEmail("jquant0@liveinternet.ru");
    patron2.setId(1001);
    patron2.setName("Jordana Quant");
    patron2.setPhone("316-844-1784");

    assertTrue(patron.equals(patron2));
  }
  
  /**
   * Test the equals method of the Patron class on an unequal Patron.
   */
  @Test
  public void testEqualsNot() {
    
    // Create a patron object with same data for comparison
    Patron patron2 = new Patron();
    Address address = new Address("Wichita", "KS", "449 Northfield Hill", 
        "67260");
    patron2.setAddress(address);
    patron2.setEmail("rpues4@ox.ac.uk");
    patron2.setId(1005);
    patron2.setName("Rois Pues");
    patron2.setPhone("432-689-6552");

    assertFalse(patron.equals(patron2));
  }

  /**
   * Test the creation of a Patron object.
   */
  @Test
  public void testPatron() {
    String expected = "\nID: 1001"
        + "\nName: Jordana Quant"
        + "\nPhone: 316-844-1784"
        + "\nEmail: jquant0@liveinternet.ru"
        + "\nAddress: Street: 449 Northfield Hill, City: Wichita, "
        + "State: KS, Zip: 67260";
    assertEquals(expected, patron.toString());
  }
  
}
