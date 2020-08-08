package edu.bu.met.cs665.resource;

import edu.bu.met.cs665.patron.Patron;
import edu.bu.met.cs665.state.Available;
import edu.bu.met.cs665.state.CheckedOut;
import edu.bu.met.cs665.state.OnHold;
import edu.bu.met.cs665.state.ResourceState;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Contains all attributes that make up a library resource.
 * MET CS 665 Design Patterns - Class Project
 * @author Nikki Tebaldi 
 * @since 2020-07-31
 */
public abstract class Resource {
  
  // Instance variables
  private ResourceState available;
  private ResourceState checkedOut;
  private ResourceState currentState;
  private Person creator;
  private LocalDateTime dueDate;
  private Format format;
  private Queue<Patron> holdQueue;
  private int id;
  private double length;
  private ResourceState onHold;
  private Patron patron;
  private String title;
  private String type;
  private int year;
  
  /**
   * Initializes the member attributes that represent state: available,
   * checkedOut, currentState, and onHold
   * AND the hold queue.
   */
  public Resource() {
    holdQueue = new LinkedList<>();
    this.available = new Available(this);
    this.checkedOut = new CheckedOut(this);
    this.onHold = new OnHold(this);
    this.currentState = available;
  }
  
  /**
   * Adds Patron to hold list if they are not present in the list.
   * @param patron Patron to add to hold list
   * @return boolean to indicate if added Patron to list
   */
  public boolean addHold(Patron patron) {
    if (holdQueue.contains(patron)) {
      return false;
    } else {
      holdQueue.add(patron);
      return true;
    }
  }
  
  /**
   * Check in the resource.
   */
  public void checkIn() {
    currentState.checkIn();
  }
  
  /**
   * Check out the resource to the patron.
   * @param patron Patron to check resource out to
   */
  public void checkOut(Patron patron) {
    currentState.checkOut(patron);
  }
  
  public ResourceState getAvailable() {
    return available;
  }
  
  public ResourceState getCheckedOut() {
    return checkedOut;
  }
  
  public Person getCreator() {
    return creator;
  }
  
  public ResourceState getCurrentState() {
    return currentState;
  }
  
  public LocalDateTime getDueDate() {
    return dueDate;
  }
  
  public Format getFormat() {
    return format;
  }
  
  public Queue<Patron> getHoldQueue() {
    return holdQueue;
  }
  
  public int getId() {
    return id;
  }
  
  public double getLength() {
    return length;
  }
  
  public ResourceState getOnHold() {
    return onHold;
  }
  
  public Patron getPatron() {
    return patron;
  }
  
  public String getTitle() {
    return title;
  }
  
  public String getType() {
    return type;
  }
  
  public int getYear() {
    return year;
  }
  
  /**
   * Placed a hold on the resource for the patron.
   * @param patron Patron to place hold on resource for
   */
  public void placeHold(Patron patron) {
    currentState.placeHold(patron);
  }

  /**
   * Removes patron from top of hold queue.
   * @return Patron object removed from queue
   */
  public Patron removeHold() {
    return holdQueue.remove();
  }
  
  /**
   * Clears the contents of the hold queue.
   */
  public void resetHoldQueue() {
    holdQueue.clear();
  }
  
  /**
   * Resets current state to available.
   */
  public void resetState() {
    currentState = available;
  }

  public void setCreator(Person creator) {
    this.creator = creator;
  }
  
  public void setCurrentState(ResourceState state) {
    this.currentState = state;
  }

  public void setDueDate(LocalDateTime dueDate) {
    this.dueDate = dueDate;
  }
  
  public void setFormat(Format format) {
    this.format = format;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setLength(double length) {
    this.length = length;
  }

  public void setPatron(Patron patron) {
    this.patron = patron;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setYear(int year) {
    this.year = year;
  }

  @Override
  public String toString() {
    return String.format("%nID: %d"
        + "%nTitle: %s"
        + "%nCreator: %s"
        + "%nLength: %.2f"
        + "%nYear: %d"
        + "%nType: %s"
        + "%s", 
        id, title, creator, length, year, type, format); 
  }
    
}
