package edu.bu.met.cs665.builder;

import edu.bu.met.cs665.resource.Format;
import edu.bu.met.cs665.resource.Person;
import edu.bu.met.cs665.resource.Resource;

/**
 * Specifies all operations required to build a Resource.
 * MET CS 665 Design Patterns - Class Project
 * @author Nikki Tebaldi 
 * @since 2020-07-31
 */
public interface ResourceBuilder {
  
  /**
   * Returns a resource or any resource sub-types after the resource has been 
   * built.
   * @return Resource object
   */
  Resource getResult();
  
  /**
   * Resets the member attribute reference of the Resource that was built.
   */
  void reset();
  
  /**
   * Set the creator of the resource.
   * @param creator String creator of resource
   */
  void setCreator(Person creator);
  
  /**
   * Set the format of the resource.
   * @param format Format object of resource
   */
  void setFormat(Format format);
  
  /**
   * Set the identifier of the resource.
   * @param id int identifier of the resource
   */
  void setId(int id);
  
  /**
   * Set the length of the resource.
   * @param length double length of the resource
   */
  void setLength(double length);
  
  /**
   * Set the title of the resource.
   * @param title String title of the resource
   */
  void setTitle(String title);
  
  /**
   * Set the type of the resource. 
   * @param type String that indicates resource type
   */
  void setType(String type);
  
  /**
   * Set the year the resource was created.
   * @param year int year the resource was created
   */
  void setYear(int year);
  
}
