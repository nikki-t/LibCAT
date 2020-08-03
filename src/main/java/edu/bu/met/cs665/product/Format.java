package edu.bu.met.cs665.product;

/**
 * Specifies all operations required of a Format-type Resource.
 * MET CS 665 Design Patterns - Class Project
 * @author Nikki Tebaldi 
 * @since 2020-07-31
 */
public interface Format {
  
  /**
   * Returns the actual format (e.g. large print, pdf, mpeg) of a resource 
   * which is based on the format.
   */
  String getResourceFormat();
  
  /**
   * Returns the location of a resource which is based on the format.
   */
  String getLocation();
  
  /**
   * Returns the size of a resource which is based on the format.
   */
  String getSize();
  
}
