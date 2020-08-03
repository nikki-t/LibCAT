package edu.bu.met.cs665.builder;

import edu.bu.met.cs665.product.Book;
import edu.bu.met.cs665.product.Format;
import edu.bu.met.cs665.product.Person;
import edu.bu.met.cs665.product.Resource;

/**
 * Builds a Book Resource.
 * MET CS 665 Design Patterns - Class Project
 * @author Nikki Tebaldi 
 * @since 2020-07-31
 */
public class ConcreteBookBuilder implements BookBuilder {
  
  // Instance variable
  private Book book;
  
  /**
   * Initializes book member attribute to Resource sub-type Book.
   */
  public ConcreteBookBuilder() {
    this.book = new Book();
  }
  
  @Override
  public Resource getResult() {
    return book;
  }
  
  @Override
  public void reset() {
    book = new Book();    
  }

  @Override
  public void setCity(String city) {
    book.setCity(city);
  }

  @Override
  public void setCreator(Person creator) {
    book.setCreator(creator);
  }

  @Override
  public void setFormat(Format format) {
    book.setFormat(format);
  }

  @Override
  public void setId(int id) {
    book.setId(id);
  }

  @Override
  public void setLength(double length) {
    book.setLength(length);
  }

  @Override
  public void setPublisher(String publisher) {
    book.setPublisher(publisher);
  }

  @Override
  public void setTitle(String title) {
    book.setTitle(title);
  }

  @Override
  public void setType(String type) {
    book.setType(type);
  }
  
  @Override
  public void setYear(int year) {
    book.setYear(year);
  }

}
