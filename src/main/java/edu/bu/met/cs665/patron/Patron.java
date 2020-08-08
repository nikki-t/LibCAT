package edu.bu.met.cs665.patron;

import edu.bu.met.cs665.resource.Resource;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Represents a patron who can check out, check in, and place hold on Resources.
 * MET CS 665 Design Patterns - Class Project
 * @author Nikki Tebaldi 
 * @since 2020-07-31
 */
public class Patron {
  
  // Class variable
  private static Logger logger = Logger.getLogger(Patron.class);
  
  // Instance variables
  private Address address;
  private List<Resource> checkOutList;
  private String email;
  private List<Resource> holdList;
  private int id;
  private String name;
  private String phone;
  
  
  /**
   * Initializes all Patron lists: check out list and hold list.
   */
  public Patron() {
    this.checkOutList = new ArrayList<>();
    this.holdList = new ArrayList<>();
  }
  
  /**
   * Patron checks out a Resource and it is added to their checked out list.
   * @param resource Resource object
   */
  public void addCheckedOutResource(Resource resource) {
    checkOutList.add(resource);
  }
  
  /**
   * Patron places a hold on a Resource and it is added to their hold list
   * if it is not already present in the list.
   * @param resource Resource object
   * @return boolean to indicate if added to hold list
   */
  public boolean addHold(Resource resource) {
    if (holdList.contains(resource)) {
      return false;
    } else {
      holdList.add(resource);
      return true;
    }
  }
  
  /**
   * Check if the Patron has a hold on the resource and if so remove it from
   * the hold list.
   * @param resource Resource object
   * @return boolean value that indicates if removal was successful
   */
  public boolean checkAndRemoveHold(Resource resource) {
    if (holdList.contains(resource)) {
      holdList.remove(resource);
      return true;
    }
    return false;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    
    if (obj == null) {
      return false;
    }
    
    if (getClass() != obj.getClass()) { 
      return false;    
    }
    
    Patron other = (Patron) obj;
    if (address == null) {
      if (other.address != null) {
        return false;
      }
    } else if (!address.equals(other.address)) {
      return false;
    }
    
    if (email == null) {
      if (other.email != null) {
        return false;
      }
    } else if (!email.equals(other.email)) {
      return false;
    }
    
    if (id != other.id) {
      return false;
    }
    
    if (name == null) {
      if (other.name != null) {
        return false;
      }
    } else if (!name.equals(other.name)) {
      return false;
    }
    
    if (phone == null) {
      if (other.phone != null) {
        return false;
      }
    } else if (!phone.equals(other.phone)) {
      return false;
    }
   
    return true;
  }

  public Address getAddress() {
    return address;
  }

  public List<Resource> getCheckedOutList() {
    return checkOutList;
  }

  public String getEmail() {
    return email;
  }

  public List<Resource> getHoldList() {
    return holdList;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }
  
  public String getPhone() {
    return phone;
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((address == null) ? 0 : address.hashCode());
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + id;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((phone == null) ? 0 : phone.hashCode());
    return result;
  }
  
  /**
   * Remove resource from Patron's checked out list.
   * @param resource Resource object
   */
  public void removeFromCheckOut(Resource resource) {
    checkOutList.remove(resource);
  }
  
  /**
   * Reset the hold list.
   */
  public void resetHoldList() {
    holdList.clear();
  }
  
  /**
   * Sends Patron an email.
   * @param message String message to send
   */
  public void sendEmail(String message) {
    logger.info("\nThe following email was sent to " + email + ": ");
    logger.info("\t" + message);
  }
  
  public void setAddress(Address address) {
    this.address = address;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public void setName(String name) {
    this.name = name;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  @Override
  public String toString() {
    return String.format("%nID: %d"
        + "%nName: %s"
        + "%nPhone: %s"
        + "%nEmail: %s"
        + "%nAddress: %s", id, name, phone, email, address);
  }

}
