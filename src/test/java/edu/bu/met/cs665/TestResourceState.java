package edu.bu.met.cs665;

import static org.junit.Assert.*;

import edu.bu.met.cs665.patron.Address;
import edu.bu.met.cs665.patron.Patron;
import edu.bu.met.cs665.resource.Book;
import edu.bu.met.cs665.resource.Physical;
import edu.bu.met.cs665.resource.Resource;
import java.time.LocalDateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the states that a Resource can be in: Available, CheckedOut, OnHold.
 * MET CS 665 Design Patterns - Class Project
 * @author Nikki Tebaldi 
 * @since 2020-07-31
 */
public class TestResourceState {
  
  Patron patron;
  Resource resource;
  
  private static Patron createAnotherPatron() {
    Patron patron = new Patron();
    patron.setAddress(new Address("Fairbanks", "AK", "14 Magdeline Point", "99790"));
    patron.setEmail("gharwick1@reverbnation.com");
    patron.setId(1002);
    patron.setName("Gallagher Harwick");
    patron.setPhone("907-438-0824");
    return patron;
  }
    
  /**
   * Resets patron hold list, resource hold queue and resource state.
   */
  private void resetResourceAndPatron() {
    patron.resetHoldList();
    resource.resetHoldQueue();
    resource.resetState();
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
    
    // Create a Resource object
    Book book = new Book();
    book.setCreator(TestUtilities.getPerson("Iain Pears"));
    book.setFormat(new Physical("regular print", "PR6066.E167 A89 2016", "25cm"));
    book.setId(158903);
    book.setLength(565);
    book.setTitle("Arcadia");
    book.setType("book");
    book.setYear(2017); 
    book.setCity("New York");
    book.setPublisher("Vintage Books");
    this.resource = book;
    
  }
  
  @After
  public void tearDown() throws Exception {
    resetResourceAndPatron();
  }
  
  /**
   * Test the ResourceState Available checkIn method.
   */
  @Test
  public void testAvailableCheckIn() {
    // Set resource state and try to check in resource
    resource.setCurrentState(resource.getAvailable());
    resource.checkIn();
    
    // Assert resource is in the same state and has no holds
    assertEquals("Available", resource.getCurrentState().getName());
    assertTrue(resource.getHoldQueue().isEmpty());    
  }
  
  /**
   * Tests the ResourceState Available checkOut method.
   */
  @Test
  public void testAvailableCheckOut() {
    // Set resource state and try to check out resource to patron
    resource.setCurrentState(resource.getAvailable());
    resource.checkOut(patron);
    
    // Assert resource patron, due date, and state
    assertEquals(patron, resource.getPatron());
    
    LocalDateTime expectedDate = LocalDateTime.now().plusDays(7);
    assertEquals(expectedDate.getYear(), resource.getDueDate().getYear());
    assertEquals(expectedDate.getMonth(), resource.getDueDate().getMonth());
    assertEquals(expectedDate.getDayOfMonth(), resource.getDueDate().getDayOfMonth());
    
    assertEquals("Checked Out", resource.getCurrentState().getName());
    
    // Assert patron check out list contains resource
    assertTrue(patron.getCheckedOutList().contains(resource));
    
    resetResourceAndPatron();
  }
  
  /**
   * Tests the ResourceState Available placeHold method when a hold already
   * exists for a specific resource.
   */
  @Test
  public void testAvailablePlaceDoubleHold() {
    // Set resource state and try to place a hold on the resource
    resource.setCurrentState(resource.getAvailable());
    resource.placeHold(patron);
    resource.placeHold(patron);
    
    // Assert resource hold queue and state\
    assertTrue(resource.getHoldQueue().contains(patron));
    assertEquals(1, resource.getHoldQueue().size());
    assertEquals("On Hold", resource.getCurrentState().getName());
    
    // Assert patron hold list
    assertTrue(patron.getHoldList().contains(resource));
    assertEquals(1, patron.getHoldList().size());
    
    resetResourceAndPatron();
  }
  
  /**
   * Tests the ResourceState Available placeHold method.
   */
  @Test
  public void testAvailablePlaceHold() {
    // Set resource state and try to place a hold on the resource
    resource.setCurrentState(resource.getAvailable());
    resource.placeHold(patron);
    
    // Assert resource hold queue and state
    assertTrue(resource.getHoldQueue().contains(patron));
    assertEquals("On Hold", resource.getCurrentState().getName());
    
    // Assert patron hold list
    assertTrue(patron.getHoldList().contains(resource));
    
    resetResourceAndPatron();
  }
  
  /**
   * Tests ResourceState CheckOut checkIn method where there are current holds 
   * on the resource.
   */
  @Test
  public void testCheckedOutCheckInHolds() {
    // Set patron and state for resource
    resource.setPatron(patron);
    resource.setCurrentState(resource.getCheckedOut());
    
    // Set hold and check in resource
    resource.addHold(createAnotherPatron());
    resource.checkIn();
    
    // Assert resource Patron, due date, and state
    assertNull(resource.getPatron());
    assertNull(resource.getDueDate());
    assertEquals("On Hold", resource.getCurrentState().getName());
    
    // Assert Patron check out list
    assertFalse(patron.getCheckedOutList().contains(resource));
    
    resetResourceAndPatron();
  }
  
  /**
   * Tests ResourceState CheckOut checkIn method where there are no current
   * holds on the resource.
   */
  @Test
  public void testCheckedOutCheckInNoHolds() {
    // Set patron and state for resource
    resource.setPatron(patron);
    resource.setCurrentState(resource.getCheckedOut());
    resource.checkIn();
    
    // Assert resource Patron, due date, and state
    assertNull(resource.getPatron());
    assertNull(resource.getDueDate());
    assertEquals("Available", resource.getCurrentState().getName());
    
    // Assert Patron check out list
    assertFalse(patron.getCheckedOutList().contains(resource));
    
    resetResourceAndPatron();
  }
  
  /**
   * Tests ResourceState CheckOut checkOut method.
   */
  @Test
  public void testCheckedOutCheckOut() {
    // Set patron and state for resource
    resource.setPatron(patron);
    resource.setCurrentState(resource.getCheckedOut());
    resource.checkOut(createAnotherPatron());
    
    // Assert Resource patron and state
    assertEquals(patron, resource.getPatron());
    assertEquals("Checked Out", resource.getCurrentState().getName());
    
    resetResourceAndPatron();
  }
  
  /**
   * Tests ResourceState CheckOut placeHold method when the patron already
   * has a hold on the resource.
   */
  @Test
  public void testCheckedOutPlaceDoubleHold() {
    // Set patron and state for resource
    resource.setPatron(patron);
    resource.setCurrentState(resource.getCheckedOut());
    
    // Place hold for another patron
    Patron patron2 = createAnotherPatron();
    resource.placeHold(patron2);
    resource.placeHold(patron2);
    
    // Assert Resource hold queue, patron and state
    assertEquals(1, resource.getHoldQueue().size());
    assertTrue(resource.getHoldQueue().contains(patron2));
    assertEquals(patron, resource.getPatron());
    assertEquals("Checked Out", resource.getCurrentState().getName());
    
    // Assert patron2 hold list
    assertEquals(1, patron2.getHoldList().size());
    assertTrue(patron2.getHoldList().contains(resource));
    
    resetResourceAndPatron();
  }
  
  
  /**
   * Tests ResourceState CheckOut placeHold method.
   */
  @Test
  public void testCheckedOutPlaceHold() {
    // Set patron and state for resource
    resource.setPatron(patron);
    resource.setCurrentState(resource.getCheckedOut());
    
    // Place hold for another patron
    Patron patron2 = createAnotherPatron();
    resource.placeHold(patron2);
    
    // Assert Resource hold queue, patron and state
    assertTrue(resource.getHoldQueue().contains(patron2));
    assertEquals(patron, resource.getPatron());
    assertEquals("Checked Out", resource.getCurrentState().getName());
    
    // Assert patron2 hold list
    assertTrue(patron2.getHoldList().contains(resource));
    
    resetResourceAndPatron();
  }
  
  /**
   * Tests ResourceState OnHold checkIn method.
   */
  @Test
  public void testOnHoldCheckIn() {
    // Place hold on resource and set resource state
    resource.placeHold(patron);
    resource.setCurrentState(resource.getOnHold());
    resource.checkIn();
    
    // Assert resource patron, due date, state and hold queue
    assertNull(resource.getPatron());
    assertNull(resource.getDueDate());
    assertEquals("On Hold", resource.getCurrentState().getName());
    assertEquals(1, resource.getHoldQueue().size());
    assertEquals(patron, resource.getHoldQueue().peek());
    
    resetResourceAndPatron();
  }
  
  /**
   * Tests ResourceState OnHold checkOut method.
   */
  @Test
  public void testOnHoldCheckOut() {
    // Place hold on resource and set resource state
    resource.placeHold(patron);
    resource.setCurrentState(resource.getOnHold());
    resource.checkOut(patron);
    
    // Assert resource patron, due date, hold queue, and state
    assertEquals(patron, resource.getPatron());
    
    LocalDateTime expectedDate = LocalDateTime.now().plusDays(7);
    assertEquals(expectedDate.getYear(), resource.getDueDate().getYear());
    assertEquals(expectedDate.getMonth(), resource.getDueDate().getMonth());
    assertEquals(expectedDate.getDayOfMonth(), resource.getDueDate().getDayOfMonth());
    
    assertFalse(resource.getHoldQueue().contains(patron));
    assertEquals(0, resource.getHoldQueue().size());
    assertEquals("Checked Out", resource.getCurrentState().getName());    
    
    // Assert patron checked out list and hold list
    assertEquals(1, patron.getCheckedOutList().size());
    assertTrue(patron.getCheckedOutList().contains(resource));
    assertFalse(patron.getHoldList().contains(resource));
    
    resetResourceAndPatron();
    
  }
  
  /**
   * Tests ResourceState OnHold checkOut method that tries to checkout to a 
   * patron is not the patron with the current hold.
   */
  @Test
  public void testOnHoldCheckOutInvalid() {
    // Place hold on resource and set resource state
    resource.placeHold(patron);
    resource.setCurrentState(resource.getOnHold());
    resource.checkOut(createAnotherPatron());
    
    // Assert resource patron, due date, state and hold queue
    assertNull(resource.getPatron());
    assertNull(resource.getDueDate());
    assertEquals("On Hold", resource.getCurrentState().getName());
    assertEquals(1, resource.getHoldQueue().size());
    assertEquals(patron, resource.getHoldQueue().peek());
    
    resetResourceAndPatron();
  }
  
  /**
   * Tests ResourceState OnHold placeHold method when the patron already
   * has a hold on the resource.
   */
  @Test
  public void testOnHoldPlaceDoubleHold() {
    // Place hold on resource and set resource state
    resource.placeHold(patron);
    resource.setCurrentState(resource.getOnHold());
    
    // Place hold for another patron
    Patron patron2 = createAnotherPatron();
    resource.placeHold(patron2);
    resource.placeHold(patron2);
    
    // Assert Resource hold queue and state
    assertEquals(2, resource.getHoldQueue().size());
    assertTrue(resource.getHoldQueue().contains(patron));
    assertTrue(resource.getHoldQueue().contains(patron2));
    assertEquals("On Hold", resource.getCurrentState().getName());
    
    // Assert patron and patron2 hold list
    assertEquals(1, patron.getHoldList().size());
    assertTrue(patron.getHoldList().contains(resource));
    assertEquals(1, patron2.getHoldList().size());
    assertTrue(patron2.getHoldList().contains(resource));
    
    resetResourceAndPatron();
  }
  
  
  /**
   * Tests ResourceState OnHold placeHold method.
   */
  @Test
  public void testOnHoldPlaceHold() {
    // Place hold on resource and set resource state
    resource.placeHold(patron);
    resource.setCurrentState(resource.getOnHold());
    
    // Place hold for another patron
    Patron patron2 = createAnotherPatron();
    resource.placeHold(patron2);
    
    // Assert Resource hold queue and state
    assertTrue(resource.getHoldQueue().contains(patron));
    assertTrue(resource.getHoldQueue().contains(patron2));
    assertEquals("On Hold", resource.getCurrentState().getName());
    
    // Assert patron and patron2 hold list
    assertTrue(patron.getHoldList().contains(resource));
    assertTrue(patron2.getHoldList().contains(resource));
    
    resetResourceAndPatron();
  }
  
}
